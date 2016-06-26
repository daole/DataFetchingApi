package com.dreamdigitizers.datafetchingapis.services.classes;

import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceZing;
import com.dreamdigitizers.datafetchingapis.models.MusicZing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Date;

public class ServiceZing implements IServiceZing {
    @Value("${zing.songUrl}")
    private String songUrl;

    @Value("${zing.songTitleSeparator}")
    private String songTitleSeparator;

    @Value("${zing.htmlElementId}")
    private String htmlElementId;

    @Value("${zing.htmlElementAttribute}")
    private String htmlElementAttribute;

    @Value("${zing.xmlElement.title}")
    private String xmlElementTitle;

    @Value("${zing.xmlElement.performer}")
    private String xmlElementPerformer;

    @Value("${zing.xmlElement.link}")
    private String xmlElementLink;

    @Value("${zing.xmlElement.source}")
    private String xmlElementSource;

    @Value("${zing.xmlElement.hq}")
    private String xmlElementHq;

    @Value("${zing.xmlElement.duration}")
    private String xmlElementDuration;

    @Value("${zing.xmlElement.lyric}")
    private String xmlElementLyric;

    @Value("${zing.xmlElement.mvLink}")
    private String xmlElementMvLink;

    @Value("${zing.xmlElement.adParam}")
    private String xmlElementAdParam;

    @Value("${zing.xmlElement.backImage}")
    private String xmlElementBackImage;

    @Value("${zing.xmlElement.errorCode}")
    private String xmlElementErrorCode;

    @Value("${zing.xmlElement.errorMessage}")
    private String xmlElementErrorMessage;

    @Override
    public MusicZing fetch(String name, String artist, String id) throws IOException {
        MusicZing musicZing = null;
        String songPageUrl = String.format(this.songUrl, (name + " " + artist).replace(" ", this.songTitleSeparator), id);
        Document songPageDocument = Jsoup.connect(songPageUrl).get();
        Element element = songPageDocument.select(this.htmlElementId).first();
        if (element != null && element.hasAttr(this.htmlElementAttribute)) {
            String songXmlDataUrl = element.attr(this.htmlElementAttribute);
            Document songXmlDataDocument = Jsoup.connect(songXmlDataUrl).get();
            Element title = songXmlDataDocument.select(this.xmlElementTitle).first();
            Element performer = songXmlDataDocument.select(this.xmlElementPerformer).first();
            Element link = songXmlDataDocument.select(this.xmlElementLink).first();
            Element source = songXmlDataDocument.select(this.xmlElementSource).first();
            Element hq = songXmlDataDocument.select(this.xmlElementHq).first();
            Element duration = songXmlDataDocument.select(this.xmlElementDuration).first();
            Element lyric = songXmlDataDocument.select(this.xmlElementLyric).first();
            Element mvLink = songXmlDataDocument.select(this.xmlElementMvLink).first();
            Element adParam = songXmlDataDocument.select(this.xmlElementAdParam).first();
            Element backImage = songXmlDataDocument.select(this.xmlElementBackImage).first();
            Element errorCode = songXmlDataDocument.select(this.xmlElementErrorCode).first();
            Element errorMessage = songXmlDataDocument.select(this.xmlElementErrorMessage).first();
            musicZing = new MusicZing();
            musicZing.setId(id);
            musicZing.setTitle(title.text());
            musicZing.setPerformer(performer.text());
            musicZing.setLink(link.text());
            musicZing.setSource(source.text());
            musicZing.setHq(hq.text());
            musicZing.setDuration(duration.text());
            musicZing.setLyric(lyric.text());
            musicZing.setMvLink(mvLink.text());
            musicZing.setAdParam(adParam.text());
            musicZing.setBackImage(backImage.text());
            musicZing.setErrorCode(errorCode.text());
            musicZing.setErrorMessage(errorMessage.text());
            musicZing.setLastFetchDatetime(new Date());
        }
        return musicZing;
    }
}
