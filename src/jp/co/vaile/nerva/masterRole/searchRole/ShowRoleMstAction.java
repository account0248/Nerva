package jp.co.vaile.nerva.masterRole.searchRole;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowDepartmentMstAction
 */

/**
 * 担当マスタメンテナンス画面に遷移する。
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */


@WebServlet( "/ShowRoleMstAction" )
public class ShowRoleMstAction extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//担当マスタメンテナンス画面に遷移する。
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterRole.jsp");
		disp.forward(request, response);
	}
}


