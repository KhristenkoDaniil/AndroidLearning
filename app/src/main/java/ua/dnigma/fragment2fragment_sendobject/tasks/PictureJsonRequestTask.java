package ua.dnigma.fragment2fragment_sendobject.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

import ua.dnigma.fragment2fragment_sendobject.model.PhotoModel;
import ua.dnigma.fragment2fragment_sendobject.model.PhotoResponseModel;

/**
 * Created by Даниил on 05.02.2017.
 */

public class PictureJsonRequestTask extends AsyncTask<Void,Integer, List<PhotoModel>> {
    private String url = "https://jsonplaceholder.typicode.com/photos";
    @Override
    protected List<PhotoModel> doInBackground(Void... strings) {
        List<PhotoModel> result = null;
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int codeStatus = httpResponse.getStatusLine().getStatusCode();
            if (codeStatus == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String data = EntityUtils.toString(httpEntity);
                Gson gson = new Gson();
                PhotoResponseModel photoResponseModel = gson.fromJson(data, PhotoResponseModel.class);
                Log.d("", "string");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
