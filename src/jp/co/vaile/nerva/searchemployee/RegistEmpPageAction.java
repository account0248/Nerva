package jp.co.vaile.nerva.searchemployee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCommonCompanyDTO;
import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCompany;

public class RegistEmpPageAction extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		//セッションからログインユーザーの所属会社グループ権限とユーザーIDを取得する。
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");
		String userId = (String)session.getAttribute("userId");

		//所属会社のリストを取得する。
		List<SearchCommonCompanyDTO> companyList  = new ArrayList<>();
		SearchCompany searchCompany=new SearchCompany();
		try {
			//ユーザーIDと所属会社グループ権限を引数に、会社識別コードを取得。
			companyList  =searchCompany.searchCompanyAllCode(companyPrivilege,userId);

			//所属組織,役職,スキル情報のリストを取得する。
			RegistEmpPageLogic registPageAction =new RegistEmpPageLogic();

			List<FetchAnyMasterTableDTO> belongDepartmentList =new ArrayList<>();
			List<FetchAnyMasterTableDTO> postList =new ArrayList<>();
			List<FetchAnyMasterTableDTO> skillTypeList =new ArrayList<>();

			belongDepartmentList = registPageAction.BelongDepartmentInfoDTOList();
			postList = registPageAction.PostInfoDTOList();
			skillTypeList = registPageAction.SkillTypeInfoDTOList();

			//従業員登録画面遷移処理を行う。

			request.setAttribute("companyList", companyList );
			request.setAttribute("departmentList", belongDepartmentList );
			request.setAttribute("postList",postList);
			request.setAttribute("skillTypeList", skillTypeList);

		} catch (ClassNotFoundException | SQLException e) {
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/error.jsp");

			disp.forward(request, response);
			e.printStackTrace();
		}
		RequestDispatcher disp =getServletContext().getRequestDispatcher("/jsp/registEmployee.jsp");
		disp.forward(request, response);
	}
}

