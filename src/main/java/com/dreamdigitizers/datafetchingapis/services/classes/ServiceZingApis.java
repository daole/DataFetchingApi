package com.dreamdigitizers.datafetchingapis.services.classes;

import com.dreamdigitizers.datafetchingapis.models.MusicZing;
import com.dreamdigitizers.datafetchingapis.models.MusicZingApi;
import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceZing;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.Date;

public class ServiceZingApis implements IServiceZing {
    @Value("${zing.api.getSongInfo.parameters.requestData.format}")
    private String getSongInfoParametersRequestDataFormat;

    private IApi api;

    public ServiceZingApis() {
        this.api = new Api();
    }

    @Override
    public MusicZing fetch(String name, String artist, String id) throws IOException {
        id = String.format(this.getSongInfoParametersRequestDataFormat, id);
        MusicZingApi musicZingApi = this.api.getSongInfo(id)
                .execute()
                .body();
        MusicZing musicZing = this.convertMusicZingApiToMusicZing(musicZingApi);
        return musicZing;
    }

    private MusicZing convertMusicZingApiToMusicZing(MusicZingApi musicZingApi) {
        MusicZing musicZing = new MusicZing();

        musicZing.setId(musicZingApi.getSongIdEncode());
        musicZing.setTitle(musicZingApi.getTitle());
        musicZing.setPerformer(musicZingApi.getArtist());
        musicZing.setLink(musicZingApi.getLink());
        musicZing.setSource(musicZingApi.getSource().get_128());
        musicZing.setHq(musicZingApi.getSource().get_320());
        musicZing.setDuration(musicZingApi.getDuration());
        musicZing.setLyric(musicZingApi.getLyricsFile());
        musicZing.setBackImage(musicZingApi.getThumbnail());
        musicZing.setErrorCode(musicZingApi.getResponse().getMsgCode());
        musicZing.setErrorMessage(musicZingApi.getResponse().getMsg());
        musicZing.setLastFetchDatetime(new Date());

        return musicZing;
    }

    private interface IApi {
        @GET("api/mobile/song/getsonginfo")
        Call<MusicZingApi> getSongInfo(@Query("requestdata") String id);
    }

    private class Api implements IApi {
        private Retrofit retrofit;
        private IApi api;

        public Api() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.mp3.zing.vn/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            this.api = this.retrofit.create(IApi.class);
        }

        @Override
        public Call<MusicZingApi> getSongInfo(@Query("requestdata") String id) {
            return this.api.getSongInfo(id);
        }
    }
}
