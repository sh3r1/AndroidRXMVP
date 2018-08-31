package sherwin.pitao.githubrxmvp.screen.main;

import android.database.Observable;

import java.util.List;

import io.reactivex.disposables.Disposable;
import sherwin.pitao.githubrxmvp.model.Github;

public interface MainActivityContract {

    interface presenter {

        void onCreate();

        void onDestroy();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecycler(List<Github> githubObservable);

        void onFailure(Throwable throwable);
    }

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onSubscribe(Disposable disposable);
            void onNext(List<Github> githubObservable);
            void onError(Throwable e);
            void onComplete();
        }

        void getNotices(OnFinishedListener onFinishedListener);
    }
}
