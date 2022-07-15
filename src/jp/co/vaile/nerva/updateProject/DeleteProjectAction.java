package jp.co.vaile.nerva.updateProject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;

public class DeleteProjectAction extends HttpServlet {

	/**
	 * 案件情報更新画面の削除ボタン押下後、削除処理を行い案件検索画面に遷移する
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String result = "/jsp/ShowProjectAction"; /* TODO案件検索表示アクション */

		// セッションスコープからユーザIDと案件情報更新画面DTOを取得する
		String userId = (String) session.getAttribute("userId");
		ProjectDetailPageDTO updatePjtPageDto = (ProjectDetailPageDTO) session.getAttribute("updatePjtPageDto");

		// 案件が削除済みかをチェックする
		DeleteProjectLogic deleteProjectLogic = new DeleteProjectLogic();
		String messageId;
		
		try {
			messageId = deleteProjectLogic.checkProjectDeleted(updatePjtPageDto.getProjectId());

			// エラーがあった場合、エラーメッセージを設定し、案件検索画面表示アクションにフォワードする
			if (!messageId.equals("")) {
				String[] prjectId = { updatePjtPageDto.getProjectId() };
				String errorMsg = new ErrorMsg().returnErrorMsg(messageId, prjectId);
				request.setAttribute("errorMsg", errorMsg);
			} else {
				// 案件情報削除処理を呼び出す
				deleteProjectLogic.deleteProject(updatePjtPageDto.getProjectInfoId(), updatePjtPageDto.getProjectId(),
						userId);
			}
			
			// セッションに格納されているログイン情報以外のセッションを取り除く
			session.removeAttribute("projectId");
			session.removeAttribute("updatePjtEntryTeamList");
			session.removeAttribute("addableTeamList");
			session.removeAttribute("");/* TODO 検索情報を取り除く */

		} catch (ClassNotFoundException | SQLException e) {
			// 例外が発生した場合、エラー画面に遷移する
			e.printStackTrace();
			result = "/jsp/error.jsp";
		}

		// 案件検索画面表示アクションにフォワードする

		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		disp.forward(request, response);
	}
}
