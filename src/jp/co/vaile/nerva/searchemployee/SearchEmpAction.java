package jp.co.vaile.nerva.searchemployee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * 1 	従業員検索画面に入力された値を受け取る。
 * 2	入力情報を引数にエラーチェック処理を呼び出す。
 * 2-1	エラーがあった場合,エラーメッセージをJSONの値を返す。
 * 2-2  エラーがなかった場合、3に進む。
 * 3    入力情報を引数に検索処理を呼び出す
 * 4    入力情報を引数に検索結果のエラーチェックを行う。
 * 4-1	従業員検索結果DTOに値がなかった場合、入力内容を保持したまま、エラーメッセージをJSONに返す。
 * 4-2	従業員検索結果DTOに値がある場合、5に進む。
 * 5    従業員検索画面に入力された値をセッションスコープに格納する。
 * 6	従業員検索DTOリストをリクエストスコープに格納し、JSONの値を返す。
 */

public class SearchEmpAction extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		//json型の値を画面から受け取る

		String employeeId = request.getParameter("json[employeeId]");

		String employeeName = request.getParameter("json[employeeName]");

		String projectName = request.getParameter("json[projectName]");

		String teamName = request.getParameter("json[teamName]");

		String companyName = request.getParameter("json[companyName]");

		String teamManager = request.getParameter("json[teamManager]");

		String skillFiltering = request.getParameter("json[skillFiltering]");

		//ログイン者ID（セッション）取得
		HttpSession session = request.getSession();

		//入力情報をDTOに格納する
		SearchEmpPageDTO serchEmpPageDTO = new SearchEmpPageDTO(employeeId, employeeName, projectName, teamName, companyName, teamManager, skillFiltering);

		//セッションから所属会社IDと所属会社グループ権限を取得。
		String loginCompanyId = (String) session.getAttribute("companyId");
		String companyPrivilege = (String) session.getAttribute("companyPrivilege");

		String companyId = null;

		SeachCompanyDAO seachCompanyDAO = new SeachCompanyDAO();
		try {
			//所属会社名を引数に所属会社IDを取得。
			companyId = seachCompanyDAO.getCompanyId(companyName);

			SearchEmpLogic serchEmpLogic = new SearchEmpLogic();
			SearchEmpLogicCheck serchEmpLogicCheck = new SearchEmpLogicCheck();

			//エラーリストのチェックをする
			List<String> errorList = new ArrayList<String>();

			errorList = serchEmpLogicCheck.checkSerchEmp(serchEmpPageDTO,loginCompanyId,companyId,companyPrivilege);

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();

			} else {
				//入力情報を引数に従業員検索処理を呼び出す。
				List<SearchEmpDTO> searchEmpDTOList;
				//try {
				searchEmpDTOList = serchEmpLogic.serchEmpLogic(serchEmpPageDTO);

				//従業員検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。/
				if (searchEmpDTOList.size() == 0) {
					errorList.add(serchEmpLogicCheck.checkSerchEmp());

					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					out.close();
					//従業員検索結果DTOリストの中身がある場合、5に進む。
				} else {
					//従業員検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す。
					SearchEmpListDTO seachEmpresultDTO =new SearchEmpListDTO();

					seachEmpresultDTO.setSearchEmpDTOList(searchEmpDTOList);

					//入力情報のDTOをセッションに格納する
					session.setAttribute("searchEmpPageDTO",serchEmpPageDTO);


					String searchEmpDTOString = mapper.writeValueAsString(seachEmpresultDTO);
					out.print(searchEmpDTOString);
					//クローズ
					out.close();
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String errorListString = mapper.writeValueAsString("");
			out.print(errorListString);
			out.close();
			e.printStackTrace();
		}
	}



	private Map<String,List<String>> storeErrorMap(List<String> errorList){
		Map<String,List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}