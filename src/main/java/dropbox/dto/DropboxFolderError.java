package dropbox.dto;

public class DropboxFolderError {
	
	private String error;

	@Override
	public String toString() {
		return "DropboxFolderError [error=" + error + "]";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	
	

}
