package mock;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class MockHttpSession implements HttpSession {
	Map<String, Object> object = new HashMap<String, Object>();

	@Override
	public Object getAttribute(String name) {
		return object.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		class InnerEnum<E> implements Enumeration<E> {
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
		InnerEnum<String> result = new InnerEnum<String>();
		result.setKeySet(object.keySet());
		return result;
	}

	@Override
	public long getCreationTime() {
		return 0;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public long getLastAccessedTime() {
		return 0;
	}

	@Override
	public int getMaxInactiveInterval() {
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	@Deprecated
	public HttpSessionContext getSessionContext() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	@Deprecated
	public Object getValue(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	@Deprecated
	public String[] getValueNames() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void invalidate() {
		object = null;
	}

	@Override
	public boolean isNew() {
		return false;
	}

	@Override
	@Deprecated
	public void putValue(String name, Object value) {
	}

	@Override
	public void removeAttribute(String name) {
		object.remove(name);
	}

	@Override
	@Deprecated
	public void removeValue(String name) {
	}

	@Override
	public void setAttribute(String name, Object value) {
		object.put(name, value);

	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
