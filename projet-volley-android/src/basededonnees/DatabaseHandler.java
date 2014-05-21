package basededonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
  
	  public static final String JOUEURS = "CREATE TABLE JOUEURS (idJ INTEGER PRIMARY KEY AUTOINCREMENT, nomJ TEXT, tailleJ INTEGER, ageJ INTEGER, posteJ INTEGER);";
	  public static final String EQUIPES = "CREATE TABLE EQUIPES (idE INTEGER PRIMARY KEY AUTOINCREMENT, nomE TEXT, entraineurE TEXT);";
	  public static final String COMPETITIONS = "CREATE TABLE COMPETITIONS (idC INTEGER PRIMARY KEY AUTOINCREMENT, anneeC INTEGER, nomC TEXT, typeC TEXT);";
	  public static final String JOUEUREQUIPE = "CREATE TABLE JOUEUR_EQUIPE (idJE INTEGER PRIMARY KEY AUTOINCREMENT, joueurJE INTEGER, EQUIPEje INTEGER, maillotJE INTEGER, coursJE INTEGER);";
	  public static final String ACTIONS = "CREATE TABLE ACTIONS (idA INTEGER PRIMARY KEY AUTOINCREMENT, nomA TEXT);";
	  public static final String MATCHS = "CREATE TABLE MATCHS (idM INTEGER PRIMARY KEY AUTOINCREMENT, dateM TEXT, lieuM TEXT, equipe1 INTEGER, equipe2 INTEGER, competition INTEGER);";
	  public static final String SETS = "CREATE TABLE SETS (idS INTEGER PRIMARY KEY AUTOINCREMENT, numS INTEGER, scoreEquipe1S INTEGER, scoreEquipe2S INTEGER, matchS INTEGER);";
	  public static final String JOUEURACTION = "CREATE TABLE JOUEUR_ACTION (idJA INTEGER PRIMARY KEY AUTOINCREMENT, setJA INTEGER, actionJA INTEGER, joueurJA INTEGER, pointJA INTEGER, noteJA INTEGER, posteJA INTEGER);";
	  
	  public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
	    super(context, name, factory, version);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase db) {
	    db.execSQL(JOUEURS + EQUIPES + COMPETITIONS + JOUEUREQUIPE + ACTIONS + MATCHS + SETS + JOUEURACTION);
	  }
	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		  this.onCreate(db);
	  }
	}
