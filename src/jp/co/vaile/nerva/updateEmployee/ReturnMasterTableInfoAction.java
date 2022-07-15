package jp.co.vaile.nerva.updateEmployee;

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

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.ContractTypeInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.RoleInfo;

/**
 * Servlet implementation class ReturnMasterTableInfoAction
 */
@WebServlet("/ReturnMasterTableInfoAction")
public class ReturnMasterTableInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ExistCheck roleInfo = new RoleInfo();
	ExistCheck contractTypeInfo = new ContractTypeInfo();

	/**
	 *業務経験欄で追加ボタンが押下された時、マスタ情報をJSON形式で返す。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			//共通処理のマスタテーブル情報取得処理を呼び出す。
			List<FetchAnyMasterTableDTO> roleList = new ArrayList<FetchAnyMasterTableDTO>();
			roleList = roleInfo.fetchMasterTableList();
			List<FetchAnyMasterTableDTO> contractTypeList = new ArrayList<FetchAnyMasterTableDTO>();
			contractTypeList = contractTypeInfo.fetchMasterTableList();
			//戻り値をマップに格納する。
			Map<String, List<FetchAnyMasterTableDTO>> masterTableInfoMap = new HashMap<String, List<FetchAnyMasterTableDTO>>();
			masterTableInfoMap.put("roleList", roleList);
			masterTableInfoMap.put("contractTypeList", contractTypeList);
			ObjectMapper mapper = new ObjectMapper();
			String masterTableInfoData = mapper.writeValueAsString(masterTableInfoMap);
			PrintWriter out = response.getWriter();
			//取得したスキル種別情報をJSONで返し、処理を終了する。
			out.print(masterTableInfoData);
			out.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			//例外が発生した場合、エラーページに遷移する。
			ObjectMapper mapper = new ObjectMapper();
			String masterTableInfoData = mapper.writeValueAsString("");
			PrintWriter out = response.getWriter();
			out.print(masterTableInfoData);
		}
	}

}
