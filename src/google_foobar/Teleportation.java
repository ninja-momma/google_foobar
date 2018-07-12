package google_foobar;

import java.util.*;

public class Teleportation {


    class Graph {
        private int numberOfVertices;
        private LinkedList<Integer> edges[];

        Graph(int numberOfVertices){
            this.numberOfVertices = numberOfVertices;
            edges = new LinkedList[numberOfVertices];
            for (int i=0; i<numberOfVertices; i++){
                edges[i] = new LinkedList();
            }
        }

        void addEdge(int v, int w){
            edges[v].add(w);
        }

        public void doesPathExistBetweenAandB(int a, int b){
            boolean visited[] = new boolean[this.numberOfVertices];

            LinkedList<Integer> queue = new LinkedList<Integer>();
            visited[a] = true;
            queue.add(a);


            while(queue.size()!=0){
                // remove from queue
                a = queue.poll();
                System.out.print(a+" ");


                Iterator<Integer> i = edges[a].listIterator();
                while(i.hasNext()){
                    int n = i.next();
                    if (!visited[n]){
                        visited[n] = true;
                        queue.add(n);
                    }

                }
            }

        }

    }

    Teleportation() {

    }

    public void doesPathExist(){
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

//        g.BFS(2);
        g.doesPathExistBetweenAandB(2, 1);
    }

    public void doesFlightExists(){
       NodeGraph g = createNodeGraph();
        System.out.println("");
        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex Washington)");
        g.doesPathExistBetweenAandB("Washington","New York");
    }

    public void maxJumps(){
        NodeGraph g = createNodeGraph();
        System.out.println("");
        System.out.println("Jumps "+
                "(starting from vertex Seattle)");
        g.findPathsWithNumberOfJumps(2, "Seattle");
    }

    private NodeGraph createNodeGraph(){
        NodeGraph g = new NodeGraph(9);
        g.addEdge("Washington", "Baltimore");
        g.addEdge("Washington", "Atlanta");
        g.addEdge("Baltimore", "Philadelphia");
        g.addEdge("Philadelphia", "New York");
        g.addEdge("Los Angeles", "San Fransisco");
        g.addEdge("San Fransisco", "Oakland");
        g.addEdge("Los Angeles", "Oakland");
        g.addEdge("Seattle", "New York");
        g.addEdge("Seattle", "Baltimore");

//        Iterator<String> places =   g.edges.keySet().iterator();
//        while(places.hasNext()){
//            String str = places.next();
//            System.out.println("============ "+str+" ========");
//            Node n = g.edges.get(str);
//            System.out.println("Name "+n.getName());
//            LinkedList<Node> neighbors = n.getNeighbors();
//            for(Node node : neighbors){
//                System.out.println(node.getName());
//            }
//        }


        return g;
    }

    // Driver method to
    public static void main(String args[]) {
        Teleportation teleportation = new Teleportation();
//        teleportation.doesPathExist();
        teleportation.doesFlightExists();
        teleportation.maxJumps();
    }
}
