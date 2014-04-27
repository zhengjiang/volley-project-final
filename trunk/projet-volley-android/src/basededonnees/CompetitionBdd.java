package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Competition;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class CompetitionBdd{

	private static  String TABLE = "Competition";
	private static  String [] TABLEAU = {"ID int primary key","ANNEE INT","NOM VARCHAR2","TYPE VARCHAR2"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public CompetitionBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	public void ajouter(Competition c){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", c.getId());
		parametres.put("ANNEE", c.getAnnee());
		parametres.put("NOM", c.getNom());
		parametres.put("TYPE", c.getType());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	public void modifier(Competition c){
		ContentValues parametres = new ContentValues();
		parametres.put("ANNEE", c.getAnnee());
		parametres.put("NOM", c.getNom());
		parametres.put("TYPE", c.getType());
		this.bdd.update(TABLE, parametres, "ID = " + c.getId(), null);
	}
	
	public void supprimer(Competition c){
		this.bdd.delete(TABLE, "ID = " + c.getId(), null);
	}
	
	public Competition selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","ANNEE","NOM","TYPE"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		Competition cp = new Competition(c.getInt(0), c.getInt(1),c.getString(2),c.getString(3));
		c.close();
		return cp;
	}
	
	public List<Competition> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","ANNEE","NOM","TYPE"}, null, null, null, null, null);
		c.moveToFirst();
		List<Competition> competitions = new ArrayList<Competition>();
		while(!c.isAfterLast()){
			Competition competition = new Competition(c.getInt(0), c.getInt(1),c.getString(2),c.getString(3));
			competitions.add(competition);
			c.moveToNext();
		}
		c.close();
		return competitions;
	}
	
}