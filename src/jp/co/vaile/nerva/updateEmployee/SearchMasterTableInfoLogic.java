package jp.co.vaile.nerva.updateEmployee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongDepartmentInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.ContractTypeInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.PostInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.RoleInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.SkillTypeInfo;

public class SearchMasterTableInfoLogic {
	ExistCheck belongDepartmentInfo = new BelongDepartmentInfo();
	ExistCheck postInfo = new PostInfo();
	ExistCheck skillTypeInfo = new SkillTypeInfo();
	ExistCheck roleInfo = new RoleInfo();
	ExistCheck contractTypeInfo = new ContractTypeInfo();

	/**
	 * 画面上でセレクトボックスとして使用するマスタテーブルの情報を取得する。
	 * マスタテーブルの情報をマップとして返す。
	 * @return マスタテーブルの情報を格納したマップ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, List<FetchAnyMasterTableDTO>> searchMasterTableInfo() throws ClassNotFoundException, SQLException {
		//セレクトボックスとして使用するマスタテーブルの情報を取得する。
		//共通処理のマスタテーブル情報取得処理を呼び出す。
		List<FetchAnyMasterTableDTO> departmentDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		departmentDTOList = belongDepartmentInfo.fetchMasterTableList();
		List<FetchAnyMasterTableDTO> postDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		postDTOList = postInfo.fetchMasterTableList();
		List<FetchAnyMasterTableDTO> skillDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		skillDTOList = skillTypeInfo.fetchMasterTableList();
		List<FetchAnyMasterTableDTO> roleDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		roleDTOList = roleInfo.fetchMasterTableList();
		List<FetchAnyMasterTableDTO> contractTypeDTOList = new ArrayList<FetchAnyMasterTableDTO>();
		contractTypeDTOList = contractTypeInfo.fetchMasterTableList();
		//戻り値をマップに格納する。
		Map<String, List<FetchAnyMasterTableDTO>> masterTableInfo = new HashMap<String, List<FetchAnyMasterTableDTO>>();
		masterTableInfo.put("departmentDTOList", (List<FetchAnyMasterTableDTO>) departmentDTOList);
		masterTableInfo.put("postDTOList", (List<FetchAnyMasterTableDTO>) postDTOList);
		masterTableInfo.put("skillDTOList", (List<FetchAnyMasterTableDTO>) skillDTOList);
		masterTableInfo.put("roleDTOList", (List<FetchAnyMasterTableDTO>) roleDTOList);
		masterTableInfo.put("contractTypeDTOList", (List<FetchAnyMasterTableDTO>) contractTypeDTOList);
		//戻り値を返す。
		return masterTableInfo;
	}
}
