package jp.co.vaile.nerva.masterContract.updateContract;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
public class UpdateContractCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	public List<String> checkUpdateContract(UpdateContractDTO updateContractDTO) {
		List<String> errorList = new ArrayList<String>();
		// 契約形態名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdateContractName(updateContractDTO.getContractName()),
				errorList);
		return errorList;
	}

	/**
	 * 契約形態名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param ContractName 契約形態マスタメンテナンス画面の更新フォームで入力された契約形態名
	 * @return エラーメッセージ
	 */
	private String checkUpdateContractName(String[] contractName) {

		String[] contractNameErrorList = { CommonConstants.CONTRACT_TYPE_NAME, "10" };

		for (int i = 0; i < contractName.length; i++) {
			// 1.未入力の場合、エラーメッセージを返す。
			// 入力されている場合2に進む。
			if (!lengthCheck.isNomNullOrNomEmpty(contractName[i])) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, contractNameErrorList);
			}
			// 入力された値が10文字以内でなければ、エラーメッセージを返す。
			else if (!lengthCheck.isMaxStringLength(contractName[i], 10)) {
				return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, contractNameErrorList);
			}
			// 重複処理
			CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
			int numContractName = 0;
			try {
				numContractName = checkDuplicateDAO.checkDuplicate(contractName[i],M_CONTRACT_TYPE, COLUMN_CONTRACT_TYPE);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if (numContractName != 0) {
				return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, contractNameErrorList);
			}
		}
		return null;
	}
}
