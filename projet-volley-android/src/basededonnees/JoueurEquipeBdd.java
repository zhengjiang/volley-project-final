package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.JoueurEquipe;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class JoueurEquipeBdd extends BDD {
	  public JoueurEquipeBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param ja le joueurAction à ajouter à la base
	   */
	  public void ajouter(JoueurEquipe jE) {
		  ContentValues value = new ContentValues();
		  value.put("joueurJE", jE.getJoueur().getId());
		  value.put("equipeJE", jE.getEquipe().getId());
		  value.put("maillotJE", jE.getNumMaillot());
		  value.put("coursJE", jE.isEnCours());
		  mDb.insert("JOUEUR_EQUIPE", null, value);
	  }

	  /**
	   * @param id l'identifiant du joueur à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("JOUEUR_EQUIPE", "idJE = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param jA le joueur modifié
	   */
	  public void modifier(JoueurEquipe jE) {
		  ContentValues value = new ContentValues();
		  value.put("joueurJE", jE.getJoueur().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("equipeJE", jE.getEquipe().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("maillotJE", jE.getNumMaillot());
		  value.put("coursJE", jE.isEnCours());
		  mDb.update("JOUEUR_EQUIPE", value, "idJE = ?", new String[] {String.valueOf(jE.getId())});
	  }

	  /**
	   * @param i l'identifiant du actionJoueur à récupérer
	   *//*
	  public JoueurEquipe selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM JOUEUREQUIPE WHERE idJE = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new JoueurEquipe(c.getInt(0), joueur, equipe, c.getInt(3), c.getInt(4));
	  }
	  
	  public List<JoueurEquipe> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM JOUEUREQUIPE", null);
		c.moveToFirst();
		List<JoueurEquipe> joueurs = new ArrayList<JoueurEquipe>();
		while(c.moveToNext()){
			joueurs.add(new JoueurEquipe(c.getInt(0), joueur, equipe, c.getInt(3), c.getInt(4)));
		}
		c.close();
		return joueurs;
	  }*/
	}