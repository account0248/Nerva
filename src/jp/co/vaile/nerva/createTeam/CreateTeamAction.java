package jp.co.vaile.nerva.createTeam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CreateTeamAction
 */
/*@WebServlet("/CreateTeamAction")*/

/**
 *作成ボタンを押下した際の処理。
 *チーム登録処理の呼び出しを行い、チーム詳細表示画面に遷移する。
 *
 */
public class CreateTeamAction extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String result = "/jsp/ShowTeamDetailInfoAction";
		HttpSession session = request.getSession();
		CreateTeamInfoPageDTO createTeamInfoPageDTO = new CreateTeamInfoPageDTO();
		CreateTeamLogic createTeamLogic = new CreateTeamLogic();
		CreateTeamCheckLogic createTeamCheckLogic = new CreateTeamCheckLogic();

		//	登録画面で記載された入力情報と会社識別コードを受け取る。
		String teamId = request.getParameter("teamId");
		String teamName = request.getParameter("teamName");
		String companyId = (String)session.getAttribute("companyId");
		//	チーム作成者はユーザーIDで登録するためにセッションのユーザーIDを取得する
		String managerId = (String)session.getAttribute("userId");
		
		String companyCode = (String)session.getAttribute("companyCode");
		
		// 入力情報をチーム情報ページDTOに格納する
		createTeamInfoPageDTO.setTeamId(teamId);
		createTeamInfoPageDTO.setTeamName(teamName);
		createTeamInfoPageDTO.setCompanyId(companyId);
		createTeamInfoPageDTO.setManagerId(managerId);
		try {
			//	入力情報のエラーをチェックする
			List<String> errorList = createTeamCheckLogic.checkCreateTeam(createTeamInfoPageDTO,companyCode);

			//	エラーがあった場合、エラーメッセージを設定し、入力内容を保持したままチーム作成画面に遷移させ、処理を終了する。
			if (errorList.size() != 0) {
				request.setAttribute("createTeamInfoPageDTO", createTeamInfoPageDTO);
				request.setAttribute("errorList", errorList);
				request.setAttribute("teamId", teamId);
				request.setAttribute("teamName", teamName);
				result = "/jsp/createTeam.jsp";
			} else {

				//	ユーザーIDと入力内容を引数にチーム登録処理を呼び出す。
				String createTeamErrorMsg = null;
				createTeamErrorMsg = createTeamLogic.createTeam(createTeamInfoPageDTO, managerId);


				//	nullでなかった場合、エラーメッセージを設定し、入力内容を保持したままチーム作成画面に遷移させ、処理を終了する。
				if ( createTeamErrorMsg!= null) {
					errorList.add(createTeamErrorMsg);
					request.setAttribute("createTeamInfoPageDTO", createTeamInfoPageDTO);
					request.setAttribute("errorList", errorList);
					request.setAttribute("teamId", teamId);
					request.setAttribute("teamName", teamName);
					result = "/jsp/createTeam.jsp";
				}

			}
			
		
			//	チーム詳細表示処理を呼び出し、登録したチームのチーム詳細表示画面へ遷移させる。
			request.setAttribute("teamId", teamId);
			RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
			// forwardメソッドでJSPに遷移します。
			disp.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {

			//エラー画面に遷移する。
			e.printStackTrace();
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
	}

}
