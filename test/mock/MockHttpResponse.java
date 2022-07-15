package mock;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class MockHttpResponse implements HttpServletResponse{

	@Override
	public void flushBuffer() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getBufferSize() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getContentType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				// TODO 自動生成されたメソッド・スタブ

			}
		});
	}

	@Override
	public boolean isCommitted() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void resetBuffer() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setBufferSize(int size) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setCharacterEncoding(String charset) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setContentLength(int len) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setContentLengthLong(long len) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setContentType(String type) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setLocale(Locale loc) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void addCookie(Cookie cookie) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void addDateHeader(String name, long date) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void addHeader(String name, String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void addIntHeader(String name, int value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean containsHeader(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public String encodeRedirectURL(String url) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String encodeRedirectUrl(String url) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String encodeURL(String url) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String encodeUrl(String url) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getHeader(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Collection<String> getHeaderNames() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Collection<String> getHeaders(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getStatus() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void sendError(int sc) throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void sendRedirect(String location) throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setDateHeader(String name, long date) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setHeader(String name, String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setIntHeader(String name, int value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setStatus(int sc) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setStatus(int sc, String sm) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
