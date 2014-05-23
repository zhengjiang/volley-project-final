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

public class CreationJoueurActivity extends Activity{
	
	//initialisation de "equipes" avec la BD
	private ArrayList<Equipe> equipes=InitialisationModele.initEquipes(); // permet de sauvegarder les ï¿½quipes de la BD 

	private ArrayList<String> listeNomEquipe; // liste des noms équipes
	
	private OnClickListener clikSurBouton = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			//Construction des pop-ups (erreur et confirmation)
			AlertDialog.Builder messErreur = new AlertDialog.Builder(CreationJoueurActivity.this);
			messErreur.setTitle("Erreur");
			messErreur.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			});
			
			AlertDialog.Builder messConfirmation = new AlertDialog.Builder(CreationJoueurActivity.this);
			messConfirmation.setTitle("");
			messConfirmation.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					CreationJoueurActivity.this.finish();
					dialog.cancel();
				}
			});
			
			if (v.getId()==R.id.ValidationCreationJoueur) // bouton valider creation joueur
			{
				EditText nomJoueur = (EditText) findViewById(R.id.nomJoueur);
				EditText tailleJoueur = (EditText) findViewById(R.id.tailleJoueur);
				EditText ageJoueur = (EditText) findViewById(R.id.AgeJoueur);
				EditText posteEnCoursJoueur = (EditText) findViewById(R.id.Poste_en_courJoueur);
				
				//Verif + Ajout du joueur dans la BD
				
				// verification des attributs
				if ( (tailleJoueur.getText().toString().length()>0) && (numMaillotJoueur.getText().toString().length()>0) && (ageJoueur.getText().toString().length()>0) && (posteEnCoursJoueur.getText().toString().length()>0) )
				{
					Joueur monJoueur = new Joueur(0,nomJoueur.getText().toString(),Integer.parseInt(tailleJoueur.getText().toString()),Integer.parseInt(ageJoueur.getText().toString()),Integer.parseInt(posteEnCoursJoueur.getText().toString()));
					JoueurEquipe monJoueurEquipe = new JoueurEquipe(0,monJoueur,null,Integer.parseInt(numMaillotJoueur.getText().toString()),true);
				
					if (monJoueur.nomEstValide())
					{
						if(monJoueur.ageEstValide())
						{
							if(monJoueur.tailleEstValide())
							{
								if(monJoueur.posteEstValide())
								{
									if (equipes.get(listeEquipe.getCheckedItemPosition())!=null)
									{
										if(monJoueurEquipe.numMaillotEstValide())
										{
											monJoueurEquipe.setEquipe(equipes.get(listeEquipe.getCheckedItemPosition()));
		
											messConfirmation.setMessage("Joueur ajouté");
											AlertDialog alertConfirmation = messConfirmation.create();
											alertConfirmation.show();
											
										}
										else
										{
											messErreur.setMessage("n° maillot est invalide");
											AlertDialog alertErreur = messErreur.create();
											alertErreur.show();
											numMaillotJoueur.setText("");
										}
									}
									else
									{
										monJoueurEquipe.setEquipe(equipes.get(listeEquipe.getCheckedItemPosition()));
										
										messConfirmation.setMessage("Joueur ajouté");
										AlertDialog alertConfirmation = messConfirmation.create();
										alertConfirmation.show();
									}
									
								}
								else
								{
									messErreur.setMessage("poste est invalide");
									AlertDialog alertErreur = messErreur.create();
									alertErreur.show();
									posteEnCoursJoueur.setText("");
								}
							}
							else
							{
								messErreur.setMessage("taille est invalide");
								AlertDialog alertErreur = messErreur.create();
								alertErreur.show();
								tailleJoueur.setText("");
							}
						}
						else
						{
							messErreur.setMessage("age est invalide");
							AlertDialog alertErreur = messErreur.create();
							alertErreur.show();
							ageJoueur.setText("");
						}
					}
					else
					{
						messErreur.setMessage("nom est invalide");
						AlertDialog alertErreur = messErreur.create();
						alertErreur.show();
						nomJoueur.setText("");
					}
				}
				else
				{
					messErreur.setMessage("Veuillez remplir les champs vides");
					AlertDialog alertErreur = messErreur.create();
					alertErreur.show();
				}
			}
			else if (v.getId()==R.id.Precedent)// bouton precedent
			{
				CreationJoueurActivity.this.finish();
			}
			else 
			{
				Intent intent = new Intent(CreationJoueurActivity.this, MainActivity.class);
				startActivity(intent);
			}
			
			
		}
	};
	
	private OnItemClickListener clickSurItemListe = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			
			if (parent.getId()==R.id.listeEquipes) // clique sur un item de la liste des ï¿½quipes			
			{
				if (equipes.get(listeEquipe.getCheckedItemPosition())!=null)
				{
					numMaillotJoueur.setVisibility(View.VISIBLE);
				}
				else
				{
					numMaillotJoueur.setVisibility(View.INVISIBLE);
				}
			}
			
		}
		
	};
	
	Button precedent = null;
	Button accueil = null;
	Button valider = null;
	ListView listeEquipe = null;
	EditText numMaillotJoueur = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.creation_joueur_activity);
		
		// recuperation des elements de la fenetre
		precedent = (Button) findViewById(R.id.Precedent);
		accueil = (Button) findViewById(R.id.RetourAccueil);
		valider = (Button) findViewById(R.id.ValidationCreationJoueur);
		listeEquipe = (ListView) findViewById(R.id.listeEquipes);
		numMaillotJoueur = (EditText) findViewById(R.id.numMaillot);
		
		// initialisation du contenu de chaque item prï¿½sent dans la liste
		ArrayList<String> listeNomEquipe = new ArrayList<String>();
		
		equipes.add(0,null);
		listeNomEquipe.add("Aucune");
		for (int i=0; i<equipes.size();i++)
		{
			listeNomEquipe.add(equipes.get(i).getNom());
		}
		
		numMaillotJoueur.setVisibility(View.INVISIBLE);
		
		// creation et initialisation de la liste des ï¿½quipes 
		listeEquipe.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,listeNomEquipe));
		// selection du premier element
		listeEquipe.setItemChecked(0,true);
		
		//gestion des actions issue du clique en fonction des boutons 
		listeEquipe.setOnItemClickListener(clickSurItemListe);
		precedent.setOnClickListener(clikSurBouton);
		accueil.setOnClickListener(clikSurBouton);
		valider.setOnClickListener(clikSurBouton);
	}


}
