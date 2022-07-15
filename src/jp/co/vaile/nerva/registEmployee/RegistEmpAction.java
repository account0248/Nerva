/**
 *
 */
package jp.co.vaile.nerva.registEmployee;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistEmpAction extends HttpServlet {

	/**
	 *登録ボタンを押下した際の処理。
	 *従業員登録処理・スキル情報登録処理の呼び出しを行い、従業員詳細画面に遷移する。
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String result = "/jsp/EmpDetailInfoAction";
		HttpSession session = request.getSession();
		//セッションから入力情報を取得
		RegistEmpPageDTO registEmpPageDTO = (RegistEmpPageDTO) session.getAttribute("registEmpPageDTO");
		
		RegistSkillInfoPageDTO registSkillInfoPageDTO=(RegistSkillInfoPageDTO)session.getAttribute("registSkillInfoPageDTO");
		session.removeAttribute("registEmpPageDTO");
		session.removeAttribute("registSkillInfoPageDTO");
		String userId = (String) session.getAttribute("userId");

		try {
			RegistEmpLogic registEmpLogic = new RegistEmpLogic(registEmpPageDTO,registSkillInfoPageDTO, userId);
			//2.セッションのユーザーIDを引数に従業員情報登録処理を呼び出す。
			registEmpLogic.registEmp();

		} catch (ParseException | ClassNotFoundException | SQLException e) {
			result = "/jsp/error.jsp";
			e.printStackTrace();
			RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
			// forwardメソッドでJSPに遷移します。
			disp.forward(request, response);
		}

		//4.従業員詳細画面遷移処理を呼び出し、登録した従業員の従業員詳細表示画面へ遷移させる。
		request.setAttribute("employeeId",registEmpPageDTO.getEmployeeId());
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		// forwardメソッドでJSPに遷移します。
		disp.forward(request, response);

	}
}
