package jp.co.vaile.nerva.commonprocess.existchecksub;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;

public class BelongCompanyInfo extends ExistCheck {
	
	public BelongCompanyInfo() {
		super("m_belong_company");
	}

	/**
	 * ログインユーザーの所属会社によって取り出す所属会社の情報を絞り込む。
	 * 所属会社DTOリストを返す。
	 *
	 * @param loginUserBelongCompanyID
	 * @return ログインユーザーに可視性のある所属会社DTOリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FetchAnyMasterTableDTO> fetchBelongCompanyList(String loginUserBelongCompanyID,String privilege) throws SQLException, ClassNotFoundException{
		List<FetchAnyMasterTableDTO> masterTableList = new ArrayList<>();
		masterTableList = fetchMasterTableList();
		// 所属会社DTOリストを取得し、引数に応じて場合分けする。
		for (int i = 0; i < masterTableList.size(); i++) {
			if (loginUserBelongCompanyID.equals(masterTableList.get(i).getMasterDataId())) {
				if (privilege.equals(String.valueOf(SUBCOMPANY_VALUE))) {
					FetchAnyMasterTableDTO masterTableData = masterTableList.get(i);
					masterTableList.clear();
					masterTableList.add(masterTableData);
					return masterTableList;
				}
			}
		}
		// P1が親会社は、全件のDTOリストを戻り値とする。
		// P1が子会社は、その会社のDTOリストのみを戻り値とする。
		return masterTableList;
	}
}
