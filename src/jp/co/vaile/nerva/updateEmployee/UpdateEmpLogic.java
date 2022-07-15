package jp.co.vaile.nerva.updateEmployee;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;

public class UpdateEmpLogic {
	CommonConstants commonConstants = new CommonConstants();

	/**
	 * 従業員情報更新用DTOを受け取り、従業員更新処理を呼び出す。
	 * @param updateEmpInfo 従業員情報DTOを表す
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void passUpdateEmpInfo(UpdateEmpInfoDTO updateEmpInfoDTO) throws ClassNotFoundException, SQLException {
		//従業員情報更新処理を呼び出す。
		UpdateEmpInfoDAO updateEmpInfoDAO = new UpdateEmpInfoDAO();
		updateEmpInfoDAO.updateEmpInfo(updateEmpInfoDTO);
	}

	/**
	 * スキル情報更新用DTOのリストを受け取り、スキル情報更新処理を呼び出す。
	 * @param updateSkillInfoDTOList スキル情報更新用DTOを格納したリスト
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void passUpdateSkillInfo(List<UpdateSkillInfoDTO> updateSkillInfoDTOList) throws ClassNotFoundException, SQLException, ParseException {
		//リストの長さが0でない場合、スキル情報更新処理を呼び出す。
		if (updateSkillInfoDTOList.size() != 0) {
			UpdateSkillInfoDAO updateSkillInfoDAO = new UpdateSkillInfoDAO();
			updateSkillInfoDAO.updateSkillInfo(updateSkillInfoDTOList);
		}
	}

	/**
	 * スキル情報登録用DTOを受け取り、スキル情報登録処理を呼び出す。
	 * @param registSkillInfoPageDTOList スキル情報登録用DTOを格納したリスト
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void passRegistSkillInfo(List<RegistSkillInfoDTO> registSkillInfoDTOList) throws ClassNotFoundException, SQLException, ParseException {
		//リストの長さが0でない場合、スキル情報登録処理を呼び出す。
		if (registSkillInfoDTOList.size() != 0) {
			UpdateSkillInfoDAO updateSkillInfoDAO = new UpdateSkillInfoDAO();
			updateSkillInfoDAO.insertSkillInfo(registSkillInfoDTOList);
		}
	}

	/**
	 * 従業員業務経験更新用DTOのリストを受け取り、業務経験更新処理を呼び出す。
	 * @param updateEmpWorkExpPageDTOList 従業員業務経験更新用DTOを格納したリスト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void passUpdateEmpWorkExp(List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList) throws ClassNotFoundException, SQLException {
		//リストの長さが0でない場合、従業員業務経験更新処理を呼び出す。
		if (updateEmpWorkExpDTOList.size() != 0) {
			UpdateEmpWorkExpDAO updateEmpWorkExpDAO = new UpdateEmpWorkExpDAO();
			updateEmpWorkExpDAO.updateEmpWorkExp(updateEmpWorkExpDTOList);
		}
	}

	/**
	 * 従業員業務経験登録用DTOを受け取り、従業員業務経験登録処理を呼び出す。
	 * @param registEmpWorkExpDTOList 従業員業務経験登録用DTOを格納したリスト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void passRegistEmpWorkExp(List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList) throws ClassNotFoundException, SQLException {
		//リストの長さが0でない場合、従業員業務経験更新処理を呼び出す。
		if (registEmpWorkExpDTOList.size() != 0) {
			UpdateEmpWorkExpDAO updateEmpWorkExpDAO = new UpdateEmpWorkExpDAO();
			//リストの長さの回数繰り返す。
			for (int i = 0; i < registEmpWorkExpDTOList.size(); i++) {
				//DTOの移管申請フラグをもとに業務経験テーブルか移管申請テーブルか振り分ける
				boolean transferApplicationflg = registEmpWorkExpDTOList.get(i).isApplicationFlag();
				if (transferApplicationflg) {
					updateEmpWorkExpDAO.InsertTransferApplication(registEmpWorkExpDTOList.get(i));
				} else {
					updateEmpWorkExpDAO.insertEmpWorkExp(registEmpWorkExpDTOList.get(i));
				}
			}
		}
	}
}
