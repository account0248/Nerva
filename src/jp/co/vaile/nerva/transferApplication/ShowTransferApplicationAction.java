package jp.co.vaile.nerva.transferApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowTransferApplicationAction extends HttpServlet {
	CheckTransferApplicationLogic checkTransferApplicationLogic = new CheckTransferApplicationLogic();
	//遷移先ページ用変数
	String move;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログイン者ID（セッション）取得
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		
		session.removeAttribute("transferApplicationErrorList");

		//移管申請情報が表示していいものかチェック
		try {
			ArrayList<ErrorTransferApplicationDTO> errorTransferApplicationArr = new ArrayList<ErrorTransferApplicationDTO>();

			//エラーメッセージ,//エラーウィンドウに表示する用の移管申請情報
			errorTransferApplicationArr = checkTransferApplicationLogic.checkShowTransferApplication(loginUserId);

			//		移管申請検索のビジネス層にログイン者IDを渡す
			ShowTransferApplicationLogic showTransferApplicationLogic = new ShowTransferApplicationLogic();
			ArrayList<TransferApplicationToStringDTO> transferApplicationArr = new ArrayList<TransferApplicationToStringDTO>();

			transferApplicationArr = showTransferApplicationLogic.showTransferApplicationLogic(loginUserId);

			request.setAttribute("errorTransferApplicationArr", errorTransferApplicationArr);
			request.setAttribute("transferApplicationArr", transferApplicationArr);
			//移管申請情報をリクエストスコープで返す
			move = "approval.jsp";

			request.getRequestDispatcher(move).forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			move = "/jsp/error.jsp";
			request.getRequestDispatcher(move).forward(request, response);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			move = "/jsp/error.jsp";
			request.getRequestDispatcher(move).forward(request, response);
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			move = "/jsp/error.jsp";
			request.getRequestDispatcher(move).forward(request, response);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			move = "/jsp/error.jsp";
			request.getRequestDispatcher(move).forward(request, response);
		}

	}
}
