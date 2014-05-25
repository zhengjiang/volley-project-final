package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Competition;
import modele.Equipe;
import modele.Match;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
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
	  public long ajouter(Match m) {
		  ContentValues value = new ContentValues();
		  value.put("dateM", m.getDate());
		  value.put("lieuM", m.getLieu());
		  value.put("equipe1", m.getEquipeDomicile().getId());
		  value.put("equipe2", m.getEquipeExterieur().getId());
		  value.put("competition", m.getCompetition().getId());
		  long i = mDb.insert("MATCHS", null, value);
		  return i;
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
	  
	  public Match selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT idM, dateM, lieuM, e1.idE, e1.nomE, e1.entraineurE, e2.idE, e2.nomE, e2.entraineurE FROM MATCHS m, EQUIPES e1, EQUIPES e2 WHERE m.equipe1 = e1.idE AND m.equipe2 = e2.idE AND idM = ?", new String[]{String.valueOf(i)});
		  if(c.getCount() == 0){
			  return null;
		  }
		  c.moveToFirst();
		  return new Match(c.getInt(0), c.getString(1), c.getString(2), new Equipe(c.getInt(3), c.getString(4), c.getString(5)), new Equipe(c.getInt(6), c.getString(7), c.getString(8)), new Competition(0, 0, "", ""));
	  }
	  
	  public List<Match> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT idM, dateM, lieuM, e1.idE, e1.nomE, e1.entraineurE, e2.idE, e2.nomE, e2.entraineurE FROM MATCHS m, EQUIPES e1, EQUIPES e2 WHERE m.equipe1 = e1.idE AND m.equipe2 = e2.idE", null);
		List<Match> matchs = new ArrayList<Match>();
		while(c.moveToNext()){
			matchs.add(new Match(c.getInt(0), c.getString(1), c.getString(2), new Equipe(c.getInt(3), c.getString(4), c.getString(5)), new Equipe(c.getInt(6), c.getString(7), c.getString(8)), new Competition(0, 0, "", "")));
		}
		c.close();
		return matchs;
	  }
	}