package yothio.gnavisearch.network.model;

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
        Object openTime;
        @SerializedName("holiday")
        Object holiday;
        @SerializedName("budget")
        Object budget;
        @SerializedName("access")
        Access access;
        @SerializedName("latitude")
        String latitude;
        @SerializedName("longitude")
        String longitude;
        @Getter
        @Setter
        public class ImageUrl{
            @SerializedName("shop_image1")
            Object imageUrl1;

        }

        @Getter
        @Setter
        public class Access{
            @SerializedName("line")
            Object line;
            @SerializedName("station")
            Object station;
            @SerializedName("walk")
            Object walk;

        }
    }
}
