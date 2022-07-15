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

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;
import jp.co.vaile.nerva.managementattedance.WorkScheduleListDTO;
import jp.co.vaile.nerva.managementattedance.WorkscheduleContents;

/**
 * Servlet implementation class ImportExcelAction
 */
@WebServlet("/ImportExcelAction")
@MultipartConfig(fileSizeThreshold = WorkscheduleContents.FILESIZETHRESHOLD, 
maxFileSize = WorkscheduleContents.MAXFILESIZE, location = WorkscheduleContents.IMPORT_PATH)
public class ImportExcelAction extends HttpServlet {
	/**
	 * Excelファイルのインポート処理
	 * インポート内容にエラーが無ければインポート内容を、エラーがあればエラーメッセージを画面に返す
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		// formの値を受け取る
		Part file = request.getPart("file");
		String fileName = request.getParameter("fileName");
		String month = request.getParameter("month");
		int days = Integer.parseInt(request.getParameter("days"));
		String password = request.getParameter("password");

		try {
			ImportExcelLogicCheck importExcelLogicCheck = new ImportExcelLogicCheck();
			List<String> errorList = new ArrayList<>();
			
			//エラーチェック処理呼び出し
			errorList = importExcelLogicCheck.checkImportExcel(fileName, file, password);

			String errorJsonList = "";

			//エラーが無い場合の処理
			if (errorList.size() == 0) {
				// Excelファイルへアクセス
				POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
				EncryptionInfo info = new EncryptionInfo(fileSystem);
				Decryptor excelFile = Decryptor.getInstance(info);
				// インポート処理
				ImportExcelLogic importExcelLogic = new ImportExcelLogic();
				List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();

				// 勤務表内容取得
				workScheduleDTOList = importExcelLogic.importExcelWS(excelFile, fileSystem, password, month, days);

				// エラーチェック
				errorList = importExcelLogic.importExcelWSCheck(workScheduleDTOList);

				//エラーが無い場合の処理
				if (errorList.size() == 0) {
					// 勤務表格納
					ObjectMapper mapper = new ObjectMapper();
					PrintWriter out = response.getWriter();
					String displayWorkScheduleJsonList = "";
					WorkScheduleListDTO workScheduleListDTO = new WorkScheduleListDTO();
					workScheduleListDTO.setWorkScheduleDTOList(workScheduleDTOList);
					//勤務表リストDTOをString型にキャスト
					displayWorkScheduleJsonList = mapper.writeValueAsString(workScheduleListDTO);
					//勤務表リストを返す
					out.write(displayWorkScheduleJsonList);
					out.close();
				} else {
					// エラーリスト格納
					ObjectMapper mapper = new ObjectMapper();
					PrintWriter out = response.getWriter();
					//エラーリストをString型にキャスト
					errorJsonList = mapper.writeValueAsString(errorList);
					//エラーリストを返す
					out.write(errorJsonList);
					out.close();
				}

			} else {
				// エラーリストを格納
				ObjectMapper mapper = new ObjectMapper();
				PrintWriter out = response.getWriter();
				//エラーリストをString型にキャスト
				errorJsonList = mapper.writeValueAsString(errorList);
				//エラーリストを返す
				out.write(errorJsonList);
				out.close();

			}
		} catch (Exception e) {
			
			e.printStackTrace();
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

}
