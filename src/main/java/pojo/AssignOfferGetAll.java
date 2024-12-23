package pojo;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AssignOfferGetAll {
	@JsonProperty("assignOfferId")
	private Integer assignOfferId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("offerId")
	private Integer offerId;
	@JsonProperty("offerName")
	private String offerName;
	@JsonProperty("productName")
	private String productName;
	@JsonProperty("offerStartDate")
	private String offerStartDate;
	@JsonProperty("offerEndDate")
	private String offerEndDate;
	@JsonProperty("sellPrice")
	private Double sellPrice;
	@JsonProperty("discountPercent")
	private Double discountPercent;
	@JsonProperty("offerPrice")
	private Double offerPrice;
	@JsonProperty("createdBy")
	private String createdBy;
	
	@JsonProperty("assignOfferId")
	public Integer getAssignOfferId() {
	return assignOfferId;
	}

	@JsonProperty("assignOfferId")
	public void setAssignOfferId(Integer assignOfferId) {
	this.assignOfferId = assignOfferId;
	}

	@JsonProperty("productId")
	public Integer getProductId() {
	return productId;
	}

	@JsonProperty("productId")
	public void setProductId(Integer productId) {
	this.productId = productId;
	}

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

	@JsonProperty("productName")
	public String getProductName() {
	return productName;
	}

	@JsonProperty("productName")
	public void setProductName(String productName) {
	this.productName = productName;
	}

	@JsonProperty("offerStartDate")
	public String getOfferStartDate() {
	return offerStartDate;
	}

	@JsonProperty("offerStartDate")
	public void setOfferStartDate(String offerStartDate) {
	this.offerStartDate = offerStartDate;
	}

	@JsonProperty("offerEndDate")
	public String getOfferEndDate() {
	return offerEndDate;
	}

	@JsonProperty("offerEndDate")
	public void setOfferEndDate(String offerEndDate) {
	this.offerEndDate = offerEndDate;
	}

	@JsonProperty("sellPrice")
	public Double getSellPrice() {
	return sellPrice;
	}

	@JsonProperty("sellPrice")
	public void setSellPrice(Double sellPrice) {
	this.sellPrice = sellPrice;
	}

	@JsonProperty("discountPercent")
	public Double getDiscountPercent() {
	return discountPercent;
	}

	@JsonProperty("discountPercent")
	public void setDiscountPercent(Double discountPercent) {
	this.discountPercent = discountPercent;
	}

	@JsonProperty("offerPrice")
	public Double getOfferPrice() {
	return offerPrice;
	}

	@JsonProperty("offerPrice")
	public void setOfferPrice(Double offerPrice) {
	this.offerPrice = offerPrice;
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
	
