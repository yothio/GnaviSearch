package yothio.gnavisearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/11/13.
 */

@Getter
@Setter
public class SearchResponse{

    @SerializedName("total_hit_count")
    int totalHitCount;
    @SerializedName("hit_per_page")
    int showPerPage;
    @SerializedName("page_offset")
    int pageOffset;
    @SerializedName("rest")
    List<Rest> rest;

    @Getter
    @Setter
    public class Rest{
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
        @SerializedName("holiday")
        String holiday;
        @SerializedName("budget")
        String budget;
        @SerializedName("access")
        Access access;

        @Getter
        @Setter
        public  class ImageUrl{
            @SerializedName("shop_image1")
            String imageUrl1;

        }

        @Getter
        @Setter
        public class Access{
            @SerializedName("line")
            String line;
            @SerializedName("station")
            String station;
            @SerializedName("walk")
            String walk;

        }
    }
}
