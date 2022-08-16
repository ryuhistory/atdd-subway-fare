package support.auth.userdetails;

import java.util.List;

public class User implements UserDetails {
	private String username;
	private String password;
	private List<String> authorities;
	private boolean isTeenager;
	private boolean isChildren;

	public User(String username, String password, List<String> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public User(String username, String password, List<String> authorities, boolean isTeenager, boolean isChildren) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isTeenager = isTeenager;
		this.isChildren = isChildren;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public List<String> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean checkCredentials(Object credentials) {
		return password.equals(credentials.toString());
	}

	@Override
	public boolean isTeenager() {
		return isTeenager;
	}

	@Override
	public boolean isChildren() {
		return isChildren;
	}
}
