package online.himakeit.lightmusic.network;

import java.util.Map;

import online.himakeit.lightmusic.bean.BaiduMusicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongEntity;
import online.himakeit.lightmusic.bean.BaiduMusicPicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongListEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongRankEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author：LiXueLong
 * @date：2018/2/3
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:参考 http://blog.csdn.net/zuiaisha1/article/details/61200422
 * http://blog.csdn.net/zuiaisha1/article/details/61202252
 */
public interface BaiduMusicApi {

    String MUSIC_BASE_URL = "http://tingapi.ting.baidu.com";
    String SUB_URL = "/v1/restserver/ting";
    String BASE_URL = MUSIC_BASE_URL + SUB_URL;

    /**
     * 获取歌单列表
     *
     * @param params
     * @return
     */
    @GET(BASE_URL)
    Call<BaiduMusicSongListEntity> getSongList(@QueryMap Map<String, String> params);

    /**
     * 获取歌曲排行榜
     *
     * @param params
     * @return
     */
    @GET(BASE_URL)
    Call<BaiduMusicSongRankEntity> getSongRank(@QueryMap Map<String, String> params);


    /**
     * 获取新歌
     *
     * @param params
     * @return
     */
    @GET(BASE_URL)
    Call<BaiduMusicBaseEntity<BaiduMusicNewSongEntity>> getNewSong(@QueryMap Map<String, String> params);

    /**
     * 获取轮播图图片
     *
     * @param params
     * @return
     */
    @GET(BASE_URL)
    Call<BaiduMusicPicBaseEntity> getPicData(@QueryMap Map<String, String> params);
}
