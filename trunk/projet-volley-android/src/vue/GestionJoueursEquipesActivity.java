package vue;
import com.l3info.projet_volley_android.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.*;

public class GestionJoueursEquipesActivity extends Activity{
	 
	
	private OnClickListener clikSurBouton = new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent monIntent = getIntent();
				
				if (v.getId()==R.id.CreerJoueur)// bouton creation 
				{
					if (monIntent.getStringExtra("entite").equals("joueur"))
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, CreationJoueurActivity.class);
						startActivity(intent);
					}
					else
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, CreationEquipeActivity.class);
						startActivity(intent);
					}
				}
				else if (v.getId()==R.id.modifierJoueur)// bouton modification 
				{
					if (monIntent.getStringExtra("entite").equals("joueur"))
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, ConsultationModificationSuppressionJoueurActivity.class);
						intent.putExtra("mode","modification"); // transfert du type d'entite pour obtenir la fenetre correspondante ? au mode
						startActivity(intent);
					}
					else
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, ConsultationModificationSuppressionEquipeActivity.class);
						intent.putExtra("mode","modification"); // transfert du type d'entite pour obtenir la fenetre correspondante ? au mode
						startActivity(intent);
					}
				}
				else if (v.getId()==R.id.supprimerJoueur)// bouton suppression 
				{
					if (monIntent.getStringExtra("entite").equals("joueur"))
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, ConsultationModificationSuppressionJoueurActivity.class);
						intent.putExtra("mode","suppression"); // transfert du type d'entite pour obtenir la fenetre correspondante ? au mode
						startActivity(intent);
					}
					else
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, ConsultationModificationSuppressionEquipeActivity.class);
						intent.putExtra("mode","suppression"); // transfert du type d'entite pour obtenir la fenetre correspondante ? au mode
						startActivity(intent);
					}
				}
				else if (v.getId()==R.id.consulterJoueur)// bouton consultation 
				{
					if (monIntent.getStringExtra("entite").equals("joueur"))
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, ConsultationModificationSuppressionJoueurActivity.class);
						intent.putExtra("mode","consultation"); // transfert du type d'entite pour obtenir la fenetre correspondante ? au mode
						startActivity(intent);
					}
					else
					{
						Intent intent = new Intent(GestionJoueursEquipesActivity.this, ConsultationModificationSuppressionEquipeActivity.class);
						intent.putExtra("mode","consultation"); // transfert du type d'entite pour obtenir la fenetre correspondante ? au mode
						startActivity(intent);
					}
				}
				else if (v.getId()==R.id.Precedent)// bouton precedent
				{
					GestionJoueursEquipesActivity.this.finish();
				}
				else 
				{
					Intent intent = new Intent(GestionJoueursEquipesActivity.this, MainActivity.class);
					startActivity(intent);
				}
				
			}
		};
	
	TextView titre = null;
	Button creation = null;
	Button modification = null;
	Button suppression = null;
	Button consulter = null;
	Button boutonPrecedent = null;
	Button boutonAcceuil = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.gestion_des_joueurs_activity);
		
		titre = (TextView) findViewById(R.id.libGestionDesJoueurs);
		creation = (Button) findViewById(R.id.CreerJoueur);
		modification = (Button) findViewById(R.id.modifierJoueur);
		suppression = (Button) findViewById(R.id.supprimerJoueur);
		consulter = (Button) findViewById(R.id.consulterJoueur);
		boutonPrecedent = (Button) findViewById(R.id.Precedent);
		boutonAcceuil = (Button) findViewById(R.id.RetourAccueil);
		
		Intent monIntent = getIntent();
		
		if (monIntent.getStringExtra("entite").equals("joueur"))//changement des intitul?s des boutons en fonction de l'entit?
		{
			//System.out.println("joueur");
			titre.setText("Gestion des joueurs");
			creation.setText("Création d'un joueur");
			modification.setText("Modification d'un joueur");
			suppression.setText("Suppression d'un joueur");
			consulter.setText("Consultation des joueurs");
		}
		else
		{
			//System.out.println("equipe");
			titre.setText("Gestion des équipes");
			creation.setText("Création d'une équipe");
			modification.setText("Modification d'une équipe");
			suppression.setText("Suppression d'une équipe");
			consulter.setText("Consultation des équipes");
		}
		
		
		
		creation.setOnClickListener(clikSurBouton);
		modification.setOnClickListener(clikSurBouton);
		suppression.setOnClickListener(clikSurBouton);
		consulter.setOnClickListener(clikSurBouton);
		boutonPrecedent.setOnClickListener(clikSurBouton);
		boutonAcceuil.setOnClickListener(clikSurBouton);
	}

}



