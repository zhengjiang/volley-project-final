/**
* @author : Bazia
*/

package modele;

public class ActionJoueur {
	private int id;
	private Set set;
	private Action action;
	private Joueur joueur;
	private int numPoint;
	private int note;
	private int poste;
	
	public ActionJoueur(int id, Set set, Action action, Joueur joueur, int numPoint, int note, int poste) {
		this.id = id;
		this.set = set;
		this.action = action;
		this.joueur = joueur;
		this.numPoint = numPoint;
		this.note = note;
		this.poste = poste;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Set getSet() {
		return set;
	}
	
	public void setSet(Set set) {
		this.set = set;
	}
	
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public int getNumPoint() {
		return numPoint;
	}
	
	public void setNumPoint(int numPoint) {
		this.numPoint = numPoint;
	}
	
	public int getNote() {
		return note;
	}
	
	public void setNote(int note) {
		this.note = note;
	}
	
	public int getPoste() {
		return poste;
	}
	
	public void setPoste(int poste) {
		this.poste = poste;
	}
	
	
}
