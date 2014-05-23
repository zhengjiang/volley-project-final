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
	
	private OnTouchListener touchListenerValider = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (equipes.get(listeEquipes1.getCheckedItemPosition()).getId() !=
	    			equipes.get(listeEquipes2.getCheckedItemPosition()).getId()) {
	    			if (lieu.getText().toString().trim().length() != 0) {
				    	Intent intent = new Intent(ChoixEquipesMatch.this, ChoixJoueursMatch.class);
				    	intent.putExtra("idEquipe1", equipes.get(listeEquipes1.getCheckedItemPosition()).getId());
				    	intent.putExtra("idEquipe2", equipes.get(listeEquipes2.getCheckedItemPosition()).getId());
				    	intent.putExtra("nomEquipe1", equipes.get(listeEquipes1.getCheckedItemPosition()).getNom());
				    	intent.putExtra("nomEquipe2", equipes.get(listeEquipes2.getCheckedItemPosition()).getNom());
				    	intent.putExtra("lieuMatch", lieu.getText());
				    	
				    	Calendar dateMatch = Calendar.getInstance();
				    	dateMatch.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
				    	Calendar dateCourante = Calendar.getInstance();
				    	
				    	if (!dateMatch.after(dateCourante)) {
				    		intent.putExtra("dateMatch", dateMatch.getTime());
					    	startActivity(intent);
				    	}
				    	
				    }
	    			return true;
	    	}
	    	else return true;
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
		
		ArrayList<String> listeNomEquipe = new ArrayList<String>();
		for (int i=0; i<equipes.size();i++)
		{
			listeNomEquipe.add(equipes.get(i).getNom());
		}
		
		listeEquipes1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,
				listeNomEquipe));		
		listeEquipes2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,
				listeNomEquipe));
		
		boutonValider.setOnTouchListener(touchListenerValider);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
