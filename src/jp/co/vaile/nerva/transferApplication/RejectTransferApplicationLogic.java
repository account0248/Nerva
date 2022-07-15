package jp.co.vaile.nerva.transferApplication;

import java.sql.SQLException;
import java.text.ParseException;

public class RejectTransferApplicationLogic {
	//TODO 却下作成
	public
	void rejectTransferApplicationLogic(
			int applicationNum)throws SQLException, ClassNotFoundException, ParseException {

		SearchTransferApplicationDTO searchTransferApplicationDTO = new SearchTransferApplicationDTO();

		//移管申請情報の更新前の排他性$従業員退職チェック
		searchTransferApplicationDTO = SearchTransferApplicationDAO.searchTransferApplication(applicationNum);

		CheckTransferApplicationLogic checkTransferApplicationLogic = new CheckTransferApplicationLogic();

		//移管申請を承認出来るかのチェック

		boolean transferflg= checkTransferApplicationLogic.checkApproveTransferApplication(searchTransferApplicationDTO,applicationNum);
		
		if (transferflg) {
			UpdateTransferApplicationDAO updateTransferApplicationDAO = new UpdateTransferApplicationDAO();



			//移管申請番号を移管申請情報更新機能に渡す
			updateTransferApplicationDAO.updateTransferApplication(applicationNum);
		}


	}

}
