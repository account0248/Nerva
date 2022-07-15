
package jp.co.vaile.nerva.commonprocess.errormessage;

public class CommonConstants {

	//メッセージID
	//W000000={0}はかならず入力してください。
	public static final String REQUIRED_ERROR_MESSAGE = "W000000";
	//W000001={0}はかならず選択してください。
	public static final String REQUIRED_SELECT_ERROR_MESSAGE = "W000001";
	//W000002={0}は{1}文字以上で入力してください。
	public static final String INPUT_MIN_LENGTH_ERROR_MESSAGE = "W000002";
	//W000003={0}は{1}文字以内で入力してください。
	public static final String INPUT_MAX_LENGTH_ERROR_MESSAGE = "W000003";
	//W000004={0}は{1}文字で入力してください。
	public static final String INPUT_LENGTH_ERROR_MESSAGE = "W000004";
	//W000005={0}は正しく選択してください。
	public static final String VALIDITY_SELECT_ERROR_MESSAGE = "W000005";
	//W000006={0}は{1}と同日以降の日付を選択してください。
	public static final String AFTER_DATE = "W000006";
	//W000007={0}は{1}と同日以前の日付を選択してください。
	public static final String BEFORE_DATE = "W000007";
	//W000008={0}は半角英数字で入力してください。
	public static final String HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE = "W000008";
	//W000009={0}は半角数字で入力してください。
	public static final String HARF_WIDTH_NUM_ERROR_MESSAGE = "W000009";
	//W000012={0}は半角英数字で形式通りに入力してください。
	public static final String PROJECT_ID_FORMAT_ERROR_MESSAGE = "W000012";
	//W000014={0}検索結果は0件です。
	public static final String SEARCH_RESULT_EMPTY_ERROR_MESSAGE = "W000014";
	//W000016={0}に失敗しました。
	public static final String PROCESS_FAILURE_ERROR_MESSAGE = "W000016";
	//W000017={0}は削除することが出来ません。
	public static final String CAN_NOT_DELETE_ERROR_MESSAGE = "W000017";
	//W000020={0}は既に使われています。
	public static final String ID_EXIST_ERROR_MESSAGE = "W000020";
	//W000022={0}は形式通りに入力してください。
	public static final String FORMAT_INPUT_ERROR_MESSAGE = "W000022";
	//W000023={0}は形式通りに入力してください。
    public static final String DUPLICATE_INPUT_ERROR_MESSAGE = "W000023";
	//W100001=このチームには既にリーダーが存在します。
	public static final String ALREADY_LEADER ="W100001";
	//W100002=ファイルは指定されたフォーマットを使用してください。
	public static final String FILE_FORMAT ="W100002";
	//W100003=パスワードが一致しません。
	public static final String PASSWORD_MATCH ="W100003";
	//W200001=このチームの詳細情報は閲覧できません。
	public static final String TEAM_NO_AVAILABLE ="W200001";
	//W300001={0}は大文字、小文字、数字を含む半角英数字で入力してください。
	public static final String PASSWORD_FORMAT = "W300001";
	//W500001={0}は日付順に入力してください。
	public static final String DATE_INPUT_ORDER = "W500001";
	//W500003=移管申請は一件しか行えません。
	public static final String TOTAL_TRANSFER_APPLICATION = "W500003";
	//W500004=この従業員は現在移管申請中です。
	public static final String TRNSFER_ERROR_MESSAGE = "W500004";
	//W600001=同じチームが選択されています。
	public static final String REPEATED_TEAM_EROOR_MESSAGE = "W600001";
	//W600003=更新するものがありません。全て最新の情報です。
	public static final String UPDATE_PROJECT_NON_MESSAGE ="W600003";
	//W600004=この{0}は存在しません。
	public static final String NON_EMP_ERROR = "W600004";
	//W600005=予期せぬエラーが発生しました。
	public static final String TODAY_EMP_TEAM_FINISH_ERROR = "W600005";

	//機能別共通定数
	//従業員登録
	public static final String EMPLOYEE_ID = "従業員ID";
	public static final String EMPLOYEE_NAME = "従業員名";
	public static final String SEX = "性別";
	public static final String BIRTHDATE = "生年月日";
	public static final String BELONGC_COMPANY_NAME = "所属会社";
	public static final String OFFICE_NAME = "事業所";
	public static final String DEPARTMENT = "所属組織";
	public static final String POSTAL_CODE_NAME = "郵便番号";
	public static final String ADDRESS_NAME = "住所";
	public static final String PHONE_NUMBER_NAME = "電話番号";
	public static final String POST = "役職";
	public static final String MAIL_NAME = "メールアドレス";
	public static final String SKILL_INFO_NAME = "保有スキル";
	public static final String SKILL_TYPE = "スキル種別";
	public static final String REGIST_EMPLOYEE = "従業員登録";
	public static final String EXPERIENCE_YEARS_NAME = "経験年数";
	public static final String ACQUISITION_YEAR_MONTH = "取得年月";
	public static final String SKILL_DETAIL_NAME = "内容";
	public static final String BELONG_COMPANY_TABLE_NAME = "m_belong_company";
	public static final String BELONG_DEPARTMENT_TABLE_NAME = "m_belong_department ";
	public static final String POST_TABLE_NAME = "m_post";
	public static final String SKILL_TYPE_TABLE_NAME = "m_skill_type";
	// 案件登録
	public static final String PROJECT_ID = "案件ID";
	public static final String PROJECT_NAME = "案件名";
	public static final String ORDER_SOURCE_NAME = "発注元";
	public static final String INDUSTRY = "業種";
	public static final String PROJECT_START_DATE = "案件開始日";
	public static final String PROJECT_COMPLETE_DATE = "案件完了日";
	public static final String ENTRY_TEAM_ASSIGN_START_DATE = "配属開始日";
	public static final String ENTRY_TEAM_ASSIGN_COMPLETE_DATE = "配属完了日";
	public static final String ADD_ENTRY_TEAM_ASSIGN_START_DATE = "追加されたチームの配属開始日";
	public static final String ADD_ENTRY_TEAM_ASSIGN_COMPLETE_DATE = "追加されたチームの配属完了日";
	public static final String REGIST_PROJECT = "案件登録";
	public static final String TEAM = "チーム";
	//従業員検索
	public static final String SKILL_FILTERING = "スキル絞り込み";
	public static final String TEAM_MANAGER = "担当部長";
	public static final String RESPONSIBLE_PROJECT_ID = "担当案件ID";
	public static final String RESPONSIBLE_PROJECT_NAME = "担当案件名";
	public static final String SEARCH_EMPLOYEE = "従業員";
	// チーム作成
	public static final String TEAM_ID = "チームID";
	public static final String TEAM_NAME = "チーム名";
	public static final String TEAM_LEADER = "チームリーダー";
	public static final String DELETE_TEAM = "チーム削除";
	public static final String EMPLOYEE_ID_INITIAL_V = "V";
	public static final String EMPLOYEE_ID_INITIAL_K = "K";
	public static final String EMPLOYEE_ID_INITIAL_O = "O";
	public static final String BELONG_MANAGER = "所属部長";
	public static final String CREATE_TEAM = "チーム作成";
	public static final String TEAM_ID_K = "T0000K";
	public static final String TEAM_ID_O = "T0000O";
	//移管申請
	public static final String EMPLOYEE = "従業員";
	// 案件検索
	public static final String RESIPOSIBLE_NAME = "責任者名";
	public static final String CONTRACTOR_NAME = "受注元";
	public static final String TOTAL_TEAMS_PROJECT = "参加チーム数";
	public static final String TOTAL_EMP_PROJECT = "総参加人数";
	public static final String SEARCH_PROJECT = "案件";
	//案件更新
	public static final String  EMP_TEAM_BELONG_START_DATE ="チームに所属している従業員の所属開始日";
	public static final String  EMP_TEAM_BELONG_COMPLETE_DATE ="チームに所属している従業員の所属完了日";
	public static final String ENTRIED_TEAM_ASSIGN_START_DATE = "配属済みのチームの配属開始日";
	public static final String ENTRIED_TEAM_ASSIGN_COMPLETE_DATE = "配属済みのチームの配属完了日";
	public static final String UPDATE = "更新";
	public static final String DELETE = "削除";
	//案件詳細情報
	public static final String PROJECT = "案件";
	//従業員更新
	public static final String QUALIFICATION_ID = "S000000001";
	public static final String LANGUAGE_ID = "S000000002";
	public static final String DB_ID = "S000000003";
	public static final String TOOL_ID = "S000000004";
	public static final String BELONG_TEAM = "所属チーム";
	public static final String TEAM_BELONG_START_DATE = "所属開始日";
	public static final String TEAM_BELONG_COMPLETE_DATE = "所属完了日";
	public static final String ROLE = "担当";
	public static final String LEADER = "T000000001";
	public static final String CONTRACT_TYPE = "契約形態";
	public static final String MONTHLY_UNIT_PRICE = "月単価";
	//ログイン
	public static final String LOGIN = "ログイン";
	public static final String LOGIN_ERROR = "ユーザID、パスワード";
	//所属組織マスタメンテナンス画面
	public static final String DEPARTMENT_MASTER = "所属組織";
	public static final String DEPARTMENT_NAME = "所属組織名";
	public static final String DEPARTMENT_ID = "所属組織ID";
	
	//業種マスタメンテナンス画面
	public static final String INDUSTRY_MASTER = "業種";
	public static final String INDUSTRY_NAME = "業種名";
	public static final String INDUSTRY_ID = "業種ID";

	//契約形態マスタメンテナンス画面
	public static final String CONTRACT_TYPE_MASTER = "契約形態";
	public static final String CONTRACT_TYPE_NAME = "契約形態名";
	public static final String CONTRACT_TYPE_ID = "契約形態ID";

	//役職マスタメンテナンス画面
	public static final String POST_MASTER = "役職";
	public static final String POST_NAME = "役職名";
	public static final String POST_ID = "役職ID";

	//担当マスタメンテナンス画面
	public static final String ROLE_MASTER = "担当";
	public static final String ROLE_NAME = "担当名";
	public static final String ROLE_ID = "担当ID";

	//所属会社マスタメンテナンス画面
	public static final String COMPANY_MASTER = "所属会社";
	public static final String COMPANY_NAME = "所属会社名";
	public static final String COMPANY_ID = "所属会社ID";
	public static final String GROUP_ID = "グループ";
	public static final String COMPANYCODE = "会社識別コード";
	
	//スキル種別マスタメンテナンス画面
	public static final String SKILL_TYPE_MASTER = "スキル種別";
	public static final String SKILL_TYPE_NAME = "スキル種別名";
	public static final String SKILL_TYPE_ID = "スキル種別ID";
	public static final String SKILL_TYPE_FLAG = "年数/取得日";
	

	//ユーザーマスタメンテナンス画面
	public static final String USER_MASTER = "ユーザー";
	public static final String USER_NAME = "ユーザー名";
	public static final String USER_ID = "ユーザーID";
	public static final String PASSWORD = "パスワード";
	public static final String USER_COMPANY = "所属会社";
	public static final String USER_POST = "役職";
	public static final String USER_PRIVILEGE = "権限";

	//勤怠情報管理画面
	public static final String ATTENDANCE_MANAGEMENT_TABLE = "勤怠情報";
	public static final String ATTENDANCE_MANAGEMENT_YEAR = "年度";
	public static final String ATTENDANCE_MANAGEMENT_MONTH = "月";
	public static final String ATTENDANCE_MANAGEMENT_IMPORT = "インポート";
	public static final String ATTENDANCE_MANAGEMENT_FILE = "インポートするファイル";
	public static final String ATTENDANCE_MANAGEMENT_START = "開始";
	public static final String ATTENDANCE_MANAGEMENT_FINISH = "終了";
	public static final String ATTENDANCE_MANAGEMENT_REST = "休憩";
	public static final String ATTENDANCE_MANAGEMENT_VACATION_PERIOD ="休暇期間等が";
	public static final String ATTENDANCE_MANAGEMENT_CASE ="の場合";	
	public static final String ATTENDANCE_MANAGEMENT_VACATION_TIME ="休暇時間";
	public static final String ATTENDANCE_MANAGEMENT_LATE_TIME ="遅早時刻";
	public static final String ATTENDANCE_MANAGEMENT_REMARK = "備考";
	public static final String ATTENDANCE_MANAGEMENT_PASSWORD = "パスワード";
	public static final String ATTENDANCE_MANAGEMENT_ATTENDANCE = "年度、月の勤務表";
}