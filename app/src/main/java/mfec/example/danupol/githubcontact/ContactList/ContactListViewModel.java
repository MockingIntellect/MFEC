package mfec.example.danupol.githubcontact.ContactList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import mfec.example.danupol.githubcontact.AsyncService;
import mfec.example.danupol.githubcontact.GitHubService;
import mfec.example.danupol.githubcontact.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactListViewModel extends ViewModel implements AsyncService.OnDoneListener<List<User>> {

    private List<User> users = new ArrayList<>();
    private MutableLiveData<List<User>> displayUser = new MutableLiveData<>();

    // TODO: Implement the ViewModel
    public void getAllContact(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<User>> call = service.getAllContact();

        AsyncService<List<User>> async = new AsyncService<>(this);
        async.execute(call);
    }

    public MutableLiveData<List<User>> getDisplayUser() {
        return displayUser;
    }

    @Override
    public void OnDone(List<User> result) {
        displayUser.postValue(result);
    }
}
