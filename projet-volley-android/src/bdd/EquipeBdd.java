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
public class EquipeBdd extends BDD
{
private static final String NOM_TABLE = "Equipe";
private static final String [] CHAMPS = {"ID  INT PRIMARY KEY",
										 "NOM  TEXT",
										 "ENTRAINEUR TEXT"};
public EquipeBdd(Context context) 
{
super(context, NOM_TABLE, CHAMPS);
}
//******************** Methode ajouter une Equipe ************************
public void add(Equipe e)
{
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put("ID", e.getId());
values.put("NOM", e.getNom());
values.put("ENTRAINEUR", e.getEntraineur());
db.insert(NOM_TABLE, null, values);
db.close();
}
//******************** methode modifier une Equipe **********************
public int update(Equipe e)
{
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put("ID", e.getId());
values.put("NOM", e.getNom());
values.put("AGE", e.getEntraineur());
return db.update(NOM_TABLE, values,"ID=",new String[]{String.valueOf(e.getId())});
}
//******************** methode supprimer une Equipe **********************
public void delete(Equipe e)
{
	SQLiteDatabase db = this.getWritableDatabase();	
	db.delete(NOM_TABLE, "ID="+new String[] { String.valueOf(e.getId())},null);
	db.close();
}
//******************** methode rechercher une Equipe **********************
public Equipe select(int i)
{
Equipe e=new Equipe();
SQLiteDatabase db = this.getReadableDatabase();
Cursor c=db.query(NOM_TABLE,new String[]{"ID","NOM","ENTRAINEUR"},"ID="+i,null,null,null,null);
if(c.moveToFirst())
{
	e.setId(c.getInt(1));
	e.setNom(c.getString(2));
	e.setEntraineur(c.getString(3)); 	
}
return e;
}
//******************** methode liste d'une Equipe **********************
public ArrayList<Equipe> selectAll()
{
	ArrayList<Equipe> lst=new ArrayList();
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor c=db.query(NOM_TABLE,new String[]{"ID","NOM","ENTRAINEUR"},null,null,null,null,null);
	c.moveToFirst();
	while(!c.isAfterLast())
	{
		Equipe e=new Equipe();
		e.setId(c.getInt(1));
		e.setNom(c.getString(2));
		e.setEntraineur(c.getString(3));
		// Ajouter l'enregistrement a la liste
		lst.add(e);
		c.moveToNext();	
	}
	//retourner la liste
	return lst;
}
}