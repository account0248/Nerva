package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;
import jp.co.vaile.nerva.managementattedance.WorkScheduleListDTO;
import jp.co.vaile.nerva.managementattedance.WorkscheduleContents;

/**
 * Servlet implementation class ImportTextAction
 */
@WebServlet("/ImportTextAction")
@MultipartConfig(fileSizeThreshold = 5000000, maxFileSize = 700 * 1024
		* 1024, location = WorkscheduleContents.IMPORT_PATH)
public class ImportTextAction extends HttpServlet {
	/**
	 * インポートチェック処理結果がfalseの場合呼び出す処理
	 * Textファイルのインポートを行い、取得した内容をリターンする
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		// json型の値を画面から受け取る
		String fileName = request.getParameter("fileName");
		Part textFile = request.getPart("file");

		try {
			ImportTextLogicCheck importTextLogicCheck = new ImportTextLogicCheck();
			List<String> errorList = new ArrayList<>();
			// エラーチェックを行う
			errorList = importTextLogicCheck.checkImportText(fileName);

			// エラーが無かった場合
			if (errorList.size() == 0) {

				ImportTextLogic importTextLogic = new ImportTextLogic();
				// インポート処理を呼び出す
				List<WorkScheduleDTO> workScheduleDTOLIst = importTextLogic.importTextWS(textFile);

				List<String> eroorList = new ArrayList<>();
				// エラーチェック処理を呼び出す
				eroorList = importTextLogic.importTextWSCheck(workScheduleDTOLIst);

				/*
				 * エラーが無かった場合、勤務表リストDTOを返す
				 * エラーがあった場合、エラーリストを返す
				 */
				if (eroorList.size() == 0) {
					
					WorkScheduleListDTO workScheduleListDTO = new WorkScheduleListDTO();
					workScheduleListDTO.setWorkScheduleDTOList(workScheduleDTOLIst);
					
					// エラー文を返す
					ObjectMapper mapper = new ObjectMapper();
					PrintWriter out = response.getWriter();
					String workScheduleJisonList = "";
					//勤務表リストDTOをString型にキャスト
					workScheduleJisonList = mapper.writeValueAsString(workScheduleListDTO);
					//勤務表リストDTOを返す
					out.write(workScheduleJisonList);
					out.close();
				} else {

					// エラー文を返す
					ObjectMapper mapper = new ObjectMapper();
					PrintWriter out = response.getWriter();
					String errorJsonList = "";
					//エラーリストをString型にキャスト
					errorJsonList = mapper.writeValueAsString(errorList);
					//エラーリストを返す
					out.write(errorJsonList);
					out.close();
				}

			} else {

				// エラー文を返す
				ObjectMapper mapper = new ObjectMapper();
				PrintWriter out = response.getWriter();
				String errorJsonList = "";
				//エラーリストをString型にキャスト
				errorJsonList = mapper.writeValueAsString(errorList);
				//エラーリストを返す
				out.write(errorJsonList);
				out.close();
			}
		} catch (Exception e) {
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
