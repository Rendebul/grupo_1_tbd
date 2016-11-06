package model;

public class PlaceModel
{

	//falta boundingBox
	//falta attributes
	private String country;
	private String countryCode;
	private String fullName;
	private String id;
	private String name;
	private String placeType;
	private String url;

	public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getUrl() {
        return url;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}