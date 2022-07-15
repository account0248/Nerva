package jp.co.vaile.nerva.masterIndustry.insertIndustry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/InsertIndustryMstAction")
public class InsertIndustryMstAction extends HttpServlet {

	/**
	 * 登録ボタンを押下した際の処理。 業種の登録処理を呼びだす。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();

		String industryId = request.getParameter("json[industryId]");
		String industryName = request.getParameter("json[industryName]");
		String userId = (String) session.getAttribute("userId");

		// 入力情報をDTOに格納する
		InsertIndustryDTO insertIndustryDTO = new InsertIndustryDTO(userId, industryId, industryName);

		// エラーチェック処理を呼び出す
		try {
			// エラーチェッククラスのインスタンスを生成する
			InsertIndustryLogicCheckAction insertIndustryCheckAction = new InsertIndustryLogicCheckAction();
			List<String> errorList = new ArrayList<String>();

			errorList = insertIndustryCheckAction.checkInsertIndustry(insertIndustryDTO);
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
			} else {
				// 入力情報を引数に登録処理を呼び出す。
				InsertIndustryMstLogic insertIndustryMstLogic = new InsertIndustryMstLogic();
				insertIndustryMstLogic.insertIndustryMstLogic(insertIndustryDTO);
				out.print("{}");
				// クローズ
				out.close();
			}
		} catch (ParseException | ClassNotFoundException | SQLException e) {
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String errorListString = mapper.writeValueAsString("");
			out.print(errorListString);
			out.close();
			e.printStackTrace();
		}
	}

	private Map<String, List<String>> storeErrorMap(List<String> registErrorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("registErrorListDTO", registErrorList);
		return errorMap;
	}
}
