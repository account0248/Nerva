package jp.co.vaile.nerva.masterSkillType.searchSkillType;

import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.MasterContents;

public class SearchSkillTypeMstLogic {
	/**
	* 真偽値の変数に引数の年数/取得日の値が1の場合trueを代入
	* 真偽値の変数、searchSkillTypeDTOを引数に登録処理の呼び出し
	* @param searchSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @return　エラーメッセージ
	*/
	public List<SearchSkillTypeDTO> searchSkillTypeMstLogic (SearchSkillTypeDTO searchSkillTypeDTO) throws ClassNotFoundException, SQLException{
		Boolean yearsDateOfAcquisition = null;
		String years =  String.valueOf(MasterContents.YEARS_VALUE);
		String dateOfAcquisition =  String.valueOf(MasterContents.DATE_OF_ACQUISITION_VALUE);
		
		//年数/取得日の値が1の場合true、0の場合falseを代入
		if(searchSkillTypeDTO.getYearsDateOfAcquisition().equals(years)) {
			yearsDateOfAcquisition = true;
		}else if(searchSkillTypeDTO.getYearsDateOfAcquisition().equals(dateOfAcquisition)) {
			yearsDateOfAcquisition = false;
		}
		//DAOインスタンスを生成
		SearchSkillTypeDAO searchSkillTypeDAO = new SearchSkillTypeDAO();
		
		//検索結果を戻り値とする
		return searchSkillTypeDAO.searchSkillTypeList(searchSkillTypeDTO, yearsDateOfAcquisition);
	}
}
