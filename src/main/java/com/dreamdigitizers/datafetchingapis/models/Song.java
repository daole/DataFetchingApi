package com.dreamdigitizers.datafetchingapis.models;

public class Song {
    private String id;
    private String title;
    private String performer;
    private String link;
    private String source;
    private String hq;
    private String duration;
    private String lyric;
    private String mvLink;
    private String adParam;
    private String backImage;
    private String errorCode;
    private String errorMessage;

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
}
