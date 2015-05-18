package dropbox.tests;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.jayway.restassured.internal.MultiPartSpecificationImpl;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.MultiPartSpecification;

import core.api.ServiceAccessToken;
import dropbox.api.DropboxApi;

import dropbox.api.DropboxService;
import dropbox.dto.DropboxFile;
import dropbox.dto.DropboxFolderError;
import dropbox.dto.FileCopyReference;
import dropbox.dto.SharedLinkObject;
import static java.lang.System.out;

public class DropboxDriveTests implements DropboxService{
	
	
	HashMap<String, String> queryParams;	
	HashMap<String, String> headers ;
	String dropboxAccessToken=null;
	java.io.File testFile;
	private DropboxApi dropboxApi;
	private DropboxApi dropboxApiUrl;
	private DropboxApi dropboxApiContentUrl;
	private DropboxApi dropboxApiNotifyUrl;
	Properties properties=null;
	 DropboxService service;
	
	
    public DropboxDriveTests() throws Exception {
    	

		 properties = new Properties();
		InputStream inputStream = DropboxDriveTests.class.getClassLoader().getResourceAsStream("dropboxconfig.properties");
		properties.load(inputStream);

		if (inputStream == null) {
			throw new FileNotFoundException("Dropbox properties file dropboxconfig.properties not found in the classpath");
		}
		
		queryParams = new HashMap<String,  String>();
    	queryParams.put("access_token", getAccessToken());
    	
		headers = new HashMap<String,  String>();
		dropboxApi=new DropboxApi();
	
		
		
		}
   
    
    public String getAccessToken() throws Exception {
		
    	dropboxAccessToken=properties.getProperty(DropboxService.DROPBOX_APP_ACCESSTOKEN);
    	return dropboxAccessToken;
	}
   
  public String createFolderInAutoModeDropBox(DropboxService service,
		String folder, String file) throws Exception {
	//parameter=auto
		//path   
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		String requestBody="";
		String folderName=folder;
		DropboxFile file_folderObject=null;
		DropboxFolderError error=null;
		String path=null;
		 	HashMap<String, String> queryParams=new HashMap<String,  String>();
				queryParams.put("access_token", dropboxAccessToken);
				queryParams.put("root", "auto");
				queryParams.put("path", folderName);
				HashMap<String, String> headers =new HashMap<String,  String>();
				headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
				headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
				
				//invoke the httpcore post api
				Response response = dropboxApiUrl.doPost(service.API_DROPBOX_FOLDER_CREATE,  requestBody,
						queryParams,
						headers, true); 
				
				
				int responseCode=response.getStatusCode();
				
				String file_exist_notexistMsg=((responseCode== HttpStatus.SC_FORBIDDEN)?folderName+" already exist"
						                     : (responseCode== HttpStatus.SC_OK)?folderName+" created sucessfully":"");
				
				//System.out.println("Folder Details:" + file_exist_notexistMsg);
				//System.out.println("response as string:"+response.asString());
				
				if(responseCode==HttpStatus.SC_OK){
					
					file_folderObject=response.as(DropboxFile.class);
					path=file_folderObject.getPath();
				}
				else if (responseCode==HttpStatus.SC_FORBIDDEN)
				{
					error=response.as(DropboxFolderError.class);
					path="/"+folderName;
				}
				print(path);
				
	return path;			

}
 

  public void createFolderInSandBoxModeDropBox(DropboxService service,
		String folder, String file) throws Exception {
	  dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		String requestBody="";
		String folderName="Sample5";
		DropboxFile file_folderObject=null;
		DropboxFolderError error=null;
		String path=null;
			
	  HashMap<String, String> queryParams=new HashMap<String,  String>();
			queryParams.put("access_token", dropboxAccessToken);
			queryParams.put("root", "sandbox");
			queryParams.put("path", folderName);
			HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			
			//invoke the httpcore post api
			Response response = dropboxApi.doPost(service.API_DROPBOX_FOLDER_CREATE,  requestBody,
					queryParams,
					headers, true); 
			String folderNameInSandBoxMode=response.asString();
			System.out.println("folderNameInSandBoxMode:" + folderNameInSandBoxMode);
			
			/**
			 * {"error": "App folder (sandbox) access attempt failed because this app is not configured to have an app folder.  Should your access type be 'dropbox' instead?"}
PASSED: test
			 */
	
}


  public void createFolderInDropBoxModeDropBox(DropboxService service,
		String folder, String file) throws Exception {
	  dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		String requestBody="";
		String folderName="Sample5";
		DropboxFile file_folderObject=null;
		DropboxFolderError error=null;
		String path=null;
		HashMap<String, String> queryParams=new HashMap<String,  String>();
			queryParams.put("access_token", dropboxAccessToken);
			queryParams.put("root", "dropbox");
			queryParams.put("path", folderName);
			HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			
			//invoke the httpcore post api
			Response response = dropboxApi.doPost(service.API_DROPBOX_FOLDER_CREATE,  requestBody,
					queryParams,
					headers, true); 
			String folderNameInDropBoxMode=response.asString();
			System.out.println("folderNameInDropBoxMode:" + folderNameInDropBoxMode);
	
}
public void uploadFileIntoDropboxFolder(DropboxService service,
		String folder, String file) throws Exception {
	// TODO Auto-generated method stub
	
}
public void deleteFileDrobBox(DropboxService service, String file)
		throws Exception {
	dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
	String requestBody="";
	String folderName="Sample.txt";
	DropboxFile file_folderObject=null;
	DropboxFolderError error=null;
	String path=null;
	HashMap<String, String> queryParams=new HashMap<String,  String>();
		queryParams.put("access_token", dropboxAccessToken);
		queryParams.put("root", "auto");
		queryParams.put("path", folderName);
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		//invoke the httpcore post api
		Response response = dropboxApi.doPost(service.API_DROPBOX_FILE_OR_FOLDER_DELETE,  requestBody,
				queryParams,
				headers, true); 
		String folderNameInDropBoxMode=response.asString();
		System.out.println("folderNameInDropBoxMode:" + folderNameInDropBoxMode);
	
	
}
public void deleteFolderDrobBox(DropboxService service, String file)
		throws Exception {
	dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
	String requestBody="";
	String folderName="Sample10";
	DropboxFile file_folderObject=null;
	DropboxFolderError error=null;
	String path=null;
	HashMap<String, String> queryParams=new HashMap<String,  String>();
		queryParams.put("access_token", dropboxAccessToken);
		queryParams.put("root", "auto");
		queryParams.put("path", folderName);
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		//invoke the httpcore post api
		Response response = dropboxApi.doPost(service.API_DROPBOX_FILE_OR_FOLDER_DELETE,  requestBody,
				queryParams,
				headers, true); 
		String folderNameInDropBoxMode=response.asString();
		System.out.println("folderNameInDropBoxMode:" + folderNameInDropBoxMode);
		
		
}

	public void uploadFileIntoDropBox(DropboxService service, String file)
			throws Exception {
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		//invoke the httpcore post api
		Response response = dropboxApi.doPut(service.API_DROPBOX_UPLOAD_FILE.replace("<path>", testFile.getName()),
				fileToByteArray(testFile),
				queryParams,
				headers, true); 
		
	}
	
	   public MultiPartSpecification build() {
	        MultiPartSpecificationImpl spec = new MultiPartSpecificationImpl();
	        spec.setCharset("UTF-8");
	        spec.setContent(new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\test1.txt"));
	        spec.setControlName("file");
	        spec.setFileName("test1.txt");
	        spec.setMimeType("application/octet-stream");
	        return spec;
	    }
	public void uploadFileUsingMultipart(DropboxService service)
			throws Exception {
		testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\test1.txt");
		  String fileName=testFile.getName();
		  HashMap<String, String> queryParams=new HashMap<String,  String>();
			queryParams.put("access_token", dropboxAccessToken);
			HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.CONTENT_TYPE, "multipart/form-data");
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			headers.put(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
			headers.put("boundary", Long.toString(new Date().getTime()));
			
			//invoke the httpcore post api
			Response response = dropboxApi.multiPartPostSimpleFile(service.API_DROPBOX_UPLOAD_FILE.replace("<path>", fileName),
					testFile,
					queryParams,
					headers, true); 
			System.out.println("status code.."+response.getStatusCode()+"..."+response.getStatusLine());
		
	}
	public void uploadFileUsingMultipartSpicification(DropboxService service)
			throws Exception {
		 testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\test1.txt");
		  String fileName=testFile.getName();
		  HashMap<String, String> queryParams=new HashMap<String,  String>();
			queryParams.put("access_token", dropboxAccessToken);
			HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.CONTENT_TYPE, "multipart/form-data");
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			headers.put(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
			headers.put("boundary", Long.toString(new Date().getTime()));
			
			//MultiPartSpecBuilder builder= new MultiP
			
			MultiPartSpecification multiPartSpecification= build();
			
			//invoke the httpcore post api
			Response response = dropboxApi.multiPartPostSimpleFile(service.API_DROPBOX_UPLOAD_FILE.replace("<path>", fileName),
					multiPartSpecification,
					queryParams,
					headers, true); 
			System.out.println("status code.."+response.getStatusCode()+"..."+response.getStatusLine());
		
	}
	 
	  	  
	public void downloadFileFromDropBox(DropboxService service, String file)
			throws Exception {
		 testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
	 		HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			//invoke the httpcore post api
			Response response = dropboxApi.doGet(service.API_DROPBOX_GET_FILE_BY_PATH.replace("<path>", testFile.getName()),  
					queryParams,
					headers, true); 
			String respAsString = response.getHeader("x-dropbox-metadata");
			System.out.println("test.."+respAsString);
		
	}
	
	
	public String copyReferenceFileDropBox(DropboxService service, String folder_filePath)
			throws Exception {
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		//  testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		Response response = dropboxApiUrl.doGet(service.API_DROPBOX_COPY_REF.replace("<path>", folder_filePath), 
				queryParams,
				headers, true); 
		FileCopyReference fileReference=response.as(FileCopyReference.class);
		System.out.println("fileCopyReference.."+fileReference);
		String fileCopyReference=fileReference.getCopy_ref();
		return fileCopyReference;
	}
	


	
	public void fileCopyDropBox(DropboxService service, String folder_filePath)
			throws Exception {
		
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		//  testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		
		queryParams.put("root", "auto");
		queryParams.put("from_copy_ref", copyReferenceFileDropBox(service,"/gitcommands.txt"));
		queryParams.put("to_path", "gitbackup.txt");
		
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		Response response = dropboxApiUrl.doPost(service.API_DROPBOX_FILE_OR_FOLDER_COPY,  "",
				queryParams,
				headers, true); 
		
		System.out.println("response..."+response.asString());
	
	
	}

	public void moveFileDrobBox(DropboxService service, String file)
			throws Exception {
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		//  testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		
		queryParams.put("root", "auto");
		queryParams.put("from_path", "gitcommands.txt");
		queryParams.put("to_path", "Sample11.txt");
		
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		Response response = dropboxApiUrl.doPost(service.API_DROPBOX_FILE_OR_FOLDER_MOVE,  "",
				queryParams,
				headers, true); 
		
		System.out.println("response..."+response.asString());
		
	}
	
	public void moveFolderDrobBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void getTheSharedLinkToFileOrFolder(DropboxService service,
			String file) throws Exception {
		
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		//  testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		
		queryParams.put("short_url", "false");
	
		
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		Response response = dropboxApiUrl.doPost(service.API_DROPBOX_SHARED_LINK_PATH.replace("<path>", "Sample3"),  "",
				queryParams,
				headers, true); 
		
		SharedLinkObject sharedLinkObject=response.as(SharedLinkObject.class);
		
		
	}
	

	public void sharedFoldersListFromDrobBox(DropboxService service, String file)
			throws Exception {
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		//  testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		
		Response response = dropboxApiUrl.doGet(service.API_DROPBOX_SHARED_FOLDERS_LIST, 
				queryParams,
				headers, true); 
		out.println("response.."+response.asString());
		
		
	}
	public void shareFileOrFolderDropBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	public void chunkedUploadDropBox(DropboxService service, String folder)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void commitChunkedUploadDropBox(DropboxService service,
			String folder) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public void deltaFromDropBox(DropboxService service, String folder)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void deltaLatestCursorFromDrobBox(DropboxService service,
			String file) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void disableAccessTokenDrobBox(DropboxService service,
			String file) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void editDropBoxFile(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void filePreviewOfDropBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void folderCopyDropBox(DropboxService service, String folder)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void longPollDataDropBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void mediaDropBox(DropboxService service, String folder)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void oAuth1AuthorizeDrobBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void oAuth1requestTokenDrobBox(DropboxService service,
			String file) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void oAuth2AuthorizeDrobBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void oAuth2TokenDrobBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void oAuth2TokenFromoAuth1DrobBox(DropboxService service,
			String file) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void oAuthAccessToenDrobBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void restoreFileDropBox(DropboxService service, String folder)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void retriveAccountsInfoDrobBox(DropboxService service,
			String file) throws Exception {
		dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		HashMap<String, String> headers =new HashMap<String,  String>();
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

		//invoke the httpcore post api
		Response response = dropboxApiUrl.doGet(service.API_DROPBOX_ACCOUNT_INFO,  
				queryParams,
				headers, true); 
	//AccountInfo accountInfo=response.as(AccountInfo.class);
	out.println("finished dropboxaccounts info"+response.asString());
		
	}
	
	@Test
	public void test() throws Exception
	{
		retriveFileAndFolderMetadata(service,"");
	}
	public void retriveFileAndFolderMetadata(DropboxService service,
			String folder) throws Exception {
		// testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.txt");
		 dropboxApiUrl=new DropboxApi(DropboxService.DROPBOX_API_URL_ROOT);
		   
			HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			
			Response response = dropboxApiUrl.doGet(service.API_DROPBOX_METADATA.replace("<path>", "gitbackup.txt"),  
					queryParams,
					headers, true); 
		out.println("response for meta data:"+response.asString());
		
	}
	
	public void revisionsDropBox(DropboxService service, String folder)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void searchFileFromDrobBox(DropboxService service, String file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void thumbnailOfFileFromDrobBox(DropboxService service,
			String file) throws Exception {
		// TODO Auto-generated method stub
		
	}


	
	public void uploadFileIntoDropBoxThrMultipart(DropboxService service,
			String file) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private static byte[] fileToByteArray(java.io.File file) {
		byte[] byteArrayFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = null;
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(byteArrayFile);
			fileInputStream.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return byteArrayFile;
	}
	
	public void print(String str)
	{
		out.println(str);
	}
	
	
	
	
	

}
