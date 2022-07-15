package mock;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class MockServletConfig implements ServletConfig {

	@Override
	public String getServletName() {
		// TODO 自動生成されたメソッド・スタブ
		return "TestServlet";
	}

	@Override
	public ServletContext getServletContext() {
		return new MockServletContext();
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getInitParameter(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
