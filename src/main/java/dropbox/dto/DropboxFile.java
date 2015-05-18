package dropbox.dto;

import java.util.Date;

public class DropboxFile {
	
	
	private boolean read_only;
	private long revision;
	private long bytes;
	private boolean thumb_exists;
	private String rev;
	private Date modified;
	private String size;
	private String path;
	private boolean is_dir;
	private String modifier;
	private String root;
	private String icon;
	public boolean isRead_only() {
		return read_only;
	}
	public void setRead_only(boolean read_only) {
		this.read_only = read_only;
	}
	public long getRevision() {
		return revision;
	}
	public void setRevision(long revision) {
		this.revision = revision;
	}
	public long getBytes() {
		return bytes;
	}
	public void setBytes(long bytes) {
		this.bytes = bytes;
	}
	public boolean isThumb_exists() {
		return thumb_exists;
	}
	public void setThumb_exists(boolean thumb_exists) {
		this.thumb_exists = thumb_exists;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isIs_dir() {
		return is_dir;
	}
	public void setIs_dir(boolean is_dir) {
		this.is_dir = is_dir;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "DropboxFile [read_only=" + read_only + ", revision=" + revision
				+ ", bytes=" + bytes + ", thumb_exists=" + thumb_exists
				+ ", rev=" + rev + ", modified=" + modified + ", size=" + size
				+ ", path=" + path + ", is_dir=" + is_dir + ", modifier="
				+ modifier + ", root=" + root + ", icon=" + icon + "]";
	}
	
	
	
	
	
	

}
