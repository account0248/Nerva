package jp.co.vaile.nerva.masterUser.searchUser;

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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.commonprocess.CheckMasterResult;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;

/**
 * 1.ユーザーマスタメンテナンス画面内検索フォームに入力された値を受け取る。 2.入力情報を引数にエラーチェック処理を呼び出す。
 * 2-1.エラーがあった場合、エラーメッセージをJSONの値で返す。 2-2.エラーがなかった場合、3に進む。 3.入力情報を引数に検索処理を呼び出す。
 * 4.入力情報を引数に検索結果のエラーチェックを行う。
 * 4-1.ユーザー検索結果DTOに値がなかった場合、入力内容を保持したまま、エラーメッセージをJSONの値で返す。
 * 4-2.ユーザー検索結果DTOに値がある場合、5に進む。 5.ユーザー検索DTO結果のリストをリクエストスコープに格納し、JSONの値で返す。
 */
@WebServlet("/SearchUserMstAction")
public class SearchUserMstAction extends HttpServlet {

	/**
	 * ユーザーマスタメンテナンス画面で入力された値を受け取り、エラーチェックや検索処理を呼び出し画面に出力する。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();

		// json型の値を画面から受け取る。
		String userId = request.getParameter("json[userId]");
		String userName = request.getParameter("json[userName]");
		String company = request.getParameter("json[company]");
		String post = request.getParameter("json[post]");
		String adminFlg = request.getParameter("json[adminFlg]");
		
		// セッションからログインユーザーの所属会社グループ権限と所属会社IDを受け取る。
		String companyGroup = (String)session.getAttribute("companyPrivilege");
		String loginUserCompanyId = (String)session.getAttribute("companyId");

		// 入力情報をDTOに格納する。
		SearchUserDTO searchUserDTO = new SearchUserDTO();
		searchUserDTO.setUserId(userId);
		searchUserDTO.setUserName(userName);
		searchUserDTO.setCompany(company);
		searchUserDTO.setPost(post);
		searchUserDTO.setAdminFlg(adminFlg);
		searchUserDTO.setCompanyGroup(companyGroup);
		searchUserDTO.setLoginUserCompanyId(loginUserCompanyId);
		
		SearchUserLogicCheck searchUserLogicCheck = new SearchUserLogicCheck();
		SearchUserMstLogic searchUserMstLogic = new SearchUserMstLogic();

		try {

			// エラーリストのチェックをする。
			List<String> errorList = new ArrayList<String>();
			errorList = searchUserLogicCheck.checkSearchUser(searchUserDTO);

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			// エラーリストの中身が存在する場合、エラーメッセージを画面に表示。
			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();

			} else {

				// 入力情報を引数にユーザーマスタメンテナンス検索処理を呼び出す。
				List<SearchResultUserDTO> searchResultUserDTOList;
				searchResultUserDTOList = searchUserMstLogic.searchUserMstLogic(searchUserDTO);

				// ユーザーマスタメンテナンス検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。
				if (searchResultUserDTOList.size() == 0) {
					CheckMasterResult checkMasterResult = new CheckMasterResult();
					errorList.add(checkMasterResult.checkMasterResult(CommonConstants.USER_MASTER));
					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					out.close();

					// ユーザーマスタメンテナンス検索結果DTOリストの中身がある場合、以下に進む。
				} else {

					// ユーザーマスタメンテナンス検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す。
					SearchResultUserListDTO searchResultUserListDTO = new SearchResultUserListDTO();
					searchResultUserListDTO.setSearchResultUserDTOList(searchResultUserDTOList);
					String searchResultUserDTOString = mapper.writeValueAsString(searchResultUserListDTO);
					out.print(searchResultUserDTOString);

					// セッションにユーザーマスタメンテナンス検索結果DTOリストを既存値リストとして格納する。
					session.setAttribute("origin", searchResultUserListDTO);

					// セッションに検索条件を検索入力情報として格納する。
					session.setAttribute("searchCondition", searchUserDTO);

					// クローズ
					out.close();
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
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
