package online.himakeit.lightmusic.network;

import java.util.Map;

import online.himakeit.lightmusic.bean.BaiduMusicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicGeDanInfoEntity;
import online.himakeit.lightmusic.bean.BaiduMusicLrcPicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongEntity;
import online.himakeit.lightmusic.bean.BaiduMusicPicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongInfoBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongListEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongRankEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
     * 之前在手机端一直不能访问数据成功，是因为服务器对请求头做了处理
     * @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
     */

    /**
     * 获取歌单列表
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicSongListEntity> getSongList(@QueryMap Map<String, String> params);

    /**
     * 获取歌曲排行榜
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicSongRankEntity> getSongRank(@QueryMap Map<String, String> params);


    /**
     * 获取新歌
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicBaseEntity<BaiduMusicNewSongEntity>> getNewSong(@QueryMap Map<String, String> params);

    /**
     * 获取轮播图图片
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicPicBaseEntity> getPicData(@QueryMap Map<String, String> params);

    /**
     * 获取歌词图片
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicLrcPicBaseEntity> getLrcPic(@QueryMap Map<String, String> params);

    /**
     * 获取歌曲信息
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicSongInfoBaseEntity> getSongInfo(@QueryMap Map<String, String> params);

    /**
     * 获取歌单列表
     *
     * @param params
     * @return
     */
    @Headers("user-agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(BASE_URL)
    Call<BaiduMusicGeDanInfoEntity> getGeDanInfo(@QueryMap Map<String, String> params);
}
