package ua.dnigma.fragment2fragment_sendobject.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ua.dnigma.fragment2fragment_sendobject.R;
import ua.dnigma.fragment2fragment_sendobject.model.UserProfileModel;
import ua.dnigma.fragment2fragment_sendobject.ui.adapter.ReceiverAdapter;

public class ReceiverActivity extends AppCompatActivity {

    ListView listView;
    UserProfileModel userProfileModel;
    Runnable downloadPictures = new Runnable() {
        @Override
        public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setVisibility(View.INVISIBLE);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    };

    Thread newthread = new Thread(downloadPictures);


    public class DownloadImages extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... voids) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return voids[0];
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            listView.setBackgroundColor(aVoid);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciver);
        Intent intent = getIntent();
        userProfileModel = intent.getParcelableExtra("key1");
        Log.d("","");
        listView = (ListView)findViewById(R.id.listview);
        final ReceiverAdapter receiverAdapter = new ReceiverAdapter(this, userProfileModel.getAlbom().getPictures());
        listView.setAdapter(receiverAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(ReceiverActivity.this, receiverAdapter.getItem(position).getResource(), Toast.LENGTH_SHORT).show();
            }
        });
//        newthread.start();
        DownloadImages downloadImages = new DownloadImages();
        downloadImages.execute(Color.BLUE);

    }

}
