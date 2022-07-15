package jp.co.vaile.nerva.registEmployee;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class RegistEmpLogic {

	private RegistEmpPageDTO registEmpPageDTO;
	private RegistSkillInfoPageDTO registSkillInfoPageDTO;
	private String loginUserId;

	/**
	 * コンストラクタ
	 * @param registEmpPageDTO 従業員登録画面で記載された従業員情報。
	* @param registSkillInfoPageDTO 従業員登録画面で記載されたスキル情報。
	 * @param loginUserId ログインユーザーのID
	 */
	public RegistEmpLogic(RegistEmpPageDTO registEmpPageDTO, RegistSkillInfoPageDTO registSkillInfoPageDTO,
			String loginUserId) {
		super();
		this.registEmpPageDTO = registEmpPageDTO;
		this.registSkillInfoPageDTO = registSkillInfoPageDTO;
		this.loginUserId = loginUserId;
	}

	/**
	 * 従業員登録処理を呼び出す。
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void registEmp() throws ParseException, ClassNotFoundException, SQLException {
		//1.P1の従業員情報を従業員情報登録DTOに移す。
		RegistEmpInfoDTO registEmpInfoDTO = new RegistEmpInfoDTO();
		registEmpInfoDTO.setEmployeeId(registEmpPageDTO.getEmployeeId());
		registEmpInfoDTO.setEmployeeName(registEmpPageDTO.getEmployeeName());
		registEmpInfoDTO.setSex(registEmpPageDTO.getSex());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = dateFormat.parse(registEmpPageDTO.getBirthDate());
		registEmpInfoDTO.setBirthDate(birthDate);
		registEmpInfoDTO.setOffice(registEmpPageDTO.getOffice());
		registEmpInfoDTO.setDepartmentId(registEmpPageDTO.getDepartmentId());
		registEmpInfoDTO.setCompanyId(registEmpPageDTO.getCompanyId());
		registEmpInfoDTO.setPostId(registEmpPageDTO.getPostId());
		registEmpInfoDTO.setPostalCode(registEmpPageDTO.getPostalCode());
		registEmpInfoDTO.setAddress(registEmpPageDTO.getAddress());
		registEmpInfoDTO.setPhoneNumber(registEmpPageDTO.getPhoneNumber());
		registEmpInfoDTO.setMail(registEmpPageDTO.getMail());
		//2.従業員情報登録DTOの登録者IDをP2にする。
		registEmpInfoDTO.setRegistUser(loginUserId);
		//3.作成した従業員情報登録DTOを引数に従業員情報登録処理を呼び出す。
		RegistEmpDAO registEmpDAO = new RegistEmpDAO();
		registEmpDAO.insertEmpInfo(registEmpInfoDTO);
		if (registSkillInfoPageDTO.getSkillType() != null) {
			registSkillInfo();
		}
	}

	/**
	 * スキル情報登録処理を呼び出す。
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void registSkillInfo() throws ParseException, ClassNotFoundException, SQLException {
		LengthCheck lengthCheck = new LengthCheck();
		RegistSkillInfoDTO registSkillInfoDTO = new RegistSkillInfoDTO();
		List<RegistSkillInfoDTO> registSkillInfoDTOList = new ArrayList<RegistSkillInfoDTO>();
		//6.P1の保有スキル情報DTOのスキル種別IDリストの長さの回数1～5を繰り返す。
		for (int i = 0; i < registSkillInfoPageDTO.getSkillType().size(); i++) {
			//1.P1の保有スキル情報DTOの各リストの一つ目をスキル情報登録DTOに移す。
			registSkillInfoDTO.setSkillTypeId(registSkillInfoPageDTO.getSkillType().get(i));
			registSkillInfoDTO.setSkillDetail(registSkillInfoPageDTO.getSkillDetail().get(i));
			registSkillInfoDTO.setExperienceYears(registSkillInfoPageDTO.getExperienceYears().get(i));
			//取得年月の有無をチェック
			//空文字の場合はnullを格納。値が入っている場合はそのまま格納。
			if (lengthCheck.isNomNullOrNomEmpty(registSkillInfoPageDTO.getAcquisitionYearMonth().get(i)) == false) {
				registSkillInfoDTO.setAcquisitionYearMonth(null);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
				Date acquisitionYearMonth = dateFormat.parse(registSkillInfoPageDTO.getAcquisitionYearMonth().get(i));
				registSkillInfoDTO.setAcquisitionYearMonth(acquisitionYearMonth);
			}
			//2.スキル情報登録DTOの従業員IDを、P2の従業員情報DTOの従業員IDにする。
			registSkillInfoDTO.setEmployeeId(registEmpPageDTO.getEmployeeId());
			//3.スキル情報登録DTOの登録者IDをP3にする。
			registSkillInfoDTO.setRegistUser(loginUserId);
			//5.作成したスキル情報登録DTOをスキル情報登録DTOリストに追加する。
			registSkillInfoDTOList.add(registSkillInfoDTO);
			//7.作成したスキル情報登録DTOリストを引数にスキル登録処理を呼び出す。
			RegistSkillInfoDAO registSkillInfoDAO = new RegistSkillInfoDAO();
			registSkillInfoDAO.insertSkillInfo(registSkillInfoDTOList);
		}

	}
}
