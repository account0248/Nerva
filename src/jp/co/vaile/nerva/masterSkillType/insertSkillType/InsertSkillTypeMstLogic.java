package jp.co.vaile.nerva.masterSkillType.insertSkillType;

import java.sql.SQLException;
import java.text.ParseException;

import jp.co.vaile.nerva.commonprocess.MasterContents;

public class InsertSkillTypeMstLogic {
	/**
	* 真偽値の変数に引数の年数/取得日の値が1の場合trueを代入
	* 真偽値の変数、insertSkillTypeDTOを引数に登録処理の呼び出し
	* @param insertSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @return　エラーメッセージ
	*/
	public void insertSkillTypeMstLogic(InsertSkillTypeDTO insertSkillTypeDTO) throws ClassNotFoundException, SQLException, ParseException {
		boolean yearsDateOfAcquisition = false;
		String years =  String.valueOf(MasterContents.YEARS_VALUE);
		//年数/取得日の値が1場合trueを代入
		if (insertSkillTypeDTO.getYearsDateOfAcquisition().equals(years)) {
			yearsDateOfAcquisition = true;
		}
		//DAOインスタンスを生成
		InsertSkillTypeDAO insertSkillTypeDAO = new InsertSkillTypeDAO();
		//登録処理の呼び出し
		insertSkillTypeDAO.insertSkillType(insertSkillTypeDTO, yearsDateOfAcquisition);
	}
}
