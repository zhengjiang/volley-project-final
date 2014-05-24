package vue;

import modele.*;

import java.util.ArrayList;
import java.util.Calendar;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

public class ChoixEquipesMatch extends Activity {
	
	private ListView listeEquipes1;
	private ListView listeEquipes2;
	private Button boutonValider;
	private DatePicker date;
	private EditText lieu;
	private ArrayList<Equipe> equipes=InitialisationModele.initEquipes(); 
	private EquipeAdapter equipe1Adapter;
	private EquipeAdapter equipe2Adapter;
	private Button precedent;
	private Button boutonAcceuil;
	
	
	private OnTouchListener touchListenerValider = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (equipes.get(listeEquipes1.getCheckedItemPosition()).getId() !=
	    			equipes.get(listeEquipes2.getCheckedItemPosition()).getId()) {*/
	    			if (lieu.getText().toString().trim().length() != 0) {
				    	Intent intent = new Intent(ChoixEquipesMatch.this, ChoixJoueursMatch.class);
				    	intent.putExtra("equipe1", equipes.get(listeEquipes1.getCheckedItemPosition()));
				    	intent.putExtra("equipe2", equipes.get(listeEquipes2.getCheckedItemPosition()));
				    	intent.putExtra("lieuMatch", lieu.getText().toString());
				    	
				    	Calendar dateMatch = Calendar.getInstance();
				    	dateMatch.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
				    	Calendar dateCourante = Calendar.getInstance();
				    	
				    	if (!dateMatch.after(dateCourante)) {
				    		intent.putExtra("jourDate", date.getDayOfMonth());
				    		intent.putExtra("moisDate", date.getMonth());
				    		intent.putExtra("anneeDate", date.getYear());
					    	startActivity(intent);
				    	}
				    	
				    }
	    			return true;
	    	}
	    	else return true;
	    }
	};
	
		// listener sur le bouton précédent
		private OnTouchListener touchListenerPrecedent = new View.OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {
		    	ChoixEquipesMatch.this.finish();
		    	return true;
		    }
		};
		
		// listener sur le bouton accueil
		private OnTouchListener touchListenerAccueil = new View.OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {
		    	Intent intent = new Intent(ChoixEquipesMatch.this, MainActivity.class);
				startActivity(intent);
		    	return true;
		    }
		};
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choix_equipes_match);

	    
		listeEquipes1 = (ListView) findViewById(R.id.listEquipes1);
		listeEquipes2 = (ListView) findViewById(R.id.listEquipes2);
		boutonValider = (Button) findViewById(R.id.valider3);
		lieu = (EditText) findViewById(R.id.lieuMatch);
		date = (DatePicker) findViewById(R.id.datePicker);		
		precedent = (Button) findViewById(R.id.Precedent34);
		boutonAcceuil = (Button) findViewById(R.id.RetourAccueil);
		
		boutonValider.setOnTouchListener(touchListenerValider);
		precedent.setOnTouchListener(touchListenerPrecedent);
		boutonAcceuil.setOnTouchListener(touchListenerAccueil);

		
		equipe1Adapter = new EquipeAdapter(this, android.R.layout.simple_list_item_single_choice, equipes);
	    listeEquipes1.setAdapter(equipe1Adapter);
	    
		equipe2Adapter = new EquipeAdapter(this, android.R.layout.simple_list_item_single_choice, equipes);
	    listeEquipes2.setAdapter(equipe1Adapter);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
