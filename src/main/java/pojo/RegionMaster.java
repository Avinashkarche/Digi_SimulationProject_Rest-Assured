package pojo;

public class RegionMaster {
	
	private int id;
	private String countryName;
	private String callingCode;
	
	
	public void setid(int id) 
	{
		this.id=id;

	}
	
	public int getid() 
	{
		return id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}

}
