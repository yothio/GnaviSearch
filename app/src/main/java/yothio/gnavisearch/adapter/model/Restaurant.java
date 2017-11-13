package yothio.gnavisearch.adapter.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/11/13.
 */
@Getter
@Setter
public class Restaurant implements Serializable {

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
