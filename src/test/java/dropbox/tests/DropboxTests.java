package dropbox.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import dropbox.api.DropboxApi;
import dropbox.api.DropboxConstants;
import onedrive.api.OneDrive;
import onedrive.dto.Children;
import onedrive.impl.OneDriveAPI;
import onedrive.tests.OneDriveTests;

public class DropboxTests {
  
  
	
	HashMap<String, String> queryParams;	
	HashMap<String, String> headers ;
	String dropboxAccessToken=null;
	java.io.File testFile;
	private DropboxApi dropboxApi;
	
	public DropboxTests() throws Exception{
		
		queryParams = new HashMap<String,  String>();
		headers = new HashMap<String,  String>();

		Properties properties = new Properties();
		InputStream inputStream = DropboxTests.class.getClassLoader().getResourceAsStream("dropboxconfig.properties");
		properties.load(inputStream);

		if (inputStream == null) {
			throw new FileNotFoundException("Dropbox properties file dropboxconfig.properties not found in the classpath");
		}
		
		dropboxAccessToken=properties.getProperty(DropboxConstants.DROPBOX_APP_ACCESSTOKEN);
		
		dropboxApi=new DropboxApi();
	}

	  public void uploadFile() throws Exception{
		  
		 testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		 
			HashMap<String, String> queryParams=new HashMap<String,  String>();
			queryParams.put("access_token", dropboxAccessToken);
			HashMap<String, String> headers =new HashMap<String,  String>();
			headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			//invoke the httpcore post api
			Response response = dropboxApi.doPut(DropboxConstants.API_DROPBOX_UPLOAD_FILE.replace("<path>", testFile.getName()),
					fileToByteArray(testFile),
					queryParams,
					headers, true); 	
			
		   System.out.println("statusCode"+response.getStatusCode());
		
	  }
	  @Test
	  public void downloadFile() throws Exception{
	
		  testFile= new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\gitcommands.txt");
		 		HashMap<String, String> queryParams=new HashMap<String,  String>();
				queryParams.put("access_token", dropboxAccessToken);
				HashMap<String, String> headers =new HashMap<String,  String>();
				headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
				//invoke the httpcore post api
				Response response = dropboxApi.doGet(DropboxConstants.API_DROPBOX_GET_FILE_BY_PATH.replace("<path>", testFile.getName()),  
						queryParams,
						headers, true); 
				String respAsString = response.getHeader("x-dropbox-metadata");
				System.out.println("test.."+respAsString);
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
		

  

}
