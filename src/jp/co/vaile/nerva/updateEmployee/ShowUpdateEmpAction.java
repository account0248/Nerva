package jp.co.vaile.nerva.updateEmployee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.skillMaster.GetSkillTypeDAO;
import jp.co.vaile.nerva.commonprocess.skillMaster.SkillTypeDTO;
import jp.co.vaile.nerva.detailEmployee.EmpDetailInfoLogic;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;

@WebServlet("/ShowUpdateEmpAction")
public class ShowUpdateEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *従業員IDをリクエストとして受け取り、従業員詳細情報表示機能を呼び出し、従業員更新画面に遷移する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//従業員IDをリクエストスコープから取得する
		String employeeId = (String) request.getParameter("employeeId");
		try {
			//従業員詳細表示処理を呼び出す。
			EmpDetailInfoLogic empDetailInfoLogic = new EmpDetailInfoLogic();
			EmpInfoDTO empInfoDTO = new EmpInfoDTO();
			empInfoDTO = empDetailInfoLogic.passEmpInfoDTO(employeeId);
			List<EmpSkillInfoDTO> empSkillInfoDTOList = new ArrayList<EmpSkillInfoDTO>();
			empSkillInfoDTOList = empDetailInfoLogic.passEmpSkillDTO(employeeId);
			List<EmpWorkExpDTO> empWorkExpDTOList = new ArrayList<EmpWorkExpDTO>();
			empWorkExpDTOList = empDetailInfoLogic.passEmpWorkExpDTO(employeeId);
			
			//スキル種別全件取得
			GetSkillTypeDAO getSkillTypeDAO = new GetSkillTypeDAO();
			List<SkillTypeDTO> skillTypeList = new ArrayList<SkillTypeDTO>();
			skillTypeList = getSkillTypeDAO.getSkillType();

			//従業員DTO、スキル情報DTO、業務経験DTO、スキル種別DTOをセッションスコープに格納する。
			session.setAttribute("empInfoDTO", empInfoDTO);
			session.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
			session.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
			session.setAttribute("skillTypeList", skillTypeList);

			//セレクトボックスとして使用するマスタデータを取得し、リクエストスコープに格納する
			SearchMasterTableInfoLogic searchMasterTableInfoLogic = new SearchMasterTableInfoLogic();
			Map<String, List<FetchAnyMasterTableDTO>> masterTableInfo = new HashMap<String, List<FetchAnyMasterTableDTO>>();
			masterTableInfo = searchMasterTableInfoLogic.searchMasterTableInfo();
			request.setAttribute("masterTableInfo", masterTableInfo);
		} catch (ClassNotFoundException | SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}

		//従業員更新画面に遷移する。
		request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
	}

}
