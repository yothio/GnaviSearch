package yothio.gnavisearch.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/11/13.
 */

@Getter
@Setter
public class Rest {

    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;

}
