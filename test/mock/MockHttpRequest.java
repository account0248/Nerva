package mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class MockHttpRequest implements HttpServletRequest {
	Map<String, List<String>> parameter = new HashMap<String, List<String>>();
	Map<String, Object> attribute = new HashMap<String, Object>();
	MockHttpSession httpSession;

	public void setParameter(String key, String object) {
		List<String> parameterList = parameter.get(key);
		if (parameterList == null) {
			parameterList = new ArrayList<String>();
			parameterList.add(object);
			parameter.put(key, parameterList);
		} else {
			parameterList.add(object);
		}
	}

	@Override
	public AsyncContext getAsyncContext() {
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		return attribute.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		class InnerEnumAttribute<E> implements Enumeration<E> {
			Set<E> set = new HashSet<E>();

			public void setKeySet(Set<E> entrySet) {
				set = entrySet;
			}

			@Override
			public boolean hasMoreElements() {
				return set.iterator().hasNext();
			}

			@Override
			public E nextElement() {
				return set.iterator().next();
			}
		}
		InnerEnumAttribute<String> attributeNames = new InnerEnumAttribute<String>();
		attributeNames.setKeySet(attribute.keySet());
		return attributeNames;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public int getContentLength() {
		return 0;
	}

	@Override
	public long getContentLengthLong() {
		return 0;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public DispatcherType getDispatcherType() {
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public String getLocalAddr() {
		return null;
	}

	@Override
	public String getLocalName() {
		return null;
	}

	@Override
	public int getLocalPort() {
		return 0;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return null;
	}

	@Override
	public String getParameter(String name) {
		if (parameter.get(name) == null) {
			return null;
		}
		return parameter.get(name).get(0);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> resultMap = new HashMap<String, String[]>();

		for (Entry<String, List<String>> entry : parameter.entrySet()) {
			String[] strs = new String[entry.getValue().size()];
			resultMap.put(entry.getKey(), strs);
		}
		return resultMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		class InnerParameter<E> implements Enumeration<E> {
			Set<E> set = new HashSet<E>();

			public void setKeySet(Set<E> entrySet) {
				set = entrySet;
			}

			@Override
			public boolean hasMoreElements() {
				return set.iterator().hasNext();
			}

			@Override
			public E nextElement() {
				return set.iterator().next();
			}
		}
		InnerParameter<String> parameterNames = new InnerParameter<String>();
		parameterNames.setKeySet(attribute.keySet());
		return parameterNames;
	}

	@Override
	public String[] getParameterValues(String name) {
		List<String> list = parameter.get(name);
		if (list == null) {
			return null;
		}
		String[] result = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	@Override
	public String getProtocol() {
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return null;
	}

	@Override
	public String getRealPath(String path) {
		return null;
	}

	@Override
	public String getRemoteAddr() {
		return null;
	}

	@Override
	public String getRemoteHost() {
		return null;
	}

	@Override
	public int getRemotePort() {
		return 0;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return new MockRequestDespatcher();
	}

	@Override
	public String getScheme() {
		return null;
	}

	@Override
	public String getServerName() {
		return null;
	}

	@Override
	public int getServerPort() {
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		return false;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public void removeAttribute(String name) {
		attribute.remove(name);

	}

	@Override
	public void setAttribute(String name, Object o) {
		attribute.put(name, o);

	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		return null;
	}

	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return false;
	}

	@Override
	public String changeSessionId() {
		return null;
	}

	@Override
	public String getAuthType() {
		return null;
	}

	@Override
	public String getContextPath() {
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		return null;
	}

	@Override
	public long getDateHeader(String name) {
		return 0;
	}

	@Override
	public String getHeader(String name) {
		return null;
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return null;
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		return null;
	}

	@Override
	public int getIntHeader(String name) {
		return 0;
	}

	@Override
	public String getMethod() {
		return null;
	}

	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return null;
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return null;
	}

	@Override
	public String getPathInfo() {
		return null;
	}

	@Override
	public String getPathTranslated() {
		return null;
	}

	@Override
	public String getQueryString() {
		return null;
	}

	@Override
	public String getRemoteUser() {
		return null;
	}

	@Override
	public String getRequestURI() {
		return null;
	}

	@Override
	public StringBuffer getRequestURL() {
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		return null;
	}

	@Override
	public String getServletPath() {
		return null;
	}

	public void setSession(MockHttpSession httpSession) {
		this.httpSession = httpSession;
	}

	@Override
	public HttpSession getSession() {
		MockHttpSession resultSession;
		if (httpSession == null) {
			resultSession = new MockHttpSession();
		} else {
			resultSession = this.httpSession;
		}
		return resultSession;
	}

	@Override
	public HttpSession getSession(boolean create) {
		MockHttpSession resultSession;
		if (httpSession == null) {
			resultSession = new MockHttpSession();
		} else {
			resultSession = this.httpSession;
		}
		return resultSession;
	}

	@Override
	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return false;
	}

	@Override
	public boolean isUserInRole(String role) {
		return false;
	}

	@Override
	public void login(String username, String password) throws ServletException {

	}

	@Override
	public void logout() throws ServletException {

	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
		return null;
	}

}
