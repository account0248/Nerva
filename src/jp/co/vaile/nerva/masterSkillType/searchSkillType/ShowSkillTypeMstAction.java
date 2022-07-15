package jp.co.vaile.nerva.masterSkillType.searchSkillType;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.vaile.nerva.commonprocess.MasterContents;

@WebServlet( "/ShowSkillTypeMstAction" )
public class ShowSkillTypeMstAction extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//年数/取得日をリクエストスコープにセット
		request.setAttribute("years", MasterContents.YEARS);
		request.setAttribute("dateOfAcquisition", MasterContents.DATE_OF_ACQUISITION);
		request.setAttribute("yearsValue", MasterContents.YEARS_VALUE);
		request.setAttribute("dateOfAcquisitionValue", MasterContents.DATE_OF_ACQUISITION_VALUE);
		//スキル種別マスメンテナンス画面に遷移
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/jsp/masterSkill.jsp");
		disp.forward(request, response);
	}
}
