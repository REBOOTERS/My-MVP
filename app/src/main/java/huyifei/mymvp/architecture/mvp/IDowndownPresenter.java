package huyifei.mymvp.architecture.mvp;

/**
 * Created by rookie on 2017/1/23.
 */

public interface IDowndownPresenter {
    /**
     * 下载
     * @param url
     */
    void download(String url);

    /**
     * 下载成功
     * @param result
     */
    void downloadSuccess(String result);

    /**
     * 当前下载进度
     * @param progress
     */
    void downloadProgress(int progress);

    /**
     * 下载失败
     */
    void downloadFail();
}
