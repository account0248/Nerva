package jp.co.vaile.nerva.searchemployee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongDepartmentInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.PostInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.SkillTypeInfo;


public class RegistEmpPageLogic {

	// 所属組織のDTOリストを取得する。
		public List<FetchAnyMasterTableDTO> BelongDepartmentInfoDTOList(){
			List<FetchAnyMasterTableDTO> belongDepartmentDTOList = new ArrayList<FetchAnyMasterTableDTO>();
			ExistCheck belongDepartmentInfo = new BelongDepartmentInfo();
			try {
				belongDepartmentDTOList = belongDepartmentInfo.fetchMasterTableList();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			// 所属組織のDTOリストを返す。
			return belongDepartmentDTOList;
		}

    // 役職のDTOリストを取得する。
	public List<FetchAnyMasterTableDTO> PostInfoDTOList(){
	List<FetchAnyMasterTableDTO> PostDTOList = new ArrayList<FetchAnyMasterTableDTO>();
	ExistCheck postInfo = new PostInfo();
	try {
		PostDTOList = postInfo.fetchMasterTableList();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	// 役職のDTOリストを返す。
	return PostDTOList;
}
	public List<FetchAnyMasterTableDTO> SkillTypeInfoDTOList(){

	// スキル情報のDTOリストを取得する。
	List<FetchAnyMasterTableDTO>  SkillTypeList = new ArrayList<FetchAnyMasterTableDTO>();
	ExistCheck skillTypeInfo = new  SkillTypeInfo();
	try {
		SkillTypeList = skillTypeInfo.fetchMasterTableList();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	// スキル情報のDTOリストを返す。
	return SkillTypeList;
	}
}