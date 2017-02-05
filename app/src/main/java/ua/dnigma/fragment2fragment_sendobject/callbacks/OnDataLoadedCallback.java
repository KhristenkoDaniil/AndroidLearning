package ua.dnigma.fragment2fragment_sendobject.callbacks;

import java.util.List;

import ua.dnigma.fragment2fragment_sendobject.model.MovieModel;

/**
 * Created by Даниил on 05.01.2017.
 */

public interface OnDataLoadedCallback {
    void onSucsses(List<MovieModel> movies);
    void onFail(String error);
    void beforeTaskStart();
    void afterTaskEnd();


}
