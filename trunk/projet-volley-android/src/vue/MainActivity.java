package vue;
import com.l3info.projet_volley_android.R;

import controleur.Controleur;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.*;

public class MainActivity extends Activity{
	
	private OnClickListener clikSurBouton = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId()==0x7f05003c)// bouton creation d'un match
			{
				Intent intent = new Intent(MainActivity.this, SaisieStatsActivity.class);
				startActivity(intent);
				//affichage de la vue pour la creation d'un match EDIT WARREN : Pour l'instant, lancera directement une simumlation de déroulement de match avec equipes prédéfinies
			}
			else if (v.getId()==0x7f05003d)// bouton gestion des joueurs
			{
				Intent intent = new Intent(MainActivity.this, GestionJoueursActivity.class);
				intent.putExtra("fenetre_precedente",R.layout.accueil_activity); // transfert de l'id de la fenetre en cours pour le bouton precedent
				startActivity(intent);
			}
			else if (v.getId()==0x7f05003e)// bouton gestion des équipes
			{
				Intent intent = new Intent(MainActivity.this, GestionEquipesActivity.class);
				intent.putExtra("fenetre_precedente","vue.MainActivity"); // transfert de l'id de la fenetre en cours pour le bouton precedent
				startActivity(intent);
			}
			else // bouton liste des matchs
			{
				System.out.println("4");
				//affichage de la vue pour lister les matchs
			}
			
		}
	};
	
	Button creationMatch = null;
	Button gestionJoueur = null;
	Button gestionEquipe = null;
	Button listeMatch = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.accueil_activity);
		
		Controleur c = Controleur.getInstance();
		/*c.initialiseBdd(this);
		c.prout();*/
		
		creationMatch = (Button) findViewById(R.id.creerMatch);
		gestionJoueur = (Button) findViewById(R.id.creerJoueur);
		gestionEquipe = (Button) findViewById(R.id.creerEquipe);
		listeMatch = (Button) findViewById(R.id.listeMatchs);
		
		creationMatch.setOnClickListener(clikSurBouton);
		gestionJoueur.setOnClickListener(clikSurBouton);
		gestionEquipe.setOnClickListener(clikSurBouton);
		listeMatch.setOnClickListener(clikSurBouton);
	}

}
