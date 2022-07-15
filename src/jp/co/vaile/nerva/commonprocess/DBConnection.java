package jp.co.vaile.nerva.commonprocess;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * データベースの接続を行うクラス
 * @author s.kimura
 */
public class DBConnection {
//開発環境ではパスは自分で設定する。
	private static final String PROPERTYFILE_PATHl="C:\\work\\db.properties";
//コンストラクタ自クラスのインスタンス化禁止。
private DBConnection(){};;
//コネクションを示す。
private static Connection connection;

/**
 * @author s.kimura
 *プロパティファイルを読み込みを行う
 */
public static String[] getDBProperty()   {
	Properties prop = new Properties();
	String[] dbproperty=null;
	try {
		Reader fr= new FileReader(PROPERTYFILE_PATHl);
		prop.load(fr);
		dbproperty =new String[]{prop.getProperty("db.url"),
				prop.getProperty("db.name"),
				prop.getProperty("db.pass"),
				prop.getProperty("db.db_driver")};
	} catch (IOException e) {
		e.printStackTrace();
	}
	return dbproperty ;
}
/**
 * フィールドのコネクションがnull、または閉じている場合、ドライバを読み込んでコネクションを生成する。
 *@return コネクションを返す。
 * @throws ClassNotFoundException
 * @throws SQLException
 */
public static Connection getConnection() throws ClassNotFoundException,SQLException{
	if(connection == null || connection.isClosed()) {
		String[] dbproperty = getDBProperty();
		Class.forName(dbproperty[3]);
		connection =DriverManager.getConnection(dbproperty[0],dbproperty[1],dbproperty[2]);
	}
	return connection;
}
/**
 * コネクションがnullでなく、閉じられていない時、コネクションを閉じる。
 * @throws SQLException
 */
public static void closeConnection() throws SQLException  {
	if(connection != null  && !connection.isClosed()) {
		connection.close();
		}
	}
}