package builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URL {
	
	private String protocol;
	private String port;
	private String username;
	private String password;
	private String site;
	private String path;
	private List<String> pathparams = new ArrayList<String>();
	private Map<String, String> queryparams = new HashMap<String, String>();
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<String> getPathparams() {
		return pathparams;
	}
	public void setPathparams(List<String> pathparams) {
		this.pathparams = pathparams;
	}
	public Map<String, String> getQueryparams() {
		return queryparams;
	}
	public void setQueryparams(Map<String, String> queryparams) {
		this.queryparams = queryparams;
	}
	@Override
	public String toString() {
		return "URL [protocol=" + protocol + ", port=" + port + ", username=" + username + ", password=" + password
				+ ", site=" + site + ", path=" + path + ", pathparams=" + pathparams + ", queryparams=" + queryparams
				+ "]";
	}
}
