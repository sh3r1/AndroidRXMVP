package sherwin.pitao.githubrxmvp.screen.main;

import android.util.Log;

import java.util.List;

import io.reactivex.disposables.Disposable;
import sherwin.pitao.githubrxmvp.model.Github;

public class MainPresenterImpl implements MainActivityContract.presenter, MainActivityContract.GetNoticeIntractor.OnFinishedListener {


    private MainActivityContract.MainView mainView;
    private MainActivityContract.GetNoticeIntractor intractor;


    public MainPresenterImpl(MainActivityContract.MainView mainView, MainActivityContract.GetNoticeIntractor noticeIntractor){
        this.mainView = mainView;
        this.intractor = noticeIntractor;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void requestDataFromServer() {
        intractor.getNotices(this);
    }

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(List<Github> githubObservable) {
        if(mainView != null){
            mainView.setDataToRecycler(githubObservable);
            mainView.hideProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mainView != null){
            mainView.onFailure(e);
            mainView.hideProgress();
        }
    }

    @Override
    public void onComplete() {
        if(mainView != null) mainView.hideProgress();
    }
}
