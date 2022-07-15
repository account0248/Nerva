package jp.co.vaile.nerva.updateProject;

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

public class ShowProjectUpdateDAO {

	//ログインユーザーIDを基にDBの案件詳細テーブル,チーム情報テーブル,従業員業務経験テーブル,従業員情報テーブル,所属会社マスタ,ユーザーマスタからデータを取得するSQL
	private  String selectAddableTeam = " SELECT" +
			" DISTINCT" +
			" team_info.team_id, " +
			" team_info.team_name, " +
			" m_belong_company.company_id, " +
			" m_belong_company.company_name, " +
			" m_user.user_id, " +
			" m_user.user_name, " +
			" team_leader.leader_id, " +
			" team_leader.leader_name " +
			" FROM " +
			" team_info " +
			" LEFT JOIN m_user  ON team_info.team_manager = m_user.user_id " +
			" LEFT JOIN m_belong_company ON m_user.company_id = m_belong_company.company_id " +
			" LEFT JOIN project_detail_info  ON team_info.team_id = project_detail_info.team_id " +
			" LEFT  JOIN (SELECT " +
			" employee_info.employee_name AS leader_name, " +
			" employee_info.employee_id AS leader_id, " +
			" employee_experience.team_id " +
			" FROM " +
			" employee_experience " +
			" LEFT  JOIN employee_info ON employee_experience.employee_id = employee_info.employee_id " +
			" WHERE employee_experience.role_id = 'T000000001')AS team_leader " + /*チームリーダーサブクエリ*/
			" ON team_info.team_id = team_leader.team_id " +
			" WHERE team_info.team_id NOT IN (SELECT team_id FROM project_detail_info) " + /*案件所属してないか、配属完了日が過ぎてる(過ぎてるのに削除フラグfalse)*/
			" AND team_info.delete_flg =false "; /*チーム未削除*/

	/**
	 * 所属会社IDを基にDBの案件詳細テーブル,チーム情報テーブル,従業員業務経験テーブル,従業員情報テーブル,所属会社マスタ,ユーザーマスタ
	 * からデータを取得し、参加可能チームDTOに格納する。
	 * 参加可能チームDTOのリストを返す。
	 * @param loginBelongCompanyId 所属会社ID
	 * @return addableTeamList 参加可能チームDTOリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	ArrayList<AddableTeamDTO> selectAddableTeam( String loginBelongCompanyId)
			throws ClassNotFoundException, SQLException {
		
		//参加可能チームDTOのリストを作成する
		ArrayList<AddableTeamDTO> addableTeamList = new ArrayList<AddableTeamDTO>();

		//V会社のIDを取得
		AnyMasterTableDAO anyMasterTableDAO = new AnyMasterTableDAO();
		List<FetchAnyMasterTableDTO> belongCompanyList = anyMasterTableDAO
				.fetchAnyMasterTable(BELONG_COMPANY_TABLE_NAME);
		
		//ログインユーザの所属会社がV会社でない場合WHERE句追加
		if (!(loginBelongCompanyId.equals(belongCompanyList.get(0).getMasterDataId()))) {
			selectAddableTeam += " AND m_user.company_id = ? ";
		}
		
		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(selectAddableTeam);) {


				//ログインユーザーの所属会社IDによって制限をかける
				if (!(loginBelongCompanyId.equals(belongCompanyList.get(0).getMasterDataId()))) {
					stmt.setString(1, loginBelongCompanyId);
				}

				/*取得件数分リストに格納する処理*/
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					//参加可能チームDTOを作成する
					AddableTeamDTO addableTeamDto = new AddableTeamDTO();
					//取得したデータを参加可能チームDTOに格納する
					addableTeamDto.setTeamId(rs.getString("team_info.team_id"));
					addableTeamDto.setTeamName(rs.getString("team_info.team_name"));
					addableTeamDto.setBelongCompanyId(rs.getString("m_belong_company.company_id"));
					addableTeamDto.setBelongCompanyName(rs.getString("m_belong_company.company_name"));
					addableTeamDto.setTeamManagerId(rs.getString("m_user.user_id"));
					addableTeamDto.setTeamManagerName(rs.getString("m_user.user_name"));
					addableTeamDto.setTeamLeaderId(rs.getString("team_leader.leader_id"));
					addableTeamDto.setTeamLeaderName(rs.getString("team_leader.leader_name"));
					//参加可能チームDTOを参加可能チームDTOのリストに格納する
					addableTeamList.add(addableTeamDto);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//参加可能チームDTOのリストを返す
		return addableTeamList;
	}
}
