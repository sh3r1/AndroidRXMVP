package sherwin.pitao.githubrxmvp.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sherwin.pitao.githubrxmvp.model.Github;

public interface IGitHubService {

    @GET("users")
    Observable<List<Github>> getGithubUsers();


    @GET("users/{username}")
    Observable<Github> getGithubUser(@Path("username") String user);

}
