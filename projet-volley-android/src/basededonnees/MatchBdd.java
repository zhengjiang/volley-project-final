package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Match;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.*;

/**
 * @author Thibaut C
 *
 */
public class MatchBdd{

	private static  String TABLE = "Match";
	private static  String [] TABLEAU = {"ID int primary key","DATE VARCHAR2","LIEU VARCHAR2","EQUIPE1 EQUIPE","EQUIPE2 EQUIPE","IDCOMPETITION COMPETITION"};
	private BDD volley;
	private SQLiteDatabase bdd;
	
	public MatchBdd(Context c){ 
		this.volley = new BDD(c, TABLE, TABLEAU); 
	}
	
	public void open(){
		this.bdd = this.volley.getWritableDatabase();
	}
	
	public void close(){
		this.bdd.close();
	}
	
	public void ajouter(Match m){
		ContentValues parametres = new ContentValues();
		parametres.put("ID", m.getId());
		parametres.put("DATE", m.getDate());
		parametres.put("LIEU", m.getLieu());
		parametres.put("EQUIPE1", m.getEquipeDomicile());
		parametres.put("EQUIP2", m.getEquipeExterieur());
		parametres.put("IDCOMPETITION", m.getCompetition());
		this.bdd.insert(TABLE, null, parametres);
	}
	
	public void modifier(Match m){
		ContentValues parametres = new ContentValues();
		parametres.put("DATE", m.getDate());
		parametres.put("LIEU", m.getLieu());
		parametres.put("EQUIPE1", m.getEquipeDomicile());
		parametres.put("EQUIP2", m.getEquipeExterieur());
		parametres.put("IDCOMPETITION", m.getCompetition());
		this.bdd.update(TABLE, parametres, "ID = " + m.getId(), null);
	}
	
	public void supprimer(Match m){
		this.bdd.delete(TABLE, "ID = " + m.getId(), null);
	}
	
	public Match selectionner(int i){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","DATE","LIEU","EQUIPE1","EQUIPE2","IDCOMPETITION"}, "ID = " + i, null, null, null, null);
		c.moveToFirst();
		Match m = new Match(c.getInt(0), c.getString(1), c.getString(2), EQUIPE1, EQUIPE2, IDCOMPETITION);
		c.close();
		return m;
	}
	
	public List<Match> selectionnerTout(){
		Cursor c = this.bdd.query(TABLE, new String[]  {"ID","DATE","LIEU","EQUIPE1","EQUIPE2","IDCOMPETITION"}, null, null, null, null, null);
		c.moveToFirst();
		List<Match> matchs = new ArrayList<Match>();
		while(!c.isAfterLast()){
			Match match = new Match(c.getInt(0), c.getString(1), c.getString(2), EQUIPE1, EQUIPE2, IDCOMPETITION);
			matchs.add(match);
			c.moveToNext();
		}
		c.close();
		return matchs;
	}
	
}