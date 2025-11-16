
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//-------------establish all classes and their pre reqs in node objects
public class TopSort{

    public void createClasses(List<Node> course) {
        //SoftwareDevelopment
        course.add(new Node("SENG 101"));
        course.add(new Node("SENG 102", "SENG 101"));
        course.add(new Node("SENG 300", "SENG 102"));
        course.add(new Node("SENG 301", "SENG 300"));
        course.add(new Node("SENG 315", "SENG 300"));
        course.add(new Node("SENG 400", "SENG 350"));
        course.add(new Node("SENG 430", "SENG 350"));
        //foundation
        course.add(new Node("SENG 160"));
        course.add(new Node("SENG 170", "SENG 160"));
        course.add(new Node("SENG 210", "SENG 160"));
        course.add(new Node("SENG 250", "SENG 102"));
        course.add(new Node("SENG 302", "SENG 160"));
        course.add(new Node("SENG 350", "SENG 160"));
        course.add(new Node("SENG 355", "SENG 160"));
        //engineeringFundamentals
        course.add(new Node("SENG 225", "SENG 102"));
        course.add(new Node("ECON 201", "MATH 109"));
        course.add(new Node("STQM 260", "MATH 114"));
        //mathematics
        course.add(new Node("MATH 220", "MATH 126"));
        course.add(new Node("MATH 230", "MATH 220"));
        course.add(new Node("MATH 322", "MATH 230"));
        course.add(new Node("MATH 328", "MATH 216"));
    }
//---------------------------------------------------------------------------------


// ---------------node----------------------
public class Node{
    String node;
    String preReq1;
    String preReq2;
    String preReq3;
    public Node(String Head){
        this.node = Head;
    }

    public Node(String Head, String preReq1){
        this.node = Head;
        this.preReq1 = preReq1;
    }
        public Node(String Head, String preReq1, String preReq2){
        this.node = Head;
        this.preReq1 = preReq1;
        this.preReq2 = preReq2;

    }
        public Node(String Head, String preReq1, String preReq2, String preReq3){
        this.node = Head;
        this.preReq1 = preReq1;
        this.preReq2 = preReq2;
        this.preReq3 = preReq3;
    }
}
//--------------------------------------------
// ---------------graph----------------------
public Map<Node, Integer> Graph (List<Node> course){
    Map<Node, Integer> indegree = new HashMap<>();

        for (Node n : course) {

            indegree.put(n, 0);

            if (n.preReq1 != null) {
                indegree.put(n, indegree.get(n) + 1);
            }
            if (n.preReq2 != null) {
                indegree.put(n, indegree.get(n) + 1);
            }
            if (n.preReq3 != null) {
                indegree.put(n, indegree.get(n) + 1);
            
        }
    } 
    return indegree;
}
//--------------------------------------------
//----------------topological sort ---------
public void topological(Map<Node, Integer> indegree, List<Node> course) {
    // create adjacency list
    Map<String, List<String>> adj = new HashMap<>();
    for (Node n : course) {
        adj.put(n.node, new ArrayList<>());
}
    
        for (Node n : course) {
            if (n.preReq1 != null && adj.containsKey(n.preReq1)) 
                adj.get(n.preReq1).add(n.node);

        if (n.preReq2 != null && adj.containsKey(n.preReq2)) 
            adj.get(n.preReq2).add(n.node);

        if (n.preReq3 != null && adj.containsKey(n.preReq3)) 
            adj.get(n.preReq3).add(n.node);
 }

   
    List<Node> zero = new ArrayList<>();
    for (Node n : course) {
        if (indegree.get(n) == 0) zero.add(n);
    }

    List<String> sorted = new ArrayList<>();

    while (!zero.isEmpty()) {
        Node current = zero.remove(0); 
        sorted.add(current.node);

        // go through neighbors
        for (String neighborName : adj.get(current.node)) {

            Node neighbor = null;
            for (Node n : course) {
                if (n.node.equals(neighborName)) {
                    neighbor = n;
                    break;
                }
            }
            if (neighbor != null) {
                indegree.put(neighbor, indegree.get(neighbor) - 1); 
                if (indegree.get(neighbor) == 0) {
                    zero.add(neighbor); 
                }
            }
        }
    }

    // print  topological sort
    System.out.println("Topological sort result of courses:");
    for (String courseName : sorted) {
        System.out.println(courseName);
    }
}

public static void main(String[] args) {
      List<Node> course = new ArrayList<>();
      Map<Node, Integer> indegree = new HashMap<>();
      TopSort sort = new TopSort();
      sort.createClasses(course); //creates classes
      indegree = sort.Graph(course); // makes a graph with each node having it's indegrees
      sort.topological(indegree, course); //runs algorithm and prints out courses
}




//the graph should: Store edges, find in-degrees
//store original data in a list
}

