package jp.co.vaile.nerva.masterCompany.searchCompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 会社識別コードに重複があるかをチェックし、戻り値を返す。
 * @param companyCodeList,OMPANY_CODE
 * @return companyResultCodeList
 * 
 */
public class ShowCompanyCodeCheck {
	public List<Character> getCompanyCode(List<Character>companyCodeList,Character []COMPANY_CODE) {
		//1 P2の配列をList型に変換する。
		List<Character> companyResultCodeList= new ArrayList<Character>(Arrays.asList(COMPANY_CODE));
		//2 P1と1のListが重複しているかを確認繰り返し処理。
		for (int i = 0; i < companyResultCodeList.size(); i++) {
			for(int j = 0; j < companyCodeList.size(); j++) {
				if(companyResultCodeList.get(i).equals(companyResultCodeList.get(j))) {
					companyResultCodeList.remove(companyCodeList.get(j));
				}	
			}
		}		
		return companyResultCodeList;
	}
}


