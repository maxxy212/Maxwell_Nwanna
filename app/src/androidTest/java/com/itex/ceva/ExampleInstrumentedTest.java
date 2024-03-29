package com.itex.ceva;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.itex.ceva", appContext.getPackageName());

        // Extracting words from string
        String str = "truw jdshs kwhbx wknjuw kxjubxw xnibxw xkwlbdwj xkbxuiw xckbnbuiw";
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(str);
        // Map to store count of a word
        HashMap<String, Integer> hm = new HashMap<>();
        // if a word found
        while (m.find()) {
            String word = m.group();
            // If this is first occurrence of word
            if (!hm.containsKey(word))
                hm.put(word, 1);
            else
                // increment counter of word
                hm.put(word, hm.get(word) + 1);
        }

        // Traverse map and print all words whose count
        // is  1
        Set<String> s = hm.keySet();
        assertThat(s.size(), instanceOf(Integer.class));
    }
}
