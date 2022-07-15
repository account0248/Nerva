package jp.co.vaile.nerva.detailProject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.searchProject.SearchProjectPageDTO;



public class DetailPjtBackPageAction extends HttpServlet {

	/**
	 *案件詳細情報表示画面で戻るボタンを押下後、案件検索ページに遷移する
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String result = "/jsp/ShowProjectAction";

		//セッションスコープの案件検索入力情報DTOを取得する
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO) session.getAttribute("projectSearchPageDTO");

		//案件検索入力情報DTOをリクエストスコープに格納する
		request.setAttribute("projectSearchPageDTO", projectSearchPageDTO);

		//案件検索画面表示アクションにフォワードする
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		disp.forward(request, response);
	}
}
