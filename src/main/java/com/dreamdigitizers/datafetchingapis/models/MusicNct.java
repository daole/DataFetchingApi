package com.dreamdigitizers.datafetchingapis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "music_nct")
public class MusicNct {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "time")
    private String time;

    @Column(name = "creator")
    private String creator;

    @Column(name = "location")
    private String location;

    @JsonIgnore
    @Column(name = "location_hq")
    private String locationHq;

    @Column(name = "has_hq")
    private String hasHq;

    @Column(name = "info")
    private String info;

    @Column(name = "lyric")
    private String lyric;

    @Column(name = "bg_image")
    private String bgImage;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "new_tab")
    private String newTab;

    @Column(name = "kbit")
    private String kbit;

    @JsonIgnore
    @Column(name = "file_name")
    private String fileName;

    @JsonIgnore
    @Column
    private Date lastFetchDatetime;

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String value) {
        this.creator = value;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String value) {
        this.location = value;
    }

    public String getLocationHq() {
        return this.locationHq;
    }

    public void setLocationHq(String value) {
        this.locationHq = value;
    }

    public String getHasHq() {
        return this.hasHq;
    }

    public void setHasHq(String value) {
        this.hasHq = value;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String value) {
        this.info = value;
    }

    public String getLyric() {
        return this.lyric;
    }

    public void setLyric(String value) {
        this.lyric = value;
    }

    public String getBgImage() {
        return this.bgImage;
    }

    public void setBgImage(String value) {
        this.bgImage = value;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String value) {
        this.avatar = value;
    }

    public String getNewTab() {
        return this.newTab;
    }

    public void setNewTab(String value) {
        this.newTab = value;
    }

    public String getKbit() {
        return this.kbit;
    }

    public void setKbit(String value) {
        this.kbit = value;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String value) {
        this.fileName = value;
    }

    public Date getLastFetchDatetime() {
        return this.lastFetchDatetime;
    }

    public void setLastFetchDatetime(Date lastFetchDatetime) {
        this.lastFetchDatetime = lastFetchDatetime;
    }
}
