package edu.eci.arsw.blueprints.test.persistence.impl;

import java.util.*;

import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.Filter;
import edu.eci.arsw.blueprints.persistence.impl.DuplicateFilter;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.impl.Subsampling;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class FiltersTest {

    private Filter filtro;
    private List<Blueprint> list;

    @Before
    public void setVariable() {
        filtro = new DuplicateFilter();

        Point[] pts0 = new Point[]{new Point(40, 40), new Point(15, 15), new Point(15, 15)};
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        Point[] pts1 = new Point[]{new Point(40, 40), new Point(40, 40), new Point(40, 40), new Point(15, 15)};
        Blueprint bp1 = new Blueprint("camilo", "aaaaaaa", pts1);


        Point[] pts2 = new Point[]{new Point(40, 40), new Point(15, 15)};
        Blueprint bp2 = new Blueprint("tomas", "bbbbbbbbbbb", pts2);

        list = new ArrayList<Blueprint>();
        list.add(bp0);
        list.add(bp1);
        list.add(bp2);
    }

    @Test
    public void DuplicatedFilterTest() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence imbp = new InMemoryBlueprintPersistence();
        DuplicateFilter fr = new DuplicateFilter();
        Point[] camilo = new Point[] { new Point(100, 100), new Point(15, 15), new Point(100, 100), new Point(100, 100),
                new Point(15, 15), new Point(15, 15) };
        Point[] murcia = new Point[] { new Point(15, 14) };
        Blueprint bp0 = new Blueprint("camilo", "aaaaa", camilo);
        Blueprint bp1 = new Blueprint("murciaa", "bbbbbbbbb", murcia);
        Blueprint bp2 = new Blueprint("tomas", "ccccccccc", new Point[] {});
        fr.applyFilter(bp0);
        imbp.saveBlueprint(bp0);
        Assert.assertEquals(2, bp0.getPoints().size());
        imbp.saveBlueprint(bp1);
        fr.applyFilter(bp1);
        Assert.assertEquals(1, bp1.getPoints().size());
        imbp.saveBlueprint(bp2);
        fr.applyFilter(bp2);
        Assert.assertEquals(0, bp2.getPoints().size());
    }
    @Test
    public void uniqueFilterTest() {
        Blueprint bl = list.get(0);
        List<Point> list1 = bl.getPoints();
        Blueprint bl2 = filtro.applyFilter(bl);
        List<Point> list2 = bl2.getPoints();
        Assert.assertFalse(list1.equals(list2));
    }

    @Test
    public void multiFilterTest() {
        List<Point> b1 = list.get(0).getPoints();
        List<Point> b2 = list.get(1).getPoints();
        List<Point> b3 = list.get(2).getPoints();
        Set<Blueprint> setB = new HashSet<Blueprint>(list);
        Set<Blueprint> setResponse = filtro.multiFilterBlueprint(setB);
        List<Blueprint> listb = new ArrayList<>(setResponse);
        List<Point> p1 = listb.get(0).getPoints();
        List<Point> p2 = listb.get(1).getPoints();
        List<Point> p3 = listb.get(2).getPoints();
        Assert.assertFalse(b1.equals(p1));
    }

    @Test
    public void SubsamplingFilterTest() {
        Subsampling ss = new Subsampling();
        Point[] camilo = new Point[] { new Point(10, 10), new Point(14, 12), new Point(19, 20), new Point(34, 25),
                new Point(1, 4),
                new Point(14, 18), new Point(1, 4), new Point(9, 80), new Point(8, 7), new Point(14, 25) };
        Point[] tomas = new Point[] { new Point(1, 2), new Point(2, 6) };
        Blueprint bp0 = new Blueprint("author1", "hola", camilo);
        Blueprint bp1 = new Blueprint("author2", "adios", tomas);
        Blueprint bp2 = new Blueprint("tomas", "prueba4", new Point[] {});
        ss.applyFilter(bp0);
        ss.applyFilter(bp1);
        ss.applyFilter(bp2);
        Assert.assertEquals(5, bp0.getPoints().size());
        Assert.assertEquals(1, bp1.getPoints().size());
        Assert.assertEquals(0, bp2.getPoints().size());
        Assert.assertEquals(Arrays.asList(new Point[] {}).toString(), bp2.getPoints().toString());
    }

}