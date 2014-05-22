package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Competition;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class CompetitionBdd extends BDD {
	  public CompetitionBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param c la compétition à ajouter à la base
	   */
	  public void ajouter(Competition c) {
		  ContentValues value = new ContentValues();
		  value.put("anneeC", c.getAnnee());
		  value.put("nomC", c.getNom());
		  value.put("typeC", c.getType());
		  mDb.insert("COMPETITIONS", null, value);
	  }

	  /**
	   * @param id l'identifiant du métier à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("COMPETITIONS", "idC = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param m le métier modifié
	   */
	  public void modifier(Competition c) {
		  ContentValues value = new ContentValues();
		  value.put("anneeC", c.getAnnee());
		  value.put("nomC", c.getNom());
		  value.put("typeC", c.getType());
		  mDb.update("COMPETITIONS", value, "idC = ?", new String[] {String.valueOf(c.getId())});
	  }

	  /**
	   * @param id l'identifiant du métier à récupérer
	   */
	  public Competition selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM COMPETITIONS WHERE idC = ?", new String[]{String.valueOf(i)});
		  if(c.getCount() == 0){
			  c.close();
			  return null;
		  }
		  c.moveToFirst();
		  Competition comp = new Competition(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3));
		  c.close();
		  return comp;
	  }
	  
	  public List<Competition> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM COMPETITIONS", null);
		c.moveToFirst();
		List<Competition> competitions = new ArrayList<Competition>();
		while(c.moveToNext()){
			competitions.add(new Competition(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3)));
		}
		c.close();
		return competitions;
	  }
	}