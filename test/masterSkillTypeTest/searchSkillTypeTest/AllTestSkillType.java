package masterSkillTypeTest.searchSkillTypeTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestSkillType {
	@Test
	@DisplayName("searchSkillTypeパッケージテストの一括実行")
    public void allTestSkillType() throws ClassNotFoundException, ServletException, IOException {
		ShowSkillTypeMstTest showSkillTypeMstTest = new ShowSkillTypeMstTest();
		SearchSkillTypeMstTest searchSkillTypeMstTest = new SearchSkillTypeMstTest();
		
		//スキル種別マスタメンテナンス画面に遷移する
		showSkillTypeMstTest.masterSkillTypePattern();
		
		// 検索機能全テストパターン
		searchSkillTypeMstTest.idfiltereSearchSkillType();
		searchSkillTypeMstTest.namefiltereSearchSkillType();
		searchSkillTypeMstTest.flgfiltereYearsSearchSkillType();
		searchSkillTypeMstTest.flgfiltereDateOfAcquisitionSearchSkillType();
		searchSkillTypeMstTest.filtereSearchSkillType();
		searchSkillTypeMstTest.allSearchSkillType();
		searchSkillTypeMstTest.searchSkillTypeResultCheck();
		searchSkillTypeMstTest.skillTypeIdLengthCheck();
		searchSkillTypeMstTest.skillTypeIdLengthCheck2();
		searchSkillTypeMstTest.skillTypeIdLengthCheck3();
		searchSkillTypeMstTest.skillTypeIdMatchCheck();
		searchSkillTypeMstTest.skillTypeNameLengthCheck2();
		searchSkillTypeMstTest.skillTypeNameLengthCheck3();
		searchSkillTypeMstTest.namePartfiltereSearchSkillType();
	}
}
