/**
 * @author IKBAL
 */
package bdd;

import modele.*;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class JoueurEquipeBdd extends BDD
{
private static final String NOM_TABLE = "Joueur";
private static final String [] CHAMPS = {"ID  INT PRIMARY KEY",
										 "ID_JOUEUR  INT",
										 "ID_EQUIPE INT",
										 "NUM_MAILLOT INT",
										 "ENCOURS BOOLEAN"};
	public JoueurEquipeBdd(Context context) 
	{
	super(context, NOM_TABLE, CHAMPS);
	}
//**************************** Ajouter *********************
	public void add(JoueurEquipe je)
	{
		ContentValues values = new ContentValues();
		values.put("ID", je.getId());
		values.put("ID_JOUEUR", je.getJoueur().getId());
		values.put("ID_EQUIPE", je.getEquipe().getId());
		values.put("NUM_MAILLOT", je.getNumMaillot());
		values.put("ENCOURS", je.getEnCours());		
		getWritableDatabase().insert(NOM_TABLE, null, values);
	}
	//********************* Mise a jour **********************
	public int update(JoueurEquipe je)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("ID", je.getId());
		values.put("ID_JOUEUR", je.getJoueur().getId());
		values.put("ID_EQUIPE", je.getEquipe().getId());
		values.put("NUM_MAILLOT", je.getNumMaillot());
		values.put("ENCOURS", je.getEnCours());
	return db.update(NOM_TABLE, values,"ID=",new String[]{String.valueOf(je.getId())});		
	}
	//******************** Suppression ************************
	public void delete(JoueurEquipe j)
	{
	SQLiteDatabase db = this.getWritableDatabase();	
	db.delete(NOM_TABLE, "ID="+new String[] { String.valueOf(j.getId())},null);
	db.close();
	}
	//******************** Recherche **************************
	public JoueurEquipe select(int i)
	{
		JoueurEquipe je=new JoueurEquipe();
		SQLiteDatabase db = this.getReadableDatabase();
		Context ctx = null;
		Cursor c=db.query(NOM_TABLE,new String[]{"ID","NOM","ENTRAINEUR"},"ID="+i,null,null,null,null);
		if(c.moveToFirst())
		{
			JoueurBdd j=new JoueurBdd(ctx);
			EquipeBdd e=new EquipeBdd(ctx);
			
			je.setId(c.getInt(1));			
			//*********** joueur *****************
			Joueur unj=j.select(c.getInt(2));
			je.setJoueur(unj);
			//********** Equipe ******************
			Equipe unee=e.select(c.getInt(3));
			je.setEquipe(unee);
			
			je.setNumMaillot(c.getInt(4));
			je.setEnCours(Boolean.parseBoolean(c.getString(5)));		 	
		}
		return je;	
}
	//********************** Liste ****************************
	public ArrayList<JoueurEquipe> selectAll()
	{
		ArrayList<JoueurEquipe> lst=new ArrayList();
		Context ctx = null;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c=db.query(NOM_TABLE,new String[]{"ID","ID_JOUEUR","ID_EQUIPE","NUM_MAILLOT","ENCOURS"},null,null,null,null,null);
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			JoueurEquipe je=new JoueurEquipe();
			JoueurBdd j=new JoueurBdd(ctx);
			EquipeBdd e=new EquipeBdd(ctx);
			
			je.setId(c.getInt(1));			
			//*********** joueur *****************
			Joueur unj=j.select(c.getInt(2));
			je.setJoueur(unj);
			//********** Equipe ******************
			Equipe unee=e.select(c.getInt(3));
			je.setEquipe(unee);
			
			je.setNumMaillot(c.getInt(4));
			je.setEnCours(Boolean.parseBoolean(c.getString(5)));
			// Ajouter l'enregistrement a la liste
			lst.add(je);
			c.moveToNext();	
		}
		//retourner la liste
		return lst;
	}
}