package dropbox.dto;

import com.dropbox.core.json.JsonReader;

public class Quota {
	
	private long normal;
	private long shared;
	private long total;
	private static JsonReader<Quota> reader;
	

	public long getNormal() {
		return normal;
	}
	public void setNormal(long normal) {
		this.normal = normal;
	}
	public long getShared() {
		return shared;
	}
	public void setShared(long shared) {
		this.shared = shared;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public static JsonReader<Quota> getReader() {
		return reader;
	}
	public static void setReader(JsonReader<Quota> reader) {
		Quota.reader = reader;
	}
	
	
}
