package it.polito.tdp.controller;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.DB.StudenteDAO;
import it.polito.tdp.bean.Corso;
import it.polito.tdp.bean.Studente;

public class Model {                  //deve sempre e solo richiamare metodi del dao?
	
	StudenteDAO  stuDao = new StudenteDAO();
	
	public List<Studente> cerca(String iniziali){
		return stuDao.cercaStudenti(iniziali);
	}

	
	public List<Corso> elenco(Studente studente){
		return stuDao.listaCorsiDelloStudente(studente);
	}
}
