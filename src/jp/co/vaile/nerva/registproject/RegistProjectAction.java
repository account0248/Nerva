package jp.co.vaile.nerva.registproject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistProjectAction extends HttpServlet {

	// リクエストスコープから情報を受け取る。
	private String projectId;
	private String projectName;
	private String orderSource;
	private String industryId;
	private String projectStartDate;
	private String projectCompleteDate;
	// エラーメッセージ格納用
	private List<String> errorMsgList = new ArrayList<String>();

	/**
	 * 登録ボタンを押下した際の処理。
	 * 案件情報登録処理の呼び出しを行い、案件詳細画面に遷移する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String result = "ShowProjectDetailAction";

		// リクエストスコープから情報を受け取る。
		projectId = request.getParameter("projectId");
		projectName = request.getParameter("projectName");
		orderSource = request.getParameter("orderSource");
		industryId = request.getParameter("industryId");
		projectStartDate = request.getParameter("projectStartDate");
		projectCompleteDate = request.getParameter("projectCompleteDate");

		// セッションスコープから情報を受け取る。
		HttpSession session;
		session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		String responsibleId = loginUserId;
		String contractorId = (String) session.getAttribute("companyId");

		// ページ用DTOに格納
		RegistProjectPageDTO registProjectPageDTO = new RegistProjectPageDTO();
		registProjectPageDTO.setProjectId(projectId);
		registProjectPageDTO.setProjectName(projectName);
		registProjectPageDTO.setResponsibleId(responsibleId);
		registProjectPageDTO.setContractorId(contractorId);
		registProjectPageDTO.setOrderSource(orderSource);
		registProjectPageDTO.setIndustryId(industryId);
		registProjectPageDTO.setProjectStartDate(projectStartDate);
		registProjectPageDTO.setProjectCompleteDate(projectCompleteDate);

		RegistProjectLogic registProjectLogic = new RegistProjectLogic();

		try {
			// 入力情報のエラーをチェックを行う。
			// エラーがあった場合、エラーメッセージを設定し、入力内容を保持したまま案件登録画面に遷移させ、処理を終了する。
			errorMsgList = registProjectLogic.inputCheckRegistProject(registProjectPageDTO);

			if (errorMsgList.size() > 0) {
				result = setRequest(request);
			} else {
				// 案件登録情報を引数に案件情報登録処理を呼び出す。
				registProjectLogic.registProject(registProjectPageDTO, loginUserId);
				// 分岐案件詳細画面に案件IDを渡す。
				session.setAttribute("projectId", projectId);
			}
 		} catch (ParseException | ClassNotFoundException | IllegalArgumentException | SQLException e) {
			e.printStackTrace();
			result = "error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			result = "error.jsp";
		}
		// 案件詳細画面遷移処理を呼び出し、登録した案件の案件詳細画面に遷移させる。
		request.getRequestDispatcher(result).forward(request, response);
	}

	/**
	 * リクエストスコープにデフォルト値を設定する。
	 * 案件登録画面表示処理のURL文字列
	 *
	 * @param request
	 * @return 案件登録画面表示処理のURL
	 */
	private String setRequest(HttpServletRequest request) {
		request.setAttribute("errorMsgList", errorMsgList);
		request.setAttribute("defaultProjectId", projectId);
		request.setAttribute("defaultProjectName", projectName);
		request.setAttribute("defaultOrderSource", orderSource);
		request.setAttribute("defaultIndustryId", industryId);
		request.setAttribute("defaultProjectStartDate", projectStartDate);
		request.setAttribute("defaultProjectCompleteDate", projectCompleteDate);
		return "ShowRegistProject";
	}
}
