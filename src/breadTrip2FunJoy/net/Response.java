package breadTrip2FunJoy.net;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.util.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据要求 可以接受任意格式文本数据 网络访问返回数据
 *
 * @author duohuo-jinghao
 */
public class Response {

    /**
     * 返回的纯文本
     */
    public String result;

    // 是否缓存的数据
    public boolean isCache;

    /**
     * 数据暂存
     */
    public Map<String, Object> bundle;
    // 操作是否成功
    public Boolean success;
    // 消息
    public String msg;
    // 错误码
    public String code;
    // 获取默认返回jsonarray jo对象的缓存 不一定有值
    JSONObject jo;
    private
    Gson gson = new Gson();

    public Response(String result) {
        bundle = new HashMap<String, Object>();
        this.result = result;
        this.success = true;
        // 默认处理结果为 true
        // 有返回success code 登按 返回结果
        if (!TextUtils.isEmpty(result)) {
            // json对象
            if (result.trim().startsWith("{")) {
                try {
                    jo = new JSONObject(result);
                    if (jo.has(Const.response_success)) {
                        success = JSONUtil.getBoolean(jo, Const.response_success);
                    } else if (jo.has(Const.response_status)) {
                        success = (JSONUtil.getInt(jo, Const.response_status) == 2000);
                    }
                    if (jo.has(Const.response_msg)) {
                        msg = jo.getString(Const.response_msg);
                    }
                    if (jo.has(Const.response_code)) {
                        code = JSONUtil.getString(jo, Const.response_code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (result.trim().startsWith("[")) {
                // 不处理
            }
        }
    }

    public Gson getGson() {
        return gson;
    }

    public boolean isCache() {
        return isCache;
    }

    public void isCache(boolean isCache) {
        this.isCache = isCache;
    }

    /**
     * 添加传递数据 基本在后台线程添加 前台用getBundle 获取
     *
     * @param key
     * @param obj
     */
    public void addBundle(String key, Object obj) {
        bundle.put(key, obj);
    }

    /**
     * 获取传递数据
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBundle(String key) {
        return (T) bundle.get(key);
    }

    /**
     * 返回纯文本
     *
     * @return
     */
    public String plain() {
        return result;
    }

    /**
     * 将返回的数据转换为jsonobject
     *
     * @return
     */
    public JSONObject JSON() {
        return jo;
    }

    /**
     * 将返回的数据转换为jsonArray
     *
     * @return
     */
    public JSONArray jSONArray() {
        try {
            return new JSONArray(this.result);
        } catch (JSONException e) {
//            e.printStackTrace();
            System.out.println("Response   String is not JSONArray" + e.getMessage());
        }
        return null;
    }

    /**
     * 解析json结果 为bean
     *
     * @return
     */
    public <T> T model(Class<T> clazz) {
        T obj = gson.fromJson(result, clazz);
        return obj;
    }

    /**
     * 解析json结果 为 bean list
     *
     * @return
     */
    public <T> List<T> list(final Class<T> clazz) {

        Type type = new ParameterizedType() {
            public Type getRawType() {
                return ArrayList.class;
            }

            public Type getOwnerType() {
                return null;
            }

            public Type[] getActualTypeArguments() {
                Type[] type = new Type[1];
                type[0] = clazz;
                return type;
            }
        };
        List<T> list = gson.fromJson(result, type);
        return list;
    }

    /**
     * 获取json结果 其中的某个属性 为 jsonarray
     *
     * @param prefix
     * @return
     */
    public JSONArray jSONArrayFrom(String prefix) {
        if (jo != null) {
            return JSONUtil.getJSONArray(jo, prefix);
        } else {
            return jSONArray();
        }
    }

    /**
     * 获取json结果 其中的data 为 jsonarray
     *
     * @return
     */
    public JSONArray jSONArrayFromData() {
        return jSONArrayFrom(Const.response_data);
    }

    /**
     * 获取json结果 其中的某个属性 为 jsonobject
     *
     * @param prefix
     * @return JSONObject
     */
    public JSONObject jSONFrom(String prefix) {
        if (jo != null) {
            return JSONUtil.getJSONObject(jo, prefix);
        }
        return null;
    }

    /**
     * 获取json结果 其中data为 jsonobject
     *
     * @return JSONObject
     */
    public JSONObject jSONFromData() {
        return jSONFrom(Const.response_data);
    }

    /**
     * 获取json结果 对象中的某个属性 为对象 prefix data
     *
     * @param prefix
     * @return ArrayList
     */
    public <T> T modelFrom(String prefix) {
        if (jo != null) {
            String str = JSONUtil.getString(jo, prefix);

            Type type = new TypeToken<T>() {
            }.getType();
            T obj = gson.fromJson(str, type);
            return obj;
        }
        return null;
    }

    /**
     * 解析json结果 为bean
     *
     * @return
     */
    public <T> T modelFrom(Class<T> clazz, String prefix) {
        String str = JSONUtil.getString(jo, prefix);

        T obj = gson.fromJson(str, clazz);
        return obj;
    }

    public <T> T modelFromData() {
        return modelFrom(Const.response_data);
    }

    public <T> T modelFromData(Class<T> clazz) {
        return modelFrom(clazz, Const.response_data);
    }

    /**
     * 获取json结果 对象中的某个属性 为对象 list
     *
     * @param prefix
     * @return
     */

    public <T> List<T> listFrom(final Class<T> clazz, String prefix) {
        try {
            if (jo != null) {
                String str = JSONUtil.getString(jo, prefix);

                Type type = new ParameterizedType() {
                    public Type getRawType() {
                        return ArrayList.class;
                    }

                    public Type getOwnerType() {
                        return null;
                    }

                    public Type[] getActualTypeArguments() {
                        Type[] type = new Type[1];
                        type[0] = clazz;
                        return type;
                    }
                };
                List<T> list = gson.fromJson(str, type);
                return list;
            }
        } catch (Exception e) {
            if (!Const.net_error_try) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> List<T> listFromData(Class<T> clazz) {
        return listFrom(clazz, Const.response_data);
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return TextUtils.isEmpty(msg) ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
