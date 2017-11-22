package yothio.gnavisearch.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/11/22.
 */

@Getter
@Setter
public class Session {

    private static Session instance = new Session();

    public static Session getInstance() {
        return instance;
    }

    private double userLatitude;
    private double userLongitude;

}
