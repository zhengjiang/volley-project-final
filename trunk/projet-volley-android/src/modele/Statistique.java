package modele;

import java.util.Hashtable;

public class Statistique {
	Hashtable stats = new Hashtable();
	
	public void setStats(String stat, int tot, float avg){
		float nb[] = {tot, avg};
		stats.put(stat, nb);
	}
	
	public Hashtable getStats(){
		return stats;
	}
}
