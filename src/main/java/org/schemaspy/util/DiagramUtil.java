package org.schemaspy.util;

import org.schemaspy.view.MustacheTableDiagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by rkasa on 2016-04-16.
 */
public class DiagramUtil {

    public static void generateDiagram(String diagramName, Dot dot, File dotFile, File diagramFile, List<MustacheTableDiagram> diagrams, boolean isActive, boolean isImplied) throws Dot.DotFailure {
        if (dotFile.exists()) {
            String mapDegreesDotFile = dot.generateDiagram(dotFile, diagramFile);
            createDiagram(diagramName, diagramFile, mapDegreesDotFile, diagrams, isActive, isImplied);
        } else {
            dotFile.delete();
            diagramFile.delete();
        }
    }

    private static void createDiagram(String diagramName, File diagramFile, String diagramMap, List<MustacheTableDiagram> diagrams, boolean isActive, boolean isImplied) {
        MustacheTableDiagram diagram = new MustacheTableDiagram();
        diagram.setActive(isActive);
        diagram.setName(diagramName);
        diagram.setFileName(diagramFile.getName());
        diagram.setMap(diagramMap);
        String diagramId = diagramName.replace(" ", "").toLowerCase();
        diagram.setId(diagramId + "DegreeImg");
        diagram.setMapName(diagramMapName(diagramMap));
        diagram.setIsImplied(isImplied);
        diagrams.add(diagram);
        diagramMap = null;
    }

    private static String diagramMapName(String diagramMap) {
        BufferedReader reader = new BufferedReader(new StringReader(diagramMap));
        String line;
        String diagramMapName="";
        try {
            line = reader.readLine();
            if (line != null) {
                diagramMapName = line.substring(9,line.indexOf("name")-2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diagramMapName;
    }

    public static Object diagramExists(List<MustacheTableDiagram> diagrams) {
        Object exists = null;
        if  (!diagrams.isEmpty()) {
            exists = new Object();
        }
        return exists;
    }

    public static void markFirstAsActive(List<MustacheTableDiagram> diagrams) {
        if (diagrams != null && !diagrams.isEmpty()) {
            MustacheTableDiagram diagram = diagrams.get(0);
            if (diagram != null) {
                diagram.setActive(true);
            }
        }
    }
}
