package jp.co.vaile.nerva.registproject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDAO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.IndustryInfo;

public class ShowRegistProjectLogic {
	
	/**
	 * 業種のDTOリストを取得し、返す。
	 *
	 * @return 業種のDTOリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FetchAnyMasterTableDTO> returnIndustryDTOList() throws ClassNotFoundException, SQLException{

		// 業種のDTOリストを取得する。
		List<FetchAnyMasterTableDTO> industryDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		ExistCheck industryInfo = new IndustryInfo();
		industryDTOList = industryInfo.fetchMasterTableList();

		// 業種のDTOリストを返す。
		return industryDTOList;
	}

	/**
	 * 所属会社名を取得し返す。
	 *
	 * @param companyId
	 * @return 所属会社名
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String returnBelongCompanyName(String companyId,String companyPrivilege) throws ClassNotFoundException, SQLException {

		// 所属会社のDTOリストを取得する。
		BelongCompanyInfo belongCompanyInfo = new BelongCompanyInfo();
		List<FetchAnyMasterTableDTO> belongCompanyDTOList = new ArrayList<>();
		String companyName = null;
		belongCompanyDTOList = belongCompanyInfo.fetchBelongCompanyList(companyId,companyPrivilege);

		// ログインユーザーの所属会社名に絞り込む。
		for (int i = 0; i < belongCompanyDTOList.size(); i++) {
			if (companyId.equals(belongCompanyDTOList.get(i).getMasterDataId())) {
				companyName = belongCompanyDTOList.get(i).getMasterData();
			}
		}

		// ログインユーザーの所属会社名を返す。
		return companyName;
	}

	/**
	 * 発注元のDTOリストを取得し、返す。
	 *
	 * @return 発注元のDTOリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<OrderSourceDTO> returnOrderSourceList() throws ClassNotFoundException, SQLException{

		// 発注元のDTOリストを取得する。
		OrderSourceDAO orderSourceDAO = new OrderSourceDAO();
		List<OrderSourceDTO> orderSourceDTOList = new ArrayList<>();
		orderSourceDTOList = orderSourceDAO.selectAllOrderSource();

		// 発注元のDTOリストを返す。
		 return orderSourceDTOList;
	}
}
