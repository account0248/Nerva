package jp.co.vaile.nerva.login;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.MasterContents;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;


public class LoginAction extends HttpServlet {
	HttpSession session;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession();

		String result = "/jsp/error.jsp";
		String errorMsg = null;
		session.removeAttribute("loginCheck");

		/**本当はちゃんとしたログイン管理 */
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		ErrorMsg returnErrorMsg = new ErrorMsg();
		
		//入力情報を引数にエラーチェック処理を呼び出す。
		LoginCheckLogic loginCheckLogic = new LoginCheckLogic();
		String loginInputCheck[] = { CommonConstants.LOGIN_ERROR };
		if(loginCheckLogic.checkLogin(userId,password)) {
			errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
					loginInputCheck);
			request.setAttribute("errorList",errorMsg);
			result = "/jsp/login.jsp";
		}else {

			LoginUserDto loginUserDto;
			
			try {

				loginUserDto = new LoginLogic().doLogin(userId, password);

				if (loginUserDto.isLoginSuccess()) {
					setSessionInfo(loginUserDto);
					request.setAttribute("adminValue", MasterContents.ADMIN_VALUE);
					request.setAttribute("generalValue", MasterContents.GENERAL_VALUE);
					request.setAttribute("errorList",errorMsg);
					result = "/jsp/menu.jsp";
				}else {
					String loginCheck[] = { CommonConstants.LOGIN };
					errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.PROCESS_FAILURE_ERROR_MESSAGE,
							loginCheck);
					request.setAttribute("errorList",errorMsg);
					result = "/jsp/login.jsp";
				}
			} catch (ClassNotFoundException | SQLException e) {
				// エラー発生自はログを出力してエラー画面へ遷移
				e.printStackTrace();
				result = "/jsp/error.jsp";

			}
		}
		
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		// forwardメソッドでJSPに遷移します。
		disp.forward(request, response);

	}
	
	
	private void setSessionInfo(LoginUserDto loginUserDto) {
		session.setAttribute("loginCheck", loginUserDto.isLoginSuccess());
		session.setAttribute("userId", loginUserDto.getUserId());
		session.setAttribute("userName", loginUserDto.getUserName());
		session.setAttribute("companyId", loginUserDto.getCompanyId());
		session.setAttribute("adminFlg", loginUserDto.isAdminFlg());
		
		String companyPrivilege=String.valueOf(SUBCOMPANY_VALUE);
		if(loginUserDto.isCompanyPrivilege()==true) {
			companyPrivilege=String.valueOf(PARENT_COMPANY_VALUE);
		}
		session.setAttribute("companyPrivilege", companyPrivilege);
	}

}