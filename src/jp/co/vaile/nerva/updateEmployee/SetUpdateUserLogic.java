package jp.co.vaile.nerva.updateEmployee;

import java.util.List;

public class SetUpdateUserLogic<E extends UpdateEmpRelationDTO> {
	E setUser(E dTO, String loginUserId, String employeeId) {
		dTO.setLoginUserId(loginUserId);
		dTO.setEmployeeId(employeeId);
		return dTO;
	}

	List<E> setUser(List<E> dTOList, String loginUserId,String employeeId){
		for(int i = 0 ; i < dTOList.size();i++) {
			dTOList.get(i).setLoginUserId(loginUserId);
			dTOList.get(i).setEmployeeId(employeeId);
		}
		return dTOList;
	}
}
