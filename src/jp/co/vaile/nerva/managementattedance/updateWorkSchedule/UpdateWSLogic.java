package jp.co.vaile.nerva.managementattedance.updateWorkSchedule;

import java.sql.SQLException;

import jp.co.vaile.nerva.managementattedance.SearchWSDAO;

public class UpdateWSLogic {
	/**
	 * 勤務表の更新を行う
	 * @param updateWSDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void  updateWorkSchedule(UpdateWSDTO updateWSDTO) throws ClassNotFoundException, SQLException {
		
		//検索
		SearchWSDAO searchWSDAO = new SearchWSDAO();
		boolean upFlg = true;
		upFlg = searchWSDAO.searchWS(updateWSDTO.getYear(), updateWSDTO.getMonth(), updateWSDTO.getEmployeeId());
		
		/*
		 * 登録情報があった場合、更新処理を呼び出す
		 * 登録情報が無かった場合、新規登録処理を呼び出す
		 */
		if(!upFlg) {
			//新規登録
			InsertWSDAO insertWSDAO = new InsertWSDAO();
			insertWSDAO.insertWS(updateWSDTO);
			
		}else {
			//更新
			UpdateWSDAO updateWSDAO = new UpdateWSDAO();
			updateWSDAO.updateWS(updateWSDTO);
		}
	}

}
