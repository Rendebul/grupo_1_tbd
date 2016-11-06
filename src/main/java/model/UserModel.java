package model;

public class UserModel
{
	//falta userEntities

	private boolean contributorsEnabled;
	private String createdAt;
	private boolean defaultProfile;
	private boolean defaultProfileImage;
	private String description;
	private int favouritesCount;
	private boolean followRequestSent;
	private boolean following;
	private int followersCount;
	private int friendsCount;
	private boolean geoEnabled;
	private long id;
	private String idStr;
	private boolean isTranslator;
	private String lang;
	private int listedCount;
	private String location;
	private String name;
	private boolean notifications;
	private String profileBackgroundColor;
	private String profileBackgroundImageUrl;
	private String profileBackgroundImageUrlHttps;
	private boolean profileBackgroundTile;
	private String profileBannerUrl;
	private String profileImageUrl;
	private String profileImageUrlHttps;
	private String profileLinkColor;
	private String profileSidebarBorderColor;
	private String profileSidebarFillColor;
	private String profileTextColor;
	private boolean profileUseBackgroundImage;
	private boolean isProtected;
	private String screenName;
	private boolean showAllInlineMedia;
	private TweetModel status;
	private int statusesCount;
	private String timeZone;
	private String url;
	private int utcOffset;
	private boolean verified;
	private String withheldInCountries;
	private String withheldScope;

	 public boolean isContributorsEnabled() {
        return this.contributorsEnabled;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public boolean isDefaultProfile() {
        return this.defaultProfile;
    }

    public boolean isDefaultProfileImage() {
        return this.defaultProfileImage;
    }

    public String getDescription() {
        return this.description;
    }

    public int getFavouritesCount() {
        return this.favouritesCount;
    }

    public boolean isFollowRequestSent() {
        return this.followRequestSent;
    }

    public boolean isFollowing() {
        return this.following;
    }

    public int getFollowersCount() {
        return this.followersCount;
    }

    public int getFriendsCount() {
        return this.friendsCount;
    }

    public boolean isGeoEnabled() {
        return this.geoEnabled;
    }

    public long getId() {
        return this.id;
    }

    public String getIdStr() {
        return this.idStr;
    }

    public boolean isIsTranslator() {
        return this.isTranslator;
    }

    public String getLang() {
        return this.lang;
    }

    public int getListedCount() {
        return this.listedCount;
    }

    public String getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNotifications() {
        return this.notifications;
    }

    public String getProfileBackgroundColor() {
        return this.profileBackgroundColor;
    }

    public String getProfileBackgroundImageUrl() {
        return this.profileBackgroundImageUrl;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return this.profileBackgroundImageUrlHttps;
    }

    public boolean isProfileBackgroundTile() {
        return this.profileBackgroundTile;
    }

    public String getProfileBannerUrl() {
        return this.profileBannerUrl;
    }

    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return this.profileImageUrlHttps;
    }

    public String getProfileLinkColor() {
        return this.profileLinkColor;
    }

    public String getProfileSidebarBorderColor() {
        return this.profileSidebarBorderColor;
    }

    public String getProfileSidebarFillColor() {
        return this.profileSidebarFillColor;
    }

    public String getProfileTextColor() {
        return this.profileTextColor;
    }

    public boolean isProfileUseBackgroundImage() {
        return this.profileUseBackgroundImage;
    }

    public boolean isIsProtected() {
        return this.isProtected;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public boolean isShowAllInlineMedia() {
        return this.showAllInlineMedia;
    }

    public int getStatusesCount() {
        return this.statusesCount;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getUrl() {
        return this.url;
    }

    public int getUtcOffset() {
        return this.utcOffset;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public String getWithheldInCountries() {
        return this.withheldInCountries;
    }

    public String getWithheldScope() {
        return this.withheldScope;
    }

    public void setContributorsEnabled(boolean contributorsEnabled) {
        this.contributorsEnabled = contributorsEnabled;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDefaultProfile(boolean defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    public void setDefaultProfileImage(boolean defaultProfileImage) {
        this.defaultProfileImage = defaultProfileImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public void setFollowRequestSent(boolean followRequestSent) {
        this.followRequestSent = followRequestSent;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public void setIsTranslator(boolean isTranslator) {
        this.isTranslator = isTranslator;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setListedCount(int listedCount) {
        this.listedCount = listedCount;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public void setProfileBackgroundColor(String profileBackgroundColor) {
        this.profileBackgroundColor = profileBackgroundColor;
    }

    public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
        this.profileBackgroundImageUrl = profileBackgroundImageUrl;
    }

    public void setProfileBackgroundImageUrlHttps(String profileBackgroundImageUrlHttps) {
        this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
    }

    public void setProfileBackgroundTile(boolean profileBackgroundTile) {
        this.profileBackgroundTile = profileBackgroundTile;
    }

    public void setProfileBannerUrl(String profileBannerUrl) {
        this.profileBannerUrl = profileBannerUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    public void setProfileLinkColor(String profileLinkColor) {
        this.profileLinkColor = profileLinkColor;
    }

    public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
        this.profileSidebarBorderColor = profileSidebarBorderColor;
    }

    public void setProfileSidebarFillColor(String profileSidebarFillColor) {
        this.profileSidebarFillColor = profileSidebarFillColor;
    }

    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    public void setProfileUseBackgroundImage(boolean profileUseBackgroundImage) {
        this.profileUseBackgroundImage = profileUseBackgroundImage;
    }

    public void setIsProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setShowAllInlineMedia(boolean showAllInlineMedia) {
        this.showAllInlineMedia = showAllInlineMedia;
    }

    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setWithheldInCountries(String withheldInCountries) {
        this.withheldInCountries = withheldInCountries;
    }

    public void setWithheldScope(String withheldScope) {
        this.withheldScope = withheldScope;
    }

}