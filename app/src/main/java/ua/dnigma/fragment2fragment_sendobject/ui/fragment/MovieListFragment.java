package ua.dnigma.fragment2fragment_sendobject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ua.dnigma.fragment2fragment_sendobject.R;
import ua.dnigma.fragment2fragment_sendobject.callbacks.OnDataLoadedCallback;
import ua.dnigma.fragment2fragment_sendobject.callbacks.OnDataProgressUpdate;
import ua.dnigma.fragment2fragment_sendobject.model.MovieModel;
import ua.dnigma.fragment2fragment_sendobject.tasks.ImaxRequestTask;
import ua.dnigma.fragment2fragment_sendobject.ui.activity.MainActivity;
import ua.dnigma.fragment2fragment_sendobject.ui.adapter.MovieAdapter;

/**
 * Created by Даниил on 25.01.2017.
 */

public class MovieListFragment extends Fragment implements OnDataLoadedCallback, OnDataProgressUpdate {

    TextView movieCounter;
    ListView movieList;
    Boolean isLoading = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragmet_layout, container, false);
        movieCounter = (TextView) view.findViewById(R.id.movie_counter);
        movieList = (ListView) view.findViewById(R.id.movie_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isLoading) {
            startLoading();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void startLoading() {
        ImaxRequestTask imaxRequestTask = new ImaxRequestTask(this);
        imaxRequestTask.setOnDataProgressUpdate(this);
        imaxRequestTask.execute(MainActivity.URL);

    }

    @Override
    public void onSucsses(List<MovieModel> movies) {
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movies);
        movieList.setAdapter(movieAdapter);
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void beforeTaskStart() {
        isLoading = true;

    }

    @Override
    public void afterTaskEnd() {

    }

    @Override
    public void onProgressUpdate(Integer movieCounter) {
        this.movieCounter.setText(String.valueOf(movieCounter));

    }
}
