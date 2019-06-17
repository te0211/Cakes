package com.teo.cakes;


import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.teo.cakes.view.adaptor.CakeAdapter;

import com.teo.cakes.view.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import static com.teo.cakes.BR.cake;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityJavaTest {

    @Mock
    private CakeViewModel cakeViewModel;

    @Mock
    private CakeAdapter cakeAdapter;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity .class);

    @Test
    public void ensureFrameLayoutIsPresent() throws Exception {

        MainActivity activity = rule.getActivity();
        FrameLayout viewById = activity.findViewById(R.id.fragment_container);
        assertThat(viewById, instanceOf(FrameLayout.class));

        List<Fragment> fragmentList = activity.getSupportFragmentManager().getFragments();
        assertEquals(fragmentList.size(), 1);
        Fragment fragment = (Fragment) fragmentList.get(0);
        assertThat(fragmentList.get(0), instanceOf(CakeListFragment.class));
        fragment = (CakeListFragment) fragment;

        // fragment testing
        View view = fragment.getView();
        View recyclerView = view.findViewById(R.id.cake_list);
        assertThat(recyclerView, instanceOf(RecyclerView.class));
        assertEquals(view.findViewById(R.id.loading_projects).getVisibility(), View.VISIBLE);
        recyclerView = (RecyclerView) recyclerView;

        cakeViewModel = ViewModelProviders.of(activity, new CakeViewModel.Factory(activity.getApplication()))
                .get(CakeViewModel.class);

        assertEquals(view.findViewById(R.id.loading_projects).getVisibility(), View.VISIBLE);
        cakeAdapter = (CakeAdapter) ((RecyclerView) recyclerView).getAdapter();

        int count = cakeAdapter.getItemCount();
        assertThat(count, greaterThan(0));

    }
}
