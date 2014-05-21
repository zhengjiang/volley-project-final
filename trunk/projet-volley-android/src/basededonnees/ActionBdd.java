package basededonnees;

import java.util.ArrayList;
import java.util.List;

import basededonnees.BDD;
import modele.Action;
import android.content.Context;
import android.database.Cursor;
import android.content.*;


/**
 * @author Thibaut C
 *
 */
public class ActionBdd extends BDD {
	  public ActionBdd(Context pContext){
		  super(pContext);
	  }
	  /**
	   * @param a l'action à ajouter à la base
	   */
	  public void ajouter(Action a) {
		  ContentValues value = new ContentValues();
		  value.put("nomA", a.getNom());
		  mDb.insert("ACTIONS", null, value);
	  }

	  /**
	   * @param id l'identifiant de l'action à supprimer
	   */
	  public void supprimer(int id) {
		  mDb.delete("ACTIONS", "idA = ?", new String[]{String.valueOf(id)});
	  }

	  /**
	   * @param a l'action modifiée
	   */
	  public void modifier(Action a) {
		  ContentValues value = new ContentValues();
		  value.put("nomA", a.getNom());
		  mDb.update("ACTIONS", value, "idA = ?", new String[] {String.valueOf(a.getId())});
	  }

	  /**
	   * @param i l'identifiant de l'action à récupérer
	   */
	  public Action selectionner(int i){
		  Cursor c = mDb.rawQuery("SELECT * FROM ACTIONS WHERE idA = ?", new String[]{String.valueOf(i)});
		  c.moveToFirst();
		  return new Action(c.getInt(0), c.getString(1));
	  }
	  
	  public List<Action> selectionnerTout(){
		Cursor c = mDb.rawQuery("SELECT * FROM ACTIONS", null);
		c.moveToFirst();
		List<Action> actions = new ArrayList<Action>();
		while(c.moveToNext()){
			actions.add(new Action(c.getInt(0), c.getString(1)));
		}
		c.close();
		return actions;
	  }
	}