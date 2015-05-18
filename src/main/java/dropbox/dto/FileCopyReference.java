package dropbox.dto;

import java.util.Date;

public class FileCopyReference {

	private String copy_ref;
	private Date expires;
	public String getCopy_ref() {
		return copy_ref;
	}
	public void setCopy_ref(String copy_ref) {
		this.copy_ref = copy_ref;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	@Override
	public String toString() {
		return "FileCopyReference [copy_ref=" + copy_ref + ", expires="
				+ expires + "]";
	}
	
	
	
	
	
}
