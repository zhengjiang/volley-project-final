package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.JoueurEquipe;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class JoueurEquipeBdd{

	private static  String TABLE = "JoueurEquipe";
	private static  String [] TABLEAU = {"ID int primary key","IDJOUEUR JOUEUR","IDEQUIPE EQUIPE","NUMMAILLOT INT","ENCOURS BOOLEAN"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public JoueurEquipeBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	public void ajouter(JoueurEquipe je){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", je.getId());
		parametres.put("IDJOUEUR", je.getJoueur());
		parametres.put("IDEQUIPE", je.getEquipe());
		parametres.put("NUMMAILLOT", je.getNumMaillot());
		parametres.put("ENCOURS", je.isEnCours());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	public void modifier(JoueurEquipe je){
		
		ContentValues parametres = new ContentValues();
		parametres.put("IDJOUEUR", je.getJoueur());
		parametres.put("IDEQUIPE", je.getEquipe());
		parametres.put("NUMMAILLOT", je.getNumMaillot());
		parametres.put("ENCOURS", je.isEnCours());
		this.bdd.update(TABLE, parametres, "ID = " + je.getId(), null);
	}
	
	public void supprimer(JoueurEquipe je){
		this.bdd.delete(TABLE, "ID = " + je.getId(), null);
	}
	
	public JoueurEquipe selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","IDJOUEUR","IDEQUIPE","NUMMAILLOT","ENCOURS"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		JoueurEquipe je = new JoueurEquipe(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4));//TODO
		c.close();
		return je;
	}
	
	public List<JoueurEquipe> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","IDJOUEUR","IDEQUIPE","NUMMAILLOT","ENCOURS"}, null, null, null, null, null);
		c.moveToFirst();
		List<JoueurEquipe> joueurEquipes = new ArrayList<JoueurEquipe>();
		while(!c.isAfterLast()){
			JoueurEquipe joueurEquipe = new JoueurEquipe(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4));//TODO
			joueurEquipes.add(joueurEquipe);
			c.moveToNext();
		}
		c.close();
		return joueurEquipes;
	}
	
}