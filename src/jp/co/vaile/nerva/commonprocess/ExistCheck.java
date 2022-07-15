package jp.co.vaile.nerva.commonprocess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ExistCheck {

	// チェックに用いるテーブル名を表す。
	private String masterTableName;
	// メソッドの結果を示す。
	protected boolean statusOK;

	// masterTableNameを初期化するコンストラクタ。
	public ExistCheck(String masterTableName) {
		this.masterTableName = masterTableName;
	}


	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、
	 * DBに格納されている文字と一致するかをチェックする。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public boolean isThisExistDB(String checkParam) throws ClassNotFoundException, SQLException {
		// インスタンスフィールドのtableNameを用いて、DBからテーブル内容を取得し、第一引数が存在するかをチェックする。
		statusOK = true;
		AnyMasterTableDAO masterTableDAO = new AnyMasterTableDAO();
		List<FetchAnyMasterTableDTO> masterTableList = new ArrayList<>();
		try {
			masterTableList = masterTableDAO.fetchAnyMasterTable(masterTableName);
	
		for(int i = 0; i < masterTableList.size(); i++) {
			if(!checkParam.equals(masterTableList.get(i).getMasterDataId())) {
				statusOK = false;
			}else {
				statusOK = true;
				return statusOK;
			}
		}
		// trueは第一引数が、DBに存在する状態を示す。
		// falseは第一引数が、DBに存在しない状態を示す。
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return statusOK;
	}


	/**
	 * このメソッドはテーブル内容を取得する。
	 * List<E>を返す。
	 *
	 * @return DTOを入れたList
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public List<FetchAnyMasterTableDTO> fetchMasterTableList() throws ClassNotFoundException, SQLException {
		 // インスタンスフィールドのtableNameを用いて、AnyMasterTableDao.fetchAnyMasterTableを呼び出しDBからテーブル内容を取得する。
		List<FetchAnyMasterTableDTO> masterTableList = new ArrayList<>();
		AnyMasterTableDAO anyMasterTableList = new AnyMasterTableDAO();
		try {
			masterTableList = anyMasterTableList.fetchAnyMasterTable(masterTableName);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
		
		// List<E>を返す。
		return masterTableList;
	}

}
