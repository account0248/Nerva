package jp.co.vaile.nerva.searchemployee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;

public class ShowSearchEmpAction extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//セッションからログインユーザーの所属会社を取得する
		String companyId = (String) session.getAttribute("companyId");
		//セッションからログインユーザーの所属会社の親会社フラグを取得する
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");
		session.removeAttribute("teamDetailPage");
		List<FetchAnyMasterTableDTO> campanyNameList = new ArrayList<>();


		BelongCompanyInfo belongCompanyInfo =new BelongCompanyInfo();
		try {
			campanyNameList = belongCompanyInfo.fetchBelongCompanyList(companyId,companyPrivilege);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/error.jsp");

			disp.forward(request, response);
			e.printStackTrace();
		}

		//前の画面によって分岐処理を行う。
		Map<String, String> ditileEmployee =(Map<String,String>)request.getAttribute("searchEmployee");
		
		//従業員登録画面から遷移した場合、従業員検索画面入力情報DTOを破棄する。
		if( ditileEmployee == null){
			session.removeAttribute("searchEmpPageDTO");
		}
		request.setAttribute("campanyNameList", campanyNameList);

		//従業員検索画面に遷移する。
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/searchEmployee.jsp");

		disp.forward(request, response);
	}
}