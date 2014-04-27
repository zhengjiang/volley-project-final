package bdd;

import modele.*;

import android.app.Activity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Classe de gestion des requêtes pour Match.java
 * @author Niels Benichou
 * @version 5
 */
public class MatchBdd extends BDD {
	
	private static  String TABLE = "Match";
	private static  String [] TABLEAU = {"ID  int primary key","DATE date","LIEU String","EQUIPE1 String","EQUIPE2 String","COMPETITION String"};
	private final SQLiteDatabase VolleyBall;
	
	/**
	 * Constructeur
	 * @param context
	 */
	public MatchBdd(Context context){ 
		super(context, TABLE, TABLEAU);
		
	}
	
	/**
	 * ajout d'un match dans la BDD
	 * @param m  match à ajouter
	 */
	public void ajouter(Match m){
		
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", m.getId());    
		parametres.put("DATE", m.getDate());
		parametres.put("LIEU", m.getLieu());
		parametres.put("EQUIPE1", m.getEquipeDomicile());
		parametres.put("EQUIPE2", m.getEquipeExterieur());
		parametres.put("COMPETITION", m.getCompetition());
		
		VolleyBall.insert(TABLE, null, parametres);
		VolleyBall.close();
	
	}
	
	/**
	 * modification d'un match
	 * @param m  match à modifier
	 */
	public void modifier(Match m){
		
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", m.getId());    
		parametres.put("DATE", m.getDate());
		parametres.put("LIEU", m.getLieu());
		parametres.put("EQUIPE1", m.getEquipeDomicile());
		parametres.put("EQUIPE2", m.getEquipeExterieur());
		parametres.put("COMPETITION", m.getCompetition()); // en attente
		
		VolleyBall.update(TABLE, parametres, null, null);
		VolleyBall.close();
		
	}
	
	
	/**
	 * suppression d'un match
	 * @param m match à supprimer
	 */
	public void supprimer(Match m){
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", m.getId());    
		parametres.put("DATE", m.getDate());
		parametres.put("LIEU", m.getLieu());
		parametres.put("EQUIPE1", m.getEquipeDomicile());
		parametres.put("EQUIPE2", m.getEquipeExterieur());
		parametres.put("COMPETITION", m.getCompetition()); // en attente
		
		VolleyBall.delete(TABLE, null, TABLEAU);
		VolleyBall.close();
		
	}
	
	/**
	 * En cours, méthodes non déterminées
	 */
	public void selectionner(Match m){
		
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", m.getId());    
		parametres.put("DATE", m.getDate());
		parametres.put("LIEU", m.getLieu());
		parametres.put("EQUIPE1", m.getEquipeDomicile());
		parametres.put("EQUIPE2", m.getEquipeExterieur());
		parametres.put("COMPETITION", m.getCompetition()); // en attente
		
		
		
	}
	
	public void selectionnerTout(){
		
		super.selectionnerTout(TABLE);
		
	}
	

}
