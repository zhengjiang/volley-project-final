/**
* @author : Bazia
*/

package modele;

public class ActionJoueur {
	private int id;
	private Match match;
	private Action action;
	private Joueur joueur;
	private int numPoint;
	private int note;
	
	public ActionJoueur(int id, Match match, Action action, Joueur joueur, int numPoint, int note) {
		this.id = id;
		this.match = match;
		this.action = action;
		this.joueur = joueur;
		this.numPoint = numPoint;
		this.note = note;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Match getMatch() {
		return match;
	}
	
	public void setMatch(Match match) {
		this.match = match;
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
	
	
}
