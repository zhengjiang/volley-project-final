package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Joueur;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class JoueurBdd extends BDD {
	  public JoueurBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param j le joueur à ajouter à la base
	   */
	  public void ajouter(Joueur j) {
		  ContentValues value = new ContentValues();
		  value.put("nomJ", j.getNom());
		  value.put("tailleJ", j.getTaille());
		  value.put("ageJ", j.getAge());
		  value.put("posteJ", j.getPoste());
		  mDb.insert("JOUEURS", null, value);
	  }

	  /**
	   * @param id l'identifiant du joueur à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("JOUEURS", "idJ = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param j le joueur modifié
	   */
	  public void modifier(Joueur j) {
		  ContentValues value = new ContentValues();
		  value.put("nomJ", j.getNom());
		  value.put("tailleJ", j.getTaille());
		  value.put("ageJ", j.getAge());
		  mDb.update("JOUEURS", value, "idJ = ?", new String[] {String.valueOf(j.getId())});
	  }

	  /**
	   * @param i l'identifiant du joueur à récupérer
	   */
	  public Joueur selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM JOUEURS WHERE idJ = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new Joueur(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), 0);
	  }
	  
	  public List<Joueur> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM JOUEURS", null);
		c.moveToFirst();
		List<Joueur> joueurs = new ArrayList<Joueur>();
		while(c.moveToNext()){
			joueurs.add(new Joueur(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), 0));
		}
		c.close();
		return joueurs;
	  }
	}