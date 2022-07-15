package jp.co.vaile.nerva.masterUser.searchUser;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.PostInfo;

/**
 * Servlet implementation class ShowUserstAction
 */

@WebServlet("/ShowUserMstAction")
public class ShowUserMstAction extends HttpServlet {

	/**
	 * ユーザーマスタメンテナンス画面へ遷移する。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// セッションからログインユーザーの所属会社を取得する。
		String companyId = (String) session.getAttribute("companyId");

		// セッションからログインユーザーの所属会社の親会社フラグを取得する。
		String companyPrivilege = (String) session.getAttribute("companyPrivilege");

		List<FetchAnyMasterTableDTO> companyNameList = new ArrayList<>();
		List<FetchAnyMasterTableDTO> postList = new ArrayList<>();

		BelongCompanyInfo belongCompanyInfo = new BelongCompanyInfo();
		ExistCheck postInfo = new PostInfo();

		try {

			// 所属会社と親会社フラグを引数に、会社権限に応じた所属会社DTOリストを取得。
			companyNameList = belongCompanyInfo.fetchBelongCompanyList(companyId, companyPrivilege);

			// 役職DTOリストを取得。
			postList = postInfo.fetchMasterTableList();

		} catch (ClassNotFoundException | SQLException e) {

			// システムエラー画面に遷移。
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/error.jsp");

			disp.forward(request, response);
			e.printStackTrace();
		}

		request.setCharacterEncoding("UTF-8");

		// 所属会社DTOリストをリクエストパラメータにセット。
		request.setAttribute("companyNameList", companyNameList);

		// 役職DTOリストをリクエストパラメータにセット。
		request.setAttribute("postList", postList);

		// 権限セレクタで用いる文字列を取得とその値を取得しリクエストパラメータにセット。
		request.setAttribute("admin", ADMIN);
		request.setAttribute("general", GENERAL);
		request.setAttribute("adminValue", ADMIN_VALUE);
		request.setAttribute("generalValue", GENERAL_VALUE);

		// ユーザーマスタメンテナンス画面に遷移。
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterUser.jsp");
		disp.forward(request, response);
	}
}
