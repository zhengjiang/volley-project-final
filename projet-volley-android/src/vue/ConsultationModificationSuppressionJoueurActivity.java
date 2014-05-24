package vue;
import modele.*;

import com.l3info.projet_volley_android.R;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.*;
import controleur.*;

public class ConsultationModificationSuppressionJoueurActivity extends Activity{
	
	private Controleur ctl = Controleur.getInstance(); //acces à la BD
	
	//initialisation de "equipes" et "joueurs" avec la BD
	private List<Equipe> equipes; // permet de sauvegarder les ?quipes de la BD 
	private List<JoueurEquipe> joueurEquipe;  // permet de sauvegarder d'obtenir les infos des joueurs en fonction de l'équipe (numMaillot)
	private List<Joueur> joueurs; // liste des joueurs d'une equipe
	private ArrayList<Integer> numMaillotJoueurs; // Liste des numeros de maillot des joueurs d'une ?quipe
	
	private ArrayList<String> listeNomJoueur; // Liste des noms des joueurs à sauvegarder dans le array Adapter de la listeView
	private ArrayAdapter<String> listeJoueurAdapter; // array Adapter servant de support de données à la listView des noms des joueurs
	
	private OnClickListener clikSurBouton = new View.OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if (v.getId()==R.id.Precedent)// bouton precedent
			{
				ConsultationModificationSuppressionJoueurActivity.this.finish();
			}
			else 
			{
				Intent intent = new Intent(ConsultationModificationSuppressionJoueurActivity.this, MainActivity.class);
				startActivity(intent);
			}
			
		}
	};
	
	private OnItemClickListener clickSurItemListe = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			
			if (parent.getId()==R.id.listeEquipes) // clique sur un item de la liste des ?quipes			
			{
				
				// initialisation du contenu de la liste des joueurs en fonction de l'?quipe selectionn?
				ctl.jb.open();
				joueurs = ctl.jb.joueurEquipe(equipes.get(position).getId());
				ctl.jb.close();
				
				ctl.jeb.open();
				if (equipes.get(position).getId()!=-1)
				{
					joueurEquipe = ctl.jeb.selectionnerJoueursEquipes(equipes.get(position).getId());
				}
				else
				{
					joueurEquipe = ctl.jeb.selectionnerJoueursSansEquipe(equipes.get(position).getId());
				}
				
				ctl.jeb.close();
				
				
				// initialisation du contenu de chaque item pr?sent dans la liste
				listeNomJoueur = new ArrayList<String>();
				
				for (int i=0; i<joueurs.size();i++)
				{
					listeNomJoueur.add(joueurs.get(i).getNom()+" - n° maillot : "+joueurEquipe.get(i).getNumMaillot());
				}
				
				// creation et initialisation de la liste des joueurs
				listeJoueurAdapter = new ArrayAdapter<String>(ConsultationModificationSuppressionJoueurActivity.this,android.R.layout.simple_list_item_single_choice,listeNomJoueur);
				listeJoueur.setAdapter(listeJoueurAdapter);
				
			}
			else // clique sur un item de la liste des joueurs	
			{
				Intent monIntent = getIntent();
				
				Intent intent = new Intent(ConsultationModificationSuppressionJoueurActivity.this, ConsultationModificationJoueurActivityFormulaire.class);
				
				intent.putExtra("id",joueurs.get(listeJoueur.getCheckedItemPosition()).getId());
				intent.putExtra("nom",joueurs.get(listeJoueur.getCheckedItemPosition()).getNom());
				intent.putExtra("age",joueurs.get(listeJoueur.getCheckedItemPosition()).getAge());
				intent.putExtra("taille",joueurs.get(listeJoueur.getCheckedItemPosition()).getTaille());
				intent.putExtra("poste",joueurs.get(listeJoueur.getCheckedItemPosition()).getPosteEnCours());
				intent.putExtra("numMaillot",joueurEquipe.get(listeJoueur.getCheckedItemPosition()).getNumMaillot());
				
				intent.putExtra("idJoueurEquipe",joueurEquipe.get(listeJoueur.getCheckedItemPosition()).getId());
				
				intent.putExtra("idEquipe",equipes.get(listeEquipe.getCheckedItemPosition()).getId());
				intent.putExtra("nomEquipe",equipes.get(listeEquipe.getCheckedItemPosition()).getNom());
				

				if (monIntent.getStringExtra("mode").equals("consultation"))
				{
					intent.putExtra("mode","consultation");	
					startActivity(intent);
					finish();
				}
				else if (monIntent.getStringExtra("mode").equals("modification"))
				{
					intent.putExtra("mode","modification");
					startActivity(intent);
					finish();
				}
				else
				{
					AlertDialog.Builder messConfirmation = new AlertDialog.Builder(ConsultationModificationSuppressionJoueurActivity.this);
					messConfirmation.setTitle("");
					messConfirmation.setMessage("Voulez-vous vraiment supprimer ce joueur ?");
					messConfirmation.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							ctl.jb.open();
							ctl.jb.supprimer(joueurs.get(listeJoueur.getCheckedItemPosition()).getId());
							ctl.jb.close();
							
							ctl.jeb.open();
							ctl.jeb.supprimer(joueurs.get(listeJoueur.getCheckedItemPosition()).getId());
							ctl.jeb.close();
							
							listeNomJoueur.remove(listeJoueur.getCheckedItemPosition());
							listeJoueurAdapter.notifyDataSetChanged();
							dialog.cancel();
						}
					});
					messConfirmation.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
					
					AlertDialog alertConfirmation = messConfirmation.create();
					alertConfirmation.show();
					
				}
				
				
				
			}
			
			
		}
		
	};
	
	
	TextView titre = null;
	Button boutonPrecedent = null;
	Button boutonAcceuil = null;
	ListView listeEquipe = null;
	ListView listeJoueur = null;
	
	
	public void onCreate(Bundle savedInstanceState)
	{
		ctl.initialiseBdd(ConsultationModificationSuppressionJoueurActivity.this);
		
		ctl.eb.open();
		
		equipes = ctl.eb.selectionnerTout();
		
		ctl.eb.close();
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.modification_joueur_etape1_activity);
		
		titre = (TextView) findViewById(R.id.libModificationJoueur);
		boutonPrecedent = (Button) findViewById(R.id.Precedent);
		boutonAcceuil = (Button) findViewById(R.id.RetourAccueil);
		listeEquipe = (ListView) findViewById(R.id.listeEquipes);
		listeJoueur = (ListView) findViewById(R.id.listeJoueurs);
		
		Intent monIntent = getIntent();
		if (monIntent.getStringExtra("mode").equals("consultation"))//changement des intitul?s des boutons en fonction du mode
		{
			titre.setText("Consultation d'un joueur");
			
		}
		else if (monIntent.getStringExtra("mode").equals("modification"))
		{
			titre.setText("Modification d'un joueur");
			
		}
		else
		{
			titre.setText("Suppression d'un joueur");
		}
		
		
		// initialisation du contenu de chaque item pr?sent dans la liste
		ArrayList<String> listeNomEquipe = new ArrayList<String>();
		
		for (int i=0; i<equipes.size();i++)
		{
			listeNomEquipe.add(equipes.get(i).getNom());
		}
		equipes.add(new Equipe(-1,"Sans club",""));
		listeNomEquipe.add("Sans club");
		
		// creation et initialisation de la liste des ?quipes 
		listeEquipe.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,listeNomEquipe));
		
		
		boutonPrecedent.setOnClickListener(clikSurBouton);
		boutonAcceuil.setOnClickListener(clikSurBouton);
		listeEquipe.setOnItemClickListener(clickSurItemListe);
		listeJoueur.setOnItemClickListener(clickSurItemListe);
	}

}
