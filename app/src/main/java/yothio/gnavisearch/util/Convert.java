package yothio.gnavisearch.util;

/**
 * Created by yocchi on 2017/11/16.
 */

public class Convert {

    public static String accessTime(String time) {

        String[] keyWord = {"徒歩", "歩き", "車", "バス", "電車"};

        for (String word : keyWord) {
            if (time.contains(word)) {
                return time;
            }
        }

        return "徒歩より" + time;
    }
}
