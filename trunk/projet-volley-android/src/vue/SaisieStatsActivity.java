/**
 * \file      SaisieStatsActivity.java
 * \author    Jérémy Pastor
 * \date      30 mars 2014
 * \brief     Permet la gestion de l'interface graphique lors de la saisie de statistiques
 * \details   Gestion des évenements sur les boutons, Gestion des remplacements etc.
 */

package vue;


import java.util.ArrayList;

import com.l3info.projet_volley_android.R;

import controleur.Controleur;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SaisieStatsActivity extends Activity {

	// initialisation des maillots
	RadioButton maillot_rouge1 = null;
	RadioButton maillot_rouge2 = null;
	RadioButton maillot_rouge3 = null;
	RadioButton maillot_rouge4 = null;
	RadioButton maillot_rouge5 = null;
	RadioButton maillot_rouge6 = null;
	RadioButton maillot_rouge7 = null;
	RadioButton maillot_rouge8 = null;
	RadioButton maillot_rouge9 = null;
	RadioButton maillot_rouge10 = null;
	RadioButton maillot_rouge11 = null;
	RadioButton maillot_rouge12 = null;
	RadioButton maillot_bleu1 = null;
	RadioButton maillot_bleu2 = null;
	RadioButton maillot_bleu3 = null;
	RadioButton maillot_bleu4 = null;
	RadioButton maillot_bleu5 = null;
	RadioButton maillot_bleu6 = null;
	RadioButton maillot_bleu7 = null;
	RadioButton maillot_bleu8 = null;
	RadioButton maillot_bleu9 = null;
	RadioButton maillot_bleu10 = null;
	RadioButton maillot_bleu11 = null;
	RadioButton maillot_bleu12 = null;

	RadioButton premier_checked = null;
	RadioButton dernier_checked = null;
	
	RadioButton[] equipeRouge = new RadioButton[6];
	RadioButton[] equipeBleu = new RadioButton[6];
	
	int matriceIdRouge[][] = new int [4][12];
	TextView matriceTextViewRouge[][] = new TextView [4][12];
	
	int matriceIdBleu[][] = new int [4][12];
	TextView matriceTextViewBleu[][] = new TextView [4][12];
	
	// initialisation des noms
	TextView nom_rouge1 = null;
	TextView nom_rouge2 = null;
	TextView nom_rouge3 = null;
	TextView nom_rouge4 = null;
	TextView nom_rouge5 = null;
	TextView nom_rouge6 = null;
	TextView nom_rouge7 = null;
	TextView nom_rouge8 = null;
	TextView nom_rouge9 = null;
	TextView nom_rouge10 = null;
	TextView nom_rouge11= null;
	TextView nom_rouge12 = null;
	TextView nom_bleu1 = null;
	TextView nom_bleu2 = null;
	TextView nom_bleu3 = null;
	TextView nom_bleu4 = null;
	TextView nom_bleu5 = null;
	TextView nom_bleu6 = null;
	TextView nom_bleu7 = null;
	TextView nom_bleu8 = null;
	TextView nom_bleu9 = null;
	TextView nom_bleu10 = null;
	TextView nom_bleu11= null;
	TextView nom_bleu12 = null;
	
	// initialisation des numéros
	TextView num_rouge1 = null;
	TextView num_rouge2 = null;
	TextView num_rouge3 = null;
	TextView num_rouge4 = null;
	TextView num_rouge5 = null;
	TextView num_rouge6 = null;
	TextView num_rouge7 = null;
	TextView num_rouge8 = null;
	TextView num_rouge9 = null;
	TextView num_rouge10 = null;
	TextView num_rouge11= null;
	TextView num_rouge12 = null;
	TextView num_bleu1 = null;
	TextView num_bleu2 = null;
	TextView num_bleu3 = null;
	TextView num_bleu4 = null;
	TextView num_bleu5 = null;
	TextView num_bleu6 = null;
	TextView num_bleu7 = null;
	TextView num_bleu8 = null;
	TextView num_bleu9 = null;
	TextView num_bleu10 = null;
	TextView num_bleu11= null;
	TextView num_bleu12 = null;
	
	// initialisation des boutons des types d'actions
	RadioButton bouton_fantome = null;

	RadioButton bouton_service = null;
	RadioButton bouton_defense = null;
	RadioButton bouton_passe = null;
	RadioButton bouton_attaque = null;
	RadioButton bouton_bloc = null;
	RadioGroup test = null;
	
	
	// initialisation des boutons jugeant la qualité d'action
	RadioButton bouton_excellent = null;
	RadioButton bouton_bien = null;
	RadioButton bouton_neutre = null;
	RadioButton bouton_mauvais = null;
	RadioButton bouton_catastrophique = null;
	
	// Initialisation du tableau de scores
	TableLayout tableScore = null;
	
	ArrayList<Integer> joueurs_checked = new ArrayList<Integer>(2);
	int cptTouch = 0;
	int note;
	ArrayList<RadioButton> actionsPoss;
	RadioButton courant;
	Controleur ctrl;
	
	
	
	// rend cliquable tous les joueurs sur le terrain de l'équipe bleu
	private void equipeBleuClickable() {
		maillot_bleu1.setClickable(true);
		maillot_bleu2.setClickable(true);
		maillot_bleu3.setClickable(true);
		maillot_bleu4.setClickable(true);
		maillot_bleu5.setClickable(true);
		maillot_bleu6.setClickable(true);
	}
	
	// rend cliquable tous les joueurs sur le terrain de l'équipe rouge
	private void equipeRougeClickable() {
		maillot_rouge1.setClickable(true);
		maillot_rouge2.setClickable(true);
		maillot_rouge3.setClickable(true);
		maillot_rouge4.setClickable(true);
		maillot_rouge5.setClickable(true);
		maillot_rouge6.setClickable(true);
	}
	
	// rend cliquable tous les remplacants des deux équipes
	private void remplacantsClickable() {
		maillot_rouge7.setClickable(true);
		maillot_rouge8.setClickable(true);
		maillot_rouge9.setClickable(true);
		maillot_rouge10.setClickable(true);
		maillot_rouge11.setClickable(true);
		maillot_rouge12.setClickable(true);
		maillot_bleu7.setClickable(true);
		maillot_bleu8.setClickable(true);
		maillot_bleu9.setClickable(true);
		maillot_bleu10.setClickable(true);
		maillot_bleu11.setClickable(true);
		maillot_bleu12.setClickable(true);
	}
	
	// rend non cliquable tous les boutons de type d'action
	private void typeActionNonEnabled() {
		typeActionNonChecked();
		System.out.println("Disable all !");
		
		//test.setEnabled(false);
		bouton_service.setEnabled(false);
		bouton_defense.setEnabled(false);
		bouton_passe.setEnabled(false);
		bouton_attaque.setEnabled(false);
		bouton_bloc.setEnabled(false);
	}
	
	private void qualiteActionIsEnable() {
		if (bouton_service.isChecked() || bouton_defense.isChecked() || bouton_passe.isChecked()
				|| bouton_attaque.isChecked() || bouton_bloc.isChecked()) {
			bouton_excellent.setEnabled(true);
			bouton_bien.setEnabled(true);
			bouton_neutre.setEnabled(true);
			bouton_mauvais.setEnabled(true);
			bouton_catastrophique.setEnabled(true);
		}
		else {
			bouton_excellent.setEnabled(false);
			bouton_bien.setEnabled(false);
			bouton_neutre.setEnabled(false);
			bouton_mauvais.setEnabled(false);
			bouton_catastrophique.setEnabled(false);
		}
	}
	
	private void typeActionEnabled() {
		typeActionNonEnabled();
		for (RadioButton b : actionsPoss)
		{
			System.out.println("bouton " + b.getText() + " ACTIVE !");
			b.setEnabled(true);
			//b.setClickable(true);
			//b.setActivated(true);
			if (actionsPoss.size() == 1){b.setChecked(true);}
			
			
		}
	}
	
	
	// decoche tous les joueurs sur le terrain de l'équipe bleu
	private void equipeBleuNonChecked() {
		maillot_bleu1.setChecked(false);
		maillot_bleu2.setChecked(false);
		maillot_bleu3.setChecked(false);
		maillot_bleu4.setChecked(false);
		maillot_bleu5.setChecked(false);
		maillot_bleu6.setChecked(false);
		
		joueurs_checked.clear();			// ATTENTION : VALABLE CAR ON N'UTILISE LES DEUX METHODES que ENSEMBLE (BLEU/ROUGE)
		
	}
	
	// decoche tous les joueurs sur le terrain de l'équipe rouge
	private void equipeRougeNonChecked() {
		maillot_rouge1.setChecked(false);
		maillot_rouge2.setChecked(false);
		maillot_rouge3.setChecked(false);
		maillot_rouge4.setChecked(false);
		maillot_rouge5.setChecked(false);
		maillot_rouge6.setChecked(false);
		
		joueurs_checked.clear();			// ATTENTION : VALABLE CAR ON N'UTILISE LES DEUX METHODES que ENSEMBLE (BLEU/ROUGE)
		
	}
	
	private void typeActionNonChecked() {
		System.out.println("Uncheck all buttons!");
    	test.clearCheck();
	}
	
    // echange de position 2 textviews
	private void echange(TextView t1, TextView t2) {
		
		CharSequence c = t1.getText();
		
		t1.setText(t2.getText());
		t2.setText(c);
	}
	
	// permet le remplacement entre 2 joueurs de l'équipe rouge
	private void remplacementRouge(TextView nomSortant, TextView numSortant, int indice) {
    	if (cptTouch ==1)
    	{
    		matriceTextViewRouge[0][0] = num_rouge1;
			if (maillot_rouge1 == dernier_checked) {
	    		echange(matriceTextViewRouge[1][0], nomSortant); // échange noms des joueurs
	    		echange(matriceTextViewRouge[0][0], numSortant); // échange numéros des joueurs
	    		ctrl.echangeJ(1, indice, 0);
	    		maillot_rouge1.setChecked(false); // remplacement effectué: on décoche
	    		joueurs_checked.remove(new Integer(0));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_rouge2 == dernier_checked) {
	    		echange(matriceTextViewRouge[1][1], nomSortant);
	    		echange(matriceTextViewRouge[0][1], numSortant);
	    		ctrl.echangeJ(1, indice, 1);
	    		maillot_rouge2.setChecked(false);
	    		joueurs_checked.remove(new Integer(1));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_rouge3 == dernier_checked) {
	    		echange(matriceTextViewRouge[1][2], nomSortant);
	    		echange(matriceTextViewRouge[0][2], numSortant);
	    		ctrl.echangeJ(1, indice, 2);
	    		maillot_rouge3.setChecked(false);
	    		joueurs_checked.remove(new Integer(2));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_rouge4 == dernier_checked) {
	    		echange(matriceTextViewRouge[1][3], nomSortant);
	    		echange(matriceTextViewRouge[0][3], numSortant);
	    		ctrl.echangeJ(1, indice, 3);
	    		maillot_rouge4.setChecked(false);
	    		joueurs_checked.remove(new Integer(3));
	    		dernier_checked = null;
	    		
	    	}
	    	else if (maillot_rouge5 == dernier_checked) {
	    		echange(matriceTextViewRouge[1][4], nomSortant);
	    		echange(matriceTextViewRouge[0][4], numSortant);
	    		ctrl.echangeJ(1, indice, 4);
	    		maillot_rouge5.setChecked(false);
	    		joueurs_checked.remove(new Integer(4));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_rouge6 == dernier_checked) {
	    		echange(matriceTextViewRouge[1][5], nomSortant);
	    		echange(matriceTextViewRouge[0][5], numSortant);
	    		ctrl.echangeJ(1, indice, 5);
	    		maillot_rouge6.setChecked(false);
	    		joueurs_checked.remove(new Integer(5));
	    		dernier_checked = null;
	    	}
			cptTouch = 0;
    	} else {cptTouch ++;}
	}
	
	// permet le remplacement entre 2 joueurs de l'équipe bleu
	private void remplacementBleu(TextView nomSortant, TextView numSortant, int indice) {
		if (cptTouch ==1)
    	{
	    	if (maillot_bleu1 == dernier_checked) {
	    		echange(matriceTextViewBleu[1][0], nomSortant);
	    		echange(matriceTextViewBleu[0][0], numSortant);
	    		ctrl.echangeJ(0, indice, 0);
	    		
	    		maillot_bleu1.setChecked(false);
	    		joueurs_checked.remove(new Integer(12));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_bleu2 == dernier_checked) {
	    		echange(matriceTextViewBleu[1][1], nomSortant);
	    		echange(matriceTextViewBleu[0][1], numSortant);
	    		ctrl.echangeJ(0, indice, 1);
	    		maillot_bleu2.setChecked(false);
	    		joueurs_checked.remove(new Integer(13));
	    		dernier_checked = null;
	    		
	    	}
	    	else if (maillot_bleu3 == dernier_checked) {
	    		echange(matriceTextViewBleu[1][2], nomSortant);
	    		echange(matriceTextViewBleu[0][2], numSortant);
	    		ctrl.echangeJ(0, indice, 2);
	    		maillot_bleu3.setChecked(false);
	    		joueurs_checked.remove(new Integer(14));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_bleu4 == dernier_checked) {
	    		echange(matriceTextViewBleu[1][3], nomSortant);
	    		echange(matriceTextViewBleu[0][3], numSortant);
	    		ctrl.echangeJ(0, indice, 3);
	    		maillot_bleu4.setChecked(false);
	    		joueurs_checked.remove(new Integer(15));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_bleu5 == dernier_checked) {
	    		echange(matriceTextViewBleu[1][4], nomSortant);
	    		echange(matriceTextViewBleu[0][4], numSortant);
	    		ctrl.echangeJ(0, indice, 4);
	    		maillot_bleu5.setChecked(false);
	    		joueurs_checked.remove(new Integer(16));
	    		dernier_checked = null;
	    	}
	    	else if (maillot_bleu6 == dernier_checked) {
	    		echange(matriceTextViewBleu[1][5], nomSortant);
	    		echange(matriceTextViewBleu[0][5], numSortant);
	    		ctrl.echangeJ(0, indice, 5);
	    		maillot_bleu6.setChecked(false);
	    		joueurs_checked.remove(new Integer(17));
	    		dernier_checked = null;
	    	}
	    	cptTouch = 0;
	    	
    	} else {cptTouch ++;}
	}
	
	// compte le nombre de joueurs coché
	private int nbJoueursChecked() {
		/*int compteur = 0;
		for (int i=0 ; i<6 ; i++) {
			if (equipeRouge[i].isChecked()) {
				compteur++;
			}
			if (equipeBleu[i].isChecked()) {
				compteur++;
			}
		}*/
		return joueurs_checked.size();	
	}
	
	private boolean joueursMemeEquipeChecked(RadioButton[] equipe) {
		
		for (int i=0 ; i<6 ; i++) {
			for (int j=i+1 ; j<6 ; j++) {
				if (equipe[i].isChecked() && equipe[j].isChecked()) {
					return true;
				}
			}
		}
		return false;	
	}
	
	private void notEnabledServiceAttaqueBloc() {
		if (joueursMemeEquipeChecked(equipeRouge) || joueursMemeEquipeChecked(equipeBleu)) {
			bouton_service.setChecked(false);
			bouton_attaque.setChecked(false);
			bouton_bloc.setChecked(false);
			bouton_service.setEnabled(false);
			bouton_attaque.setEnabled(false);
			bouton_bloc.setEnabled(false);
			System.out.println("A/S/B disabled !");
		}
	}
	
	private boolean joueursPasMemeEquipeChecked() {
		for (int i=0 ; i<6 ; i++) {
			for (int j=0 ; j<6 ; j++) {
				if (equipeRouge[i].isChecked() && equipeBleu[j].isChecked()) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private void notEnabledPasse() {
		if (joueursPasMemeEquipeChecked()) {
			System.out.println("Passe disabled !");
			bouton_passe.setChecked(false);
			bouton_passe.setEnabled(false);
		}
	}
	private void cocheTitulaire(RadioButton rad, int indice)
	{
		//System.out.println(joueurs_checked.size());
		if (cptTouch == 1)					// Adapte le click PC à un touch tablette
		{
			if (rad.isChecked())
			{
				if (rad != courant)
				{
					rad.setChecked(false);
					typeActionNonEnabled();
					joueurs_checked.remove(new Integer(indice));
					if (dernier_checked == rad) {dernier_checked = null;}
				}
			}
			else
			{
				if (nbJoueursChecked() < 2) {
					
					rad.setChecked(true); // on le coche
					joueurs_checked.add(indice);
					dernier_checked = rad;
					
					
				}
				if (nbJoueursChecked() == 2) {
					if (dernier_checked != courant)
					{
						if (dernier_checked != null)
						{
							dernier_checked.setChecked(false);
							joueurs_checked.remove(1);
						}
						dernier_checked = rad;
						joueurs_checked.add(indice);
						rad.setChecked(true);
						
					}
					
					
				}
				if (nbJoueursChecked() == 2)
				{
					
					typeActionEnabled();
					notEnabledServiceAttaqueBloc();
					notEnabledPasse();
					//bouton_fantome.setChecked(true);
				}else{typeActionNonEnabled();}
			}
			cptTouch = 0;
		}
		else {cptTouch ++;}
	}
	
	// listener du joueur rouge au poste 1 (titulaire)
	private OnTouchListener touchListenerRouge1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	cocheTitulaire(maillot_rouge1,0);
	        return true;
	    }
	};
	
	// listener du joueur rouge au poste 2 (titulaire)
	private OnTouchListener touchListenerRouge2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	cocheTitulaire(maillot_rouge2,1);
	        return true;
	    }
	};
	
	// listener du joueur rouge au poste 3 (titulaire)
	private OnTouchListener touchListenerRouge3 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	cocheTitulaire(maillot_rouge3,2);
	        return true;
	    }
	};
	
	// listener du joueur rouge au poste 4 (titulaire)
	private OnTouchListener touchListenerRouge4 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	cocheTitulaire(maillot_rouge4,3);
	        return true;
	    }
	};
	
	// listener du joueur rouge au poste 5 (titulaire)
	private OnTouchListener touchListenerRouge5 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	cocheTitulaire(maillot_rouge5,4);
	        return true;
	    }
	};
	
	// listener du joueur rouge au poste 6 (titulaire)
	private OnTouchListener touchListenerRouge6 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	cocheTitulaire(maillot_rouge6,5);
	        return true;
	    }
	};
	
	// listener du joueur rouge au poste 7 (remplacant)
	private OnTouchListener touchListenerRouge7 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	/* si les remplacants sont cliquables c'est qu'un joueur titulaire a été coché précédement
	    	 * au clic sur le remplacant, on échange les deux */
	    	if (maillot_rouge7.isClickable()) {
	    		remplacementRouge(matriceTextViewRouge[1][6], matriceTextViewRouge[0][6], 6);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur rouge au poste 8 (remplacant)
	private OnTouchListener touchListenerRouge8 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_rouge8.isClickable()) {
	    		remplacementRouge(matriceTextViewRouge[1][7], matriceTextViewRouge[0][7], 7);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur rouge au poste 9 (remplacant)
	private OnTouchListener touchListenerRouge9 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_rouge9.isClickable()) {
	    		remplacementRouge(matriceTextViewRouge[1][8], matriceTextViewRouge[0][8], 8);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur rouge au poste 10 (remplacant)
	private OnTouchListener touchListenerRouge10 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_rouge10.isClickable()) {
	    		remplacementRouge(matriceTextViewRouge[1][9], matriceTextViewRouge[0][9], 9);
		        return true;
	    	}
	    	return true;
	    }
	};

	// listener du joueur rouge au poste 11 (remplacant)
	private OnTouchListener touchListenerRouge11 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_rouge11.isClickable()) {
	    		remplacementRouge(matriceTextViewRouge[1][10], matriceTextViewRouge[0][10], 10);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur rouge au poste 12 (remplacant)
	private OnTouchListener touchListenerRouge12 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_rouge12.isClickable()) {
	    		remplacementRouge(matriceTextViewRouge[1][11], matriceTextViewRouge[0][11], 11);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur bleu au poste 1 (titulaire)
	private OnTouchListener touchListenerBleu1 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {	    	
	    	cocheTitulaire(maillot_bleu1,12);
	        return true;
	    }
	};
	
	// listener du joueur bleu au poste 2 (titulaire)
	private OnTouchListener touchListenerBleu2 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {	    	
	    	cocheTitulaire(maillot_bleu2,13);
	        return true;
	    }
	};
	
	// listener du joueur bleu au poste 3 (titulaire)
	private OnTouchListener touchListenerBleu3 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {	    	
	    	cocheTitulaire(maillot_bleu3,14);
	        return true;
	    }
	};
	
	// listener du joueur bleu au poste 4 (titulaire)
	private OnTouchListener touchListenerBleu4 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {	    	
	    	cocheTitulaire(maillot_bleu4,15);
	        return true;
	    }
	};
	
	// listener du joueur bleu au poste 5 (titulaire)
	private OnTouchListener touchListenerBleu5 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {	    	
	    	cocheTitulaire(maillot_bleu5,16);
	        return true;
	    }
	};
	
	// listener du joueur bleu au poste 6 (titulaire)
	private OnTouchListener touchListenerBleu6 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {	    	
	    	cocheTitulaire(maillot_bleu6,17);
	        return true;
	    }
	};
	
	// listener du joueur bleu au poste 7 (remplacant)
	private OnTouchListener touchListenerBleu7 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_bleu7.isClickable()) {
	    		remplacementBleu(matriceTextViewBleu[1][6], matriceTextViewBleu[0][6], 18);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur bleu au poste 8 (remplacant)
	private OnTouchListener touchListenerBleu8 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_bleu8.isClickable()) {
	    		remplacementBleu(matriceTextViewBleu[1][7], matriceTextViewBleu[0][7], 19);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur bleu au poste 9 (remplacant)
	private OnTouchListener touchListenerBleu9 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_bleu9.isClickable()) {
	    		remplacementBleu(matriceTextViewBleu[1][8], matriceTextViewBleu[0][8], 20);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur bleu au poste 10 (remplacant)
	private OnTouchListener touchListenerBleu10 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_bleu10.isClickable()) {
	    		remplacementBleu(matriceTextViewBleu[1][9], matriceTextViewBleu[0][9], 21);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur bleu au poste 11 (remplacant)
	private OnTouchListener touchListenerBleu11 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_bleu11.isClickable()) {
	    		remplacementBleu(matriceTextViewBleu[1][10], matriceTextViewBleu[0][10], 22);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	// listener du joueur bleu au poste 12 (remplacant)
	private OnTouchListener touchListenerBleu12 = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	if (maillot_bleu12.isClickable()) {
	    		remplacementBleu(matriceTextViewBleu[1][11], matriceTextViewBleu[0][11], 23);
		        return true;
	    	}
	    	return true;
	    }
	};
	
	
	// listener des boutons jugeant la qualité d'une action
	
	private OnTouchListener touchListenerExcellent = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	note = 2;
	    	validerAction();
	    	return true;
	    }
	};
	
	private OnTouchListener touchListenerBon = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	note = 1;
	    	validerAction();
	    	return true;
	    }
	};
	
	private OnTouchListener touchListenerNeutre = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	note = 0;
	    	validerAction();
	    	return true;
	    }
	};
	
	private OnTouchListener touchListenerMoyen = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	note = -1;
	    	validerAction();
	    	return true;
	    }
	};
	
	private OnTouchListener touchListenerMauvais = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	note = -2;
	    	validerAction();
	    	return true;
	    }
	};
	
	private void validerAction() {
			System.out.println("\n ==================================================== \n");

			System.out.println("cpt touch : " + cptTouch);
	    	if(cptTouch == 1)
	    	{
		    	if (joueurs_checked.size() == 2)
		    	{
		    		String typeA = actionSelectionnee();
		    		if(typeA != "err")
		    		{
		    			System.out.println("Action " + joueurs_checked.get(0) + "  ====>  " + joueurs_checked.get(1));	    		
		    			ctrl.soumettreAction(joueurs_checked.get(0), joueurs_checked.get(1), typeA, note);
		    			
		    			//typeActionNonChecked();
				    	typeActionNonEnabled();
				    	// on decoche tout les boutons
				    	
				    	
			
				    	equipeRougeNonChecked();
				    	equipeBleuNonChecked();
				    	
				    	// on rend à nouveau cliquable tous les joueurs
				    	equipeRougeClickable();
				    	equipeBleuClickable();
			        	remplacantsClickable();
			        	miseAJour();
			        	
		    		}
		    	}
		    	cptTouch = 0;
		    	
		    	
	    	}
	    	else {cptTouch++;}
	    	
	    }   
	    
	    	
	
	
	private String actionSelectionnee()
	{
		if (bouton_service.isChecked())
		{
			return "se";
		}
		if (bouton_defense.isChecked())
		{
			return "re";
		}
		if (bouton_passe.isChecked())
		{
			return "pa";
		}
		if (bouton_attaque.isChecked())
		{
			return "at";
		}
		if (bouton_bloc.isChecked())
		{
			return "bl";
		}
		return "er";
	}
	
	public void initialiseRouge(int i, int numeroJoueur, String nomJoueur) {
	    Resources res = getResources();
		String numero = String.format(res.getString(matriceIdRouge[0][i]), numeroJoueur);
	    matriceTextViewRouge[0][i] = (TextView) findViewById(matriceIdRouge[1][i]);
	    matriceTextViewRouge[0][i].setText(numero);
	    
	    String nom = String.format(res.getString(matriceIdRouge[2][i]), nomJoueur);
	    matriceTextViewRouge[1][i] = (TextView) findViewById(matriceIdRouge[3][i]);
	    matriceTextViewRouge[1][i].setText(nom);		
	}
	
	
	public void initialiseBleu(int i, int numeroJoueur, String nomJoueur) {
	    Resources res = getResources();
		String numero = String.format(res.getString(matriceIdBleu[0][i]), numeroJoueur);
	    matriceTextViewBleu[0][i] = (TextView) findViewById(matriceIdBleu[1][i]);
	    matriceTextViewBleu[0][i].setText(numero);
	    
	    String nom = String.format(res.getString(matriceIdBleu[2][i]), nomJoueur);
	    matriceTextViewBleu[1][i] = (TextView) findViewById(matriceIdBleu[3][i]);
	    matriceTextViewBleu[1][i].setText(nom);		
	}
	
	public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saisie_stats);
		
		tableScore = (TableLayout) findViewById(R.id.tableau_score);
		
		matriceIdRouge[0][0] = R.string.num_e1_n1;
		matriceIdRouge[0][1] = R.string.num_e1_n2;
		matriceIdRouge[0][2] = R.string.num_e1_n3;
		matriceIdRouge[0][3] = R.string.num_e1_n4;
		matriceIdRouge[0][4] = R.string.num_e1_n5;
		matriceIdRouge[0][5] = R.string.num_e1_n6;
		matriceIdRouge[0][6] = R.string.num_e1_n7;
		matriceIdRouge[0][7] = R.string.num_e1_n8;
		matriceIdRouge[0][8] = R.string.num_e1_n9;
		matriceIdRouge[0][9] = R.string.num_e1_n10;
		matriceIdRouge[0][10] = R.string.num_e1_n11;
		matriceIdRouge[0][11] = R.string.num_e1_n12;
		
		matriceIdRouge[1][0] = R.id.num_joueur_e1_n1;
		matriceIdRouge[1][1] = R.id.num_joueur_e1_n2;
		matriceIdRouge[1][2] = R.id.num_joueur_e1_n3;
		matriceIdRouge[1][3] = R.id.num_joueur_e1_n4;
		matriceIdRouge[1][4] = R.id.num_joueur_e1_n5;
		matriceIdRouge[1][5] = R.id.num_joueur_e1_n6;
		matriceIdRouge[1][6] = R.id.num_joueur_e1_n7;
		matriceIdRouge[1][7] = R.id.num_joueur_e1_n8;
		matriceIdRouge[1][8] = R.id.num_joueur_e1_n9;
		matriceIdRouge[1][9] = R.id.num_joueur_e1_n10;
		matriceIdRouge[1][10] = R.id.num_joueur_e1_n11;
		matriceIdRouge[1][11] = R.id.num_joueur_e1_n12;
		
		matriceIdRouge[2][0] = R.string.nom_e1_n1;
		matriceIdRouge[2][1] = R.string.nom_e1_n2;
		matriceIdRouge[2][2] = R.string.nom_e1_n3;
		matriceIdRouge[2][3] = R.string.nom_e1_n4;
		matriceIdRouge[2][4] = R.string.nom_e1_n5;
		matriceIdRouge[2][5] = R.string.nom_e1_n6;
		matriceIdRouge[2][6] = R.string.nom_e1_n7;
		matriceIdRouge[2][7] = R.string.nom_e1_n8;
		matriceIdRouge[2][8] = R.string.nom_e1_n9;
		matriceIdRouge[2][9] = R.string.nom_e1_n10;
		matriceIdRouge[2][10] = R.string.nom_e1_n11;
		matriceIdRouge[2][11] = R.string.nom_e1_n12;
		
		matriceIdRouge[3][0] = R.id.nom_joueur_e1_n1;
		matriceIdRouge[3][1] = R.id.nom_joueur_e1_n2;
		matriceIdRouge[3][2] = R.id.nom_joueur_e1_n3;
		matriceIdRouge[3][3] = R.id.nom_joueur_e1_n4;
		matriceIdRouge[3][4] = R.id.nom_joueur_e1_n5;
		matriceIdRouge[3][5] = R.id.nom_joueur_e1_n6;
		matriceIdRouge[3][6] = R.id.nom_joueur_e1_n7;
		matriceIdRouge[3][7] = R.id.nom_joueur_e1_n8;
		matriceIdRouge[3][8] = R.id.nom_joueur_e1_n9;
		matriceIdRouge[3][9] = R.id.nom_joueur_e1_n10;
		matriceIdRouge[3][10] = R.id.nom_joueur_e1_n11;
		matriceIdRouge[3][11] = R.id.nom_joueur_e1_n12;
		
		matriceTextViewRouge[0][0] = num_rouge1;
		matriceTextViewRouge[0][1] = num_rouge2;
		matriceTextViewRouge[0][2] = num_rouge3;
		matriceTextViewRouge[0][3] = num_rouge4;
		matriceTextViewRouge[0][4] = num_rouge5;
		matriceTextViewRouge[0][5] = num_rouge6;
		matriceTextViewRouge[0][6] = num_rouge7;
		matriceTextViewRouge[0][7] = num_rouge8;
		matriceTextViewRouge[0][8] = num_rouge9;
		matriceTextViewRouge[0][9] = num_rouge10;
		matriceTextViewRouge[0][10] = num_rouge11;
		matriceTextViewRouge[0][11] = num_rouge12;
		
		matriceTextViewRouge[1][0] = nom_rouge1;
		matriceTextViewRouge[1][1] = nom_rouge2;
		matriceTextViewRouge[1][2] = nom_rouge3;
		matriceTextViewRouge[1][3] = nom_rouge4;
		matriceTextViewRouge[1][4] = nom_rouge5;
		matriceTextViewRouge[1][5] = nom_rouge6;
		matriceTextViewRouge[1][6] = nom_rouge7;
		matriceTextViewRouge[1][7] = nom_rouge8;
		matriceTextViewRouge[1][8] = nom_rouge9;
		matriceTextViewRouge[1][9] = nom_rouge10;
		matriceTextViewRouge[1][10] = nom_rouge11;
		matriceTextViewRouge[1][11] = nom_rouge12;
		
		matriceIdBleu[0][0] = R.string.num_e2_n1;
		matriceIdBleu[0][1] = R.string.num_e2_n2;
		matriceIdBleu[0][2] = R.string.num_e2_n3;
		matriceIdBleu[0][3] = R.string.num_e2_n4;
		matriceIdBleu[0][4] = R.string.num_e2_n5;
		matriceIdBleu[0][5] = R.string.num_e2_n6;
		matriceIdBleu[0][6] = R.string.num_e2_n7;
		matriceIdBleu[0][7] = R.string.num_e2_n8;
		matriceIdBleu[0][8] = R.string.num_e2_n9;
		matriceIdBleu[0][9] = R.string.num_e2_n10;
		matriceIdBleu[0][10] = R.string.num_e2_n11;
		matriceIdBleu[0][11] = R.string.num_e2_n12;
		
		matriceIdBleu[1][0] = R.id.num_joueur_e2_n1;
		matriceIdBleu[1][1] = R.id.num_joueur_e2_n2;
		matriceIdBleu[1][2] = R.id.num_joueur_e2_n3;
		matriceIdBleu[1][3] = R.id.num_joueur_e2_n4;
		matriceIdBleu[1][4] = R.id.num_joueur_e2_n5;
		matriceIdBleu[1][5] = R.id.num_joueur_e2_n6;
		matriceIdBleu[1][6] = R.id.num_joueur_e2_n7;
		matriceIdBleu[1][7] = R.id.num_joueur_e2_n8;
		matriceIdBleu[1][8] = R.id.num_joueur_e2_n9;
		matriceIdBleu[1][9] = R.id.num_joueur_e2_n10;
		matriceIdBleu[1][10] = R.id.num_joueur_e2_n11;
		matriceIdBleu[1][11] = R.id.num_joueur_e2_n12;
		
		matriceIdBleu[2][0] = R.string.nom_e2_n1;
		matriceIdBleu[2][1] = R.string.nom_e2_n2;
		matriceIdBleu[2][2] = R.string.nom_e2_n3;
		matriceIdBleu[2][3] = R.string.nom_e2_n4;
		matriceIdBleu[2][4] = R.string.nom_e2_n5;
		matriceIdBleu[2][5] = R.string.nom_e2_n6;
		matriceIdBleu[2][6] = R.string.nom_e2_n7;
		matriceIdBleu[2][7] = R.string.nom_e2_n8;
		matriceIdBleu[2][8] = R.string.nom_e2_n9;
		matriceIdBleu[2][9] = R.string.nom_e2_n10;
		matriceIdBleu[2][10] = R.string.nom_e2_n11;
		matriceIdBleu[2][11] = R.string.nom_e2_n12;
		
		matriceIdBleu[3][0] = R.id.nom_joueur_e2_n1;
		matriceIdBleu[3][1] = R.id.nom_joueur_e2_n2;
		matriceIdBleu[3][2] = R.id.nom_joueur_e2_n3;
		matriceIdBleu[3][3] = R.id.nom_joueur_e2_n4;
		matriceIdBleu[3][4] = R.id.nom_joueur_e2_n5;
		matriceIdBleu[3][5] = R.id.nom_joueur_e2_n6;
		matriceIdBleu[3][6] = R.id.nom_joueur_e2_n7;
		matriceIdBleu[3][7] = R.id.nom_joueur_e2_n8;
		matriceIdBleu[3][8] = R.id.nom_joueur_e2_n9;
		matriceIdBleu[3][9] = R.id.nom_joueur_e2_n10;
		matriceIdBleu[3][10] = R.id.nom_joueur_e2_n11;
		matriceIdBleu[3][11] = R.id.nom_joueur_e2_n12;
		
		matriceTextViewBleu[0][0] = num_bleu1;
		matriceTextViewBleu[0][1] = num_bleu2;
		matriceTextViewBleu[0][2] = num_bleu3;
		matriceTextViewBleu[0][3] = num_bleu4;
		matriceTextViewBleu[0][4] = num_bleu5;
		matriceTextViewBleu[0][5] = num_bleu6;
		matriceTextViewBleu[0][6] = num_bleu7;
		matriceTextViewBleu[0][7] = num_bleu8;
		matriceTextViewBleu[0][8] = num_bleu9;
		matriceTextViewBleu[0][9] = num_bleu10;
		matriceTextViewBleu[0][10] = num_bleu11;
		matriceTextViewBleu[0][11] = num_bleu12;
		
		matriceTextViewBleu[1][0] = nom_bleu1;
		matriceTextViewBleu[1][1] = nom_bleu2;
		matriceTextViewBleu[1][2] = nom_bleu3;
		matriceTextViewBleu[1][3] = nom_bleu4;
		matriceTextViewBleu[1][4] = nom_bleu5;
		matriceTextViewBleu[1][5] = nom_bleu6;
		matriceTextViewBleu[1][6] = nom_bleu7;
		matriceTextViewBleu[1][7] = nom_bleu8;
		matriceTextViewBleu[1][8] = nom_bleu9;
		matriceTextViewBleu[1][9] = nom_bleu10;
		matriceTextViewBleu[1][10] = nom_bleu11;
		matriceTextViewBleu[1][11] = nom_bleu12;
		
		// on va chercher toutes les vues dans le fichier xml
		maillot_rouge1 = (RadioButton) findViewById(R.id.maillot_rouge1);
		maillot_rouge2 = (RadioButton) findViewById(R.id.maillot_rouge2);
		maillot_rouge3 = (RadioButton) findViewById(R.id.maillot_rouge3);
		maillot_rouge4 = (RadioButton) findViewById(R.id.maillot_rouge4);
		maillot_rouge5 = (RadioButton) findViewById(R.id.maillot_rouge5);
		maillot_rouge6 = (RadioButton) findViewById(R.id.maillot_rouge6);
		maillot_rouge7 = (RadioButton) findViewById(R.id.maillot_rouge7);
		maillot_rouge8 = (RadioButton) findViewById(R.id.maillot_rouge8);
		maillot_rouge9 = (RadioButton) findViewById(R.id.maillot_rouge9);
		maillot_rouge10 = (RadioButton) findViewById(R.id.maillot_rouge10);
		maillot_rouge11 = (RadioButton) findViewById(R.id.maillot_rouge11);
		maillot_rouge12 = (RadioButton) findViewById(R.id.maillot_rouge12);
		maillot_bleu1 = (RadioButton) findViewById(R.id.maillot_bleu1);
		maillot_bleu2 = (RadioButton) findViewById(R.id.maillot_bleu2);
		maillot_bleu3 = (RadioButton) findViewById(R.id.maillot_bleu3);
		maillot_bleu4 = (RadioButton) findViewById(R.id.maillot_bleu4);
		maillot_bleu5 = (RadioButton) findViewById(R.id.maillot_bleu5);
		maillot_bleu6 = (RadioButton) findViewById(R.id.maillot_bleu6);
		maillot_bleu7 = (RadioButton) findViewById(R.id.maillot_bleu7);
		maillot_bleu8 = (RadioButton) findViewById(R.id.maillot_bleu8);
		maillot_bleu9 = (RadioButton) findViewById(R.id.maillot_bleu9);
		maillot_bleu10 = (RadioButton) findViewById(R.id.maillot_bleu10);
		maillot_bleu11 = (RadioButton) findViewById(R.id.maillot_bleu11);
		maillot_bleu12 = (RadioButton) findViewById(R.id.maillot_bleu12);
		
		equipeRouge[0] = maillot_rouge1;
		equipeRouge[1] = maillot_rouge2;
		equipeRouge[2] = maillot_rouge3;
		equipeRouge[3] = maillot_rouge4;
		equipeRouge[4] = maillot_rouge5;
		equipeRouge[5] = maillot_rouge6;
		
		equipeBleu[0] = maillot_bleu1;
		equipeBleu[1] = maillot_bleu2;
		equipeBleu[2] = maillot_bleu3;
		equipeBleu[3] = maillot_bleu4;
		equipeBleu[4] = maillot_bleu5;
		equipeBleu[5] = maillot_bleu6;
		
		initialiseRouge(0, 1, "Joueur11");
		initialiseRouge(1, 2, "Joueur12");
		initialiseRouge(2, 3, "Joueur13");
		initialiseRouge(3, 4, "Joueur14");
		initialiseRouge(4, 5, "Joueur15");
		initialiseRouge(5, 6, "Joueur16");
		initialiseRouge(6, 7, "Joueur17");
		initialiseRouge(7, 8, "Joueur18");
		initialiseRouge(8, 9, "Joueur19");
		initialiseRouge(9, 10, "Joueur110");
		initialiseRouge(10, 11, "Joueur111");
		initialiseRouge(11, 12, "Joueur112");
		
		initialiseBleu(0, 1, "Joueur21");
		initialiseBleu(1, 2, "Joueur22");
		initialiseBleu(2, 3, "Joueur23");
		initialiseBleu(3, 4, "Joueur24");
		initialiseBleu(4, 5, "Joueur25");
		initialiseBleu(5, 6, "Joueur26");
		initialiseBleu(6, 7, "Joueur27");
		initialiseBleu(7, 8, "Joueur28");
		initialiseBleu(8, 9, "Joueur29");
		initialiseBleu(9, 10, "Joueur210");
		initialiseBleu(10, 11, "Joueur211");
		initialiseBleu(11, 12, "Joueur212");
		
		//bouton_fantome = (RadioButton) findViewById(R.id.button_service);
		//bouton_fantome.setEnabled(true);
		//bouton_fantome.setChecked(false);
		bouton_service = (RadioButton) findViewById(R.id.button_service);
		bouton_defense = (RadioButton) findViewById(R.id.button_reception);
		bouton_passe = (RadioButton) findViewById(R.id.button_passe);
		bouton_attaque = (RadioButton) findViewById(R.id.button_attaque);
		bouton_bloc = (RadioButton) findViewById(R.id.button_bloc);
		bouton_excellent = (RadioButton) findViewById(R.id.button_excellent);
		bouton_bien = (RadioButton) findViewById(R.id.button_bien);
		bouton_neutre = (RadioButton) findViewById(R.id.button_neutre);
		bouton_mauvais = (RadioButton) findViewById(R.id.button_mauvais);
		bouton_catastrophique = (RadioButton) findViewById(R.id.button_catastrophique);
		
		test = (RadioGroup) findViewById(R.id.radioGroupTypeAction);
		
		
		// on associe chaque bouton a son listener correspondant
		maillot_rouge1.setOnTouchListener(touchListenerRouge1);
		maillot_rouge2.setOnTouchListener(touchListenerRouge2);
		maillot_rouge3.setOnTouchListener(touchListenerRouge3);
		maillot_rouge4.setOnTouchListener(touchListenerRouge4);
		maillot_rouge5.setOnTouchListener(touchListenerRouge5);
		maillot_rouge6.setOnTouchListener(touchListenerRouge6);
		maillot_rouge7.setOnTouchListener(touchListenerRouge7);
		maillot_rouge8.setOnTouchListener(touchListenerRouge8);
		maillot_rouge9.setOnTouchListener(touchListenerRouge9);
		maillot_rouge10.setOnTouchListener(touchListenerRouge10);
		maillot_rouge11.setOnTouchListener(touchListenerRouge11);
		maillot_rouge12.setOnTouchListener(touchListenerRouge12);
		maillot_bleu1.setOnTouchListener(touchListenerBleu1);
		maillot_bleu2.setOnTouchListener(touchListenerBleu2);
		maillot_bleu3.setOnTouchListener(touchListenerBleu3);
		maillot_bleu4.setOnTouchListener(touchListenerBleu4);
		maillot_bleu5.setOnTouchListener(touchListenerBleu5);
		maillot_bleu6.setOnTouchListener(touchListenerBleu6);
		maillot_bleu7.setOnTouchListener(touchListenerBleu7);
		maillot_bleu8.setOnTouchListener(touchListenerBleu8);
		maillot_bleu9.setOnTouchListener(touchListenerBleu9);
		maillot_bleu10.setOnTouchListener(touchListenerBleu10);
		maillot_bleu11.setOnTouchListener(touchListenerBleu11);
		maillot_bleu12.setOnTouchListener(touchListenerBleu12);	
		
		bouton_excellent.setOnTouchListener(touchListenerExcellent);
		bouton_bien.setOnTouchListener(touchListenerBon);
		bouton_neutre.setOnTouchListener(touchListenerNeutre);
		bouton_mauvais.setOnTouchListener(touchListenerMoyen);
		bouton_catastrophique.setOnTouchListener(touchListenerMauvais);
		
		/*bouton_excellent.setOnTouchListener(touchListenerQualiteAction);
		bouton_bien.setOnTouchListener(touchListenerQualiteAction);
		bouton_neutre.setOnTouchListener(touchListenerQualiteAction);
		bouton_mauvais.setOnTouchListener(touchListenerQualiteAction);
		bouton_catastrophique.setOnTouchListener(touchListenerQualiteAction);*/
		
		
		// le type et la qualité d'une action ne sont pas cliquable au lancement
		typeActionNonEnabled();
		// Initialisations...
		ctrl = Controleur.getInstance();
		note = -4;
		actionsPoss = new ArrayList<RadioButton>();
		miseAJour();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void miseAJour()
	{
		
		if (ctrl.estNouveauMatch())
		{
			ctrl.getModele().setNouveauMatch(false);
		}
		else
		{
			modifierCelluleScore(1, ctrl.getModele().getTabSet().size(), new Integer(ctrl.getModele().getSet().getScoreEquipeDomicile()).toString());
			modifierCelluleScore(2, ctrl.getModele().getTabSet().size(), new Integer(ctrl.getModele().getSet().getScoreEquipeExterieur()).toString());
		}
		if (ctrl.estNouveauSet())
		{
			ctrl.nouveauSet();
			ctrl.getModele().setNouveauSet(false);
			modifierCelluleScore(1, ctrl.getModele().getTabSet().size(), new Integer(ctrl.getModele().getSet().getScoreEquipeDomicile()).toString());
			modifierCelluleScore(2, ctrl.getModele().getTabSet().size(), new Integer(ctrl.getModele().getSet().getScoreEquipeExterieur()).toString());
		}
		String etat = ctrl.getEtatAuto();
		if (ctrl.estNouveauPoint())
		{
			System.out.println("Nouveau point détecté !");
			ctrl.nouveauPoint();
			actionsPoss.clear();
			actionsPoss.add(bouton_service);
			if (ctrl.getModele().getRotation() != -1)
			{
				rotationEquipe(ctrl.getService());
				ctrl.getModele().setRotation(-1);
			}
			if (ctrl.getService() == 0)
			{
				//modifierCelluleScore(1, ctrl.getModele().getTabSet().size(), new Integer(ctrl.getModele().getSet().getScoreEquipeDomicile()).toString());
				maillot_bleu1.setChecked(true);
				maillot_rouge6.setChecked(true);
				joueurs_checked.add(12);
				joueurs_checked.add(5);
				dernier_checked = maillot_rouge6;
				typeActionEnabled();
				//bouton_service.setEnabled(true);
				//bouton_service.setChecked(true);
			}
			else
			{
				//modifierCelluleScore(2, ctrl.getModele().getTabSet().size(), new Integer(ctrl.getModele().getSet().getScoreEquipeExterieur()).toString());
				maillot_rouge1.setChecked(true);
				maillot_bleu6.setChecked(true);
				joueurs_checked.add(0);
				joueurs_checked.add(17);
				dernier_checked = maillot_bleu6;
				typeActionEnabled();
				//bouton_service.setEnabled(true);
				//bouton_service.setChecked(true);
				
			}
		}
		else
		{
			actionsPoss.clear();
			int jSuiv = ctrl.getJSuiv();
			
			if (jSuiv > 11){courant = equipeBleu[(jSuiv%12)];}
			else{courant = equipeRouge[jSuiv];}
			
			courant.setChecked(true);
			joueurs_checked.add(jSuiv);
			dernier_checked = courant;
			
			ArrayList<String> actPoss = ctrl.getActionsPossibles();
			setActionsPoss(actPoss);
			
		}
	}
	
	private void setActionsPoss(ArrayList<String> actPoss)
	{
		actionsPoss.clear();
		for (String s : actPoss)
		{
			if (s == "re"){actionsPoss.add(bouton_defense);}
			else if (s == "at"){actionsPoss.add(bouton_attaque);}
			else if (s == "pa"){actionsPoss.add(bouton_passe);}
			else if (s == "bl"){actionsPoss.add(bouton_bloc);}
			else if (s == "se"){actionsPoss.add(bouton_service);}
		}
	}
	
	private void rotationEquipe(int i)
	{
		System.out.println("ROTATION EQUIPE " + i);
		
		if (i == 0)
		{
			for (int j = 0;j<5;j++)
			{
				
				
				for (int k = 0;k<2;k++)
				{
					echange(matriceTextViewBleu[k][j],matriceTextViewBleu[k][j+1]);
					
				}
				ctrl.getModele().echangeBleu(j, j+1);
			}
		}
		else if (i == 1)
		{
			for (int j = 0;j<5;j++)
			{
				
				
				for (int k = 0;k<2;k++)
				{
					echange(matriceTextViewRouge[k][j],matriceTextViewRouge[k][j+1]);
					
				}
				ctrl.getModele().echangeRouge(j, j+1);
			}
		}
	}
	
	public void modifierCelluleScore(int li, int col, String s)
	{
		TableRow tablecol = (TableRow)tableScore.getChildAt(li);
		TextView cell = (TextView)tablecol.getChildAt(col);
		cell.setText(s);
	}

}


