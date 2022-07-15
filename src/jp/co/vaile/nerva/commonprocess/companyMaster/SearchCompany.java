package jp.co.vaile.nerva.commonprocess.companyMaster;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;


public class SearchCompany {


	/**
	 * DBにアクセスし、ログインユーザーが所属している会社の会社識別コードを取得。
	 * @param  userId
	 * @return companyCode 所属会社IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  String  searchCompanyCode(String userId) throws SQLException, ClassNotFoundException {
		try {		
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。
			sql.append(" SELECT");
			sql.append(" m_belong_company.company_code ");
			sql.append(" AS " );
			sql.append(" company_code " );
			sql.append(" FROM " );
			sql.append( "m_belong_company ");
			sql.append(" INNER JOIN" );
			sql.append("  m_user" );
			sql.append("  ON" );
			sql.append("  m_user.company_id" );
			sql.append("  =" );
			sql.append(" m_belong_company.company_id " );
			sql.append(" WHERE" );
			sql.append("  user_id" );
			sql.append("  = " );
			sql.append("?" );

			//作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			String companyCode=null;
			while(rs.next()) {
				companyCode=rs.getString("company_code");
			}

			return companyCode;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}


	/**
	 * DBにアクセスし、親会社の場合、全会社の所属会社と会社識別コードを取得。
	 * 子会社の場合、自会社の所属会社と会社識別コードを取得。
	 * @param  companyCode
	 * @return companyList 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  List<SearchCommonCompanyDTO>  searchCompanyAllCode(String companyPrivilege,String userId) throws SQLException, ClassNotFoundException {
		try {		
			List<SearchCommonCompanyDTO> companyList=new ArrayList<>();
			PreparedStatement stmt=null;
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。

			if(companyPrivilege.equals(String.valueOf(SUBCOMPANY_VALUE))) {	
				sql.append(" SELECT");
				sql.append(" m_belong_company.company_id"+",");
				sql.append(" m_belong_company.company_name"+",");
				sql.append(" m_belong_company.company_code");
				sql.append(" FROM " );
				sql.append( "m_belong_company ");
				sql.append(" INNER JOIN" );
				sql.append("  m_user" );
				sql.append("  ON" );
				sql.append("  m_user.company_id" );
				sql.append("  =" );
				sql.append(" m_belong_company.company_id " );
				sql.append(" WHERE" );
				sql.append("  user_id" );
				sql.append("  = " );
				sql.append("?" );
				//作成したSQL文で検索を行う。
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, userId);
			}else if(companyPrivilege.equals(String.valueOf(PARENT_COMPANY_VALUE))) {	
				sql.append(" SELECT");
				sql.append(" m_belong_company.company_id"+",");
				sql.append(" m_belong_company.company_name"+",");
				sql.append(" m_belong_company.company_code");
				sql.append(" FROM " );
				sql.append( "m_belong_company ");
				stmt = con.prepareStatement(sql.toString());
			}
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				SearchCommonCompanyDTO SearchCommonCompanyDTO =new SearchCommonCompanyDTO();
				SearchCommonCompanyDTO.setCompanyId(rs.getString("company_Id"));
				SearchCommonCompanyDTO.setCompanyName(rs.getString("company_name"));
				SearchCommonCompanyDTO.setCompanyCode(rs.getString("company_code"));
				companyList.add(SearchCommonCompanyDTO);
			}
			return companyList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * DBにアクセスし、所属会社IDに紐づいた会社識別コードを取得する
	 * @param  companyId
	 * @return companyCode 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  String belongCompanyCode(String companyId) throws SQLException, ClassNotFoundException {
		try {		
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。
			sql.append(" SELECT");
			sql.append(" company_code");
			sql.append(" FROM " );
			sql.append( "m_belong_company ");
			sql.append(" WHERE" );
			sql.append(" company_id" );
			sql.append(" = " );
			sql.append("? " );
			//作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, companyId);
			ResultSet rs = stmt.executeQuery();
			String companyCode=null;
			while(rs.next()) {
				companyCode=rs.getString("company_code");
			}

			return companyCode;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
