/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

    public InMemoryBlueprintPersistence() {
        // load stub data
        Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> authorBlueprints = new HashSet<>();
        for (Tuple<String, String> key : blueprints.keySet()) {
            authorBlueprints.add(getBlueprint(key.o1, key.o2));
        }
        return authorBlueprints;
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> authorBlueprints = new HashSet<>();
        for (Tuple<String, String> key : blueprints.keySet()) {
            if (author.equals(key.o1)) {
                authorBlueprints.add(getBlueprint(key.o1, key.o2));
            }
        }
        return authorBlueprints;
    }

}