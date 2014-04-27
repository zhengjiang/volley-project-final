package controleur;
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
		System.out.println("Joueur " + modele.getJoueur(j1).getNom() + " effectue un " + type + "sur le joueur " + modele.getJoueur(j2).getNom());
		
		
		return true;
	}
}

	