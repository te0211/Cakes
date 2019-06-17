package com.teo.cakes;

import android.util.Log;
import com.teo.cakes.service.model.Cake;
import org.junit.Test;

import org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    public void sortCakes() {
        Set<Cake> set = new HashSet<Cake>();

        // Add the elements to set
        List<Cake> unsorted = new ArrayList<Cake>();

        Cake c1 = new Cake("s", "sugar" , "");
        Cake c2 = new Cake("b", "bsugar" , "");
        Cake c3 = new Cake("s", "sugar" , "");


        unsorted.add(c1);
        unsorted.add(c2);
        unsorted.add(c3);


        set.addAll(unsorted);

        List<Cake> ls = new ArrayList<Cake>();
        ls.addAll(set);

        assertNotEquals(unsorted.size(), ls.size() );
    }
}
