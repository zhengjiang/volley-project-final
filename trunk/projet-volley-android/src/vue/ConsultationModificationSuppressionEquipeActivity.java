package vue;
import java.util.ArrayList;
import java.util.List;

import modele.Equipe;
import modele.InitialisationModele;
import modele.Joueur;
import modele.JoueurEquipe;

import com.l3info.projet_volley_android.R;

import controleur.Controleur;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ConsultationModificationSuppressionEquipeActivity extends Activity {
	
	private Controleur ctl = Controleur.getInstance(); //acces à la BD

	private List<Equipe> equipes; // permet de sauvegarder les ?quipes de la BD 

	private ArrayList<String> listeNomEquipe; // Liste des noms des ?quipes à sauvegarder dans le array Adapter de la listeView
	private ArrayAdapter<String> listeEquipeAdapter; // array Adapter servant de support de données à la listView des noms des ?quipes
	
	private OnClickListener clikSurBouton = new View.OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent monIntent = getIntent();
			
			if (v.getId()==R.id.Precedent)// bouton precedent
			{
				ConsultationModificationSuppressionEquipeActivity.this.finish();
			}
			else // bouton acceuil
			{
				Intent intent = new Intent(ConsultationModificationSuppressionEquipeActivity.this, MainActivity.class);
				startActivity(intent);
			}
			
		}
	};
	
	private OnItemClickListener clickSurItemListe = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			
			if (parent.getId()==R.id.listView1) // clique sur un item de la liste des ?quipes			
			{
				
				Intent monIntent = getIntent();
				
				Intent intent = new Intent(ConsultationModificationSuppressionEquipeActivity.this, ConsultationModificationEquipeActivityFormulaire.class);
				
				
				intent.putExtra("idEquipe",equipes.get(listeEquipe.getCheckedItemPosition()).getId());
				intent.putExtra("nomEquipe",equipes.get(listeEquipe.getCheckedItemPosition()).getNom());
				intent.putExtra("nomEntraineurEquipe",equipes.get(listeEquipe.getCheckedItemPosition()).getEntraineur());
				
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
					AlertDialog.Builder messConfirmation = new AlertDialog.Builder(ConsultationModificationSuppressionEquipeActivity.this);
					messConfirmation.setTitle("");
					messConfirmation.setMessage("Voulez-vous vraiment supprimer cette équipe ?");
					messConfirmation.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							ctl.eb.open();
							ctl.eb.supprimer(equipes.get(listeEquipe.getCheckedItemPosition()).getId());
							ctl.eb.close();
							
							ctl.jeb.open();
							List<JoueurEquipe> joueursEquipe = ctl.jeb.selectionnerJoueursEquipes(equipes.get(listeEquipe.getCheckedItemPosition()).getId());
							
							
							for (int i=0;i<joueursEquipe.size();i++)
							{
								joueursEquipe.get(i).getEquipe().setId(-1);
								joueursEquipe.get(i).setNumMaillot(-1);
								ctl.jeb.modifier(joueursEquipe.get(i));
							}
							
							
							ctl.jeb.close();
							
							listeNomEquipe.remove(listeEquipe.getCheckedItemPosition());
							listeEquipeAdapter.notifyDataSetChanged();
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

	
	public void onCreate(Bundle savedInstanceState)
	{
		ctl.initialiseBdd(ConsultationModificationSuppressionEquipeActivity.this);
		
		ctl.eb.open();
		
		equipes = ctl.eb.selectionnerTout();
		
		ctl.eb.close();
		
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.modification_equipe_etape1_activity);
		
		titre = (TextView) findViewById(R.id.libModificationEquipe);
		boutonPrecedent = (Button) findViewById(R.id.Precedent);
		boutonAcceuil = (Button) findViewById(R.id.RetourAccueil);
		listeEquipe = (ListView) findViewById(R.id.listView1);
		
		Intent monIntent = getIntent();
		if (monIntent.getStringExtra("mode").equals("consultation"))//changement des intitul?s des boutons en fonction du mode
		{
			titre.setText("Consultation d'une ?quipe");
			
		}
		else if (monIntent.getStringExtra("mode").equals("modification"))
		{
			titre.setText("Modification d'une équipe");
			
		}
		else
		{
			titre.setText("Suppression d'une équipe");
		}
		
		
		// initialisation du contenu de chaque item pr?sent dans la liste
		listeNomEquipe = new ArrayList<String>();
		
		for (int i=0; i<equipes.size();i++)
		{
			listeNomEquipe.add(equipes.get(i).getNom());
		}
		
		// creation et initialisation de la liste des ?quipes 
		listeEquipeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,listeNomEquipe);
		listeEquipe.setAdapter(listeEquipeAdapter);
		
		
		boutonPrecedent.setOnClickListener(clikSurBouton);
		boutonAcceuil.setOnClickListener(clikSurBouton);
		listeEquipe.setOnItemClickListener(clickSurItemListe);
	}

}
