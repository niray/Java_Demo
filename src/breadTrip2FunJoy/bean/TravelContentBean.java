package breadTrip2FunJoy.bean;

/**
 * Created by Chen Zhining
 * Date : 16/2/23.
 */
public class TravelContentBean {
    /**
     * id : 游记内容id
     * travels_id : 游记id
     * caption : 标题
     * images : 插图
     * content : 游记内容
     * date : 记录时间
     * uid : 发布人id
     * nickname : 发布人昵称
     * status : 发布状态
     * created_at : 发布时间
     * updated_up : 修改时间
     * image_width : 2322
     * image_height : 4128
     * updated_at : 2016-02-26 15:39:42
     */

    public String id;
    public String travels_id;
    public String caption;
    public String images;
    public String content;
    public long date;
    public String uid;
    public String nickname;
    public String status;
    public String created_at;
    public String updated_up;
    public int image_width;
    public int image_height;
    public String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTravels_id() {
        return travels_id;
    }

    public void setTravels_id(String travels_id) {
        this.travels_id = travels_id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_up() {
        return updated_up;
    }

    public void setUpdated_up(String updated_up) {
        this.updated_up = updated_up;
    }

    public int getImage_width() {
        return image_width;
    }

    public void setImage_width(int image_width) {
        this.image_width = image_width;
    }

    public int getImage_height() {
        return image_height;
    }

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
