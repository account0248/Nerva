package jp.co.vaile.nerva.registEmployee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class RegistSkillInfoDAO {
	private final String INSERT_SKILL_INFO = "INSERT INTO skill_info (" +
			"employee_id," +
			"skill_type_id," +
			"skill_detail," +
			"experience_years," +
			"acquisition_year_month," +
			"regist_time," +
			"regist_user," +
			"latest_flg)" +
			"VALUES(?,?,?,?,?,CURRENT_TIMESTAMP,?,0);";

	/**
	 * DBのスキル情報テーブルに引数のスキル情報リストの情報を順番に登録する。
	 * @param registSkillInfoDTOList 登録されるスキル情報のリスト
	 * @param registEmpInfoDTO 登録される従業員情報
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void insertSkillInfo(List<RegistSkillInfoDTO> registSkillInfoDTOList) throws ClassNotFoundException, SQLException {

		//1DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_SKILL_INFO);) {
				//3.	P1のスキル情報リストの長さの回数2を繰り返す。
				for (RegistSkillInfoDTO registSkillInfoDTO : registSkillInfoDTOList) {
					//2.	P1のスキル情報リストの中身を一つ取り出し、スキル情報テーブルに登録する。
					stmt.setString(1, registSkillInfoDTO.getEmployeeId());
					stmt.setString(2, registSkillInfoDTO.getSkillTypeId());
					stmt.setString(3, registSkillInfoDTO.getSkillDetail());
					stmt.setString(4, registSkillInfoDTO.getExperienceYears());
					if (registSkillInfoDTO.getAcquisitionYearMonth() != null) {
						java.sql.Date birthDate = new Date(
								registSkillInfoDTO.getAcquisitionYearMonth().getTime());
						stmt.setDate(5, birthDate);
					} else {
						stmt.setDate(5, null);
					}
					stmt.setString(6, registSkillInfoDTO.getRegistUser());
				}
				stmt.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
