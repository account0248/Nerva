package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ImportCheckAction
 */
@WebServlet("/ImportCheckAction")
public class ImportCheckAction extends HttpServlet {
	/**
	 * インポートボタン押下時に呼び出す
	 * ファイル名からインポートするファイル種類を判別しフラグをリターンする
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		// json型の値を画面から受け取る
		String fileName = request.getParameter("json[fileName]");
		ImportCheckLogicCheck importCheckLogicCheck = new ImportCheckLogicCheck();
		
		//エラーチェック処理呼び出し
		List<String> errorList = importCheckLogicCheck.checkImport(fileName);
		
		/*
		 * エラーが無かった場合、ファイルフラグを取得、それを返す
		 * エラーがあった場合、エラーメッセージを返す
		 */
		if(errorList.size()==0) {
			ImportCheckLogic importCheckLogic = new ImportCheckLogic();
			//ファイルフラグを取得
			boolean flg = importCheckLogic.importCheck(fileName);
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String flgStr = "";
			//ファイルフラグをString型にキャストする
			flgStr = mapper.writeValueAsString(flg);
			//ファイルフラグを返す
			out.write(flgStr);
			out.close();
			
		}else {
			//エラー文を返す
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String errorJsonList = "";
			//エラーリストをString型にキャストする
			errorJsonList = mapper.writeValueAsString(errorList);
			//エラーリストを返す
			out.write(errorJsonList);
			out.close();
		}
		
	}

}
