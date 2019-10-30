package com.itex.ceva.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.itex.ceva.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Package com.itex.ceva.activity in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureListViewIsPresent() throws Exception {
        MainActivity activity = rule.getActivity();
        Button play = activity.findViewById(R.id.play);
        assertThat(play,notNullValue());
        assertThat(play, instanceOf(Button.class));

        TextView text = activity.findViewById(R.id.text);
        assertThat(text, notNullValue());
        assertThat(text, instanceOf(TextView.class));

    }
}

