package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Part;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;
import jp.co.vaile.nerva.managementattedance.WorkscheduleContents;

public class ImportTextService {

	/**
	 * テキストファイルの内容を取得し、勤務表リストに格納する
	 * @param textFile
	 * @return
	 * @throws IOException
	 */
	public List<WorkScheduleDTO> getTextWorkSchedule(Part textFile) throws IOException {
		List<WorkScheduleDTO> workScheduleList = new ArrayList<>();
		Scanner scanner = new Scanner(textFile.getInputStream());
		// 改行
		scanner.nextLine();
		// 次の行がある間ループ
		while (scanner.hasNextLine()) {
			WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();

			// 1行分の文字列取得
			String allValue = scanner.next();
			// 項目数カウント
			int count = 0;
			for (char x : allValue.toCharArray()) {
				if (x == ';') {
					count++;
				}
			}
			//行の内容を取り出す
			for (int i = 0; i < count; i++) {
				String value = allValue;

				// ","があるか確認し再代入
				if (allValue.contains(",")) {
					// 項目と内容取得
					value = allValue.substring(0, allValue.indexOf(","));
					// 取得した1行分の文字列から切り取りした文字列を削除
					allValue = allValue.substring(allValue.indexOf(",") + 1);
				}

				int index = value.indexOf(";");
				String check = value.substring(0, index);
				String setValue = value.substring(index + 1);
				// 項目によってセット
				switch (check) {

				case WorkscheduleContents.DAY:
					workScheduleDTO.setDays(setValue);
					break;

				case WorkscheduleContents.START_TIME:
					workScheduleDTO.setStartTime(setValue);
					break;

				case WorkscheduleContents.END_TIME:
					workScheduleDTO.setEndTime(setValue);
					break;

				case WorkscheduleContents.REST_TIME:
					workScheduleDTO.setRest(setValue);
					break;

				case WorkscheduleContents.HOLIDAY_WORKING_TIME:
					workScheduleDTO.setHolidayWorking(setValue);
					break;

				case WorkscheduleContents.HOLIDAY_PERIOD:
					workScheduleDTO.setHolidayPeriod(setValue);
					break;

				case WorkscheduleContents.HOLIDAY_DAYS:
					workScheduleDTO.setHolidayDays(setValue);
					break;

				case WorkscheduleContents.HOLIDAY_TIME:
					workScheduleDTO.setHolidayTime(setValue);
					break;

				case WorkscheduleContents.LATE_TIME:
					workScheduleDTO.setLateTime(setValue);
					break;

				case WorkscheduleContents.REMARKS:
					workScheduleDTO.setRemarks(setValue);
					break;

				}
			}

			workScheduleList.add(workScheduleDTO);
		}

		return workScheduleList;
	}

}
