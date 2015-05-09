package onedrive.api;
import java.util.Map;

import onedrive.dto.TokenProduce;
import onedrive.dto.Drive;
import onedrive.dto.Quota;
import onedrive.dto.User;
public interface OneDrive {
	
	public static final String ONEDRIVE_OAUTH20_AUTHORIZE_URL 			= "https://login.live.com/oauth20_authorize.srf";
	public static final String ONEDRIVE_OAUTH20_TOKEN_URL 				= "https://login.live.com/oauth20_token.srf";
	public static final String ONEDRIVE_OAUTH20_DESKTOP_REDIRECT_URL 	= "https://login.live.com/oauth20_desktop.srf";
	public static final String ONEDRIVE_GRANT_TYPE_AUTHORIZATION_CODE 	= "authorization_code";
	public static final String ONEDRIVE_GRANT_TYPE_REFRESH_TOKEN 		= "refresh_token";	
	public static final String ONEDRIVE_URL_ROOT  		   				= "https://api.onedrive.com/v1.0";			
	public static final String API_PATH_DEFAULT_DRIVE      				= "/drive";
	public static final String API_PATH_DRIVES	  		   				= "/drives";
	public static final String API_PATH_DRIVE_ROOT_CHILDREN				= "/drive/root/children";
	public static final String API_PATH_DRIVE_BY_ID        				= "/drives/{drive-id}";
	public static final String API_PATH_GET_CHILDRENS_BY_ITEMID			= "/drive/items/{item-id}/children";
	
	
	/** File Upload APIS **/
	
	/*
	 * PUT /drive/items/{parent-id}:/{filename}:/content
	 * PUT /drive/root:/{parent-path}/{filename}:/content
	 * PUT /drive/items/{parent-id}/children/{filename}/content
	 */
	
	public static final String API_PATH_SIMPLE_UPLOAD_BY_PARENT_ID	 	= "/drive/items/{parent-id}:/{filename}:/content";
	public static final String API_PATH_SIMPLE_UPLOAD_BY_PARENT_PATH	= "/drive/root:/{parent-path}/{filename}:/content";
	public static final String API_PATH_SIMPLE_UPLOAD_BY_CHILDREN_PATH	= "/drive/items/{parent-id}/children/{filename}/content";
	
	
	/**File download apis **/
	/* GET /drive/items/{item-id}/content
	   GET /drive/root:/{path and filename}:/content
	*/
	 
	public static final String API_PATH_DOWNLOAD_BY_ITEM_ID	 			= "/drive/items/{item-id}/content";
	public static final String API_PATH_DOWNLOAD_BY_PATH_AND_FILENAME	= "/drive/root:/{path and filename}:/content";
	
	
	
	public Drive getDefaultDrive(String uri, Map<String, String> queryparams, Map<String, String> headers, boolean logRequestResponse) throws Exception;
	
	/*User getUser() throws Exception;
	
	Quota getQuota() throws Exception;*/
	
	/**
     * Initialize the token (e.g. refresh token) for the oauth service provider by Auth Service provider.
     * @return true if the token initialization was successful, otherwise false
     */
    boolean initTokenByTokenProduce();

    /**
     * Initialize the one drive access token by using the refresh token and client identification.
     * @return true if access token initialization was successful, otherwise false
     */
    boolean initAccessTokenByRefreshTokenAndClientId();

    /**
     * Return the current auth .
     *
     * @return current TokenProduce details
     */
    TokenProduce getTokenProduce();

}
