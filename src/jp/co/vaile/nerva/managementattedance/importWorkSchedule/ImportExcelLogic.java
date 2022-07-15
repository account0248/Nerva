package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class ImportExcelLogic {
	ErrorMsg errorMsg = new ErrorMsg();
	/**
	 * 勤怠情報管理画面で選択された勤務表の内容を取得する。
	 * @param excelFile
	 * @param fileSystem
	 * @param password
	 * @param month
	 * @param days
	 * @return workscheduleDTOList
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<WorkScheduleDTO> importExcelWS(Decryptor excelFile, POIFSFileSystem fileSystem, String password,
			String month,int days) throws IOException, GeneralSecurityException, ClassNotFoundException, SQLException{
		List<WorkScheduleDTO> workscheduleDTOList = new ArrayList<>();
		ImportExcelService importExcelService = new ImportExcelService();
		//エクセルインポート処理を呼び出す
		workscheduleDTOList = importExcelService.getExcelWorkSchedule(excelFile, fileSystem, password, month, days);
		
		return workscheduleDTOList;
	}
	
	/**
	 * 勤務表チェック処理を呼び出す
	 * @param workscheduleDTOList
	 * @return errorList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> importExcelWSCheck(List<WorkScheduleDTO> workscheduleDTOList) throws ClassNotFoundException, SQLException{
		List<String> errorList = new ArrayList<>();
		WorkScheduleCheck workScheduleCheck = new WorkScheduleCheck();
		//エラーチェック処理を呼び出す
		errorList = errorMsg.errorMsgNullCheck(workScheduleCheck.checkWorkSchedule(workscheduleDTOList),errorList);
		
		return errorList;
	}
	
}
