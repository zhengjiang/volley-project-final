package controleur;
import java.util.ArrayList;

import modele.*;

/**
 * @author Vincent
 *
 */
public class Controleur {
	
	private static Controleur instance;
	private Modele modele;
	
	public static void main(String[] args){
		Controleur controleur = Controleur.getInstance();
	}
	
	protected Controleur(){
		modele = new Modele();
	}
	
	public static synchronized Controleur getInstance(){
		if(instance == null){
			instance = new Controleur();
		}
		return instance;
	}
	// Methodes accès Modèle
	
	public boolean estNouveauMatch(){return modele.estNouveauMatch();}
	public boolean estNouveauSet(){return modele.estNouveauSet();}
	public String getEtatAuto(){return modele.getEtatAuto();}
	public boolean getService(){return modele.getService();}
	public ArrayList<String> getActionsPossibles(){return modele.getEtatsAuto();}
	public int getJSuiv(){return modele.getJSuiv();}
	public void echangeJ(int eq, int j1, int j2)
	{
		j1 = j1%12;
		j2 = j2%12;
		if (eq == 0)
		{
			
			modele.echangeBleu(j1, j2);
			System.out.println(modele.getJoueur(j2).getNom() + "  ====>  " + modele.getJoueur(j1).getNom());
			
		}
		else if (eq ==1)
		{
			modele.echangeRouge(j1, j2);
			System.out.println(modele.getJoueur(j2).getNom() + "  ====>  " + modele.getJoueur(j1).getNom());
		}else System.out.println("EQUIPE INVALIDE ECHANGEJ()");
		
	}
	
	public boolean soumettreAction(int j1, int j2, String type, int note)
	{
		int eq = 0;
		if (j1 < 12){eq = 1;}
		j1 = j1%12;
		Action action = new Action(0, type);
		System.out.println("Joueur " + modele.getJoueur(j1).getNom() + " effectue un " + type + " " + note + " sur le joueur " + modele.getJoueur(j2).getNom());
		ActionJoueur act = new ActionJoueur(0, modele.getMatch(), action, modele.getJoueur(j1), modele.getNumPoint(), note);
		modele.ajouterAction(act);
		modele.setJSuiv(j2);
		
		if (note == 2)
		{
			modele.setGagne(eq);
		}else if (note == -2){modele.setGagne(eq);}
		
		if (modele.getGagne() == -1)
		{
			modele.avancerAuto(type);
		}
		else 
		{
			System.out.println("Equipe " + modele.getGagne() + " remporte le point");
			soumettrePoint();
		}
		
		
		
		
		
		return true;
	}
	
	public void nouveauSet()
	{
		modele.ajouterNouveauSet();
		modele.setNouveauSet(false);
	}
	
	public void nouveauPoint()
	{
		modele.nouveauPoint();
	}
	
	public void soumettrePoint(){}
	
	
}

	