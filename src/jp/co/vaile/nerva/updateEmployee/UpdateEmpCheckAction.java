package jp.co.vaile.nerva.updateEmployee;

import java.io.IOException;
import java.io.PrintWriter;
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

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;

/**
 * Servlet implementation class UpdateEmpCheckAction
 */
@WebServlet("/UpdateEmpCheckAction")
public class UpdateEmpCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ErrorMsg errorMsg = new ErrorMsg();

	/**
	 *更新ボタンが押下された時、画面の内容をDTOに格納して入力チェックロジックを呼び出す。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		InputCheckUpdateEmpInfoLogic inputCheckUpdateEmpInfoLogic = new InputCheckUpdateEmpInfoLogic();
		InputCheckSkillLogic inputCheckSkillLogic = new InputCheckSkillLogic();
		InputCheckWorkExpLogic inputCheckWorkExpLogic = new InputCheckWorkExpLogic();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		try {
			//従業員情報をDTOにセット
			UpdateEmpInfoDTO updateEmpInfoDTO = null;
			updateEmpInfoDTO = inputEmpInfoContent(request);
			//従業員情報の入力チェックロジックを呼び出す。
			errorMsgMap = errorMsg.errorMsgMapNullCheck(
					inputCheckUpdateEmpInfoLogic.checkEmpInfoContent(updateEmpInfoDTO), errorMsgMap);
			//保有スキル更新欄の行数を取得
			int updateSkillRow = Integer.parseInt(request.getParameter("json[updateSkillRow]"));
			List<UpdateSkillInfoDTO> updateSkillInfoDTOList = null;
			if (updateSkillRow != 0) {
				//保有スキル更新情報をDTOにセット
				updateSkillInfoDTOList = inputUpdateSkillContent(request, updateSkillRow);
				//保有スキル更新用入力チェックロジックを呼び出す。
				errorMsgMap = errorMsg.errorMsgMapNullCheck(
						inputCheckSkillLogic.checkUpdateSkillContent(updateSkillInfoDTOList), errorMsgMap);
			}
			//保有スキル登録欄の行数を取得
			int registSkillRow = Integer.parseInt(request.getParameter("json[registSkillRow]"));
			List<RegistSkillInfoDTO> registSkillInfoDTOList = null;
			if (registSkillRow != 0) {
				//保有スキル登録情報をDTOにセット
				registSkillInfoDTOList = inputRegistSkillContent(request, registSkillRow);
				//保有スキル登録用入力チェックロジックを呼び出す。
				errorMsgMap = errorMsg.errorMsgMapNullCheck(
						inputCheckSkillLogic.checkRegistSkillContent(registSkillInfoDTOList), errorMsgMap);
			}
			//業務経験更新欄の行数を取得
			int updateWorkExpRow = Integer.parseInt(request.getParameter("json[updateWorkExpRow]"));
			List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = null;
			if (updateWorkExpRow != 0) {
				//業務経験更新情報をDTOにセット
				updateEmpWorkExpDTOList = inputUpdateWorkExpContent(request,
						updateWorkExpRow);
				//業務経験更新用入力チェックロジックを呼び出す。
				errorMsgMap = errorMsg.errorMsgMapNullCheck(
						inputCheckWorkExpLogic.checkUpdateWorkExpContent(updateEmpWorkExpDTOList), errorMsgMap);
			}
			//業務経験登録欄の行数を取得
			int registWorkExpRow = Integer.parseInt(request.getParameter("json[registWorkExpRow]"));
			List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = null;
			if (registWorkExpRow != 0) {
				//業務経験登録情報をDTOにセット
				registEmpWorkExpDTOList = inputRegistWorkExpContent(request,session,
						registWorkExpRow);
				//業務経験登録用入力チェックロジックを呼び出す。
				errorMsgMap = errorMsg.errorMsgMapNullCheck(
						inputCheckWorkExpLogic.checkRegistWorkExpContent(registEmpWorkExpDTOList), errorMsgMap);
			}
			//業務経験欄の行数を計算
			int workExpRow = updateWorkExpRow + registWorkExpRow;
			if (workExpRow != 0) {
				//業務経験日付チェック用DTOにセット
				CheckDateEmpWorkExpDTO checkDateEmpWorkExpDTO = inputDateEmpWorkExpContent(request, updateWorkExpRow,
						registWorkExpRow);
				//業務経験日付比較用入力チェックロジックを呼び出す。
				errorMsgMap = errorMsg.errorMsgMapNullCheck(
						inputCheckWorkExpLogic.checkWorkExpDateContent(checkDateEmpWorkExpDTO), errorMsgMap);
			}
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			//エラーメッセージがある場合、エラーメッセージをJSONとして返す。
			if (errorMsgMap.size() != 0) {
				String jsonStr = mapper.writeValueAsString(errorMsgMap);
				out.write(jsonStr);
			}
			//エラーメッセージがない場合、セッションに各DTOを格納する。
			else {
				session.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
				if (updateSkillRow != 0) {
					session.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
				}
				if (registSkillRow != 0) {
					session.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
				}
				if (updateWorkExpRow != 0) {
					session.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
				}
				if (registWorkExpRow != 0) {
					session.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
				}
				String successResult = "{ }";
				out.write(successResult);
			}
			out.close();
		} catch ( Exception e) {
			//例外が発生した場合、エラーページに遷移する。
			ObjectMapper mapper = new ObjectMapper();
			String masterTableInfoData = mapper.writeValueAsString("");
			PrintWriter out = response.getWriter();
			out.print(masterTableInfoData);
		}
	}

	/**
	 * 画面で入力された従業員情報をDTOに格納する。
	 * @param request リクエストスコープ
	 * @return 従業員情報DTO
	 */
	private UpdateEmpInfoDTO inputEmpInfoContent(HttpServletRequest request) {
		//画面で入力された内容を受け取り、DTOにセットする
		String employeeName = request.getParameter("json[employeeInfo][employeeName]");
		String office = request.getParameter("json[employeeInfo][office]");
		String departmentId = request.getParameter("json[employeeInfo][departmentId]");
		String postId = request.getParameter("json[employeeInfo][postId]");
		String postalCode = request.getParameter("json[employeeInfo][postalCode]");
		String address = request.getParameter("json[employeeInfo][address]");
		String phoneNumber = request.getParameter("json[employeeInfo][phoneNumber]");
		String mail = request.getParameter("json[employeeInfo][mail]");
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode,
				address,
				phoneNumber, mail);
		//戻り値を返す。
		return updateEmpInfoDTO;
	}

	/**
	 * 画面で入力されたスキル情報をDTOに格納する。
	 * @param request リクエストスコープ
	 * @param updateSkillRow スキル情報更新欄の行数
	 * @return スキル情報更新用DTOのリスト
	 */
	private List<UpdateSkillInfoDTO> inputUpdateSkillContent(HttpServletRequest request, int updateSkillRow) {
		List<UpdateSkillInfoDTO> updateSkillInfoDTOList = new ArrayList<>();
		//スキル情報更新欄の行数分だけ繰り返す
		for (int i = 0; i < updateSkillRow; i++) {
			//画面で入力された内容を受け取り、DTOにセットする。
			int skillInfoId = Integer.parseInt(request.getParameter("json[updateSkill][" + i + "][skillInfoId]"));
			String skillTypeId = request.getParameter("json[updateSkill][" + i + "][skillTypeId]");
			String skillDetail = request.getParameter("json[updateSkill][" + i + "][skillDetail]");
			String experienceYears = request.getParameter("json[updateSkill][" + i + "][experienceYears]");
			String acquisitionYearMonth = request.getParameter("json[updateSkill][" + i + "][acquisitionYearMonth]");
			UpdateSkillInfoDTO updateSkillDTO = new UpdateSkillInfoDTO(skillInfoId, skillTypeId, skillDetail,
					experienceYears,
					acquisitionYearMonth);
			//リストに追加する。
			updateSkillInfoDTOList.add(updateSkillDTO);
		}
		//戻り値を返す。
		return updateSkillInfoDTOList;
	}

	/**
	 * 画面で入力されたスキル情報をDTOに格納する。
	 * @param request リクエストスコープ
	 * @param registSkillRow スキル情報登録欄の行数
	 * @return スキル情報登録用DTOのリスト
	 */
	private List<RegistSkillInfoDTO> inputRegistSkillContent(HttpServletRequest request, int registSkillRow) {
		List<RegistSkillInfoDTO> registSkillInfoDTOList = new ArrayList<>();
		//スキル情報登録欄の行数分だけ繰り返す
		for (int i = 0; i < registSkillRow; i++) {
			//画面で入力された内容を受け取り、DTOにセットする。
			String skillTypeId = request.getParameter("json[registSkill][" + i + "][skillTypeId]");
			String skillDetail = request.getParameter("json[registSkill][" + i + "][skillDetail]");
			String experienceYears = request.getParameter("json[registSkill][" + i + "][experienceYears]");
			String acquisitionYearMonth = request.getParameter("json[registSkill][" + i + "][acquisitionYearMonth]");
			RegistSkillInfoDTO registSkillDTO = new RegistSkillInfoDTO(skillTypeId, skillDetail,
					experienceYears,
					acquisitionYearMonth);
			//リストに追加する。
			registSkillInfoDTOList.add(registSkillDTO);
		}
		//戻り値を返す
		return registSkillInfoDTOList;
	}

	/**
	 * 画面で入力された業務経験をDTOに格納する。
	 * @param request リクエストスコープ
	 * @param updateWorkExpRow 業務経験更新欄の行数
	 * @return 業務経験更新用DTOのリスト
	 */
	private List<UpdateEmpWorkExpDTO> inputUpdateWorkExpContent(HttpServletRequest request, int updateWorkExpRow) {
		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<>();
		//業務経験更新欄の行数分だけ繰り返す。
		for (int i = 0; i < updateWorkExpRow; i++) {
			//画面で入力された内容を受け取り、DTOにセットする。
			int employeeExperienceId = Integer
					.parseInt(request.getParameter("json[updateWorkExp][" + i + "][employeeExperienceId]"));
			String teamBelongStartDate = request.getParameter("json[updateWorkExp][" + i + "][teamBelongStartDate]");
			String teamBelongCompleteDate = request
					.getParameter("json[updateWorkExp][" + i + "][teamBelongCompleteDate]");
			UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(employeeExperienceId, teamBelongStartDate,
					teamBelongCompleteDate);
			//リストに追加する。
			updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		}
		//戻り値を返す。
		return updateEmpWorkExpDTOList;
	}

	/**
	 * 画面で入力された業務経験をDTOに格納する。
	 * @param request リクエストスコープ
	 * @param registWorkExpRow 業務経験登録欄の行数
	 * @return 業務経験登録用DTOのリスト
	 */
	private List<RegistEmpWorkExpDTO> inputRegistWorkExpContent(HttpServletRequest request,HttpSession session, int registWorkExpRow) {
		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<>();
		//セッションから更新画面に表示したDTOを取得する
				EmpInfoDTO displayedEmpInfoDTO = (EmpInfoDTO) session.getAttribute("empInfoDTO");
		//業務経験登録欄の行数分だけ繰り返す。
		for (int i = 0; i < registWorkExpRow; i++) {
			//画面で入力された内容を受け取り、DTOにセットする。
			String belongTeamId = request.getParameter("json[registWorkExp][" + i + "][teamId]");
			String contractTypeId = request.getParameter("json[registWorkExp][" + i + "][contractTypeId]");
			String roleId = request.getParameter("json[registWorkExp][" + i + "][roleId]");
			String monthlyUnitPrice = request.getParameter("json[registWorkExp][" + i + "][monthlyUnitPrice]");
			String teamBelongStartDate = request.getParameter("json[registWorkExp][" + i + "][teamBelongStartDate]");
			String teamBelongCompleteDate = request
					.getParameter("json[registWorkExp][" + i + "][teamBelongCompleteDate]");
			RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId,
					roleId, monthlyUnitPrice, teamBelongStartDate, teamBelongCompleteDate);
			registEmpWorkExpDTO.setLoginUserId((String) session.getAttribute("userId"));
			registEmpWorkExpDTO.setEmployeeId(displayedEmpInfoDTO.getEmployeeId());

			//リストに追加する。
			registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		}
		//戻り値を返す。
		return registEmpWorkExpDTOList;
	}

	/**
	 * 画面で入力された業務経験欄の日付をDTOに格納する。
	 * @param request リクエストスコープ
	 * @param updateWorkExpRow 業務経験更新欄の行数
	 * @param registWorkExpRow 業務経験登録欄の行数
	 * @return 業務経験日付比較用DTO
	 */
	private CheckDateEmpWorkExpDTO inputDateEmpWorkExpContent(HttpServletRequest request, int updateWorkExpRow,
			int registWorkExpRow) {
		List<String> teamBelongStartDateList = new ArrayList<String>();
		List<String> teamBelongCompleteDateList = new ArrayList<String>();
		//業務経験更新欄の行数分だけ繰り返す。
		for (int i = 0; i < updateWorkExpRow; i++) {
			//画面で入力された内容を受け取り、DTOにセットする。
			String teamBelongStartDate = request.getParameter("json[updateWorkExp][" + i + "][teamBelongStartDate]");
			String teamBelongCompleteDate = request
					.getParameter("json[updateWorkExp][" + i + "][teamBelongCompleteDate]");
			teamBelongStartDateList.add(teamBelongStartDate);
			teamBelongCompleteDateList.add(teamBelongCompleteDate);
		}
		////業務経験登録欄の行数分だけ繰り返す。
		for (int i = 0; i < registWorkExpRow; i++) {
			//画面で入力された内容を受け取り、DTOにセットする。
			String teamBelongStartDate = request.getParameter("json[registWorkExp][" + i + "][teamBelongStartDate]");
			String teamBelongCompleteDate = request
					.getParameter("json[registWorkExp][" + i + "][teamBelongCompleteDate]");
			teamBelongStartDateList.add(teamBelongStartDate);
			teamBelongCompleteDateList.add(teamBelongCompleteDate);
		}
		CheckDateEmpWorkExpDTO checkDateEmpWorkExpDTO = new CheckDateEmpWorkExpDTO(teamBelongStartDateList,
				teamBelongCompleteDateList);
		//戻り値を返す。
		return checkDateEmpWorkExpDTO;
	}
}
