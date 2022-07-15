package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateSkillInfoDAO {
	//スキル情報テーブルの変更があったレコードをUPDATEするSQL文
	private final String UPDATE_SKILL_INFO_SET_OLDER = "UPDATE skill_info SET update_time = CURRENT_TIMESTAMP, update_user = ?, "
			+ "latest_flg = 1 WHERE skill_info_id = ?";
	//スキル情報テーブルの変更があったレコードをINSERTするSQL文
	private final String INSERT_MODIFICATION_SKILL_INFO = "INSERT INTO skill_info (employee_id, skill_type_id, skill_detail, "
			+ "experience_years, acquisition_year_month, regist_time, regist_user, latest_flg) VALUES (?,?,?,?,?,CURRENT_TIMESTAMP,"
			+ "?,0)";
	//スキル情報テーブルに新しくレコードをINSERTするSQL文
	private final String INSERT_SKILL_INFO = "INSERT INTO skill_info (employee_id, skill_type_id, skill_detail, "
			+ "experience_years, acquisition_year_month, regist_time, regist_user, latest_flg) VALUES (?,?,?,?,?,CURRENT_TIMESTAMP,"
			+ "?,0)";

	/**
	 * スキル情報更新用DTOのリストを受け取り、スキル情報を更新する。
	 * @param updateSkillInfoDTOList スキル情報更新用DTOを格納したリスト
	 * @throws ClassNotFoundException, SQLException, ParseException 
	 */
	void updateSkillInfo(List<UpdateSkillInfoDTO> updateSkillInfoDTOList) throws ClassNotFoundException, SQLException, ParseException {
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {
			//リストの長さ分繰り返す。
			for (int i = 0; i < updateSkillInfoDTOList.size(); i++) {
				//DTOから・スキル情報ID・ログインユーザーIDを取り出し、SQL文に追加して更新フラグを変更するSQL文を送る。
				int skillInfoId = updateSkillInfoDTOList.get(i).getSkillInfoId();
				String loginUserId = updateSkillInfoDTOList.get(i).getLoginUserId();
				PreparedStatement pStmt = conn.prepareStatement(UPDATE_SKILL_INFO_SET_OLDER);
				pStmt.setString(1, loginUserId);
				pStmt.setInt(2, skillInfoId);
				pStmt.executeUpdate();
				
				//DTOからスキル情報ID以外の情報を取り出し、SQL文に追加してSQL文を送る。
				String employeeId = updateSkillInfoDTOList.get(i).getEmployeeId();
				String skillTypeId = updateSkillInfoDTOList.get(i).getSkillTypeId();
				String skillDetail = updateSkillInfoDTOList.get(i).getSkillDetail();
				String experienceYears = updateSkillInfoDTOList.get(i).getExperienceYears();
				String acquisitionYearMonth = updateSkillInfoDTOList.get(i).getAcquisitionYearMonth();
				pStmt = conn.prepareStatement(INSERT_MODIFICATION_SKILL_INFO);
				pStmt.setString(1, employeeId);
				pStmt.setString(2, skillTypeId);
				pStmt.setString(3, skillDetail);
				pStmt.setString(4, experienceYears);
				if(acquisitionYearMonth != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Date acquisitionYearMonthDate = sdf.parse(acquisitionYearMonth);
					java.sql.Date  acquisitionYearMonthSetDate = new java.sql.Date(acquisitionYearMonthDate.getTime());
					pStmt.setDate(5, acquisitionYearMonthSetDate);
				}else {
					pStmt.setString(5, acquisitionYearMonth);
				}
				pStmt.setString(6, loginUserId);
				pStmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * スキル情報登録用DTOのリストを受け取り、スキル情報を登録する。
	 * @param registSkillInfoDTOList スキル情報登録用DTOを格納したリスト
	 * @throws ClassNotFoundException, SQLException, ParseException 
	 */
	void insertSkillInfo(List<RegistSkillInfoDTO> registSkillInfoDTOList) throws ClassNotFoundException, SQLException, ParseException {
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {
			//リストの長さ分繰り返す。
			for (int i = 0; i < registSkillInfoDTOList.size(); i++) {

				//DTOからスキル情報を取り出し、SQL文に追加してSQL文を送る。
				String employeeId = registSkillInfoDTOList.get(i).getEmployeeId();
				String loginUserId = registSkillInfoDTOList.get(i).getLoginUserId();
				String skillTypeId = registSkillInfoDTOList.get(i).getSkillTypeId();
				String skillDetail = registSkillInfoDTOList.get(i).getSkillDetail();
				String experienceYears = registSkillInfoDTOList.get(i).getExperienceYears();
				String acquisitionYearMonth = registSkillInfoDTOList.get(i).getAcquisitionYearMonth();
				PreparedStatement pStmt = conn.prepareStatement(INSERT_SKILL_INFO);
				pStmt.setString(1, employeeId);
				pStmt.setString(2, skillTypeId);
				pStmt.setString(3, skillDetail);
				pStmt.setString(4, experienceYears);
				if(acquisitionYearMonth != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Date acquisitionYearMonthDate = sdf.parse(acquisitionYearMonth);
					java.sql.Date  acquisitionYearMonthSetDate = new java.sql.Date(acquisitionYearMonthDate.getTime());
					pStmt.setDate(5, acquisitionYearMonthSetDate);
				}else {
					pStmt.setString(5, acquisitionYearMonth);
				}
				pStmt.setString(6, loginUserId);
				pStmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
