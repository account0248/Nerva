package jp.co.vaile.nerva.masterIndustry.updateIndustry;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class UpdateIndustryLogicCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param updateIndustryDTO
	 * @return errorList
	 **/
	public List<String> checkUpdateIndustry(UpdateIndustryDTO updateIndustryDTO) {
		List<String> errorList = new ArrayList<String>();
		// 業種名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdateIndustryName(updateIndustryDTO.getIndustryName()), errorList);
		return errorList;
	}

	/**
	 * 業種名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param industryName 業種マスタメンテナンス画面の更新フォームで入力された業種名
	 * @return エラーメッセージ
	 */
	private String checkUpdateIndustryName(String[] industryName) {

		String[] industryNameErrorList = { CommonConstants.INDUSTRY_NAME, "10" };
		// String[] industryNameList = { CommonConstants.INDUSTRY_NAME };
		for (int i = 0; i < industryName.length; i++) {
			// 未入力の場合、エラーメッセージを返す。
			if (!lengthCheck.isNomNullOrNomEmpty(industryName[i])) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, industryNameErrorList);
			}
			// 入力された値が10文字以内でなければ、エラーメッセージを返す。
			else if (!lengthCheck.isMaxStringLength(industryName[i], 10)) {
				return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, industryNameErrorList);
			}
			// 入力された値が既に使用されているならば、エラーメッセージを返す。
			CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
			int numIndustryName = 0;

			try {
				numIndustryName = checkDuplicateDAO.checkDuplicate(industryName[i], M_INDUSTRY, COLUMN_INDUSTRY_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (numIndustryName != 0) {
				return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, industryNameErrorList);
			}
			// 同名で一度に複数更新された場合、エラーメッセージを返す。
			for (int j = 0; j < industryName.length; j++) {
				if (i != j && industryName[i].equals(industryName[j])) {
					return errorMsg.returnErrorMsg(DUPLICATE_INPUT_ERROR_MESSAGE, industryNameErrorList);
				}
			}
		}
		return null;
	}
}
