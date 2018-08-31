package sherwin.pitao.githubrxmvp.screen.main;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sherwin.pitao.githubrxmvp.api.GitHubClient;
import sherwin.pitao.githubrxmvp.model.Github;

public class GetNoticeIntractorImpl implements MainActivityContract.GetNoticeIntractor {

    @Override
    public void getNotices(final OnFinishedListener onFinishedListener) {

        Observable<List<Github>> git = GitHubClient.getInstance()
                .getGitHubUsers();


        git.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Github>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        onFinishedListener.onSubscribe(d);
                    }

                    @Override
                    public void onNext(List<Github> githubs) {
                        onFinishedListener.onNext(githubs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFinishedListener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        onFinishedListener.onComplete();
                    }
                });
    }
}
