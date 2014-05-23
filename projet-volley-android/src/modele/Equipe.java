/**
* @author : Depret Axel
* Classe Equipe
*/

package modele;

public class Equipe 
{
	protected int id;
	protected String nom;
	protected String entraineur;
	
	/**
	* @constructor
	*/
	public Equipe(int idEquipe, String nomEquipe, String nomEntraineur)
	{
		this.id=idEquipe;
		this.nom=nomEquipe;
		this.entraineur=nomEntraineur;
	}

	/**
	* @getter
	* @setter
	*/
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

	public String getEntraineur() {
		return entraineur;
	}

	public void setEntraineur(String entraineur) {
		this.entraineur = entraineur;
	}

	/**
	* Verifie si le nom est valide
	* @return : retourne vrai si le nom de l'équipe n'est pas vide
	*/
	public boolean nomEstValide()
	{
		return (this.nom.trim().length()!=0);
	}
	
	/**
	* Verifie si le nom de l'entraineur est valide
	* @return : retourne vrai si le nom de l'entraineur de l'équipe n'est pas vide
	*/
	public boolean nomEntraineurEstValide()
	{
		return (this.entraineur.trim().length()!=0);
	}
	
	/**
	* Verifie si deux équipe sont égales
	* @return : retourne vrai si l'équipe est égale à l'équipe e, autrement dit si leurs id's sont egaux
	*/
	public boolean equipeEgale(Equipe e)
	{
		return (this.id == e.getId());
	}

	/**
	* toString() : pour affichage en console de l'instance
	*/	
	public String toString()
	{
		return "n° Equipe : "+this.id+"\n"+"nom Equipe : "+this.nom+"\n"+"nom Entraineur Equipe : "+this.entraineur+"\n";
	}
		
}
