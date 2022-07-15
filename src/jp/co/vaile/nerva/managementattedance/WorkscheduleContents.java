package jp.co.vaile.nerva.managementattedance;

public class WorkscheduleContents {
	
	//画面からサーブレットへファイル送信時に使用するP
	public static final int FILESIZETHRESHOLD = 5000000;
	public static final long MAXFILESIZE = 5242880;
	public static final String IMPORT_PATH = "C:\\pleiades-2021-06-java-win-64bit-jre_20210629\\pleiades\\tomcat\\8\\temp";
	
	//インポート時のチェック処理で使用
	public static final String DAY = "日";
	public static final String START_TIME = "開始";
	public static final String END_TIME = "終了";
	public static final String REST_TIME = "休憩";
	public static final String HOLIDAY_WORKING_TIME = "法定休日出勤時間";
	public static final String HOLIDAY_PERIOD = "休暇期間等";
	public static final String HOLIDAY_DAYS = "休暇日数";
	public static final String HOLIDAY_TIME = "休暇時間";
	public static final String LATE_TIME = "遅早時刻";
	public static final String REMARKS = "備考";
	public static final String NONE = "-";
	
	//Excelインポート時に使用
	public static final String GET_SHEET = "月";
	public static final int STRAT_ROW_NUMBER = 5;
	public static final int START_TIME_CELL_NUMBER = 2;
	public static final int END_TIME_CELL_NUMBER = 3;
	public static final int REST_TIME_CELL_NUMBER = 4;
	public static final int HOLIDAY_WORKING_TIME_CELL_NUMBER = 8;
	public static final int HOLIDAY_PERIOD_CELL_NUMBER = 9;
	public static final int HOLIDAY_DAYS_CELL_NUMBER = 10;
	public static final int HOLIDAY_TIME_CELL_NUMBER = 11;
	public static final int LATE_TIME_CELL_NUMBER = 12;
	public static final int REMARKS_CELL_NUMBER = 13;
	

}
