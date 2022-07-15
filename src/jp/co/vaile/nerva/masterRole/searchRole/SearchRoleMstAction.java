package jp.co.vaile.nerva.masterRole.searchRole;
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



/**
 * 1 	担当マスタメンテナンス検索画面に入力された値を受け取る。
 * 2	入力情報を引数にエラーチェック処理を呼び出す。
 * 2-1	エラーがあった場合,エラーメッセージをJSONの値を返す。
 * 2-2  エラーがなかった場合、3に進む。
 * 3    入力情報を引数に検索処理を呼び出す
 * 4    入力情報を引数に検索結果のエラーチェックを行う。
 * 4-1	担当マスタメンテナンス検索結果DTOに値がなかった場合、入力内容を保持したまま、エラーメッセージをJSONに返す。
 * 4-2	担当マスタメンテナンス検索結果DTOに値がある場合、5に進む。
 * 5    担当マスタメンテナンス検索画面に入力された値をセッションスコープに格納する。
 * 6	担当マスタメンテナンス検索DTOリストをリクエストスコープに格納し、JSONの値を返す。
 */
@WebServlet( "/SearchRoleMstAction" )
public class SearchRoleMstAction extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		//json型の値を画面から受け取る
		String roleId = request.getParameter("json[roleId]");
		String roleName = request.getParameter("json[roleName]");

		SearchRoleDTO searchRoleDTO = new SearchRoleDTO(roleId, roleName);

		SearchRoleLogicCheck searchRoleLogicCheck = new SearchRoleLogicCheck();
		SearchRoleMstLogic searchRoleMstLogic = new SearchRoleMstLogic();


		try {
			//エラーリストのチェックをする
			List<String> errorList = new ArrayList<String>();

			errorList = searchRoleLogicCheck.checkSearchRole(searchRoleDTO);

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
				//3-2.エラーが無い場合、4に進む。
			} else {
				//4.入力情報を引数に担当マスタメンテナンス検索処理を呼び出す。
				List<SearchRoleDTO> searchRoleDTOList;
				//try {
				searchRoleDTOList = searchRoleMstLogic.searchRoleMstLogic(searchRoleDTO);
				;
				//4-1.担当マスタメンテナンス検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。/
				if (searchRoleDTOList.size() == 0) {
					CheckMasterResult checkMasterResult = new CheckMasterResult();
					errorList.add(checkMasterResult.checkMasterResult(CommonConstants. ROLE_MASTER));

					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					out.close();
					//4-2.担当マスタメンテナンス検索結果DTOリストの中身がある場合、5に進む。
				} else {
					//5.担当マスタメンテナンス検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す。
					SearchRoleListDTO searchResultRoleListDTO =new SearchRoleListDTO();
					searchResultRoleListDTO.setSearchRoleDTOList(searchRoleDTOList);
					String searchRoleDTOString = mapper.writeValueAsString(searchResultRoleListDTO);
					out.print(searchRoleDTOString);
					//クローズ
					out.close();
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			// TODO 自動生成された catch ブロック
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
