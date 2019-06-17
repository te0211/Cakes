package com.teo.cakes;


import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
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
    private CakeAdapter cakeAdapter;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity .class);

    @Test
    public void ensureFrameLayoutIsPresent() throws Exception {

        MainActivity activity = rule.getActivity();

        View recyclerView = activity.findViewById(R.id.main_list);
        assertThat(recyclerView, instanceOf(RecyclerView.class));

        recyclerView = (RecyclerView) recyclerView;

       // activity.getApplication().onCreate();
        cakeAdapter = (CakeAdapter) ((RecyclerView) recyclerView).getAdapter();

        int count = cakeAdapter.getItemCount();
        assertThat(count, greaterThan(0));

    }
}
