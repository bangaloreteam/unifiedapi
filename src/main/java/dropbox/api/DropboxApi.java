package dropbox.api;

import java.util.Map;

import onedrive.api.OneDrive;
import onedrive.dto.TokenProduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;

import core.api.HttpCore;

public class DropboxApi extends HttpCore {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
			
	public DropboxApi() {
		super(DropboxService.DROPBOX_API_CONTENT_ROOT);
		logger.info("Initializing One Drive API ....");
	}	
	public DropboxApi(String dropBoxApiOtherUrl)
	{
		super(dropBoxApiOtherUrl);
	}
	
	
	
	@Override
	public Response doDelete(String uri, Map<String, ?> queryparams,
			Map<String, ?> headers, boolean logAll) {
		// TODO Auto-generated method stub
		return super.doDelete(uri, queryparams, headers, logAll);
	}
	
	@Override
	public Response doGet(String uri, Map<String, ?> queryparams,
			Map<String, ?> headers, boolean logAll) {
		// TODO Auto-generated method stub
		return super.doGet(uri, queryparams, headers, logAll);
	}
	@Override
	public Response doPost(String uri, String postBody,
			Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) {
		// TODO Auto-generated method stub
		return super.doPost(uri, postBody, queryparams, headers, logAll);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public Response doPut(String uri, byte[] postBody,
			Map<String, ?> queryparams, Map<String, ?> headers, boolean logAll) {
		// TODO Auto-generated method stub
		return super.doPut(uri, postBody, queryparams, headers, logAll);
	}

}
