package huyifei.mymvp.architecture.mvp;

/**
 * Created by rookie on 2017/1/23.
 */

public interface IDownloadView {
    /**
     * 显示进度条
     * @param show
     */
    void showProgressBar(boolean show);

    /**
     * 设置进度条进度
     * @param progress
     */
    void setProcessProgress(int progress);

    /**
     * 根据数据设置view
     * @param result
     */
    void setView(String result);

    /**
     * 设置请求失败时的view
     */
    void showFailToast();
}
