package jp.co.vaile.nerva.transferApplication;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class CheckTransferApplicationLogic {

	ErrorMsg returnErrorMsg = new ErrorMsg();

	UpdateTransferApplicationDAO updateTransferApplicationDAO = new UpdateTransferApplicationDAO();
	ShowTransferApplicationDAO showTransferApplicationDAO = new ShowTransferApplicationDAO();
	ShowTransferApplicationToStringDAO showTransferApplicationToStringDAO = new ShowTransferApplicationToStringDAO();

	public ArrayList<ErrorTransferApplicationDTO> checkShowTransferApplication(String loginUserId)
			throws ClassNotFoundException, SQLException, ParseException {

		ArrayList<ErrorTransferApplicationDTO> errorTransferApplicationArr = new ArrayList<ErrorTransferApplicationDTO>();

		ArrayList<TransferApplicationDTO> transferApplicationDTOArr = new ArrayList<TransferApplicationDTO>();
		transferApplicationDTOArr = showTransferApplicationDAO.showTransferApplication(loginUserId);
		ArrayList<TransferApplicationToStringDTO> transferApplicationToStringDTOArr = new ArrayList<TransferApplicationToStringDTO>();
		transferApplicationToStringDTOArr = showTransferApplicationToStringDAO
				.showTransferApplicationToString(transferApplicationDTOArr);

		for (int i = 0; i < transferApplicationDTOArr.size(); i++) {

			String errorMsg = null;
			//移管申請中の従業員が存在しているか確認
			errorMsg = exiteEmpCheckTransferApplicationLogic(transferApplicationDTOArr.get(i).getApplicationNum());

			if (errorMsg == null) {
				//移管申請のチームへの配属開始日とチームの案件への配属開始日のチェック
				errorMsg = checkTransferApplicationStartDate(transferApplicationDTOArr.get(i).getApplicationNum());
			}
			if (errorMsg != null) {
				TransferApplicationToStringDTO transferApplicationErrorDTO = new TransferApplicationToStringDTO();
				ErrorTransferApplicationDTO errorTransferApplicationDTO = new ErrorTransferApplicationDTO();
				transferApplicationErrorDTO
						.setApplicationNum(transferApplicationToStringDTOArr.get(i).getApplicationNum());
				transferApplicationErrorDTO.setApplicant(transferApplicationToStringDTOArr.get(i).getApplicant());
				transferApplicationErrorDTO
						.setApplicationBelongCompany(
								transferApplicationToStringDTOArr.get(i).getApplicationBelongCompany());
				transferApplicationErrorDTO.setTransferEmp(transferApplicationToStringDTOArr.get(i).getTransferEmp());
				transferApplicationErrorDTO
						.setNowApplicationTeam(transferApplicationToStringDTOArr.get(i).getNowApplicationTeam());
				transferApplicationErrorDTO
						.setTransferApplicationTeam(
								transferApplicationToStringDTOArr.get(i).getTransferApplicationTeam());
				transferApplicationErrorDTO
						.setTransferPreferredDate(transferApplicationToStringDTOArr.get(i).getTransferPreferredDate());

				errorTransferApplicationDTO.setErrorMsg(errorMsg);
				errorTransferApplicationDTO.setTransferApplicationErrorDTO(transferApplicationErrorDTO);
				errorTransferApplicationArr.add(errorTransferApplicationDTO);

			}

		}
		return errorTransferApplicationArr;

	}

	//移管申請の際の従業員の存在チェック
	public String exiteEmpCheckTransferApplicationLogic(String applicationNumber)
			throws ClassNotFoundException, SQLException {
		String errorMsg = null;
		int applicationNum = Integer.parseInt(applicationNumber);
		boolean exiteEmpCheckFlg = SearchTransferApplicationDAO.searchTransferApplication(applicationNum).isDeleteFlg();

		if (exiteEmpCheckFlg) {
			String[] empErrorMsg = { EMPLOYEE };
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.NON_EMP_ERROR,empErrorMsg);
			updateTransferApplicationDAO.updateTransferApplication(applicationNum);

		}
		return errorMsg;
	}

	//移管申請中の従業員のチームへの所属完了日と現在の日付とチェック
	public String checkTransferApplicationStartDate(String applicationNumber)
			throws ParseException, ClassNotFoundException, SQLException {

		String errorMsg = null;
		String searchProjectLengthCheckConstants[] = {
				CommonConstants.ENTRY_TEAM_ASSIGN_COMPLETE_DATE };
		SearchTransferApplicationDTO searchTransferApplicationDTO = new SearchTransferApplicationDTO();
		int applicationNum = Integer.parseInt(applicationNumber);
		searchTransferApplicationDTO = SearchTransferApplicationDAO.searchTransferApplication(applicationNum);
		//
		Date empTeamFinishDate = searchTransferApplicationDTO.getTeamFinishDate();
		if (empTeamFinishDate != null) {

			Calendar calendar = Calendar.getInstance();
			// 分、秒、ミリ秒、時の部分をクリアする
			calendar.clear(Calendar.MINUTE);
			calendar.clear(Calendar.SECOND);
			calendar.clear(Calendar.MILLISECOND);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			Date today = calendar.getTime();

			if (empTeamFinishDate.compareTo(today) == -1) {

				errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.TODAY_EMP_TEAM_FINISH_ERROR,
						searchProjectLengthCheckConstants);

				updateTransferApplicationDAO.updateTransferApplication(applicationNum);
			}
		}
		return errorMsg;
	}

	public boolean checkApproveTransferApplication(SearchTransferApplicationDTO  searchTransferApplicationDTO,int applicationNum) throws ClassNotFoundException, ParseException, SQLException{
		boolean transferFlg =true;
		boolean exiteEmpCheckFlg =exiteEmpCheckTransferApplicationLogic(searchTransferApplicationDTO);

		boolean checkStartDateFlg = checkApproveTransferApplicationStartDate(searchTransferApplicationDTO,applicationNum);

		if(exiteEmpCheckFlg==true || checkStartDateFlg==false) {
			transferFlg =false;
		}
		return transferFlg;
	}

	public boolean exiteEmpCheckTransferApplicationLogic(SearchTransferApplicationDTO searchTransferApplicationDTO) {
		boolean exiteEmpCheckFlg = searchTransferApplicationDTO.isDeleteFlg();

		return exiteEmpCheckFlg;
	}

	//移管申請中の従業員のチームへの所属完了日と現在の日付とチェック
	public boolean checkApproveTransferApplicationStartDate(SearchTransferApplicationDTO searchTransferApplicationDTO,
			int applicationNum)
			throws ParseException, ClassNotFoundException, SQLException {
		boolean checkStartDateFlg = true;

		Date empTeamFinishDate = searchTransferApplicationDTO.getTeamFinishDate();
		if (empTeamFinishDate != null) {

			//現在時刻を取得し、従業員の所属完了日と比較するために、日程だけ残し、時間をクリア
			Calendar calendar = Calendar.getInstance();
			calendar.clear(Calendar.MINUTE);
			calendar.clear(Calendar.SECOND);
			calendar.clear(Calendar.MILLISECOND);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			Date today = calendar.getTime();

			if (empTeamFinishDate.compareTo(today) == -1) {

				checkStartDateFlg = false;
			}
		}
		return checkStartDateFlg;
	}
}
