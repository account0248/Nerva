package jp.co.vaile.nerva.updateEmployee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;

/**
 * Servlet implementation class UpdateEmpAction
 */
@WebServlet("/UpdateEmpAction")
public class UpdateEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 更新ボタンが押下された時、従業員更新を行う。
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ErrorMsg errorMsg = new ErrorMsg();
		List<String> errorMessage = new ArrayList<String>();
		HttpSession session = request.getSession();
		// セッションから入力チェック済みのDTOを取得する
		UpdateEmpInfoDTO updateEmpInfoDTO = (UpdateEmpInfoDTO) session.getAttribute("updateEmpInfoDTO");
		List<UpdateSkillInfoDTO> updateSkillInfoDTOList = (ArrayList<UpdateSkillInfoDTO>) session
				.getAttribute("updateSkillInfoDTOList");
		List<RegistSkillInfoDTO> registSkillInfoDTOList = (ArrayList<RegistSkillInfoDTO>) session
				.getAttribute("registSkillInfoDTOList");
		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = (ArrayList<UpdateEmpWorkExpDTO>) session
				.getAttribute("updateEmpWorkExpDTOList");
		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = (ArrayList<RegistEmpWorkExpDTO>) session
				.getAttribute("registEmpWorkExpDTOList");

		// セッションから更新画面に表示したDTOを取得する
		EmpInfoDTO displayedEmpInfoDTO = (EmpInfoDTO) session.getAttribute("empInfoDTO");
		List<EmpSkillInfoDTO> displayedEmpSkillInfoDTOList = (List<EmpSkillInfoDTO>) session
				.getAttribute("empSkillInfoDTOList");
		List<EmpWorkExpDTO> displayedEmpWorkExpDTOList = (List<EmpWorkExpDTO>) session
				.getAttribute("empWorkExpDTOList");

		// セッションからログインユーザーID、従業員IDを取得する
		String loginUserId = (String) session.getAttribute("userId");
		String employeeId = displayedEmpInfoDTO.getEmployeeId();

		// セッションにある全ての要素名を取得する
		Enumeration<String> allElementName = session.getAttributeNames();
		// 取得した要素名をループ処理で全て削除する
		while (allElementName.hasMoreElements()) {
			String sessionName = (String) allElementName.nextElement();
			// ログイン情報は破棄しない
			if (!sessionName.equals("loginCheck") && !sessionName.equals("userId") && !sessionName.equals("userName")
					&& !sessionName.equals("companyId") && !sessionName.equals("adminFlg")
					&& !sessionName.equals("companyPrivilege")) {
				session.removeAttribute(sessionName);
			}
		}

		try {

			// 更新画面に表示した内容と更新しようとしている内容に相違がないか確認する
			ConfirmBeforeUpdateEmpLogic confirmBeforeEmpLogic = new ConfirmBeforeUpdateEmpLogic();
			errorMessage = errorMsg.errorMsgNullCheck(
					confirmBeforeEmpLogic.checkEmpInfoBeforeUpdate(displayedEmpInfoDTO), errorMessage);
			errorMessage = errorMsg.errorMsgNullCheck(
					confirmBeforeEmpLogic.checkSkillInfoBeforeUpdate(displayedEmpSkillInfoDTOList), errorMessage);
			errorMessage = errorMsg.errorMsgNullCheck(
					confirmBeforeEmpLogic.checkWorkExpBeforeUpdate(displayedEmpWorkExpDTOList), errorMessage);
			// エラーメッセージがある場合、エラーメッセージを格納して従業員検索画面に遷移する
			if (errorMessage.size() != 0) {
				request.setAttribute("errorMsg", errorMessage.get(0));
				request.getRequestDispatcher("ShowSearchEmpAction").forward(request, response);
				return;
			}

			UpdateEmpLogic updateEmpLogic = new UpdateEmpLogic();
			SetUpdateUserLogic<UpdateEmpInfoDTO> setUpdateEmpInfo = new SetUpdateUserLogic<UpdateEmpInfoDTO>();
			// 従業員情報更新処理を呼び出す
			updateEmpInfoDTO = setUpdateEmpInfo.setUser(updateEmpInfoDTO, loginUserId, employeeId);
			updateEmpLogic.passUpdateEmpInfo(updateEmpInfoDTO);
			// スキル情報更新用リストがnullでない場合、更新処理を呼び出す
			if (updateSkillInfoDTOList != null) {
				SetUpdateUserLogic<UpdateSkillInfoDTO> setUpdateSkillInfo = new SetUpdateUserLogic<UpdateSkillInfoDTO>();
				updateSkillInfoDTOList = setUpdateSkillInfo.setUser(updateSkillInfoDTOList, loginUserId, employeeId);
				updateEmpLogic.passUpdateSkillInfo(updateSkillInfoDTOList);
			}
			// 業務経験更新用リストがnullでない場合、更新処理を呼び出す
			if (updateEmpWorkExpDTOList != null) {
				SetUpdateUserLogic<UpdateEmpWorkExpDTO> setUpdateWorkExp = new SetUpdateUserLogic<UpdateEmpWorkExpDTO>();
				updateEmpWorkExpDTOList = setUpdateWorkExp.setUser(updateEmpWorkExpDTOList, loginUserId, employeeId);
				updateEmpLogic.passUpdateEmpWorkExp(updateEmpWorkExpDTOList);
			}
			// スキル情報登録用リストがnullでない場合、更新処理を呼び出す
			if (registSkillInfoDTOList != null) {
				SetUpdateUserLogic<RegistSkillInfoDTO> setRegistSkillInfo = new SetUpdateUserLogic<RegistSkillInfoDTO>();
				registSkillInfoDTOList = setRegistSkillInfo.setUser(registSkillInfoDTOList, loginUserId, employeeId);
				updateEmpLogic.passRegistSkillInfo(registSkillInfoDTOList);
			}
			// 業務経験登録用リストがnullでない場合、更新処理を呼び出す
			if (registEmpWorkExpDTOList != null) {
				SetUpdateUserLogic<RegistEmpWorkExpDTO> setRegistWorkExp = new SetUpdateUserLogic<RegistEmpWorkExpDTO>();
				registEmpWorkExpDTOList = setRegistWorkExp.setUser(registEmpWorkExpDTOList, loginUserId, employeeId);
				updateEmpLogic.passRegistEmpWorkExp(registEmpWorkExpDTOList);
			}

		} catch (Exception e) {
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

		// 更新に成功した場合、リクエストスコープに従業員IDを格納して従業員詳細画面に遷移する
		request.setAttribute("employeeId", updateEmpInfoDTO.getEmployeeId());
		request.getRequestDispatcher("EmpDetailInfoAction").forward(request, response);
	}
}
