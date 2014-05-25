
package modele;

import java.util.Date;
import java.text.*;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable {
	protected int id;
	protected Date date;
	protected String lieu;
	protected Equipe equipe1;
	protected Equipe equipe2;
	protected Competition competition;

	/**
	* @constructor
	*/
	public Match(int idMatch, String dateMatch, String lieuMatch, Equipe equipe1Match, Equipe equipe2Match, Competition competition)
	{
		this.id=idMatch;
	
		if (dateMatch.matches("(0[0-9]|[1-3][0-9])/(0[1-9]|1[0-2])/([0-9]{4})")) // on verifie que la chaine correspond au format date JJ/MM/AAAA
		{
			try {
				// transformation de la chaine en type Date
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				this.date = sdf.parse(dateMatch);
			}
			catch (Exception e) { e.printStackTrace(); }
		}
		else
		{
			this.date=null;
		}

		this.lieu=lieuMatch;
		this.equipe1=equipe1Match;
		this.equipe2=equipe2Match;
		this.competition = new Competition(0, 2014, "", "");
	}

	/**
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
	
	public String getDate() 
	{
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		return formater.format(this.date);
	}
	public Competition getCompetition(){return competition;}
	
	public void setDate(Date date)
	{
		this.date=date;
	}
	
	public String getLieu()
	{
		return this.lieu;
	}
	
	public void setLieu(String lieu)
	{
		this.lieu=lieu;
	}
	
	public Equipe getEquipeDomicile()
	{
		return this.equipe1;
	}
	
	public void setEquipeDomicile(Equipe equipe)
	{
		this.equipe1=equipe;
	}

	public Equipe getEquipeExterieur()
	{
		return this.equipe2;
	}
	
	public void setEquipeExterieur(Equipe equipe)
	{
		this.equipe2=equipe;
	}

	/**
	* Verifie si la date est valide
	* @return : retourne vrai si la date a bien été entré correctement et qu'elle est bien supérieur ou égale à la date du jour
	*/
	public boolean dateEstValide()
	{
		if (this.date!=null)
		{
			if (this.date.getDate()>=1) // si le jour est >= 1
			{
				if (this.date.getMonth() == 1) // si c'est le mois de février
				{
					int monAnnee = (this.date.getYear()+1900); 

					if ( ( monAnnee%100==0 && monAnnee%400==0 ) || ( monAnnee%100==0 && monAnnee%400==0 ) ) // si l'annee est bissextile, sinon, le nombre de jours doit etre <=  28 
					{
						if (this.date.getDate()<=29)
						{
							Date dateDuJour = new Date();
							return ( this.date.after(dateDuJour) || this.date.equals(dateDuJour) );	
						}
						else
						{
							return false;
						}
					}
					else // sinon, le nombre de jours doit etre <=  28
					{
						if (this.date.getDate()<=28)
						{
							// on regarde ensuite si la date est supérieur ou bien egale à la date du jour
							Date dateDuJour = new Date();
							return ( this.date.after(dateDuJour) || this.date.equals(dateDuJour) );	
						}
						else
						{
							return false;
						}
					}
				}
				else if ( (this.date.getMonth() == 3) || (this.date.getMonth() == 5) || (this.date.getMonth() == 8) || (this.date.getMonth() == 10) ) // si c'est le mois d'avril, de juin, de septembre ou bien de novembre
				{
					if (this.date.getDate()<=30) // le nombre de jours doit etre <= 30
					{
						// on regarde ensuite si la date est supérieur ou bien egale à la date du jour
						Date dateDuJour = new Date();
						return ( this.date.after(dateDuJour) || ( this.date.getDate()==dateDuJour.getDate() && this.date.getMonth()==dateDuJour.getMonth() && this.date.getYear()==dateDuJour.getYear() ) );
					}
					else
					{
						return false;
					}
				}
				else // si ce sont les autres mois 
				{
					if (this.date.getDate()<=31) // le nombre de jours doit etre <= 30
					{
						// on regarde ensuite si la date est supérieur ou bien egale à la date du jour
						Date dateDuJour = new Date();
						return ( this.date.after(dateDuJour) || ( this.date.getDate()==dateDuJour.getDate() && this.date.getMonth()==dateDuJour.getMonth() && this.date.getYear()==dateDuJour.getYear() ) );
					}
					else
					{
						return false;
					}
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	* Verifie si le lieu est valide
	* @return : retourne vrai si le nom du lieu n'est pas vide
	*/
	public boolean lieuEstValide()
	{
		return (this.lieu.trim().length()!=0);
	}
	
	/**
	* Verifie si les équipes sont valide
	* @return : retourne vrai si les equipes ne sont pas egales
	*/
	public boolean equipesValide()
	{
		return (!this.equipe1.equipeEgale(this.equipe2));
	}
	
	/**
	* toString() : pour affichage en console de l'instance
	*/	
	public String toString()
	{
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

		return "n° Match : "+this.id+"\n"+"date Match : "+formater.format(this.date)+"\n"+"lieu : "+this.lieu+"\n\n"+"equipe domicile Match : \n"+this.equipe1.toString()+"\n"+"equipe exterieur Match : \n"+this.equipe2.toString()+"\n"+"competition Match : \n";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(lieu);
		out.writeParcelable(equipe1, flags);
		out.writeParcelable(equipe2, flags);
	}
	
    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    private Match(Parcel in) {
        id = in.readInt();
        lieu = in.readString();
        equipe1 = in.readParcelable(getClass().getClassLoader());
        equipe2 = in.readParcelable(getClass().getClassLoader());
    }

}
