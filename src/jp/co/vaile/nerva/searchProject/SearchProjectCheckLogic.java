package jp.co.vaile.nerva.searchProject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.IndustryInfo;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ProjectIDFormatCheck;

public class SearchProjectCheckLogic {

	ErrorMsg returnErrorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck projectIDFormatCheck = new ProjectIDFormatCheck();
	FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
	ExistCheck belongCompanyInfoCheck = new BelongCompanyInfo();
	ExistCheck industryInfoCheck = new IndustryInfo();

	/**
	 * 案件検索画面の入力情報が正しいかチェックする。正しくない場合、エラーリストに追加する。
	 * @param searchProjectPageDTO 
	 * @return errorMsgList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public List<String> checkSearchProject(SearchProjectPageDTO searchProjectPageDTO)
			throws ClassNotFoundException, SQLException {

		List<String> errorMsgList = new ArrayList<>();

		//変数に案件検索画面の入力情報を代入する。
		String projectIdErrorMsg = checkSearchProjectId(searchProjectPageDTO.getProjectId());

		String projectNameErrorMsg = checkSearchProjectName(searchProjectPageDTO.getProjectName());

		String resposibleNameErrorMsg = checkSearchResponsibleName(searchProjectPageDTO.getResponsibleName());

		String contratorNameErrorMsg = checkSearchContractorName(searchProjectPageDTO.getContratorId());

		String clientNameErrorMsg = checkSearchClientName(searchProjectPageDTO.getClientName());

		String inductryNameErrorMsg = checkSearchIndustryName(searchProjectPageDTO.getInductryId());

		String totalTeamsProjectErrorMsg = checkSearchTotalTeamsProject(searchProjectPageDTO.getTotalTeamsProject());

		String totalEmpProjectErrorMsg = checkSearchTotalEmpProject(searchProjectPageDTO.getTotalEmpProject());

		
		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectIdErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectNameErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(resposibleNameErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(contratorNameErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(clientNameErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(inductryNameErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(totalTeamsProjectErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(totalEmpProjectErrorMsg, errorMsgList);

		return errorMsgList;
	}

	/**
	 * 案件IDの入力チェック
	 * @param projectId
	 * @return
	 */
	public String checkSearchProjectId(String projectId) {

		String errorMsg = null;
		String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_ID, "8" };
		String searchProjectIdFormatCheckConstants[] = { CommonConstants.PROJECT_ID };

		if (projectId != "") {
			//案件IDの入力桁数チェック
			if (!lengthCheck.isStringLength(projectId, 8)) {
				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE,
						searchProjectLengthCheckConstants);
			}

			//形式チェック
			else if (!projectIDFormatCheck.isCorrectFormat(projectId)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.PROJECT_ID_FORMAT_ERROR_MESSAGE,
						searchProjectIdFormatCheckConstants);
			}
		}
		return errorMsg;
	}

	/**
	 * 案件名のチェック
	 * @param projectName
	 * @return
	 */
	public String checkSearchProjectName(String projectName) {

		String errorMsg = null;
		String searchProjectCheckConstants[] = { CommonConstants.PROJECT_NAME, "256" };

		//案件名の最大入力桁数チェック
		if (projectName != "") {
			if (!lengthCheck.isMaxStringLength(projectName, 256)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
						searchProjectCheckConstants);
			}
		}
		return errorMsg;
	}

	/**
	 * 責任者名のチェック
	 * @param resposibleName
	 * @return
	 */
	public String checkSearchResponsibleName(String resposibleName) {

		String errorMsg = null;
		String searchProjectCheckConstants[] = { CommonConstants.RESIPOSIBLE_NAME, "20" };

		//責任者名の最大入力桁数チェック
		if (resposibleName != "") {
			if (!lengthCheck.isMaxStringLength(resposibleName, 20)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
						searchProjectCheckConstants);
			}
		}
		return errorMsg;

	}

	/**
	 * 受注元のチェック
	 * @param contractorName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String checkSearchContractorName(String contractorName) throws ClassNotFoundException, SQLException {

		String errorMsg = null;
		String searchProjectCheckConstants[] = { CommonConstants.CONTRACTOR_NAME };
		
		//受注元の妥当性チェック
		if (contractorName != "") {
			if (!belongCompanyInfoCheck.isThisExistDB(contractorName)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.VALIDITY_SELECT_ERROR_MESSAGE,
						searchProjectCheckConstants);
			}
		}
		return errorMsg;
	}

	/**
	 * 発注元チェック
	 * @param clientName
	 * @return
	 */
	public String checkSearchClientName(String clientName) {

		String errorMsg = null;
		String searchProjectCheckConstants[] = { CommonConstants.ORDER_SOURCE_NAME ,"20"};
		
		//発注元の最大入力桁数チェック
		if (clientName != null) {
			if (!lengthCheck.isMaxStringLength(clientName, 20)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
						searchProjectCheckConstants);
			}
		}
		return errorMsg;
	}

	/**
	 * 業種のチェック
	 * @param industryName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String checkSearchIndustryName(String industryName) throws ClassNotFoundException, SQLException {

		String errorMsg = null;

		if (industryName != "") {
			String searchProjectCheckConstants[] = { CommonConstants.INDUSTRY };

			//業種の妥当性チェック
			if (!industryInfoCheck.isThisExistDB(industryName)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.VALIDITY_SELECT_ERROR_MESSAGE,
						searchProjectCheckConstants);
			}
		}
		return errorMsg;
	}

	/**
	 * 参加チーム数のチェック
	 * @param totalTeamsProject
	 * @return
	 */
	public String checkSearchTotalTeamsProject(String totalTeamsProject) {
		String errorMsg = null;

		if (totalTeamsProject != "") {
			String searchProjectCheckConstants[] = { CommonConstants.TOTAL_TEAMS_PROJECT, "3" };

			//参加チーム数の半角数字チェック
			if (!harfWidthNumFormatCheck.isCorrectFormat(totalTeamsProject)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.HARF_WIDTH_NUM_ERROR_MESSAGE,
						searchProjectCheckConstants);

				//参加チーム数の最大入力桁数チェック
			} else {

				if (!lengthCheck.isMaxStringLength(totalTeamsProject, 3)) {

					errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
							searchProjectCheckConstants);
				}
			}
		}
		return errorMsg;
	}

	/**
	 * 総参加人数のチェック
	 * @param totalEmpProject
	 * @return
	 */
	public String checkSearchTotalEmpProject(String totalEmpProject) {

		String errorMsg = null;
		if (totalEmpProject != "") {

			String searchProjectCheckConstants[] = { CommonConstants.TOTAL_EMP_PROJECT, "4" };

			//総参加人数の半角英数字チェック
			if (!harfWidthNumFormatCheck.isCorrectFormat(totalEmpProject)) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.HARF_WIDTH_NUM_ERROR_MESSAGE,
						searchProjectCheckConstants);
				
			//総参加人数の最大入力桁数チェック
			} else {

				if (!lengthCheck.isMaxStringLength(totalEmpProject, 4)) {

					errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
							searchProjectCheckConstants);
				}
			}
		}
		return errorMsg;
	}

	/**
	 * 案件検索結果チェック
	 * @return
	 */
	public List<String> checkSearchProjectAll() {
		List<String> errorMsgList = new ArrayList<>();
		String errorMsg = null;
		String searchProjectCheckConstants[] = { CommonConstants.SEARCH_PROJECT };

		errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.SEARCH_RESULT_EMPTY_ERROR_MESSAGE,
				searchProjectCheckConstants);

		errorMsgList = returnErrorMsg.errorMsgNullCheck(errorMsg, errorMsgList);

		return errorMsgList;
	}
}