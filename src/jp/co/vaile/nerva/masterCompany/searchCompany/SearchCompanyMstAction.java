package jp.co.vaile.nerva.masterCompany.searchCompany;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

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

@WebServlet( "/SearchCompanyMstAction" )
public class SearchCompanyMstAction extends HttpServlet {
	/**
	 * 検索ボタンを押下した時の処理。
	 * 所属会社検索処理を呼び出す。
	 * @param HttpServletRequest request, HttpServletResponse response
	 * @return
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// 1.所属会社マスタメンテナンス画面内検索フォームに入力された値を受け取る。
		String companyId = request.getParameter("json[companyId]");
		String companyName = request.getParameter("json[companyName]");
		String companyGroup = request.getParameter("json[companyGroup]");
		String companyCode = request.getParameter("json[companyCode]");
		HttpSession session = request.getSession();
		CheckMasterResult checkMasterResult=new CheckMasterResult();
		// 2 所属会社マスタメンテナンスDTOを作成、１で受け取った入力値を格納。入力情報をDTOに格納する
		SearchCompanyDTO searchCompanyDTO = new SearchCompanyDTO();
		searchCompanyDTO.setCompanyId(companyId);
		searchCompanyDTO.setCompanyName(companyName);
		searchCompanyDTO.setCompanyGroup(companyGroup);
		searchCompanyDTO.setCompanyCode(companyCode);
		SearchCompanyLogicCheck searchCompanyLogicCheck = new SearchCompanyLogicCheck();
		SearchCompanyMstLogic searchCompanyMstLogic = new SearchCompanyMstLogic();
		try {
			// 3 所属会社マスタメンテナンスDTOを引数にエラーチェック処理を呼び出す。
			List<String> errorList = new ArrayList<String>();
			errorList = searchCompanyLogicCheck.checkSearchCompany(searchCompanyDTO);
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			// 3-1.エラーがあった場合,エラーメッセージをJSONの値を返す
			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
				// 3-2.エラーが無い場合、3に進む
			} else {
				//4 セッションから所属会社グループ権限を呼び出し、条件分岐を行う。
				String companyPrivilege =(String)session.getAttribute("companyPrivilege");
				String userId =(String)session.getAttribute("userId");
				List<SearchCompanyDTO> searchCompanyDTOList=null;
				//所属会社マスタメンテナンスDTOを引数に検索処理を呼び出す。
					
					searchCompanyDTOList = searchCompanyMstLogic.searchCompanyMstLogic(searchCompanyDTO,companyPrivilege,userId);
					
				// 5. 4で取得したものを引数に検索結果のエラーチェックを行う。
				// 5-1.所属会社マスタメンテナンス検索結果DTOに値がなかった場合、入力内容を保持したまま、エラーメッセージをJSONに返す
				if (searchCompanyDTOList.size() == 0) {
					errorList.add(checkMasterResult.checkMasterResult(COMPANY_NAME));
					String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
					out.print(errorListString);
					out.close();
					//5-2.所属会社マスタメンテナンス検索結果DTOリストの中身がある場合、6に進む
				} else {
					//6.所属会社マスタメンテナンス検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す
					SearchCompanyListDTO searchResultCompanyListDTO =new SearchCompanyListDTO();
					searchResultCompanyListDTO.setSearchCompanyDTOList(searchCompanyDTOList);
					//所属会社マスタメンテナンス検索DTOリストをリクエストスコープに格納し、JSONの値を返す
					String searchCompanyDTOString = mapper.writeValueAsString(searchResultCompanyListDTO);
					out.print(searchCompanyDTOString);
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
