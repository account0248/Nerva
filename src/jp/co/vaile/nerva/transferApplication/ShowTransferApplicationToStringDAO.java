package jp.co.vaile.nerva.transferApplication;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowTransferApplicationToStringDAO {

	public ArrayList<TransferApplicationToStringDTO> showTransferApplicationToString(
			ArrayList<TransferApplicationDTO> transferApplicationDTOArr) {

		ArrayList<TransferApplicationToStringDTO> transferApplicationToStringDTOArr = new ArrayList<TransferApplicationToStringDTO>();

		for (int i = 0; i < transferApplicationDTOArr.size(); i++) {

			TransferApplicationToStringDTO transferApplicationToStringDTO = new TransferApplicationToStringDTO();

			transferApplicationToStringDTO = setShowTransferApplicationToString(transferApplicationDTOArr.get(i));

			transferApplicationToStringDTOArr.add(transferApplicationToStringDTO);

		}

		return transferApplicationToStringDTOArr;
	}

	public static TransferApplicationToStringDTO setShowTransferApplicationToString(
			TransferApplicationDTO transferApplicationDTO) {

		TransferApplicationToStringDTO transferApplicationToStringDTO = new TransferApplicationToStringDTO();

		transferApplicationToStringDTO.setTransferEmp(transferApplicationDTO.getTransferEmp());

		transferApplicationToStringDTO.setApplicant(transferApplicationDTO.getApplicant());

		transferApplicationToStringDTO
				.setApplicationBelongCompany(transferApplicationDTO.getApplicationBelongCompany());

		transferApplicationToStringDTO.setNowApplicationTeam(transferApplicationDTO.getNowApplicationTeam());

		transferApplicationToStringDTO
				.setApplicationNum(transferApplicationDTO.getApplicationNum());


		transferApplicationToStringDTO.setTransferApplicationTeam(transferApplicationDTO.getTransferApplicationTeam());

		Date transferPreferredDate = transferApplicationDTO.getTransferPreferredDate();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//Date型をString型に変換
		String transferPreferredDateToString = dateFormat.format(transferPreferredDate);

		transferApplicationToStringDTO.setTransferPreferredDate(transferPreferredDateToString);

		return transferApplicationToStringDTO;

	}

}
