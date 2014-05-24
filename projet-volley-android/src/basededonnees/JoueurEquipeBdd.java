package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Equipe;
import modele.Joueur;
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
	  public long ajouter(JoueurEquipe jE) {
		  ContentValues value = new ContentValues();
		  value.put("joueurJE", jE.getJoueur().getId());
		  value.put("equipeJE", jE.getEquipe().getId());
		  value.put("maillotJE", jE.getNumMaillot());
		  value.put("coursJE", jE.isEnCours());
		  return mDb.insert("JOUEUR_EQUIPE", null, value);
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
	  }*/
	  
	  public List<JoueurEquipe> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT idJE, maillotJE, coursJE, idJ, nomJ, tailleJ, ageJ, idE, nomE, entraineurE FROM JOUEUR_EQUIPE je, EQUIPES e, JOUEURS j WHERE je.joueurJE = j.idJ AND je.equipeJE = e.idE", null);
		List<JoueurEquipe> joueurs = new ArrayList<JoueurEquipe>();
		while(c.moveToNext()){
			joueurs.add(new JoueurEquipe(c.getInt(0), new Joueur(c.getInt(3), c.getString(4), c.getInt(5), c.getInt(6), 0), new Equipe(c.getInt(7), c.getString(8), c.getString(9)), c.getInt(1), c.getInt(2) == 1));
		}
		c.close();
		return joueurs;
	  }
	  
	  public List<JoueurEquipe> selectionnerJoueursEquipes(int i){
			Cursor c = mDb.rawQuery("SELECT idJE, maillotJE, coursJE, idJ, nomJ, tailleJ, ageJ, posteJ, idE, nomE, entraineurE FROM JOUEUR_EQUIPE je, EQUIPES e, JOUEURS j WHERE je.joueurJE = j.idJ AND je.equipeJE = e.idE AND e.idE = ?", new String[]{String.valueOf(i)});
			List<JoueurEquipe> joueurs = new ArrayList<JoueurEquipe>();
			while(c.moveToNext()){
				joueurs.add(new JoueurEquipe(c.getInt(0), new Joueur(c.getInt(3), c.getString(4), c.getInt(5), c.getInt(6), c.getInt(7)), new Equipe(c.getInt(8), c.getString(9), c.getString(10)), c.getInt(1), c.getInt(2) == 1));
			}
			c.close();
			return joueurs;
		  }
	  
	  public List<JoueurEquipe> selectionnerJoueursSansEquipe(int i){
			Cursor c = mDb.rawQuery("SELECT idJE, maillotJE, coursJE, idJ, nomJ, tailleJ, ageJ, posteJ FROM JOUEUR_EQUIPE je, JOUEURS j WHERE je.joueurJE = j.idJ AND je.equipeJE = ?", new String[]{String.valueOf(i)});
			List<JoueurEquipe> joueurs = new ArrayList<JoueurEquipe>();
			while(c.moveToNext()){
				joueurs.add(new JoueurEquipe(c.getInt(0), new Joueur(c.getInt(3), c.getString(4), c.getInt(5), c.getInt(6), c.getInt(7)), new Equipe(-1, "Sans club", ""), c.getInt(1), c.getInt(2) == 1));
			}
			c.close();
			return joueurs;
		  }
	  
	  
	}