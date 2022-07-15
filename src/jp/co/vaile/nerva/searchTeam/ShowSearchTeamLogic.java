package jp.co.vaile.nerva.searchTeam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.OrderSourceDAO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;

public class ShowSearchTeamLogic {

	List<OrderSourceDTO> returnOrderSourceTable() throws ClassNotFoundException, SQLException {
		OrderSourceDAO orderSourceDAO = new OrderSourceDAO();
		//1.	発注元テーブルの情報取得処理の呼び出しを行う。
		List<OrderSourceDTO> orderSourceList = new ArrayList<OrderSourceDTO>();
		orderSourceList = orderSourceDAO.selectAllOrderSource();
		//2.	発注元テーブルリストを戻り値とする
		return orderSourceList;
	}
}
