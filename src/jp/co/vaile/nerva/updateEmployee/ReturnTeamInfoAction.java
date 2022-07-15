package jp.co.vaile.nerva.updateEmployee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.commonprocess.ListDTO;

/**
 * Servlet implementation class ReturnTeamInfoAction
 */
@WebServlet("/ReturnTeamInfoAction")
public class ReturnTeamInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *業務経験欄で追加ボタンが押下された時、チーム情報をJSON形式で返す。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		try {
			//チーム情報取得処理を呼び出す。
			SearchTeamInfoDAO searchTeamInfoDAO = new SearchTeamInfoDAO();
			List<TeamInfoDTO> teamInfoDTOList;
			teamInfoDTOList = searchTeamInfoDAO.searchTeamInfo(loginUserId);
			ListDTO<TeamInfoDTO> teamInfoDTO = new ListDTO<TeamInfoDTO>();
			teamInfoDTO.setListDTO(teamInfoDTOList);
			ObjectMapper mapper = new ObjectMapper();
			String teamInfoDTOListData = mapper.writeValueAsString(teamInfoDTO);
			PrintWriter out = response.getWriter();
			//取得したチーム情報をJSONで返し、処理を終了する。
			out.print(teamInfoDTOListData);
			out.close();
		} catch ( Exception e) {
			//例外が発生した場合、エラーページに遷移する。
			ObjectMapper mapper = new ObjectMapper();
			String masterTableInfoData = mapper.writeValueAsString("");
			PrintWriter out = response.getWriter();
			out.print(masterTableInfoData);
		}
	}

}
