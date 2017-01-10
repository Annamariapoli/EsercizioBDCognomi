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
		String query= "SELECT * FROM studente WHERE cognome like ?";
		List<Studente> listaStudentiTrovati = new LinkedList<Studente>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,  iniziali+"%");
		    ResultSet rs = st.executeQuery();
			while(rs.next()){
				Studente stu = new Studente(rs.getInt("matricola"), rs.getString("cognome"),rs.getString("nome"), rs.getString("cds"));
			    listaStudentiTrovati.add(stu);
			}
			conn.close();
			//System.out.println(listaStudentiTrovati);
			return listaStudentiTrovati;
					
	} catch (SQLException e ){
			e.printStackTrace();
			throw new RuntimeException("Errore nel db", e);
		}
	}
	
	//utente seleziona uno studente, ritorno la lista di corsi ai quli è iscritto
	
	public List<Corso> listaCorsiDelloStudente(Studente studente){
		Connection conn = DBConn.getConnection();
		String query = "select c.codins, c.crediti, c.nome, c.pd  "
				+ "from iscrizione i, corso c  "
				+ "where i.matricola=? and i.codins=c.codins";
	    List<Corso> corsiDelloStudente = new LinkedList<Corso>();
	    try{
	    	PreparedStatement st = conn.prepareStatement(query);
	    	st.setInt(1,  studente.getMatricola());
	    	ResultSet rs = st.executeQuery();
	    		while(rs.next()){
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
