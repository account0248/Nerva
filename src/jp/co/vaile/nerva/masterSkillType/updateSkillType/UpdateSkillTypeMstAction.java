package jp.co.vaile.nerva.masterSkillType.updateSkillType;

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

import jp.co.vaile.nerva.masterSkillType.searchSkillType.SearchSkillTypeDTO;
import jp.co.vaile.nerva.masterSkillType.searchSkillType.SearchSkillTypeMstLogic;

@WebServlet("/UpdateSkillTypeMstAction")
public class UpdateSkillTypeMstAction extends HttpServlet {
	/**
	 * 更新するボタン押下後の処理。 スキル種別の更新処理を呼びだす。
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();

		// json型の値を画面から受け取る
		// スキル種別マスタメンテナンス画面内検索フォームに入力された値を受け取る。
		String[] skillTypeId = mapper.readValue(request.getParameter("json[skillTypeId]"), String[].class);
		String[] skillTypeName = mapper.readValue(request.getParameter("json[skillTypeName]"), String[].class);
		String[] yearsDateOfAcquisition = mapper.readValue(request.getParameter("json[yearsDateOfAcquisition]"),
				String[].class);
		// ログイン者ID（セッション）取得
		String userId = (String) session.getAttribute("userId");

		// 検索結果（セッション）取得
		List<SearchSkillTypeDTO> searchSkillTypeDTOList = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList = (List<SearchSkillTypeDTO>) session.getAttribute("searchSkillTypeDTOList");

		boolean[] check = new boolean[skillTypeName.length];
		for (int i = 0; i < check.length; i++) {
			check[i] = true;
		}

		// 検索結果（セッション）で条件分岐
		for (SearchSkillTypeDTO searchSkillTypeDTOValue : searchSkillTypeDTOList) {
			for (int i = 0; i < skillTypeName.length; i++) {
				// スキル種別IDが同じかつスキル種別名が同じとき
				if (searchSkillTypeDTOValue.getSkillTypeId().equals(skillTypeId[i])
						&& searchSkillTypeDTOValue.getSkillTypeName().equals(skillTypeName[i])) {
					check[i] = false;
				}
			}
		}

		// 入力情報をDTOに格納する
		UpdateSkillTypeDTO updateSkillTypeDTO = new UpdateSkillTypeDTO(skillTypeId, skillTypeName,
				yearsDateOfAcquisition, userId);

		try {
			// エラーチェックを行うクラスのインスタンス生成
			UpdateSkillTypeCheckAction updateSkillTypeCheckAction = new UpdateSkillTypeCheckAction();
			// エラーメッセージを格納するリストを生成
			List<String> errorList = new ArrayList<String>();

			// エラーリストのチェックをする
			errorList = updateSkillTypeCheckAction.checkUpdateSkillType(updateSkillTypeDTO, check);

			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				// クローズ
				out.close();
				// 3-2.エラーが無い場合、4に進む。
			} else {
				// 4.入力情報を引数に更新処理を呼び出す。
				UpdateSkillTypeMstLogic updateSkillTypeMstLogic = new UpdateSkillTypeMstLogic();
				updateSkillTypeMstLogic.updateSkillTypeMstLogic(updateSkillTypeDTO);
				// 全項目空文字の検索DTOインスタンス生成
				SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("", "", "");
				// 検索処理を呼び出すインスタンスを生成
				SearchSkillTypeMstLogic searchSkillTypeMstLogic = new SearchSkillTypeMstLogic();
				// 入力情報(セッション)を引数に検索処理を呼び出す。
				searchSkillTypeDTOList = searchSkillTypeMstLogic.searchSkillTypeMstLogic(searchSkillTypeDTO);
				// 検索結果をセッションに格納する
				session.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList);
				out.print("{}");
				// クローズ
				out.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
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
