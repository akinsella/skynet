package org.helyx;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestUtils {

    public static List<Link> readLinks(String content) {
        InputStream in = new ByteArrayInputStream(content.getBytes());
        Scanner scanner = new Scanner(in);

        List<Link> links = new ArrayList<>();

        while (scanner.hasNextInt()) {
            links.add(new Link(scanner.nextInt(), scanner.nextInt()));
        }
        return links;
    }

}
