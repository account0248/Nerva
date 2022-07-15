package jp.co.vaile.nerva.registproject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;

public class ShowRegistProjectAction extends HttpServlet {
	
	/**
	 * 案件登録画面を表示する。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String result = "registProject.jsp";

		// セッションスコープから情報を受け取る。
		HttpSession session;
		session = request.getSession();
		String companyId = (String) session.getAttribute("companyId");
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");

		List<FetchAnyMasterTableDTO> industryDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		String companyName = null;
		List<OrderSourceDTO> orderSourceDTOList = new ArrayList<>();

		try {
			ShowRegistProjectLogic showRegistProjectLogic = new ShowRegistProjectLogic();

			// DBから業種のDTOリストを取得
			industryDTOList = showRegistProjectLogic.returnIndustryDTOList();
			// DBから所属会社名のDTOリストを取得
			companyName = showRegistProjectLogic.returnBelongCompanyName(companyId,companyPrivilege);
			// DBから発注元のDTOリストを取得
			orderSourceDTOList = showRegistProjectLogic.returnOrderSourceList();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "error.jsp";
			request.getRequestDispatcher(result).forward(request, response);
		}

		// リクエストスコープに情報を格納し、案件登録画面に遷移する。
		request.setAttribute("companyName", companyName);
		request.setAttribute("orderSourceDTOList", orderSourceDTOList);
		request.setAttribute("industryDTOList", industryDTOList);

		request.getRequestDispatcher(result).forward(request, response);
	}
}
