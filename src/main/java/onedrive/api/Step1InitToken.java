package onedrive.api;
import onedrive.dto.TokenProduce;
import onedrive.impl.OneDriveAPI;


public class Step1InitToken {
    private static OneDriveAPI oneDriveAPI;
    private static boolean DEBUG = true;
    private static String CLIENT_ID = "************"; // see OneDrive Development Dashboard
    private static String CLIENT_SECRET = "***************"; // see OneDrive Development Dashboard
    private static String AUTHORIZATION_CODE = "***************"; // see description below
    
    //https://login.live.com/oauth20_desktop.srf?code=4f9b1517-8328-7d01-8c58-9db2c82e73fc&lc=1033
    /**
     * This program requires the following values which are defined at the top of the class:
     *
     * CLIENT_ID - unique identification for your client/software/app (see OneDrive Developers Dashboard)
     * CLIENT_SECRET - unique secret for your client, also see the OneDrive Developers Dashboard
     * AUTHORIZATION_CODE - unique authorization code you must generate by calling the following URL which
     * returns an authorization code (which is in the URL):
     *
     *https://login.live.com/oauth20_authorize.srf
     *?client_id=000000004C14FCB2
     *&scope=wl.signin%20wl.offline_access%20onedrive.readwrite%20onedrive.appfolder
     *&response_type=code
     *&redirect_uri=https://login.live.com/oauth20_desktop.srf
     *
     * In theory you should have to run this program only once to get the refresh token. The refresh token
     * can be reused every time to get a new access token . The access token is required to make OneDrive API calls.
     *
     * When done, see the OneDriveTest unit test class to see a few examples of OneDrive API calls. Be sure that
     * all properties (onedrive.properties) are defined in the test resources folder.
     *
     * @param args none used
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        TokenProduce tokenProduce = new TokenProduce(CLIENT_ID, CLIENT_SECRET, AUTHORIZATION_CODE);

        oneDriveAPI = new OneDriveAPI(tokenProduce, DEBUG);
        oneDriveAPI.initTokenByTokenProduce();

        System.out.println("Refresh token: " + tokenProduce.getoAuth20Token().getRefresh_token());
    }
}
