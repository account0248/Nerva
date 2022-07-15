package jp.co.vaile.nerva.searchTeam;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.AnyMasterTableDAO;
import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class SearchTeamDAO {
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * 該当テーブルからチーム情報を検索し、必要な情報をDTOリストにして戻り値とする。
	 * @param searchTeamPageDTO チーム検索画面で記述された入力情報
	 * @return チーム検索結果DTOリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<SearchTeamDTO> selectByTeamList(SearchTeamPageDTO searchTeamPageDTO, String belongCompanyId) throws ClassNotFoundException, SQLException
			 {
		//2.SQL文を作成する。
		StringBuilder selectTeamSQL = new StringBuilder();
		//2-1.チーム情報テーブル・ユーザー管理マスタ・所属会社マスタ・業務経験テーブル・従業員情報テーブル・案件詳細情報テーブル・案件情報テーブル・発注元テーブルを結合する。
		//2-2.チームID・チーム名・所属会社名・所属部長名・チームリーダー名・担当案件名のカラムを射影する。
	selectTeamSQL.append("	SELECT                                                                                    ");
	selectTeamSQL.append("	  team_info.team_id,                                                                      ");
	selectTeamSQL.append("	  team_info.team_name,                                                                    ");
	selectTeamSQL.append("	  m_belong_company.company_name,                                                          ");
	selectTeamSQL.append("	  m_user.user_name,                                                                       ");
	selectTeamSQL.append("	  team_leader.employee_name,                                                              ");
	selectTeamSQL.append("	  project_info.project_name,                                                              ");
	selectTeamSQL.append("	  order_source.order_source_name,                                                         ");
	selectTeamSQL.append("	  IFNULL(belong_member.belong_member_num, 0) AS belong_member_num                         ");
	selectTeamSQL.append("	FROM                                                                                      ");
	selectTeamSQL.append("	  team_info                                                                               ");
	selectTeamSQL.append("	  LEFT JOIN                                                                               ");
	selectTeamSQL.append("	    m_user                                                                                ");
	selectTeamSQL.append("	  ON  team_info.team_manager = m_user.user_id                                             ");
	selectTeamSQL.append("	  LEFT JOIN                                                                               ");
	selectTeamSQL.append("	    m_belong_company                                                                      ");
	selectTeamSQL.append("	  ON  m_user.company_id = m_belong_company.company_id                                     ");
	selectTeamSQL.append("	  LEFT JOIN                                                                               ");
	selectTeamSQL.append("	    (                                                                                     ");
	selectTeamSQL.append("	      SELECT                                                                              ");
	selectTeamSQL.append("	        *                                                                                 ");
	selectTeamSQL.append("	      FROM                                                                                ");
	selectTeamSQL.append("	        project_detail_info                                                               ");
	selectTeamSQL.append("	      WHERE                                                                               ");
	selectTeamSQL.append("	        (                                                                                 ");
	selectTeamSQL.append("	          project_detail_info.assign_complete_date IS NULL                                ");
	selectTeamSQL.append("	        OR  CURRENT_DATE <= project_detail_info.assign_complete_date                      ");
	selectTeamSQL.append("	        )                                                                                 ");
	selectTeamSQL.append("	      AND latest_flg = false                                                              ");
	selectTeamSQL.append("	    ) AS project_detail_info                                                              ");
	selectTeamSQL.append("	  ON  team_info.team_id = project_detail_info.team_id                                     ");
	selectTeamSQL.append("	  LEFT JOIN                                                                               ");
	selectTeamSQL.append("	    (                                                                                     ");
	selectTeamSQL.append("	      SELECT                                                                              ");
	selectTeamSQL.append("	        *                                                                                 ");
	selectTeamSQL.append("	      FROM                                                                                ");
	selectTeamSQL.append("	        project_info                                                                      ");
	selectTeamSQL.append("	      WHERE                                                                               ");
	selectTeamSQL.append("	        latest_flg = false                                                                ");
	selectTeamSQL.append("	    ) AS project_info                                                                     ");
	selectTeamSQL.append("	  ON  project_detail_info.project_info_id = project_info.project_info_id                  ");
	selectTeamSQL.append("	  LEFT JOIN                                                                               ");
	selectTeamSQL.append("	    (                                                                                     ");
	selectTeamSQL.append("	      SELECT                                                                              ");
	selectTeamSQL.append("	        employee_name,                                                                    ");
	selectTeamSQL.append("	        team_id                                                                           ");
	selectTeamSQL.append("	      FROM                                                                                ");
	selectTeamSQL.append("	        employee_experience                                                               ");
	selectTeamSQL.append("	        LEFT JOIN                                                                         ");
	selectTeamSQL.append("	          employee_info                                                                   ");
	selectTeamSQL.append("	        ON  employee_experience.employee_id = employee_info.employee_id                   ");
	selectTeamSQL.append("	      WHERE                                                                               ");
	selectTeamSQL.append("	        role_id = 'T000000001'                                                            ");
	selectTeamSQL.append("	      AND employee_experience.latest_flg = false                                          ");
	selectTeamSQL.append("	      AND employee_experience.team_belong_start_date <= CURRENT_DATE                      ");
	selectTeamSQL.append("	      AND (                                                                               ");
	selectTeamSQL.append("	          employee_experience.team_belong_complete_date IS NULL                           ");
	selectTeamSQL.append("	        OR  employee_experience.team_belong_complete_date IN(                             ");
	selectTeamSQL.append("	            SELECT                                                                        ");
	selectTeamSQL.append("	              MAX(team_belong_complete_date)                                              ");
	selectTeamSQL.append("	            FROM                                                                          ");
	selectTeamSQL.append("	              employee_experience                                                         ");
	selectTeamSQL.append("	            GROUP BY                                                                      ");
	selectTeamSQL.append("	              employee_experience.team_id                                                 ");
	selectTeamSQL.append("	          )                                                                               ");
	selectTeamSQL.append("	        )                                                                                 ");
	selectTeamSQL.append("	    ) AS team_leader                                                                      ");
	selectTeamSQL.append("	  ON  team_info.team_id = team_leader.team_id                                             ");
	selectTeamSQL.append("	  LEFT JOIN                                                                               ");
	selectTeamSQL.append("	    (                                                                                     ");
	selectTeamSQL.append("	      SELECT                                                                              ");
	selectTeamSQL.append("	        team_id,                                                                          ");
	selectTeamSQL.append("	        COUNT(*) AS belong_member_num                                                     ");
	selectTeamSQL.append("	      FROM                                                                                ");
	selectTeamSQL.append("	        (                                                                                 ");
	selectTeamSQL.append("	          SELECT                                                                          ");
	selectTeamSQL.append("	            *                                                                             ");
	selectTeamSQL.append("	          FROM                                                                            ");
	selectTeamSQL.append("	            (                                                                             ");
	selectTeamSQL.append("	              SELECT                                                                      ");
	selectTeamSQL.append("	                *                                                                         ");
	selectTeamSQL.append("	              FROM                                                                        ");
	selectTeamSQL.append("	                (                                                                         ");
	selectTeamSQL.append("	                  SELECT                                                                  ");
	selectTeamSQL.append("	                    *                                                                     ");
	selectTeamSQL.append("	                  FROM                                                                    ");
	selectTeamSQL.append("	                    employee_experience                                                   ");
	selectTeamSQL.append("	                  WHERE                                                                   ");
	selectTeamSQL.append("	                    team_belong_start_date <= CURRENT_DATE                                ");
	selectTeamSQL.append("	                  AND (                                                                   ");
	selectTeamSQL.append("	                      team_belong_complete_date IS NULL                                   ");
	selectTeamSQL.append("	                    OR  CURRENT_DATE <= team_belong_complete_date                         ");
	selectTeamSQL.append("	                    )                                                                     ");
	selectTeamSQL.append("	                ) AS emp_experience1                                                      ");
	selectTeamSQL.append("	                INNER JOIN                                                                ");
	selectTeamSQL.append("	                  (                                                                       ");
	selectTeamSQL.append("	                    SELECT                                                                ");
	selectTeamSQL.append("	                      employee_experience.employee_id AS employee_id2,                    ");
	selectTeamSQL.append("	                      MAX(team_belong_start_date) AS maxDate2                             ");
	selectTeamSQL.append("	                    FROM                                                                  ");
	selectTeamSQL.append("	                      employee_experience                                                 ");
	selectTeamSQL.append("	                    WHERE                                                                 ");
	selectTeamSQL.append("	                      team_belong_start_date <= CURRENT_DATE                              ");
	selectTeamSQL.append("	                    AND (                                                                 ");
	selectTeamSQL.append("	                        team_belong_complete_date IS NULL                                 ");
	selectTeamSQL.append("	                      OR  CURRENT_DATE <= team_belong_complete_date                       ");
	selectTeamSQL.append("	                      )                                                                   ");
	selectTeamSQL.append("	                    AND latest_flg = false                                                ");
	selectTeamSQL.append("	                    GROUP BY                                                              ");
	selectTeamSQL.append("	                      employee_experience.employee_id                                     ");
	selectTeamSQL.append("	                  ) AS emp_experience2                                                    ");
	selectTeamSQL.append("	                ON  emp_experience1.team_belong_start_date = emp_experience2.maxDate2     ");
	selectTeamSQL.append("	                AND emp_experience1.employee_id = emp_experience2.employee_id2            ");
	selectTeamSQL.append("	            ) AS emp_experience3                                                          ");
	selectTeamSQL.append("	            INNER JOIN                                                                    ");
	selectTeamSQL.append("	              (                                                                           ");
	selectTeamSQL.append("	                SELECT                                                                    ");
	selectTeamSQL.append("	                  employee_experience.employee_id AS employee_id4,                        ");
	selectTeamSQL.append("	                  MAX(regist_time) AS maxDate4                                            ");
	selectTeamSQL.append("	                FROM                                                                      ");
	selectTeamSQL.append("	                  employee_experience                                                     ");
	selectTeamSQL.append("	                WHERE                                                                     ");
	selectTeamSQL.append("	                  team_belong_start_date <= CURRENT_DATE                                  ");
	selectTeamSQL.append("	                AND (                                                                     ");
	selectTeamSQL.append("	                    team_belong_complete_date IS NULL                                     ");
	selectTeamSQL.append("	                  OR  CURRENT_DATE <= team_belong_complete_date                           ");
	selectTeamSQL.append("	                  )                                                                       ");
	selectTeamSQL.append("	                GROUP BY                                                                  ");
	selectTeamSQL.append("	                  employee_experience.employee_id                                         ");
	selectTeamSQL.append("	              ) AS emp_experience4                                                        ");
	selectTeamSQL.append("	            ON  emp_experience3.regist_time = emp_experience4.maxDate4                    ");
	selectTeamSQL.append("	            AND emp_experience3.employee_id = emp_experience4.employee_id4                ");
	selectTeamSQL.append("	        ) AS employee_experience                                                          ");
	selectTeamSQL.append("	      GROUP BY                                                                            ");
	selectTeamSQL.append("	        team_id                                                                           ");
	selectTeamSQL.append("	    ) AS belong_member                                                                    ");
	selectTeamSQL.append("	  ON  team_info.team_id = belong_member.team_id                                           ");
	selectTeamSQL.append("	  LEFT JOIN order_source                                                                  ");
	selectTeamSQL.append("	  ON project_info.client_id = order_source.order_source_id                                ");


		String operator = " WHERE ";
		List<String> paramList = new ArrayList<String>();

		//2-3.P1のチームIDに値が含まれている場合、検索条件に追加する。(完全一致検索)
		//値が含まれていない場合、検索条件に追加しない。
		if (lengthCheck.isNomNullOrNomEmpty(searchTeamPageDTO.getTeamId()) == true) {
			selectTeamSQL.append(operator + " team_info.team_id  = ?");
			paramList.add(searchTeamPageDTO.getTeamId());
			operator = " AND ";
		}

		//2-4.P1のチーム名に値が含まれている場合、検索条件に追加する。(部分一致検索)
		//値が含まれていない場合、検索条件に追加しない。
		if (lengthCheck.isNomNullOrNomEmpty(searchTeamPageDTO.getTeamName()) == true) {
			selectTeamSQL.append(operator + " team_info.team_name  LIKE ?");
			paramList.add("%" + searchTeamPageDTO.getTeamName() + "%");
			operator = " AND ";
		}

		//2-5.P1のリーダーに値が含まれている場合、検索条件に追加する。(部分一致検索)
		//値が含まれていない場合、検索条件に追加しない。
		if (lengthCheck.isNomNullOrNomEmpty(searchTeamPageDTO.getTeamLeaderName()) == true) {
			selectTeamSQL.append(operator + " team_leader.employee_name  LIKE ?");
			paramList.add("%" + searchTeamPageDTO.getTeamLeaderName() + "%");
			operator = " AND ";
		}

		//2-6.P1の担当案件IDに値が含まれている場合、検索条件に追加する。(部分一致検索)
		//値が含まれていない場合、検索条件に追加しない。
		if (lengthCheck.isNomNullOrNomEmpty(searchTeamPageDTO.getProjectId()) == true) {
			selectTeamSQL.append(operator + " project_info.project_id  = ?");
			paramList.add(searchTeamPageDTO.getProjectId());
			operator = " AND ";
		}

		//2-7.P1の担当案件名に値が含まれている場合、検索条件に追加する。(完全一致検索)
		//値が含まれていない場合、検索条件に追加しない。
		if (lengthCheck.isNomNullOrNomEmpty(searchTeamPageDTO.getProjectName()) == true) {
			selectTeamSQL.append(operator + " project_info.project_name LIKE ?");
			paramList.add("%" + searchTeamPageDTO.getProjectName() + "%");
			operator = " AND ";
		}

		//2-8.P1の発注元名に値が含まれている場合、検索条件に追加する。(部分一致検索)
		//値が含まれていない場合、検索条件に追加しない。
		if (lengthCheck.isNomNullOrNomEmpty(searchTeamPageDTO.getOrderSourceName()) == true) {
			selectTeamSQL.append(operator + " order_source.order_source_name  LIKE ?");
			paramList.add("%" + searchTeamPageDTO.getOrderSourceName() + "%");
			operator = " AND ";
		}

		//2-9.所属会社IDが株式会社Vでない場合、P1の所属会社IDを検索条件に追加する。
		//株式会社Vである場合、検索条件に追加しない。
		AnyMasterTableDAO anyMasterTableDAO = new AnyMasterTableDAO();
		List<FetchAnyMasterTableDTO> belongCompanyList;
		try {
			belongCompanyList = anyMasterTableDAO
					.fetchAnyMasterTable(BELONG_COMPANY_TABLE_NAME);
		if (!(belongCompanyId.equals(belongCompanyList.get(0).getMasterDataId()))) {
			selectTeamSQL.append(operator + " m_belong_company.company_id = ?");
			paramList.add(belongCompanyId);
			operator = " AND ";
		}
		//2-10.チーム情報テーブルの削除フラグがfalseを検索条件に追加する。
		selectTeamSQL.append(operator + " team_info.delete_flg  = false ORDER BY team_info.team_id ASC ");

		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			List<SearchTeamDTO> searchTeamDTOList = new ArrayList<SearchTeamDTO>();
			//3.作成したSQL文で検索処理を行う。
			try (PreparedStatement stmt = con.prepareStatement(selectTeamSQL.toString());) {

				//検索条件の回数
				for (int i = 0; i < paramList.size(); i++) {
					stmt.setString(i + 1, paramList.get(i));
				}
				ResultSet rs = stmt.executeQuery();
				//13.検索結果のレコードの回数4～12を繰り返す。
				while (rs.next()) {
					SearchTeamDTO searchTeamDTO = new SearchTeamDTO();
					//4.検索結果の1レコード目のチームIDをチーム検索結果DTOのチームIDに格納する。
					searchTeamDTO.setTeamId(rs.getString("team_info.team_id"));
					//5.検索結果の1レコード目のチーム名をチーム検索結果DTOのチーム名に格納する。
					searchTeamDTO.setTeamName(rs.getString("team_info.team_name"));
					//6.検索結果の1レコード目の所属会社名をチーム検索結果DTOの所属会社名に格納する。
					searchTeamDTO.setBelongCompanyName(rs.getString("m_belong_company.company_name"));
					//7.検索結果の1レコード目の所属部長名をチーム検索結果DTOの所属部長名に格納する。
					searchTeamDTO.setTeamManagerName(rs.getString("m_user.user_name"));
					//8.検索結果の1レコード目のチームリーダー名をチーム検索結果DTOのチームリーダー名に格納する
					searchTeamDTO.setTeamLeaderName(rs.getString("team_leader.employee_name"));
					//9.検索結果の1レコード目の担当案件名をチーム検索結果DTOの担当案件名に格納する。
					searchTeamDTO.setProjectName(rs.getString("project_info.project_name"));
					//所属人数
					searchTeamDTO.setTotalBelongMember(rs.getString("belong_member_num"));
					//12.作成したチーム検索結果DTOをチーム検索結果DTOリストに追加する。
					searchTeamDTOList.add(searchTeamDTO);
				}
			}
			//14.作成したチーム検索結果DTOリストを戻り値とする。
			return searchTeamDTOList;
		}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}
}
