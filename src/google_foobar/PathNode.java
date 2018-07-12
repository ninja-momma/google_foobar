package google_foobar;

import java.util.LinkedList;

public class PathNode {

    private String name;
    private LinkedList<PathNode> parents;
    private Integer distance;

    PathNode(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<PathNode> getParents() {
        return parents;
    }

    public void setParents(LinkedList<PathNode> parents) {
        this.parents = parents;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void addParent(PathNode n){
        if (this.parents==null){
            this.parents = new LinkedList<>();
        }
        this.parents.add(n);
    }
}
