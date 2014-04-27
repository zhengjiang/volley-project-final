package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Equipe;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class EquipeBdd{

	private static  String TABLE = "Equipe";
	private static  String [] TABLEAU = {"ID int primary key","NOM VARCHAR2","ENTRAINEUR VARCHAR2"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public EquipeBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	public void ajouter(Equipe e){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", e.getId());
		parametres.put("NOM", e.getNom());
		parametres.put("ENTRAINEUR", e.getEntraineur());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	public void modifier(Equipe e){
		
		ContentValues parametres = new ContentValues();
		parametres.put("NOM", e.getNom());
		parametres.put("ENTRAINEUR", e.getEntraineur());
		this.bdd.update(TABLE, parametres, "ID = " + e.getId(), null);
	}
	
	public void supprimer(Equipe e){
		this.bdd.delete(TABLE, "ID = " + e.getId(), null);
	}
	
	public Equipe selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NOM","ENTRAINEUR"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		Equipe e = new Equipe(c.getInt(0), c.getString(1), c.getString(2));
		c.close();
		return e;
	}
	
	public List<Equipe> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","NOM","ENTRAINEUR"}, null, null, null, null, null);
		c.moveToFirst();
		List<Equipe> equipes = new ArrayList<Equipe>();
		while(!c.isAfterLast()){
			Equipe equipe = new Equipe(c.getInt(0), c.getString(1), c.getString(2));
			equipes.add(equipe);
			c.moveToNext();
		}
		c.close();
		return equipes;
	}
	
}