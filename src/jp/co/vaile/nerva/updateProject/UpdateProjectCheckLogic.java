package jp.co.vaile.nerva.updateProject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class UpdateProjectCheckLogic {

	SelectLastestDataDAO selectLastestDataDAO = new SelectLastestDataDAO();
	ShowUpdatePjtLogic showUpdatePjtLogic = new ShowUpdatePjtLogic();

	/**
	 * 更新確認する
	 * 
	 * @param projectInfoDTO 案件情報（更新内容）
	 * @param entryTeamAssignCompleteDate 既に参加しているチームの配属完了日
	 * @param updateAddTeamDTOArr 新規追加したチーム情報
	 * @param loginBelongCompanyId ログインユーザの所属会社ID
	 * @param entryTeamList 既に参加しているチーム情報
	 * @param registUser ログインユーザ
	 * @return updateCheck 更新フラグ（trueで更新、falseで更新失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean updateProjectCheck(ProjectInfoDTO projectInfoDTO, String[] entryTeamAssignCompleteDate,
			ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr, String loginBelongCompanyId,
			ArrayList<ProjectEntryTeamDTO> entryTeamList, String registUser)
			throws ClassNotFoundException, SQLException, ParseException {

		// 更新対象の案件情報IDを取得する
		int projectInfoId = projectInfoDTO.getProjectInfoId();

		// 案件更新の排他性チェック
		// 既に更新されていないか確認するフラグ)：更新されていない場合true
		boolean updateProjectExclusivityCheck = checkUpdateProject(projectInfoDTO, projectInfoId, entryTeamList);

		// 更新されたかどうか
		boolean updateCheck = false;
		// 排他性チェックがOKなら以下のチェックを行う
		if (updateProjectExclusivityCheck == true) {

			// 案件情報テーブルが更新対象かどうかチェック：更新対象の場合true
			boolean projectCheck = checkLatestProjectInfo(projectInfoDTO);

			// 最新の案件情報詳細テーブルのDB情報
			ArrayList<ProjectDetailDTO> latestEntryTeamInfoDtoArr = new ArrayList<ProjectDetailDTO>();
			latestEntryTeamInfoDtoArr = selectLastestDataDAO.selectLatestEntryTeamInfo(projectInfoId);

			ArrayList<String> udadateEntryTeamProjectDatailIdArr = new ArrayList<String>();
			
			// 案件情報テーブルが更新対象でない場合
			if (projectCheck == false) {
				// 更新対象の案件詳細情報IDを取得
				udadateEntryTeamProjectDatailIdArr = checkLatestEntryTeam(entryTeamAssignCompleteDate,
						latestEntryTeamInfoDtoArr);
			}

			// 追加チームがあればtrue
			boolean addTeamCheck = checkLatestAddTeamInfo(updateAddTeamDTOArr);

			// 案件:更新なし かつ チーム:更新なし かつ 更新前の排他性エラーが発生した場合projectCheck=falseのまま返す
			if (projectCheck == false
					&& (udadateEntryTeamProjectDatailIdArr.size() == 0 || udadateEntryTeamProjectDatailIdArr == null)
					&& addTeamCheck == false) {
				
			} else {
				updateCheck = true;
				// 更新処理のロジック層に渡す。
				UpdateProjectLogic updateProjectLogic = new UpdateProjectLogic();
				updateCheck = updateProjectLogic.updateProject(projectCheck, udadateEntryTeamProjectDatailIdArr,
						addTeamCheck, projectInfoDTO, entryTeamAssignCompleteDate, updateAddTeamDTOArr, entryTeamList,
						registUser);

			}
		}

		return updateCheck;

	}

	// 案件更新の排他性チェック
	public boolean checkUpdateProject(ProjectInfoDTO projectInfoDTO, int projectInfoId,
			ArrayList<ProjectEntryTeamDTO> entryTeamList) throws ClassNotFoundException, SQLException {
		
		// return用のフラグ(falseの場合；排他性エラー)
		boolean checkUpdateProjectFlg = true;

		// 案件情報テーブルが既に更新されていないかチェック
		boolean projectCheck = checkUpdateProjectInfo(projectInfoDTO);
		// 案件情報詳細テーブルが既に更新されていないかチェック
		boolean projectDetailCheck = checkLatestProjectDetailInfo(projectInfoId, entryTeamList);

		if (projectCheck == false || projectDetailCheck == false) {
			checkUpdateProjectFlg = false;
		}

		return checkUpdateProjectFlg;
	}

	// 案件情報テーブルが既に更新されていないかチェック
	public boolean checkUpdateProjectInfo(ProjectInfoDTO projectInfoDTO) throws ClassNotFoundException, SQLException {
		
		// return用のフラグ
		boolean checkUpdateProjectInfoFlg = true;

		ProjectInfoDTO latestProjectInfoDto = new ProjectInfoDTO();

		// 最新の案件情報テーブル取得し、nullか確認（WHERE;project_info,latest_flg=0）
		latestProjectInfoDto = selectLastestDataDAO.selectLatestProjectInfo(projectInfoDTO.getProjectInfoId());
		if (latestProjectInfoDto.getProjectStartDate() == null) {

			checkUpdateProjectInfoFlg = false;
		}
		return checkUpdateProjectInfoFlg;
	}

	// 案件詳細テーブルが既に更新されていないかチェック
	public boolean checkLatestProjectDetailInfo(int projectInfoId, ArrayList<ProjectEntryTeamDTO> entryTeamList)
			throws ClassNotFoundException, SQLException {
		
		// return用のフラグ
		boolean checkLatestProjectDetailInfoFlg = true;

		// 案件詳細情報テーブルのDB情報
		for (int i = 0; i < entryTeamList.size(); i++) {
			ProjectDetailDTO latestProjectDetailDto = new ProjectDetailDTO();
			latestProjectDetailDto = selectLastestDataDAO
					.selectLatestProjectDetailInfo(entryTeamList.get(i).getProjectDetailId());

			if (latestProjectDetailDto.getProjectDetailId() == null) {
				checkLatestProjectDetailInfoFlg = false;
			}
		}

		return checkLatestProjectDetailInfoFlg;
	}

	// 案件情報テーブルについて開始日、完了日が書き換えられているかどうかチェック：更新対象の場合true
	public boolean checkLatestProjectInfo(ProjectInfoDTO projectInfoDTO) throws ClassNotFoundException, SQLException {

		// 案件情報テーブル更新対象フラグ
		boolean latestCheck = false;

		ProjectInfoDTO latestProjectInfoDto = new ProjectInfoDTO();

		// 最新の案件情報を取得する
		latestProjectInfoDto = selectLastestDataDAO.selectLatestProjectInfo(projectInfoDTO.getProjectInfoId());

		// 最新の案件情報と更新内容を比較
		// 最新の案件開始日＝案件開始日（更新内容）でない場合
		if (!latestProjectInfoDto.getProjectStartDate().equals(projectInfoDTO.getProjectStartDate())) {

			latestCheck = true;

		} else {
			// 最新の案件開始日＝案件開始日（更新内容）の場合
			
			// 最新の案件完了日がnullの場合
			if (latestProjectInfoDto.getProjectCompleteDate() == null) {

				// 案件完了日（更新内容）が空文字でない場合
				if (!projectInfoDTO.getProjectCompleteDate().equals("")) {
					latestCheck = true;
				}

			} else if (!latestProjectInfoDto.getProjectCompleteDate().equals(projectInfoDTO.getProjectCompleteDate())) {
				// 最新の案件完了日＝案件完了日（更新内容）でない場合

				latestCheck = true;
			}
		}
		return latestCheck;

	}

	/**
	 * 既に参加しているチームの配属完了日に変更があった場合、案件詳細IDを返す
	 * @param entryTeamAssignCompleteDate 既に参加しているチームの配属完了日
	 * @param latestEntryTeamInfoDtoArr   DBからとってきた最新の案件詳細情報
	 * @return udadateEntryTeamProjectDatailIdArr 案件情報詳細IDリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> checkLatestEntryTeam(String[] entryTeamAssignCompleteDate,
			ArrayList<ProjectDetailDTO> latestEntryTeamInfoDtoArr) throws ClassNotFoundException, SQLException {

		ArrayList<String> udadateEntryTeamProjectDatailIdArr = new ArrayList<String>();
		// 既に参加しているチームの配属完了日がNULLでない場合
		if (entryTeamAssignCompleteDate != null) {
			// DBから取得してきた最新の案件情報リスト分ループ
			for (int i = 0; i < latestEntryTeamInfoDtoArr.size(); i++) {
				// 最新案件情報配属完了日がNULLの場合
				// 配属完了日未入力⇒未入力で更新
				if (latestEntryTeamInfoDtoArr.get(i).getAssignCompleteDate() == null) {
					// 既に参加しているチームの配属完了日が空文字でない場合
					if (!entryTeamAssignCompleteDate[i].equals("")) {
						// 最新の案件詳細IDを戻り値リストに追加
						udadateEntryTeamProjectDatailIdArr.add(latestEntryTeamInfoDtoArr.get(i).getProjectDetailId());
					}

				} else {
					// 最新案件情報配属完了日がNULLでない場合
					// 既に参加しているチームの配属完了日が空文字の場合
					// 配属完了日あり⇒未入力で更新
					if (entryTeamAssignCompleteDate[i] == "") {
						// 最新の案件詳細IDを戻り値リストに追加
						udadateEntryTeamProjectDatailIdArr.add(latestEntryTeamInfoDtoArr.get(i).getProjectDetailId());

					} else {
						// 既に参加しているチームの配属完了日が空文字でない場合
						// 最新案件情報配属完了日が既に参加しているチームの配属完了日と異なる場合
						// 配属完了日あり⇒ありで更新
						if (!latestEntryTeamInfoDtoArr.get(i).getAssignCompleteDate()
								.equals(entryTeamAssignCompleteDate[i])) {
							// 最新の案件詳細IDを戻り値リストに追加
							udadateEntryTeamProjectDatailIdArr
									.add(latestEntryTeamInfoDtoArr.get(i).getProjectDetailId());
						}
					}
				}
			}
		}
		return udadateEntryTeamProjectDatailIdArr;
	}

	/**
	 * 追加チームがあるかないか判定
	 * @param updateAddTeamDTOArr 追加したチームリスト
	 * @return addCheck 追加チームフラグ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean checkLatestAddTeamInfo(ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr)
			throws ClassNotFoundException, SQLException {
		
		boolean addCheck = true;

		if (updateAddTeamDTOArr == null) {
			addCheck = false;
		}
		return addCheck;
	}
}
