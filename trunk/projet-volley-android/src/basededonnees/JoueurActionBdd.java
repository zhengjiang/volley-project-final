package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.ActionJoueur;
import android.content.Context;
import android.database.Cursor;
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
		  value.put("setJA", jA.getSet().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("actionJA", jA.getAction().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("joueurJA", jA.getJoueur().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("pointJA", jA.getNumPoint());
		  value.put("noteJA", jA.getNote());
		  value.put("posteJA", jA.getPoste());
		  mDb.insert("JOUEURACTION", null, value);
	  }

	  /**
	   * @param id l'identifiant du joueur à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("JOUEURACTION", "idJA = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param jA le joueur modifié
	   */
	  public void modifier(ActionJoueur jA) {
		  ContentValues value = new ContentValues();
		  value.put("setJA", jA.getSet().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("actionJA", jA.getAction().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("joueurJA", jA.getJoueur().getId());// A VERIFIEEEERRRR ET TESTER !
		  value.put("pointJA", jA.getNumPoint());
		  value.put("noteJA", jA.getNote());
		  value.put("posteJA", jA.getPoste());
		  mDb.update("JOUEURACTION", value, "idJA = ?", new String[] {String.valueOf(jA.getId())});
	  }

	  /**
	   * @param i l'identifiant du actionJoueur à récupérer
	   */
	  public ActionJoueur selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM JOUEURACTION WHERE idJA = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new ActionJoueur(c.getInt(0), set, action, joueur, c.getInt(4), c.getInt(5), c.getInt(6));
	  }
	  
	  public List<ActionJoueur> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM JOUEURACTION", null);
		c.moveToFirst();
		List<ActionJoueur> joueurs = new ArrayList<ActionJoueur>();
		while(c.moveToNext()){
			joueurs.add(new ActionJoueur(c.getInt(0), set, action, joueur, c.getInt(4), c.getInt(5), c.getInt(6)));
		}
		c.close();
		return joueurs;
	  }
	}