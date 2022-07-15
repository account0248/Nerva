package jp.co.vaile.nerva.managementattedance.displayWorkSchedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DisplayCheckAction
 */
@WebServlet("/DisplayCheckAction")
public class DisplayCheckAction extends HttpServlet {
	/**
	 * 表示ボタン押下時のチェック処理
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		// json型の値を画面から受け取る
		String year = request.getParameter("json[year]");
		String month = request.getParameter("json[month]");

		try {
			DisplayCheckLogicCheck displayCheckLogicCheck = new DisplayCheckLogicCheck();
			List<String> errorList = new ArrayList<>();

			//エラーチェック処理の呼び出し
			errorList = displayCheckLogicCheck.checkDisplay(year, month);
			
			/*
			 * エラーがあった場合、エラーメッセージを返す
			 * エラーがなかった場合、空文字を返す
			 */
			if (errorList.size() > 0) {
				// 処理結果セット
				ObjectMapper mapper = new ObjectMapper();
				PrintWriter out = response.getWriter();
				String errorJsonList = "";
				errorJsonList = mapper.writeValueAsString(errorList);
				out.write(errorJsonList);
				out.close();
			} else {
				PrintWriter out = response.getWriter();
				out.write("{}");
				out.close();
			}
		} catch (Exception e) {
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

}
