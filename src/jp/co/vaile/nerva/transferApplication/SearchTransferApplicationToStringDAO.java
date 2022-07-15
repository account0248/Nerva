package jp.co.vaile.nerva.transferApplication;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class SearchTransferApplicationToStringDAO {

	public SearchTransferApplicationToStringDTO searchTransferApplicationToString(
			SearchTransferApplicationDTO searchTransferApplicationDTO) throws SQLException  {

		SearchTransferApplicationToStringDTO searchTransferApplicationToStringDTO = new SearchTransferApplicationToStringDTO();

		try {
			searchTransferApplicationToStringDTO = setSearchTransferApplicationToString(searchTransferApplicationDTO);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}

		
		return searchTransferApplicationToStringDTO;
	}

	public static SearchTransferApplicationToStringDTO setSearchTransferApplicationToString(
			SearchTransferApplicationDTO searchTransferApplicationDTO) throws SQLException {

		SearchTransferApplicationToStringDTO searchTransferApplicationToStringDTO = new SearchTransferApplicationToStringDTO();

		searchTransferApplicationToStringDTO.setTransferEmpId(searchTransferApplicationDTO.getTransferEmpId());

		searchTransferApplicationToStringDTO.setRoleId(searchTransferApplicationDTO.getRoleId());

		searchTransferApplicationToStringDTO.setContractTypeId(searchTransferApplicationDTO.getContractTypeId());

		searchTransferApplicationToStringDTO.setMonthUnitPrice(searchTransferApplicationDTO.getMonthUnitPrice());

		searchTransferApplicationToStringDTO
				.setTransferApplicationTeam(searchTransferApplicationDTO.getTransferApplicationTeam());

		Date transferPreferredDate = searchTransferApplicationDTO.getTransferPreferredDate();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//Date型をString型に変換
		String transferPreferredDateToString = dateFormat.format(transferPreferredDate);

		searchTransferApplicationToStringDTO.setTransferPreferredDate(transferPreferredDateToString);


		Date teamFinishDate = searchTransferApplicationDTO.getTeamFinishDate();

		if(teamFinishDate==null) {
			searchTransferApplicationToStringDTO.setTeamFinishDate(null);
		}else {

		//Date型をString型に変換
		String teamFinishDateToString = dateFormat.format(teamFinishDate);

		searchTransferApplicationToStringDTO.setTeamFinishDate(teamFinishDateToString);
		}

		searchTransferApplicationDTO.setDeleteFlg(searchTransferApplicationDTO.isDeleteFlg());

		searchTransferApplicationDTO.setProjectId(searchTransferApplicationDTO.getProjectId());

		return searchTransferApplicationToStringDTO;

	}

}
