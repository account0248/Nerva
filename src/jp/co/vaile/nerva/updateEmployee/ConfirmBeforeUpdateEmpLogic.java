package jp.co.vaile.nerva.updateEmployee;

import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;

public class ConfirmBeforeUpdateEmpLogic {
	ErrorMsg errorMsg = new ErrorMsg();
	CheckBeforeUpdateEmpDAO checkBeforeUpdateEmpDAO = new CheckBeforeUpdateEmpDAO();

	/**
	 * 従業員情報をDTOとして受け取り、更新機能を呼び出す前に
	 * テーブルの情報が書き換わっていないかを確認する。
	 * @param empInfoDTO 従業員情報DTO
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	String checkEmpInfoBeforeUpdate(EmpInfoDTO empInfoDTO) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//従業員情報DTOをもとに従業員情報テーブル確認処理を呼び出す。
		errorMessage = checkBeforeUpdateEmpDAO.checkEmployeeInfo(empInfoDTO);
		//エラーメッセージがある場合、エラーメッセージを返す。
		if (errorMessage != null) {
			return errorMessage;
		}
		return null;
	}

	/**
	 * スキル情報をDTOとして受け取り、更新機能を呼び出す前に
	 * テーブルの情報が書き換わっていないかを確認する。
	 * @param empSkillInfoDTOList スキル情報更新用DTOのリスト
	 * @return エラーメッセージ
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	String checkSkillInfoBeforeUpdate(List<EmpSkillInfoDTO> empSkillInfoDTOList) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//スキル情報DTOの長さが0でない場合
		if (empSkillInfoDTOList != null) {
			//スキル情報IDをもとにスキル情報テーブル確認処理を呼び出す。
			errorMessage = checkBeforeUpdateEmpDAO.checkSkillInfo(empSkillInfoDTOList);
			//エラーメッセージがある場合、エラーメッセージを返す。
			if (errorMessage != null) {
				return errorMessage;
			}
		}
		return null;
	}

	/**
	 * 業務経験情報をDTOとして受け取り、更新機能を呼び出す前に
	 * テーブルの情報が書き換わっていないかを確認する。
	 * @param empWorkExpDTOList 従業員業務経験更新用DTOのリスト
	 * @return エラーメッセージ
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	String checkWorkExpBeforeUpdate(List<EmpWorkExpDTO> empWorkExpDTOList) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//業務経験情報DTOの長さが0でない場合
		if (empWorkExpDTOList != null) {
			//従業員業務経験情報IDをもとに従業員業務経験テーブル確認処理を呼び出す。
			errorMessage = checkBeforeUpdateEmpDAO.checkEmployeeExperience(empWorkExpDTOList);
			//エラーメッセージがある場合、エラーメッセージを返す。
			if (errorMessage != null) {
				return errorMessage;
			}
		}
		return null;
	}
}
