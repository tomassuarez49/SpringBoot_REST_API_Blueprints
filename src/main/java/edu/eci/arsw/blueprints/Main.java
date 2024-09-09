
package edu.eci.arsw.blueprints;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class Main {

    public static void main(String a[]) throws BlueprintNotFoundException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpps = appContext.getBean(BlueprintsServices.class);
        Point[] pts = new Point[] { new Point(0, 0), new Point(5, 10), new Point(10, 20), new Point(19, 20),
                new Point(7, 3), new Point(20, 1), new Point(0, 0) };

        Blueprint currentBp = new Blueprint("Tomas", "casa1", pts);
        bpps.addNewBlueprint(currentBp);
        bpps.addNewBlueprint(new Blueprint("Camilo", "casa2"));
        bpps.addNewBlueprint(new Blueprint("Tomas", "casa3"));
        bpps.addNewBlueprint(new Blueprint("Camilo", "casa4"));

        Set<Blueprint> author = bpps.getBlueprintsByAuthor(currentBp.getAuthor());

        System.out.println("getCurrentBluePrintByAuthor");
        for (Blueprint b : author) {
            System.out.println(b);
        }

        System.out.println("getCurrentBluePrint");
        System.out.println(bpps.getBlueprint(currentBp.getAuthor(), currentBp.getName()));
        ((ClassPathXmlApplicationContext) appContext).close();

    }
}
