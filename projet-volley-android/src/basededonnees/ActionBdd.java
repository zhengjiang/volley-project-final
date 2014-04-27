package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Action;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class ActionBdd{

	private static  String TABLE = "Action";
	private static  String [] TABLEAU = {"ID int primary key","NOM VARCHAR2"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public ActionBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	public void ajouter(Action a){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", a.getId());
		parametres.put("NOM", a.getNom());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	public void modifier(Action a){
		ContentValues parametres = new ContentValues();
		parametres.put("NOM", a.getNom());
		this.bdd.update(TABLE, parametres, "ID = " + a.getId(), null);
	}
	
	public void supprimer(Action a){
		this.bdd.delete(TABLE, "ID = " + a.getId(), null);
	}
	
	public Action selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NOM"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		Action a = new Action(c.getInt(0), c.getString(1));
		c.close();
		return a;
	}
	
	public List<Action> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NOM"}, null, null, null, null, null);
		c.moveToFirst();
		List<Action> actions = new ArrayList<Action>();
		while(!c.isAfterLast()){
			Action action = new Action(c.getInt(0), c.getString(1));
			actions.add(action);
			c.moveToNext();
		}
		c.close();
		return actions;
	}
	
}