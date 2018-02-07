package online.himakeit.lightmusic.network;

import online.himakeit.lightmusic.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author：LiXueLong
 * @date：2018/2/5
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public abstract class BaiduNetCallBack<T> implements Callback<T> {
    /**
     * 成功时回调
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    public void onFailure(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccess(response.body());
            } else {
                onFailure("获取数据为空");
            }
        } else {
            onFailure("获取数据失败");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(t.getMessage());
    }
}
