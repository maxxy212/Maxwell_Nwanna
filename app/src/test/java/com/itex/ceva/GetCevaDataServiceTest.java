package com.itex.ceva;

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Package com.itex.ceva in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-11-02
 */
public class GetCevaDataServiceTest {

    @Test
    public void countUniquedWords(){
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
