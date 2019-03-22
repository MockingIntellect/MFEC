package mfec.example.danupol.githubcontact;

import android.os.AsyncTask;
import retrofit2.Call;

public class AsyncService<T> extends AsyncTask<Call<T>, Void, T> {

    private OnDoneListener<T> listener;

    public AsyncService(OnDoneListener<T> listener) {
        this.listener = listener;
    }

    @Override
    protected T doInBackground(Call<T>... calls) {
        for(Call<T> call: calls) {
            try {
                return call.execute().body();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);
        listener.OnDone(t);
    }

    public interface OnDoneListener<T> {
        void OnDone(T result);
    }
}
