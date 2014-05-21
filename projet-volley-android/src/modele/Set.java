/**
* @author : Depret Axel
* Classe Match
*/

package modele;

import java.util.*;

public class Set
{
	private int id;
	private int numSet;
	private int scoreEquipe1;
	private int scoreEquipe2;
	private Match match;
	
	/*
	* @constructor
	*/
	public Set(int id,int numSet,int scoreEquipe1,int scoreEquipe2,Match match)
	{
		this.id=id;
		this.numSet=numSet;
		this.scoreEquipe1=scoreEquipe1;
		this.scoreEquipe2=scoreEquipe2;
		new ArrayList<Point>();
		this.match=match;
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
	
	public int getNumSet()
	{
		return this.numSet;
	}
	
	public void setNumSet(int numSet)
	{
		this.numSet=numSet;
	}
	
	public int getScoreEquipeDomicile()
	{
		return this.scoreEquipe1;
	}
	
	public void setScoreEquipeDomicile(int scoreEquipe1)
	{
		this.scoreEquipe1=scoreEquipe1;
	}

	public int getScoreEquipeExterieur()
	{
		return this.scoreEquipe2;
	}
	
	public void setScoreEquipeExterieur(int scoreEquipe2)
	{
		this.scoreEquipe1=scoreEquipe2;
	}
	
	public Match getMatch()
	{
		return this.match;
	}
	
	public void setMatch(Match match)
	{
		this.match=match;
	}
	

	/*
	* Augmente de 1 le score de l'equipe à domicile
	*/
	public void scoreEquipe1Plus()
	{
		this.scoreEquipe1++;
	}

	/*
	* Augmente de 1 le score de l'equipe à extérieur
	*/
	public void scoreEquipe2Plus()
	{
		this.scoreEquipe2++;
	}

	/*
	* Diminue de 1 le score de l'equipe à domicile
	*/
	public void scoreEquipe1Moins()
	{
		this.scoreEquipe1--;
	}

	/*
	* Diminue de 1 le score de l'equipe à extérieur
	*/
	public void scoreEquipe2Moins()
	{
		this.scoreEquipe2--;
	}
	
	/*
	* Verifie si le set est gagnant
	* @return : retourne l'id de l'equipe si le set a été remporté par l'équipe sinon renvoie -1 si le set n'a pas été pas gagner
	*/
	public int setEstGagnant()
	{
		if (this.numSet==5)// si il s'agit du cinquieme set
		{
			// si le score d'une des 2 équipes a atteind 15 points ou bien plus et que la différence entre les deux scores est de 2 points minimum alors on renvoie l'id de l'equipe sinon on renvoie -1
			if ( (this.scoreEquipe1>=15) && ((this.scoreEquipe1-this.scoreEquipe2)>=2) )
			{
				return this.match.getEquipeDomicile().getId();
			}
			else if ( (this.scoreEquipe2>=15) && ((this.scoreEquipe2-this.scoreEquipe1)>=2) ) 
			{
				return this.match.getEquipeExterieur().getId();
			}
			else
			{
				return -1;
			}	
		}
		else // sinon
		{
			// si le score d'une des 2 équipes a atteind 25 points ou bien plus et que la différence entre les deux scores est de 2 points minimum alors on renvoie l'id de l'equipe sinon on renvoie -1
			if ( (this.scoreEquipe1>=25) && ((this.scoreEquipe1-this.scoreEquipe2)>=2) ) 
			{
				return this.match.getEquipeDomicile().getId();
			}
			else if ( (this.scoreEquipe2>=25) && ((this.scoreEquipe2-this.scoreEquipe1)>=2) ) 
			{
				return this.match.getEquipeExterieur().getId();
			}
			else
			{
				return -1;
			}	
		}
	}
	
	/*
	* Verifie si le match est gagné 
	* @param : une liste de Set 
	* @return : retourne vrai si le match est terminé
	*/		 
	public boolean matchEstGagnant(ArrayList<Set> setMatch)
	{
		int nbSetGagnantEquipeDomicile = 0;
		int nbSetGagnantEquipeExterieur = 0;

		// compte le nombre de set gagner par équipe
		for (int i=0;i<setMatch.size();i++)
		{
			int idEquipeSetGagnant = setMatch.get(i).setEstGagnant();

			if (idEquipeSetGagnant==this.match.getEquipeDomicile().getId())
			{
				nbSetGagnantEquipeDomicile++;	
			}
			else if (idEquipeSetGagnant==this.match.getEquipeExterieur().getId())
			{
				nbSetGagnantEquipeExterieur++;
			}
		}
	
		return ( (nbSetGagnantEquipeDomicile==3) || (nbSetGagnantEquipeExterieur==3) ); // renvoie vrai si une des deux équipes à 3 set gagnant et donc a remporté le match
	}

	/*
	* toString() : pour affichage en console de l'instance
	*/
	public String toString()
	{
		return "id Set : "+this.id+"\n"+"n° Set : "+this.numSet+"\n"+"Score : "+this.scoreEquipe1+" - "+this.scoreEquipe2+"\n\n"+this.match.toString();
	}	

}
