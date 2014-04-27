/**
* @author : Bazia
*/
/**
 * @author IKBAL
 */

package modele;

public class Joueur {
	private int id;
	private String nom;
	private int numero;
	private int taille;
	private int age;
	private int poste;
	
	public Joueur(int id, String nom, int numero, int taille, int age, int poste) {
		this.id = id;
		this.nom = nom;
		this.numero = numero;
		this.taille = taille;
		this.age = age;
		this.poste = poste;
	}
	
	public boolean nomValide(){
		if(this.nom.length() != 0){
			return true;
		}
		return false;
	}

	
	//GETTERS SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoste() {
		return poste;
	}

	public void setPoste(int poste) {
		this.poste = poste;
	}
	
	
}


