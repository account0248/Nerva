package jp.co.vaile.nerva.managementattedance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SearchWSDAO {
	/**
	 * 受け取ったパラメータをもとに勤怠情報管理テーブルが登録されているかチェックを行う
	 * 登録されていればtrueを、登録されていなければfalseを返す
	 * @param year
	 * @param month
	 * @param employeeId
	 * @return serchFlg
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean searchWS(String year,String month,String employeeId) throws SQLException, ClassNotFoundException{
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT ");
			sql.append("*");
			sql.append(" FROM ");
			sql.append("attendance_management_info");
			sql.append(" WHERE ");
			sql.append("(");
			sql.append("employee_id = ?");
			sql.append(" AND ");
			sql.append("DATE_FORMAT(fiscal_year_month_date ,'%Y-%m') = ");
			sql.append("?");
			sql.append(")");
			
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, employeeId);
			stmt.setString(2, year+'-'+ month);
			
			ResultSet rs = stmt.executeQuery();

			//検索結果フラグをtrueで初期化する。
			boolean serchFlg = true;
			//検索結果が存在する場合、検索結果フラグにtrueを代入する。
			if(rs.next()) {
				serchFlg = true;
			//検索結果が存在しない場合、検索結果フラグにfalseを代入する。
			}else {
				serchFlg = false;
			}
			
			// 検索結果フラグを戻り値として返す。
			return serchFlg;
	}

}
