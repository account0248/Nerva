package jp.co.vaile.nerva.masterPost.searchPost;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 
@WebServlet( "/ShowPostMstAction" )
public class ShowPostMstAction extends HttpServlet {

	/**
	 * 役職マスタメンテナンス画面に遷移する。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterPost.jsp");
		disp.forward(request, response);
	}
}


