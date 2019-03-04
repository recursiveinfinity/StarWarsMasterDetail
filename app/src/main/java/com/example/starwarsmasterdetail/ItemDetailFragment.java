package com.example.starwarsmasterdetail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.starwarsmasterdetail.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String PLANET_NAME = "planet_name";
    public static final String FILMS_LIST = "films_list";

    private String name = "";
    private final ArrayList<String> films = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(PLANET_NAME)
                && getArguments().containsKey(FILMS_LIST)) {
            name = getArguments().getString(PLANET_NAME);
            films.addAll(getArguments().getStringArrayList(FILMS_LIST));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        TextView tvResult = rootView.findViewById(R.id.item_detail);

        StringBuilder stringBuilder = new StringBuilder();
        for (String film : films) {
            stringBuilder.append(film);
            stringBuilder.append("\n");
        }
        stringBuilder.append(name);
        tvResult.setText(stringBuilder.toString());

        return rootView;
    }
}
