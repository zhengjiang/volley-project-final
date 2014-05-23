package basededonnees;

import java.util.ArrayList;
import java.util.List;

import modele.Equipe;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class EquipeBdd extends BDD {
	  public EquipeBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param e l'équipe à ajouter à la base
	   */
	  public long ajouter(Equipe e) {
		  ContentValues value = new ContentValues();
		  value.put("nomE", e.getNom());
		  value.put("entraineurE", e.getEntraineur());
		  return mDb.insert("EQUIPES", null, value);
	  }

	  /**
	   * @param id l'identifiant de l'équipe à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("EQUIPES", "idE = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param e l'équipe modifiée
	   */
	  public void modifier(Equipe e) {
		  ContentValues value = new ContentValues();
		  value.put("nomE", e.getNom());
		  value.put("entraineurE", e.getEntraineur());
		  mDb.update("EQUIPES", value, "idE = ?", new String[] {String.valueOf(e.getId())});
	  }

	  /**
	   * @param i l'identifiant de l'équipe à récupérer
	   */
	  public Equipe selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM EQUIPES WHERE idE = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new Equipe(c.getInt(0), c.getString(1), c.getString(2));
	  }
	  
	  public List<Equipe> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM EQUIPES", null);
		List<Equipe> equipes = new ArrayList<Equipe>();
		while(c.moveToNext()){
			equipes.add(new Equipe(c.getInt(0), c.getString(1), c.getString(2)));
		}
		c.close();
		return equipes;
	  }
	}