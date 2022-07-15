package jp.co.vaile.nerva.updateEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;

public class CheckBeforeUpdateEmpDAO {
	//エラーメッセージ用のインスタンス、変数を宣言
	ErrorMsg errorMsg = new ErrorMsg();
	String[] updateErrorMsg = { UPDATE };
	
	//従業員情報テーブルから指定したレコードが最新の情報か確認するSQL文
	private final String SELECT_EMP_INFO = "SELECT * FROM employee_info WHERE employee_id = ? AND employee_name = ?"
			+ " AND sex = ? AND office = ? AND birth_date = ? AND postal_code = ? AND address = ?"
			+ " AND phone_number = ? AND mail = ?";
	//スキル情報テーブルから指定したレコードが最新の情報か確認するSQL文
	private final String SELECT_SKILL_INFO = "SELECT * FROM skill_info WHERE skill_info_id = ? AND latest_flg = false";
	//従業員業務経験テーブルから指定したレコードが最新の情報か確認するSQL文
	private final String SELECT_EMP_EXP = "SELECT * FROM employee_experience WHERE employee_experience_id = ? "
			+ "AND latest_flg = false ";

	/**
	 * 従業員情報DTOを受け取り、従業員更新画面に表示した情報と更新しようとしている情報に差異がないか調べる。
	 * @param EmpInfoDTO 従業員情報画面DTOを表す
	 * @return エラーメッセージを表す文字列
	 * @throws ClassNotFoundException | SQLException 
	 */
	String checkEmployeeInfo(EmpInfoDTO EmpInfoDTO) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//従業員情報画面DTOをもとに従業員情報テーブルのレコードを取得する。
			PreparedStatement pStmt = conn.prepareStatement(SELECT_EMP_INFO);
			pStmt.setString(1, EmpInfoDTO.getEmployeeId());
			pStmt.setString(2, EmpInfoDTO.getEmployeeName());
			pStmt.setString(3, EmpInfoDTO.getSex());
			pStmt.setString(4, EmpInfoDTO.getOffice());
			pStmt.setString(5, EmpInfoDTO.getBirthDate());
			pStmt.setString(6, EmpInfoDTO.getPostalCode());
			pStmt.setString(7, EmpInfoDTO.getAddress());
			pStmt.setString(8, EmpInfoDTO.getPhoneNumber());
			pStmt.setString(9, EmpInfoDTO.getMail());
			ResultSet rs = pStmt.executeQuery();
			rs.last();
			//レコードが存在しなかった場合、エラーメッセージを追加し戻り値を返す。
			if (rs.getRow() == 0) {
				errorMessage = errorMsg.returnErrorMsg(PROCESS_FAILURE_ERROR_MESSAGE, updateErrorMsg);
				return errorMessage;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//戻り値を返す。
		return errorMessage;
	}

	/**
	 * スキル情報画面DTOのリストを受け取り、従業員更新画面に表示した情報と更新しようとしている情報に差異がないか調べる。
	 * @param updateSkillInfoDTOList スキル情報更新用DTOを格納したリスト
	 * @return エラーメッセージを表す文字列
	 * @throws ClassNotFoundException, SQLException 
	 */
	String checkSkillInfo(List<EmpSkillInfoDTO> empSkillInfoDTOList) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//リストの長さの回数繰り返す。
			for (int i = 0; i < empSkillInfoDTOList.size(); i++) {
				//スキル情報IDをもとに更新フラグがfalseのスキル情報テーブルのレコードを取得する。
				PreparedStatement pStmt = conn.prepareStatement(SELECT_SKILL_INFO);
				pStmt.setInt(1, empSkillInfoDTOList.get(i).getSkillInfoId());
				ResultSet rs = pStmt.executeQuery();
				rs.last();
				//レコードが存在しなかった場合、エラーメッセージを追加し戻り値を返す。
				if (rs.getRow() == 0) {
					errorMessage = errorMsg.returnErrorMsg(PROCESS_FAILURE_ERROR_MESSAGE, updateErrorMsg);
					return errorMessage;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//戻り値を返す。
		return errorMessage;
	}

	/**
	 * 従業員情報画面DTOを受け取り、従業員更新画面に表示した情報と更新しようとしている情報に差異がないか調べる。
	 * @param updateEmpWorkExpDTOList 従業員業務経験更新用DTOのリストを表す
	 * @return エラーメッセージを表す文字列
	 * @throws ClassNotFoundException, SQLException 
	 */
	String checkEmployeeExperience(List<EmpWorkExpDTO> empWorkExpDTOList) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//リストの長さの回数繰り返す。
			for (int i = 0; i < empWorkExpDTOList.size(); i++) {
				//従業員業務経験情報IDをもとに更新フラグがfalseの従業員業務経験情報テーブルのレコードを取得する。
				PreparedStatement pStmt = conn.prepareStatement(SELECT_EMP_EXP);
				pStmt.setInt(1, empWorkExpDTOList.get(i).getEmployeeExperienceId());
				ResultSet rs = pStmt.executeQuery();
				rs.last();
				//レコードが存在しなかった場合、エラーメッセージを追加し戻り値を返す。
				if (rs.getRow() == 0) {
					errorMessage = errorMsg.returnErrorMsg(PROCESS_FAILURE_ERROR_MESSAGE, updateErrorMsg);
					return errorMessage;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return errorMessage;
	}
}
