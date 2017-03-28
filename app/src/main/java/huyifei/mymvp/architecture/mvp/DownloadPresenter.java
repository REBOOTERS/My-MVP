package huyifei.mymvp.architecture.mvp;

/**
 * Created by rookie on 2017/1/23.
 */

public class DownloadPresenter implements IDowndownPresenter {
    private IDownloadView mIDownloadView;
    private IDownloadModel mIDownloadModel;


    public DownloadPresenter(IDownloadView IDownloadView) {
        mIDownloadView = IDownloadView;
        mIDownloadModel = new DownloadModel(this);
    }

    @Override
    public void download(String url) {
        mIDownloadView.showProgressBar(true);
        mIDownloadModel.download(url);
    }

    @Override
    public void downloadSuccess(String result) {
        mIDownloadView.showProgressBar(false);
        mIDownloadView.setView(result);
    }

    @Override
    public void downloadProgress(int progress) {
        mIDownloadView.setProcessProgress(progress);
    }

    @Override
    public void downloadFail() {
        mIDownloadView.showProgressBar(false);
        mIDownloadView.showFailToast();
    }
}
