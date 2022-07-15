package jp.co.vaile.nerva.commonprocess.existchecksub;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;

public class SexInfo extends ExistCheck {

	private String[] sexInfo = {"男", "女"};

	public SexInfo() {
		super("no_table");
	}

	/**
	 *このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、
	 *仕様書で定義された文字と一致するかをチェックする。
	 *真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @return
	 */
	public boolean isThisExistInfo(String checkParam) {
		// インスタンスフィールドのsexInfoを用いて第一引数が仕様書で定義された存在かをチェックする。
		statusOK = true;
		if(!sexInfo[0].equals(checkParam) && !sexInfo[1].equals(checkParam)) {
			statusOK = false;
		}
		// trueは第一引数が、仕様に存在する状態を示す。
		// falseは第一引数が、仕様に存在しない状態を示す。
		return statusOK;
	}

	@Override
	public boolean isThisExistDB(String checkParam) {
		statusOK = isThisExistInfo(checkParam);
		return statusOK;
	}

	@Override
	public final List<FetchAnyMasterTableDTO> fetchMasterTableList() throws SQLException, ClassNotFoundException{
		List<FetchAnyMasterTableDTO> masterTableList = new ArrayList<>();
		for(int i = 0; i < sexInfo.length; i++) {
			FetchAnyMasterTableDTO fetchAnyMasterTableDTO = new FetchAnyMasterTableDTO();
			fetchAnyMasterTableDTO.setMasterDataId("" +i);
			fetchAnyMasterTableDTO.setMasterData(sexInfo[i]);
			masterTableList.add(fetchAnyMasterTableDTO);
		}
		// List<E>を返す。
		return masterTableList;
	}
}
