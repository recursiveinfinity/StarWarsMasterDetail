package com.example.starwarsmasterdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starwarsmasterdetail.home.FilmsAdapter;
import com.example.starwarsmasterdetail.home.HomeContract;
import com.example.starwarsmasterdetail.home.HomePresenter;
import com.example.starwarsmasterdetail.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements HomeContract.View, FilmsAdapter.OnItemSelectedListener {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private HomeContract.Presenter presenter;
    private final FilmsAdapter adapter = new FilmsAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);


        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        presenter = new HomePresenter(this);
        presenter.loadAllFilms();

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFilms(List<Result> films) {
        adapter.setData(films);
    }

    @Override
    public void showPlanetDetails(String name, List<String> films) {
        if (mTwoPane) {
            ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ItemDetailFragment.PLANET_NAME, name);
            bundle.putStringArrayList(ItemDetailFragment.FILMS_LIST, (ArrayList) films);

            itemDetailFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, itemDetailFragment)
                    .commit();

        } else {
            Intent intent = new Intent(this, ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.PLANET_NAME, name);
            intent.putStringArrayListExtra(ItemDetailFragment.FILMS_LIST, (ArrayList)films);
            startActivity(intent);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(Result result) {
        presenter.onFilmSelected(result);
    }

}
