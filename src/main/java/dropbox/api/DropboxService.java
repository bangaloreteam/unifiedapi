package dropbox.api;

import com.jayway.restassured.response.Response;

import core.api.ServiceAccessToken;

public interface DropboxService extends ServiceAccessToken{
	

	 //DROPBOX authentication constants
		public static final String DROPBOX_APP_KEY="drivebox.app.key";
		public static final String DROPBOX_APP_SECRET="drivebox.app.secret";
		public static final String DROPBOX_APP_AUTHORIZATION_URL="";
		public static final String DROPBOX_APP_AUTHORIZATION_CODE="";
		public static final String DROPBOX_APP_ACCESSTOKEN="drivebox.accessToken";
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
		public static final String API_DROPBOX_SHARED_FOLDERS_LIST_By_ID="/shared_folders/<shared_folder_id>";	
		public static final String API_DROPBOX_SHARED_FOLDERS_LIST="/shared_folders/";
	
	//DROPBOX File operations	
		
		public static final String API_DROPBOX_FILE_OR_FOLDER_COPY="/fileops/copy";		
		public static final String API_DROPBOX_FOLDER_CREATE="/fileops/create_folder";		
		public static final String API_DROPBOX_FILE_OR_FOLDER_DELETE="/fileops/delete";		
		public static final String API_DROPBOX_FILE_OR_FOLDER_MOVE="/fileops/move";
		
		
	
	
	//files and metadata
	public void uploadFileIntoDropBox(DropboxService service, String file) throws Exception;
	public void uploadFileIntoDropBoxThrMultipart(DropboxService service, String file) throws Exception;
	public void downloadFileFromDropBox( DropboxService service,String file ) throws Exception;
	public void editDropBoxFile(DropboxService service, String file) throws Exception;
	public void retriveFileAndFolderMetadata(DropboxService service, String folder) throws Exception;
	public void deltaFromDropBox(DropboxService service, String folder) throws Exception;	
	public void deltaLatestCursorFromDrobBox( DropboxService service,String file ) throws Exception;
	public void longPollDataDropBox(DropboxService service, String file) throws Exception;
	public void revisionsDropBox(DropboxService service, String folder) throws Exception;
	public void restoreFileDropBox(DropboxService service, String folder) throws Exception;	
	public void searchFileFromDrobBox( DropboxService service,String file ) throws Exception;
	public void shareFileOrFolderDropBox(DropboxService service, String file) throws Exception;
	public void mediaDropBox(DropboxService service, String folder) throws Exception;
	public String copyReferenceFileDropBox(DropboxService service, String folder) throws Exception;	
	public void thumbnailOfFileFromDrobBox( DropboxService service,String file ) throws Exception;
	public void filePreviewOfDropBox(DropboxService service, String file) throws Exception;
	public void chunkedUploadDropBox(DropboxService service, String folder) throws Exception;
	public void commitChunkedUploadDropBox(DropboxService service, String folder) throws Exception;	
	public void sharedFoldersListFromDrobBox( DropboxService service,String file ) throws Exception;
	public void getTheSharedLinkToFileOrFolder( DropboxService service,String file ) throws Exception;
	public void uploadFileUsingMultipart(DropboxService service) throws Exception;
	public void uploadFileUsingMultipartSpicification(DropboxService service) throws Exception;
	
	
	
	//file operations
	public void fileCopyDropBox(DropboxService service, String file) throws Exception;
	public void folderCopyDropBox(DropboxService service, String folder) throws Exception;
	public void deleteFileDrobBox( DropboxService service,String file ) throws Exception;
	public void deleteFolderDrobBox( DropboxService service,String file ) throws Exception;
	public void moveFileDrobBox( DropboxService service,String file ) throws Exception;
	public void moveFolderDrobBox( DropboxService service,String file ) throws Exception;
	public String createFolderInAutoModeDropBox( DropboxService service, String folder, String file) throws Exception;
	public void uploadFileIntoDropboxFolder( DropboxService service, String folder, String file) throws Exception;
	
	public void createFolderInSandBoxModeDropBox( DropboxService service, String folder, String file) throws Exception;
	public void createFolderInDropBoxModeDropBox( DropboxService service, String folder, String file) throws Exception;
	
	
	//DropBox accounts
	public void retriveAccountsInfoDrobBox( DropboxService service,String file ) throws Exception;
	
	//DropBox delete access tokesn
	public void disableAccessTokenDrobBox( DropboxService service,String file ) throws Exception;
	
	//DropBox OAuth1.0
	public void oAuth1requestTokenDrobBox( DropboxService service,String file ) throws Exception;
	public void oAuth1AuthorizeDrobBox( DropboxService service,String file ) throws Exception;
	public void oAuthAccessToenDrobBox( DropboxService service,String file ) throws Exception;
	
	
	//DropBox OAuth2.0
	public void oAuth2AuthorizeDrobBox( DropboxService service,String file ) throws Exception;
	public void oAuth2TokenDrobBox( DropboxService service,String file ) throws Exception;
	public void oAuth2TokenFromoAuth1DrobBox( DropboxService service,String file ) throws Exception;
	
	
	
	

}
