package jp.co.vaile.nerva.masterCompany.insertCompany;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.vaile.nerva.commonprocess.MasterContents;

public class InsertCompanyMstLogic {
	
	/**
	 * 所属会社登録処理を呼び出す。
	 * @param insertCompanyDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void insertCompanyMstLogic(InsertCompanyDTO insertCompanyDTO) throws ClassNotFoundException, SQLException, ParseException {
		//登録処理のインスタンスを作成する。
		InsertCompanyDAO insertCompanyDAO = new InsertCompanyDAO();
		//真偽値を格納する変数を作成する。
		boolean companyPrivilege = false;
		String companyValue =  String.valueOf(MasterContents.PARENT_COMPANY_VALUE);
		//条件分岐を行い、P1の所属会社グループ権限が1の場合、1で作成した変数にtrueを代入する。
		if (insertCompanyDTO.getCompanyGroup().equals(companyValue)) {
			companyPrivilege= true;
		}
		//所属会社登録処理の呼び出し
		insertCompanyDAO.insertCompany(insertCompanyDTO,companyPrivilege);
	}
	
}
