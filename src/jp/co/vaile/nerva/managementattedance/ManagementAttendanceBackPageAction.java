package jp.co.vaile.nerva.managementattedance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;

/**
 * Servlet implementation class ManagementAttendanceBackPageAction
 */
public class ManagementAttendanceBackPageAction extends HttpServlet {
	/**
	 * 戻るボタン押下時の処理 従業員検索画面へ遷移する
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		// セッションからログインユーザーの所属会社を取得する
		String companyId = (String) session.getAttribute("companyId");
		// セッションからログインユーザーの所属会社の親会社フラグを取得する
		String companyPrivilege = (String) session.getAttribute("companyPrivilege");
		List<FetchAnyMasterTableDTO> campanyNameList = new ArrayList<>();

		BelongCompanyInfo belongCompanyInfo = new BelongCompanyInfo();
		try {
			campanyNameList = belongCompanyInfo.fetchBelongCompanyList(companyId, companyPrivilege);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/error.jsp");

			disp.forward(request, response);
		}
		Map<String, String> searchEmployee = new HashMap<String, String>();
		searchEmployee.put("managementattedance", "managementattedance");
		request.setAttribute("searchEmployee", searchEmployee);
		request.setAttribute("campanyNameList", campanyNameList);

		// 従業員検索画面に遷移する。
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/searchEmployee.jsp");

		disp.forward(request, response);
	}

}
