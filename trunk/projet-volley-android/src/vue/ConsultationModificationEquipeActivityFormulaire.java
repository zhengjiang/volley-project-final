package vue;

import java.util.ArrayList;

import modele.Equipe;
import modele.InitialisationModele;
import modele.Joueur;
import modele.JoueurEquipe;

import com.l3info.projet_volley_android.R;
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
					ConsultationModificationEquipeActivityFormulaire.this.finish();
					dialog.cancel();
				}
			});
			
			if(v.getId()==R.id.ValidationModifieEquipe)//  button  modifier   equipe  
			{
				EditText nomEquipe = (EditText) findViewById(R.id.nomEquipe);
				EditText nomEntraineur = (EditText) findViewById(R.id.nomEntraineur);
							
			    if ( (nomEquipe.getText().toString().length()>0) && (nomEntraineur.getText().toString().length()>0) )
				{
					Equipe monEquipe=new Equipe(0,nomEquipe.getText().toString(),nomEntraineur.getText().toString());
				
					if(monEquipe.nomEstValide())
					{
						if(monEquipe.nomEntraineurEstValide())
						{
							// modif de l'equipe  a  la  base  
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
							nomEntraineur.setText("");
						}
																	
					}	
					else
					{
						messErreur.setMessage("nom est invalide");
						AlertDialog alertErreur = messErreur.create();
						alertErreur.show();
						nomEquipe.setText("");
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
	
	// coté modification
	EditText nomEquipeEnModification=null;
	EditText nomEntraineurEnModification=null;
	Button modifier = null;
	
	// coté consultation
	TextView nomEquipeConsultation = null;
	TextView nomEntraineurConsultation = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		Intent monIntent = getIntent();
		
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
