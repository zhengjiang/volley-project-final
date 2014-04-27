package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Joueur;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class JoueurBdd{

	private static  String TABLE = "Joueur";
	private static  String [] TABLEAU = {"ID int primary key","NOM VARCHAR2","TAILLE INT","AGE INT","POSTE INT"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public JoueurBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	/**
	 * @param Joueur j
	 */
	public void ajouter(Joueur j){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", j.getId());
		parametres.put("NOM", j.getNom());
		parametres.put("TAILLE", j.getTaille());
		parametres.put("AGE", j.getAge());
		parametres.put("POSTE", j.getPoste());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	/**
	 * @param Joueur j
	 */
	public void modifier(Joueur j){
		
		ContentValues parametres = new ContentValues();
		parametres.put("NOM", j.getNom());
		parametres.put("TAILLE", j.getTaille());
		parametres.put("AGE", j.getAge());
		parametres.put("POSTE", j.getPoste());
		this.bdd.update(TABLE, parametres, "ID = " + j.getId(), null);
	}
	
	/**
	 * @param Joueur j
	 */
	public void supprimer(Joueur j){
		this.bdd.delete(TABLE, "ID = " + j.getId(), null);
	}
	
	/**
	 * @param joueur id
	 */
	public Joueur selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NOM","TAILLE","AGE","POSTE"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		Joueur j = new Joueur(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4));
		c.close();
		return j;
	}
	
	public List<Joueur> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NOM","TAILLE","AGE","POSTE"}, null, null, null, null, null);
		c.moveToFirst();
		List<Joueur> joueurs = new ArrayList<Joueur>();
		while(!c.isAfterLast()){
			Joueur joueur = new Joueur(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4));
			joueurs.add(joueur);
			c.moveToNext();
		}
		c.close();
		return joueurs;
	}
	
}