package com.forthbutton.telematics.entity;

import java.util.Date;

import com.google.code.morphia.annotations.Id;

//@Document(collection = "vehicle_position")
public class EventData implements java.io.Serializable {

    private static final long serialVersionUID = -600611827430072453L;

    @Id
    private String id;
    private String suid;
    private Integer report_powerfailure;
    private Integer report_undervoltage;
    private Integer report_powerfailure_recovery;
    private Integer report_undervoltage_recovery;
    private Integer report_speeding;
    private Integer report_dismantle;
    private Integer report_bluetooth;
    private Integer bluetooth_state;
    private Integer acc_state;
    private Integer gps_fixed_position;
    private Double latitude;
    private Double longitude;
    private Short height;
    private Short speed;
    private Short direction;
    private Date report_time;
    private Date create_time;

    public Integer getReport_bluetooth() {
        return report_bluetooth;
    }

    public void setReport_bluetooth(Integer report_bluetooth) {
        this.report_bluetooth = report_bluetooth;
    }

    public Integer getBluetooth_state() {
        return bluetooth_state;
    }

    public void setBluetooth_state(Integer bluetooth_state) {
        this.bluetooth_state = bluetooth_state;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public Integer getReport_powerfailure() {
        return report_powerfailure;
    }

    public void setReport_powerfailure(Integer report_powerfailure) {
        this.report_powerfailure = report_powerfailure;
    }

    public Integer getReport_undervoltage() {
        return report_undervoltage;
    }

    public void setReport_undervoltage(Integer report_undervoltage) {
        this.report_undervoltage = report_undervoltage;
    }

    public Integer getReport_powerfailure_recovery() {
        return report_powerfailure_recovery;
    }

    public void setReport_powerfailure_recovery(Integer report_powerfailure_recovery) {
        this.report_powerfailure_recovery = report_powerfailure_recovery;
    }

    public Integer getReport_undervoltage_recovery() {
        return report_undervoltage_recovery;
    }

    public void setReport_undervoltage_recovery(Integer report_undervoltage_recovery) {
        this.report_undervoltage_recovery = report_undervoltage_recovery;
    }

    public Integer getReport_speeding() {
        return report_speeding;
    }

    public void setReport_speeding(Integer report_speeding) {
        this.report_speeding = report_speeding;
    }

    public Integer getReport_dismantle() {
        return report_dismantle;
    }

    public void setReport_dismantle(Integer report_dismantle) {
        this.report_dismantle = report_dismantle;
    }

    public Integer getAcc_state() {
        return acc_state;
    }

    public void setAcc_state(Integer acc_state) {
        this.acc_state = acc_state;
    }

    public Integer getGps_fixed_position() {
        return gps_fixed_position;
    }

    public void setGps_fixed_position(Integer gps_fixed_position) {
        this.gps_fixed_position = gps_fixed_position;
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

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Short getSpeed() {
        return speed;
    }

    public void setSpeed(Short speed) {
        this.speed = speed;
    }

    public Short getDirection() {
        return direction;
    }

    public void setDirection(Short direction) {
        this.direction = direction;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


}
