package com.dreamdigitizers.datafetchingapis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicZingApi {
    @JsonProperty("song_id")
    private String songId;

    @JsonProperty("song_id_encode")
    private String songIdEncode;

    @JsonProperty("video_id_encode")
    private String videoIdEncode;

    @JsonProperty("title")
    private String title;

    @JsonProperty("artist_id")
    private String artistId;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("album_id")
    private String albumId;

    @JsonProperty("album")
    private String album;

    @JsonProperty("composer_id")
    private String composerId;

    @JsonProperty("composer")
    private String composer;

    @JsonProperty("genre_id")
    private String genreId;

    @JsonProperty("isrc")
    private String isrc;

    @JsonProperty("zaloid")
    private String zaloId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("is_hit")
    private String isHit;

    @JsonProperty("is_official")
    private String isOfficial;

    @JsonProperty("download_status")
    private String downloadStatus;

    @JsonProperty("copyright")
    private String copyright;

    @JsonProperty("co_id")
    private String coId;

    @JsonProperty("ad_status")
    private String adStatus;

    @JsonProperty("license_status")
    private String licenseStatus;

    @JsonProperty("download_disable")
    private String downloadDisable;

    @JsonProperty("vn_only")
    private String vnOnly;

    @JsonProperty("total_play")
    private String totalPlay;

    @JsonProperty("link")
    private String link;

    @JsonProperty("source")
    private Source source;

    @JsonProperty("link_download")
    private LinkDownload linkDownload;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("album_cover")
    private String albumCover;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("likes")
    private String likes;

    @JsonProperty("like_this")
    private String likeThis;

    @JsonProperty("favourites")
    private String favourites;

    @JsonProperty("favourite_this")
    private String favouriteThis;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("genre_name")
    private String genreName;

    @JsonProperty("lyrics_file")
    private String lyricsFile;

    @JsonProperty("video")
    private Video video;

    @JsonProperty("response")
    private Response response;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongIdEncode() {
        return songIdEncode;
    }

    public void setSongIdEncode(String songIdEncode) {
        this.songIdEncode = songIdEncode;
    }

    public String getVideoIdEncode() {
        return videoIdEncode;
    }

    public void setVideoIdEncode(String videoIdEncode) {
        this.videoIdEncode = videoIdEncode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getComposerId() {
        return composerId;
    }

    public void setComposerId(String composerId) {
        this.composerId = composerId;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getZaloId() {
        return zaloId;
    }

    public void setZaloId(String zaloId) {
        this.zaloId = zaloId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsHit() {
        return isHit;
    }

    public void setIsHit(String isHit) {
        this.isHit = isHit;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    public String getLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public String getDownloadDisable() {
        return downloadDisable;
    }

    public void setDownloadDisable(String downloadDisable) {
        this.downloadDisable = downloadDisable;
    }

    public String getVnOnly() {
        return vnOnly;
    }

    public void setVnOnly(String vnOnly) {
        this.vnOnly = vnOnly;
    }

    public String getTotalPlay() {
        return totalPlay;
    }

    public void setTotalPlay(String totalPlay) {
        this.totalPlay = totalPlay;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public LinkDownload getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(LinkDownload linkDownload) {
        this.linkDownload = linkDownload;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikeThis() {
        return likeThis;
    }

    public void setLikeThis(String likeThis) {
        this.likeThis = likeThis;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

    public String getFavouriteThis() {
        return favouriteThis;
    }

    public void setFavouriteThis(String favouriteThis) {
        this.favouriteThis = favouriteThis;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getLyricsFile() {
        return lyricsFile;
    }

    public void setLyricsFile(String lyricsFile) {
        this.lyricsFile = lyricsFile;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source {
        @JsonProperty("128")
        private String _128;

        @JsonProperty("320")
        private String _320;

        @JsonProperty("lossless")
        private String lossless;

        public String get_128() {
            return _128;
        }

        public void set_128(String _128) {
            this._128 = _128;
        }

        public String get_320() {
            return _320;
        }

        public void set_320(String _320) {
            this._320 = _320;
        }

        public String getLossless() {
            return lossless;
        }

        public void setLossless(String lossless) {
            this.lossless = lossless;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LinkDownload {
        @JsonProperty("128")
        private String _128;

        @JsonProperty("320")
        private String _320;

        @JsonProperty("lossless")
        private String lossless;

        public String get_128() {
            return _128;
        }

        public void set_128(String _128) {
            this._128 = _128;
        }

        public String get_320() {
            return _320;
        }

        public void set_320(String _320) {
            this._320 = _320;
        }

        public String getLossless() {
            return lossless;
        }

        public void setLossless(String lossless) {
            this.lossless = lossless;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Video {
        @JsonProperty("video_id")
        private String videoId;

        @JsonProperty("song_id_encode")
        private String songIdEncode;

        @JsonProperty("title")
        private String title;

        @JsonProperty("artist")
        private String artist;

        @JsonProperty("thumbnail")
        private String thumbnail;

        @JsonProperty("duration")
        private String duration;

        @JsonProperty("download_status")
        private String downloadStatus;

        @JsonProperty("copyright")
        private String copyright;

        @JsonProperty("co_id")
        private String coId;

        @JsonProperty("ad_status")
        private String adStatus;

        @JsonProperty("license_status")
        private String licenseStatus;

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getSongIdEncode() {
            return songIdEncode;
        }

        public void setSongIdEncode(String songIdEncode) {
            this.songIdEncode = songIdEncode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDownloadStatus() {
            return downloadStatus;
        }

        public void setDownloadStatus(String downloadStatus) {
            this.downloadStatus = downloadStatus;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCoId() {
            return coId;
        }

        public void setCoId(String coId) {
            this.coId = coId;
        }

        public String getAdStatus() {
            return adStatus;
        }

        public void setAdStatus(String adStatus) {
            this.adStatus = adStatus;
        }

        public String getLicenseStatus() {
            return licenseStatus;
        }

        public void setLicenseStatus(String licenseStatus) {
            this.licenseStatus = licenseStatus;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        @JsonProperty("msgCode")
        private String msgCode;

        @JsonProperty("msg")
        private String msg;

        public String getMsgCode() {
            return msgCode;
        }

        public void setMsgCode(String msgCode) {
            this.msgCode = msgCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
