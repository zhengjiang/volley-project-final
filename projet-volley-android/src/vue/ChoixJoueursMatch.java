package vue;

import modele.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.l3info.projet_volley_android.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ChoixJoueursMatch extends Activity {

	// on déclare les élements qui vont constituer la vue
	private ListView listViewJoueurs1;
	private ListView listViewTitulaires1;
	private ListView listViewRemplacants1;
	private ListView listViewJoueurs2;
	private ListView listViewTitulaires2;
	private ListView listViewRemplacants2;
	private Button titulaire1 = null;
	private Button remplacant1 = null;
	private Button titulaire2 = null;
	private Button remplacant2 = null;
	private Button nonTitulaire1 = null;
	private Button nonRemplacant1 = null;
	private Button nonTitulaire2 = null;
	private Button nonRemplacant2 = null;
	private Button precedent;
	private Button valider;
	private TextView nomEquipe1;
	private TextView nomEquipe2;
	
	// on initialise un tableau de JoueurEquipe avec tous les JoueursEquipe de notre modèle
	ArrayList<JoueurEquipe> arrayListJoueurs1=InitialisationModele.initJoueurEquipe();
	// on déclare un tableau qui va contenir les noms des joueurs de l'équipe 1...
	ArrayList<String> listeNomJoueurs1;
	// ...et un adaptateur sur ce tableau
	ArrayAdapter<String> adapterJoueurs1;

	// on fait pareil pour les joueurs titulaires, les joueurs remplaçcants et pour l'équipe 2
	ArrayList<JoueurEquipe> arrayListTitulaires1;
	ArrayAdapter<String> adapterTitulaires1;
	ArrayList<String> listeNomTitulaires1;
	
	ArrayList<JoueurEquipe> arrayListRemplacants1;
	ArrayAdapter<String> adapterRemplacants1;
	ArrayList<String> listeNomRemplacants1;
	
	ArrayList<JoueurEquipe> arrayListJoueurs2=InitialisationModele.initJoueurEquipe();
	ArrayAdapter<String> adapterJoueurs2;
	ArrayList<String> listeNomJoueurs2;
	
	ArrayList<JoueurEquipe> arrayListTitulaires2;
	ArrayAdapter<String> adapterTitulaires2;
	ArrayList<String> listeNomTitulaires2;
	
	ArrayList<JoueurEquipe> arrayListRemplacants2;
	ArrayAdapter<String> adapterRemplacants2;
	ArrayList<String> listeNomRemplacants2;
	
    // listener sur le bouton qui permet de mettre des joueurs de l'équipe 1 comme titulaires	
	private OnTouchListener touchListenerTitulaire1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewJoueurs1.getCount()];
	    	for (int i=0 ; i<listViewJoueurs1.getCount() ; i++) {
	    		if (listViewJoueurs1.getCheckedItemPositions().get(i)) {
	    			listViewJoueurs1.setItemChecked(i, false);
	    			// on ajoute le joueur à la liste des titulaires
	    			listeNomTitulaires1.add(listViewJoueurs1.getItemAtPosition(i).toString());
	    			adapterTitulaires1.notifyDataSetChanged();
	    			
	    			// on a jouter le joueur sélectionner a un tableau pour le supprimer à la fin
	    			supprimer[i] = listViewJoueurs1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomJoueurs1.remove(supprimer[i]);
	    		adapterJoueurs1.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de sortir des joueurs de l'équipe 1 de la liste des titulaires	
	private OnTouchListener touchListenerNonTitulaire1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewTitulaires1.getCount()];
	    	for (int i=0 ; i<listViewTitulaires1.getCount() ; i++) {
	    		if (listViewTitulaires1.getCheckedItemPositions().get(i)) {
	    			listViewTitulaires1.setItemChecked(i, false);
	    			// on ajoute le joueur à la liste des remplacants
	    			listeNomJoueurs1.add(listViewTitulaires1.getItemAtPosition(i).toString());
	    			adapterJoueurs1.notifyDataSetChanged();
	    			supprimer[i] = listViewTitulaires1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomTitulaires1.remove(supprimer[i]);
	    		adapterTitulaires1.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de mettre des joueurs de l'équipe 1 comme remplacants
	private OnTouchListener touchListenerRemplacant1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewJoueurs1.getCount()];
	    	for (int i=0 ; i<listViewJoueurs1.getCount() ; i++) {
	    		if (listViewJoueurs1.getCheckedItemPositions().get(i)) {
	    			listViewJoueurs1.setItemChecked(i, false);
	    			listeNomRemplacants1.add(listViewJoueurs1.getItemAtPosition(i).toString());
	    			adapterRemplacants1.notifyDataSetChanged();
	    			supprimer[i] = listViewJoueurs1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomJoueurs1.remove(supprimer[i]);
	    		adapterJoueurs1.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de sortir des joueurs de l'équipe 1 de la liste des remplacants	
	private OnTouchListener touchListenerNonRemplacant1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewRemplacants1.getCount()];
	    	for (int i=0 ; i<listViewRemplacants1.getCount() ; i++) {
	    		if (listViewRemplacants1.getCheckedItemPositions().get(i)) {
	    			listViewRemplacants1.setItemChecked(i, false);
	    			listeNomJoueurs1.add(listViewRemplacants1.getItemAtPosition(i).toString());
	    			adapterJoueurs1.notifyDataSetChanged();
	    			supprimer[i] = listViewRemplacants1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomRemplacants1.remove(supprimer[i]);
	    		adapterRemplacants1.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
	   // listener sur le bouton qui permet de mettre des joueurs de l'équipe 2 comme titulaires	
	private OnTouchListener touchListenerTitulaire2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewJoueurs2.getCount()];
	    	for (int i=0 ; i<listViewJoueurs2.getCount() ; i++) {
	    		if (listViewJoueurs2.getCheckedItemPositions().get(i)) {
	    			listViewJoueurs2.setItemChecked(i, false);
	    			listeNomTitulaires2.add(listViewJoueurs2.getItemAtPosition(i).toString());
	    			adapterTitulaires2.notifyDataSetChanged();
	    			supprimer[i] = listViewJoueurs2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomJoueurs2.remove(supprimer[i]);
	    		adapterJoueurs2.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de sortir des joueurs de l'équipe 2 de la liste des titulaires	
	private OnTouchListener touchListenerNonTitulaire2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewTitulaires2.getCount()];
	    	for (int i=0 ; i<listViewTitulaires2.getCount() ; i++) {
	    		if (listViewTitulaires2.getCheckedItemPositions().get(i)) {
	    			listViewTitulaires2.setItemChecked(i, false);
	    			listeNomJoueurs2.add(listViewTitulaires2.getItemAtPosition(i).toString());
	    			adapterJoueurs2.notifyDataSetChanged();
	    			supprimer[i] = listViewTitulaires2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomTitulaires2.remove(supprimer[i]);
	    		adapterTitulaires2.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de mettre des joueurs de l'équipe 2 comme remplacants
	private OnTouchListener touchListenerRemplacant2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewJoueurs2.getCount()];
	    	for (int i=0 ; i<listViewJoueurs2.getCount() ; i++) {
	    		if (listViewJoueurs2.getCheckedItemPositions().get(i)) {
	    			listViewJoueurs2.setItemChecked(i, false);
	    			listeNomRemplacants2.add(listViewJoueurs2.getItemAtPosition(i).toString());
	    			adapterRemplacants2.notifyDataSetChanged();
	    			supprimer[i] = listViewJoueurs2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomJoueurs2.remove(supprimer[i]);
	    		adapterJoueurs2.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de sortir des joueurs de l'équipe 1 de la liste des remplacants
	private OnTouchListener touchListenerNonRemplacant2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewRemplacants2.getCount()];
	    	for (int i=0 ; i<listViewRemplacants2.getCount() ; i++) {
	    		if (listViewRemplacants2.getCheckedItemPositions().get(i)) {
	    			listViewRemplacants2.setItemChecked(i, false);
	    			listeNomJoueurs2.add(listViewRemplacants2.getItemAtPosition(i).toString());
	    			adapterJoueurs2.notifyDataSetChanged();
	    			supprimer[i] = listViewRemplacants2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		listeNomRemplacants2.remove(supprimer[i]);
	    		adapterRemplacants2.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
	// listener sur le bouton précédent
	private OnTouchListener touchListenerPrecedent = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	ChoixJoueursMatch.this.finish();
	    	return true;
	    }
	};
	
	private OnTouchListener touchListenerValider = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	
	    	/*System.out.println(listViewTitulaires1.getCount());
	    	System.out.println(listViewTitulaires2.getCount());
	    	System.out.println(listViewRemplacants2.getCount());
	    	System.out.println(listViewRemplacants2.getCount());
	    	*/
	    	
	    	if (listViewTitulaires1.getCount() == 6) {
	    		if (listViewTitulaires2.getCount() == 6) {
	    			if (listViewRemplacants1.getCount() <= 6) {
	    				if (listViewRemplacants2.getCount() <= 6) {
	    					System.out.println("OK"); //Intent intent = new Intent(ChoixJoueursMatch.this, SaisieStatsActivity.class);
	    				}
	    			}
	    		}
	    	}
	    	return true;
	    }
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choix_joueurs_match);
		
		// on récupère les vues du fichier XML
		listViewJoueurs1 = (ListView) findViewById(R.id.listJoueursEquipe1);
		listViewTitulaires1 = (ListView) findViewById(R.id.listJoueursTitulairesEquipe1);
		listViewRemplacants1 = (ListView) findViewById(R.id.listJoueursRemplacantsEquipe1);
		listViewJoueurs2 = (ListView) findViewById(R.id.listJoueursEquipe2);
		listViewTitulaires2 = (ListView) findViewById(R.id.listJoueursTitulairesEquipe2);
		listViewRemplacants2 = (ListView) findViewById(R.id.listJoueursRemplacantsEquipe2);
		titulaire1 = (Button) findViewById(R.id.titulaire1);
		remplacant1 = (Button) findViewById(R.id.remplacant1);
		nonTitulaire1 = (Button) findViewById(R.id.nonTitulaire1);
		nonRemplacant1 = (Button) findViewById(R.id.nonRemplacant1);
		titulaire2 = (Button) findViewById(R.id.titulaire2);
		remplacant2 = (Button) findViewById(R.id.remplacant2);
		nonTitulaire2 = (Button) findViewById(R.id.nonTitulaire2);
		nonRemplacant2 = (Button) findViewById(R.id.nonRemplacant2);
		precedent = (Button) findViewById(R.id.precedent2);
		valider = (Button) findViewById(R.id.valider4);
		
		
		Intent intent = getIntent();
		Resources res = getResources();
	    String stringNomEquipe1 = String.format(res.getString(R.string.nomEquipe1),
	    		intent.getStringExtra("nomEquipe1"));
	    nomEquipe1 = (TextView) findViewById(R.id.nomEquipe1);
	    nomEquipe1.setText(stringNomEquipe1);
	    
	    String stringNomEquipe2 = String.format(res.getString(R.string.nomEquipe2),
	    		intent.getStringExtra("nomEquipe2"));
	    nomEquipe2 = (TextView) findViewById(R.id.nomEquipe2);
	    nomEquipe2.setText(stringNomEquipe2);
	 
		
		/* on génère dans la liste, le noms des joueurs de l'équipe qui a été sélectionné dans
		   l'activité précédente */
		listeNomJoueurs1 = new ArrayList<String>();
		for (int i=0; i<arrayListJoueurs1.size();i++)
		{
			if (intent.getIntExtra("idEquipe1", -1) == arrayListJoueurs1.get(i).getEquipe().getId()) {
				listeNomJoueurs1.add(arrayListJoueurs1.get(i).getJoueur().getNom());
			}
		}
		
		// on initialise l'adaptateur et on le lie a la ListView des joueurs de l'équipe1
		adapterJoueurs1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
				listeNomJoueurs1);
		listViewJoueurs1.setAdapter(adapterJoueurs1);
		
		arrayListTitulaires1 = new ArrayList<JoueurEquipe>();
		listeNomTitulaires1 = new ArrayList<String>();
		adapterTitulaires1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
				listeNomTitulaires1);
		listViewTitulaires1.setAdapter(adapterTitulaires1);
		
		arrayListRemplacants1 = new ArrayList<JoueurEquipe>();
		listeNomRemplacants1 = new ArrayList<String>();
		adapterRemplacants1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
				listeNomRemplacants1);
		listViewRemplacants1.setAdapter(adapterRemplacants1);
		
		
		listeNomJoueurs2 = new ArrayList<String>();
		for (int i=0; i<arrayListJoueurs2.size();i++)
		{
			if (intent.getIntExtra("idEquipe2", -1) == arrayListJoueurs2.get(i).getEquipe().getId()) {
				listeNomJoueurs2.add(arrayListJoueurs2.get(i).getJoueur().getNom());
			}
		}
		
		adapterJoueurs2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
				listeNomJoueurs2);
		listViewJoueurs2.setAdapter(adapterJoueurs2);
		
		arrayListTitulaires2 = new ArrayList<JoueurEquipe>();
		listeNomTitulaires2 = new ArrayList<String>();
		adapterTitulaires2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
				listeNomTitulaires2);
		listViewTitulaires2.setAdapter(adapterTitulaires2);
		
		arrayListRemplacants2 = new ArrayList<JoueurEquipe>();
		listeNomRemplacants2 = new ArrayList<String>();
		adapterRemplacants2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
				listeNomRemplacants2);
		listViewRemplacants2.setAdapter(adapterRemplacants2);
		
		// on associe chaque boutons a son listener
		titulaire1.setOnTouchListener(touchListenerTitulaire1);
		remplacant1.setOnTouchListener(touchListenerRemplacant1);
		nonTitulaire1.setOnTouchListener(touchListenerNonTitulaire1);
		nonRemplacant1.setOnTouchListener(touchListenerNonRemplacant1);
		titulaire2.setOnTouchListener(touchListenerTitulaire2);
		remplacant2.setOnTouchListener(touchListenerRemplacant2);
		nonTitulaire2.setOnTouchListener(touchListenerNonTitulaire2);
		nonRemplacant2.setOnTouchListener(touchListenerNonRemplacant2);
		precedent.setOnTouchListener(touchListenerPrecedent);
		valider.setOnTouchListener(touchListenerValider);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
