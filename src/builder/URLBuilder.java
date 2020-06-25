package builder;

public class URLBuilder {
	private URL url = new URL();
	
	private URLBuilder() {}

	public static URLBuilder newInstance() {
		return(new URLBuilder());
	}

	public URLBuilder withProtocol(String protocol) {
		this.url.setProtocol(protocol);
		return(this);
	}
	
	public URLBuilder withPort(String port) {
		this.url.setPort(port);
		return(this);
	}

	public URLBuilder withUsername(String username) {
		this.url.setUsername(username);
		return(this);
	}

	public URLBuilder withPassword(String password) {
		this.url.setPassword(password);
		return(this);
	}

	public URLBuilder withSite(String site) {
		this.url.setSite(site);
		return(this);
	}

	public URLBuilder withPath(String path) {
		this.url.setPath(path);
		return(this);
	}

	public URLBuilder withPathParam(String value) {
		this.url.getPathparams().add(value);
		return(this);
	}

	public URLBuilder withQueryParam(String key, String value) {
		this.url.getQueryparams().put(key, value);
		return(this);
	}

	public URLBuilder clone() {
		return(this.clone());
	}
	
	public URL build() {
		return(this.url);
	}

}
