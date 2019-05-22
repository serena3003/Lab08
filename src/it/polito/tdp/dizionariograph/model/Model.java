package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {

	private WordDAO dao;
	private Graph<String, DefaultEdge> graph;

	public Model() {
		dao = new WordDAO();
	}

	public void createGraph(int numeroLettere) {

		System.out.println("createGraph " + numeroLettere);

		List<String> words = new ArrayList<String>();
		words = dao.getAllWordsFixedLength(numeroLettere);

		graph = new SimpleGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(graph, words);

		for (String w1 : words) {
			for (String w2 : words) {
				if (!graph.containsEdge(w1, w2)) {
					if (confrontaParole(w1, w2) == 1) {
						graph.addEdge(w1, w2);
					}
				}
			}
		}
		System.out.println("Vertici: "+ graph.vertexSet().size() +" Archi: " + graph.edgeSet().size());
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> result = new ArrayList<String>();
		
		if(!graph.containsVertex(parolaInserita)) {
			return null;
		} else {
			for(DefaultEdge e : graph.edgesOf(parolaInserita)) {
				String s =  graph.getEdgeSource(e);
				  if(s.equals(parolaInserita)){
			            s = graph.getEdgeTarget(e);
			        }
				result.add(s);
				}
		}
		return result ;
	}

	public int findMaxDegree() {
		int grado = 0;
		for(String vert : graph.vertexSet()) {
			List<String> vicini = displayNeighbours(vert);
			if(vicini.size()>= grado) {
				grado = vicini.size();
			}
		}
		return grado;
	}

	public int confrontaParole(String w1, String w2) {
		int count = 0;
		for (int i = 0; i < w1.length(); i++) {
			if (w1.charAt(i) != w2.charAt(i)) {
				count = count + 1;
			}
		}
		return count;
	}
	
	public String findMaxVertex() {
		String res = null;
		int grado = 0;
		for(String vert : graph.vertexSet()) {
			List<String> vicini = displayNeighbours(vert);
			if(vicini.size()>= grado) {
				grado = vicini.size();
				res = vert;
			}
		}
		return res;
	}

	public void reset() {
		graph = new SimpleGraph<>(DefaultEdge.class);
	}
}
