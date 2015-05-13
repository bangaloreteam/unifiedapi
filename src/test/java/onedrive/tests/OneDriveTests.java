package onedrive.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import com.jayway.restassured.internal.MultiPartSpecificationImpl;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.MultiPartSpecification;
import com.sun.jersey.api.client.ClientResponse;

import onedrive.api.OneDrive;
import onedrive.dto.Children;
import onedrive.dto.Drive;
import onedrive.dto.Item;
import onedrive.dto.TokenProduce;
import onedrive.impl.OneDriveAPI;


public class OneDriveTests {
	private OneDriveAPI oneDriveAPI;
	private TokenProduce tokenProduce;
	HashMap<String, String> queryParams;	
	HashMap<String, String> headers ;

	public OneDriveTests() throws Exception {	
		queryParams = new HashMap<String,  String>();
		headers = new HashMap<String,  String>();

		Properties properties = new Properties();
		InputStream inputStream = OneDriveTests.class.getClassLoader().getResourceAsStream("onedrive.properties");
		properties.load(inputStream);

		if (inputStream == null) {
			throw new FileNotFoundException("OneDriveAPI properties file onedrive.properties not found in the classpath");
		}

		tokenProduce = new TokenProduce(properties.getProperty("clientid"), properties.getProperty("clientsecret"),
				properties.getProperty("authorizationcode"), properties.getProperty("refreshtoken"));

		this.oneDriveAPI = new OneDriveAPI(tokenProduce, true);
		//initTokenByPrincipal is following the code flow and requires some additional coding
		//This is todo item
		//oneDriveAPI.initTokenByPrincipal();
		oneDriveAPI.getAccessTokenByRefreshTokenAndClientId();		
	}


	/**
	 * This is the test to retrieve the default drive of the authorized user
	 * @throws Exception
	 */
	@Test
	public void getDefaultDrive() throws Exception {				

		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

		Drive drive = oneDriveAPI.getDefaultDrive(OneDrive.API_PATH_DEFAULT_DRIVE, queryParams, headers, true);
		System.out.println("Drive id:" + drive.getId());

		System.out.print(new ObjectMapper().writeValueAsString(drive));		
	}		


	@Test
	public void createFolderInRoot() throws Exception {		
		//Config behavior can be rename, replace, fail
		String requestBody = "{\"name\":\"MyFolder\", \"folder\":{}, \"@name.conflictBehavior\":\"rename\"}";

		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		String folder = oneDriveAPI.createFolder(OneDrive.API_PATH_DRIVE_ROOT_CHILDREN, requestBody, queryParams, headers, true);
		System.out.println("Folder Details:" + folder);
	}

	@Test
	public void getRootChildren() throws Exception {		
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		//Filter the id, name,size from the item resource
		queryParams.put("select", "id,name,size");

		Children children = oneDriveAPI.getRootChildren(OneDrive.API_PATH_DRIVE_ROOT_CHILDREN, queryParams, headers, true);

		String myFolderId = "";

		for (Item item : children.getValue()) {
			System.out.println("Item id  : " + item.getId());
			System.out.println("Item name: " + item.getName());			
			System.out.println("Item size: " + item.getSize());
			if(item.getName().equals("MyFolder")) {
				myFolderId = item.getId();
			}
		}

		System.out.println("MyFolder Id:" + myFolderId);
		String uri = OneDrive.API_PATH_GET_CHILDRENS_BY_ITEMID.replace("{item-id}", myFolderId);
		Children folderChildren = oneDriveAPI.getChildrenByItemId(uri, queryParams, headers, true);

		System.out.println("Get children by item id uri:" + uri);
		System.out.println("Size of folder children:" + folderChildren.getValue().length);		
		System.out.println("Folder contents:" + new ObjectMapper().writeValueAsString(folderChildren));	
	}

	@Test
	public void uploadSimpleFile() throws Exception {	
				
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		headers.put(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);

		//Filter the id, name,size from the item resource
		queryParams.put("select", "id,name,size");
		Children children = oneDriveAPI.getRootChildren(OneDrive.API_PATH_DRIVE_ROOT_CHILDREN, queryParams, headers, false);
		String myFolderId = null;

		for (Item item : children.getValue()) {			
			if(item.getName().equals("MyFolder")) {
				myFolderId = item.getId();
			}
		}
	
		//Upload the file into my folder
		
		java.io.File oneDriveTestfile = new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.txt");
		queryParams.clear();
		//Specify the behavior to use if the file already exists. You can use the values fail, replace, or rename. The default for PUT is replace.
		queryParams.put("@name.conflictBehavior", "rename");

		if (myFolderId != null) {
			String uri = OneDrive.API_PATH_SIMPLE_UPLOAD_BY_CHILDREN_PATH.replace("{parent-id}", myFolderId)
																		 .replace("{filename}", oneDriveTestfile.getName());
			
			Item uploadedItem = oneDriveAPI.uploadSimpleFile(uri, fileToByteArray(oneDriveTestfile), queryParams, headers, true);

			System.out.println("Item contents:" + new ObjectMapper().writeValueAsString(uploadedItem));	
		}
		
		//After upload get the list of items in MyFolder
		String uri = OneDrive.API_PATH_GET_CHILDRENS_BY_ITEMID.replace("{item-id}", myFolderId);
		Children folderChildren = oneDriveAPI.getChildrenByItemId(uri, queryParams, headers, true);

		System.out.println("Get children by item id uri:" + uri);
		System.out.println("Size of my folder children:" + folderChildren.getValue().length);		
		System.out.println("Folder contents:" + new ObjectMapper().writeValueAsString(folderChildren));	
	}
	
	 public MultiPartSpecification build() {
	        MultiPartSpecificationImpl spec = new MultiPartSpecificationImpl();
	        spec.setCharset("UTF-8");
	        spec.setContent(new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.txt"));
	        spec.setControlName("file");
	        spec.setFileName("test1.txt");
	        spec.setMimeType("application/octet-stream");
	        return spec;
	    }
	
	@Test
	public void uploadFileUsingMultiPart() throws Exception {	
				
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		headers.put(HttpHeaders.CONTENT_TYPE, "multipart/form-data");
		headers.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		headers.put(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
		headers.put("boundary", Long.toString(new Date().getTime()));

		//Filter the id, name,size from the item resource
		queryParams.put("select", "id,name,size");
		Children children = oneDriveAPI.getRootChildren(OneDrive.API_PATH_DRIVE_ROOT_CHILDREN, queryParams, headers, false);
		String myFolderId = null;

		for (Item item : children.getValue()) {			
			if(item.getName().equals("MyFolder")) {
				myFolderId = item.getId();
			}
		}
	
		//Upload the file into my folder
		
		java.io.File oneDriveTestfile = new java.io.File(System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.txt");
		queryParams.clear();
		//Specify the behavior to use if the file already exists. You can use the values fail, replace, or rename. The default for PUT is replace.
		queryParams.put("@name.conflictBehavior", "rename");

		if (myFolderId != null) {
			String uri = OneDrive.API_PATH_SIMPLE_UPLOAD_BY_CHILDREN_PATH.replace("{parent-id}", myFolderId)
																		 .replace("{filename}", oneDriveTestfile.getName());
			
			MultiPartSpecification multiPartSpecification= build();
			
			
			Response response = oneDriveAPI.uploadFileUsingMultiPart(uri, multiPartSpecification, queryParams, headers, true);

			System.out.println("response code:" + response.getStatusCode());	
		}
		
		//After upload get the list of items in MyFolder
		String uri = OneDrive.API_PATH_GET_CHILDRENS_BY_ITEMID.replace("{item-id}", myFolderId);
		Children folderChildren = oneDriveAPI.getChildrenByItemId(uri, queryParams, headers, true);

		System.out.println("Get children by item id uri:" + uri);
		System.out.println("Size of my folder children:" + folderChildren.getValue().length);		
		System.out.println("Folder contents:" + new ObjectMapper().writeValueAsString(folderChildren));	
	}

	
	//Utility method need to be moved to util package.
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
