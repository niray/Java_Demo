package breadTrip2FunJoy;

import java.util.List;

/**
 * Created by Chen Zhining
 * Date : 16/3/9.
 */
public class BreadTripBean {

    private int target_waypoint_id;
    private int waypoints;
    private boolean wifi_sync;
    private String last_day;
    /**
     * flight : 0
     * mall : 0
     * hotel : 0
     * sights : 3
     * restaurant : 0
     */

    private PoiInfosCountEntity poi_infos_count;
    private String first_day;
    private long id;
    private String city;
    private int privacy;
    private int day_count;
    private String first_timezone;
    private int comment_count;
    private int shared;
    private String province;
    private double mileage;
    private String description;
    private int view_count;
    private double last_modified;

    private UserEntity user;
    private int date_complete;
    private int device;
    private float date_added;
    private String trackpoints_thumbnail_image;
    private String name;
    private String country;
    private int recommendations;
    private String cover_image;

    private StartPointEntity start_point;
    private boolean recommended;

    private List<CoveredCountriesEntity> covered_countries;
    private List<String> city_slug_urls;
    private List<String> cities;

    private List<DaysEntity> days;

    public int getTarget_waypoint_id() {
        return target_waypoint_id;
    }

    public void setTarget_waypoint_id(int target_waypoint_id) {
        this.target_waypoint_id = target_waypoint_id;
    }

    public int getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(int waypoints) {
        this.waypoints = waypoints;
    }

    public boolean isWifi_sync() {
        return wifi_sync;
    }

    public void setWifi_sync(boolean wifi_sync) {
        this.wifi_sync = wifi_sync;
    }

    public String getLast_day() {
        return last_day;
    }

    public void setLast_day(String last_day) {
        this.last_day = last_day;
    }

    public PoiInfosCountEntity getPoi_infos_count() {
        return poi_infos_count;
    }

    public void setPoi_infos_count(PoiInfosCountEntity poi_infos_count) {
        this.poi_infos_count = poi_infos_count;
    }

    public String getFirst_day() {
        return first_day;
    }

    public void setFirst_day(String first_day) {
        this.first_day = first_day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    public String getFirst_timezone() {
        return first_timezone;
    }

    public void setFirst_timezone(String first_timezone) {
        this.first_timezone = first_timezone;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getShared() {
        return shared;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public double getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(double last_modified) {
        this.last_modified = last_modified;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getDate_complete() {
        return date_complete;
    }

    public void setDate_complete(int date_complete) {
        this.date_complete = date_complete;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public float getDate_added() {
        return date_added;
    }

    public void setDate_added(float date_added) {
        this.date_added = date_added;
    }

    public String getTrackpoints_thumbnail_image() {
        return trackpoints_thumbnail_image;
    }

    public void setTrackpoints_thumbnail_image(String trackpoints_thumbnail_image) {
        this.trackpoints_thumbnail_image = trackpoints_thumbnail_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public StartPointEntity getStart_point() {
        return start_point;
    }

    public void setStart_point(StartPointEntity start_point) {
        this.start_point = start_point;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public List<CoveredCountriesEntity> getCovered_countries() {
        return covered_countries;
    }

    public void setCovered_countries(List<CoveredCountriesEntity> covered_countries) {
        this.covered_countries = covered_countries;
    }

    public List<String> getCity_slug_urls() {
        return city_slug_urls;
    }

    public void setCity_slug_urls(List<String> city_slug_urls) {
        this.city_slug_urls = city_slug_urls;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<DaysEntity> getDays() {
        return days;
    }

    public void setDays(List<DaysEntity> days) {
        this.days = days;
    }

    public static class PoiInfosCountEntity {
        private int flight;
        private int mall;
        private int hotel;
        private int sights;
        private int restaurant;

        public int getFlight() {
            return flight;
        }

        public void setFlight(int flight) {
            this.flight = flight;
        }

        public int getMall() {
            return mall;
        }

        public void setMall(int mall) {
            this.mall = mall;
        }

        public int getHotel() {
            return hotel;
        }

        public void setHotel(int hotel) {
            this.hotel = hotel;
        }

        public int getSights() {
            return sights;
        }

        public void setSights(int sights) {
            this.sights = sights;
        }

        public int getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(int restaurant) {
            this.restaurant = restaurant;
        }
    }

    public static class UserEntity {
        private String location_name;
        private String name;
        private String resident_city_id;
        /**
         * verified : true
         * name : 特约作者
         */

        private TitleEntity title;
        private String mobile;
        private int gender;
        private String avatar_m;
        private String cover;
        private String custom_url;
        /**
         * value : 6634
         * level_info : {"name":"","value":6}
         */

        private ExperienceEntity experience;
        private long id;
        private String birthday;
        private Object country_num;
        private String avatar_s;
        private String avatar_l;
        private boolean email_verified;
        private boolean is_hunter;
        private Object country_code;
        private String email;
        private String user_desc;

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getResident_city_id() {
            return resident_city_id;
        }

        public void setResident_city_id(String resident_city_id) {
            this.resident_city_id = resident_city_id;
        }

        public TitleEntity getTitle() {
            return title;
        }

        public void setTitle(TitleEntity title) {
            this.title = title;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getAvatar_m() {
            return avatar_m;
        }

        public void setAvatar_m(String avatar_m) {
            this.avatar_m = avatar_m;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCustom_url() {
            return custom_url;
        }

        public void setCustom_url(String custom_url) {
            this.custom_url = custom_url;
        }

        public ExperienceEntity getExperience() {
            return experience;
        }

        public void setExperience(ExperienceEntity experience) {
            this.experience = experience;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getCountry_num() {
            return country_num;
        }

        public void setCountry_num(Object country_num) {
            this.country_num = country_num;
        }

        public String getAvatar_s() {
            return avatar_s;
        }

        public void setAvatar_s(String avatar_s) {
            this.avatar_s = avatar_s;
        }

        public String getAvatar_l() {
            return avatar_l;
        }

        public void setAvatar_l(String avatar_l) {
            this.avatar_l = avatar_l;
        }

        public boolean isEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(boolean email_verified) {
            this.email_verified = email_verified;
        }

        public boolean isIs_hunter() {
            return is_hunter;
        }

        public void setIs_hunter(boolean is_hunter) {
            this.is_hunter = is_hunter;
        }

        public Object getCountry_code() {
            return country_code;
        }

        public void setCountry_code(Object country_code) {
            this.country_code = country_code;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_desc() {
            return user_desc;
        }

        public void setUser_desc(String user_desc) {
            this.user_desc = user_desc;
        }

        public static class TitleEntity {
            private boolean verified;
            private String name;

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ExperienceEntity {
            private int value;
            /**
             * name :
             * value : 6
             */

            private LevelInfoEntity level_info;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public LevelInfoEntity getLevel_info() {
                return level_info;
            }

            public void setLevel_info(LevelInfoEntity level_info) {
                this.level_info = level_info;
            }

            public static class LevelInfoEntity {
                private String name;
                private int value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }
        }
    }

    public static class StartPointEntity {
        private Object latitude;
        private Object longitude;

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }
    }

    public static class CoveredCountriesEntity {
        private int type;
        private int id;
        private String name;
        private String icon;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class DaysEntity {
        private String date;
        private int day;
        /**
         * photo : http://photos.breadtrip.com/photo_2016_03_06_5d80613c6a412efde6d38d5d50af7671.jpg?imageView/2/w/960/q/85
         * photo_1600 : http://photos.breadtrip.com/photo_2016_03_06_5d80613c6a412efde6d38d5d50af7671.jpg?imageView/2/w/1384/h/1384/q/85
         * poi : {"tel":"+81 01-34253333","currency":"CNY","is_nearby":true,"timezone":"Asia/Tokyo","id":2388358303,"category":11,"recommended_reason":"这里可观赏北海道三大夜景之一的\u201c小樽夜景\u201d，同时电影《情书》中片尾呼唤藤井树的雪原也是在此处取景。","fee":"1100円(成人缆车往返费用)，550円(儿童缆车往返费用)。","spot_region":"小樽市, 日本","date_added":"2013-07-04 12:21:53","time_consuming_max":0,"time_consuming":null,"extra1":"1425777","recommended":true,"location":{"lat":43.166938,"lng":140.969868},"opening_time":"9:30-21:00； 冬季期间9:00开始。4月和11月时，有可能因设备维修而停运缆车的情况。","type":5,"time_consuming_min":0,"website":"http://www.ckk.chuo-bus.co.jp/tenguyama/","description":"天狗山小樽夜景与函馆山函馆夜景，藻岩山札幌夜景并称为北海道三大夜景，可谓名不虚传。 这里是小樽的最高点，乘坐缆车到达天狗山顶，可以俯瞰小樽全城，尤其是到了夜晚，小樽在灯火下呈现出沿着海岸线扭动延伸的优雅线条，夜景如同地上的银河，非常美丽。 同时，《情书》中片尾呼唤藤井树的雪原，便是在天狗山上取景拍摄的哦！","arrival_type":"小樽站站前巴士乘降中心，乘坐9号巴士，至终点下车； 或乘坐小樽散策巴士(天狗山线)至终点站天狗山缆车站(天狗山ロープウェイ)下车，全程约20分钟。","address":"北海道小樽市最上2丁目16-15","verified":true,"name_en":"Tenguyama","icon":"http://media.breadtrip.com/images/icons/poi_category_11.png","name":"天狗山","popularity":28}
         * timezone : Asia/Tokyo
         * trip_id : 2387313031
         * id : 2349416574
         * city : 小樽市
         * privacy : 0
         * comments : 0
         * recommended : false
         * photo_webtrip : http://photos.breadtrip.com/photo_2016_03_06_5d80613c6a412efde6d38d5d50af7671.jpg?imageView/2/w/640/q/85
         * location : {"latitude":43.166938,"lat":43.166938,"lng":140.969868,"longitude":140.969868}
         * text : 2015年12月，为了体验一下霓虹国的圣诞气氛，我们再次前往日本。 从12月14日-26日，先去北海道追了雪，再回到关西过了圣诞节。 本篇游记主要记录我们行程的上半段，即函馆-小樽-札幌。 下半段勤劳的我也更新完啦，详情请移步：http://web.breadtrip.com/trips/2387313105/ 给你讲一讲我和@阿猴HOSEA 一起在关西度过的圣诞节：）
         * photo_weblive : http://photos.breadtrip.com/photo_2016_03_06_5d80613c6a412efde6d38d5d50af7671.jpg?imageView/2/w/278/q/75
         * shared : 0
         * province : 北海道
         * photo_s : http://photos.breadtrip.com/photo_2016_03_06_5d80613c6a412efde6d38d5d50af7671.jpg?imageView/1/w/280/h/280/q/75
         * track : false
         * hotel : null
         * device : 0
         * date_added : 1450149699
         * day : 1
         * country : 日本
         * cover : false
         * photo_w640 : http://photos.breadtrip.com/photo_2016_03_06_5d80613c6a412efde6d38d5d50af7671.jpg?imageView/1/w/640/h/480/q/85
         * photo_info : {"h":1600,"w":1063}
         * place : {"province":{"type":2,"id":4474},"country":{"type":1,"id":3808},"city":{"type":3,"id":26772}}
         * local_time : 2015-12-15 12:21:39
         * recommendations : 10
         * model : null
         */

        private List<WaypointsEntity> waypoints;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public List<WaypointsEntity> getWaypoints() {
            return waypoints;
        }

        public void setWaypoints(List<WaypointsEntity> waypoints) {
            this.waypoints = waypoints;
        }

        public static class WaypointsEntity {
            private String photo;
            private String photo_1600;

            private PoiEntity poi;
            private String timezone;
            private long trip_id;
            private long id;
            private String city;
            private int privacy;
            private int comments;
            private boolean recommended;
            private String photo_webtrip;
            /**
             * latitude : 43.166938
             * lat : 43.166938
             * lng : 140.969868
             * longitude : 140.969868
             */

            private LocationEntity location;
            private String text;
            private String photo_weblive;
            private int shared;
            private String province;
            private String photo_s;
            private boolean track;
            private Object hotel;
            private int device;
            private float date_added;
            private int day;
            private String country;
            private boolean cover;
            private String photo_w640;
            /**
             * h : 1600
             * w : 1063
             */

            private PhotoInfoEntity photo_info;
            /**
             * province : {"type":2,"id":4474}
             * country : {"type":1,"id":3808}
             * city : {"type":3,"id":26772}
             */

            private PlaceEntity place;
            private String local_time;
            private int recommendations;
            private Object model;

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getPhoto_1600() {
                return photo_1600;
            }

            public void setPhoto_1600(String photo_1600) {
                this.photo_1600 = photo_1600;
            }

            public PoiEntity getPoi() {
                return poi;
            }

            public void setPoi(PoiEntity poi) {
                this.poi = poi;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public long getTrip_id() {
                return trip_id;
            }

            public void setTrip_id(long trip_id) {
                this.trip_id = trip_id;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getPrivacy() {
                return privacy;
            }

            public void setPrivacy(int privacy) {
                this.privacy = privacy;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public boolean isRecommended() {
                return recommended;
            }

            public void setRecommended(boolean recommended) {
                this.recommended = recommended;
            }

            public String getPhoto_webtrip() {
                return photo_webtrip;
            }

            public void setPhoto_webtrip(String photo_webtrip) {
                this.photo_webtrip = photo_webtrip;
            }

            public LocationEntity getLocation() {
                return location;
            }

            public void setLocation(LocationEntity location) {
                this.location = location;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getPhoto_weblive() {
                return photo_weblive;
            }

            public void setPhoto_weblive(String photo_weblive) {
                this.photo_weblive = photo_weblive;
            }

            public int getShared() {
                return shared;
            }

            public void setShared(int shared) {
                this.shared = shared;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getPhoto_s() {
                return photo_s;
            }

            public void setPhoto_s(String photo_s) {
                this.photo_s = photo_s;
            }

            public boolean isTrack() {
                return track;
            }

            public void setTrack(boolean track) {
                this.track = track;
            }

            public Object getHotel() {
                return hotel;
            }

            public void setHotel(Object hotel) {
                this.hotel = hotel;
            }

            public int getDevice() {
                return device;
            }

            public void setDevice(int device) {
                this.device = device;
            }

            public int getDate_added() {
                return (int) date_added;
            }

            public void setDate_added(int date_added) {
                this.date_added = date_added;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public boolean isCover() {
                return cover;
            }

            public void setCover(boolean cover) {
                this.cover = cover;
            }

            public String getPhoto_w640() {
                return photo_w640;
            }

            public void setPhoto_w640(String photo_w640) {
                this.photo_w640 = photo_w640;
            }

            public PhotoInfoEntity getPhoto_info() {
                return photo_info;
            }

            public void setPhoto_info(PhotoInfoEntity photo_info) {
                this.photo_info = photo_info;
            }

            public PlaceEntity getPlace() {
                return place;
            }

            public void setPlace(PlaceEntity place) {
                this.place = place;
            }

            public String getLocal_time() {
                return local_time;
            }

            public void setLocal_time(String local_time) {
                this.local_time = local_time;
            }

            public int getRecommendations() {
                return recommendations;
            }

            public void setRecommendations(int recommendations) {
                this.recommendations = recommendations;
            }

            public Object getModel() {
                return model;
            }

            public void setModel(Object model) {
                this.model = model;
            }

            public static class PoiEntity {
                private String tel;
                private String currency;
                private boolean is_nearby;
                private String timezone;
                private long id;
                private int category;
                private String recommended_reason;
                private String fee;
                private String spot_region;
                private String date_added;
                private int time_consuming_max;
                private Object time_consuming;
                private String extra1;
                private boolean recommended;
                /**
                 * lat : 43.166938
                 * lng : 140.969868
                 */

                private LocationEntity location;
                private String opening_time;
                private int type;
                private int time_consuming_min;
                private String website;
                private String description;
                private String arrival_type;
                private String address;
                private boolean verified;
                private String name_en;
                private String icon;
                private String name;
                private int popularity;

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public boolean isIs_nearby() {
                    return is_nearby;
                }

                public void setIs_nearby(boolean is_nearby) {
                    this.is_nearby = is_nearby;
                }

                public String getTimezone() {
                    return timezone;
                }

                public void setTimezone(String timezone) {
                    this.timezone = timezone;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getCategory() {
                    return category;
                }

                public void setCategory(int category) {
                    this.category = category;
                }

                public String getRecommended_reason() {
                    return recommended_reason;
                }

                public void setRecommended_reason(String recommended_reason) {
                    this.recommended_reason = recommended_reason;
                }

                public String getFee() {
                    return fee;
                }

                public void setFee(String fee) {
                    this.fee = fee;
                }

                public String getSpot_region() {
                    return spot_region;
                }

                public void setSpot_region(String spot_region) {
                    this.spot_region = spot_region;
                }

                public String getDate_added() {
                    return date_added;
                }

                public void setDate_added(String date_added) {
                    this.date_added = date_added;
                }

                public int getTime_consuming_max() {
                    return time_consuming_max;
                }

                public void setTime_consuming_max(int time_consuming_max) {
                    this.time_consuming_max = time_consuming_max;
                }

                public Object getTime_consuming() {
                    return time_consuming;
                }

                public void setTime_consuming(Object time_consuming) {
                    this.time_consuming = time_consuming;
                }

                public String getExtra1() {
                    return extra1;
                }

                public void setExtra1(String extra1) {
                    this.extra1 = extra1;
                }

                public boolean isRecommended() {
                    return recommended;
                }

                public void setRecommended(boolean recommended) {
                    this.recommended = recommended;
                }

                public LocationEntity getLocation() {
                    return location;
                }

                public void setLocation(LocationEntity location) {
                    this.location = location;
                }

                public String getOpening_time() {
                    return opening_time;
                }

                public void setOpening_time(String opening_time) {
                    this.opening_time = opening_time;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getTime_consuming_min() {
                    return time_consuming_min;
                }

                public void setTime_consuming_min(int time_consuming_min) {
                    this.time_consuming_min = time_consuming_min;
                }

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getArrival_type() {
                    return arrival_type;
                }

                public void setArrival_type(String arrival_type) {
                    this.arrival_type = arrival_type;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public boolean isVerified() {
                    return verified;
                }

                public void setVerified(boolean verified) {
                    this.verified = verified;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPopularity() {
                    return popularity;
                }

                public void setPopularity(int popularity) {
                    this.popularity = popularity;
                }

                public static class LocationEntity {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationEntity {
                private double latitude;
                private double lat;
                private double lng;
                private double longitude;

                public double getLatitude() {
                    return latitude;
                }

                public void setLatitude(double latitude) {
                    this.latitude = latitude;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public double getLongitude() {
                    return longitude;
                }

                public void setLongitude(double longitude) {
                    this.longitude = longitude;
                }
            }

            public static class PhotoInfoEntity {
                private int h;
                private int w;

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }
            }

            public static class PlaceEntity {
                /**
                 * type : 2
                 * id : 4474
                 */

                private ProvinceEntity province;
                /**
                 * type : 1
                 * id : 3808
                 */

                private CountryEntity country;
                /**
                 * type : 3
                 * id : 26772
                 */

                private CityEntity city;

                public ProvinceEntity getProvince() {
                    return province;
                }

                public void setProvince(ProvinceEntity province) {
                    this.province = province;
                }

                public CountryEntity getCountry() {
                    return country;
                }

                public void setCountry(CountryEntity country) {
                    this.country = country;
                }

                public CityEntity getCity() {
                    return city;
                }

                public void setCity(CityEntity city) {
                    this.city = city;
                }

                public static class ProvinceEntity {
                    private int type;
                    private int id;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }

                public static class CountryEntity {
                    private int type;
                    private int id;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }

                public static class CityEntity {
                    private int type;
                    private int id;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }
            }
        }
    }
}
