package yothio.gnavisearch.adapter;

/**
 * Created by yocchi on 2017/11/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantItem implements Parcelable {
//    店舗名
    String name;
//    住所
    String address;
//    連絡先
    String tel;
//    営業時間
    String openTime;
//    画像url
    String imageUri;
//    休日
    String holiday;
//    ディナー価格
    String budget;
//    最寄り電車系列
    String accessLine;
//    最寄り駅
    String accessStation;
//    駅からの徒歩時間
    String accessWalk;
//    緯度系ど
    double latitude;
    double longitude;

    public RestaurantItem(){

    }

    protected RestaurantItem(Parcel in) {
        name = in.readString();
        address = in.readString();
        tel = in.readString();
        openTime = in.readString();
        imageUri = in.readString();
        holiday = in.readString();
        budget = in.readString();
        accessLine = in.readString();
        accessStation = in.readString();
        accessWalk = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<RestaurantItem> CREATOR = new Creator<RestaurantItem>() {
        @Override
        public RestaurantItem createFromParcel(Parcel in) {
            return new RestaurantItem(in);
        }

        @Override
        public RestaurantItem[] newArray(int size) {
            return new RestaurantItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(tel);
        parcel.writeString(openTime);
        parcel.writeString(imageUri);
        parcel.writeString(holiday);
        parcel.writeString(budget);
        parcel.writeString(accessLine);
        parcel.writeString(accessStation);
        parcel.writeString(accessWalk);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}
