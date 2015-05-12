package dropbox.api;

public interface DropboxConstants {
	

	 //DROPBOX authentication constants
		public static final String DROPBOX_APP_KEY="";
		public static final String DROPBOX_APP_SECRET="";
		public static final String DROPBOX_APP_AUTHORIZATION_URL="";
		public static final String DROPBOX_APP_AUTHORIZATION_CODE="";
		public static final String DROPBOX_APP_ACCESSTOKEN="access_token";
		public static final String DROPBOX_APP_TEST_FILE="";
		public static final String DROPBOX_APP_USER_DIR="user.dir";
		
	 	
		
	//drive box root uri	
		public static final String DROPBOX_URL_ROOT = "https://www.dropbox.com/1";	
		public static final String DROPBOX_API_URL_ROOT = "https://api.dropbox.com/1";	
		public static final String DROPBOX_API_CONTENT_ROOT = "https://api-content.dropbox.com/1";	
		public static final String DROPBOX_API_NOTIFY_ROOT = "https://api-notify.dropbox.com/1";	


	//DROPBOX oAuth 1.0 API reference
		public static final String API_DROPBOX_OAUTH1_AUTHORIZE="/oauth/authorize";//goes through https://www.dropbox.com
		public static final String API_DROPBOX_OAUTH1_REQUEST_TOKEN="/oauth/request_token";
		public static final String API_DROPBOX_OAUTH1_ACCESS_TOKEN="/oauth/access_token";


	//DROPBOX oAuth 2.0 API reference
		public static final String API_DROPBOX_OAUTH2_AUTHORIZE="/oauth2/authorize"; //goes through https://www.dropbox.com
		public static final String API_DROPBOX_OAUTH2_TOKEN="/oauth2/token";
		public static final String API_DROPBOX_OAUTH2_TOKEN_FROM_OAUTH1="/oauth2/token_from_oauth1";


	//DROPBOX disable AccessToken API reference
		public static final String API_DROPBOX_DISABLE_ACCESS_TOKEN="/disable_access_token";
		
	//DROPBOX accountinfo Api reference
		public static final String API_DROPBOX_ACCOUNT_INFO="/account/info";
	
	//DROPBOX Files and Metadata Api reference
		public static final String API_DROPBOX_GET_FILE_BY_PATH="/files/auto/<path>";		
		public static final String API_DROPBOX_UPLOAD_FILE="/files_put/auto/<path>?param=val";		
		public static final String API_DROPBOX_METADATA="/metadata/auto/<path>";		
		public static final String API_DROPBOX_DELTA="/delta";		
		public static final String API_DROPBOX_DELTA_LATEST_CURSOR="delta/latest_cursor";		
		public static final String API_DROPBOX_LONGPOLL_DELTA="/longpoll_delta";		
		public static final String API_DROPBOX_REVISIONS="/revisions/auto/<path>";		
		public static final String API_DROPBOX_RESTORE="/restore/auto/<path>";		
		public static final String API_DROPBOX_SEARCH="/search/auto/<path>";		
		public static final String API_DROPBOX_SHARED_LINK_PATH="/shares/auto/<path>";		
		public static final String API_DROPBOX_LINK_DIRECTORY_TO_A_FILE="/media/auto/<path>";		
		public static final String API_DROPBOX_COPY_REF="/copy_ref/auto/<path>";//goes through api-content.dropbox.com 
		public static final String API_DROPBOX_IMAGE_THUMBNAIL="/thumbnails/auto/<path>";		
		public static final String API_DROPBOX_FILE_PREVIEW="/previews/auto/<path>";//goes through api-content.dropbox.com 		
		public static final String API_DROPBOX_CHUNKED_UPLOAD="/chunked_upload?param=val";//goes through api-content.dropbox.com		
		public static final String API_DROPBOX_COMMIT_CHUNKED_UPLOAD="/commit_chunked_upload/auto/<path>";	//goes through api-content.dropbox.com		
		public static final String API_DROPBOX_SHARED_FOLDERS_LIST="/shared_folders/<shared_folder_id>";	
	
	//DROPBOX File operations	
		
		public static final String API_DROPBOX_FILE_OR_FOLDER_COPY="/fileops/copy";		
		public static final String API_DROPBOX_FOLDER_CREATE="/fileops/create_folder";		
		public static final String API_DROPBOX_FILE_OR_FOLDER_DELETE="/fileops/delete";		
		public static final String API_DROPBOX_FILE_OR_FOLDER_MOVE="/fileops/move";
		
		
		
		


}
