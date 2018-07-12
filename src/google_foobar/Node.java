package google_foobar;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Node {

    LinkedList<Node> neighbors;
    String parent;
    String name;

    public Integer getJump() {
        return jump;
    }

    public void setJump(Integer jump) {
        this.jump = jump;
    }

    Integer jump;

    public Node(String name){
        this.name = name;
        neighbors = new LinkedList<>();
    }

    public LinkedList<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNeighbor(Node node){
        neighbors.add(node);
    }
}
