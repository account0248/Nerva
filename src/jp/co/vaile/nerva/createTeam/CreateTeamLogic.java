package jp.co.vaile.nerva.createTeam;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCommonCompanyDTO;
import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCompany;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class CreateTeamLogic {


	/**
	 * チーム登録処理を呼び出す。
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 *
	 */
	String createTeam(CreateTeamInfoPageDTO createTeamInfoPageDTO, String loginUserId) throws ClassNotFoundException, SQLException {

		int result = 0;
		String errorMessage = null;

		// チーム情報をチーム情報登録DTOに移す。
		CreateTeamInfoDTO createTeamInfoDTO = new CreateTeamInfoDTO();
		createTeamInfoDTO.setTeamId(createTeamInfoPageDTO.getTeamId());
		createTeamInfoDTO.setTeamName(createTeamInfoPageDTO.getTeamName());
		createTeamInfoDTO.setManagerId(createTeamInfoPageDTO.getManagerId());

		//登録者IDをログインユーザーIDにする
		createTeamInfoDTO.setRegistUser(loginUserId);

		// 作成したチーム情報登録DTOを引数にチーム情報登録処理を呼び出す。
		CreateTeamDAO createTeamDAO = new CreateTeamDAO();
		result = createTeamDAO.insertTeamInfo(createTeamInfoDTO);

		if (result == 0) {

			ErrorMsg errorMsg = new ErrorMsg();
			String[] createTeamList = { CREATE_TEAM };
			return errorMsg.returnErrorMsg(PROCESS_FAILURE_ERROR_MESSAGE, createTeamList);

		}
		return errorMessage;
	}
	
	
	/**
	 * 所属会社検索処理を呼び出す。戻り値として所属会社名を返す。
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 *
	 */
	String getCompanyName (String userId,String companyId, String companyPrivilege) throws ClassNotFoundException, SQLException {
		SearchCompany searchCompany = new SearchCompany();
 		List<SearchCommonCompanyDTO> companyList=new ArrayList<>();
 		String companyName = "";
		
 		// 所属会社グループ権限とユーザーIDを引数に所属会社検索処理を呼び出す。
		companyList = searchCompany.searchCompanyAllCode(companyPrivilege, userId);
		
		// 所属会社名グループ権限が0(子会社)の場合、Listの0番目に格納された所属会社名を取り出す。
		if (companyPrivilege.equals(String.valueOf(SUBCOMPANY_VALUE))) {
			companyName = companyList.get(0).getCompanyName();
			
		// 所属会社名グループ権限が親会社(1)の場合、Listの所属会社IDを繰り返し処理を用いて取り出す。
		}else if(companyPrivilege.equals(String.valueOf(PARENT_COMPANY_VALUE))) {
			for(int i=0 ; i < companyList.size(); i++) {
				String resultComanyId = "";
				resultComanyId = companyList.get(i).getCompanyId();
				// Listから取り出した所属会社IDとログインユーザーの所属会社IDが等しい場合、Listから所属会社名を取り出す。
				if(resultComanyId.equals(companyId)) {
					companyName = companyList.get(i).getCompanyName();
				}
			}
		}	
		return companyName;
	}
}
