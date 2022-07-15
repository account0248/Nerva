package jp.co.vaile.nerva.updateEmployee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmpBackPageAction")
public class UpdateEmpBackPageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *戻るボタンが押下された時、従業員詳細画面に遷移する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String employeeId = (String) request.getParameter("employeeId");
		//従業員IDをリクエストスコープに格納する。
		request.setAttribute("employeeId", employeeId);
		//従業員詳細情報遷移処理を呼び出す。
		request.getRequestDispatcher("EmpDetailInfoAction").forward(request, response);
	}

}
