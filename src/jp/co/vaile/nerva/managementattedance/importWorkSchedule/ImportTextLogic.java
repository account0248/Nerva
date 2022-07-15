package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class ImportTextLogic {

	/**
	 * インポート処理を呼び出し、取得した勤務表DTOリストを返す
	 * @param textFile
	 * @return workScheduleDTOList
	 * @throws IOException
	 */
	public List<WorkScheduleDTO> importTextWS(Part textFile) throws IOException{
		List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();
		ImportTextService importTextService = new ImportTextService();
		//勤務表インポート
		workScheduleDTOList = importTextService.getTextWorkSchedule(textFile);
		
		return workScheduleDTOList;
	}
	
	/**
	 * 勤務表チェック処理を呼び出し、エラーがあった場合エラーリストを返す
	 * @param workScheduleDTOList
	 * @return errorList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> importTextWSCheck(List<WorkScheduleDTO> workScheduleDTOList) throws ClassNotFoundException, SQLException{
		List<String> errorList = new ArrayList<>();
		WorkScheduleCheck workScheduleCheck = new WorkScheduleCheck();
		ErrorMsg errorMsg = new ErrorMsg();
		
		//エラーチェック処理を呼び出す
		String error = workScheduleCheck.checkWorkSchedule(workScheduleDTOList);
		errorList = errorMsg.errorMsgNullCheck(error, errorList);
		
		
		return errorList;
	}
}
