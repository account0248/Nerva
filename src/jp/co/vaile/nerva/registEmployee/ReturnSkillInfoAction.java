package jp.co.vaile.nerva.registEmployee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.commonprocess.ListDTO;
import jp.co.vaile.nerva.commonprocess.skillMaster.SkillTypeDTO;

public class ReturnSkillInfoAction extends HttpServlet {

	/**
	 *スキル種別マスタから情報を取得する処理の呼び出しを行い、JSONで情報を返す。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		//1.	スキル種別情報取得処理の呼び出しを行う。
		try {
			ReturnSkillInfoLogic returnSkillInfoLogic = new ReturnSkillInfoLogic();
			List<SkillTypeDTO> skillTypeList = returnSkillInfoLogic.returnSkillTypeList();

			ListDTO<SkillTypeDTO> listDto = new ListDTO<SkillTypeDTO>();
			listDto.setListDTO(skillTypeList);
			ObjectMapper mapper = new ObjectMapper();
			String skilTypeListData = mapper.writeValueAsString(listDto);
			PrintWriter out = response.getWriter();
			//2.	取得したスキル種別情報をJSONで返し、処理を終了する。
			out.print(skilTypeListData);
			out.close();
		} catch (ClassNotFoundException | SQLException e) {
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String skilTypeListData = mapper.writeValueAsString("");
			out.print(skilTypeListData);
			out.close();
			e.printStackTrace();
		}
	}
}
