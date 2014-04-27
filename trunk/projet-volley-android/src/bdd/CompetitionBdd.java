package bdd;

import modele.*;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

/**
 * Classe de gestion des requêtes pour Competition.java
 * @author Niels Benichou
 * @version 5
 */
public class CompetitionBdd extends BDD {
	
	private static  String TABLE = "Competition";
	private static  String [] TABLEAU = {"ID  int primary key","ANNEE  int","NOM String","TYPE String"};
	private final SQLiteDatabase VolleyBall;
	
	/**
	 * Constructeur
	 * @param context
	 */
	public CompetitionBdd(Context context){ 
		super(context, TABLE, TABLEAU);
	}
	
/************************************  METHODES   ************************************/
	public void ajouter(Competition c){
		
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", c.getId());
		parametres.put("ANNEE", c.getAnnee());
		parametres.put("NOM", c.getNom());
		parametres.put("TYPE", c.getType());
		
		VolleyBall.insert(TABLE, null, parametres);	
		VolleyBall.close();
	
	}
	
	public void modifier(Competition c){
		
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", c.getId());
		parametres.put("ANNEE", c.getAnnee());
		parametres.put("NOM", c.getNom());
		parametres.put("TYPE", c.getType());;
		
		VolleyBall.update(TABLE, parametres, null, null);
		VolleyBall.close();
		
	}
	
	public void supprimer(Competition c){
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", c.getId());
		parametres.put("ANNEE", c.getAnnee());
		parametres.put("NOM", c.getNom());
		parametres.put("TYPE", c.getType());
		
		VolleyBall.delete(TABLE, null, TABLEAU);
		VolleyBall.close();
		
	}
	
	public void selectionner(Competition c){
		
		ContentValues parametres = new ContentValues();
		
		parametres.put("ID", c.getId());
		parametres.put("ANNEE", c.getAnnee());
		parametres.put("NOM", c.getNom());
		parametres.put("TYPE", c.getType());
		
		super.selectionner(parametres);
		
	}
	
	public void selectionnerTout(){
		
		super.selectionnerTout(TABLE);
		
	}
	

}
