package jp.co.vaile.nerva.masterPost.searchPost;

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

@WebServlet("/SearchPostMstAction")
public class SearchPostMstAction extends HttpServlet {

	/**
	 * 検索ボタンを押下した時の処理。役職検索処理を呼び出す。
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 **/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// json型の値を画面から受け取る
		String postId = request.getParameter("json[postId]");

		String postName = request.getParameter("json[postName]");

		// 入力情報をDTOに格納する
		SearchPostDTO searchPostDTO = new SearchPostDTO();
		searchPostDTO.setPostId(postId);
		searchPostDTO.setPostName(postName);
		SearchPostLogicCheck searchPostLogicCheck = new SearchPostLogicCheck();
		SearchPostMstLogic searchPostMstLogic = new SearchPostMstLogic();

		try {
			// エラーリストのチェックをする
			List<String> errorList = new ArrayList<String>();

			errorList = searchPostLogicCheck.checkSearchPost(searchPostDTO);

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			if (errorList.size() != 0) {

				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
				// 3-2.エラーが無い場合、4に進む。
			} else {
				// 4.入力情報を引数に役職マスタメンテナンス検索処理を呼び出す。
				List<SearchPostDTO> searchPostDTOList;
				// try {
				searchPostDTOList = searchPostMstLogic.searchPostMstLogic(searchPostDTO);
				;
				// 4-1.役職マスタメンテナンス検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。/
				if (searchPostDTOList.size() == 0) {
					CheckMasterResult checkMasterResult = new CheckMasterResult();
					errorList.add(checkMasterResult.checkMasterResult(CommonConstants.POST_MASTER));

					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					out.close();
					// 4-2.役職マスタメンテナンス検索結果DTOリストの中身がある場合、5に進む。
				} else {
					// 5.役職マスタメンテナンス検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す。
					SearchPostListDTO searchResultPostListDTO = new SearchPostListDTO();
					searchResultPostListDTO.setSearchPostDTOList(searchPostDTOList);
					String searchPostDTOString = mapper.writeValueAsString(searchResultPostListDTO);
					out.print(searchPostDTOString);
					// クローズ
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

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}
