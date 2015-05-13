package core.api;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import static org.hamcrest.Matchers.is;

import com.jayway.restassured.response.Response;

public class HttpMultipart {
	
	public HttpMultipart(String baseUri) {
		RestAssuredUtil.setup(baseUri);		
	}
	
	public HttpMultipart(String baseUri, boolean debug) {
		RestAssuredUtil.setup(baseUri, debug);		
	}
	
	/**
	Specify a file to upload to the server using multi-part form data uploading. 
	It will assume that the control name is file and the mime-type is application/octet-stream. 
	If this is not what you want please use an overloaded method.
	Parameters:
	**/
	
	public Response multiPartPostSimpleFile(String uri, String strFile, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) throws Exception {
		File file=new File(strFile);
		Response response = 			
				given().
				        multiPart(file).
						log().all(logAll).
						queryParams(queryparams).				
			    		headers(headers).	    		
			    when().
			    		post(uri);
		
				
		return response;
	}

}
