package jp.co.vaile.nerva.managementattedance.displayWorkSchedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;
import jp.co.vaile.nerva.managementattedance.WorkScheduleListDTO;

/**
 * Servlet implementation class DisplayAction
 */
@WebServlet("/DisplayAction")
public class DisplayAction extends HttpServlet {
	/**
	 * 表示ボタンを押下した時の処理
	 * 勤務表表示処理を呼び出し表示結果を画面に返す
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		// json型の値を画面から受け取る
		String year = request.getParameter("json[year]");
		String month = request.getParameter("json[month]");
		String employeeId = request.getParameter("json[employeeId]");
		DisplayWSDTO displayWSDTO = new DisplayWSDTO();
		displayWSDTO.setYear(year);
		displayWSDTO.setMonth(month);
		displayWSDTO.setEmployeeId(employeeId);

		// 勤務表表示取得処理
		DisplayWorkScheduleLogic displayWorkScheduleLogic = new DisplayWorkScheduleLogic();
		List<WorkScheduleDTO> workScheduleDTOList;
		try {
			// DBに格納されている勤務表を取得する
			workScheduleDTOList = displayWorkScheduleLogic.displayWorkSchedule(displayWSDTO);

			//処理結果セット
			WorkScheduleListDTO workScheduleListDTO = new WorkScheduleListDTO();
			workScheduleListDTO.setWorkScheduleDTOList(workScheduleDTOList);
			
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String displayWorkScheduleJsonList = "";
			
			// String型にキャスト
			displayWorkScheduleJsonList = mapper.writeValueAsString(workScheduleListDTO);
			out.write(displayWorkScheduleJsonList);
			out.close();

		} catch (Exception e) {
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
