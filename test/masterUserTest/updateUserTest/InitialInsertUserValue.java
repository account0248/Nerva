package masterUserTest.updateUserTest;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.masterUser.searchUser.SearchResultUserDTO;
import jp.co.vaile.nerva.masterUser.searchUser.SearchResultUserListDTO;
import jp.co.vaile.nerva.masterUser.searchUser.SearchUserDTO;

//疑似的な初期値を設定するメソッド。
public class InitialInsertUserValue {
	
	//生成したインスタンスを格納するためのメソッドのインスタンスを生成。
	SearchResultUserListDTO searchResultUserListDTO = new SearchResultUserListDTO();
	
	//4.検索条件を格納するメソッドのインスタンスを生成。
	SearchUserDTO searchUserDTO = new SearchUserDTO();
	
	//疑似初期値を返すメソッド。
	public SearchResultUserListDTO initialValueList(String[] adminValue,String[] generalValue,String[] taroValue,String[] hanakoValue,String[] aValue){
		
		List<SearchResultUserDTO> searchResultUserDTOList = new ArrayList<SearchResultUserDTO>();
		//管理者の初期値DTOを格納。
		searchResultUserDTOList.add(0,initialAdminValue(adminValue));
		
		//A123456789の初期値DTOを格納。
		searchResultUserDTOList.add(1,initialaValue(aValue));
		//一般の初期値DTOを格納。
		searchResultUserDTOList.add(2,initialGeneralValue(generalValue));
						
		//花子の初期値DTOを格納。
		searchResultUserDTOList.add(3,initialHanakoValue(hanakoValue));

		//太郎の初期値DTOを格納。
		searchResultUserDTOList.add(4,initialTaroValue(taroValue));
		
		//格納したリストをDTOに格納。呼び出し元に戻す。
		searchResultUserListDTO.setSearchResultUserDTOList(searchResultUserDTOList);
		return searchResultUserListDTO;
		
	}
	
	//管理者の初期値をDTOに格納するメソッド。
	public SearchResultUserDTO initialAdminValue(String[] adminValue) {
		SearchResultUserDTO searchResultUserDTO = new SearchResultUserDTO();
		searchResultUserDTO.setUserId(adminValue[0]);
		searchResultUserDTO.setUserName(adminValue[1]);
		searchResultUserDTO.setPassword(adminValue[2]);
		searchResultUserDTO.setCompany(adminValue[3]);
		searchResultUserDTO.setPost(adminValue[4]);
		searchResultUserDTO.setAdminFlg(adminValue[5]);
		return searchResultUserDTO;
		
		
	}
	
	//一般の初期値をDTOに格納するメソッド。
	public SearchResultUserDTO initialGeneralValue(String[] generalValue) {
		SearchResultUserDTO searchResultUserDTO = new SearchResultUserDTO();
		searchResultUserDTO.setUserId(generalValue[0]);
		searchResultUserDTO.setUserName(generalValue[1]);
		searchResultUserDTO.setPassword(generalValue[2]);
		searchResultUserDTO.setCompany(generalValue[3]);
		searchResultUserDTO.setPost(generalValue[4]);
		searchResultUserDTO.setAdminFlg(generalValue[5]);
		return searchResultUserDTO;
		
	}
	
	//太郎の初期値をDTOに格納するメソッド。
		public SearchResultUserDTO initialTaroValue(String[] taroValue) {
			SearchResultUserDTO searchResultUserDTO = new SearchResultUserDTO();
			searchResultUserDTO.setUserId(taroValue[0]);
			searchResultUserDTO.setUserName(taroValue[1]);
			searchResultUserDTO.setPassword(taroValue[2]);
			searchResultUserDTO.setCompany(taroValue[3]);
			searchResultUserDTO.setPost(taroValue[4]);
			searchResultUserDTO.setAdminFlg(taroValue[5]);
			return searchResultUserDTO;
			
		}
		
		//花子の初期値をDTOに格納するメソッド。
		public SearchResultUserDTO initialHanakoValue(String[] hanakoValue) {
			SearchResultUserDTO searchResultUserDTO = new SearchResultUserDTO();
			searchResultUserDTO.setUserId(hanakoValue[0]);
			searchResultUserDTO.setUserName(hanakoValue[1]);
			searchResultUserDTO.setPassword(hanakoValue[2]);
			searchResultUserDTO.setCompany(hanakoValue[3]);
			searchResultUserDTO.setPost(hanakoValue[4]);
			searchResultUserDTO.setAdminFlg(hanakoValue[5]);
			return searchResultUserDTO;
			
		}
		//A123456789の初期値をDTOに格納するメソッド。
		public SearchResultUserDTO initialaValue(String[] aValue) {
			SearchResultUserDTO searchResultUserDTO = new SearchResultUserDTO();
			searchResultUserDTO.setUserId(aValue[0]);
			searchResultUserDTO.setUserName(aValue[1]);
			searchResultUserDTO.setPassword(aValue[2]);
			searchResultUserDTO.setCompany(aValue[3]);
			searchResultUserDTO.setPost(aValue[4]);
			searchResultUserDTO.setAdminFlg(aValue[5]);
			return searchResultUserDTO;
			
		}
	
	//検索条件の既存値をDTOに格納するメソッド。
	public SearchUserDTO searchCondition(){
		searchUserDTO.setUserId("");
		searchUserDTO.setUserName("");
		searchUserDTO.setCompany("");
		searchUserDTO.setPost("");
		searchUserDTO.setAdminFlg("");
		searchUserDTO.setCompanyGroup("");
		searchUserDTO.setLoginUserCompanyId("");
		
		return searchUserDTO;
		
		
	}
}
