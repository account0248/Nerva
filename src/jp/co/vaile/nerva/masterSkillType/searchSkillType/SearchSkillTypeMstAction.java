package jp.co.vaile.nerva.masterSkillType.searchSkillType;

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

@WebServlet( "/SearchSkillTypeMstAction" )
public class SearchSkillTypeMstAction extends HttpServlet {

	/**
	 * 検索ボタン押下後の処理。 スキル種別の検索処理を呼びだす。
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		
		//json型の値を画面から受け取る
		//1 スキル種別マスタメンテナンス画面内検索フォームに入力された値を受け取る。
		String skillTypeId = request.getParameter("json[skillTypeId]");
		String skillTypeName = request.getParameter("json[skillTypeName]");
		String yearsDateOfAcquisition = request.getParameter("json[yearsDateOfAcquisition]");
		
		//入力情報をDTOに格納する
		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO(skillTypeId, skillTypeName, yearsDateOfAcquisition);
		//エラーチェックを行うクラスのインスタンス生成
		SearchSkillTypeLogicCheck searchSkillTypeLogicCheck = new SearchSkillTypeLogicCheck();
		//検索処理を呼び出すインスタンスを生成
		SearchSkillTypeMstLogic searchSkillTypeMstLogic = new SearchSkillTypeMstLogic();
		
		try {
			//エラーメッセージを格納するリストを生成
			List<String> errorList = new ArrayList<String>();
			//2 入力情報を引数にエラーチェック処理を呼び出す。
			errorList = searchSkillTypeLogicCheck.checkSearchSkillType(searchSkillTypeDTO);

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			//2-1 エラーがあった場合,エラーメッセージをJSONの値で返す。
			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				//クローズ
				out.close();
			//2-2 エラーが無い場合、3に進む。
			} else {
				List<SearchSkillTypeDTO> searchSkillTypeDTOList = new ArrayList<SearchSkillTypeDTO>();
				//3 入力情報を引数に検索処理を呼び出す。
				searchSkillTypeDTOList = searchSkillTypeMstLogic.searchSkillTypeMstLogic(searchSkillTypeDTO);

				//4	検索結果のエラーチェックを行う。
				//4-1 検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。
				if (searchSkillTypeDTOList.size() == 0) {
					//検索結果0件のエラーメッセージを格納
					CheckMasterResult checkMasterResult = new CheckMasterResult();
					errorList.add(checkMasterResult.checkMasterResult(CommonConstants. SKILL_TYPE_MASTER));
					
					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					//クローズ
					out.close();
				//4-2 検索結果DTOリストの中身がある場合、5に進む。
				} else {
					//5.検索結果DTOリストをセッションスコープに格納し、JSONで情報を返す。
					SearchSkillTypeListDTO searchSkillTypeListDTO = new SearchSkillTypeListDTO();
					searchSkillTypeListDTO.setSearchSkillTypeDTOList(searchSkillTypeDTOList);
					//検索結果をセッションに格納する
					session.setAttribute("searchSkillTypeDTOList",searchSkillTypeDTOList);

					String searchSkillTypeDTOString = mapper.writeValueAsString(searchSkillTypeListDTO);
					out.print(searchSkillTypeDTOString);
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
