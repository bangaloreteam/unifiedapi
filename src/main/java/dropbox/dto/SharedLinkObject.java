package dropbox.dto;

import java.util.Date;

public class SharedLinkObject {
	
	  private String url;
	  private Date expires;
	  private String visibility;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	@Override
	public String toString() {
		return "SharedLinkObject [url=" + url + ", expires=" + expires
				+ ", visibility=" + visibility + "]";
	}
	
	
	  
	  
	  
	  
	  
	  

}
