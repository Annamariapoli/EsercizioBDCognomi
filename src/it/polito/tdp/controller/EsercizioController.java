package it.polito.tdp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.bean.Corso;
import it.polito.tdp.bean.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EsercizioController {
	
	private Model model= new Model();
	
	public void setModel(Model model){
		this.model = model;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private ComboBox<Studente> combo;          

    @FXML
    private Button btnElencoCorsi;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCerca(ActionEvent event) {
    	txtResult.clear();
    	String lettereIniziali = txtCognome.getText();
    	if(lettereIniziali==null){
    		txtResult.appendText("Scrivi delle iniziali!\n");
    		return;
    	}
    	combo.getItems().addAll(model.cerca(lettereIniziali));
    	
    }

    @FXML
    void doElencoCorsi(ActionEvent event) {                                            //ha gia selezionato lo studente 
    	Studente studente = combo.getValue();
    	List<Corso> listaCorsiDelloStudente = model.elenco(studente);
   	    txtResult.setText(listaCorsiDelloStudente.toString());
    
    }

    @FXML
    void initialize() {
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Esercizio.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Esercizio.fxml'.";
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'Esercizio.fxml'.";
        assert btnElencoCorsi != null : "fx:id=\"btnElencoCorsi\" was not injected: check your FXML file 'Esercizio.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Esercizio.fxml'.";

    }
}
