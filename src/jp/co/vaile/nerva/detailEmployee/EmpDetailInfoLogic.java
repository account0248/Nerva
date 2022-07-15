package jp.co.vaile.nerva.detailEmployee;

import java.sql.SQLException;
import java.util.List;

public class EmpDetailInfoLogic {
	/**
	 * 呼び出し元のメソッドから渡された従業員IDを受け取り、従業員情報検索処理を呼び出す。
	 * @param employeeId 従業員IDを表す文字列
	 * @return empInfoDTO 従業員情報DTOを表す
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public EmpInfoDTO passEmpInfoDTO(String employeeId) throws ClassNotFoundException, SQLException {
		EmpInfoDAO empInfoDAO = new EmpInfoDAO();
		//従業員情報検索処理を呼び出す。
		EmpInfoDTO empInfoDTO = empInfoDAO.selectEmpInfoByEmpId(employeeId);
		return empInfoDTO;
	}

	/**
	 * 呼び出し元のメソッドから渡された従業員ID受け取り、スキル情報検索処理を呼び出す。
	 * @param employeeId 従業員IDを表す文字列
	 * @return empSkillInfoDTOList スキル情報DTOを格納したリスト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<EmpSkillInfoDTO> passEmpSkillDTO(String employeeId) throws ClassNotFoundException, SQLException{
		EmpSkillDAO empSkillDAO = new EmpSkillDAO();
		//スキル情報検索処理を呼び出す。
		List<EmpSkillInfoDTO> empSkillInfoDTOList = empSkillDAO.selectEmpSkillByEmpId(employeeId);
		return empSkillInfoDTOList;
	}

	/**
	 * 呼び出し元のメソッドから渡された従業員IDを受け取り、従業員業務経験検索処理を呼び出す。
	 * @param employeeId 従業員IDを表す文字列
	 * @return empWorkExpDTOList 従業員業務経験DTOを格納したリスト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<EmpWorkExpDTO> passEmpWorkExpDTO(String employeeId) throws ClassNotFoundException, SQLException{
		EmpWorkExpDAO empWorkExpDAO = new EmpWorkExpDAO();
		//従業員業務経験検索処理を呼び出す。
		List<EmpWorkExpDTO> empWorkExpDTOList = empWorkExpDAO.selectWorkExpByEmpId(employeeId);
		return empWorkExpDTOList;
	}
}
