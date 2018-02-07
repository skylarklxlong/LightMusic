package online.himakeit.lightmusic.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author：LiXueLong
 * @date：2018/2/7
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class MusicTrack implements Parcelable {
    public long mId;
    public int mSourcePosition;

    protected MusicTrack(Parcel in) {
        mId = in.readLong();
        mSourcePosition = in.readInt();
    }

    public MusicTrack(long id, int sourcePosition) {
        mId = id;
        mSourcePosition = sourcePosition;
    }

    public static final Creator<MusicTrack> CREATOR = new Creator<MusicTrack>() {
        @Override
        public MusicTrack createFromParcel(Parcel in) {
            return new MusicTrack(in);
        }

        @Override
        public MusicTrack[] newArray(int size) {
            return new MusicTrack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mSourcePosition);
    }
}
