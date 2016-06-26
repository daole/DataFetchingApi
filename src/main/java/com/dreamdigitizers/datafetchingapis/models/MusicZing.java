package com.dreamdigitizers.datafetchingapis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "music_zing")
public class MusicZing {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "performer")
    private String performer;

    @Column(name = "link")
    private String link;

    @Column(name = "source")
    private String source;

    @Column(name = "hq")
    private String hq;

    @Column(name = "duration")
    private String duration;

    @Column(name = "lyric")
    private String lyric;

    @Column(name = "mv_link")
    private String mvLink;

    @Column(name = "ad_param")
    private String adParam;

    @Column(name = "back_image")
    private String backImage;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_message")
    private String errorMessage;

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

    public String getPerformer() {
        return this.performer;
    }

    public void setPerformer(String value) {
        this.performer = value;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String value) {
        this.link = value;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String value) {
        this.source = value;
    }

    public String getHq() {
        return this.hq;
    }

    public void setHq(String value) {
        this.hq = value;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String value) {
        this.duration = value;
    }

    public String getLyric() {
        return this.lyric;
    }

    public void setLyric(String value) {
        this.lyric = value;
    }

    public String getMvLink() {
        return this.mvLink;
    }

    public void setMvLink(String value) {
        this.mvLink = value;
    }

    public String getAdParam() {
        return this.adParam;
    }

    public void setAdParam(String value) {
        this.adParam = value;
    }

    public String getBackImage() {
        return this.backImage;
    }

    public void setBackImage(String value) {
        this.backImage = value;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String value) {
        this.errorMessage = value;
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
