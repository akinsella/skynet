package org.helyx;

import com.sun.tools.javah.Util;
import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.helyx.graph.Path;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestUtils {

    public static Scanner createScanner(String value) {
        InputStream in = new ByteArrayInputStream(value.getBytes());
        return new Scanner(in);
    }

    public static Graph readGraph(String value) {
        return new Graph(readLinks(value));
    }

    public static List<ExitNode> readExitNodes(String value) {
        Scanner scanner = createScanner(value);

        List<ExitNode> exitNodes = new ArrayList<>();

        while (scanner.hasNextInt()) {
            exitNodes.add(new ExitNode(scanner.nextInt()));
        }

        return exitNodes;
    }

    public static Path readPath(String value) {
        return new Path(readLinks(value));
    }

    public static List<Link> readLinks(String value) {
        Scanner scanner = createScanner(value);

        List<Link> links = new ArrayList<>();

        while (scanner.hasNextInt()) {
            links.add(new Link(scanner.nextInt(), scanner.nextInt()));
        }

        return links;
    }

}
