package breadTrip2FunJoy.net;

/**
 * 一些系统常量的定义
 *
 * @author duohuo-jinghao
 */
public class Const {

    //打印日志
    public static boolean logable = true;
    //打印日志时打印行号
    public static boolean logline = true;


    public static boolean net_error_try = false;

    public static boolean auto_inject = true;

    //数据库版本号
    public static int DATABASE_VERSION = 1;
    //数据库公共库名
    public static String DATABASE_NAME = "default.db";

    //adapter 的分页相关
    public static String netadapter_page_no = "page";
    public static String netadapter_step = "pagesize";
    public static Integer netadapter_step_default = 10;
    public static String netadapter_timeline = "timeline";
    public static String netadapter_json_timeline = "id";
    public static String netadapter_no_more = "已经没有了";

    public static String[] ioc_instal_pkg = null;

    //图片缓存相关
    public static String image_cache_dir = "dhcache";
    public static int image_cache_num = 12;
    public static Boolean image_cache_is_in_sd = false;
    public static long image_cache_time = 100000l;

    //网络访问返回数据的格式定义
    public static String response_success = "success";
    public static String response_msg = "message";
    public static String response_status = "status";
    public static String response_data = "data";
    public static String response_code = "status";
    public static int net_pool_size = 10;


}
