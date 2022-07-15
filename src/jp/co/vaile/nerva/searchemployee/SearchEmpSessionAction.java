package jp.co.vaile.nerva.searchemployee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchEmpSessionAction extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
;		String employeeId = (String)request.getParameter("employeeId");
		request.setAttribute("employeeId", employeeId);
		//	従業員詳細表示用サーブレットにアクションにフォワードする
		RequestDispatcher disp =getServletContext().getRequestDispatcher("/EmpDetailInfoAction");

		disp.forward(request, response);
	}
}