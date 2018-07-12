package google_foobar;

import java.util.*;

public class NodeGraph {

    int numberOfVertices;
    HashMap<String, Node> edges;
    LinkedList<String> path = new LinkedList<>();
    List<LinkedList<String>> jumpPaths = new ArrayList<>();
    boolean found = false;

    NodeGraph(int numberOfVertices){
        edges = new HashMap<String, Node>(numberOfVertices);

    }

    void addEdge(String pointA, String pointB){
//        System.out.println("Adding "+pointA+" and "+pointB);
        Node a;
        Node b = new Node(pointB);
        if (edges.containsKey(pointA)){
//            System.out.println(pointA+ " already exists, adding neighbor "+pointB);
            a = edges.get(pointA);
            a.addNeighbor(b);
//            System.out.println("Neighbor List");
//            for (Node x :a.getNeighbors()){
//                System.out.println("--"+x.getName());
//            }

        } else {
//            System.out.println(pointA+ " does not exist, creating");
            a = new Node(pointA);
            a.addNeighbor(b);
//            System.out.println("Neighbor List");
            for (Node x : a.getNeighbors()){
//                System.out.println("--"+x.getName());
            }
            edges.put(pointA, a);
//            System.out.println("put in list");
        }


        if (edges.containsKey(pointB)){
//            System.out.println(pointB+" already exists, adding neighbor");
            b = edges.get(pointB);
            b.addNeighbor(a);
        } else {
//            System.out.println(pointB+ " does not exist, creating");
            b.addNeighbor(a);
            edges.put(pointB, b);
        }

    }

    public void doesPathExistBetweenAandB(String pointA, String pointB) {

        HashSet<String> visited = new HashSet<>(this.numberOfVertices);

        Node nodeA = edges.get(pointA);

        path.add(pointA);
        dfsFromAtoB(nodeA, visited, path, pointA, pointB);
        if (found) {
            System.out.println("=============== PATH ================ ");
            for (String p : path) {
                System.out.print(p + " ");
            }
            System.out.println("");
        } else {
            System.out.println("No path found");
        }
    }


        private void dfsFromAtoB(Node n, HashSet<String> visited, LinkedList<String>path, String pointA, String pointB) {
        if (found){
            return;
        }
        else if (edges.containsKey(n.getName())) {
            n = edges.get(n.getName());
            System.out.println("visiting "+ n.getName()+" with #neighbors "+n.getNeighbors().size());
            visited.add(n.getName());
            Iterator neighbors = n.getNeighbors().listIterator();
            while (neighbors.hasNext()) {
                Node next = (Node) neighbors.next();
                if (next.getName().equals(pointB)){
                    path.add(next.getName());
                    System.out.println("Found!");
                    found = true;
                   return;
                } else if (!found) {
//                    System.out.println("neighbor " + next.getName());

                    if (!visited.contains(next.getName())) {
                        path.add(next.getName());
                        //                visited.add(next.getName());
//                        System.out.println("havent visited, visit now");
                        dfsFromAtoB(next, visited, path, pointA, pointB);
                    }
                }
            }
        } else {
            System.out.print(n.getName()+  " end of the line");
            if (n.getName().equals(pointB)){
                path.add(n.getName());
                found = true;
            }else {
                path.clear();
                path.add(pointA);
            }
        }

    }

//    public void findPathsWithNumberOfJumps(int x, String pointA){
//        HashSet<String> visited = new HashSet<>(this.numberOfVertices);
//
//        Node nodeA = edges.get(pointA);
//        visited.add(nodeA.getName());
//        LinkedList<String> p = new LinkedList<String>();
//        p.add(pointA);
//
//        dfsJumps(1,x,nodeA, visited, pointA, p);
//
//        System.out.println("================== Jump Path Max Jumps: "+x+" ================");
//        int c = 1;
//        for (LinkedList<String> path : jumpPaths){
//            System.out.println("-- path "+c+" --");
//            for (String s: path){
//                System.out.print(s+" ");
//            }
//            c++;
//            System.out.println("");
//        }
//    }

        public void findPathsWithNumberOfJumps(int maxJumps, String pointA ){

        HashSet<String> visited = new HashSet<>(this.numberOfVertices);
        HashMap<String, PathNode>jumps = new HashMap<>();

        LinkedList<Node> queue = new LinkedList<Node>();
        List<LinkedList<PathNode>> destinations = new ArrayList<>();

        Node nodeA = edges.get(pointA);

        visited.add(nodeA.getName());
        PathNode copyOfA = new PathNode(pointA);

        copyOfA.setDistance(new Integer(0));

          jumps.put(pointA, copyOfA);
        queue.add(nodeA);



//        bfs
        while(queue.size()!=0  ){
            // remove from queue
            nodeA = queue.poll();
            if (jumps.get(nodeA.getName()).getDistance()<maxJumps){
//            System.out.println("Current Node: "+nodeA.getName()+" and jump distance "+jumps.get(nodeA.getName()).getDistance());


                if (edges.get(nodeA.getName())!=null) {
                    Iterator<Node> i = edges.get(nodeA.getName()).getNeighbors().listIterator();

                    while (i.hasNext()) {
                        Node n = i.next();
//                        System.out.println("next neighbor: "+n.getName());

                        if (!visited.contains(n.getName())) {
                            visited.add(n.getName());
                            PathNode parentPathNode = jumps.get(nodeA.getName());
                            PathNode newChildPathNode = new PathNode(n.getName());
                            newChildPathNode.setDistance(parentPathNode.getDistance()+1);
                            LinkedList<PathNode> parents = new LinkedList<>();
                            if (parentPathNode.getParents()!=null) {
                                parents.addAll(parentPathNode.getParents());
                            }
                            parents.add(parentPathNode);
                            newChildPathNode.setParents(parents);


                            jumps.put(n.getName(), newChildPathNode);
                            queue.add(n);
                        }

                    }
                }
            } else {

                // got to an end node, add it to the list of destinations
                PathNode endNode = jumps.get(nodeA.getName());
                endNode.addParent(endNode);
                destinations.add(endNode.getParents());
            }

        }

        int i=1;
        for (LinkedList<PathNode> paths : destinations){
            System.out.print("Path "+i);
            for(PathNode p : paths){
                System.out.print(" "+p.getName());
            }
            System.out.println(" ");
            i++;
        }

        System.out.println("Destinations "+maxJumps+" from "+pointA);
        Set<String> jumpNodes = jumps.keySet();
        Iterator it = jumpNodes.iterator();
        while(it.hasNext()){
            System.out.print(" "+(String)it.next());
        }





    }

    private void dfsJumps(int count, int max, Node n, HashSet<String> visited, String pointA, LinkedList<String> p) {
        System.out.println("visiting "+n.getName());
//        if (edges.containsKey(n.getName())) {
            n = edges.get(n.getName());
//            visited.add(n.getName());
            Iterator neighbors = n.getNeighbors().listIterator();
            System.out.println("## "+n.getName()+" has "+n.getNeighbors().size()+" neighbors, level count: "+count);
            int levelCount = count;
            System.out.println("level count:"+levelCount);
            int neighborCount = 1;
            while (neighbors.hasNext()) {

                LinkedList<String> pChildPath = new LinkedList<String>();
                for (String s: p){
                    pChildPath.add(s);
                }

                int newCount = count;

                Node next = (Node) neighbors.next();
                System.out.println("-- visiting "+n.getName()+"'s neighbor #"+neighborCount+" "+next.getName());
                neighborCount++;
                if (!visited.contains(next.getName())) {
                    visited.add(next.getName());

//                    System.out.println("Count " + count + " neighbor: " + next.getName());
                    pChildPath.add(next.getName());
                    if (newCount <= max) {

                        System.out.println("havent visited, visit now ");
                        newCount++;
                        dfsJumps(newCount, max, next, visited, pointA, pChildPath);
                    } else {
                        //copy p to jump paths
                        LinkedList<String> jp = new LinkedList<String>();
                        count = levelCount;
                        System.out.println("Hit max count copying path and should start over "+count);
                        for (String s : p) {
                            jp.add(s);
                        }
                        jumpPaths.add(jp);
//                        p.clear();
//                        p.add(pointA);

                    }
                }
//            }
//        } else {
//            System.out.println("end of the line");
//            p.clear();
//            p.add(pointA);
//        }
        }
    }


}
