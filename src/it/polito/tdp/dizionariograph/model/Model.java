package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		// System.out.println("Vertici: "+ graph.vertexSet().size() +" Archi: " +
		// graph.edgeSet().size());
	}

	public List<String> displayNeighbours(String parolaInserita) {

		System.err.println("displayNeighbours -- TODO");
		return new ArrayList<String>();
	}

	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");
		return -1;
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
}
