package jp.co.vaile.nerva.transferApplication;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowTransferApplicationLogic {

	public ArrayList<TransferApplicationToStringDTO> showTransferApplicationLogic(String loginUserId) throws SQLException, ClassNotFoundException {

//		移管申請情報検索のデータアクセス層にログイン者IDを渡す。
		ShowTransferApplicationDAO showTransferApplicationDAO = new ShowTransferApplicationDAO();
		ArrayList<TransferApplicationDTO> transferApplicationDTOArr = new ArrayList<TransferApplicationDTO>();

		transferApplicationDTOArr = showTransferApplicationDAO.showTransferApplication(loginUserId);

		ShowTransferApplicationToStringDAO showTransferApplicationToStringDAO = new ShowTransferApplicationToStringDAO();

		ArrayList<TransferApplicationToStringDTO> transferApplicationToStringDTOArr = new ArrayList<TransferApplicationToStringDTO>();

		transferApplicationToStringDTOArr = showTransferApplicationToStringDAO
				.showTransferApplicationToString(transferApplicationDTOArr);

		return transferApplicationToStringDTOArr;

	}

}
