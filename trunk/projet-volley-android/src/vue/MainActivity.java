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
			if (v.getId()==R.id.creerMatch)// bouton creation d'un match
			{
				Intent intent = new Intent(MainActivity.this, ChoixEquipesMatch.class);
				startActivity(intent);
			}
			else if (v.getId()==R.id.creerJoueur)// bouton gestion des joueurs
			{
				Intent intent = new Intent(MainActivity.this, GestionJoueursEquipesActivity.class);
				intent.putExtra("entite","joueur"); // transfert du type d'entite pour obtenir la fenetre correspondante à l'entite
				startActivity(intent);
			}
			else if (v.getId()==R.id.creerEquipe)// bouton gestion des équipes
			{
				Intent intent = new Intent(MainActivity.this, GestionJoueursEquipesActivity.class);
				intent.putExtra("entite","equipe"); // transfert du type d'entite pour obtenir la fenetre correspondante à l'entite
				startActivity(intent);
			}
			else if(v.getId()==R.id.statsJoueurs){
				Intent intent = new Intent(MainActivity.this, StatsJoueurs.class);
				startActivity(intent);
			}
			else // bouton liste des matchs
			{
				//affichage de la vue pour lister les matchs
			}
			
		}
	};
	
	
	Button creationMatch = null;
	Button gestionJoueur = null;
	Button gestionEquipe = null;
	Button listeMatch = null;
	Button statsJoueurs = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.accueil_activity);
		
		Controleur c = Controleur.getInstance();
		c.initialiseBdd(this);
		c.testBdd();
		
		creationMatch = (Button) findViewById(R.id.creerMatch);
		gestionJoueur = (Button) findViewById(R.id.creerJoueur);
		gestionEquipe = (Button) findViewById(R.id.creerEquipe);
		//listeMatch = (Button) findViewById(R.id.listeMatchs);
		statsJoueurs = (Button) findViewById(R.id.statsJoueurs);
		
		creationMatch.setOnClickListener(clikSurBouton);
		gestionJoueur.setOnClickListener(clikSurBouton);
		gestionEquipe.setOnClickListener(clikSurBouton);
		//listeMatch.setOnClickListener(clikSurBouton);
		statsJoueurs.setOnClickListener(clikSurBouton);
	}

}
