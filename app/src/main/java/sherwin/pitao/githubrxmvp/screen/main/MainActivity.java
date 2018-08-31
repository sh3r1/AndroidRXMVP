package sherwin.pitao.githubrxmvp.screen.main;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sherwin.pitao.githubrxmvp.R;
import sherwin.pitao.githubrxmvp.adapter.GithubAdapter;
import sherwin.pitao.githubrxmvp.model.Github;

public class MainActivity extends AppCompatActivity implements MainActivityContract.MainView {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private MainActivityContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        presenter = new MainPresenterImpl(this,new GetNoticeIntractorImpl());
        presenter.requestDataFromServer();
        presenter.onCreate();
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.d("WEN",throwable.getMessage());
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void setDataToRecycler(List<Github> githubObservable) {
        GithubAdapter adapter = new GithubAdapter(getBaseContext(),githubObservable);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
