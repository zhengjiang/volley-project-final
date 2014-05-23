/**
* @author : Bazia
*/
/**
 * @author IKBAL
 */


package modele;

public class JoueurEquipe {
	private int id;
	private Joueur joueur;
	private Equipe equipe;
	private int numMaillot;
	private boolean enCours;
	
	
	
	public JoueurEquipe(int id, Joueur joueur, Equipe equipe, int numMaillot, boolean enCours) {
		this.id = id;
		this.joueur = joueur;
		this.equipe = equipe;
		this.numMaillot = numMaillot;
		this.enCours = enCours;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	public int getNumMaillot() {
		return numMaillot;
	}
	
	public void setNumMaillot(int numMaillot) {
		this.numMaillot = numMaillot;
	}
	
	public boolean isEnCours() {
		return enCours;
	}
	
	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
	
	//Verification des attributs
	public boolean numMaillotEstValide()
	{
		return this.numMaillot > 0;
	}
}
