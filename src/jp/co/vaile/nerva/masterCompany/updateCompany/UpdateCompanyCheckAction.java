package jp.co.vaile.nerva.masterCompany.updateCompany;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class UpdateCompanyCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * @param updateCompanyDTO
	 * @return errorList
	 */
	public List<String> checkUpdateCompany(UpdateCompanyDTO updateCompanyDTO) {
		List<String> errorList = new ArrayList<String>();
		errorList = errorMsg.errorMsgNullCheck(checkUpdateCompanyName(updateCompanyDTO.getCompanyName()),
				errorList);
		return errorList;
	}

	/**
	 * 所属会社名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param companyName 所属会社マスタメンテナンス画面の更新フォームで入力された所属会社名
	 * @return エラーメッセージ
	 */
	public String checkUpdateCompanyName(String[] companyName) {

		String[] companyNameErrorList = { CommonConstants.COMPANY_NAME, "10" };

		for (int i = 0; i < companyName.length; i++) {
			// 未入力の場合、エラーメッセージを返す
			if (!lengthCheck.isNomNullOrNomEmpty(companyName[i])) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, companyNameErrorList);
			}
			// 入力された値が10文字以内でなければ、エラーメッセージを返す
			else if (!lengthCheck.isMaxStringLength(companyName[i], 10)) {
				return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, companyNameErrorList);
			}
			// 入力された値が重複していた場合、エラーメッセージを返す
			CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
			int numCompanyName = 0;
			try {
				numCompanyName = checkDuplicateDAO.checkDuplicate(companyName[i],M_BELONG_COMPANY, COLUMN_COMPANY_NAME);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if (numCompanyName != 0) {
				return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, companyNameErrorList);
			}
			//重複処理
			for(int j = 0; j <companyName.length;j++) {
				if(i != j && companyName[i].equals(companyName[j])) {
					return errorMsg.returnErrorMsg(DUPLICATE_INPUT_ERROR_MESSAGE, companyNameErrorList);
				}
			}
		}
		return null;
	}
}
