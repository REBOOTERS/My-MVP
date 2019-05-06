package huyifei.mymvp.architecture.newmvp;

/**
 * @author: zhuyongging
 * @since: 2019-05-05
 */
public class SearchPresenter extends BasePresenter {

    private IViewinterface mIViewinterface;

    public SearchPresenter(IViewinterface IViewinterface) {
        mIViewinterface = IViewinterface;
    }

    @Override
    public void dispatch(Action action) {
        if (action.actionName.equals("search")) {
            mIViewinterface.searchResult();
        }
    }
}
