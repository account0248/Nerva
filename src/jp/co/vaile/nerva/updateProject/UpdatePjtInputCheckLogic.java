package jp.co.vaile.nerva.updateProject;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DateFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class UpdatePjtInputCheckLogic {

	ErrorMsg returnErrorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	DateFormatCheck dateFormatCheck = new DateFormatCheck();
	SelectLastestDataDAO selectLastestDataDAO = new SelectLastestDataDAO();

	// 案件開始日が入っているかチェック
	/**
	 * @param projectStartDate            案件開始日
	 * @param projectCompleteDate         案件完了日
	 * @param entryTeamAssignStartDate    配属開始日
	 * @param entryTeamAssignCompleteDate 配属完了日
	 * @param addTeamId                   追加チームID
	 * @param addTeamAssignStartDate      追加チーム配属開始日
	 * @param addTeamAssignCompleteDate   追加チーム配属完了日
	 * @param projectId                   案件ID
	 * @param loginBelongCompanyId        ログインユーザの所属会社ID
	 * @return errorMsgList エラーメッセージリスト
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<String> checkUpdateProject(String projectStartDate, String projectCompleteDate,
			String[] entryTeamAssignStartDate, String[] entryTeamAssignCompleteDate, String[] addTeamId,
			String[] addTeamAssignStartDate, String[] addTeamAssignCompleteDate, int projectId,
			String loginBelongCompanyId) throws ParseException, ClassNotFoundException, SQLException {

		List<String> errorMsgList = new ArrayList<>();

		// 案件開始日の入力チェック
		String projectStartDateErrorMsg = checkProjectStartDate(projectStartDate);
		// 案件完了日の入力チェック
		String projectCompleteDateErrorMsg = checkProjectCompleteDate(projectCompleteDate);

		String projectStartProjectCompleteDateErrorMsg = null;

		String teamStartDateErrorMsg = null;
		String teamCompleteDateErrorMsg = null;

		String projectStartTeamStartDateErrorMsg = null;
		String teamStartTeamCompleteDateErrorMsg = null;
		String teamStartProjectCompleteDateErrorMsg = null;
		String teamCompleteProjectCompleteDateErrorMsg = null;
		String addableTeamErrorMsg = null;
		String exsistTeamErrorMsg = null;
		String repeatedAddTeamErrorMsg = null;
		String addTeamAssignStartDateErrorMsg = null;
		String addTeamAssignCompleteDateErrorMsg = null;
		String projectStartAddTeamStartDateErrorMsg = null;
		String addTeamStartAddTeamCompleteDateErrorMsg = null;
		String addTeamStartProjectCompleteDateErrorMsg = null;
		String addTeamCompleteProjectCompleteDateErrorMsg = null;
		String addTeamAssignStartEmpStartDateErrorMsg = null;
		String addTeamAssignCompleteEmpCompleteDateErrorMsg = null;
		String addTeamIdRequiredErrorMsg = null;

		// 案件開始日、案件完了日の入力チェック片方でもエラーの場合以降のチェックを行わない
		if (projectStartDateErrorMsg == null && projectCompleteDateErrorMsg == null) {

			// 案件開始日と案件完了日の日付チェック
			projectStartProjectCompleteDateErrorMsg = checkProjectStartProjectCompleteDate(projectStartDate,
					projectCompleteDate);

			// 参加チームが存在する場合は以降の日付チェックを行う
			if (entryTeamAssignStartDate != null) {

				for (int i = 0; i < entryTeamAssignCompleteDate.length; i++) {
					// 既に参加しているチームIDを取得
					String entryTeamId = selectEntryTeamId(projectId, i);
					// 配属完了日の入力チェック
					teamCompleteDateErrorMsg = checkEntryTeamCompleteDate(entryTeamAssignCompleteDate[i], entryTeamId,
							projectId);
					// 配属完了日の入力チェックにエラーがあれば以降のチェックを行わない
					if (teamCompleteDateErrorMsg != null) {
						break;

					} else {
						// 配属完了日の入力チェックにエラーがなければ以降のチェックを行う
						// 配属完了日が空でない場合
						if (!entryTeamAssignCompleteDate[i].equals("")) {

							// 配属済みのチームの配属開始日と配属完了日の日付チェック
							teamStartTeamCompleteDateErrorMsg = checkTeamStartTeamCompleteDate(
									entryTeamAssignStartDate[i], entryTeamAssignCompleteDate[i]);
							if (teamStartTeamCompleteDateErrorMsg != null) {

								break;
							}

						} else if (entryTeamAssignCompleteDate[i].equals("")) {
							// 配属完了日が空の場合
							// 配属済みのチームの配属開始日と案件完了日の日付チェック
							teamStartProjectCompleteDateErrorMsg = checkTeamStartProjectCompleteDate(
									entryTeamAssignStartDate[i], projectCompleteDate);
							if (teamStartProjectCompleteDateErrorMsg != null) {

								break;
							}
						}
						// 配属済みのチームの配属完了日と案件完了日の日付チェック
						teamCompleteProjectCompleteDateErrorMsg = checkTeamCompleteProjectCompleteDate(
								entryTeamAssignCompleteDate[i], projectCompleteDate);
						if (teamCompleteProjectCompleteDateErrorMsg != null) {

							break;
						}
					}

				}
			}

			// チームが追加された場合
			boolean addTeamFlg = false;
			if (addTeamId != null) {

				// 同じチームが選択されていないかのチェック
				repeatedAddTeamErrorMsg = checkRepeatedAddTeam(addTeamId);

				if (repeatedAddTeamErrorMsg == null) {
					for (int i = 0; i < addTeamId.length; i++) {

						addTeamFlg = true;

						// チーム名の必須チェック
						if (addTeamId[i].equals("")) {
							addTeamFlg = false;
							addTeamIdRequiredErrorMsg = checkRequiredAddTeam();
						}

						// チーム名が入力されている場合
						if (addTeamIdRequiredErrorMsg == null) {

							// 選択されたチームが存在しているチームかチェック
							ArrayList<String> exsistTeamArr = new ArrayList<String>();
							// 最新のチーム名を取得する
							exsistTeamArr = selectLastestDataDAO.selectexisitTeam(addTeamId[i]);
							if (exsistTeamArr.isEmpty()) {
								exsistTeamErrorMsg = checkexsistTeam();
							}

							// チームがDBに存在する場合
							if (exsistTeamErrorMsg == null) {

								// 選択されたチームが追加可能チームかチェック
								ArrayList<String> addableTeamIdArr = new ArrayList<String>();
								SelectLastestDataDAO selectLastestDataDAO = new SelectLastestDataDAO();
								// 案件に参加している最新のチームIDを取得する
								addableTeamIdArr = selectLastestDataDAO.selectAddableTeamId(projectId);
								for (int j = 0; j < addableTeamIdArr.size(); j++) {

									if (addTeamId[i].equals(addableTeamIdArr.get(j))) {
										addableTeamErrorMsg = checkAddableTeam();
									}

								}

							}
						}

						// 追加された配属開始日の入力チェック
						if (addTeamAssignStartDateErrorMsg == null) {
							addTeamAssignStartDateErrorMsg = checkAddTeamAssignStartDate(addTeamAssignStartDate[i]);
							if (addTeamAssignStartDateErrorMsg != null) {
								addTeamFlg = false;
								break;
							}
						}

						// 追加された配属完了日の入力チェック
						if (addTeamAssignCompleteDate[i] != "") {
							addTeamAssignCompleteDateErrorMsg = checkAddTeamAssignCompleteDate(
									addTeamAssignCompleteDate[i]);
							if (addTeamAssignCompleteDateErrorMsg != null) {
								addTeamFlg = false;
								break;
							} else {
								// 追加されるチームの従業員の所属完了日とチームの配属完了日の日付チェック
								addTeamAssignCompleteEmpCompleteDateErrorMsg = checkTeamCompleteDate(
										addTeamAssignCompleteDate[i], addTeamId[i]);
								if (addTeamAssignCompleteEmpCompleteDateErrorMsg != null) {
									addTeamFlg = false;
									break;
								}
							}
						}
					}
				}

				if (addTeamFlg) {

					// 選択されたチームが参加可能であるため、日付のチェックをする
					for (int i = 0; i < addTeamId.length; i++) {

						if (projectStartDate.equals("") || addTeamAssignStartDate[i].equals("")) {
						} else {

							if (!addTeamAssignCompleteDate[i].equals("")) {

								// 追加された配属開始日と追加された配属完了日の日付チェック
								if (addTeamStartAddTeamCompleteDateErrorMsg == null) {
									addTeamStartAddTeamCompleteDateErrorMsg = checkAddTeamStartAddTeamCompleteDate(
											addTeamAssignStartDate[i], addTeamAssignCompleteDate[i]);
								}
							}
						}
						if (!projectCompleteDate.equals("") && !addTeamAssignCompleteDate[i].equals("")) {
							// 追加された配属完了日と案件完了日の日付チェック
							if (addTeamCompleteProjectCompleteDateErrorMsg == null) {
								addTeamCompleteProjectCompleteDateErrorMsg = checkAddTeamCompleteProjectCompleteDate(
										addTeamAssignCompleteDate[i], projectCompleteDate);
							}
						} else if (addTeamAssignCompleteDate[i].equals("")) {
							// 追加された配属開始日と案件完了日の日付チェック

							if (addTeamStartProjectCompleteDateErrorMsg == null) {

								addTeamStartProjectCompleteDateErrorMsg = checkAddTeamStartProjectCompleteDate(
										addTeamAssignStartDate[i], projectCompleteDate);
							}

						}

					}
				}

			}
		}

		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectStartDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(teamStartDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(teamCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectStartTeamStartDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(teamStartTeamCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(teamCompleteProjectCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(teamStartProjectCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addableTeamErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(exsistTeamErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamAssignStartDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamAssignCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectStartAddTeamStartDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamStartAddTeamCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamCompleteProjectCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamStartProjectCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(projectStartProjectCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(repeatedAddTeamErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamAssignStartEmpStartDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamAssignCompleteEmpCompleteDateErrorMsg, errorMsgList);
		errorMsgList = returnErrorMsg.errorMsgNullCheck(addTeamIdRequiredErrorMsg, errorMsgList);

		return errorMsgList;
	}

	// 案件開始日の入力チェック
	public String checkProjectStartDate(String projectStartDate) {

		String errorMsg = null;
		String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_START_DATE };
		if (projectStartDate != "") {
			// 入力された日付のフォーマットチェック
			if (!dateFormatCheck.isCorrectFormat(projectStartDate)) {
				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE,
						searchProjectLengthCheckConstants);
			}
		} else // {
		if (projectStartDate == "") {

			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					searchProjectLengthCheckConstants);
		}
		return errorMsg;
	}

	// 案件完了日の入力チェック
	public String checkProjectCompleteDate(String projectCompleteDate) {
		String errorMsg = null;
		String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE };
		if (projectCompleteDate != "") {
			// 入力された日付のフォーマットチェック
			if (!dateFormatCheck.isCorrectFormat(projectCompleteDate)) {
				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE,
						searchProjectLengthCheckConstants);
			}
		}
		return errorMsg;
	}

	// 案件開始と案件完了日の日付チェック
	public String checkProjectStartProjectCompleteDate(String projectStartDate, String projectCompleteDate)
			throws ParseException {

		String errorMsg = null;

		if (projectStartDate != "" && projectCompleteDate != "") {
			String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE,
					CommonConstants.PROJECT_START_DATE };
			// try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date projectStartDateToDate = dateFormat.parse(projectStartDate);
			Date projectCompleteDateToDate = dateFormat.parse(projectCompleteDate);

			if (projectCompleteDateToDate.compareTo(projectStartDateToDate) == -1) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

			}

		}
		return errorMsg;
	}

	// 既に参加しているチームIDを取得
	public String selectEntryTeamId(int projectId, int i) throws ClassNotFoundException, SQLException {

		// 案件更新画面表示DAOを使用し、画面と同じ順番の参加チーム情報を取得
		ShowUpdatePjtLogic showUpdatePjtLogic = new ShowUpdatePjtLogic();
		ArrayList<ProjectEntryTeamDTO> updatePjtEntryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		updatePjtEntryTeamList = showUpdatePjtLogic.acquireUpdatePjtTeam(projectId);
		String teamId = updatePjtEntryTeamList.get(i).getTeamId();

		return teamId;

	}

	// 既に配属されたチームの配属完了日の入力チェック
	public String checkEntryTeamCompleteDate(String teamCompleteDate, String entryTeamId, int projectId)
			throws ParseException, ClassNotFoundException, SQLException {

		String errorMsg = null;
		String teamStartComplete = "";

		teamStartComplete = CommonConstants.ENTRIED_TEAM_ASSIGN_COMPLETE_DATE;

		String searchProjectLengthCheckConstants[] = { teamStartComplete };
		String updateProjectTeamEmpCompleteDateCheckConstants[] = { teamStartComplete,
				CommonConstants.EMP_TEAM_BELONG_COMPLETE_DATE };

		if (teamCompleteDate != "") {
			// 入力された日付のフォーマットチェック
			if (!dateFormatCheck.isCorrectFormat(teamCompleteDate)) {
				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE,
						searchProjectLengthCheckConstants);
			} else {
				// 参加チームの従業員の情報を取得
				ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
				latestEmpWorkExpDTOArr = selectEmployeeEntryTeam(entryTeamId, projectId);
				// 対象のチームの従業員の所属完了日取得し、チームの配属完了日と日付チェックを行う
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date teamCompleteDateToDate = dateFormat.parse(teamCompleteDate);

				for (int i = 0; i < latestEmpWorkExpDTOArr.size(); i++) {
					Date employeeCompleteDate = latestEmpWorkExpDTOArr.get(i).getTeamBelongCompleteDate();
					if (employeeCompleteDate != null) {
						if (teamCompleteDateToDate.compareTo(employeeCompleteDate) == -1) {

							errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE,
									updateProjectTeamEmpCompleteDateCheckConstants);
						}
					}
					if (errorMsg != null) {
						break;
					}
				}
			}
		}
		return errorMsg;
	}

	// 配属済みのチームの配属開始日と配属完了日の日付チェック
	public String checkTeamStartTeamCompleteDate(String teamStartDate, String teamCompleteDate) throws ParseException {

		String errorMsg = null;

		if (!teamStartDate.equals("") && !teamCompleteDate.equals("")) {
			String searchProjectLengthCheckConstants[] = { CommonConstants.ENTRY_TEAM_ASSIGN_COMPLETE_DATE,
					CommonConstants.ENTRY_TEAM_ASSIGN_START_DATE };
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date teamStartDateToDate = dateFormat.parse(teamStartDate);
			Date teamCompleteDateToDate = dateFormat.parse(teamCompleteDate);

			if (teamCompleteDateToDate.compareTo(teamStartDateToDate) == -1) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

			}
		}
		return errorMsg;
	}

	// 配属済みのチームの配属開始日と案件完了日の日付チェック
	public String checkTeamStartProjectCompleteDate(String entryTeamAssignStartDate, String projectCompleteDate)
			throws ParseException {

		String errorMsg = null;

		if (!projectCompleteDate.equals("")) {

			String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE,
					CommonConstants.ENTRY_TEAM_ASSIGN_START_DATE };
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date entryTeamAssignStartDateToDate = dateFormat.parse(entryTeamAssignStartDate);
			Date projectCompleteDateToDate = dateFormat.parse(projectCompleteDate);

			if (projectCompleteDateToDate.compareTo(entryTeamAssignStartDateToDate) == -1) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

			}
		}
		return errorMsg;
	}

	// 配属済みのチームの配属完了日と案件完了日の日付チェック
	public String checkTeamCompleteProjectCompleteDate(String teamCompleteDate, String projectCompleteDate)
			throws ParseException {

		String errorMsg = null;
		if (!teamCompleteDate.equals("") && !projectCompleteDate.equals("")) {
			String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE,
					CommonConstants.ENTRY_TEAM_ASSIGN_COMPLETE_DATE };
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date teamCompleteDateToDate = dateFormat.parse(teamCompleteDate);
			Date projectCompleteDateToDate = dateFormat.parse(projectCompleteDate);

			if (projectCompleteDateToDate.compareTo(teamCompleteDateToDate) == -1) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

			}
		}
		return errorMsg;
	}

	// 追加されるチームの従業員の所属完了日とチームの配属完了日の日付チェック
	public String checkTeamCompleteDate(String teamCompleteDate, String entryTeamId)
			throws ParseException, ClassNotFoundException, SQLException {

		String errorMsg = null;
		String teamStartComplete = "";

		teamStartComplete = CommonConstants.ADD_ENTRY_TEAM_ASSIGN_COMPLETE_DATE;

		String updateProjectTeamEmpCompleteDateCheckConstants[] = { teamStartComplete,
				CommonConstants.EMP_TEAM_BELONG_COMPLETE_DATE };

		if (teamCompleteDate != "") {

			// 参加チームの従業員の情報を取得
			ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
			latestEmpWorkExpDTOArr = selectEmployeeAddTeam(entryTeamId);
			// 対象のチームの従業員の所属完了日取得し、チームの配属完了日と日付チェックを行う
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date teamCompleteDateToDate = dateFormat.parse(teamCompleteDate);

			for (int i = 0; i < latestEmpWorkExpDTOArr.size(); i++) {
				Date employeeCompleteDate = latestEmpWorkExpDTOArr.get(i).getTeamBelongCompleteDate();
				if (employeeCompleteDate != null) {
					if (teamCompleteDateToDate.compareTo(employeeCompleteDate) == -1) {

						errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE,
								updateProjectTeamEmpCompleteDateCheckConstants);
					}
				}
				if (errorMsg != null) {
					break;
				}
			}

		}
		return errorMsg;

	}

	// 追加されたチームの従業員情報を取得
	public ArrayList<StubEmpExpDTO> selectEmployeeAddTeam(String team_id)
			throws ClassNotFoundException, SQLException, ParseException {

		// チームの従業員の情報を取得
		ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
		latestEmpWorkExpDTOArr = selectLastestDataDAO.searchLatestEmpEx(team_id);

		return latestEmpWorkExpDTOArr;
	}

	// 配属済みのチームの従業員情報を取得
	public ArrayList<StubEmpExpDTO> selectEmployeeEntryTeam(String team_id, int projectId)
			throws ClassNotFoundException, SQLException, ParseException {

		// チームの従業員の情報を取得
		ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
		latestEmpWorkExpDTOArr = selectLastestDataDAO.searchLatestEmpExEntryTeam(team_id, projectId);

		return latestEmpWorkExpDTOArr;
	}

	// チーム名の必須チェック
	public String checkRequiredAddTeam() {

		String errorMsg = null;

		String searchProjectLengthCheckConstants[] = { CommonConstants.TEAM_NAME };

		errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_SELECT_ERROR_MESSAGE,
				searchProjectLengthCheckConstants);

		return errorMsg;
	}

	// 追加選択されたチームが重複していないかチェック
	public String checkAddableTeam() {

		String errorMsg = null;

		String searchProjectLengthCheckConstants[] = { CommonConstants.TEAM_NAME };

		errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.VALIDITY_SELECT_ERROR_MESSAGE,
				searchProjectLengthCheckConstants);
		return errorMsg;
	}

	// 追加されたチームが存在しているチームかチェック
	public String checkexsistTeam() {

		String errorMsg = null;

		String searchProjectLengthCheckConstants[] = { CommonConstants.TEAM_NAME };

		errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.VALIDITY_SELECT_ERROR_MESSAGE,
				searchProjectLengthCheckConstants);
		return errorMsg;
	}

	// 追加されたチームの配属開始日の入力チェック
	public String checkAddTeamAssignStartDate(String addTeamAssignStartDate) {

		String errorMsg = null;
		String searchProjectLengthCheckConstants[] = { CommonConstants.ADD_ENTRY_TEAM_ASSIGN_START_DATE };
		// 必須チェック
		if (addTeamAssignStartDate == "") {

			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					searchProjectLengthCheckConstants);
			// フォーマットチェック
		} else if (!dateFormatCheck.isCorrectFormat(addTeamAssignStartDate)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE,
					searchProjectLengthCheckConstants);

		}
		return errorMsg;
	}

	// 追加された配属完了日の入力チェック
	public String checkAddTeamAssignCompleteDate(String addTeamAssignCompleteDate) {

		String errorMsg = null;
		String searchProjectLengthCheckConstants[] = { CommonConstants.ADD_ENTRY_TEAM_ASSIGN_COMPLETE_DATE };

		// フォーマットチェック
		if (!dateFormatCheck.isCorrectFormat(addTeamAssignCompleteDate)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.FORMAT_INPUT_ERROR_MESSAGE,
					searchProjectLengthCheckConstants);

		}
		return errorMsg;
	}

	// 追加された配属開始日と追加された配属完了日の日付チェック
	public String checkAddTeamStartAddTeamCompleteDate(String addTeamAssignStartDate, String addTeamAssignCompleteDate)
			throws ParseException {

		String errorMsg = null;

		String searchProjectLengthCheckConstants[] = { CommonConstants.ADD_ENTRY_TEAM_ASSIGN_COMPLETE_DATE,
				CommonConstants.ADD_ENTRY_TEAM_ASSIGN_START_DATE };
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date addTeamStartDateToDate = dateFormat.parse(addTeamAssignStartDate);
		Date addTeamCompleteDateToDate = dateFormat.parse(addTeamAssignCompleteDate);

		if (addTeamCompleteDateToDate.compareTo(addTeamStartDateToDate) == -1) {

			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

		}

		return errorMsg;
	}

	// 追加された配属完了日と案件完了日の日付チェック
	public String checkAddTeamCompleteProjectCompleteDate(String addTeamAssignCompleteDate, String projectCompleteDate)
			throws ParseException {

		String errorMsg = null;

		String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE,
				CommonConstants.ADD_ENTRY_TEAM_ASSIGN_COMPLETE_DATE };
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date addTeamCompleteDateToDate = dateFormat.parse(addTeamAssignCompleteDate);
		Date projectCompleteDateToDate = dateFormat.parse(projectCompleteDate);

		if (projectCompleteDateToDate.compareTo(addTeamCompleteDateToDate) == -1) {

			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

		}

		return errorMsg;
	}

	// 追加された配属開始日と案件完了日の日付チェック
	public String checkAddTeamStartProjectCompleteDate(String addTeamAssignStartDate, String projectCompleteDate)
			throws ParseException {

		String errorMsg = null;
		if (!projectCompleteDate.equals("") && !addTeamAssignStartDate.equals("")) {

			String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT_COMPLETE_DATE,
					CommonConstants.ADD_ENTRY_TEAM_ASSIGN_START_DATE };
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date addTeamAssignStartDateToDate = dateFormat.parse(addTeamAssignStartDate);
			Date projectCompleteDateToDate = dateFormat.parse(projectCompleteDate);

			if (projectCompleteDateToDate.compareTo(addTeamAssignStartDateToDate) == -1) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.AFTER_DATE, searchProjectLengthCheckConstants);

			}
		}

		return errorMsg;

	}

	// 同じチームが選択されていないかのチェック
	public String checkRepeatedAddTeam(String[] addTeamId) {

		String errorMsg = null;

		for (int i = 0; i < addTeamId.length; i++) {
			if (addTeamId[i] != "") {
				for (int j = 0; j < addTeamId.length; j++) {
					if ((i != j) && (addTeamId[i].equals(addTeamId[j]))) {
						errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REPEATED_TEAM_EROOR_MESSAGE);
						break;
					}
				}
			}
			if (errorMsg != null) {
				break;
			}
		}
		return errorMsg;

	}

	/**
	 * 更新失敗の場合のエラーメッセージを取得する
	 * 
	 * @return errorMsgList エラーメッセージのリスト
	 */
	public ArrayList<String> updateProjectError() {

		ArrayList<String> errorMsgList = new ArrayList<String>();
		String searchProjectLengthCheckConstants[] = { CommonConstants.UPDATE };
		String errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.PROCESS_FAILURE_ERROR_MESSAGE,
				searchProjectLengthCheckConstants);
		errorMsgList.add(errorMsg);

		return errorMsgList;

	}

	/**
	 * 更新するものがない場合のエラーメッセージを取得する
	 * 
	 * @return errorMsgList エラーメッセージのリスト
	 */
	public ArrayList<String> updateProjectNonError() {

		ArrayList<String> errorMsgList = new ArrayList<String>();
		String errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.UPDATE_PROJECT_NON_MESSAGE);
		errorMsgList.add(errorMsg);

		return errorMsgList;

	}

}
