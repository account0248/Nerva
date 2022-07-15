package jp.co.vaile.nerva.transferApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TransferApplicationAction extends HttpServlet {

	String result;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
			try {
		//ログイン者ID（セッション）取得
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");

			int applicationNum = Integer.parseInt(request.getParameter("transferEmpName"));

			//申請ボタンが承認か却下かチェックする。
			String applicationButton = request.getParameter("applicationButton");

			if (applicationButton.equals("approve")) {

				ApproveTransferApplicationLogic approveTransferApplicationLogic = new ApproveTransferApplicationLogic();

				approveTransferApplicationLogic.approveTransferApplicationLogic(loginUserId,
						applicationNum);

			} else if (applicationButton.equals("reject")) {

				RejectTransferApplicationLogic rejectTransferApplicationLogic = new RejectTransferApplicationLogic();
				rejectTransferApplicationLogic.rejectTransferApplicationLogic(
						applicationNum);

			}

			result = "/jsp/ShowTransferApplicationAction";

			//移管申請画面表示アクションにフォワードする
			RequestDispatcher disp = getServletContext().getRequestDispatcher(result);

			disp.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			result = "/jsp/error.jsp";
			request.getRequestDispatcher(result).forward(request, response);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			result = "/jsp/error.jsp";
			request.getRequestDispatcher(result).forward(request, response);
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			result = "/jsp/error.jsp";
			request.getRequestDispatcher(result).forward(request, response);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			result = "/jsp/error.jsp";
			request.getRequestDispatcher(result).forward(request, response);
		}

	}

}
