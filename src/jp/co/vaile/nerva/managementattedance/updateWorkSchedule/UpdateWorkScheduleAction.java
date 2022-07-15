package jp.co.vaile.nerva.managementattedance.updateWorkSchedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

/**
 * Servlet implementation class UpdateWorkScheduleAction
 */
@WebServlet("/UpdateWorkScheduleAction")
public class UpdateWorkScheduleAction extends HttpServlet {
	/**
	 * 更新ボタン押下時の処理
	 * 入力内容をもとに勤怠情報テーブルを更新する
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();

		// json型の値を画面から受け取る
		String year = request.getParameter("json[year]");
		String month = request.getParameter("json[month]");
		String employeeId = request.getParameter("json[employeeId]");
		String userId = (String) session.getAttribute("userId");
		String[] start = mapper.readValue(request.getParameter("json[start]"), String[].class);
		String[] end = mapper.readValue(request.getParameter("json[end]"), String[].class);
		String[] rest = mapper.readValue(request.getParameter("json[rest]"), String[].class);
		String[] holidayWorking = mapper.readValue(request.getParameter("json[holidayWork]"), String[].class);
		String[] holiday = mapper.readValue(request.getParameter("json[holiday]"), String[].class);
		String[] paiddays = mapper.readValue(request.getParameter("json[holidayDays]"), String[].class);
		String[] paidtime = mapper.readValue(request.getParameter("json[holidayTime]"), String[].class);
		String[] latetime = mapper.readValue(request.getParameter("json[lateTime]"), String[].class);
		String[] remarks = mapper.readValue(request.getParameter("json[remarks]"), String[].class);
		
		// 勤務表DTOにセットし、リストに追加していく
		List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();
		for (int i = 0; i < start.length; i++) {
			WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();
			workScheduleDTO.setStartTime(start[i]);
			workScheduleDTO.setEndTime(end[i]);
			workScheduleDTO.setRest(rest[i]);
			workScheduleDTO.setHolidayWorking(holidayWorking[i]);
			workScheduleDTO.setHolidayDays(paiddays[i]);
			workScheduleDTO.setHolidayPeriod(holiday[i]);
			workScheduleDTO.setHolidayTime(paidtime[i]);
			workScheduleDTO.setLateTime(latetime[i]);
			workScheduleDTO.setRemarks(remarks[i]);
			workScheduleDTOList.add(workScheduleDTO);
		}
		UpdateWSDTO updateWSDTO = new UpdateWSDTO();
		updateWSDTO.setYear(year);
		updateWSDTO.setMonth(month);
		updateWSDTO.setEmployeeId(employeeId);
		updateWSDTO.setWorkScheduleDTOList(workScheduleDTOList);
		updateWSDTO.setUserId(userId);

		
		List<String> errorList = new ArrayList<>();
		UpdateWSLogicCheck updateWSLogicCheck = new UpdateWSLogicCheck();
		try {
			// エラーチェック
			errorList = updateWSLogicCheck.checkUpdateWS(updateWSDTO.getWorkScheduleDTOList());

			/*
			 * エラーが無かった場合、更新処理を呼び出す
			 * エラーがあった場合、エラーリストを返す
			 */
			if (errorList.size() == 0) {
				// 更新処理
				UpdateWSLogic updateWSLogic = new UpdateWSLogic();
				updateWSLogic.updateWorkSchedule(updateWSDTO);

				PrintWriter out = response.getWriter();
				out.write("{}");
				out.close();

			} else {
				
				PrintWriter out = response.getWriter();
				String errorJsonList = "";
				//エラーリストをString型にキャスト
				errorJsonList = mapper.writeValueAsString(errorList);
				// エラー文を返す
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
