package jp.co.vaile.nerva.masterDepartment.searchDepartment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.commonprocess.CheckMasterResult;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;

@WebServlet("/SearchDepartmentMstAction")
public class SearchDepartmentMstAction extends HttpServlet {

	/**
	 * 検索ボタンを押下した時の処理。所属組織検索処理を呼び出す。
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// json型の値を画面から受け取る
		String departmentId = request.getParameter("json[departmentId]");

		String departmentName = request.getParameter("json[departmentName]");

		// 入力情報をDTOに格納する
		SearchDepartmentDTO searchDepartmentDTO = new SearchDepartmentDTO();
		searchDepartmentDTO.setDepartmentId(departmentId);
		searchDepartmentDTO.setDepartmentName(departmentName);
		SearchDepartmentLogicCheck searchDepartmentLogicCheck = new SearchDepartmentLogicCheck();
		SearchDepartmentMstLogic searchDepartmentMstLogic = new SearchDepartmentMstLogic();

		try {
			// エラーリストのチェックをする
			List<String> errorList = new ArrayList<String>();

			errorList = searchDepartmentLogicCheck.checkSearchDepartment(searchDepartmentDTO);

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
				// 3-2.エラーが無い場合、4に進む。
			} else {
				// 4.入力情報を引数に所属組織マスタメンテナンス検索処理を呼び出す。
				List<SearchDepartmentDTO> searchDepartmentDTOList;

				searchDepartmentDTOList = searchDepartmentMstLogic.searchDepartmentMstLogic(searchDepartmentDTO);

				// 4-1.所属組織マスタメンテナンス検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。/
				if (searchDepartmentDTOList.size() == 0) {
					CheckMasterResult checkMasterResult = new CheckMasterResult();
					errorList.add(checkMasterResult.checkMasterResult(CommonConstants.DEPARTMENT_MASTER));

					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					out.close();
					// 4-2.所属組織マスタメンテナンス検索結果DTOリストの中身がある場合、5に進む。
				} else {
					// 5.所属組織マスタメンテナンス検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す。
					SearchDepartmentListDTO searchResultDepartmentListDTO = new SearchDepartmentListDTO();
					searchResultDepartmentListDTO.setSearchDepartmentDTOList(searchDepartmentDTOList);
					String searchDepartmentDTOString = mapper.writeValueAsString(searchResultDepartmentListDTO);
					out.print(searchDepartmentDTOString);
					// クローズ
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

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}
