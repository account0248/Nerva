package jp.co.vaile.nerva.masterContract.searchContract;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowContractMstAction
 */

/**
 * 契約形態マスタメンテナンス画面に遷移する。
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */


@WebServlet( "/ShowContractMstAction" )
public class ShowContractMstAction extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	//契約形態マスタメンテナンス画面に遷移する。
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterContract.jsp");
		disp.forward(request, response);
	}
}


