package jp.co.vaile.nerva.managementattedance.displayWorkSchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class DisplayWorkScheduleLogic {

	/**
	 * DBに登録されている勤務表を取得する
	 * @param displayWSDTO
	 * @return workScheduleDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<WorkScheduleDTO> displayWorkSchedule(DisplayWSDTO displayWSDTO) throws ClassNotFoundException, SQLException{
		
		List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();
		DisplayWorkScheduleDAO displayWorkScheduleDAO = new DisplayWorkScheduleDAO();
		
		//勤務表取得
		workScheduleDTOList = displayWorkScheduleDAO.displayWSDTOList(displayWSDTO);
		
		return workScheduleDTOList;
	}
	
}
