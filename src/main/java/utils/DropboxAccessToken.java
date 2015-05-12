package utils;

import java.util.Locale;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;

public class DropboxAccessToken {
	

	
	public static String getAccessToken() throws Exception
	{
		 final String APP_KEY = "kpuexwy6nue5dq8";
	     final String APP_SECRET = "ys0ukjrzskjm3wh";
	     
	     DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
	     
	     DbxRequestConfig config = new DbxRequestConfig(
	             "JavaTutorial/1.0", Locale.getDefault().toString());
	         DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
	         
	   String authorizeUrl = webAuth.start();
	   System.out.println("authorization url.."+authorizeUrl);
	   
	   System.out.println("1. Go to: " + authorizeUrl);
	   System.out.println("2. Click \"Allow\" (you might have to log in first)");
	   System.out.println("3. Copy the authorization code.");
	  // String code = new BufferedReader( new InputStreamReader(System.in)).readLine().trim();
	   System.out.println("Done the Authorization");
	   String authorizationCode="10HDS1m_jiAAAAAAAAAABwTGPcNHM78lrFYsiMgsF4A";
	   System.out.println("authorization code:"+authorizationCode);
	   DbxAuthFinish authFinish = webAuth.finish(authorizationCode);
	   
	   //getting the accesstoken to make the API requests 
	   String accessToken = authFinish.accessToken;
	   System.out.println("accessToken:"+accessToken);
	   //AccessToken=10HDS1m_jiAAAAAAAAAACIOtx9abbUtRSufy8Wx66gbIzQbw_LLEPCK7Y9ww8dMo
	   return accessToken;
	   
	   //DbxClient client = new DbxClient(config, accessToken);
	   //System.out.println("Linked account: " + client.getAccountInfo().displayName);

	}

}
