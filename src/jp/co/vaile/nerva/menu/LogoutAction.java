package jp.co.vaile.nerva.menu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author y.sano
 *
 *ログアウトのハイパーリンクを押下後、セッション情報を破棄しログアウトを行う。
 */

public class LogoutAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッション情報を取得し、セッションの破棄を行う。
		HttpSession session = request.getSession();

		//セッション情報を破棄
		session.invalidate();

		// ログイン画面に遷移する。
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}
}