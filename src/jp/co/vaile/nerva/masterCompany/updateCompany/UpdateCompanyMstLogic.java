package jp.co.vaile.nerva.masterCompany.updateCompany;

import java.sql.SQLException;

public class UpdateCompanyMstLogic {

	/* 所属会社更新処理を呼び出す。 *
	 * @param updateCompanyDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateCompanyMstLogic(UpdateCompanyDTO updateCompanyDTO) throws ClassNotFoundException, SQLException {
		UpdateCompanyDAO updateCompanyDAO = new UpdateCompanyDAO();
		updateCompanyDAO.updateCompany(updateCompanyDTO);
	}
}
