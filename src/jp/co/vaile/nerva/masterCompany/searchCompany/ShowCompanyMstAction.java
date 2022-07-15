package jp.co.vaile.nerva.masterCompany.searchCompany;
import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCompany;

/**
 * Servlet implementation class ShowDepartmentMstAction
 */

/**
 * 所属会社マスタメンテナンス画面に遷移する。
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */


@WebServlet( "/ShowCompanyMstAction" )
public class ShowCompanyMstAction extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	// 所属会社マスタメンテナンス画面に遷移する。
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		SearchCompany searchCompany =new SearchCompany();
		SearchCompanyDAO searchCompanyDAO =new SearchCompanyDAO();
		ShowCompanyCodeCheck showCompanyCodeCheck=new ShowCompanyCodeCheck();
		List<Character> companyCodeList = new ArrayList<Character>();
		try {
			// 1 所属会社マスタメンテナンス画面から会社識別コードを取得し、Listに格納する
			companyCodeList=searchCompanyDAO.searchCompanyCode();
			//2 定数クラスにある会社識別コードのListと1で取得した登録済みの会社識別コードが重複するものを排除し、Listに格納する。
			companyCodeList=showCompanyCodeCheck.getCompanyCode(companyCodeList, COMPANY_CODE);

			/* 3  セッションからログイン者の所属会社グループ権限を取得し、条件分岐を行う。
			   4  定数クラスにある子会社、親会社を取得。
			   5  定数クラスから権限セレクタに用いる文字列とその値（1/0）を取得。
			 	3-1所属会社グループ権限が1であれば、親会社であれば、定数クラスにある会社識別コードの配列をリクエストスコープに格納する。
			 　 4-1所属会社グループ権限が１であれば、4で取得した子会社、親会社の両方をリクエストスコープに格納する。
			  	5-1所属会社グループ権限が1であれば、値1を格納している文字列をリクエストスコープにセットする。*/
			String companyPrivilege =(String)session.getAttribute("companyPrivilege");
			String parentCompanyValue=String.valueOf(PARENT_COMPANY_VALUE);
			String subCompanyValue=String.valueOf(SUBCOMPANY_VALUE);
			if(companyPrivilege.equals(parentCompanyValue)) {
				List<Character> companyAllCodeList= new ArrayList<Character>(Arrays.asList(COMPANY_CODE));
				request.setAttribute("companyCodeList", companyCodeList);
				request.setAttribute("companyAllCodeList",companyAllCodeList);
				request.setAttribute("companyParent", COMPANY_PARENT);
				request.setAttribute("companyChild", COMPANY_CHILD);
				request.setAttribute("parentCompanyValue",parentCompanyValue);
				request.setAttribute("subCompanyValue", subCompanyValue);
			}
			/* 3-2 所属会社グループ権限が0であれば、ログイン者の所属会社IDと結びついた会社識別コードを取得しリクエストスコープに格納する。
			 　4-2 所属会社グループ権限が0であれば、4で取得した子会社のみをリクエストスコープに格納する。
			   5-2 所属会社グループ権限が0であれば、値0を格納している文字列をリクエストスコープにセットする。 */
			if(companyPrivilege.equals(subCompanyValue)) {
				String userId =(String)session.getAttribute("userId");
				String  companyBelongCode=searchCompany.searchCompanyCode(userId);
				request.setAttribute("companyCodeList", companyCodeList);
				request.setAttribute("companyBelongCode", companyBelongCode);
				request.setAttribute("companyChild", COMPANY_CHILD);
				request.setAttribute("subCompanyValue", subCompanyValue);
			}
			// 所属会社マスタメンテナンス画面に遷移
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterCompany.jsp");
			disp.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}


}


