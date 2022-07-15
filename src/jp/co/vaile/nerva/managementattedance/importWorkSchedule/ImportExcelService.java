package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;
import jp.co.vaile.nerva.managementattedance.WorkscheduleContents;

public class ImportExcelService {

	/**
	 * エクセルファイルがパスワードで開けるか確認する
	 * 
	 * @param file
	 * @param password
	 * @return flg
	 * @throws GeneralSecurityException
	 */
	public boolean openExcelCheck(Part file, String password) {
		boolean flg = true;
		
		try {
			// Excelファイルへアクセス
			POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
			EncryptionInfo info = new EncryptionInfo(fileSystem);
			Decryptor excelFile = Decryptor.getInstance(info);

			// 開けるか確認する
			if (excelFile.verifyPassword(password) == false) {
				flg = false;
			} else {
				flg = true;
			}
			
		} catch (IOException | GeneralSecurityException | OfficeXmlFileException e) {
			//エラーの場合、falseを格納
			flg = false;
		}
		return flg;
	}

	/**
	 * エクセルファイルをインポートする
	 * 
	 * @param excelFile
	 * @param fileSystem
	 * @param password
	 * @param month
	 * @param days
	 * @return workScheduleDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public List<WorkScheduleDTO> getExcelWorkSchedule(Decryptor excelFile, POIFSFileSystem fileSystem, String password,
			String month, int days) throws ClassNotFoundException, SQLException, GeneralSecurityException, IOException {
		List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();

		// パスワード解除
		excelFile.verifyPassword(password);
		// ファイル内容抽出
		InputStream inputStream = excelFile.getDataStream(fileSystem);
		// Excelファイル作成
		Workbook workbook;
		workbook = WorkbookFactory.create(inputStream);
		// シート抽出
		Sheet sheet = workbook.getSheet(month + WorkscheduleContents.GET_SHEET);

		// インポート処理
		for (int i = 0; i < days; i++) {

			WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();
			Integer day = Integer.valueOf(i + 1);
			Row row = sheet.getRow(WorkscheduleContents.STRAT_ROW_NUMBER + i);
			workScheduleDTO.setDays(day.toString());
			String startTime = getTime(
					getCellValue(row.getCell(WorkscheduleContents.START_TIME_CELL_NUMBER)));
			workScheduleDTO.setStartTime(startTime);
			String endTime = getTime(getCellValue(row.getCell(WorkscheduleContents.END_TIME_CELL_NUMBER)));
			workScheduleDTO.setEndTime(endTime);
			String restTime = getTime(getCellValue(row.getCell(WorkscheduleContents.REST_TIME_CELL_NUMBER)));
			workScheduleDTO.setRest(restTime);
			String holidayWorking = getTime(
					getCellValue(row.getCell(WorkscheduleContents.HOLIDAY_WORKING_TIME_CELL_NUMBER)));
			workScheduleDTO.setHolidayWorking(holidayWorking);
			workScheduleDTO.setHolidayPeriod(
					getCellValue(row.getCell(WorkscheduleContents.HOLIDAY_PERIOD_CELL_NUMBER)));
			workScheduleDTO
					.setHolidayDays(getCellValue(row.getCell(WorkscheduleContents.HOLIDAY_DAYS_CELL_NUMBER)));
			String holidayTime = getTime(
					getCellValue(row.getCell(WorkscheduleContents.HOLIDAY_TIME_CELL_NUMBER)));
			workScheduleDTO.setHolidayTime(holidayTime);
			String lateTime = getTime(getCellValue(row.getCell(WorkscheduleContents.LATE_TIME_CELL_NUMBER)));
			workScheduleDTO.setLateTime(lateTime);
			workScheduleDTO.setRemarks(getCellValue(row.getCell(WorkscheduleContents.REMARKS_CELL_NUMBER)));
			workScheduleDTOList.add(workScheduleDTO);

		}

		return workScheduleDTOList;
	}

	/**
	 * 時間の表示方式に整形
	 * 
	 * @param time
	 * @return time
	 */
	private String getTime(String time) {
		if ((!(time == null)) && (time.length() == 4)) {
			time = "0" + time;
		}
		return time;
	}

	/**
	 * セルの内容を取得する
	 * 
	 * @param cell
	 * @return rtnStr
	 */
	private String getCellValue(Cell cell) {

		String value = "";
		DataFormatter formatter = new DataFormatter();

		// セルの型を判定する
		CellType cType = cell.getCellType();

		switch (cType) {

		case STRING:
			value = cell.getRichStringCellValue().getString();
			break;

		case NUMERIC:
			value = formatter.formatCellValue(cell);
			break;

		case FORMULA:
			value = null;
			break;
			
		default:
			value = null;
			break;
		}

		return value;
	}

}
