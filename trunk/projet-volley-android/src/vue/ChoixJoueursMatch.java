package vue;

import modele.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.l3info.projet_volley_android.R;

import android.os.Bundle;
import android.os.Parcelable;
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
	private Button boutonAcceuil;
	private TextView nomEquipe1;
	private TextView nomEquipe2;
	
	ArrayList<JoueurEquipe> arrayListTousJoueurs=InitialisationModele.initJoueurEquipe();
	
	ArrayList<JoueurEquipe> arrayListJoueurs1 = new ArrayList<JoueurEquipe>();
	JoueurEquipeAdapter joueurEquipeAdapter1;
	
	ArrayList<JoueurEquipe> arrayListJoueursTitulaires1 = new ArrayList<JoueurEquipe>();
	JoueurEquipeAdapter joueurEquipeAdapterTitulaires1;
	
	ArrayList<JoueurEquipe> arrayListJoueursRemplacants1 = new ArrayList<JoueurEquipe>();
	JoueurEquipeAdapter joueurEquipeAdapterRemplacants1;
	
	ArrayList<JoueurEquipe> arrayListJoueurs2 = new ArrayList<JoueurEquipe>();
	JoueurEquipeAdapter joueurEquipeAdapter2;
	
	ArrayList<JoueurEquipe> arrayListJoueursTitulaires2 = new ArrayList<JoueurEquipe>();
	JoueurEquipeAdapter joueurEquipeAdapterTitulaires2;
	
	ArrayList<JoueurEquipe> arrayListJoueursRemplacants2 = new ArrayList<JoueurEquipe>();
	JoueurEquipeAdapter joueurEquipeAdapterRemplacants2;
	
	String dateMatch;
	String lieuMatch;
	Equipe equipe1;
	Equipe equipe2;
	
    // listener sur le bouton qui permet de mettre des joueurs de l'équipe 1 comme titulaires	
	private OnTouchListener touchListenerTitulaire1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewJoueurs1.getCount()];
	    	for (int i=0 ; i<listViewJoueurs1.getCount() ; i++) {
	    		if (listViewJoueurs1.getCheckedItemPositions().get(i)) {
	    			listViewJoueurs1.setItemChecked(i, false);
	    			arrayListJoueursTitulaires1.add((JoueurEquipe) listViewJoueurs1.getItemAtPosition(i));
	    			joueurEquipeAdapterTitulaires1.notifyDataSetChanged();
	  
	    			supprimer[i] = listViewJoueurs1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueurs1.remove(supprimer[i]);
	    		joueurEquipeAdapter1.notifyDataSetChanged();
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
	    			arrayListJoueurs1.add((JoueurEquipe) listViewTitulaires1.getItemAtPosition(i));
	    			joueurEquipeAdapter1.notifyDataSetChanged();
	    			supprimer[i] = listViewTitulaires1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueursTitulaires1.remove(supprimer[i]);
	    		joueurEquipeAdapterTitulaires1.notifyDataSetChanged();
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
	    			arrayListJoueursRemplacants1.add((JoueurEquipe) listViewJoueurs1.getItemAtPosition(i));
	    			joueurEquipeAdapterRemplacants1.notifyDataSetChanged();
	    			supprimer[i] = listViewJoueurs1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueurs1.remove(supprimer[i]);
	    		joueurEquipeAdapter1.notifyDataSetChanged();
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
	    			arrayListJoueurs1.add((JoueurEquipe) listViewRemplacants1.getItemAtPosition(i));
	    			joueurEquipeAdapter1.notifyDataSetChanged();
	    			supprimer[i] = listViewRemplacants1.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueursRemplacants1.remove(supprimer[i]);
	    		joueurEquipeAdapterRemplacants1.notifyDataSetChanged();
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
	    			arrayListJoueursTitulaires2.add((JoueurEquipe) listViewJoueurs2.getItemAtPosition(i));
	    			joueurEquipeAdapterTitulaires2.notifyDataSetChanged();
	  
	    			supprimer[i] = listViewJoueurs2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueurs2.remove(supprimer[i]);
	    		joueurEquipeAdapter2.notifyDataSetChanged();
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
	    			// on ajoute le joueur à la liste des remplacants
	    			arrayListJoueurs2.add((JoueurEquipe) listViewTitulaires2.getItemAtPosition(i));
	    			joueurEquipeAdapter2.notifyDataSetChanged();
	    			supprimer[i] = listViewTitulaires2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueursTitulaires2.remove(supprimer[i]);
	    		joueurEquipeAdapterTitulaires2.notifyDataSetChanged();
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
	    			arrayListJoueursRemplacants2.add((JoueurEquipe) listViewJoueurs2.getItemAtPosition(i));
	    			joueurEquipeAdapterRemplacants2.notifyDataSetChanged();
	    			supprimer[i] = listViewJoueurs2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueurs2.remove(supprimer[i]);
	    		joueurEquipeAdapter2.notifyDataSetChanged();
	    	}
	    	return true;
	    }
	};
	
    // listener sur le bouton qui permet de sortir des joueurs de l'équipe 2 de la liste des remplacants	
	private OnTouchListener touchListenerNonRemplacant2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Object[] supprimer = new Object[listViewRemplacants2.getCount()];
	    	for (int i=0 ; i<listViewRemplacants2.getCount() ; i++) {
	    		if (listViewRemplacants2.getCheckedItemPositions().get(i)) {
	    			listViewRemplacants2.setItemChecked(i, false);
	    			arrayListJoueurs2.add((JoueurEquipe) listViewRemplacants2.getItemAtPosition(i));
	    			joueurEquipeAdapter2.notifyDataSetChanged();
	    			supprimer[i] = listViewRemplacants2.getItemAtPosition(i);
	    		}
	    	}
	    	for (int i=0 ; i<supprimer.length ; i++) {
	    		arrayListJoueursRemplacants2.remove(supprimer[i]);
	    		joueurEquipeAdapterRemplacants2.notifyDataSetChanged();
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
	
	// listener sur le bouton accueil
	private OnTouchListener touchListenerAccueil = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	Intent intent = new Intent(ChoixJoueursMatch.this, MainActivity.class);
			startActivity(intent);
	    	return true;
	    }
	};
	
	private OnTouchListener touchListenerValider = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	
	    	if (listViewTitulaires1.getCount() == 6) {
	    		if (listViewTitulaires2.getCount() == 6) {
	    			if (listViewRemplacants1.getCount() <= 6) {
	    				if (listViewRemplacants2.getCount() <= 6) {
	    					Intent intent = new Intent(ChoixJoueursMatch.this, SaisieStatsActivity.class);
	    					Match match = new Match(0, dateMatch, lieuMatch, equipe1, equipe2, new Competition(0, 2014, "Championnat", "Championnat"));
	    					//System.out.println(match.getDate()); => NE FONCTIONNE PAS
	    					intent.putExtra("match", match);
	    					intent.putParcelableArrayListExtra("titulairesEquipe1", arrayListJoueursTitulaires1);
	    					intent.putParcelableArrayListExtra("remplacantsEquipe1", arrayListJoueursTitulaires1);
	    					intent.putParcelableArrayListExtra("titulairesEquipe2", arrayListJoueursTitulaires1);
	    					intent.putParcelableArrayListExtra("remplacantsEquipe2", arrayListJoueursTitulaires1);
	    					startActivity(intent);
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
		precedent = (Button) findViewById(R.id.precedent35);
		valider = (Button) findViewById(R.id.valider4);
		boutonAcceuil = (Button) findViewById(R.id.RetourAccueil);
		
		Intent intent = getIntent();
		Resources res = getResources();
		
		equipe1 = (Equipe) intent.getParcelableExtra("equipe1");
		equipe2 = (Equipe) intent.getParcelableExtra("equipe2");
		
		int jourDate = intent.getIntExtra("jourDate", -1);
		int moisDate = intent.getIntExtra("moisDate", -1);
		int anneeDate = intent.getIntExtra("anneeDate", -1);
		dateMatch = Integer.toString(jourDate) + "/" + Integer.toString(moisDate) + "/" + Integer.toString(anneeDate);
		
		lieuMatch = intent.getStringExtra("lieuMatch");
		
		
		
	    String stringNomEquipe1 = String.format(res.getString(R.string.nomEquipe1),
	    		equipe1.getNom());
	    nomEquipe1 = (TextView) findViewById(R.id.nomEquipe1);
	    nomEquipe1.setText(stringNomEquipe1);
	    
	    String stringNomEquipe2 = String.format(res.getString(R.string.nomEquipe2),
	    		equipe2.getNom());
	    nomEquipe2 = (TextView) findViewById(R.id.nomEquipe2);
	    nomEquipe2.setText(stringNomEquipe2);
	    
		for (int i=0; i<arrayListTousJoueurs.size();i++)
		{
			if (equipe1.getId() == arrayListTousJoueurs.get(i).getEquipe().getId()) {
			    arrayListJoueurs1.add(arrayListTousJoueurs.get(i));
			}
		}
		joueurEquipeAdapter1 = new JoueurEquipeAdapter(this, android.R.layout.simple_list_item_single_choice, arrayListJoueurs1);
	    listViewJoueurs1.setAdapter(joueurEquipeAdapter1);
		
	    joueurEquipeAdapterTitulaires1 = new JoueurEquipeAdapter(this, android.R.layout.simple_list_item_single_choice, arrayListJoueursTitulaires1);
	    listViewTitulaires1.setAdapter(joueurEquipeAdapterTitulaires1);
		
	    joueurEquipeAdapterRemplacants1 = new JoueurEquipeAdapter(this, android.R.layout.simple_list_item_single_choice, arrayListJoueursRemplacants1);
	    listViewRemplacants1.setAdapter(joueurEquipeAdapterRemplacants1);
		
	    
		for (int i=0; i<arrayListTousJoueurs.size();i++)
		{
			if (equipe2.getId() == arrayListTousJoueurs.get(i).getEquipe().getId()) {
			    arrayListJoueurs2.add(arrayListTousJoueurs.get(i));
			}
		}
		joueurEquipeAdapter2 = new JoueurEquipeAdapter(this, android.R.layout.simple_list_item_single_choice, arrayListJoueurs2);
	    listViewJoueurs2.setAdapter(joueurEquipeAdapter2);
		
	    joueurEquipeAdapterTitulaires2 = new JoueurEquipeAdapter(this, android.R.layout.simple_list_item_single_choice, arrayListJoueursTitulaires2);
	    listViewTitulaires2.setAdapter(joueurEquipeAdapterTitulaires2);
		
	    joueurEquipeAdapterRemplacants2 = new JoueurEquipeAdapter(this, android.R.layout.simple_list_item_single_choice, arrayListJoueursRemplacants2);
	    listViewRemplacants2.setAdapter(joueurEquipeAdapterRemplacants2);
		
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
		boutonAcceuil.setOnTouchListener(touchListenerAccueil);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
