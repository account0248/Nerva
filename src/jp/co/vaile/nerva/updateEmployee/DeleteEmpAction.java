package jp.co.vaile.nerva.updateEmployee;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteEmpAction")
public class DeleteEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 削除ボタンが押下された時、従業員論理削除を行う。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストスコープから削除対象従業員ID、セッションからログインユーザーIDを受け取る。
		String employeeId = (String) request.getParameter("employeeId");
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");

		try {
			LogicalDeleteEmpLogic logicalDeleteEmpLogic = new LogicalDeleteEmpLogic();

			// 従業員IDをもとに従業員情報削除チェックロジックを呼び出す。
			String errorMsg = logicalDeleteEmpLogic.logicalDeleteEmpCheck(employeeId);

			// 対象の従業員が削除済の場合、エラーメッセージをリクエストスコープに保存し従業員検索画面に遷移する。
			if (errorMsg != null) {
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("ShowSearchEmpAction").forward(request, response);
				return;
			}

			// 従業員ID、ログインユーザーIDをもとに従業員業務経験削除ロジックを呼び出す。
			logicalDeleteEmpLogic.logicalDeleteEmpWorkExp(employeeId, loginUserId);

			// 従業員ID、ログインユーザーIDをもとに保有スキル削除ロジックを呼び出す。
			logicalDeleteEmpLogic.logicalDeleteSkillInfo(employeeId, loginUserId);

			// 従業員ID、ログインユーザーIDをもとに従業員削除ロジックを呼び出す。
			logicalDeleteEmpLogic.logicalDeleteEmpInfo(employeeId, loginUserId);

			// セッションにある全ての要素名を取得する
			Enumeration<String> allElementName = session.getAttributeNames();
			// 取得した要素名をループ処理で全て削除する
			while (allElementName.hasMoreElements()) {
				String sessionName = (String) allElementName.nextElement();
				if (!sessionName.equals("loginCheck") && !sessionName.equals("userId")
						&& !sessionName.equals("userName") && !sessionName.equals("companyId")
						&& !sessionName.equals("adminFlg")) {
					session.removeAttribute(sessionName);
				}
			}

		} catch (Exception e) {
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

		// 従業員検索画面表示処理を呼び出して遷移する。
		request.getRequestDispatcher("ShowSearchEmpAction").forward(request, response);
		return;
	}

}
