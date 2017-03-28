package huyifei.mymvp.architecture.mvp;

import android.os.Handler;
import android.os.Message;

import huyifei.mymvp.architecture.common.Constants;
import huyifei.mymvp.architecture.common.DownloadCallback;
import huyifei.mymvp.architecture.common.HttpUtil;

/**
 * Created by rookie on 2017/1/23.
 */

public class DownloadModel implements IDownloadModel {
    private IDowndownPresenter mIDowndownPresenter;


    private MyHandler mMyHandler = new MyHandler();


    public DownloadModel(IDowndownPresenter IDowndownPresenter) {
        mIDowndownPresenter = IDowndownPresenter;
    }

    @Override
    public void download(String url) {
        HttpUtil.HttpGet(url, new DownloadCallback(mMyHandler));
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    int percent = msg.arg1;
                    if (percent < 100) {
                        mIDowndownPresenter.downloadProgress(percent);
                    } else {
                        mIDowndownPresenter.downloadSuccess(Constants.LOCAL_FILE_PATH);
                    }
                    break;
                case 404:
                    mIDowndownPresenter.downloadFail();
                    break;
                default:
                    break;
            }
        }
    }
}
