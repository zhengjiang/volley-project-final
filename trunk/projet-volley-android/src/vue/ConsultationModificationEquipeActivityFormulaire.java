package vue;

import java.util.ArrayList;

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

public class ConsultationModificationEquipeActivityFormulaire extends Activity{
	
private OnClickListener clikSurBouton = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			//Construction des pop-ups (erreur et confirmation)
			AlertDialog.Builder messErreur = new AlertDialog.Builder(ConsultationModificationEquipeActivityFormulaire.this);
			messErreur.setTitle("Erreur");
			messErreur.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			});
	
			AlertDialog.Builder messConfirmation = new AlertDialog.Builder(ConsultationModificationEquipeActivityFormulaire.this);
			messConfirmation.setTitle("");
			messConfirmation.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					Intent intent = new Intent(ConsultationModificationEquipeActivityFormulaire.this, ConsultationModificationSuppressionEquipeActivity.class);
					intent.putExtra("mode","modification");
					startActivity(intent);
					ConsultationModificationEquipeActivityFormulaire.this.finish();
					dialog.cancel();
				}
			});
			
			if(v.getId()==R.id.ValidationModifieEquipe)//  button  modifier   equipe  
			{
							
			    if ( (nomEquipeEnModification.getText().toString().length()>0) && (nomEntraineurEnModification.getText().toString().length()>0) )
				{
					Equipe monEquipe=new Equipe(monIntent.getIntExtra("idEquipe",-1),nomEquipeEnModification.getText().toString(),nomEntraineurEnModification.getText().toString());
				
					if(monEquipe.nomEstValide())
					{
						if(monEquipe.nomEntraineurEstValide())
						{
							Controleur ctl = Controleur.getInstance();
							ctl.initialiseBdd(ConsultationModificationEquipeActivityFormulaire.this);
							
							ctl.eb.open();
							ctl.eb.modifier(monEquipe);
							ctl.eb.close();
							
							messConfirmation.setTitle("");
							messConfirmation.setMessage("Equipe modifié");
							AlertDialog alertErreur = messConfirmation.create();
							alertErreur.show();
									
									
						}
						else
						{
							messErreur.setMessage("nom de l'entraineur est invalide");
							AlertDialog alertErreur = messErreur.create();
							alertErreur.show();
							nomEntraineurEnModification.setText("");
						}
																	
					}	
					else
					{
						messErreur.setMessage("nom est invalide");
						AlertDialog alertErreur = messErreur.create();
						alertErreur.show();
						nomEquipeEnModification.setText("");
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
				
				Intent intent = new Intent(ConsultationModificationEquipeActivityFormulaire.this, ConsultationModificationSuppressionEquipeActivity.class);
				if (monIntent.getStringExtra("mode").equals("consultation"))
				{
					intent.putExtra("mode","consultation");	
				}
				else if (monIntent.getStringExtra("mode").equals("modification"))
				{
					intent.putExtra("mode","modification");
				}
				startActivity(intent);
				ConsultationModificationEquipeActivityFormulaire.this.finish();
			}
			else 
			{
				Intent intent = new Intent(ConsultationModificationEquipeActivityFormulaire.this, MainActivity.class);
				startActivity(intent);
			}
			
			
		}
	};
	
	
	TextView titre=null;
	Button precedent = null;
	Button accueil = null;
	
	// cot? modification
	EditText nomEquipeEnModification=null;
	EditText nomEntraineurEnModification=null;
	Button modifier = null;
	
	// cot? consultation
	TextView nomEquipeConsultation = null;
	TextView nomEntraineurConsultation = null;
	Intent monIntent;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		monIntent = getIntent();
		
		if (monIntent.getStringExtra("mode").equals("consultation"))//chargement des elements de la fenetre en fonction du mode
		{
			setContentView(R.layout.consulter_equipe_1_activity);
			
			nomEquipeConsultation = (TextView) findViewById(R.id.libNonEquipe);
			nomEntraineurConsultation = (TextView) findViewById(R.id.libNonEntrainneurEquipe);
			titre=(TextView) findViewById(R.id.libConsultationEquipe);
			
			titre.setText("Consultation d'une équipe");
			nomEquipeConsultation.setText(nomEquipeConsultation.getText()+"   "+monIntent.getStringExtra("nomEquipe"));
			nomEntraineurConsultation.setText(nomEntraineurConsultation.getText()+"   "+monIntent.getStringExtra("nomEntraineurEquipe"));
		}
		else
		{
			setContentView(R.layout.modification_equipe_etape2_activity);
			
			modifier=(Button) findViewById(R.id.ValidationModifieEquipe);
			titre=(TextView) findViewById(R.id.ModificationEquipe);
			nomEquipeEnModification=(EditText) findViewById(R.id.nomEquipe);
			nomEntraineurEnModification=(EditText) findViewById(R.id.nomdelentraineur);
			
			

			titre.setText("Modification d'une équipe");
			nomEquipeEnModification.setText(monIntent.getStringExtra("nomEquipe"));
			nomEntraineurEnModification.setText(monIntent.getStringExtra("nomEntraineurEquipe"));
			
			
			modifier.setOnClickListener(clikSurBouton);
		}
		
		precedent = (Button) findViewById(R.id.Precedent);
		accueil = (Button) findViewById(R.id.RetourAccueil);
		
		//gestion des actions issue du clique en fonction des boutons 
		precedent.setOnClickListener(clikSurBouton);
		accueil.setOnClickListener(clikSurBouton);
		
	}

}
