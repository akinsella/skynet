package org.helyx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.unmodifiableList;

public class GameState {

    private int nodeCount;
    private int linkCount;

    private int exitCount;

    private List<Link> links;
    private List<Gateway> gateways;
    private List<Link> cutLinks = new ArrayList<>();

    private int skynetAgentIndice;

    private List<Link> uncutLinks;


    private GameState() {
        super();
    }

    public static GameState buildGameState(Scanner in) {

        GameState gs = new GameState();

        gs.nodeCount = in.nextInt(); // the total number of nodes in the level, including the gateways
        gs.linkCount = in.nextInt(); // the number of links
        gs.exitCount = in.nextInt(); // the number of exit gateways

        gs.links = new ArrayList<>();
        gs.gateways = new ArrayList<>();

        for (int i = 0; i < gs.linkCount; i++) {
            int node1 = in.nextInt(); // node1 and node2 defines a link between these nodes
            int node2 = in.nextInt();

            gs.links.add(new Link(node1, node2));
        }

        for (int i = 0; i < gs.exitCount; i++) {
            int exitIndice = in.nextInt(); // the index of a gateway node

            gs.gateways.add(new Gateway(exitIndice));
        }

        gs.uncutLinks = new ArrayList(gs.links);

        return gs;
    }

    public void consumeNextSkynetAgentAction(int skynetAgentIndice) {
        this.skynetAgentIndice = skynetAgentIndice;
    }

    public void cutLink(Link link) {
        cutLinks.add(link);
        uncutLinks.remove(link);
    }

    public List<Gateway> getGateways() {
        return unmodifiableList(gateways);
    }

    public List<Link> getCutLinks() {
        return unmodifiableList(cutLinks);
    }

    public List<Link> getUncutList() {
        return unmodifiableList(uncutLinks);
    }

    public int getSkynetAgentIndice() {
        return skynetAgentIndice;
    }

}