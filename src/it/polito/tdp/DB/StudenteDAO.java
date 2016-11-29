package it.polito.tdp.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.bean.Corso;
import it.polito.tdp.bean.Studente;

public class StudenteDAO {
	
	//utente inserisce iniziali, ritorno la lista di studenti trovati 
	
	public List<Studente> cercaStudenti(String iniziali){
		Connection conn = DBConn.getConnection();
		String query= "select * form studente where cognome like ?";
		List<Studente> listaStudentiTrovati = new LinkedList<Studente>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			st.setString(1,  iniziali + "%");
			while(rs.next()){
				Studente stu = new Studente(rs.getInt("matricola"), rs.getString("cognome"),rs.getString("nome"), rs.getString("cds"));
			    listaStudentiTrovati.add(stu);
			}
			conn.close();
			return listaStudentiTrovati;
					
		} catch (SQLException e ){
			e.printStackTrace();
			throw new RuntimeException("Errore nel db", e);
		}
	}
	
	//utente seleziona uno studente, ritorno la lista di corsi ai quli è iscritto
	
	public List<Corso> listaCorsiDelloStudente(Studente studente){
		Connection conn = DBConn.getConnection();
		String query = " select corso.codins, crediti,nome, pd from iscrizione, corso where Iscrizione.codins= corso.codins and iscrizione.matricola=?";
	    List<Corso> corsiDelloStudente = new LinkedList<Corso>();
	    try{
	    	PreparedStatement st = conn.prepareStatement(query);
	    	ResultSet rs = st.executeQuery();
	    	st.setInt(1, studente.getMatricola());
	    		if(rs.next()){
	    			Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
	    			corsiDelloStudente.add(c);
	    		}
	    	conn.close();
	    	return corsiDelloStudente;
	    } catch(SQLException e ){
	    	e.printStackTrace();
	    	throw new RuntimeException ("Errore nel DB", e);
	    }
	
	}
	
}
