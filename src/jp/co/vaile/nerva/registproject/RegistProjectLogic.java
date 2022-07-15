package jp.co.vaile.nerva.registproject;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.existchecksub.IndustryInfo;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DateFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ProjectIDFormatCheck;

public class RegistProjectLogic {

	ErrorMsg returnErrorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	FormatCheck projectIDFormatCheck = new ProjectIDFormatCheck();
	FormatCheck dateFormatCheck = new DateFormatCheck();
	ErrorMsg errorMsg = new ErrorMsg();
	ExistCheck industryInfo = new IndustryInfo();

	/**
	 *  案件登録処理を呼び出す。
	 *  エラーメッセージリストを返す。
	 *
	 * @param registProjectPageDTO
	 * @param loginUserId
	 * @throws Exception 
	 */
	public void registProject(RegistProjectPageDTO registProjectPageDTO, String loginUserId)
			throws Exception  {

		Date projectCompleteDate;

		//案件完了日が空であるか判定
		if (registProjectPageDTO.getProjectCompleteDate() == "") {
			//空の場合nullにする
			projectCompleteDate = null;
		} else {
			//空でない場合はDate型に変換
			projectCompleteDate = Date.valueOf(registProjectPageDTO.getProjectCompleteDate());
		}

		// P1の案件情報を案件情報登録DTOに移す。
		// 案件情報登録DTOの登録者IDをP2にする。
		RegistProjectInfoDTO registProjectInfoDTO = new RegistProjectInfoDTO();
		registProjectInfoDTO.setProjectId(registProjectPageDTO.getProjectId());
		registProjectInfoDTO.setProjectName(registProjectPageDTO.getProjectName());
		registProjectInfoDTO.setResponsibleId(registProjectPageDTO.getResponsibleId());
		registProjectInfoDTO.setContractorId(registProjectPageDTO.getContractorId());
		registProjectInfoDTO.setOrderSource(registProjectPageDTO.getOrderSource());
		registProjectInfoDTO.setIndustryId(registProjectPageDTO.getIndustryId());
		registProjectInfoDTO.setProjectStartDate(Date.valueOf(registProjectPageDTO.getProjectStartDate()));
		registProjectInfoDTO.setProjectCompleteDate(projectCompleteDate);
		registProjectInfoDTO.setRegistUser(loginUserId);

		RegistProjectDAO registProjectDAO = new RegistProjectDAO();
		// 作成した案件情報登録DTOを引数に案件情報登録処理を呼び出す。
		registProjectDAO.insertProjectInfo(registProjectInfoDTO);
	}

	/**
	 * 案件情報画面で入力された案件情報をチェックする。
	 * エラーがあった場合はエラーメッセージを格納したリストを返す。
	 *
	 * @param registProjectPageDTO 案件登録画面で記載された情報
	 * @return エラーメッセージリスト
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<String> inputCheckRegistProject(RegistProjectPageDTO registProjectPageDTO)
			throws ParseException, ClassNotFoundException, SQLException {

		List<String> errorMsgList = new ArrayList<>();

		// P1の案件ID、案件名、発注元、業種、案件開始日、案件完了日についてそれぞれチェックを行う。
		String projectIdErrorMsg = checkRegistProjectId(registProjectPageDTO.getProjectId());
		String projectNameErrorMsg = checkRegistProjectName(registProjectPageDTO.getProjectName());
		String orderSourceErrorMsg = checkRegistOrderSource(registProjectPageDTO.getOrderSource());
		String industryErrorMsg = checkRegistIndustryId(registProjectPageDTO.getIndustryId());
		String projectStartDateErrorMsg = checkProjectStartDate(registProjectPageDTO.getProjectStartDate());
		String projectCompleteDateErrorMsg = checkProjectCompleteDate(registProjectPageDTO.getProjectCompleteDate(),
				registProjectPageDTO.getProjectStartDate());

		// エラーメッセージがあった場合はエラーメッセージをリストに追加する。
		// エラーメッセージが無かった場合は追加しない。
		errorMsgList = errorMsg.errorMsgNullCheck(projectIdErrorMsg, errorMsgList);
		errorMsgList = errorMsg.errorMsgNullCheck(projectNameErrorMsg, errorMsgList);
		errorMsgList = errorMsg.errorMsgNullCheck(orderSourceErrorMsg, errorMsgList);
		errorMsgList = errorMsg.errorMsgNullCheck(industryErrorMsg, errorMsgList);
		errorMsgList = errorMsg.errorMsgNullCheck(projectStartDateErrorMsg, errorMsgList);
		errorMsgList = errorMsg.errorMsgNullCheck(projectCompleteDateErrorMsg, errorMsgList);

		// エラーメッセージを格納したリストを返す。
		return errorMsgList;
	}

	/**
	 * 案件IDが正しいかチェックを行い、正しくなかった場合エラーメッセージを返す。
	 *
	 * @param projectId 案件登録画面で記載された案件ID
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String checkRegistProjectId(String projectId) throws ClassNotFoundException, SQLException {
		String errorMsg = null;
		String allRegistProjectCheckConstants[] = { CommonConstants.PROJECT_ID, "8" };
		String registProjectCheckConstants[] = { CommonConstants.PROJECT_ID };
		RegistProjectDAO registProjectDAO = new RegistProjectDAO();
		int projectIdNum = registProjectDAO.selectProjectId(projectId);

		// 案件IDについて必須入力チェックを行う。
		if (!lengthCheck.isNomNullOrNomEmpty(projectId)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					registProjectCheckConstants);
		}
		// 案件IDについて半角英数字で入力されているかチェックを行う。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(projectId)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE,
					registProjectCheckConstants);
		}
		// 案件IDについて8文字で入力されているかチェックを行う。
		else if (!lengthCheck.isStringLength(projectId, 8)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE,
					allRegistProjectCheckConstants);
		}
		// 案件IDについて形式通りに入力されているかチェックを行う。
		else if (!projectIDFormatCheck.isCorrectFormat(projectId)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.PROJECT_ID_FORMAT_ERROR_MESSAGE,
					registProjectCheckConstants);
			// 案件IDについてDBに存在するデータであるかチェックを行う。
		}else if(projectIdNum != 0) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.ID_EXIST_ERROR_MESSAGE,registProjectCheckConstants);
		}
		// エラーが無かった場合はnullを呼び出し元に返す。
		return errorMsg;
	}

	/**
	 * 案件名が正しいかチェックを行い、正しくない場合はエラーメッセージを返す。
	 *
	 * @param projectName 案件登録画面で記載された案件名
	 * @return エラーメッセージ
	 */
	public String checkRegistProjectName(String projectName) {
		String errorMsg = null;
		String allRegistProjectCheckConstants[] = { CommonConstants.PROJECT_NAME, "256" };
		String registProjectCheckConstants[] = { CommonConstants.PROJECT_NAME };
		// 案件名について必須入力チェックを行う。
		if (!lengthCheck.isNomNullOrNomEmpty(projectName)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					registProjectCheckConstants);
		}
		// 案件名について256文字以下で入力されているかチェックを行う。
		else if (!lengthCheck.isMaxStringLength(projectName, 256)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
					allRegistProjectCheckConstants);
		}
		//エラーが無かった場合はnullを呼び出し元に返す
		return errorMsg;
	}

	/**
	 *
	 * 発注元が正しいかチェックを行い、正しくなかった場合はエラーメッセージを返す。
	 *
	 * @param orderSource 案件登録画面で記載された発注元
	 * @return エラーメッセージ
	 */
	public String checkRegistOrderSource(String orderSource) {
		String errorMsg = null;
		String allRegistProjectCheckConstants[] = { CommonConstants.ORDER_SOURCE_NAME, "20" };
		String registProjectCheckConstants[] = { CommonConstants.ORDER_SOURCE_NAME };
		// 発注元について必須入力チェックを行う。
		if (!lengthCheck.isNomNullOrNomEmpty(orderSource)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					registProjectCheckConstants);
		}
		// 発注元について20文字以内で入力されているかチェックを行う。
		else if (!lengthCheck.isMaxStringLength(orderSource, 20)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE,
					allRegistProjectCheckConstants);
		}
		// エラーが無かった場合はnullを呼び出し元に返す。
		return errorMsg;
	}

	/**
	 * 業種について正しいかチェックを行い、正しくなかった場合エラーメッセージを返す。
	 *
	 * @param industryId 案件登録画面で記載された業種
	 * @return エラーメッセージ
	 * @throws SQLException
	 */
	public String checkRegistIndustryId(String industryId) throws ClassNotFoundException, SQLException {
		String errorMsg = null;
		String registProjectCheckConstants[] = { CommonConstants.INDUSTRY };
		// 業種について必須入力チェックを行う。
		if (!lengthCheck.isNomNullOrNomEmpty(industryId)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_SELECT_ERROR_MESSAGE,
					registProjectCheckConstants);
		}
		// 業種についてDBに存在するかチェックを行う。
		else if (!industryInfo.isThisExistDB(industryId)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.VALIDITY_SELECT_ERROR_MESSAGE,
					registProjectCheckConstants);
		}
		// エラーが無かった場合はnullを呼び出し元に返す。
		return errorMsg;
	}

	/**
	 * 案件開始日について正しいかチェックし、正しくなかった場合はエラーメッセージを返す。
	 *
	 * @param projectStartDate 案件登録画面で記載された案件開始日
	 * @return エラーメッセージ
	 * @throws ParseException
	 */
	public String checkProjectStartDate(String projectStartDate) {
		String errorMsg = null;
		String registProjectCheckConstants[] = { CommonConstants.PROJECT_START_DATE };
		// 案件開始日について必須入力チェックを行う。
		if (!lengthCheck.isNomNullOrNomEmpty(projectStartDate)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					registProjectCheckConstants);
		}else if(!dateFormatCheck.isCorrectFormat(projectStartDate)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE, registProjectCheckConstants);
		}
		// エラーが無かった場合はnullを呼び出し元に返す。

		return errorMsg;
	}

	/**
	 * 案件完了日について正しいかチェックし、正しくなかった場合はエラーメッセージを返す。
	 *
	 * @param projectCompleteDate 案件登録画面で記載された案件完了日
	 * @param projectStartDate 案件登録画面で記載された案件開始日
	 * @return エラーメッセージ
	 * @throws ParseException
	 */
	public String checkProjectCompleteDate(String projectCompleteDate, String projectStartDate) throws ParseException {

		String errorMsg = null;
		// 案件完了日について入力チェックを行う。
		// 案件完了日または案件開始日が空文字だった場合
		if (!lengthCheck.isNomNullOrNomEmpty(projectCompleteDate) || !lengthCheck.isNomNullOrNomEmpty(projectStartDate)) {
			return errorMsg;
		}

		// エラーメッセージ用リスト
		String registProjectCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE,
				CommonConstants.PROJECT_START_DATE };
		String completeDateProjectCheckConstant[] = { CommonConstants.PROJECT_COMPLETE_DATE };

		// 案件完了日が入力されていた場合、案件完了日が形式通りに入力されているかチェックを行う。
		if(!dateFormatCheck.isCorrectFormat(projectCompleteDate)) {
			// 案件完了日が形式通りではない場合エラーメッセージを格納
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE, completeDateProjectCheckConstant);
			return errorMsg;
		// 案件開始日が形式通りに入力されているかチェックを行う。
		} else if(!dateFormatCheck.isCorrectFormat(projectStartDate)) {
			return errorMsg;
		}
		// 日付型に変換
		Date startDate = Date.valueOf(projectStartDate);
		Date completeDate = Date.valueOf(projectCompleteDate);

		// 完了日と開始日を比較
		if (completeDate.compareTo(startDate) == -1) {
			// 完了日が開始日よりも前の日付かつ同日でない場合エラーメッセージを格納
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, registProjectCheckConstants);
		}
	
	// 案件完了日が案件開始日以前で入力されていた場合のみエラーメッセージを返す。
	return errorMsg;
	}
}
