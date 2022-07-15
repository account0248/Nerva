package jp.co.vaile.nerva.transferApplication;

import java.sql.SQLException;
import java.text.ParseException;

import jp.co.vaile.nerva.updateEmployee.RegistEmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpWorkExpDAO;

public class ApproveTransferApplicationLogic {

	//TODO 承認作成
	public void approveTransferApplicationLogic(String loginUserId, int applicationNum)
			throws SQLException, ClassNotFoundException, ParseException {

		SearchTransferApplicationDTO searchTransferApplicationDTO = new SearchTransferApplicationDTO();

//		//移管申請情報の更新前の排他性$従業員退職チェック
		searchTransferApplicationDTO = SearchTransferApplicationDAO.searchTransferApplication(applicationNum);

		CheckTransferApplicationLogic checkTransferApplicationLogic = new CheckTransferApplicationLogic();

		//移管申請を承認出来るかのチェック

			boolean transferflg= checkTransferApplicationLogic.checkApproveTransferApplication(searchTransferApplicationDTO,applicationNum);

		//エラーリストがない場合移管申請を行う
		if (transferflg) {

				SearchTransferApplicationToStringDAO searchTransferApplicationToStringDAO = new SearchTransferApplicationToStringDAO();

				SearchTransferApplicationToStringDTO searchTransferApplicationToStringDTO = new SearchTransferApplicationToStringDTO();

				//移管申請検索結果のDTOのDate型をString型に変換し、別のDTOに格納する
				searchTransferApplicationToStringDTO = searchTransferApplicationToStringDAO
						.searchTransferApplicationToString(searchTransferApplicationDTO);

				UpdateTransferApplicationDAO updateTransferApplicationDAO = new UpdateTransferApplicationDAO();

				//移管申請番号を移管申請情報更新機能に渡す
				updateTransferApplicationDAO.updateTransferApplication(applicationNum);

				//従業員業務経験情報更新機能用のDTO作成
				String monthlyUnitPrice = "";
				if(searchTransferApplicationToStringDTO.getMonthUnitPrice()==0) {

					monthlyUnitPrice="";
				}else {
				monthlyUnitPrice = ""+searchTransferApplicationToStringDTO.getMonthUnitPrice();
				}
				RegistEmpWorkExpDTO latestEmpWorkExpDTO = new RegistEmpWorkExpDTO(searchTransferApplicationToStringDTO.getTransferApplicationTeam(),
						searchTransferApplicationToStringDTO.getContractTypeId(),searchTransferApplicationToStringDTO.getRoleId(),monthlyUnitPrice,
						searchTransferApplicationToStringDTO.getTransferPreferredDate(), searchTransferApplicationToStringDTO.getTeamFinishDate()

						);
				String transferEmpId = searchTransferApplicationToStringDTO.getTransferEmpId();
				latestEmpWorkExpDTO.setEmployeeId(transferEmpId);
				latestEmpWorkExpDTO.setLoginUserId(loginUserId);

				UpdateEmpWorkExpDAO updateEmpWorkExpDAO = new UpdateEmpWorkExpDAO();

				//String変換後のDTOを従業員業務経験情報更新機能に渡す
				updateEmpWorkExpDAO.insertEmpWorkExp(latestEmpWorkExpDTO);
		}
	}
}
