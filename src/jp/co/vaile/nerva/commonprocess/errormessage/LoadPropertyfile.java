package jp.co.vaile.nerva.commonprocess.errormessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadPropertyfile {

	// 初期化時に参照するpropertiesファイルのパスを示す。
	private final static String INIT_FILE_PATH = "C:\\work\\errorMsg.properties";
	// 初期化で読み込んだpropertiesファイルを示す。
	private final static Properties properties;

	// インスタンス化の禁止
    private LoadPropertyfile() throws Exception {
    }


    // static初期化子、propertiesファイルを読み込んでPROPERTIES初期化する。
    static {
        properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get(INIT_FILE_PATH), StandardCharsets.UTF_8));
        } catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
        }
    }


    /**
     * メッセージIDからエラーメッセージを取得し、呼び出し元にエラーメッセージを返す。
     *
     * @param key キー
     * @return 値
     */
    public static String getProperty(final String key) {
    	// 引数とフィールドのpropertiesを用いて、エラーメッセージを取得する。
    	// 文字列としてエラーメッセージを返す。
        return properties.getProperty(key);
    }
}