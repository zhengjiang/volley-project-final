/**
* @author : Depret Axel
* Classe Competition
*/

package modele;

import java.util.Date;

public class Competition
{
	protected int id;
	protected int annee;
	protected String nom;
	protected String type;


	/*
	* @constructor
	*/
	public Competition(int idCompetition, int anneCompetition, String nomCompetition, String typeCompetition)
	{
		this.id=idCompetition;
		this.annee=anneCompetition;
		this.nom=nomCompetition;
		this.type=typeCompetition;
	}

	/*
	* @getter
	* @setter
	*/
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id=id;
	}

	public int getAnnee()
	{
		return this.annee;
	}

	public void setAnnee(int annee)
	{
		this.annee=annee;
	}
		
	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type=type;
	}


	/*
	* Verifie si l'année est valide
	* @return : retourne vrai si l'année est supérieur ou égale à l'année en cours 
	*/
	public boolean anneeEstValide()
	{
		Date dateDuJour = new Date();

		return ( this.annee >= (dateDuJour.getYear()+1900) );
	}
	
	/*
	* Verifie si le nom de la compétition est valide
	* @return : retourne vrai si le nom n'est pas vide
	*/
	public boolean nomEstValide()
	{
		return (this.nom.length()!=0);
	}
	
	/*
	* Verifie si le type de la compétition est valide
	* @return : retourne vrai si le type n'est pas vide
	*/
	public boolean typeEstValide()
	{
		return (this.type.length()!=0);
	}
	
	/*
	* toString() : pour affichage en console de l'instance
	*/
	public String toString()
	{
		return "n° Competition : "+this.id+"\n"+"annee Competition : "+this.annee+"\n"+"nom Competition : "+this.nom+"\n"+"type : "+this.type+"\n";
	}

}
	
