import com.qiniu.pili.*;
import com.qiniu.pili.utils.Base64;
import com.qiniu.pili.utils.HMac;
import com.qiniu.pili.utils.UrlSafeBase64;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author zjh
 * @Date 2018/11/21,21:09
 */
public class MyDemo {

    private static final String push_domin = "pili-publish.dfsdedu.cn";     //推流域名

    private static final String pull_domin = "pili-live-hls.dfsdedu.cn";   //拉流域名

    private static final String hub_name = "nets";                          //直播空间名

    private static final String stream_name = "test2";                      //直播流名

    private static final String access_key = "Mq2Bhzwx-2GtrD650HrrZ_ACrZsBLXhWBqMmDKyU";

    private static final String secret_key = "hC4-OF7QCMI_Nkd-nBN0IlRYI9NJHZeIyKWxb5YM";

    public Client client;

    public Hub hub;

    public MyDemo() {
        this.client = new Client(access_key, secret_key);
        this.hub = client.newHub(hub_name);
    }

    /**
     * @methodname getPushURL
     * @description 获取带凭证的推流地址
     * @author zjh
     * @date 16:30,2018/12/14
     * @return
     */
    public String getPushURL() {
        return client.RTMPPublishURL(push_domin, hub_name, stream_name, 3600);
    }

    /**
     * @methodname getPullURL
     * @description 获取带凭证的拉流地址
     * @author zjh
     * @date 16:30,2018/12/14
     * @return
     */
    public String getPullURL() {
        String path = "/" + hub_name + "/" + stream_name;
        String t = Long.toHexString(System.currentTimeMillis() / 1000 + 60*5) + "";
        System.out.println(t);
        String t_low = t.toLowerCase();

        String s = "sselab" + path + t_low;
        String sign = md5(s).toLowerCase();

        String url = client.RTMPPlayURL(pull_domin, hub_name, stream_name) + "?sign=" + sign + "&t=" + t_low;
        return url;
    }


    public static void main(String[] args) throws PiliException, InterruptedException, ParseException {

        MyDemo myDemo = new MyDemo();
////        System.out.println(myDemo.getPushURL());
////        System.out.println(myDemo.getPullURL());
//        String[] s = myDemo.hub.list("xx", Integer.MAX_VALUE, "").keys;
//        for(String i : s)
//        System.out.println(i);
        Stream stream = myDemo.client.newHub("nets").get("1");
        String start_str = "2019-01-03 09:35:00";
        String end_str = "2029-01-03 11:38:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.parse(start_str).getTime() + "     " + sdf.parse(end_str).getTime());
        long start = sdf.parse(start_str).getTime() / 1000;
        long end = sdf.parse(end_str).getTime() / 1000;
        Stream.SaveOptions opt = new Stream.SaveOptions();
        System.out.println(stream.save(start, end));

        Hub hub = myDemo.client.newHub("nets");
        Hub.ListRet ret = hub.list("", Integer.MAX_VALUE, "");
        for(String key : ret.keys)
            System.out.println(key);

    }


    public static String md5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
