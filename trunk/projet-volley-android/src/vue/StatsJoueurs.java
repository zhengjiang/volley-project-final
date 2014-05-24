package vue;

import java.util.List;

import modele.*;

import com.l3info.projet_volley_android.R;

import controleur.Controleur;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StatsJoueurs extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_joueurs);
    	
    	Controleur c = Controleur.getInstance();
    	
    	c.jb.open();
//    	Joueur testJ = new Joueur(42, "JeanJoueur", 190, 23, 2);
//    	Joueur testJ2 = new Joueur(43, "JeanGaspard", 180, 21, 3);
//    	c.jb.ajouter(testJ);
//    	c.jb.ajouter(testJ2);
//    	
//    	Competition compet = new Competition(1, 2014, "L1", "Tournoi");
//    	c.cb.ajouter(compet);
//    	Equipe equipe1 = new Equipe(1, "Pacers", "Francis Cabrel");
//    	Equipe equipe2 = new Equipe(2, "Lakers", "Pitbull");
//    	c.eb.ajouter(equipe1);
//    	c.eb.ajouter(equipe1);
//    	Match m = new Match(1, "17/01/2014", "Montpellier", equipe1, equipe2, compet);
//    	c.mb.ajouter(m);
//    	Set set = new Set(2, 2, 5, 7, m);
//    	c.sb.ajouter(set);
//    	Action action = new Action(4, "passe");
//    	c.ab.ajouter(action);
//    	ActionJoueur actionJoueur = new ActionJoueur(35, set, action, testJ, 4, 1, 2);
//    	c.jab.ajouter(actionJoueur);
    	
//    	List<Joueur> listeJ = c.jb.selectionnerTout();
//        
//    	Log.v("test",""+listeJ.size());
//
//    	String[] array = new String[listeJ.size()];
//    	
//    	for(int i  = 0; i < listeJ.size(); i++){
//        	Log.v("test",""+listeJ.get(i).getNom());
//        	array[i] = listeJ.get(i).getNom();
//        }
//        
//    	Statistique s = c.jab.getStats(42);
//    	Log.v("test", ""+s);
//    	float[] nb = (float[])s.getStats().get(nomAction);
    	
    	Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
    	String[] items = new String[]{"Joueur1","Joueur2","Joueur3","..."};
//    	String[] items = array;
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
    	dropdown.setAdapter(adapter);
    	
    	

	}
	
	

}
