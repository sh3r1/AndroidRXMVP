package sherwin.pitao.githubrxmvp.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sherwin.pitao.githubrxmvp.model.Github;

public class GitHubClient {
    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GitHubClient instance;
    private IGitHubService gitHubService;

    private GitHubClient(){

        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gitHubService= retrofit.create(IGitHubService.class);
    }
    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }

    public Observable<List<Github>> getGitHubUsers() {
        return gitHubService.getGithubUsers();
    }

    public Observable<Github> getGitHubUser(String user) {
        return gitHubService.getGithubUser(user);
    }
}
