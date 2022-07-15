package jp.co.vaile.nerva.managementattedance.displayWorkSchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.managementattedance.HolidayPeriodDTO;
import jp.co.vaile.nerva.managementattedance.SearchHolidayPeriodDAO;

public class DisplayHolidayPeriodLogic {
	
	/**
	 * 休暇期間等マスタをすべて取得する
	 * @return HolidayPeriodDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<HolidayPeriodDTO> displayHolidayPeriod() throws ClassNotFoundException, SQLException{
		
		List<HolidayPeriodDTO> HolidayPeriodDTOList = new ArrayList<>();
		SearchHolidayPeriodDAO shDAO = new SearchHolidayPeriodDAO();
		
		//休暇期間等取得
		HolidayPeriodDTOList = shDAO.serchHolidayPeriodDTOList();
		
		return HolidayPeriodDTOList;
	}

}
