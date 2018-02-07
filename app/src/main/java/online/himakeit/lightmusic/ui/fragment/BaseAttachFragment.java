package online.himakeit.lightmusic.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * @author：LiXueLong
 * @date：2018/2/1
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaseAttachFragment extends Fragment {

    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
