package jp.co.vaile.nerva.searchemployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

/**
 * 検索画面で記載された値を持つDTOを用いて、DBにアクセスし検索結果を検索結果をDTOに格納する。
 *
 */
public class SearchEmpDAO {

	LengthCheck lengthCheck = new LengthCheck();

	/**
	 *検索画面で記載された値を持つDTOを用いて、DBにアクセスし検索結果を検索結果をDTOに格納する。
	 * 1-1       DBに接続する。
	 * 2-1		 従業員情報テーブル、所属会社マスタ、従業員業務経験テーブル、案件情報テーブル、
	 *           チーム情報テーブル、スキル情報テーブル,システム管理テーブルを結合する。
	 * 2-2		 P1に従業員IDが含まれている場合は、検索条件に追加する。(完全一致検索),
	 *           値が含まれていない場合検索条件に追加しない。
	 * 2-3       P1に従業員名会社が含まれている場合は、検索条件に追加する。(部分一致検索)
	 *           値が含まれていない場合検索条件に追加しない。
	 * 2-4		 P1に所属会社が含まれている場合は、検索条件に追加する。(部分一致検索)
	 *           値が含まれていない場合検索条件に追加しない。
	 * 2-5		 P1に担当案件が含まれている場合は、検索条件に追加する。(部分一致検索)
	 *           値が含まれていない場合検索条件に追加しない。
	 * 2-6 		 P1に所属チームが含まれている場合は、検索条件に追加する。(部分一致検索)
	 *           値が含まれていない場合検索条件に追加しない。
	 * 2-7		 P1に担当部長が含まれている場合は、検索条件に追加する。(部分一致検索)
	 *           値が含まれていない場合検索条件に追加しない。
	 * 2-8		 P1にスキル絞り込みが含まれている場合は、検索条件に追加する。(部分一致検索)
	 *           値が含まれていない場合検索条件に追加しない。
	 *3	作成したSQL文で検索を行う。
	 *4	検索結果DTOに値を格納する。
	 *検索結果の1レコード目を従業員IDを検索結果DTOに格納する。
	 *検索結果の1レコード目を従業員名を検索結果DTOに格納する。
	 *検索結果の1レコード目を所属会社を検索結果DTOに格納する
	 *検索結果の1レコード目を担当案件を検索結果DTOに格納する
	 *検索結果の1レコード目を所属チームを検索結果DTOに格納する
	 *検索結果の1レコード目を担当部長を検索結果DTOに格納する
	 *5	検索結果DTOに検索結果を戻り値として返す。
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public List<SearchEmpDTO> serchEmpDTOList(SearchEmpPageDTO serchEmpPageDTO) throws ClassNotFoundException, SQLException {
		List<SearchEmpDTO> serchEmpDTOList = new ArrayList<>();
		// DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT DISTINCT                                                                          ");
			sql.append("  search_employee.employee_id,                                                            ");
			sql.append("  search_employee.employee_name,                                                          ");
			sql.append("  search_employee.company_name,                                                           ");
			sql.append("  search_employee.project_name,                                                           ");
			sql.append("  search_employee.team_name,                                                              ");
			sql.append("  search_employee.user_name                                                               ");
			sql.append(" FROM                                                                                     ");
			sql.append("  (                                                                                       ");
			sql.append("    SELECT                                                                                ");
			sql.append("      employee_info.employee_id AS employee_id,                                           ");
			sql.append("      employee_info.employee_name AS employee_name,                                       ");
			sql.append("      m_belong_company.company_name AS company_name,                                      ");
			sql.append("      project_info.project_name AS project_name,                                          ");
			sql.append("      team_info.team_name AS team_name,                                                   ");
			sql.append("      m_user.user_name AS user_name                                                       ");
			sql.append("    FROM                                                                                  ");
			sql.append("      employee_info                                                                       ");
			sql.append("      LEFT JOIN                                                                           ");
			sql.append("        m_belong_company                                                                  ");
			sql.append("      ON  employee_info.company_id = m_belong_company.company_id                          ");
			sql.append("      LEFT JOIN                                                                           ");
			sql.append("        (                                                                                 ");
			sql.append("          SELECT                                                                          ");
			sql.append("            *                                                                             ");
			sql.append("          FROM                                                                            ");
			sql.append("            (                                                                             ");
			sql.append("              SELECT                                                                      ");
			sql.append("                *                                                                         ");
			sql.append("              FROM                                                                        ");
			sql.append("                (                                                                         ");
			sql.append("                  SELECT                                                                  ");
			sql.append("                    *                                                                     ");
			sql.append("                  FROM                                                                    ");
			sql.append("                    employee_experience                                                   ");
			sql.append("                  WHERE                                                                   ");
			sql.append("                    team_belong_start_date <= CURRENT_DATE                                ");
			sql.append("                  AND (                                                                   ");
			sql.append("                      team_belong_complete_date IS NULL                                   ");
			sql.append("                    OR  CURRENT_DATE <= team_belong_complete_date                         ");
			sql.append("                    )                                                                     ");
			sql.append("                ) AS emp_experience1                                                      ");
			sql.append("                INNER JOIN                                                                ");
			sql.append("                  (                                                                       ");
			sql.append("                    SELECT                                                                ");
			sql.append("                      employee_experience.employee_id AS employee_id2,                    ");
			sql.append("                      MAX(team_belong_start_date) AS maxDate2                             ");
			sql.append("                    FROM                                                                  ");
			sql.append("                      employee_experience                                                 ");
			sql.append("                    WHERE                                                                 ");
			sql.append("                      team_belong_start_date <= CURRENT_DATE                              ");
			sql.append("                    AND (                                                                 ");
			sql.append("                        team_belong_complete_date IS NULL                                 ");
			sql.append("                      OR  CURRENT_DATE <= team_belong_complete_date                       ");
			sql.append("                      )                                                                   ");
			sql.append("                    AND latest_flg = false                                                ");
			sql.append("                    GROUP BY                                                              ");
			sql.append("                      employee_experience.employee_id                                     ");
			sql.append("                  ) AS emp_experience2                                                    ");
			sql.append("                ON  emp_experience1.team_belong_start_date = emp_experience2.maxDate2     ");
			sql.append("                AND emp_experience1.employee_id = emp_experience2.employee_id2            ");
			sql.append("            ) AS emp_experience3                                                          ");
			sql.append("            INNER JOIN                                                                    ");
			sql.append("              (                                                                           ");
			sql.append("                SELECT                                                                    ");
			sql.append("                  employee_experience.employee_id AS employee_id4,                        ");
			sql.append("                  MAX(regist_time) AS maxDate4                                            ");
			sql.append("                FROM                                                                      ");
			sql.append("                  employee_experience                                                     ");
			sql.append("                WHERE                                                                     ");
			sql.append("                  team_belong_start_date <= CURRENT_DATE                                  ");
			sql.append("                AND (                                                                     ");
			sql.append("                    team_belong_complete_date IS NULL                                     ");
			sql.append("                  OR  CURRENT_DATE <= team_belong_complete_date                           ");
			sql.append("                  )                                                                       ");
			sql.append("                GROUP BY                                                                  ");
			sql.append("                  employee_experience.employee_id                                         ");
			sql.append("              ) AS emp_experience4                                                        ");
			sql.append("            ON  emp_experience3.regist_time = emp_experience4.maxDate4                    ");
			sql.append("            AND emp_experience3.employee_id = emp_experience4.employee_id4                ");
			sql.append("        ) AS employee_experience                                                          ");
			sql.append("      ON  employee_info.employee_id = employee_experience.employee_id                     ");
			sql.append("      LEFT JOIN                                                                           ");
			sql.append("        team_info                                                                         ");
			sql.append("      ON  employee_experience.team_id = team_info.team_id                                 ");
			sql.append("      LEFT JOIN                                                                           ");
			sql.append("        m_user                                                                            ");
			sql.append("      ON  team_info.team_manager = m_user.user_id                                         ");
			sql.append("      LEFT JOIN                                                                           ");
			sql.append("        project_info                                                                      ");
			sql.append("      ON  employee_experience.project_info_id = project_info.project_info_id              ");
			sql.append("    WHERE                                                                                 ");
			sql.append("      employee_info.delete_flg = false                                                    ");
			sql.append("  ) AS search_employee                                                                    ");
			sql.append("  LEFT JOIN                                                                               ");
			sql.append("    skill_info                                                                            ");
			sql.append("  ON  search_employee.employee_id = skill_info.employee_id                                ");


			String conditionWord = " WHERE ";

			List<Object> serchEmpList = new ArrayList<>();
			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getEmployeeId()) == true) {
				sql.append(conditionWord + "search_employee.employee_id = ?");
				serchEmpList.add(serchEmpPageDTO.getEmployeeId());
				conditionWord = " AND ";
			}
			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getEmployeeName()) == true) {
				sql.append(conditionWord + "search_employee.employee_name LIKE ?");
				serchEmpList.add("%" + serchEmpPageDTO.getEmployeeName() + "%");
				conditionWord = " AND ";
			}
			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getCompanyName()) == true) {
				sql.append(conditionWord + "search_employee.company_name = ?");
				serchEmpList.add(serchEmpPageDTO.getCompanyName());
				conditionWord = " AND ";
			}

			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getProjectName()) == true) {
				sql.append(conditionWord + "search_employee.project_name LIKE ?");
				serchEmpList.add("%" + serchEmpPageDTO.getProjectName() + "%");
				conditionWord = " AND ";
			}
			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getTeamName()) == true) {
				sql.append(conditionWord + "search_employee.team_name LIKE ?");
				serchEmpList.add("%" + serchEmpPageDTO.getTeamName() + "%");
				conditionWord = " AND ";
			}

			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getTeamManager()) == true) {
				sql.append(conditionWord + "search_employee.user_name LIKE ?");
				serchEmpList.add("%" + serchEmpPageDTO.getTeamManager() + "%");
				conditionWord = " AND ";
			}
			if (lengthCheck.isNomNullOrNomEmpty(serchEmpPageDTO.getSkillFiltering()) == true) {
				sql.append(conditionWord + " skill_info.skill_detail LIKE ?");
				serchEmpList.add("%" + serchEmpPageDTO.getSkillFiltering() + "%");
				conditionWord = " AND ";
			}

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				for (int i = 0; i < serchEmpList.size(); i++) {

					stmt.setString(1 + i, (String) serchEmpList.get(i));
				}
				ResultSet rs = stmt.executeQuery();
				

				while (rs.next()) {
					SearchEmpDTO serchEmpDTO = new SearchEmpDTO();
					serchEmpDTO.setEmployeeId(rs.getString("employee_id"));
					serchEmpDTO.setEmployeeName(rs.getString("employee_name"));
					serchEmpDTO.setCompanyName(rs.getString("company_name"));
					serchEmpDTO.setProjectName(rs.getString("project_name"));
					serchEmpDTO.setTeamName(rs.getString("team_name"));
					serchEmpDTO.setTeamManager(rs.getString("user_name"));
					serchEmpDTOList.add(serchEmpDTO);
				
				}
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
		return serchEmpDTOList;
	}
}
