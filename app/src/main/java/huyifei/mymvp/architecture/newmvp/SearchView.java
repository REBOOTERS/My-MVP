package huyifei.mymvp.architecture.newmvp;

/**
 * @author: zhuyongging
 * @since: 2019-05-05
 */
public class SearchView implements IViewinterface {

    private BasePresenter mBasePresenter = new SearchPresenter(this);

    @Override
    public void searchResult() {
        mBasePresenter.dispatch(new SearchAction("search"));
    }
}
