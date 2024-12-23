package pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
	
	public class GetAllOffers {

	@JsonProperty("offerId")
	private Integer offerId;
	@JsonProperty("offerName")
	private String offerName;
	@JsonProperty("offerPrice")
	private Float offerPrice;
	@JsonProperty("offerDescription")
	private String offerDescription;
	@JsonProperty("offerType")
	private Integer offerType;
	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("offerId")
	public Integer getOfferId() {
	return offerId;
	}

	@JsonProperty("offerId")
	public void setOfferId(Integer offerId) {
	this.offerId = offerId;
	}

	@JsonProperty("offerName")
	public String getOfferName() {
	return offerName;
	}

	@JsonProperty("offerName")
	public void setOfferName(String offerName) {
	this.offerName = offerName;
	}

	@JsonProperty("offerPrice")
	public Float getOfferPrice() {
	return offerPrice;
	}

	@JsonProperty("offerPrice")
	public void setOfferPrice(Float offerPrice) {
	this.offerPrice = offerPrice;
	}

	@JsonProperty("offerDescription")
	public String getOfferDescription() {
	return offerDescription;
	}

	@JsonProperty("offerDescription")
	public void setOfferDescription(String offerDescription) {
	this.offerDescription = offerDescription;
	}

	@JsonProperty("offerType")
	public Integer getOfferType() {
	return offerType;
	}

	@JsonProperty("offerType")
	public void setOfferType(Integer offerType) {
	this.offerType = offerType;
	}

	@JsonProperty("createdBy")
	public String getCreatedBy() {
	return createdBy;
	}

	@JsonProperty("createdBy")
	public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
	}

	}


