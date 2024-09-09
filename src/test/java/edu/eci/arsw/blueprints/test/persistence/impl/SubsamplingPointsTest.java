package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.*;
import edu.eci.arsw.blueprints.persistence.*;
import edu.eci.arsw.blueprints.persistence.impl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.*;

public class SubsamplingPointsTest {

    private Filter filtro;
    private List<Blueprint> lista;

    @Before
    public void setVariable() {
        filtro = new Subsampling();

        Point[] pts0 = new Point[]{new Point(40, 40), new Point(15, 15), new Point(15, 15)};
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        Point[] pts1 = new Point[]{new Point(40, 40), new Point(40, 40), new Point(40, 40), new Point(15, 15)};
        Blueprint bp1 = new Blueprint("camilo", "aaaaaaa", pts1);


        Point[] pts2 = new Point[]{new Point(40, 40), new Point(15, 15)};
        Blueprint bp2 = new Blueprint("tomas", "bbbbbbbbbbb", pts2);

        lista = new ArrayList<Blueprint>();
        lista.add(bp0);
        lista.add(bp1);
        lista.add(bp2);

    }

    @Test
    public void uniqueFilterTest() {
        Blueprint bl = lista.get(0);
        List<Point> list1 = bl.getPoints();
        Blueprint bl2 = filtro.applyFilter(bl);
        List<Point> list2 = bl2.getPoints();
        Assert.assertTrue(list1.size() != list2.size());
    }

    @Test
    public void multiFilterTest() {
        List<Point> b1 = lista.get(0).getPoints();
        List<Point> b2 = lista.get(1).getPoints();
        List<Point> b3 = lista.get(2).getPoints();
        Set<Blueprint> setB = new HashSet<Blueprint>(lista);
        Set<Blueprint> setResponse = filtro.multiFilterBlueprint(setB);
        List<Blueprint> listb = new ArrayList<>(setResponse);
        List<Point> p1 = listb.get(0).getPoints();
        List<Point> p2 = listb.get(1).getPoints();
        List<Point> p3 = listb.get(2).getPoints();
        Assert.assertTrue(b1.size() != p1.size());
        Assert.assertTrue(b2.size() != p2.size());
        Assert.assertTrue(b3.size() != p3.size());
    }

}
