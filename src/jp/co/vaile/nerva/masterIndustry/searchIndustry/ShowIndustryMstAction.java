package jp.co.vaile.nerva.masterIndustry.searchIndustry;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowIndustryMstAction
 */

/**
 * 業種マスタメンテナンス画面に遷移する。
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */

@WebServlet( "/ShowIndustryMstAction" )
public class ShowIndustryMstAction extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	//業種マスタメンテナンス画面に遷移する
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterIndustry.jsp");
		disp.forward(request, response);
	}
}


