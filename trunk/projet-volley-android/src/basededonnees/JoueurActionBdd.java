package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.ActionJoueur;
import modele.Statistique;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class JoueurActionBdd extends BDD {
	  public JoueurActionBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param ja le joueurAction à ajouter à la base
	   */
	  public void ajouter(ActionJoueur jA) {
		  ContentValues value = new ContentValues();
		  value.put("setJA", jA.getSet().getId());
		  value.put("actionJA", jA.getAction().getNom());
		  value.put("joueurJA", jA.getJoueur().getId());
		  value.put("pointJA", jA.getNumPoint());
		  value.put("noteJA", jA.getNote());
		  value.put("posteJA", jA.getPoste());
		  mDb.insert("JOUEUR_ACTION", null, value);
	  }

	  /**
	   * @param id l'identifiant du joueur à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("JOUEUR_ACTION", "idJA = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param jA le joueur modifié
	   */
	  public void modifier(ActionJoueur jA) {
		  ContentValues value = new ContentValues();
		  value.put("setJA", jA.getSet().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("actionJA", jA.getAction().getNom());// A VERIFIEEEERRRR ET TESTER !
		  value.put("joueurJA", jA.getJoueur().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("pointJA", jA.getNumPoint());
		  value.put("noteJA", jA.getNote());
		  value.put("posteJA", jA.getPoste());
		  mDb.update("JOUEUR_ACTION", value, "idJA = ?", new String[] {String.valueOf(jA.getId())});
	  }

	  /**
	   * @param i l'identifiant du actionJoueur à récupérer
	   */
	  /*public ActionJoueur selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM JOUEUR_ACTION ja, SETS s, ACTIONS a, JOUEUR j, MATCHS m, COMPETITIONS c WHERE  AND idJA = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new ActionJoueur(c.getInt(0), new Set(0, 0), new Action(0, "penis"), new Joueur(0, "chat", 0, 0, 0), c.getInt(4), c.getInt(5), c.getInt(6));
		  return new ActionJoueur(null, null, null, null, null, null);
	  }*/
	  
	  public void selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM JOUEUR_ACTION", null);
		c.moveToFirst();
		List<ActionJoueur> joueurs = new ArrayList<ActionJoueur>();
		while(c.moveToNext()){
			Log.v("test", c.getString(2));
		}
		c.close();
	  }
	  
	  public Statistique getStats(int i){
		  Statistique s = new Statistique();
		  Cursor c = mDb.rawQuery("SELECT COUNT(*) as tot, AVG(noteJA) as moy, actionJA FROM JOUEUR_ACTION WHERE joueurJA = ? GROUP BY actionJA;", new String[]{String.valueOf(i)});
		  while(c.moveToNext()){
			  s.setStats(c.getString(2), c.getInt(0), c.getFloat(1));
		  }
		  c.close();
		  return s;
	  }
	}