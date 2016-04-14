package breadTrip2FunJoy.utils;


public class API {

    public static final String WX_API_ID = "wx5d2a0c1579e9a244";
    /**
     * APP SD Path
     */
    public static final String APP_IMAGE_CACHE = "/Android/data/net.duohuo.lequ/cache";
    /**
     * 分享内容
     */
    public static final String SHARE_CONTENT_START = "我发现了一个特别赞的出行计划，";
    public static final String SHARE_CONTENT_JOIN_END = "，我已经报名了，你要一起吗？";
    public static final String SHARE_CONTENT_NORMAL_END = "，好想参加啊，小伙伴们约吗？";
    public static final String SHARE_TOPIC_CONTENT = " 世界那么多美景，让我带你一一走过，乐去游记分享，给你不一样的直观感受。";
    public static final String SHARE_VISIT_TITLE = "乐享户外,去哪都行";
    public static final String SHARE_VISIT_CONTENT = "乐去APP，只推荐独具特色的户外玩法，户外活动必备神器！";
    public static final String SHARE_VISIT_LOGO = "http://a4.mzstatic.com/us/r30/Purple1/v4/c4/aa/8b/c4aa8b6d-46c6-e77b-ed97-dc3df5542ac0/icon175x175.png";
    /**
     * 头像修改
     */
    public static final String OSS_HEAD_100 = "@100w_100h_1e_100Q_1sh.jpg|100x100-2rc.jpg";
    /**
     * DEBUG
     */
    public static boolean DEBUG = false;
    /**
     * 基地址
     */
    public static String BASE_SERVER_URL = "https://api.huway.com/";
    /**
     * 基地址
     */
    public static String APK_CHANNEL = "huway";

    public static String SHARE_BASE_URL = "huway.com/";


    public static class Global {
        //头像上传
        public static final String uploadHead = BASE_SERVER_URL + "upload/img/face";
        //附件上传
        public static final String uploadPic = BASE_SERVER_URL + "helper/attachment/upload/";
    }


    public static class Active {
        public static final String list = BASE_SERVER_URL + "event/list";

        //线路详情
        public static final String line_detail = BASE_SERVER_URL + "event/line/detail";
        //活动详情
        public static final String active_detail = BASE_SERVER_URL + "event/detail";
        //报名
        public static final String join = BASE_SERVER_URL + "event/apply/add";
        // 获取活动额外报名信息
        public static final String extraInfo = BASE_SERVER_URL + "event/line/applyextendfield";
        // 获取App立减
        public static final String appCoupon = BASE_SERVER_URL + "event/line/appcoupon";
        // 获取App立减
        public static final String userApplyList = BASE_SERVER_URL + "event/apply/userapplylist";
    }

    public static class EventCode {

        public static final String regist_success = "event_hasregist";
        public static final String reget_password = "reget_password";
        public static final String chat_back_show = "chat_back_show";
        public static final String order_change = "order_change";
        public static final String event_login = "event_login_change";
        public static final String third_login = "third_login";

        public static final String partner_change = "partner_change";
        public static final String follow_change = "follow_change";
        public static final String member_change = "member_change";
        public static final String city_change = "city_change";
        public static final String message_refresh = "message_refresh";
        public static final String pay_end = "pay_end";
        public static final String reply_complete = "reply_complete";
        public static final String bind_new_card = "bindNewCard";
        public static final String travels_edit = "travels_edit";
        public static final String travels_follow = "travels_delete";

        public static final String reply_comment = "reply_comment";
        /**
         * 报名添加成员
         */
        public static final String member_add = "member_add";
        /**
         * 报名成功
         */
        public static final String apply_success = "apply_success";

        public static final String travels_release = "travels_release";
    }


    public static class Club {
        //查询俱乐部的活动
        public static final String event = BASE_SERVER_URL + "event/clubsevent";

        public static final String list = BASE_SERVER_URL + "club/list";
        //用户关注俱乐部
        public static final String join = BASE_SERVER_URL + "club/member/follow";
        //用户取消关注俱乐部
        public static final String quit = BASE_SERVER_URL + "club/member/unfollow";
        //用户关注的俱乐部
        public static final String follow_list = BASE_SERVER_URL + "club/followlist";
        //俱乐部活动类型
        public static final String tags = BASE_SERVER_URL + "club/type";
        //1.1.1	获取俱乐部信息
        public static final String detail = BASE_SERVER_URL + "club/info";

        public static final String members = BASE_SERVER_URL + "group&action=members";
    }

    public static class Travels {
        //创建游记标题
        public static final String createTravelsHeader = BASE_SERVER_URL + "travels/header/create";
        //创建游记内容
        public static final String addTopicItem = BASE_SERVER_URL + "travels/content/add";
        //编辑游记头
        public static final String editTravelsHeader = BASE_SERVER_URL + "travels/header/edit";
        //编辑游记内容
        public static final String topicItemEdit = BASE_SERVER_URL + "travels/content/edit";
        //游记列表
        public static final String list = BASE_SERVER_URL + "travels/list/";
        //2.11.1	游记内容排序
        public static final String travelsContentSort = BASE_SERVER_URL + "travels/content/sort";
        //发布游记
        public static final String release = BASE_SERVER_URL + "travels/release";
        //游记详情
        public static final String detail = BASE_SERVER_URL + "travels/detail";
        //删除游记头
        public static final String deleteHeader = BASE_SERVER_URL + "travels/header/delete";
        //删除游记内容
        public static final String deleteContent = BASE_SERVER_URL + "travels/content/delete";

        public static final String addcomment = BASE_SERVER_URL + "bbs&action=add_comment";
        public static final String like = BASE_SERVER_URL + "bbs&action=like";
        public static final String likelist = BASE_SERVER_URL + "bbs&action=like_list";
        public static final String replylist = BASE_SERVER_URL + "bbs&action=reply_list";
        public static final String webdetail = BASE_SERVER_URL + "bbs_view_id_";
    }

    public static class User {

        public static final String login = BASE_SERVER_URL + "uc/login";
        public static final String info = BASE_SERVER_URL + "uc/user";
        public static final String getSmsReg = BASE_SERVER_URL + "uc/sms/reg";
        public static final String verifySmsCode = BASE_SERVER_URL + "uc/sms/verify";
        public static final String getSmsPWD = BASE_SERVER_URL + "uc/sms/pwd";
        public static final String reg = BASE_SERVER_URL + "uc/reg";
        public static final String resetPWD = BASE_SERVER_URL + "uc/pwd/reset";
        public static final String logout = BASE_SERVER_URL + "uc/login/out";
        //积分更新
        public static final String creditUpdate = BASE_SERVER_URL + "uc/credit/update";
        //积分详情
        public static final String creditlist = BASE_SERVER_URL + "uc/credit/list";
        //检测手机号是否注册
        public static final String checkphone = BASE_SERVER_URL + "uc/service/checkreg";
        //检测昵称
        public static final String checkNick = BASE_SERVER_URL + "uc/service/checknick";
        //系统消息列表和私信列表
        public static final String msglist = BASE_SERVER_URL + "uc/pm/list";
        //发送消息
        public static final String sendTalk = BASE_SERVER_URL + "uc/pm/send";
        //私信消息详情
        public static final String chatDetail = BASE_SERVER_URL + "uc/pm/mobileplist";
        //修改密码
        public static final String checkPWD = BASE_SERVER_URL + "uc/pwd/check";
        //修改密码
        public static final String modifyPWD = BASE_SERVER_URL + "uc/user/password";
        //获取未读通知数量
        public static final String checkNoticeNew = BASE_SERVER_URL + "uc/pm/checknew";
        //获取未读通知数量
        public static final String readStatus = BASE_SERVER_URL + "uc/pm/readstatus";
        // 关注,粉丝列表
        public static final String friends = BASE_SERVER_URL + "uc/info/friend";
        //删除消息
        public static final String delMsg = BASE_SERVER_URL + "uc/pm/del";
        // 添加,取消关注
        public static final String follow = BASE_SERVER_URL + "uc/user/follow";

        //添加 删除 编辑 查看 同伴列表
        public static final String passengerList = BASE_SERVER_URL + "uc/passenger/list";
        public static final String passengerAdd = BASE_SERVER_URL + "uc/passenger/add";
        public static final String passengerEdit = BASE_SERVER_URL + "uc/passenger/edit";
        public static final String passengerDelete = BASE_SERVER_URL + "uc/passenger/delete";

        public static final String AVATAR = BASE_SERVER_URL.replace("https://api", "http://uc") + "avatar.php?uid=";
        //查询用户支付密码状态
        public static final String queryPayPwd = BASE_SERVER_URL + "uc/paypassword/query";
        //设置支付密码
        public static final String setPayPwd = BASE_SERVER_URL + "uc/paypassword/edit";

        ////////////////////////////
        //添加收藏
        public static final String addCollect = BASE_SERVER_URL + "uc/collect/add";
        //收藏列表
        public static final String collectList = BASE_SERVER_URL + "uc/collect/list";
        //删除收藏
        public static final String delCollect = BASE_SERVER_URL + "uc/collect/cancel";
        //是否收藏
        public static final String checkCollect = BASE_SERVER_URL + "uc/collect/check";
        //第三方登陆
        public static final String thirdPartLogin = BASE_SERVER_URL + "uc/login/third";
        //第三方注册
        public static final String thirdPartReg = BASE_SERVER_URL + "uc/reg/third";
        //第三方注册发送手机验证码
        public static final String thirdPartRegCode = BASE_SERVER_URL + "uc/sms/bind";

        //获取用户信息
        public static final String getUserInfo = BASE_SERVER_URL + "uc/info/list";
        ////old version///////////////////////////////////////
        public static final String detailmsglist = BASE_SERVER_URL + "message&action=list";
        public static final String aboutapp = BASE_SERVER_URL + "material/theme/huway/html/aboutapp.html";
        public static final String deal = BASE_SERVER_URL + "material/theme/huway/html/agreement.html";
    }

    //资金
    public static class Capital {

        public static final String recharge_type_AlipayApp = "alipay.app";

        public static final String recharge_type_WeChatApp = "wechat.app";

        //账户详情
        public static final String capitalDetail = BASE_SERVER_URL + "capital/account/detail";
        //资金明细  type ==  2 为个人, type == 1 为俱乐部
        public static final String billFlow = BASE_SERVER_URL + "capital/account/flow";
        //银行列表
        public static final String bankCategory = BASE_SERVER_URL + "capital/bank/category";
        //已绑定银行列表
        public static final String selfBankList = BASE_SERVER_URL + "capital/bank/list";
        //添加银行卡
        public static final String bindBankCard = BASE_SERVER_URL + "capital/bank/add";
        //删除银行卡
        public static final String unbindBankCard = BASE_SERVER_URL + "capital/bank/delete";
        //充值
        public static final String recharge = BASE_SERVER_URL + "capital/recharge/launch";
        //提现
        public static final String withdraw = BASE_SERVER_URL + "capital/withdraw";
        //我的优惠券
        public static final String couponList = BASE_SERVER_URL + "capital/coupon/mine";
        //新增优惠券
        public static final String queryCoupon = BASE_SERVER_URL + "capital/coupon/query";
        //订单详情
        public static final String queryOrderDetail = BASE_SERVER_URL + "capital/order/detail";
        //校验优惠券是否可用
        public static final String validateCoupon = BASE_SERVER_URL + "capital/coupon/validate";
        //支付
        public static final String pay = BASE_SERVER_URL + "capital/order/pay";
        //订单列表
        public static final String orderList = BASE_SERVER_URL + "capital/order/mine";
        //取消订单
        public static final String cancelOrder = BASE_SERVER_URL + "capital/order/cancel";
        //订单退款
        public static final String orderRefund = BASE_SERVER_URL + "capital/order/refund";
    }


    public static class Helper {
        //用户反馈
        public static final String suggestion = BASE_SERVER_URL + "helper/suggestion";
        //提交用户设备标识码及经纬度
        public static final String device = BASE_SERVER_URL + "helper/device";
        //获取附近的驴友
        public static final String nearFriend = BASE_SERVER_URL + "helper/near/people";
        //检测数据库版本以及下载地址
        public static final String district_file = BASE_SERVER_URL + "helper/upgrade/district";
        //广告位
        public static final String ad = BASE_SERVER_URL + "helper/ad";
        //检测新版本
        public static final String update = BASE_SERVER_URL + "helper/upgrade/android";
        //评论列表
        public static final String commentList = BASE_SERVER_URL + "helper/comment/list";
        //回复列表
        public static final String replyList = BASE_SERVER_URL + "helper/comment/reply";
        //发表评论
        public static final String addComment = BASE_SERVER_URL + "helper/comment/add";
        //添加回复
        public static final String addReply = BASE_SERVER_URL + "helper/comment/replyadd";
        //删除评论
        public static final String delComment = BASE_SERVER_URL + "helper/comment/delete";
        //删除回复
        public static final String delReply = BASE_SERVER_URL + "helper/comment/replycancel";
        //活动退款说明
        public static final String activeRule = BASE_SERVER_URL + "helper/instructions/refund";
        //获取基URL
        public static final String domain = "http://www.huway.com/config.json";
    }

    //目的地
    public static class Destination {
        //热门目的地
        public static final String hot_city = "http://api.d.huway.org/destination/hot";
        //目的地索引列表
        public static final String city_index = "http://api.d.huway.org/destination/index";
        //目的地搜索
        public static final String city_search = "http://api.d.huway.org/destination";
        //目的地详情头
        public static final String city_header = "http://api.d.huway.org/destination";
        //热门目的地活动
        public static final String hot_destination = "http://api.d.huway.org/destination/current_month";
    }
}
