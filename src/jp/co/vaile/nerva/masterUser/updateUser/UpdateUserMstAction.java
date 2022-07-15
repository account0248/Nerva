package jp.co.vaile.nerva.masterUser.updateUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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

import jp.co.vaile.nerva.commonprocess.ConvertPassword;
import jp.co.vaile.nerva.masterUser.searchUser.SearchResultUserDTO;
import jp.co.vaile.nerva.masterUser.searchUser.SearchResultUserListDTO;
import jp.co.vaile.nerva.masterUser.searchUser.SearchUserDTO;
import jp.co.vaile.nerva.masterUser.searchUser.SearchUserMstLogic;

@WebServlet("/UpdateUserMstAction")
public class UpdateUserMstAction extends HttpServlet {

	/**
	 * ユーザーマスタメンテナンス画面で入力された値を受け取り、エラーチェックや更新処理を呼び出す。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();

		// json型の値を画面から受け取る。
		String[] userId = mapper.readValue(request.getParameter("json[userId]"), String[].class);
		String[] userName = mapper.readValue(request.getParameter("json[userName]"), String[].class);
		String[] password = mapper.readValue(request.getParameter("json[password]"), String[].class);
		String[] post = mapper.readValue(request.getParameter("json[post]"), String[].class);
		String[] adminFlg = mapper.readValue(request.getParameter("json[adminFlg]"), String[].class);
		String loginUserId = (String) session.getAttribute("userId");

		// セッションの既存値リストを取得。
		SearchResultUserListDTO serchResultUserListDTO = (SearchResultUserListDTO) session.getAttribute("origin");

		// 既存値DTOのListを新規に宣言、初期化。
		List<SearchResultUserDTO> originList = serchResultUserListDTO.getSearchResultUserDTOList();

		// エラーチェックを行うクラスのインスタンス生成。
		UpdateUserCheckAction updateUserCheckAction = new UpdateUserCheckAction();

		// エラーメッセージをを格納するインスタンスを生成。
		List<String> errorList = new ArrayList<String>();

		// ハッシュ値を格納するインスタンス生成。
		ConvertPassword convertPassword = new ConvertPassword();

		// 既存値のユーザーID数だけ繰り返し処理。
		for (int i = 0; i < originList.size(); i++) {

			// 編集されたユーザーID数だけ編集値をチェック。
			for (int j = 0; j < userId.length; j++) {

				// 既存値のユーザーIDが編集したユーザーのユーザーIDと合致したときエラー処理をするか分岐。
				if (originList.get(i).getUserId().equals(userId[j])) {

					// ユーザー名が既存値と編集値が異なる場合、エラーチェックの処理を呼び出す。
					if (!(originList.get(i).getUserName().equals((userName)[j]))) {

						// エラーリストに格納。
						errorList = updateUserCheckAction.checkUpdateUserNameErrMsg(userName[j]);
					}
					// パスワードが既存値と編集値が異なる場合、エラーチェックの処理を呼び出す。
					if (!(originList.get(i).getPassword().equals((password)[j]))) {
						try {

							// エラーリストに格納。
							errorList = updateUserCheckAction.checkUpdatePasswordErrMsg(password[j]);

							// パスワードをハッシュ化。
							password[j] = convertPassword.hashPassword(password[j]);

						} catch (ClassNotFoundException | SQLException | ParseException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}

		}

		// エラーリストの中身が存在する場合、出力する。
		PrintWriter out = response.getWriter();

		if (errorList.size() != 0) {
			String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
			out.print(errorListString);
			out.close();

		} else {

			// 更新用DTOのインスタンスを作成。
			UpdateUserDTO updateUserDTO = new UpdateUserDTO();

			// 更新用DTOに入力値を格納する。
			updateUserDTO.setTargetUserId(userId);
			updateUserDTO.setUserName(userName);
			updateUserDTO.setPassword(password);
			updateUserDTO.setPost(post);
			updateUserDTO.setAdminFlg(adminFlg);
			updateUserDTO.setUpdateUserId(loginUserId);

			UpdateUserMstLogic updateUserMstLogic = new UpdateUserMstLogic();
			try {
				// DTOを引数に更新処理を呼び出す。
				updateUserMstLogic.updateUserMstLogic(updateUserDTO);

				// 更新後にセッションの値を更新するためインスタンスを生成。
				SearchUserDTO searchUserDTO = new SearchUserDTO();

				// セッション情報から検索入力情報を取得。
				searchUserDTO = (SearchUserDTO) session.getAttribute("searchCondition");

				// 検索処理を呼び出すインスタンスを生成。
				SearchUserMstLogic searchUserMstLogic = new SearchUserMstLogic();

				// 検索入力情報を引数に検索処理を呼び出し、既存値リストに格納。
				originList = searchUserMstLogic.searchUserMstLogic(searchUserDTO);

				// 検索結果を検索結果DTOリスト型DTOに格納。
				SearchResultUserListDTO searchResultUserListDTO = new SearchResultUserListDTO();
				searchResultUserListDTO.setSearchResultUserDTOList(originList);

				// 検索結果をセッションに再格納する。
				session.setAttribute("origin", searchResultUserListDTO);

			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			// クローズ
			out.print("{}");
			out.close();
		}
	}

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}
