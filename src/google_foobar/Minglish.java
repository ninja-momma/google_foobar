package google_foobar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Minglish<V> {

	
	public static void main(String[] args){
		String[] words = {"ba","ab","cb"};
		String alphabet = Minglish.answer(words);
		System.out.println(alphabet);
	}
	
	public static String answer(String[] words){
		Minglish<Character> graph = new Minglish<Character>();
		buildGraph(graph,words);
		List<Character> alphabet = graph.topologicallySort();
		StringBuilder sb = new StringBuilder();
		for (Character c : alphabet){
			sb.append(c);
		}
		return sb.toString();
	}
	
	private static void buildGraph(Minglish<Character> graph, String[] words){
		String previous = words[0];
		insertVertices(graph, previous);
		
		for (int i=1; i< words.length;++i){
			insertVertices(graph,words[i]);
			createEdges(graph, previous, words[i]);
			previous = words[i];
		}
	}
	
	private static void insertVertices(Minglish<Character> graph, String word){
		for (int i=0; i<word.length();i++){
			graph.addVertex(word.charAt(i));
		}
	}
	
	private static void createEdges(Minglish<Character> graph, String previous, String current){
		int i=0;
		int j=0;
		
		//find the next mismatched character
		while( (i<previous.length() && j<current.length()) && (previous.charAt(i)==current.charAt(j))){
			i++;
			j++;
		}
		if (i<previous.length() && j<current.length()){
			graph.addEdge(current.charAt(j), previous.charAt(i));
		}
	}
	
		private Map<V, List<V>> edgeMap = new HashMap<V,List<V>>();
		
		public void addVertex(V v){
			if (!edgeMap.containsKey(v)){
				edgeMap.put(v,  new LinkedList<V>());
			}
		}
		
		public void addEdge(V from, V to){
			List<V> links = edgeMap.get(from);
			links.add(to);
		}
		
		public List<V> topologicallySort(){
			List<V> orderedVertices = new LinkedList<V>();
			Set<V> discovered = new HashSet<V>();
			Set<V> processed = new HashSet<V>();
			Stack<V> stack = new Stack<V>();
			
			for (V startNode: edgeMap.keySet()){
				if(discovered.contains(startNode)){
					continue;
				}
				stack.push(startNode);
				while(!stack.isEmpty()){
					V top = stack.peek();
					if (!discovered.contains(top)){
						discovered.add(top);
						for (V predecsessor: edgeMap.get(top)){
							stack.push(predecsessor);
						}
					} else {
						stack.pop();
						if (!processed.contains(top)){
							processed.add(top);
							orderedVertices.add(top);
						}
					}
				}
			}
			return orderedVertices;
		}
	

}
