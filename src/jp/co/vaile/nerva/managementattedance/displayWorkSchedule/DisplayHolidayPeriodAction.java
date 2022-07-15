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

import jp.co.vaile.nerva.managementattedance.HolidayPeriodDTO;
import jp.co.vaile.nerva.managementattedance.HolidayPeriodListDTO;

/**
 * Servlet implementation class DisplayHolidayPeriodAction
 */
@WebServlet("/DisplayHolidayPeriodAction")
public class DisplayHolidayPeriodAction extends HttpServlet {
	/**
	 * 表示ボタン押下後、勤務表フォーマット表示時に呼び出す
	 * 休暇期間等マスタの内容を取得しリターンする
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		DisplayHolidayPeriodLogic displayHolidayPeriodLogic = new DisplayHolidayPeriodLogic();
		List<HolidayPeriodDTO> holidayPeriodDTOList;
		try {
			// 休暇均等検索処理呼び出し
			holidayPeriodDTOList = displayHolidayPeriodLogic.displayHolidayPeriod();
			//リストDTOに格納
			HolidayPeriodListDTO holidayPeriodListDTO = new HolidayPeriodListDTO();
			holidayPeriodListDTO.setHolidayPeriodDTOList(holidayPeriodDTOList);

			//処理結果セット
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String displayHolidayPeriodJsonList = "";
			displayHolidayPeriodJsonList = mapper.writeValueAsString(holidayPeriodListDTO);
			//休暇期間等DTOリストを返す
			out.print(displayHolidayPeriodJsonList);
			out.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			// 例外が発生した場合、エラーページに遷移する
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
