package jp.co.vaile.nerva.masterSkillType.insertSkillType;

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

@WebServlet( "/InsertSkillTypeMstAction" )
public class InsertSkillTypeMstAction extends HttpServlet {
	/**
	 * 登録するボタン押下後の処理。 スキル種別の登録処理を呼びだす。
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();

		//json型の値を画面から受け取る
		//スキル種別マスタメンテナンス画面内検索フォームに入力された値を受け取る。
		String skillTypeId = request.getParameter("json[skillTypeId]");
		String skillTypeName = request.getParameter("json[skillTypeName]");
		String yearsDateOfAcquisition = request.getParameter("json[yearsDateOfAcquisition]");
		//セッションからユーザーIDを取得
		String userId =(String) session.getAttribute("userId"); 
	
		//入力情報をDTOに格納する
		InsertSkillTypeDTO insertSkillTypeDTO = new InsertSkillTypeDTO(skillTypeId,skillTypeName,yearsDateOfAcquisition,userId);

		try {
			//エラーチェッククラスのインスタンスを生成する
			InsertSkillTypeCheckAction insertSkillTypeCheckAction = new InsertSkillTypeCheckAction();
			//エラーメッセージを格納するリストを生成
			List<String> errorList = new ArrayList<String>();
			//入力情報を引数にエラーチェック処理を呼び出す。
			errorList = insertSkillTypeCheckAction.checkInsertSkillType(insertSkillTypeDTO);
			
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			//エラーがあった場合エラーメッセージを送りクローズ
			if (errorList.size() != 0) {
			String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
			out.print(errorListString);
			// クローズ
			out.close();
			} else {
			// 入力情報を引数に登録処理を呼び出す。
			InsertSkillTypeMstLogic insertSkillTypeMstLogic = new InsertSkillTypeMstLogic();
			insertSkillTypeMstLogic.insertSkillTypeMstLogic(insertSkillTypeDTO);
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
			// クローズ
			out.close();
			e.printStackTrace();
		}
	}
	
	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("registErrorListDTO", errorList);
		return errorMap;
	}
}
