package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Match;
import modele.Set;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class SetBdd extends BDD {
	  public SetBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param s le set à ajouter à la base
	   */
	  public void ajouter(Set s) {
		  ContentValues value = new ContentValues();
		  value.put("numS", s.getNumSet());
		  value.put("scoreEquipe1S", s.getScoreEquipeDomicile());
		  value.put("scoreEquipe2S", s.getScoreEquipeExterieur());
		  value.put("matchS", s.getMatch().getId());// A VERIFIEEEERRRR ET TESTER !
		  mDb.insert("SETS", null, value);
	  }

	  /**
	   * @param id l'identifiant du set à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("SETS", "idS = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param s le set modifié
	   */
	  public void modifier(Set s) {
		  ContentValues value = new ContentValues();
		  value.put("numS", s.getNumSet());
		  value.put("scoreEquipe1S", s.getScoreEquipeDomicile());
		  value.put("scoreEquipe2S", s.getScoreEquipeExterieur());
		  value.put("matchS", s.getMatch().getId());// A VERIFIEEEERRRR ET TESTER !
		  mDb.update("SETS", value, "idS = ?", new String[] {String.valueOf(s.getId())});
	  }

	  /**
	   * @param i l'identifiant du set à récupérer
	   */
	  public Set selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM SETS WHERE idS = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new Set(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), leMatch);
	  }
	  
	  public List<Set> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM SETS", null);
		c.moveToFirst();
		List<Set> sets = new ArrayList<Set>();
		while(c.moveToNext()){
			sets.add(new Set(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), leMatch));
		}
		c.close();
		return sets;
	  }
	}