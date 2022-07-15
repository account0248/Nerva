package jp.co.vaile.nerva.masterDepartment.searchDepartment;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( "/ShowDepartmentMstAction" )
public class ShowDepartmentMstAction extends HttpServlet {

	/**
	 * 所属組織マスタメンテナンス画面に遷移する。
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterBelongDepartment.jsp");
		disp.forward(request, response);
	}
}


