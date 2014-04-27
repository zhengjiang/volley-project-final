package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Set;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class SetBdd{

	private static  String TABLE = "Set";
	private static  String [] TABLEAU = {"ID int primary key","NUM int","SCORE1 int","SCORE2 int","IDMATCH int"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public SetBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	

	public void ajouter(Set s){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", s.getId());
		parametres.put("NUM", s.getNumSet());
		parametres.put("SCORE1", s.getScoreEquipeDomicile());
		parametres.put("SCORE2", s.getScoreEquipeExterieur());
		parametres.put("IDMATCH", s.getMatch());
		this.bdd.insert(TABLE, null, parametres);
	}
	

	public void modifier(Set s){
		
		ContentValues parametres = new ContentValues();
		parametres.put("NUM", s.getNumSet());
		parametres.put("SCORE1", s.getScoreEquipeDomicile());
		parametres.put("SCORE2", s.getScoreEquipeExterieur());
		parametres.put("IDMATCH", s.getMatch());
		this.bdd.update(TABLE, parametres, "ID = " + s.getId(), null);
	}
	

	public void supprimer(Set s){
		this.bdd.delete(TABLE, "ID = " + s.getId(), null);
	}
	
	public Set selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NUM","SCORE1","SCORE2","IDMATCH"}, "ID = " + i, null, null, null, null, null);
		c.moveToFirst();
		Set s = new Set(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4));
		c.close();
		return s;
	}
	

	public List<Set> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NUM","SCORE1","SCORE2","IDMATCH"}, null, null, null, null, null, null);
		c.moveToFirst();
		List<Set> sets = new ArrayList<Set>();
		while(!c.isAfterLast()){
			Set set = new Set(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4));
			sets.add(set);
			c.moveToNext();
		}
		c.close();
		return sets;
	}
	
}