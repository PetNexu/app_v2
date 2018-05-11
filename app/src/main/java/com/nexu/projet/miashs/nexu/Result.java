package com.nexu.projet.miashs.nexu;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("AddjMulti")
    @Expose
    private Double addjMulti;
    @SerializedName("AddjMulti2")
    @Expose
    private Double addjMulti2;
    @SerializedName("AddjValue")
    @Expose
    private Double addjValue;
    @SerializedName("AddjValue2")
    @Expose
    private Double addjValue2;
    @SerializedName("BatteryLevel")
    @Expose
    private Integer batteryLevel;
    @SerializedName("CustomImage")
    @Expose
    private Integer customImage;
    @SerializedName("Data")
    @Expose
    private String data;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DewPoint")
    @Expose
    private String dewPoint;
    @SerializedName("Favorite")
    @Expose
    private Integer favorite;
    @SerializedName("HardwareID")
    @Expose
    private Integer hardwareID;
    @SerializedName("HardwareName")
    @Expose
    private String hardwareName;
    @SerializedName("HardwareType")
    @Expose
    private String hardwareType;
    @SerializedName("HardwareTypeVal")
    @Expose
    private Integer hardwareTypeVal;
    @SerializedName("HaveTimeout")
    @Expose
    private Boolean haveTimeout;
    @SerializedName("Humidity")
    @Expose
    private Integer humidity;
    @SerializedName("HumidityStatus")
    @Expose
    private String humidityStatus;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Notifications")
    @Expose
    private String notifications;
    @SerializedName("PlanID")
    @Expose
    private String planID;
    @SerializedName("PlanIDs")
    @Expose
    private List<Integer> planIDs = null;
    @SerializedName("Protected")
    @Expose
    private Boolean _protected;
    @SerializedName("ShowNotifications")
    @Expose
    private Boolean showNotifications;
    @SerializedName("SignalLevel")
    @Expose
    private String signalLevel;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("SubType")
    @Expose
    private String subType;
    @SerializedName("Temp")
    @Expose
    private Double temp;
    @SerializedName("Timers")
    @Expose
    private String timers;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("TypeImg")
    @Expose
    private String typeImg;
    @SerializedName("Unit")
    @Expose
    private Integer unit;
    @SerializedName("Used")
    @Expose
    private Integer used;
    @SerializedName("XOffset")
    @Expose
    private String xOffset;
    @SerializedName("YOffset")
    @Expose
    private String yOffset;
    @SerializedName("idx")
    @Expose
    private String idx;

    public Double getAddjMulti() {
        return addjMulti;
    }

    public void setAddjMulti(Double addjMulti) {
        this.addjMulti = addjMulti;
    }

    public Double getAddjMulti2() {
        return addjMulti2;
    }

    public void setAddjMulti2(Double addjMulti2) {
        this.addjMulti2 = addjMulti2;
    }

    public Double getAddjValue() {
        return addjValue;
    }

    public void setAddjValue(Double addjValue) {
        this.addjValue = addjValue;
    }

    public Double getAddjValue2() {
        return addjValue2;
    }

    public void setAddjValue2(Double addjValue2) {
        this.addjValue2 = addjValue2;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Integer getCustomImage() {
        return customImage;
    }

    public void setCustomImage(Integer customImage) {
        this.customImage = customImage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Integer getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(Integer hardwareID) {
        this.hardwareID = hardwareID;
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public Integer getHardwareTypeVal() {
        return hardwareTypeVal;
    }

    public void setHardwareTypeVal(Integer hardwareTypeVal) {
        this.hardwareTypeVal = hardwareTypeVal;
    }

    public Boolean getHaveTimeout() {
        return haveTimeout;
    }

    public void setHaveTimeout(Boolean haveTimeout) {
        this.haveTimeout = haveTimeout;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getHumidityStatus() {
        return humidityStatus;
    }

    public void setHumidityStatus(String humidityStatus) {
        this.humidityStatus = humidityStatus;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public List<Integer> getPlanIDs() {
        return planIDs;
    }

    public void setPlanIDs(List<Integer> planIDs) {
        this.planIDs = planIDs;
    }

    public Boolean getProtected() {
        return _protected;
    }

    public void setProtected(Boolean _protected) {
        this._protected = _protected;
    }

    public Boolean getShowNotifications() {
        return showNotifications;
    }

    public void setShowNotifications(Boolean showNotifications) {
        this.showNotifications = showNotifications;
    }

    public String getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(String signalLevel) {
        this.signalLevel = signalLevel;
    }

    public String getStatus() { return status;}

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getTimers() {
        return timers;
    }

    public void setTimers(String timers) {
        this.timers = timers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public String getXOffset() {
        return xOffset;
    }

    public void setXOffset(String xOffset) {
        this.xOffset = xOffset;
    }

    public String getYOffset() {
        return yOffset;
    }

    public void setYOffset(String yOffset) {
        this.yOffset = yOffset;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

}