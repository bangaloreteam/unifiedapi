package dropbox.dto;

import com.dropbox.core.json.JsonReader;

public class AccountInfo {
	
	private String country ;
	private String displayName;
	private String referralLink;
	private long userId;
	private Quota quota;
	private static JsonReader<AccountInfo> reader;
	
	@Override
	public String toString() {
		return "AccountInfo [country=" + country + ", displayName="
				+ displayName + ", referralLink=" + referralLink + ", userId="
				+ userId + ", quota=" + quota + "]";
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getReferralLink() {
		return referralLink;
	}

	public void setReferralLink(String referralLink) {
		this.referralLink = referralLink;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Quota getQuota() {
		return quota;
	}

	public void setQuota(Quota quota) {
		this.quota = quota;
	}

	public static JsonReader<AccountInfo> getReader() {
		return reader;
	}

	public static void setReader(JsonReader<AccountInfo> reader) {
		AccountInfo.reader = reader;
	}
	
	
	

}
