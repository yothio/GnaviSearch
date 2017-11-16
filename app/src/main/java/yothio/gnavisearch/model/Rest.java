package yothio.gnavisearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/11/13.
 */

@Getter
@Setter
public class Rest implements Parcelable {

    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;
    @SerializedName("image_url")
    ImageUrl imageUrl;
    @SerializedName("tel")
    String tel;
    @SerializedName("opentime")
    String openTime;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(getName());
        out.writeString(getAddress());
        out.writeString(getTel());
        out.writeString(getOpenTime());
    }

    public static final Parcelable.Creator<Rest> CREATOR
            = new Parcelable.Creator<Rest>() {
        public Rest createFromParcel(Parcel in) {
            return new Rest(in);
        }

        public Rest[] newArray(int size) {
            return new Rest[size];
        }
    };

    private Rest(Parcel in) {
        setName(in.readString());
        setAddress(in.readString());
        setTel(in.readString());
        setOpenTime(in.readString());
//        setImageUrl(in.readParcelable(ImageUrl.ClassLoaderCreator));
    }

    public Rest() {
        super();
    }

    @Getter
    @Setter
    public class ImageUrl implements Parcelable{
        @SerializedName("shop_image1")
        String imageUrl1;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

        }

         final Parcelable.Creator<Rest> CREATOR
                = new Parcelable.Creator<Rest>() {
            public Rest createFromParcel(Parcel in) {
                return new Rest(in);
            }

            public Rest[] newArray(int size) {
                return new Rest[size];
            }
        };
    }
}
