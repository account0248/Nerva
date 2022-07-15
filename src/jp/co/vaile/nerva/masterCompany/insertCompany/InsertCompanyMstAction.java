package jp.co.vaile.nerva.masterCompany.insertCompany;

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

@WebServlet( "/InsertCompanyMstAction" )
public class InsertCompanyMstAction extends HttpServlet {

	/**
	 * 登録ボタンを押下した際の処理。 所属会社の登録処理を呼びだす。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		//1 所属会社マスタメンテナンス画面内の登録フォームに入力された値を受け取る。
		String companyId = request.getParameter("json[companyId]");
		String companyName = request.getParameter("json[companyName]");
		String companyGroup = request.getParameter("json[companyGroup]");
		String companyCode = request.getParameter("json[companyCode]");
		//2 セッション情報からユーザーIDを所属会社登録DTOに格納する。
		String userId =(String) session.getAttribute("userId");		
		//入力情報をDTOに格納する
		InsertCompanyDTO insertCompanyDTO = new InsertCompanyDTO(userId,companyId,companyName,companyGroup,companyCode);
		//3 所属会社登録DTOを引数にエラーチェック処理を呼び出す。
		//エラーチェッククラスのインスタンスを生成する
		InsertCompanyCheckAction insertCompanyCheckAction = new InsertCompanyCheckAction();	
		try {
			List<String> errorList = new ArrayList<String>();
			errorList = insertCompanyCheckAction.checkInsertCompany(insertCompanyDTO);
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
			} else {
				// 4 所属会社登録DTOを引数に登録処理を呼び出す。
				InsertCompanyMstLogic insertCompanyMstLogic = new InsertCompanyMstLogic();
				insertCompanyMstLogic.insertCompanyMstLogic(insertCompanyDTO);
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
