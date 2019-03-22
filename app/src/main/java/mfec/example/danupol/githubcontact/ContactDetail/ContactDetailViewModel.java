package mfec.example.danupol.githubcontact.ContactDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import mfec.example.danupol.githubcontact.AsyncService;
import mfec.example.danupol.githubcontact.GitHubService;
import mfec.example.danupol.githubcontact.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactDetailViewModel extends ViewModel implements AsyncService.OnDoneListener<User> {

    private MutableLiveData<User> user = new MutableLiveData<>();

    // TODO: Implement the ViewModel
    public void getContact(String username){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<User> call = service.getContact(username);

        AsyncService<User> asyncService = new AsyncService<>(this);
        asyncService.execute(call);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    @Override
    public void OnDone(User result) {
        user.postValue(result);
    }
}
