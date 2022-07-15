package jp.co.vaile.nerva.registEmployee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistEmpCheckAction extends HttpServlet {
	RegistEmpPageDTO registEmpPageDTO;
	RegistSkillInfoPageDTO registSkillInfoPageDTO;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		returnInputDTO(request);
		//2.入力情報のエラーをチェックする
		RegistEmpCheckLogic registEmpCheckLogic = new RegistEmpCheckLogic();
		try {
			List<String> errorList = registEmpCheckLogic.inputCheckRegistEmp(registEmpPageDTO, registSkillInfoPageDTO);
			//2-1.エラーがあった場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。
			//2-2.エラーが無い場合、3に進む。
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			if (errorList.size() != 0) {
				//JSONで情報を返す
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.write(errorListString);
				return;
			}
			HttpSession session = request.getSession();
			//入力情報をセッションに格納
			session.setAttribute("registEmpPageDTO", registEmpPageDTO);
			session.setAttribute("registSkillInfoPageDTO", registSkillInfoPageDTO);
			String successResult = "{ }";
			out.write(successResult);
			//クローズ
			out.close();
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String errorListString = "";
			errorListString = mapper.writeValueAsString("");
			out.write(errorListString);
			String successResult = "{ }";
			out.write(successResult);
			out.close();
			e.printStackTrace();
		}
	}

	/**
	 * 入力情報を受け取り、DTOにする
	 * @param request
	 */
	private void returnInputDTO(HttpServletRequest request) {
		//1.登録画面で記載された入力情報を受け取る。
		String employeeId = request.getParameter("json[employeeId]");
		String employeeName = request.getParameter("json[employeeName]");
		String sex = request.getParameter("json[sex]");
		String birthDate = request.getParameter("json[birthDate]");
		String companyId = request.getParameter("json[companyId]");
		String office = request.getParameter("json[office]");
		String departmentId = request.getParameter("json[departmentId]");
		String postId = request.getParameter("json[postId]");
		String postalCode = request.getParameter("json[postalCode]");
		String address = request.getParameter("json[address]");
		String phoneNumber = request.getParameter("json[phoneNumber]");
		String mail = request.getParameter("json[mail]");
		String[] skillType = request.getParameterValues("json[skillType][]");
		String[] skillDetail = request.getParameterValues("json[skillDetail][]");
		String[] experienceYears = request.getParameterValues("json[experienceYears][]");
		String[] acquisitionYearMonth = request.getParameterValues("json[acquisitionYearMonth][]");
		registEmpPageDTO = new RegistEmpPageDTO(employeeId, employeeName, sex, birthDate, companyId,
				office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		registSkillInfoPageDTO = new RegistSkillInfoPageDTO(skillType, skillDetail,
				experienceYears, acquisitionYearMonth);
	}

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}
