package vue;

import java.util.ArrayList;
import java.util.List;

import modele.*;

import com.l3info.projet_volley_android.R;

import controleur.Controleur;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class StatsJoueurs extends Activity {
	
	private Spinner dropdown;
	private Button btnSubmit;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_joueurs);
    	 
    	addItemsOnSpinner2();
    	addListenerOnButton();
    	addListenerOnSpinnerItemSelection();
	}
	

	public void addItemsOnSpinner2() {
		 
		Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
		Controleur c = Controleur.getInstance();
    	c.jb.open();
    	List<Joueur> listeJ = c.jb.selectionnerTout();
    	c.jb.close();
		List<String> list = new ArrayList<String>();
    	for(int i  = 0; i < listeJ.size(); i++){
        	list.add(listeJ.get(i).getNom());
        	
        }
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dropdown.setAdapter(dataAdapter);
	  }
	 
	  public void addListenerOnSpinnerItemSelection() {
		dropdown = (Spinner) findViewById(R.id.spinner1);
		dropdown.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	  }

	  public void addListenerOnButton() {
	 
		dropdown = (Spinner) findViewById(R.id.spinner1);
		btnSubmit = (Button) findViewById(R.id.button1);
	 
		btnSubmit.setOnClickListener(new OnClickListener() {
		  @Override
		  public void onClick(View v) {
			  int pos = dropdown.getSelectedItemPosition();
			  Controleur c = Controleur.getInstance();
			  c.jb.open();
			  List<Joueur> listeJ = c.jb.selectionnerTout();
			  c.jb.close();
			  c.jab.open();
			  Statistique s = c.jab.getStats(listeJ.get(pos).getId());
			  c.jab.close();
			  
			  TextView totse = (TextView)findViewById(R.id.totse);
			  TextView avgse = (TextView)findViewById(R.id.avgse);
			  TextView totre = (TextView)findViewById(R.id.totre);
			  TextView avgre = (TextView)findViewById(R.id.avgre);
			  TextView totbl = (TextView)findViewById(R.id.totbl);
			  TextView avgbl = (TextView)findViewById(R.id.avgbl);
			  TextView totpa = (TextView)findViewById(R.id.totpa);
			  TextView avgpa = (TextView)findViewById(R.id.avgpa);
			  TextView totat = (TextView)findViewById(R.id.totat);
			  TextView avgat = (TextView)findViewById(R.id.avgat);
			  TextView totde = (TextView)findViewById(R.id.totde);
			  TextView avgde = (TextView)findViewById(R.id.avgde);
			  
			  totse.setText(""+0);
			  avgse.setText(""+0);
			  totre.setText(""+0);
			  avgre.setText(""+0);
			  totbl.setText(""+0);
			  avgbl.setText(""+0);
			  totpa.setText(""+0);
			  avgpa.setText(""+0);
			  totat.setText(""+0);
			  avgat.setText(""+0);
			  totde.setText(""+0);
			  avgde.setText(""+0);
			  
			  if(s.getStats().containsKey("se")){
				  float[] nb = (float[])s.getStats().get("se");
				  totse.setText(""+nb[0]);
				  avgse.setText(""+nb[1]);
			  }
			  
			  if(s.getStats().containsKey("re")){
				  float[] nb = (float[])s.getStats().get("re");
				  totre.setText(""+nb[0]);
				  avgre.setText(""+nb[1]);
			  }
			  
			  if(s.getStats().containsKey("bl")){
				  float[] nb = (float[])s.getStats().get("bl");
				  totbl.setText(""+nb[0]);
				  avgbl.setText(""+nb[1]);
			  }
			  
			  if(s.getStats().containsKey("pa")){
				  float[] nb = (float[])s.getStats().get("pa");
				  totpa.setText(""+nb[0]);
				  avgpa.setText(""+nb[1]);
			  }
			  if(s.getStats().containsKey("at")){
				  float[] nb = (float[])s.getStats().get("at");
				  totat.setText(""+nb[0]);
				  avgat.setText(""+nb[1]);
			  }
			  if(s.getStats().containsKey("de")){
				  float[] nb = (float[])s.getStats().get("de");
				  totde.setText(""+nb[0]);
				  avgde.setText(""+nb[1]);
			  }
		  }
		});
	  }
	
	
}


