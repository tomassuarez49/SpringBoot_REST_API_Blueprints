
package edu.eci.arsw.blueprints;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Set;

@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    BlueprintsServices bps;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String ... args) throws BlueprintNotFoundException{
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = appContext.getBean(BlueprintsServices.class);
        Point[] pts = new Point[] { new Point(0, 0), new Point(5, 10), new Point(10, 20), new Point(19, 20),
                new Point(7, 3), new Point(20, 1), new Point(0, 0) };

        Blueprint currentBp = new Blueprint("Tomas", "casa1", pts);
        bs.addNewBlueprint(currentBp);
        bs.addNewBlueprint(new Blueprint("Camilo", "casa2"));
        bs.addNewBlueprint(new Blueprint("Tomas", "casa3"));
        bs.addNewBlueprint(new Blueprint("Camilo", "casa4"));

        Set<Blueprint> author = bs.getBlueprintsByAuthor(currentBp.getAuthor());

        System.out.println("getCurrentBluePrintByAuthor");
        for (Blueprint b : author) {
            System.out.println(b);
        }

        System.out.println("getCurrentBluePrint");
        System.out.println(bs.getBlueprint(currentBp.getAuthor(), currentBp.getName()));
        ((ClassPathXmlApplicationContext) appContext).close();

    }

}
