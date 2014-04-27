package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.ActionJoueur;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class JoueurActionBdd{

	private static  String TABLE = "JoueurAction";
	private static  String [] TABLEAU = {"ID int primary key","IDMATCH MATCH","IDACTION ACTION","IDJOUEUR JOUEUR","NUMPOINT INT","NOTE INT"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public JoueurActionBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	public void ajouter(ActionJoueur aj){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", aj.getId());
		parametres.put("IDMATCH", aj.getMatch());
		parametres.put("IDACTION", aj.getAction());
		parametres.put("IDJOUEUR", aj.getJoueur());
		parametres.put("NUMPOINT", aj.getNumPoint());
		parametres.put("NOTE", aj.getNote());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	public void modifier(ActionJoueur aj){
		
		ContentValues parametres = new ContentValues();
		parametres.put("IDMATCH", aj.getMatch());
		parametres.put("IDACTION", aj.getAction());
		parametres.put("IDJOUEUR", aj.getJoueur());
		parametres.put("NUMPOINT", aj.getNumPoint());
		parametres.put("NOTE", aj.getNote());
		this.bdd.update(TABLE, parametres, "ID = " + aj.getId(), null);
	}
	
	public void supprimer(ActionJoueur aj){
		this.bdd.delete(TABLE, "ID = " + aj.getId(), null);
	}
	
	public ActionJoueur selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","IDMATCH","IDACTION","IDJOUEUR","NUMPOINT","NOTE"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		ActionJoueur aj = new ActionJoueur(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5));//TODO
		c.close();
		return aj;
	}
	
	public List<ActionJoueur> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","IDMATCH","IDACTION","IDJOUEUR","NUMPOINT","NOTE"}, null, null, null, null, null);
		c.moveToFirst();
		List<ActionJoueur> actionJoueurs = new ArrayList<ActionJoueur>();
		while(!c.isAfterLast()){
			ActionJoueur actionJoueur = new ActionJoueur(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5));//TODO
			actionJoueurs.add(actionJoueur);
			c.moveToNext();
		}
		c.close();
		return actionJoueurs;
	}
	
}