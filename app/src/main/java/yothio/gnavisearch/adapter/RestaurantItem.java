package yothio.gnavisearch.adapter;

/**
 * Created by yocchi on 2017/11/16.
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantItem {
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
}
