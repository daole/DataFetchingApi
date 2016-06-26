package com.dreamdigitizers.datafetchingapis.services.classes;

import com.dreamdigitizers.datafetchingapis.models.MusicNct;
import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceNct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceNct implements IServiceNct {
    @Value("${nct.htmlElementId}")
    private String htmlElementId;

    @Value("${nct.searchText}")
    private String searchText;

    @Value("${nct.xmlElement.title}")
    private String xmlElementTitle;

    @Value("${nct.xmlElement.time}")
    private String xmlElementTime;

    @Value("${nct.xmlElement.creator}")
    private String xmlElementCreator;

    @Value("${nct.xmlElement.location}")
    private String xmlElementLocation;

    @Value("${nct.xmlElement.locationHq}")
    private String xmlElementLocationHq;

    @Value("${nct.xmlElement.hasHq}")
    private String xmlElementHasHq;

    @Value("${nct.xmlElement.info}")
    private String xmlElementInfo;

    @Value("${nct.xmlElement.lyric}")
    private String xmlElementLyric;

    @Value("${nct.xmlElement.bgImage}")
    private String xmlElementBgImage;

    @Value("${nct.xmlElement.avatar}")
    private String xmlElementAvatar;

    @Value("${nct.xmlElement.newTab}")
    private String xmlElementNewTab;

    @Value("${nct.xmlElement.kbit}")
    private String xmlElementKbit;

    @Value("${nct.xmlElement.key}")
    private String xmlElementKey;

    @Override
    public MusicNct fetch(String url) throws IOException {
        MusicNct musicNct = null;
        Document songPageDocument = Jsoup.connect(url).get();
        Element element = songPageDocument.select(this.htmlElementId).first();
        Element nextElement = element.nextElementSibling();
        String nextElementText = nextElement.html();
        Pattern pattern = Pattern.compile(this.searchText);
        Matcher matcher = pattern.matcher(nextElementText);
        if (matcher.find()) {
            String songXmlDataUrl = matcher.group();
            Document songXmlDataDocument = Jsoup.connect(songXmlDataUrl).get();
            Element title = songXmlDataDocument.select(this.xmlElementTitle).first();
            Element time = songXmlDataDocument.select(this.xmlElementTime).first();
            Element creator = songXmlDataDocument.select(this.xmlElementCreator).first();
            Element location = songXmlDataDocument.select(this.xmlElementLocation).first();
            Element locationHq = songXmlDataDocument.select(this.xmlElementLocationHq).first();
            Element hasHq = songXmlDataDocument.select(this.xmlElementHasHq).first();
            Element info = songXmlDataDocument.select(this.xmlElementInfo).first();
            Element lyric = songXmlDataDocument.select(this.xmlElementLyric).first();
            Element bgImage = songXmlDataDocument.select(this.xmlElementBgImage).first();
            Element avatar = songXmlDataDocument.select(this.xmlElementAvatar).first();
            Element newTab = songXmlDataDocument.select(this.xmlElementNewTab).first();
            Element kbit = songXmlDataDocument.select(this.xmlElementKbit).first();
            Element key = songXmlDataDocument.select(this.xmlElementKey).first();
            musicNct = new MusicNct();
            musicNct.setTitle(title.text().replace("<![CDATA[", "").replace("]]>", ""));
            musicNct.setTime(time.text());
            musicNct.setCreator(creator.text());
            musicNct.setLocation(location.text());
            musicNct.setLocationHq(locationHq.text());
            musicNct.setHasHq(hasHq.text());
            musicNct.setInfo(info.text());
            musicNct.setLyric(lyric.text());
            musicNct.setBgImage(bgImage.text());
            musicNct.setAvatar(avatar.text());
            musicNct.setNewTab(newTab.text());
            musicNct.setKbit(kbit.text());
            musicNct.setId(key.text());
            musicNct.setLastFetchDatetime(new Date());
        }
        return musicNct;
    }
}
