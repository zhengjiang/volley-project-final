package basededonnees;

import basededonnees.DatabaseHandler;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Vincent B
 *
 */

public abstract class BDD {
	  protected final static int VERSION = 1;
	  protected final static String NOM = "database.db";
	    
	  protected SQLiteDatabase mDb = null;
	  protected DatabaseHandler mHandler = null;
	    
	  public BDD(Context pContext) {
	    this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
	  }
	    
	  public SQLiteDatabase open() {
	    mDb = mHandler.getWritableDatabase();
	    return mDb;
	  }
	    
	  public void close() {
	    mDb.close();
	  }
	    
	  public SQLiteDatabase getDb() {
	    return mDb;
	  }
	}


