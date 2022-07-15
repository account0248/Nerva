package jp.co.vaile.nerva.transferApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SearchTransferApplicationDAO {

	//sql文
	static String sql;

	static ResultSet rs;

	public static Statement s;

	public static PreparedStatement ps;

	static final String SELECT = " SELECT ";

	static final String FROM = " FROM ";

	static final String WH = " WHERE ";

	static final String AS = " AS ";

	static final String AND = " AND ";

	static final String APPLICATION_NUM = " application_num ";

	//join用テーブル
	static final String APPLICATION_TRANSFER = " transfer_application ";

	static final String EMPLOYEE_INFO = " employee_info ";

	static final String PROJECT_DETAIL_INFO = " project_detail_info ";

	static final String PROJECT_INFO = " project_info ";

	static final String PROJECT_TABLE = " project_table ";

	//取得カラム
	static final String TRANSFER_EMP_ID = " transfer_emp_id ";

	static final String ROLE_ID = " role_id ";

	static final String CONTRACT_TYPE_ID = " contract_type_id ";

	static final String MONTH_UNIT_PRICE = " month_unit_price ";

	static final String TRANSFER_APPLICATION_TEAM = " transfer_application_team ";

	static final String TRANSFER_PREFERRED_DATE = " transfer_preferred_date";

	static final String TEAM_FINISH_DATE = " team_finish_date ";

	static final String UNAPPROVED_FLG = " unapproved_flg ";

	static final String DELETE_FLG = " delete_flg ";

	static final String PROJECT_ID = " project_id ";

	//join用カラム

	static final String NOW_AFFILICATION_TEAM = " now_affiliation_team ";

	static final String TEAM_ID = " team_id";

	static final String PROJECT_INFO_ID = " project_info_id ";

	public static SearchTransferApplicationDTO searchTransferApplication(int applicationNum) throws SQLException, ClassNotFoundException
	{

		SearchTransferApplicationDTO searchTransferApplicationDTO = new SearchTransferApplicationDTO();

		Connection con;
		try {
			con = DBConnection.getConnection();


			sql = SELECT + APPLICATION_TRANSFER + "." + TRANSFER_EMP_ID + "," + APPLICATION_TRANSFER + "."
					+ ROLE_ID + "," + APPLICATION_TRANSFER + "." + CONTRACT_TYPE_ID
					+ "," + APPLICATION_TRANSFER + "." + MONTH_UNIT_PRICE
					+ "," + APPLICATION_TRANSFER + "." + TRANSFER_APPLICATION_TEAM
					+ "," + APPLICATION_TRANSFER + "." + TRANSFER_PREFERRED_DATE
					+ "," + APPLICATION_TRANSFER + "." + TEAM_FINISH_DATE
					+ "," + APPLICATION_TRANSFER + "." + UNAPPROVED_FLG
					+ "," + EMPLOYEE_INFO + "." + DELETE_FLG
					+ ","
					+PROJECT_TABLE + "." + "project_id "

					+"FROM "+APPLICATION_TRANSFER
					+ "LEFT OUTER JOIN"
					+ "(" + SELECT + PROJECT_DETAIL_INFO + "." + TEAM_ID + ","
					+ PROJECT_INFO + "." + PROJECT_ID

					+FROM+PROJECT_DETAIL_INFO+ " LEFT OUTER JOIN "+ PROJECT_INFO + " ON "

					+ PROJECT_INFO + "." + PROJECT_INFO_ID + "="
					+ PROJECT_DETAIL_INFO + "." + PROJECT_INFO_ID + ")"

					+ AS
					+ PROJECT_TABLE
					+ " ON " + APPLICATION_TRANSFER + "." + NOW_AFFILICATION_TEAM + "="
					+ PROJECT_TABLE + "." + TEAM_ID


					+ " LEFT OUTER JOIN "+EMPLOYEE_INFO+ " ON "
					+ APPLICATION_TRANSFER + "." + TRANSFER_EMP_ID + "="+EMPLOYEE_INFO+"."+"employee_id"


					+ WH + APPLICATION_NUM + " = ? " + AND + UNAPPROVED_FLG + " = ? ;";

			try (PreparedStatement ps = con.prepareStatement(sql);) {

				ps.setInt(1, applicationNum);
				ps.setInt(2, 0);

				rs = ps.executeQuery();

				while (rs.next()) {
					searchTransferApplicationDTO = new SearchTransferApplicationDTO();
					searchTransferApplicationDTO = setSearchTransferApplication(rs);

				}
				return searchTransferApplicationDTO;

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

	public static SearchTransferApplicationDTO setSearchTransferApplication(ResultSet rs) throws SQLException  {

		SearchTransferApplicationDTO searchTransferApplicationDTO = new SearchTransferApplicationDTO();

		try {
			searchTransferApplicationDTO.setTransferEmpId(rs.getString("transfer_emp_id"));


		searchTransferApplicationDTO.setRoleId(rs.getString("role_id"));

		searchTransferApplicationDTO.setContractTypeId(rs.getString("contract_type_id"));

		searchTransferApplicationDTO.setMonthUnitPrice(rs.getInt("month_unit_price"));

		searchTransferApplicationDTO.setTransferApplicationTeam(rs.getString("transfer_application_team"));

		searchTransferApplicationDTO.setTransferPreferredDate(rs.getDate("transfer_preferred_date"));

		searchTransferApplicationDTO.setTeamFinishDate(rs.getDate("team_finish_date"));

		searchTransferApplicationDTO.setUnapprovalFlg(rs.getBoolean("unapproved_flg"));

		searchTransferApplicationDTO.setDeleteFlg(rs.getBoolean("delete_flg"));

		searchTransferApplicationDTO.setProjectId(rs.getString("project_id"));
		
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}

		return searchTransferApplicationDTO;

	}

}
