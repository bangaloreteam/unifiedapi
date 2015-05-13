package core.api;

import static com.jayway.restassured.RestAssured.given;




import java.io.File;
import java.util.Map;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.MultiPartSpecification;



public class HttpCore {
	
	public HttpCore(String baseUri) {
		RestAssuredUtil.setup(baseUri);		
	}
	
	public HttpCore(String baseUri, boolean debug) {
		RestAssuredUtil.setup(baseUri, debug);		
	}
	
	
	/**
	 * This is the core http get to retrieve the http response for the given parameters
	 * @param uri
	 * @param queryparams
	 * @param headers
	 * @param logAll
	 * @return
	 */
	public Response doGet(String uri, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) {
        
		Response response = 				
				given().
						log().all(logAll).
			    		queryParams(queryparams).				
			    		headers(headers).	    		
			    when().
			    		get(uri);
				
		return response;		
    }
	
	public Response multiPartPostSimpleFile(String uri, File file, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll)  {
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
	
	public Response multiPartPostSimpleFile(String uri, MultiPartSpecification multiPartspec, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll)  {
		Response response = 			
				given().
				        multiPart(multiPartspec).
						log().all(logAll).
						queryParams(queryparams).				
			    		headers(headers).	    		
			    when().
			    		post(uri);
		
				
		return response;
	}

	
	/**
	 * This is the core http delete to delete a resource
	 * @param uri
	 * @param queryparams
	 * @param headers
	 * @param logAll
	 * @return
	 */
	public Response doDelete(String uri, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) {
		Response response = 				
				given().
						log().all(logAll).
			    		queryParams(queryparams).				
			    		headers(headers).	    		
			    when().
			    		delete(uri);
				
		return response;
	}

	/**
	 * This is the core http post to create a resource using post method
	 * @param uri
	 * @param postBody
	 * @param queryparams
	 * @param headers
	 * @param logAll
	 * @return
	 */
	public Response doPost(String uri, String postBody, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) {
		Response response = 				
				given().
						log().all(logAll).
						body(postBody).
			    		queryParams(queryparams).				
			    		headers(headers).	    		
			    when().
			    		post(uri);
				
		return response;
	}
	
	
	
	/**
	 * This is the core http post to put a resource using post method
	 * @param uri
	 * @param postBody
	 * @param queryparams
	 * @param headers
	 * @param logAll
	 * @return
	 */
	public Response doPut(String uri, byte[] postBody, Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) {
		Response response = 				
				given().
						log().all(logAll).
						body(postBody).
			    		queryParams(queryparams).				
			    		headers(headers).	    		
			    when().
			    		put(uri);
				
		return response;
	}	
   
}
