package breadTrip2FunJoy.bean;

import java.io.Serializable;

/**
 * 游记头
 * Created by ChenZhining on 15/8/25.
 */
public class TravelsHeaderBean implements Serializable {

    public String id;
    public String title;
    public String district;
    public String cover_img;
    public String status;
    public String uid;
    public String is_prefect;
    public String item_sort;
    public String created_at;
    public String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIs_prefect() {
        return is_prefect;
    }

    public void setIs_prefect(String is_prefect) {
        this.is_prefect = is_prefect;
    }

    public String getItem_sort() {
        return item_sort;
    }

    public void setItem_sort(String item_sort) {
        this.item_sort = item_sort;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
