package jp.co.vaile.nerva.masterContract.searchContract;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchContractLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	public List<String> checkSearchContract(SearchContractDTO searchContractDTO) {
		List<String> errorList = new ArrayList<String>();
		//契約形態IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchContractId(searchContractDTO.getContractId()), errorList);
		//契約形態名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchContractName(searchContractDTO.getContractName()), errorList);

		return errorList;
	}
	
	/**契約形態IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param employeeId 契約形態マスタメンテナンス画面に記載された契約形態ID
	 * @return エラーメッセージ
	 */
	private String checkSearchContractId(String contractId) {

		String[] ContractIderrorList= { CommonConstants.CONTRACT_TYPE_ID, "10"};
		String[] ContractIdList = { CommonConstants.CONTRACT_TYPE_ID };

		if (!lengthCheck.isNomNullOrNomEmpty(contractId)) {
			return null;
		}
		//入力された値が10文字でなければ、エラーメッセージを返す。
				else if (!lengthCheck.isStringLength(contractId, 10)) {
					return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, ContractIderrorList);
				}
		//半角英数字で入力されてない場合、エラーメッセージを返す。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(contractId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, ContractIdList);
		}
		return null;
	}
	/**
	 * 契約形態名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param projectName 契約形態マスタメンテナンス画面に記載された契約形態名
	 * @return　エラーメッセージ
	 */

	private String checkSearchContractName(String ContractName) {
		
		//入力された値が10文字以内でなければ、エラーメッセージを返す。
		String[] ContractNameList = { CommonConstants.CONTRACT_TYPE_NAME,"10"};
		if (!lengthCheck.isNomNullOrNomEmpty(ContractName)) {
			return null;
		}
		else if (!lengthCheck.isMaxStringLength(ContractName, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, ContractNameList);
		}
		return null;
	}

}