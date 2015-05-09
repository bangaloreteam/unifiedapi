package onedrive.impl;

import static com.jayway.restassured.RestAssured.given;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import onedrive.api.OneDrive;
import onedrive.constants.OneDriveConstants;
import onedrive.dto.Children;
import onedrive.dto.Drive;
import onedrive.dto.Item;
import onedrive.dto.OAuth20Token;
import onedrive.dto.TokenProduce;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;

import core.api.HttpCore;

public class OneDriveAPI extends HttpCore implements OneDrive {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	private TokenProduce tokenProduce;
		
	public OneDriveAPI() {
		super(OneDrive.ONEDRIVE_URL_ROOT);
		logger.info("Initializing One Drive API ....");
	}		
	
	public OneDriveAPI(TokenProduce tokenProduce, boolean debug) {
		super(OneDrive.ONEDRIVE_URL_ROOT, debug);
		this.tokenProduce = tokenProduce;
	}
	
	
	public TokenProduce getTokenProduce() {
		// TODO Auto-generated method stub
		return this.tokenProduce;
	}
	
	public boolean initTokenByTokenProduce() {
        if (hasAuthServiceProviderClientSecretAndClientIdAndAuthorizationCode()) {
            logger.info("Client-id, client secret and authorization code found, trying to initialize tokens");
            this.tokenProduce.setoAuth20Token(getTokenByTokenProduce());
            return true;
        } else {
            logger.error("Cannot initialize tokens, missing client-id, client secret or authorization code");
            return false;
        }
    }


	public boolean initAccessTokenByRefreshTokenAndClientId() {
        if (hasAuthServiceProviderRefreshTokenAndClientId()) {
            this.tokenProduce.getoAuth20Token().setAccess_token(this.getAccessTokenByRefreshTokenAndClientId().getAccess_token());
            return true;
        } else {
            return false;
        }
    }
	

    /**
     * Return whether the TokenProduce has a client id and secret and authorization code set.
     * @return true if TokenProduce has a client identification, secret and authorization code set, otherwise false
     */
    private boolean hasAuthServiceProviderClientSecretAndClientIdAndAuthorizationCode() {
        return (StringUtils.isNotEmpty(tokenProduce.getClientId()) && StringUtils.isNotEmpty(tokenProduce.getClientSecret()) &&
                StringUtils.isNotEmpty(tokenProduce.getAuthorizationCode()));
    }

    /**
     * Return whether the TokenProduce has a refresh token and client identification set.
     * @return true if TokenProduce has a refresh token and client identification set, otherwise false
     */
    private boolean hasAuthServiceProviderRefreshTokenAndClientId() {
        return (StringUtils.isNotEmpty(tokenProduce.getoAuth20Token().getRefresh_token()) && StringUtils.isNotEmpty(tokenProduce.getClientId()));
    }
    
   
	/**
	 * This method returns the default drive of the one drive account
	 * @throws Exception 
	 * 
	 */
	public Drive getDefaultDrive(String uri, Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception {
		
		//Add the Auth token to the query params
		queryparams.put(OneDriveConstants.API_PARAM_ACCESS_TOKEN, tokenProduce.getoAuth20Token().getAccess_token());
		
		//invoke the httpcore get api
		Response response = this.doGet(uri, queryparams, headers, logRequestResponse); 	
		
		if (response.getStatusCode() != HttpStatus.SC_OK) throw new Exception(response.asString());
		
		return response.as(Drive.class);		
	}

	
	public String createFolder(String uri, String body, Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception {
		
		//Add the Auth token to the query params
		queryparams.put(OneDriveConstants.API_PARAM_ACCESS_TOKEN, tokenProduce.getoAuth20Token().getAccess_token());
		
		//invoke the httpcore post api
		Response response = this.doPost(uri, body, queryparams, headers, logRequestResponse); 	
		
		if (response.getStatusCode() != HttpStatus.SC_CREATED) throw new Exception(response.asString());
		
		return response.asString();		
	} 
	
	
	public Children getRootChildren(String uri, Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception {
		
		//Add the Auth token to the query params
		queryparams.put(OneDriveConstants.API_PARAM_ACCESS_TOKEN, tokenProduce.getoAuth20Token().getAccess_token());
		
		//invoke the httpcore get api
		Response response = this.doGet(uri, queryparams, headers, logRequestResponse); 	
		
		if (response.getStatusCode() != HttpStatus.SC_OK) throw new Exception(response.asString());
		
		return response.as(Children.class);		
	} 
	
	
	public Children getChildrenByItemId(String uri, Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception {
		
		//Add the Auth token to the query params
		queryparams.put(OneDriveConstants.API_PARAM_ACCESS_TOKEN, tokenProduce.getoAuth20Token().getAccess_token());
		
		//invoke the httpcore get api
		Response response = this.doGet(uri, queryparams, headers, logRequestResponse); 	
		
		if (response.getStatusCode() != HttpStatus.SC_OK) throw new Exception(response.asString());
		
		return response.as(Children.class);		
	} 
	
	
	public Item uploadSimpleFile(String uri, byte[] inputfile,  Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception {

		//Add the Auth token to the query params
		queryparams.put(OneDriveConstants.API_PARAM_ACCESS_TOKEN, tokenProduce.getoAuth20Token().getAccess_token());

		//invoke the httpcore post api
		Response response = this.doPut(uri, inputfile, queryparams, headers, logRequestResponse); 	

		if (response.getStatusCode() != HttpStatus.SC_CREATED) throw new Exception(response.asString());

		return response.as(Item.class);		
	}
	
	
	public Response downloadSimpleFile(String uri,  Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception {
		
		//Add the Auth token to the query params
		//queryparams.put(OneDriveConstants.API_PARAM_ACCESS_TOKEN, tokenProduce.getoAuth20Token().getAccess_token());
		
		headers.put("Authorization", "bearer "+ tokenProduce.getoAuth20Token().getAccess_token());
		
		//invoke the httpcore post api
		Response response = this.doGet(uri, queryparams, headers, logRequestResponse); 	

		if (response.getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY) throw new Exception(response.asString());
		
		return response;	
		//API_PATH_DOWNLOAD_BY_ITEM_ID
	}
	
	
	/**
     * Get an oauth 2.0 object for a TokenProduce.    
     * 
     * @return oauth 2.0 token object
     */
    public OAuth20Token getTokenByTokenProduce() {
        OAuth20Token oAuth20Token = new onedrive.dto.OAuth20Token();
        
        /*
        //Get the code as specified in https://dev.onedrive.com/auth/msa_oauth.htm
        Response response =                
                given().
                	log().all().
                	//queryParam("access_token", this.accessToken).
                	queryParam(API_PARAM_CLIENT_ID, principal.getClientId()).
                	queryParam(API_PARAM_SCOPE, "wl.signin wl.offline_access onedrive.readwrite onedrive.appfolder").
                	queryParam(API_PARAM_RESPONSE_TYPE, "code").
                	queryParam(API_PARAM_REDIRECT_URI, OneDrive.ONEDRIVE_OAUTH20_DESKTOP_REDIRECT_URL).
                	headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).                	
                when().
                get(OneDrive.ONEDRIVE_OAUTH20_AUTHORIZE_URL).andReturn();
        System.out.println("Code flow:" + response.asString());
        */
        try {
        	Response response = 
         
    			given().
    				log().all().
	    			//queryParam("access_token", this.accessToken).
	    			formParam(OneDriveConstants.API_PARAM_CLIENT_ID, tokenProduce.getClientId()).
	    			formParam(OneDriveConstants.API_PARAM_CLIENT_SECRET, tokenProduce.getClientSecret()).
	    			formParam(OneDriveConstants.API_PARAM_CODE, tokenProduce.getAuthorizationCode()).
	    			formParam(OneDriveConstants.API_PARAM_REDIRECT_URI, OneDrive.ONEDRIVE_OAUTH20_DESKTOP_REDIRECT_URL).
	    			formParam(OneDriveConstants.API_PARAM_GRANT_TYPE, OneDrive.ONEDRIVE_GRANT_TYPE_AUTHORIZATION_CODE).
	    			headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
	    			headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED).
    			when().
    				post(OneDrive.ONEDRIVE_OAUTH20_TOKEN_URL);        
        oAuth20Token =  response.as(OAuth20Token.class) ;
        
        System.out.println("Oauth token:" + response.asString());
        oAuth20Token.setStatus(response.getStatusCode());
        } catch (Exception e) {
            logger.error("Can't unmarshall the response into class");
            e.printStackTrace();
        }
        this.tokenProduce.setoAuth20Token(oAuth20Token);
        return oAuth20Token;
    }

    /**
     * Get the access token for a TokenProduce by refresh token and client identification, requiring the TokenProduce to have
     * the refresh token and client identification set.
     *
     * @return oauth 2.0 token object
     */
    public OAuth20Token getAccessTokenByRefreshTokenAndClientId() {

    	long startTime = System.currentTimeMillis();
    	OAuth20Token oAuth20Token = new OAuth20Token();

    	try {
    		Response response = 

    				given().
    				log().all().
    				formParam(OneDriveConstants.API_PARAM_CLIENT_ID, tokenProduce.getClientId()).
    				formParam(OneDriveConstants.API_PARAM_REDIRECT_URI, OneDrive.ONEDRIVE_OAUTH20_DESKTOP_REDIRECT_URL).
    				formParam(OneDriveConstants.API_PARAM_CLIENT_SECRET, tokenProduce.getClientSecret()).    				
    				formParam(OneDriveConstants.API_PARAM_REFRESH_TOKEN, tokenProduce.getoAuth20Token().getRefresh_token()).
    				formParam(OneDriveConstants.API_PARAM_GRANT_TYPE, OneDrive.ONEDRIVE_GRANT_TYPE_REFRESH_TOKEN).    				
    				headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
    				headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED).
    				when().
    				post(OneDrive.ONEDRIVE_OAUTH20_TOKEN_URL);

    		oAuth20Token =  response.as(OAuth20Token.class) ;    		
    		//System.out.println("Oauth token:" + response.asString());

    		oAuth20Token.setStatus(response.getStatusCode());
    	} catch (Exception e) {
    		logger.error("Can't unmarshall the response into class");
    		e.printStackTrace();
    	}
    	long elapsedTime = System.currentTimeMillis() - startTime;
    	logger.debug("Executed getAccessTokenByRefreshTokenAndClientId in " + elapsedTime + " ms");
    	this.tokenProduce.setoAuth20Token(oAuth20Token);
    	return oAuth20Token;
    }
   
	
}
