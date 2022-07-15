package jp.co.vaile.nerva.masterContract.updateContract;

import java.sql.SQLException;

public class UpdateContractMstLogic {

	public void updateContractMstLogic(UpdateContractDTO updateContractDTO) throws ClassNotFoundException, SQLException {
		UpdateContractDAO updateContractDAO = new UpdateContractDAO();
		updateContractDAO.updateContract(updateContractDTO);
		
	}

}
