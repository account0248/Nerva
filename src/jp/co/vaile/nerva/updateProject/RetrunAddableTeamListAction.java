package jp.co.vaile.nerva.updateProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RetrunAddableTeamListAction extends HttpServlet {

	/**
	 * 案件情報更新画面の追加ボタン押下後、参加チームに行を追加する
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();

		// セッションスコープから所属会社IDを取得する
		String loginBelongCompanyId = (String) session.getAttribute("companyId");

		// 参加可能チームDTOのリストを作成
		List<AddableTeamDTO> addableTeamList = new ArrayList<AddableTeamDTO>();

		// 所属会社IDを基に参加可能チームDTOのリストを取得する
		try {
			addableTeamList = new ShowUpdatePjtLogic().fetchPjtAddableTeam(loginBelongCompanyId);

			// 参加可能チームDTOのリストをMapに格納
			AddableTeamListDTO addableTeam = new AddableTeamListDTO();
			addableTeam.teamlistDto = addableTeamList;
			Map<String, List<AddableTeamDTO>> mapAddableTeam = new HashMap<String, List<AddableTeamDTO>>();
			mapAddableTeam.put("addableTeam", addableTeamList);

			// Mapをjson文字列に変換する
			ObjectMapper mapper = new ObjectMapper();
			String addableTeamListData = mapper.writeValueAsString(mapAddableTeam);
			PrintWriter out = response.getWriter();

			// json文字列をストリームに書き出し、クライアントに送る
			out.print(addableTeamListData);
			out.close();

		} catch (ClassNotFoundException | SQLException e) {
			// 例外が発生した場合、エラー画面に遷移する
			e.printStackTrace();
			
			ObjectMapper mapper = new ObjectMapper();
			String ajaxExceptionJson = "";
			PrintWriter out = response.getWriter();

			ajaxExceptionJson = mapper.writeValueAsString("");
			out.print(ajaxExceptionJson);
			out.close();
			
		}

	}

	public class AddableTeamListDTO {

		List<AddableTeamDTO> teamlistDto;
	}
}
