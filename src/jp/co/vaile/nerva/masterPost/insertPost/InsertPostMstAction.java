package jp.co.vaile.nerva.masterPost.insertPost;

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

@WebServlet( "/InsertPostMstAction" )
public class InsertPostMstAction extends HttpServlet{

	/**
	 * 登録ボタンを押下した際の処理。 所属組織の登録処理を呼びだす。
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();

		String postId = request.getParameter("json[postId]");
		String postName = request.getParameter("json[postName]");
		String userId =(String) session.getAttribute("userId"); 
	
		//入力情報をDTOに格納する
		InsertPostDTO insertPostDTO = new InsertPostDTO(userId,postId,postName);
				
		//エラーチェック処理を呼び出す
		try {
			//エラーチェッククラスのインスタンスを生成する
			InsertPostCheckAction insertPostCheckAction = new InsertPostCheckAction();	
			List<String> errorList = new ArrayList<String>();
			
			errorList = insertPostCheckAction.checkInsertPost(insertPostDTO);
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

			String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
			out.print(errorListString);
			out.close();
			} else {
			// 入力情報を引数に登録処理を呼び出す。
			InsertPostMstLogic insertPostMstLogic = new InsertPostMstLogic();
			insertPostMstLogic.insertPostMstLogic(insertPostDTO);
			out.print("{}");
			// クローズ
			out.close();
			}
		} catch (ParseException | ClassNotFoundException | SQLException e) {
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			// TODO 自動生成された catch ブロック
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
