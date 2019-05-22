package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtWord;

    @FXML
    private Button btnGraph;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGrado;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGradoMax(ActionEvent event) {
    	txtResult.appendText("Grado massimo del grafo: " + model.findMaxDegree() + "\n");
    	String ver = model.findMaxVertex();
    	txtResult.appendText("Vertice del grado massimo del grafo: " + ver + "\n");
    	txtResult.appendText("Vicini del vertice di grado massimo: " + model.displayNeighbours(ver));
    }

    @FXML
    void doGraph(ActionEvent event) {
    	model.createGraph(Integer.parseInt(txtNumber.getText()));
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtWord.clear();
    	txtNumber.clear();
    	model.reset();
    }

    @FXML
    void searchVicini(ActionEvent event) {
    	List<String> vicini = model.displayNeighbours(txtWord.getText());
    	txtResult.appendText("Vicini di " + txtWord.getText()+": " + vicini.toString());
    }

    @FXML
    void initialize() {
        assert txtNumber != null : "fx:id=\"txtNumber\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGraph != null : "fx:id=\"btnGraph\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrado != null : "fx:id=\"btnGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
