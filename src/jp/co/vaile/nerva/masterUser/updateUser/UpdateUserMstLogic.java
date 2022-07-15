package jp.co.vaile.nerva.masterUser.updateUser;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.sql.SQLException;

public class UpdateUserMstLogic {

	/**
	 * 更新内容を受け取り、システム管理者権限の置き換え処理を行った後更新処理を呼び出す。
	 * 
	 * @param updateUserDTO
	 */
	public void updateUserMstLogic(UpdateUserDTO updateUserDTO) throws ClassNotFoundException, SQLException {

		// boolean配列型のadminFlgを初期化。
		boolean[] adminFlg = new boolean[updateUserDTO.getAdminFlg().length];

		// 繰り返し処理を用いて、システム管理者権限の置き換え処理を行う。
		for (int i = 0; i < updateUserDTO.getTargetUserId().length; i++) {

			// システム管理者権限の配列から要素を取得し値が1の場合、adminFlgをtrue(管理者)に変更する。
			if (updateUserDTO.getAdminFlg()[i].equals(String.valueOf(ADMIN_VALUE))) {
				adminFlg[i] = true;
				// 当てはまらない場合、adminFlgをFalse(一般)に変更する。
			} else {
				adminFlg[i] = false;
			}
		}

		// ユーザーマスタ更新DTOと置き換え処理を行ったシステム管理者権限を引数に更新処理を行う。
		UpdateUserDAO updateUserDAO = new UpdateUserDAO();
		updateUserDAO.updateUser(updateUserDTO, adminFlg);
	}
}
