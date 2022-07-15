package mock;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

public class MockServletContext implements ServletContext{

	@Override
	public String getContextPath() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ServletContext getContext(String uripath) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int getEffectiveMajorVersion() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int getEffectiveMinorVersion() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public String getMimeType(String file) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Set<String> getResourcePaths(String path) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public URL getResource(String path) throws MalformedURLException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public InputStream getResourceAsStream(String path) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO 自動生成されたメソッド・スタブ
		return new MockRequestDespatcher();
	}

	@Override
	public RequestDispatcher getNamedDispatcher(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Servlet getServlet(String name) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Enumeration<Servlet> getServlets() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Enumeration<String> getServletNames() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void log(String msg) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void log(Exception exception, String msg) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void log(String message, Throwable throwable) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getRealPath(String path) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getServerInfo() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getInitParameter(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean setInitParameter(String name, String value) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Object getAttribute(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void setAttribute(String name, Object object) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void removeAttribute(String name) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getServletContextName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Dynamic addServlet(String servletName, String className) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Dynamic addServlet(String servletName, Servlet servlet) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T extends Servlet> T createServlet(Class<T> c) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ServletRegistration getServletRegistration(String servletName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T extends Filter> T createFilter(Class<T> c) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public FilterRegistration getFilterRegistration(String filterName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public SessionCookieConfig getSessionCookieConfig() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void addListener(String className) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <T extends EventListener> void addListener(T t) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void addListener(Class<? extends EventListener> listenerClass) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <T extends EventListener> T createListener(Class<T> c) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public JspConfigDescriptor getJspConfigDescriptor() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void declareRoles(String... roleNames) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getVirtualServerName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
