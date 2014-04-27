package vue;
import com.l3info.projet_volley_android.R;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.*;

public class GestionJoueursActivity extends Activity{
	
private OnClickListener clikSurBouton = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId()==0x7f0500d9)// bouton creation d'un joueur
			{
				setContentView(R.layout.creation_joueur_activity);
			}
			else if (v.getId()==0x7f0500da)// bouton modification d'un joueur
			{
				setContentView(R.layout.modification_joueur_etape1_activity);
			}
			else if (v.getId()==0x7f0500db)// bouton suppression d'un joueur
			{
				setContentView(R.layout.suppresion_joueur_activity);
			}
			else if (v.getId()==0x7f0500dc)// bouton consultation des joueurs
			{
				setContentView(R.layout.consulter_joueur_activity);
			}
			else if (v.getId()==0x7f0500b5)// bouton precedent
			{
				
				Intent intent = new Intent(GestionJoueursActivity.this, MainActivity.class);
				startActivity(intent);
				/*
				Intent intent = getIntent();
				
				try {
					final Class<?> fen_precedent = Class.forName(intent.getStringExtra("fenetre_precedente"));
					Intent monIntent = new Intent(GestionJoueursActivity.this, fen_precedent);
					
					startActivity(monIntent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
				//setContentView(intent.getIntExtra("fenetre_precedent",-1));
			}
			else 
			{
				Intent intent = new Intent(GestionJoueursActivity.this, MainActivity.class);
				startActivity(intent);
			}
			
		}
	};
	
	Button creationJoueur = null;
	Button modificationJoueur = null;
	Button suppressionJoueur = null;
	Button consulterJoueur = null;
	Button boutonPrecedent = null;
	Button boutonAcceuil = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.gestion_des_joueurs_activity);
		
		creationJoueur = (Button) findViewById(R.id.CreerJoueur);
		modificationJoueur = (Button) findViewById(R.id.modifierJoueur);
		suppressionJoueur = (Button) findViewById(R.id.supprimerJoueur);
		consulterJoueur = (Button) findViewById(R.id.consulterJoueur);
		boutonPrecedent = (Button) findViewById(R.id.Precedent);
		boutonAcceuil = (Button) findViewById(R.id.RetourAccueil);
		
		
		creationJoueur.setOnClickListener(clikSurBouton);
		modificationJoueur.setOnClickListener(clikSurBouton);
		suppressionJoueur.setOnClickListener(clikSurBouton);
		consulterJoueur.setOnClickListener(clikSurBouton);
		boutonPrecedent.setOnClickListener(clikSurBouton);
		boutonAcceuil.setOnClickListener(clikSurBouton);
	}

}



