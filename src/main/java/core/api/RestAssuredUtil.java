package core.api;
import com.jayway.restassured.RestAssured;

public class RestAssuredUtil
{
	public static final void setup(String baseUri) {		
		RestAssured.baseURI  = baseUri;		
	}
	
	public static final void setup(String baseUri, boolean debug) {
		setup(baseUri);		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();		
	}
}