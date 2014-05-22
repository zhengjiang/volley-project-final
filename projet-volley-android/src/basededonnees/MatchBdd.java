package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Match;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class MatchBdd extends BDD {
	  public MatchBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param m le match à ajouter à la base
	   */
	  public void ajouter(Match m) {
		  ContentValues value = new ContentValues();
		  value.put("dateM", m.getDate());
		  value.put("lieuM", m.getLieu());
		  value.put("equipe1", m.getEquipeDomicile().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("equipe2", m.getEquipeExterieur().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("competition", m.getCompetition().getId());// A VERIFIEEEERRRR ET TESTER !
		  mDb.insert("MATCHS", null, value);
	  }

	  /**
	   * @param id l'identifiant du match à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("JOUEURS", "idJ = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param m le match modifié
	   */
	  public void modifier(Match m) {
		  ContentValues value = new ContentValues();
		  value.put("dateM", m.getDate());
		  value.put("lieuM", m.getLieu());
		  value.put("equipe1", m.getEquipeDomicile().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("equipe2", m.getEquipeExterieur().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("competition", m.getCompetition().getId());// A VERIFIEEEERRRR ET TESTER !
		  mDb.update("MATCHS", value, "idM = ?", new String[] {String.valueOf(m.getId())});
	  }

	  /**
	   * @param i l'identifiant du match à récupérer
	   */
	  /*
	  public Match selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM MATCHS WHERE idJ = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new Match(c.getInt(0), c.getString(1), c.getInt(2), equipe1, equipe2, competition);
	  }
	  
	  public List<Match> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM MATCHS", null);
		c.moveToFirst();
		List<Match> matchs = new ArrayList<Match>();
		while(c.moveToNext()){
			matchs.add(new Match(c.getInt(0), c.getString(1), c.getInt(2), equipe1, equipe2, competition));
		}
		c.close();
		return matchs;
	  }*/
	}