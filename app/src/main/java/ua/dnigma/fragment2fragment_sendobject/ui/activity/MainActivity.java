package ua.dnigma.fragment2fragment_sendobject.ui.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import ua.dnigma.fragment2fragment_sendobject.R;
import ua.dnigma.fragment2fragment_sendobject.callbacks.OnDataLoadedCallback;
import ua.dnigma.fragment2fragment_sendobject.callbacks.OnDataProgressUpdate;
import ua.dnigma.fragment2fragment_sendobject.enums.GenderType;
import ua.dnigma.fragment2fragment_sendobject.model.AlbumModel;
import ua.dnigma.fragment2fragment_sendobject.model.MovieModel;
import ua.dnigma.fragment2fragment_sendobject.model.PictureModel;
import ua.dnigma.fragment2fragment_sendobject.model.UserProfileModel;
import ua.dnigma.fragment2fragment_sendobject.tasks.ImaxRequestTask;
import ua.dnigma.fragment2fragment_sendobject.ui.adapter.MovieAdapter;
import ua.dnigma.fragment2fragment_sendobject.ui.fragment.MovieListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String URL = "http://planetakino.ua/showtimes/xml";
    MovieModel movieModel;
    ImaxRequestTask imaxRequestTask;

    Button startActivityButton;
    TextView movieCounterField;

    private int[] resourceIds = {R.drawable.ic_launcher_cat, R.drawable.ios, R.drawable.light_color,
            R.drawable.night_fury, R.drawable.pinkhellokitty};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivityButton = (Button) findViewById(R.id.start_activity_button);
        startActivityButton.setOnClickListener(this);
//        startActivityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }


    private void startActivityWithData(UserProfileModel userProfileModel) {
        Intent intent = new Intent(this, ReceiverActivity.class);
        intent.putExtra("key1", userProfileModel);
        startActivity(intent);

    }

    private UserProfileModel createUserProfileModel() {
        UserProfileModel userProfileModel =
                new UserProfileModel(56, "Dan", "Christenko", GenderType.MALE, "http://foto");
        userProfileModel.setAge(12);
        List<PictureModel> pictures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PictureModel pictureModel = new PictureModel(i + 1, resourceIds[i]);
            pictures.add(pictureModel);
        }
        AlbumModel albomModel = new AlbumModel(5, pictures, "15.12.2016");
        userProfileModel.setAlbom(albomModel);
        return userProfileModel;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_activity_button:
                startFragment();

//                startActivityWithData(createUserProfileModel());
                break;

        }
    }

    private void startFragment() {
        String tag = MovieListFragment.class.getName();
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if ((fragment = fragmentManager.findFragmentByTag(tag)) != null) {
            fragmentTransaction.replace(R.id.cotainer_id, fragment, tag);
        } else {
            fragmentTransaction.replace(R.id.cotainer_id, new MovieListFragment(), tag);
        }
        fragmentTransaction.commit();

    }


    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void unlockScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }


}
