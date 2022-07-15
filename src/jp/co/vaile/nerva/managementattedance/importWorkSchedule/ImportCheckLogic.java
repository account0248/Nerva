package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

public class ImportCheckLogic {
	/**
	 * ファイル種別を判定し、Excelファイルならtrueを返しテキストファイルならfalseを返す
	 * @param fileName
	 * @return extensionFlg
	 */
	public boolean importCheck(String fileName) {
		boolean extensionFlg = true;
		int strIndex = fileName.indexOf(".");
		String extension = fileName.substring(strIndex+1);
		//拡張子を判定する
		if(extension.equals("xlsx")) {
			
			extensionFlg = true;
		}else {
			
			extensionFlg = false;
		}
		
		return extensionFlg;
	}

}
