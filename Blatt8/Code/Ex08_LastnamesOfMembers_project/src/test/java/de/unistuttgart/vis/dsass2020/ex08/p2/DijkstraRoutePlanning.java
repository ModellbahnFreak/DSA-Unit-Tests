package de.unistuttgart.vis.dsass2020.ex08.p2;

import de.unistuttgart.vis.dsass2020.ex08.p2.MetaData.JunctionDetails;
import de.unistuttgart.vis.dsass2020.ex08.p2.MetaData.StreetDetails;

import java.util.Locale;

/**
 * Class working on the street data of Birmingham, US calculating the best location between the university ant the
 * health center
 *
 */
public class DijkstraRoutePlanning {

    /**
     * Main function for calculating the point
     * <p>
     * The best point is calculated by loading the street graph, using two instaces of {@link ShortestPath} to
     * calculate the shortest path from the uni and the health center to all points and then calculating the
     * optimal points using {@link ShortestPath#closestPointsBetween(ShortestPath, int)}
     * <p>
     * The results for the 1, 10 and 100 best points will be printed as sets and for the best point the path
     * from the uni and health center to is as well as the coordinates and a link to OSM will be printed
     *
     * @param args Unused. Can be anything.
     */
    public static void main(final String[] args) {
        final StreetGraph g = GraphLoader.loadBirminghamStreetGraph();
        System.out.println("nodes " + g.numberOfNodes() + "  edges " + g.numberOfEdges());
        final ShortestPath<JunctionDetails, StreetDetails> calculationFromUni = new ShortestPath<>(g, MetaData.NODE_UNIVERSITY);
        final ShortestPath<JunctionDetails, StreetDetails> calculationFromMedic = new ShortestPath<>(g, MetaData.NODE_HEALTH_CENTER);

        final int bestNode = calculationFromUni.closestPointsBetween(calculationFromMedic, 1).stream().findAny().get();
        System.out.println("Best location: " + bestNode);
        System.out.println(String.format("Path from Uni to best Node: %s", calculationFromUni.pathTo(bestNode)));
        System.out.println(String.format("Path from Medic to best Node: %s", calculationFromMedic.pathTo(bestNode)));
        System.out.println(String.format("Coordinates of best node: %s°  %s°", g.getNodeMetaData(bestNode).y,
                g.getNodeMetaData(bestNode).x));
        System.out.println(String.format(Locale.ENGLISH, "Link on map: https://www.openstreetmap.org/#map=17/%s/%s",
                g.getNodeMetaData(bestNode).y,
                g.getNodeMetaData(bestNode).x));
        System.out.println(calculationFromUni.closestPointsBetween(calculationFromMedic, 10));
        System.out.println(calculationFromUni.closestPointsBetween(calculationFromMedic, 100));
    }

}
