package online.himakeit.lightmusic.network;

import java.util.Map;

import online.himakeit.lightmusic.AppContext;
import online.himakeit.lightmusic.bean.BaiduMusicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongEntity;
import online.himakeit.lightmusic.bean.BaiduMusicPicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongListEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongRankEntity;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author：LiXueLong
 * @date：2018/2/5
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class ApiManager {
    private static ApiManager apiManager;
    private BaiduMusicApi baiduMusicApi;
    private static Object monitor = new Object();

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (monitor) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    private BaiduMusicApi getBaiduMusicApi() {
        if (baiduMusicApi == null) {
            synchronized (monitor) {
                if (baiduMusicApi == null) {
                    baiduMusicApi = new Retrofit.Builder()
                            .baseUrl(BaiduMusicApi.MUSIC_BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(AppContext.defaultOkHttpClient())
                            .build()
                            .create(BaiduMusicApi.class);
                }
            }
        }
        return baiduMusicApi;
    }


    /**
     * 获取歌单
     *
     * @param pageNo
     * @param pageSize
     * @param callBack
     */
    public void getSongListData(int pageNo, int pageSize, BaiduNetCallBack<BaiduMusicSongListEntity> callBack) {
        Map<String, String> params = BaiduNetHelper.getMusicApiCommonParams("baidu.ting.diy.gedan");
        params.put("page_no", String.valueOf(pageNo));
        params.put("page_size", String.valueOf(pageSize));
        getBaiduMusicApi().getSongList(params).enqueue(callBack);
    }

    /**
     * 获取歌曲排行榜
     *
     * @param type
     * @param offset
     * @param size
     * @param callBack
     */
    public void getSongRankData(int type, int offset, int size, BaiduNetCallBack<BaiduMusicSongRankEntity> callBack) {
        Map<String, String> params = BaiduNetHelper.getMusicApiCommonParams("baidu.ting.billboard.billList");
        params.put("type", String.valueOf(type));
        params.put("offset", String.valueOf(offset));
        params.put("size", String.valueOf(size));
        getBaiduMusicApi().getSongRank(params).enqueue(callBack);
    }

    /**
     * 获取新歌
     *
     * @param operator
     * @param callBack
     */
    public void getNewSongData(int operator, BaiduNetCallBack<BaiduMusicBaseEntity<BaiduMusicNewSongEntity>> callBack) {
        Map<String, String> params = BaiduNetHelper.getMusicApiCommonParams("baidu.ting.plaza.index");
        params.put("channel", "ppzs");
        params.put("cuid", "89CF1E1A06826F9AB95A34DC0F6AAA14");
        params.put("operator", String.valueOf(operator));
        getBaiduMusicApi().getNewSong(params).enqueue(callBack);
    }

    /**
     * 获取轮播图图片
     * @param num
     * @param callBack
     */
    public void getPicData(int num, BaiduNetCallBack<BaiduMusicPicBaseEntity> callBack){
        Map<String, String> params = BaiduNetHelper.getMusicApiCommonParams("baidu.ting.plaza.getFocusPic");
        params.put("num", String.valueOf(num));
        getBaiduMusicApi().getPicData(params).enqueue(callBack);
    }

}
