package jp.co.vaile.nerva.searchProject;

import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;

public class SearchProjectLogic {

	/**
	 * 案件検索処理を呼び出す。
	 * @param searchProjectPageDTO 
	 * @return searchProjectDTOArr
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public ArrayList<SearchProjectDTO> searchProjectLogic(SearchProjectPageDTO searchProjectPageDTO ) throws ClassNotFoundException, SQLException {

		ArrayList<SearchProjectDTO> searchProjectDTOArr = new ArrayList<SearchProjectDTO>();

		searchProjectDTOArr = SearchProjectDAO.searchProject(searchProjectPageDTO);

		return searchProjectDTOArr;
	}

	/**
	 * 検索を行ったユーザーの所属会社グループ権限に基づき表示する検索結果を決定する。
	 * @param searchProjectDTOArr 
	 * @param loginUserCompanyId 
	 * @param companyPrivilege
	 * @return searchProjectToStringDTOArr
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public ArrayList<SearchProjectToStringDTO> limitViewingProjectByPrivilegeToStringLogic(ArrayList<SearchProjectDTO> searchProjectDTOArr ,String loginUserCompanyId,String companyPrivilege) throws ClassNotFoundException, SQLException {

		ArrayList<SearchProjectDTO> searchProjectAfterDTOArr = new ArrayList<SearchProjectDTO>();
		//案件検索結果の要素数分for文内の処理を繰り返す。
		for(int i = 0 ; i < searchProjectDTOArr.size() ; i++) {
			//責任者名を取得。
			String responsibleName = searchProjectDTOArr.get(i).getResponsibleName();
			//責任者名を引数に責任者の所属会社IDを取得。
			String responsibleCompanyId = SearchProjectDAO.searchCompanyId(responsibleName);
			
			 CheckViewingAuthority  checkViewingAuthorityLogic = new  CheckViewingAuthority();
			 //ユーザーID、所属会社グループ権限、責任者の所属会社ID、案件閲覧権限を判定する処理を呼び出す。
			 boolean viewingAuthorityFig = checkViewingAuthorityLogic.checkViewingAuthority(loginUserCompanyId,companyPrivilege,responsibleCompanyId);
			 //案件閲覧権限がtrueであれば、処理中の案件検索の要素を画面出力用のArrayListに追加する
			 if( viewingAuthorityFig == true ){

				SearchProjectDTO searchProjectAfterDTO = new SearchProjectDTO();

				searchProjectAfterDTO = searchProjectDTOArr.get(i);

				searchProjectAfterDTOArr.add(searchProjectAfterDTO);
			}
		}

		ArrayList<SearchProjectToStringDTO> searchProjectToStringDTOArr = new ArrayList<SearchProjectToStringDTO>();
		//画面出力用のArrayListを引数に各カラムをString型に変換する処理を呼び出す。
		searchProjectToStringDTOArr = SearchProjectToStringDAO.searchProjectToString(searchProjectAfterDTOArr);

		return searchProjectToStringDTOArr;
	}
}