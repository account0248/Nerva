package jp.co.vaile.nerva.masterContract.insertContract;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.MasterContents;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ContractIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class InsertContractCheckAction{
	
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
	
	public List<String> checkInsertContract(InsertContractDTO insertContractDTO) throws ClassNotFoundException, SQLException {
		List<String> errorList = new ArrayList<String>();
		// 1.P1の契約形態IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertContractId(insertContractDTO.getContractId()), errorList);
		// 2.P1の契約形態名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertContractName(insertContractDTO.getContractName()), errorList);
		return errorList;
	}
	
	private String checkInsertContractId (String contractId) throws ClassNotFoundException, SQLException {
		String[] contractIdList = { CommonConstants.CONTRACT_TYPE_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(contractId) == false) {
			return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE, contractIdList);
		}
		// 2. P1が10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isStringLength(contractId, 10) == false) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, contractIdList);
		}
		// 3.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(contractId) == false) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, contractIdList);
		}
		/*
		 * 4. P1が形式通りに入力されていない場合、エラーリストにエラーメッセージを追加する 
		 */
		ContractIDFormatCheck contractIDFormatCheck = new ContractIDFormatCheck();
		if (!contractIDFormatCheck.isCorrectFormat(contractId)) {
			return errorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE, contractIdList);
		}
		//重複チェック
		int numContractId = checkDuplicateDAO.checkDuplicate(contractId, MasterContents.M_CONTRACT_TYPE, MasterContents.COLUMN_CONTRACT_TYPE_ID);
		if (numContractId != 0) {
			return errorMsg.returnErrorMsg(CommonConstants.ID_EXIST_ERROR_MESSAGE, contractIdList);
		}
		return null;
	}
	
	private String checkInsertContractName (String contractName) throws ClassNotFoundException, SQLException {
		String[] contractNameList = { CommonConstants.CONTRACT_TYPE_NAME, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(contractName) == false) {
			return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE, contractNameList);
		}
		// 2. P1が10文字以下で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMaxStringLength(contractName, 10) == false) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, contractNameList);
		}
		//重複チェック
		int numContractName = checkDuplicateDAO.checkDuplicate(contractName, MasterContents.M_CONTRACT_TYPE, MasterContents.COLUMN_CONTRACT_TYPE);
		if (numContractName != 0) {
			return errorMsg.returnErrorMsg(CommonConstants.ID_EXIST_ERROR_MESSAGE, contractNameList);
		}
		return null;
	}
}
