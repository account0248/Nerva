package jp.co.vaile.nerva.detailEmployee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;

@WebServlet("/EmpDetailInfoAction")
public class EmpDetailInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 従業員詳細情報表示画面に遷移する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストスコープから詳細ボタンが押下されたユーザの従業員IDを取得する。
		String employeeId = (String) request.getAttribute("employeeId");
		HttpSession session = request.getSession();

		// セッションからログインユーザーの所属会社IDと所属会社グループ権限を取得する
		String loginUserCompanyId = (String) session.getAttribute("companyId");
		String companyPrivilege = (String) session.getAttribute("companyPrivilege");

		// インスタンス生成
		EmpDetailInfoLogic empDetailInfoLogic = new EmpDetailInfoLogic();
		EmpInfoDTO empInfoDTO = new EmpInfoDTO();

		// リスト作成
		List<EmpSkillInfoDTO> empSkillInfoDTOList = new ArrayList<EmpSkillInfoDTO>();
		List<EmpWorkExpDTO> empWorkExpDTOList = new ArrayList<EmpWorkExpDTO>();
		try {
			// 従業員IDをもとに従業員詳細表示処理を呼び出す。
			// 従業員情報検索処理
			empInfoDTO = empDetailInfoLogic.passEmpInfoDTO(employeeId);
			
			// スキル情報検索処理
			empSkillInfoDTOList = empDetailInfoLogic.passEmpSkillDTO(employeeId);
			
			// 従業員業務経験検索処理
			empWorkExpDTOList = empDetailInfoLogic.passEmpWorkExpDTO(employeeId);

			// ログインユーザーの参照権限をチェックする
			String companyId = null;

			// インスタンス生成
			SearchEmpCompanyIdDAO searchEmpCompanyIdDAO = new SearchEmpCompanyIdDAO();
			
			// 選択されたユーザの所属会社ID取得処理を呼び出す
			companyId = searchEmpCompanyIdDAO.searchCompanyId(employeeId);
			
			// インスタンス生成
			CheckViewingAuthority cViewingAuthority = new CheckViewingAuthority();

			// 選択されたユーザの可視性を判定する
			boolean viewingAuthority = cViewingAuthority.checkViewingAuthority(loginUserCompanyId, companyPrivilege,
					companyId);

			// ユーザの可視性の判定結果がtrueかつ、従業員情報検索処理の結果がnullでない場合
			if (viewingAuthority && empInfoDTO.getEmployeeId() != null) {
				// 従業員詳細表示処理の戻り値である従業員DTO、スキル情報DTO、業務経験DTOをリクエストスコープに格納する。
				request.setAttribute("empInfoDTO", empInfoDTO);
				request.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
				request.setAttribute("empWorkExpDTOList", empWorkExpDTOList);

				// 従業員詳細情報表示画面に遷移する。
				request.getRequestDispatcher("/jsp/detailEmployee.jsp").forward(request, response);
				
			// それ以外の場合
			} else {
				// エラー画面に遷移する。
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// エラー画面に遷移する。
			e.printStackTrace();
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
	}
}
