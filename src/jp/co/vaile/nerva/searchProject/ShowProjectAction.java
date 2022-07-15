package jp.co.vaile.nerva.searchProject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDAO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.IndustryInfo;

public class ShowProjectAction extends HttpServlet {

	//遷移先ページ用変数
	String move;

	/**
	 * 案件検索画面に遷移の為のデータを取得し、かつ遷移するメソッド。
	 * @param request 
	 * @param response 
	 * @throws UnsupportedEncodingException
	 **/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");

		try {
			//案件検索の入力情報がセッションに格納されている場合、破棄する
			HttpSession session = request.getSession();
			session.removeAttribute("projectSearchPageDTO");
			session.removeAttribute("projectId");

			//案件詳細ページから遷移してきた際、案件検索の入力情報を受け取る
			SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO) request
					.getAttribute("projectSearchPageDTO");

			//ログイン者ID（セッション）取得
			String companyId = (String) session.getAttribute("companyId");
			//セッションからログインユーザーの所属会社グループ権限を取得する
			String companyPrivilege = (String)session.getAttribute("companyPrivilege");

			//共通処理
			//共通処理データ格納用のリスト作成
			List<OrderSourceDTO> clientData = new ArrayList<OrderSourceDTO>();

			List<FetchAnyMasterTableDTO> contratorData = new ArrayList<FetchAnyMasterTableDTO>();

			List<FetchAnyMasterTableDTO> inductryData = new ArrayList<FetchAnyMasterTableDTO>();

			//共通処理データ格納の実装
			OrderSourceDAO orderSourceDAO = new OrderSourceDAO();
			try {
				// 発注元DTOリスト取得
				clientData = orderSourceDAO.selectAllOrderSource();

				// 受注元名取得
				BelongCompanyInfo belongCompanyInfo = new BelongCompanyInfo();

				contratorData = belongCompanyInfo.fetchBelongCompanyList(companyId,companyPrivilege);

				// 業種DTOリスト取得
				ExistCheck industryInfo = new IndustryInfo();

				inductryData = industryInfo.fetchMasterTableList();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				move = "/jsp/error.jsp";
				request.getRequestDispatcher(move).forward(request, response);
			}

			//案件検索画面の情報をリクエストスコープで返す
			request.setAttribute("projectSearchPageDTO", projectSearchPageDTO);

			request.setAttribute("constractorData", contratorData);

			request.setAttribute("clientData", clientData);

			request.setAttribute("inductryData", inductryData);

			//遷移先指定
			move = "/jsp/searchProject.jsp";

			request.getRequestDispatcher(move).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			move = "/Nerva/jsp/error.jsp";
		}
	}
}