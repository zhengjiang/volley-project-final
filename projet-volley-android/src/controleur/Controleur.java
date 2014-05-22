package controleur;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
//import basededonnees.*;
import modele.*;

/**
 * @author Vincent
 *
 */
public class Controleur {
	
	private static Controleur instance;
	private Modele modele;
	//private JoueurBdd jb;
	
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
	
	/*public void initialiseBdd(Context c){
		this.jb = new JoueurBdd(c);
	}
	
	public void prout(){
		jb.open();
		//jb.ajouter(new Joueur(0, "penis", 0, 0, 0, 0));
		//jb.ajouter(new Joueur(0, "PENIS ENCULE", 0, 0, 0, 0));
		List<Joueur> j2 = jb.selectionnerTout();
		jb.close();
		for(int i = 0; i < j2.size(); i++)
	    {
	      Log.v("test", i+":"+j2.get(i).getNom());
	    }
	}*/
	
	// Methodes accès Modèle
	public Modele getModele(){return modele;}
	public boolean estNouveauMatch(){return modele.estNouveauMatch();}
	public boolean estNouveauSet(){return modele.estNouveauSet();}
	public boolean estNouveauPoint(){return modele.estNouveauPoint();}

	public String getEtatAuto(){return modele.getEtatAuto();}
	public int getService(){return modele.getService();}
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
	// A MODIFIER POUR ID ET POSTE JOUEUR
	public boolean soumettreAction(int j1, int j2, String type, int note)
	{
		System.out.println("J1 est " + j1);
		System.out.println("J2 est " + j2);
		int eq = 0;
		if (j1 < 12){eq = 1;}
		
		Action action = new Action(0, type);
		System.out.println("Joueur " + modele.getJoueur(j1).getNom() + " effectue un " + type + " " + note + " sur le joueur " + modele.getJoueur(j2).getNom());
		ActionJoueur act = new ActionJoueur(0, modele.getSet(), action, modele.getJoueur(j1), modele.getNumPoint(), note, 0);
		modele.ajouterAction(act);
		modele.setJSuiv(j2);
		
		if (note == 2)
		{
			modele.setGagne(eq);
		}else if (note == -2){modele.setGagne((eq+1)%2);}
		
		if (modele.getGagne() == -1)
		{
			modele.avancerAuto(type);
			modele.setNouveauPoint(false);
		}
		else 
		{
			System.out.println("Equipe " + modele.getGagne() + " remporte le point");
			System.out.println("Test si changement de service : " + modele.getService() + " VS " + modele.getGagne());
			if (modele.getService() != modele.getGagne())
			{
				modele.setService(modele.getGagne());
				modele.setRotation(modele.getGagne());
				
			}
			modele.resetAuto();
			modele.setNouveauPoint(true);
			soumettrePoint();
			modele.incrementePoint(modele.getGagne());
			modele.setGagne(-1);
			modele.afficherScore();
			
			Set set = modele.getSet();
			if ((set.getScoreEquipeDomicile() >= 25 || set.getScoreEquipeExterieur() >= 25) && (Math.abs(set.getScoreEquipeDomicile()-set.getScoreEquipeExterieur()) >= 2))
			{
				System.out.println("Gain de set détecté !");
				modele.setNouveauSet(true);
				
			}
			
			
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

	