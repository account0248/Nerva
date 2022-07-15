package jp.co.vaile.nerva.updateProject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class UpdateProjectLogic {
	
	SelectLastestDataDAO selectLastestDataDAO = new SelectLastestDataDAO();
	UpdateProjectDAO updateProjectDAO = new UpdateProjectDAO();

	/**
	 * 案件情報の更新ロジック
	 * 
	 * @param projectCheck                       案件情報テーブル更新対象フラグ（trueで更新対象）
	 * @param udadateEntryTeamProjectDatailIdArr 更新対象の案件詳細情報IDのリスト
	 * @param addTeamCheck                       新規追加チームフラグ
	 * @param projectInfoDTO                     案件情報（更新内容）
	 * @param entryTeamAssignCompleteDate        参加済みチームの配属完了日リスト
	 * @param updateAddTeamDTOArr                新規追加チームDTOのリスト
	 * @param entryTeamList                      参加済みチームDTOのリスト
	 * @param registUser                         ログインユーザーID
	 * @return updateCheck 更新フラグ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	boolean updateProject(boolean projectCheck, ArrayList<String> udadateEntryTeamProjectDatailIdArr,
			boolean addTeamCheck, ProjectInfoDTO projectInfoDTO, String[] entryTeamAssignCompleteDate,
			ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr, ArrayList<ProjectEntryTeamDTO> entryTeamList,
			String registUser) throws ClassNotFoundException, SQLException, ParseException {

		boolean updateCheck = true;
		// 案件情報（更新内容）から案件情報詳細IDを取得
		int oldProjectInfoId = projectInfoDTO.getProjectInfoId();

		// 案件情報が更新対象の場合
		if (projectCheck == true) {

			// 更新対象の案件情報を全て取得する
			ProjectInfoDTO oldProjectInfoDTO = selectLastestDataDAO
					.selectLatestProjectInfoFlg(projectInfoDTO.getProjectInfoId());

			int latestProjectInfoId = 0;

			// 案件情報テーブルをアップデート（更新対象の案件情報のlatest_flg=1）
			updateProjectDAO.updateProjectInfo(projectInfoDTO, registUser);

			// 案件情報テーブルにインサート（最新案件情報を登録）
			updateProjectDAO.insertProjectInfo(projectInfoDTO, registUser, oldProjectInfoDTO);

			// 最新の案件情報IDを取得
			latestProjectInfoId = selectLastestDataDAO.searchLatestProjectInfoId(oldProjectInfoDTO.getProjectId());

			for (int i = 0; i < entryTeamList.size(); i++) {
				if (entryTeamList.size() != 0) {

					// 案件詳細情報テーブルをアップデート（更新対象の案件詳細情報のlatest_flg=1）
					updateProjectDAO.updateProjectDetailInfo("" + entryTeamList.get(i).getProjectDetailId(),
							registUser);

					// 案件詳細情報テーブルにインサート（最新案件詳細情報を登録）
					updateProjectDAO.insertProjectDetailInfo(latestProjectInfoId, entryTeamAssignCompleteDate[i],
							entryTeamList.get(i));

					// 対象のチーム(更新対象の案件に配属されている)に所属している従業員経験情報を取得してくる
					ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
					latestEmpWorkExpDTOArr = selectLastestDataDAO.searchLatestEmpEx(entryTeamList.get(i).getTeamId(),
							oldProjectInfoId);

					for (int j = 0; j < latestEmpWorkExpDTOArr.size(); j++) {
						// 先の従業員業務経験情報から従業員業務経験IDを取得
						int oldEmpWorkExpID = selectLastestDataDAO.searcholdEmpWorkExpID(latestEmpWorkExpDTOArr.get(j),
								oldProjectInfoId);
						// 従業員業務経験テーブルアップデート（更新対象の従業員業務経験情報のlatest_flg=1）
						updateProjectDAO.updateEmpEx(oldEmpWorkExpID, registUser);
						// 従業員業務経験テーブルにインサート（従業員業務経験情報を登録）
						updateProjectDAO.insertEmpEx(latestProjectInfoId, entryTeamList.get(i).getTeamId(),
								latestEmpWorkExpDTOArr.get(j), registUser);
					}
				}
			}

			// 追加チームがある場合
			if (addTeamCheck == true) {

				for (int i = 0; i < updateAddTeamDTOArr.size(); i++) {
					
					// 案件詳細情報テーブルにインサート（追加チーム情報を登録）
					updateProjectDAO.insertProjectDetailInfo(latestProjectInfoId, updateAddTeamDTOArr.get(i));

					// 従業員業務経験から追加されるチームの従業員の情報を取得
					ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
					latestEmpWorkExpDTOArr = selectLastestDataDAO
							.searchLatestEmpEx(updateAddTeamDTOArr.get(i).getTeamId());

					for (int j = 0; j < latestEmpWorkExpDTOArr.size(); j++) {
						// 従業員業務経験IDを取得
						int oldEmpWorkExpID = latestEmpWorkExpDTOArr.get(j).getEmployeeExperienceId();

						// 従業員業務経験テーブルアップデート（更新対象の従業員業務経験情報のlatest_flg=1）
						updateProjectDAO.updateEmpEx(oldEmpWorkExpID, registUser);

						// 従業員業務経験テーブルにインサート（従業員業務経験情報を登録）
						updateProjectDAO.insertEmpEx(latestProjectInfoId, updateAddTeamDTOArr.get(i).getTeamId(),
								latestEmpWorkExpDTOArr.get(j), registUser);
					}
				}

			}

		} else {
			// 案件情報が更新対象でない場合

			// 最新の案件詳細情報を取得
			ArrayList<ProjectDetailDTO> latestProjectDetailDTOArr = new ArrayList<ProjectDetailDTO>();
			latestProjectDetailDTOArr = selectLastestDataDAO
					.selectLatestEntryTeamInfo(projectInfoDTO.getProjectInfoId());

			// 最新の案件詳細情報が0でない場合
			if (latestProjectDetailDTOArr.size() != 0) {
				// 更新対象の案件詳細情報IDのリストが0でない場合
				if (udadateEntryTeamProjectDatailIdArr.size() != 0) {
					for (int i = 0; i < entryTeamList.size(); i++) {
						String projectDetailID = "";
						// 参加済チームがnullでない場合
						if (entryTeamList.get(i) != null) {

							boolean projectDetailIdFlg = false;
							for (int k = 0; k < udadateEntryTeamProjectDatailIdArr.size(); k++) {
								// 参加済みチームリストの案件詳細IDと更新対象の案件詳細情報IDのリストの値が同じ場合
								if (entryTeamList.get(i).getProjectDetailId() == Integer
										.parseInt(udadateEntryTeamProjectDatailIdArr.get(k))) {
									projectDetailIdFlg = true;
									projectDetailID = udadateEntryTeamProjectDatailIdArr.get(k);
								}
							}
							if (projectDetailIdFlg == true) {

								// 案件詳細情報テーブルをアップデート（更新対象の案件詳細情報のlatest_flg=1）
								updateProjectDAO.updateProjectDetailInfo(projectDetailID, registUser);

								// 案件詳細情報テーブルにインサート（最新案件詳細情報を登録）
								updateProjectDAO.insertProjectDetailInfo(oldProjectInfoId,
										entryTeamAssignCompleteDate[i], entryTeamList.get(i));
							}
						}
					} // for
				} // if
			}

			if (addTeamCheck == true) {

				for (int i = 0; i < updateAddTeamDTOArr.size(); i++) {

					// 案件詳細情報テーブルにインサート
					updateProjectDAO.insertProjectDetailInfo(oldProjectInfoId, updateAddTeamDTOArr.get(i));

					// 追加されるチームの従業員の情報を取得
					ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<StubEmpExpDTO>();
					latestEmpWorkExpDTOArr = selectLastestDataDAO
							.searchLatestEmpEx(updateAddTeamDTOArr.get(i).getTeamId());

					for (int j = 0; j < latestEmpWorkExpDTOArr.size(); j++) {
						// 就業員業務経験IDを取得
						int oldEmpWorkExpID = latestEmpWorkExpDTOArr.get(j).getEmployeeExperienceId();

						// 就業員業務経験テーブルアップデート（更新対象の従業員業務経験情報のlatest_flg=1）
						updateProjectDAO.updateEmpEx(oldEmpWorkExpID, registUser);

						// 従業員業務経験テーブルにインサート（従業員業務経験情報を登録）
						updateProjectDAO.insertEmpEx(oldProjectInfoId, updateAddTeamDTOArr.get(i).getTeamId(),
								latestEmpWorkExpDTOArr.get(j), registUser);

					} // for

				} // for

			} // if

		}
		return updateCheck;
	}
}
