package jp.co.vaile.nerva.transferApplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.DBConnection;
public class ShowTransferApplicationDAO {
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
    static final String ON = " ON ";
    //join用テーブル
    static final String APPLICATION＿TRANSFER = " transfer_application ";
    static final String EMPLOYEE_INFO = " employee_info ";
    static final String M_BELONG_COMPANY = " m_belong_company ";
    static final String TEAM_INFO = " team_info ";
    static final String M_USER = " m_user ";
    static final String NOW_AFFILICATION_TEAM_TABLE = " now_affiliation_team_table ";
    static final String TRANSFER_APPLICATION_TEAM_TABLE = " transferApplicationTeam_table";
    //取得カラム
    static final String MANAGER＿ID = " regist_user ";
    static final String UNAPPROVED＿FLAG = " unapproved_flag ";
    static final String DELETE＿FLAG = " delete_flag ";
    static final String APPLICATION_NUM = " application_num ";
    static final String NOW_AFFILICATION_TEAM_NAME = " now_affiliation_team_name ";
    static final String TRANSFER_APPLICATION_TEAM_NAME = " transfer_application_team_name";
    static final String USER_NAME = " user_name ";
    static final String COMPANY_NAME = " company_name ";
    static final String EMPLOYEE_NAME = " employee_name ";
    static final String NOW_TEAM_MANAGER = " nowTeamManager ";
    static final String TEAM_NAME = " team_name ";
    static final String TRANSFER_PREFERRED_DATE = " transfer_preferred_date";
    static final String TEAM_FINISH_DATE = " team_finish_date";
    static final String APPLICATION_BELONG_COMPANY_NAME = " application_belong_company_name";
    //join用カラム
    static final String APPLICANT_ID = " applicant_id ";
    static final String USER_ID = " user_id ";
    static final String APPLICATION_BELONG_COMPANY = " application_belong_company ";
    static final String COMPANY_ID = " company_id ";
    static final String TRANSFER_EMP_ID = " transfer_emp_id ";
    static final String EMPLOYEE_ID = " employee_id ";
    static final String NOW_AFFILICATION_TEAM = " now_affiliation_team ";
    static final String TEAM_ID = " team_id";
    static final String TRANSFER_APPLICATION_TEAM = " transfer_application_team ";
    static final String TEAM_MANAGER = " team_manager ";
    static final String TEAM_MANAGER_NAME = " team_manager_name ";
    static final String DELETE_FLG = " delete_flg ";
    static final String UNAPPROVED_FLG = " unapproved_flg ";
    public ArrayList<TransferApplicationDTO> showTransferApplication(String loginUserId) throws SQLException, ClassNotFoundException {
        ArrayList<TransferApplicationDTO> transferApplicationDTOArr = new ArrayList<TransferApplicationDTO>();
        TransferApplicationDTO transferApplicationDTO = new TransferApplicationDTO();
        try (Connection con = DBConnection.getConnection();) {
            sql = SELECT + " distinct "+APPLICATION＿TRANSFER + "." + APPLICATION_NUM + ","
                    + APPLICATION＿TRANSFER + "."+ TRANSFER_PREFERRED_DATE + "," 
                    + M_USER + "." + USER_NAME+ ","
                    + M_BELONG_COMPANY + "." + COMPANY_NAME +AS +APPLICATION_BELONG_COMPANY_NAME+ ","
                    + EMPLOYEE_INFO + "." + EMPLOYEE_NAME + ","
                    +NOW_AFFILICATION_TEAM_TABLE + "." + TEAM_NAME +AS + NOW_AFFILICATION_TEAM_NAME+ ","
                    +TRANSFER_APPLICATION_TEAM_TABLE + "." + TEAM_NAME +AS + TRANSFER_APPLICATION_TEAM_NAME

                    + FROM + APPLICATION＿TRANSFER
                    + " LEFT OUTER JOIN "
                    + "(" + SELECT + TEAM_INFO + "."+TEAM_MANAGER+","+ TEAM_INFO + "."+TEAM_NAME+ ","+APPLICATION＿TRANSFER + "."+NOW_AFFILICATION_TEAM + FROM + TEAM_INFO
                    + " LEFT OUTER JOIN " + APPLICATION＿TRANSFER + " ON " + TEAM_INFO + "." + TEAM_ID + "="
                    + APPLICATION＿TRANSFER + "." + NOW_AFFILICATION_TEAM
                    + ")"+ AS + NOW_AFFILICATION_TEAM_TABLE
                    + ON + APPLICATION＿TRANSFER +"." +NOW_AFFILICATION_TEAM + "=" +NOW_AFFILICATION_TEAM_TABLE+"." +NOW_AFFILICATION_TEAM

                    + " LEFT OUTER JOIN "
                    + "(" + SELECT + TEAM_INFO + "."+ TEAM_NAME + ","+APPLICATION＿TRANSFER + "."+TRANSFER_APPLICATION_TEAM+ FROM + TEAM_INFO
                    + " LEFT OUTER JOIN " + APPLICATION＿TRANSFER + " ON " + TEAM_INFO + "." + TEAM_ID + "="
                    + APPLICATION＿TRANSFER + "." + TRANSFER_APPLICATION_TEAM
                    + ")" + AS + TRANSFER_APPLICATION_TEAM_TABLE
                    + ON + APPLICATION＿TRANSFER +"." +TRANSFER_APPLICATION_TEAM + "=" +TRANSFER_APPLICATION_TEAM_TABLE+"." +TRANSFER_APPLICATION_TEAM

                    + " LEFT OUTER JOIN " + M_USER + " ON " + APPLICATION＿TRANSFER + "." + APPLICANT_ID
                    + "=" + M_USER + "." + USER_ID

                    + " LEFT OUTER JOIN " + M_BELONG_COMPANY + " ON " + APPLICATION＿TRANSFER + "."
                    + APPLICATION_BELONG_COMPANY
                    + "=" + M_BELONG_COMPANY + "." + COMPANY_ID
                    + " LEFT OUTER JOIN " + EMPLOYEE_INFO + " ON " + APPLICATION＿TRANSFER + "." + TRANSFER_EMP_ID
                    + "=" + EMPLOYEE_INFO + "." + EMPLOYEE_ID

                    + WH
                    + NOW_AFFILICATION_TEAM_TABLE+"."+TEAM_MANAGER + "= ?" + AND
                    + APPLICATION＿TRANSFER + "." + UNAPPROVED_FLG + "= ? ;";
            try (PreparedStatement ps = con.prepareStatement(sql);) {
                ps.setString(1, loginUserId);
                ps.setInt(2, 0);
                rs = ps.executeQuery();
                while (rs.next()) {
                    transferApplicationDTO = new TransferApplicationDTO();
                    transferApplicationDTO = setShowTransferApplication(rs);
                    transferApplicationDTOArr.add(transferApplicationDTO);
                }
                return transferApplicationDTOArr;
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
    public static TransferApplicationDTO setShowTransferApplication(ResultSet rs) throws SQLException  {
        TransferApplicationDTO transferApplicationDTO = new TransferApplicationDTO();
        try {
			transferApplicationDTO.setApplicationNum(rs.getString("application_num"));

        transferApplicationDTO.setApplicationBelongCompany(rs.getString("application_belong_company_name"));
        transferApplicationDTO.setTransferEmp(rs.getString("employee_name"));
        transferApplicationDTO.setNowApplicationTeam(rs.getString("now_affiliation_team_name"));
        transferApplicationDTO.setApplicant(rs.getString("user_name"));  //now_team_manager
        transferApplicationDTO.setTransferApplicationTeam(rs.getString("transfer_application_team_name"));
        transferApplicationDTO.setTransferPreferredDate(rs.getDate("transfer_preferred_date"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
        return transferApplicationDTO;
    }

}

