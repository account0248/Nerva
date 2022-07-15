package jp.co.vaile.nerva.masterSkillType.updateSkillType;

import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.MasterContents;

public class UpdateSkillTypeMstLogic {
	/**
	* 真偽値の変数に引数の年数/取得日の値が1の場合trueを代入
	* 真偽値の変数、updateSkillTypeDTOを引数に更新処理の呼び出し
	* @param updateSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @return　エラーメッセージ
	*/
	public void updateSkillTypeMstLogic(UpdateSkillTypeDTO updateSkillTypeDTO)
			throws ClassNotFoundException, SQLException {
		Boolean[] yearsDateOfAcquisition = new Boolean[updateSkillTypeDTO.getSkillTypeId().length];
		String years =  String.valueOf(MasterContents.YEARS_VALUE);
		String dateOfAcquisition =  String.valueOf(MasterContents.DATE_OF_ACQUISITION_VALUE);
		
		for (int i = 0; i < updateSkillTypeDTO.getSkillTypeId().length; i++) {
			//年数/取得日の値が1場合trueを代入
			if (updateSkillTypeDTO.getYearsDateOfAcquisition()[i].equals(years)) {
				yearsDateOfAcquisition[i] = true;
			//年数/取得日の値が0場合falseを代入
			}else {
				yearsDateOfAcquisition[i] = false;
			}
		}
			// DAOインスタンスを生成
			UpdateSkillTypeDAO updateSkillTypeDAO = new UpdateSkillTypeDAO();
			// 更新処理の呼び出し
			updateSkillTypeDAO.updateSkillType(updateSkillTypeDTO, yearsDateOfAcquisition);
	}
}
