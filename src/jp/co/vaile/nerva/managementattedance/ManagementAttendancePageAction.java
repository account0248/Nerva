package jp.co.vaile.nerva.managementattedance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManagementAttendancePageAction
 */
public class ManagementAttendancePageAction extends HttpServlet {
	/**
	 * 勤怠情報管理画面へ遷移する
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		
		request.setAttribute("employeeId",employeeId);
		request.setAttribute("employeeName",employeeName);
		request.getRequestDispatcher("/jsp/managementAttendance.jsp").forward(request, response);
		
		
	}

}
