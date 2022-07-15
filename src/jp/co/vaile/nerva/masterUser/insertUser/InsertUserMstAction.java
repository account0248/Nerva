package jp.co.vaile.nerva.masterUser.insertUser;

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

@WebServlet("/InsertUserMstAction")
public class InsertUserMstAction extends HttpServlet {

	/**
	 * 登録ボタンを押下した際の処理。 ユーザーの登録処理を呼びだす。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();

		String userId = request.getParameter("json[userId]");
		String userName = request.getParameter("json[userName]");
		String password = request.getParameter("json[password]");
		String company = request.getParameter("json[company]");
		String post = request.getParameter("json[post]");
		String adminFlg = request.getParameter("json[adminFlg]");
		String loginUserId = (String) session.getAttribute("userId");

		// 入力情報をユーザー登録DTOに格納する
		InsertUserDTO insertUserDTO = new InsertUserDTO(loginUserId, userId, userName, password, company, post,
				adminFlg);

		// エラーチェック処理を呼び出す
		try {
			// エラーチェッククラスのインスタンスを生成する
			InsertUserCheckAction insertUserCheckAction = new InsertUserCheckAction();
			List<String> errorList = new ArrayList<String>();

			errorList = insertUserCheckAction.checkInsertUser(insertUserDTO);
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
			} else {
				// ユーザー登録DTOを引数に登録処理を呼び出す。
				InsertUserMstLogic insertUserMstLogic = new InsertUserMstLogic();
				insertUserMstLogic.insertUserMstLogic(insertUserDTO);
				out.print("{}");
				// クローズ
				out.close();
			}
		} catch (ParseException | ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String errorListString = mapper.writeValueAsString("");
			out.print(errorListString);
			out.close();
			e.printStackTrace();
		}
	}

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}
