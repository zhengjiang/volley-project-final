package bdd;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Vincent B
 *
 */

public class  BDD extends SQLiteOpenHelper {
	private static final String BASE = "VolleyBall.db";
	private static final int DATABASE_VERSION = 1;
	private String NOM_TABLE;
	private String[] CHAMPS;
	
	public BDD(Context context,String NOM_TABLE, String[] CHAMPS) {
		super(context, BASE, null, DATABASE_VERSION);
		this.NOM_TABLE = NOM_TABLE;
		this.CHAMPS = CHAMPS;
	}	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + NOM_TABLE+"(" + CHAMPS[0]; 		
		for (int i=1;i<CHAMPS.length; i++) {
			sql+=", "+CHAMPS[i];
		}		
		sql+=" ) ";		
		db.execSQL(sql);
	}	
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
		db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE);
		onCreate(db);
	}		
}

