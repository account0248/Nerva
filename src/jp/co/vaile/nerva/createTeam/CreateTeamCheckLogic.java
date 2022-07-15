package jp.co.vaile.nerva.createTeam;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TeamIDFormatCheck;


public class CreateTeamCheckLogic {

	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
	FormatCheck teamIDFormatCheck = new TeamIDFormatCheck();
	CommonConstants commonConstants = new CommonConstants();
	CheckViewingAuthority viewingAuthority = new CheckViewingAuthority();

	public List<String> checkCreateTeam(CreateTeamInfoPageDTO createTeamInfoPageDTO,String companyCode) throws ClassNotFoundException, SQLException {

		List<String> errorList = new ArrayList<String>();

		// チームIDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkCreateTeamId(createTeamInfoPageDTO.getTeamId(), createTeamInfoPageDTO.getCompanyId(),companyCode), errorList);

		// チーム名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkCreateTeamName(createTeamInfoPageDTO.getTeamName()), errorList);

		return errorList;

	}

	// チームIDのエラーチェックを行う。
	public String checkCreateTeamId(String teamId, String companyId,String companyCode) throws ClassNotFoundException, SQLException {
		String[] teamIdList = { TEAM_ID, "10" };
		//	チームIDが入力されていない場合、エラーメッセージを返す。
		if (lengthCheck.isNomNullOrNomEmpty(teamId) == false) {

			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, teamIdList);
		}

		//	チームIDが半角英数字で入力されてない場合、エラーメッセージを返す。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(teamId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, teamIdList);
		}


		//	チームIDが10文字で入力されてない場合、エラーメッセージを返す。
		if (lengthCheck.isStringLength(teamId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, teamIdList);
		}

		//  チームIDが形式通りに入力されていない場合、エラーメッセージを返す。
		if (teamIDFormatCheck.isCorrectFormat(teamId) == false) {
			return errorMsg.returnErrorMsg(PROJECT_ID_FORMAT_ERROR_MESSAGE, teamIdList);
		}

		//所属会社に沿ったチームIDが入力されていない場合、エラーメッセージを返す。
		if (!(teamId.substring(5,6).equals(companyCode))){
	
			return errorMsg.returnErrorMsg(PROJECT_ID_FORMAT_ERROR_MESSAGE, teamIdList);
		}

		// チームIDが登録されている場合、エラーメッセージを返す。
		CreateTeamCheckDAO createTeamCheckDAO = new CreateTeamCheckDAO();
		int numTeamId = createTeamCheckDAO.selectTeamId(teamId);
		if (numTeamId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, teamIdList);
		}
		return null;

	}

	// チーム名のエラーチェックを行う。
	public String checkCreateTeamName(String teamName) {
		String[] teamNameList = { TEAM_NAME, "20" };
		//		チーム名が入力されていない場合、エラーメッセージを返す。
		if (lengthCheck.isNomNullOrNomEmpty(teamName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, teamNameList);
		}
		//		入力されている場合2に進む。
		//		チーム名が20文字以内で入力されてない場合、エラーメッセージを返す。
		if (lengthCheck.isMaxStringLength(teamName, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, teamNameList);
		}
		//		入力されている場合nullを返す。
		return null;
	}



}