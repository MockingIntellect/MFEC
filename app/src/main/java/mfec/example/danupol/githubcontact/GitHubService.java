package mfec.example.danupol.githubcontact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("/users?since=0")
    Call<List<User>> getAllContact();

    @GET("/users/{username}")
    Call<User> getContact(@Path("username") String username);
}
