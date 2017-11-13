package yothio.gnavisearch.model;

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
    public class Rest {

        @SerializedName("name")
        String name;
        @SerializedName("address")
        String address;

    }
}
