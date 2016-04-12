package breadTrip2FunJoy.net;


import org.json.JSONObject;

/**
 * 网络访问回调
 *
 * @author duohuo-jinghao
 */
public abstract class NetTask {

    /**
     * 线程ui传递
     */
    public static final int TRANSFER_DOUI = -400;
    /**
     * 利用缓存
     */
    public static final int TRANSFER_DOUI_ForCache = -403;

    /**
     * 线程取消传递
     */
    public static final int TRANSFER_DOCANCEL = -401;

    /**
     * 线程错误传递
     */
    public static final int TRANSFER_DOERROR = -800;

    public static final int TRANSFER_DOERROR_ForCache = -801;
    /**
     * code code处理
     */
    public static final int TRANSFER_CODE = -802;
    Response cacheResponse;
    // public Map<String,Response> responses;
    Response response;

    public NetTask() {
        super();
    }

    public Response getResponse() {
        return response;
    }

    public void onStart() {
    }

    /**
     * 前台ui处理 transfer
     *
     * @param response
     */
    public void doInUI(Response response, Integer transfer) {

    }

    /**
     * 后台运行 中间可调用 transfer通知是更新 通知的
     *
     * @return
     */
    public void doInBackground(Response response) {

    }

    public void onSuccess(JSONObject json) {
    }

    public void onFailure(Response response) {
//        if (!TextUtils.isEmpty(response.getMsg())) {
//            toastDialog.showToastShort(mContext, response.getMsg());
//        }
    }

    /**
     * 任务取消时运行
     */
    public void onCancelled() {
    }

    /**
     * 后台code处理默认调用全局的code处理
     *
     * @param response
     */
    public void OnCode(Response response) {
//		CodeHandler codehandler=Ioc.get(CodeHandler.class);
//		if(codehandler!=null){
//			if(mContext==null){
//				mContext=Ioc.getApplicationContext();
//			}
//			codehandler.onCode(mContext, response, response.code);
//		}
    }


    /**
     * 线程传递 将数据由后台线程传到前台 <br/>
     * 自己调用时不可 what 必须>0 防止和系统自定的冲突
     */
    public void transfer(Response response, Integer what) {
        if (response.isCache) {
            this.cacheResponse = response;
        } else {
            this.response = response;
        }

        NetTask task = (NetTask) this;
        switch (what) {
            case TRANSFER_DOCANCEL:
                try {
                    task.onCancelled();
                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case TRANSFER_DOERROR:
                try {
                    task.onFailure(response);
                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case TRANSFER_DOERROR_ForCache:
                try {
                    task.onFailure(task.cacheResponse);
                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case TRANSFER_DOUI:
                try {
                    task.doInUI(response, what);

                    if (response.isSuccess()) {
                        task.onSuccess(response.JSON());
                    } else {
                        task.onFailure(response);
                    }

                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case TRANSFER_DOUI_ForCache:
                try {
                    task.doInUI(task.cacheResponse, what);
                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case TRANSFER_CODE: {
                try {
                    task.OnCode(response);
                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }
            default:
                try {
                    task.doInUI(response, what);
                } catch (Exception e) {
                    if (!Const.net_error_try) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }
    }

}
