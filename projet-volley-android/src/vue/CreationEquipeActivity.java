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

import java.util.*;
import controleur.*;

public class CreationEquipeActivity  extends Activity{
	
	private OnClickListener clikSurBouton = new View.OnClickListener() {	
		public void onClick(View v) {
			
			//Construction des pop-ups (erreur et confirmation)
			AlertDialog.Builder messErreur = new AlertDialog.Builder(CreationEquipeActivity.this);
			messErreur.setTitle("Erreur");
			messErreur.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			});
	
			AlertDialog.Builder messConfirmation = new AlertDialog.Builder(CreationEquipeActivity.this);
			messConfirmation.setTitle("");
			messConfirmation.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					CreationEquipeActivity.this.finish();
					dialog.cancel();
				}
			});
			
			if(v.getId()==R.id.ValidationCreationEquipe)//  button  valider   equipe  
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
							Controleur ctl = Controleur.getInstance();
							ctl.initialiseBdd(CreationEquipeActivity.this);
							
							ctl.eb.open();
							ctl.eb.ajouter(monEquipe);
							ctl.eb.close();
							
							messConfirmation.setTitle("");
							messConfirmation.setMessage("Equipe ajouté");
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
				CreationEquipeActivity.this.finish();
			}
			else 
			{
				Intent intent = new Intent(CreationEquipeActivity.this, MainActivity.class);
				startActivity(intent);
			}
		}
	};


	Button precedent = null;
	Button accueil = null;
	Button valider = null;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creation_equipe_activity);
		
		// recuperation des elements de la fenetre
		precedent = (Button) findViewById(R.id.Precedent);
		accueil = (Button) findViewById(R.id.RetourAccueil);
		valider = (Button) findViewById(R.id.ValidationCreationEquipe);
		
		precedent.setOnClickListener(clikSurBouton);
		accueil.setOnClickListener(clikSurBouton);
		valider.setOnClickListener(clikSurBouton);
	}
	
}
	