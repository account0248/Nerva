package jp.co.vaile.nerva.masterSkillType.searchSkillType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.MasterContents;

public class SearchSkillTypeDAO {

	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * スキル種別マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * 
	 * @param searchSkillTypeDTO
	 * @return searchSkillTypeDTOList スキル種別検索結果DTOリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchSkillTypeDTO> searchSkillTypeList(SearchSkillTypeDTO searchSkillTypePageDTO,
			Boolean yearsDateOfAcquisition) throws SQLException, ClassNotFoundException {
		String years =  String.valueOf(MasterContents.YEARS_VALUE);
		String dateOfAcquisition =  String.valueOf(MasterContents.DATE_OF_ACQUISITION_VALUE);
		List<SearchSkillTypeDTO> searchSkillTypeDTOList = new ArrayList<>();
		try {
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append(" skill_type_id " + " , ");
			sql.append(" skill_type_name " + " , ");
			sql.append(" years_date_of_acquisition_flg ");
			sql.append(" FROM ");
			sql.append(" m_skill_type ");

			String conditionWord = " WHERE ";

			// スキル種別マスメンテナンス画面の各フォームに入力がある場合WHERE句に追加
			List<Object> serchSkillTypeList = new ArrayList<>();
			if (lengthCheck.isNomNullOrNomEmpty(searchSkillTypePageDTO.getSkillTypeId())) {
				sql.append(conditionWord + " skill_type_id = ? ");
				serchSkillTypeList.add(searchSkillTypePageDTO.getSkillTypeId());
				conditionWord = " AND ";
			}
			if (lengthCheck.isNomNullOrNomEmpty(searchSkillTypePageDTO.getSkillTypeName())) {
				sql.append(conditionWord + " skill_type_name LIKE ? ");
				serchSkillTypeList.add("%" + searchSkillTypePageDTO.getSkillTypeName() + "%");
				conditionWord = " AND ";
			}
			if (yearsDateOfAcquisition == null) {
				
			} else {
				if (yearsDateOfAcquisition) {
					sql.append(conditionWord + " years_date_of_acquisition_flg = true ");
					conditionWord = " AND ";
				} else {
					sql.append(conditionWord + " years_date_of_acquisition_flg = false ");
					conditionWord = " AND ";
				}
			}
			sql.append(" ORDER BY skill_type_id ASC ");

			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < serchSkillTypeList.size(); i++) {

				stmt.setString(1 + i, (String) serchSkillTypeList.get(i));
			}
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO();
				searchSkillTypeDTO.setSkillTypeId(rs.getString("skill_type_id"));
				searchSkillTypeDTO.setSkillTypeName(rs.getString("skill_type_name"));
				if (rs.getBoolean("years_date_of_acquisition_flg")) {
					searchSkillTypeDTO.setYearsDateOfAcquisition(years);
				} else {
					searchSkillTypeDTO.setYearsDateOfAcquisition(dateOfAcquisition);
				}
				searchSkillTypeDTOList.add(searchSkillTypeDTO);
			}
			return searchSkillTypeDTOList;

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}

	}
}
