package io.spring.trucker.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;


/*
*  "vin": "1HGCR2F3XFA027534",
   "latitude": 41.803194,
   "longitude": -88.144406,
   "timestamp": "2017-05-25T17:31:25.268Z",
   "fuelVolume": 1.5,
   "speed": 85,
   "engineHp": 240,
   "checkEngineLightOn": false,
   "engineCoolantLow": true,
   "cruiseControlOn": true,
   "engineRpm": 6300,
   "tires": {
      "frontLeft": 34,
      "frontRight": 36,
      "rearLeft": 29,
      "rearRight": 34
   }*/
@Entity
public class Readings {


    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String rid;

    @Column(columnDefinition = "VARCHAR(17)")
    private String vin;

    @Column(columnDefinition = "Decimal(10,8)")
    private Double latitude;

    @Column(columnDefinition = "Decimal(10,8)")
    private Double longitude;

    @Column(columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "YYYY-MM-DDThh:mm:ss.sTZD")
    private Date timestamp;
    @Column(columnDefinition = "Decimal(6,3)")
    private Double fuelVolume;
    @Column(columnDefinition = "INTEGER(10)")
    private Integer speed;
    @Column(columnDefinition = "INTEGER(10)")
    private Integer engineHp;
    @Column(nullable = false, columnDefinition = "BIT", length = 1)
    private boolean checkEngineLightOn;
    @Column(nullable = false, columnDefinition = "BIT", length = 1)
    private boolean engineCoolantLow;
    @Column(nullable = false, columnDefinition = "BIT", length = 1)
    private boolean cruiseControlOn;
    @Column(columnDefinition = "INTEGER(10)")
    private Integer engineRpm;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Tires tires;

    public Readings() {
        this.rid= UUID.randomUUID().toString();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return DateFormat.getDateTimeInstance().format(timestamp);
    }


    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(Double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(Integer engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public Integer getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(Integer engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    @Override
    public String toString() {
        return "Readings{" +
                "rid='" + rid + '\'' +
                ", vin='" + vin + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", fuelVolume=" + fuelVolume +
                ", speed=" + speed +
                ", engineHp=" + engineHp +
                ", checkEngineLightOn=" + checkEngineLightOn +
                ", engineCoolantLow=" + engineCoolantLow +
                ", cruiseControlOn=" + cruiseControlOn +
                ", engineRpm=" + engineRpm +
                ", tires=" + tires +
                '}';
    }
}

