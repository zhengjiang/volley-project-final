package modele;

import android.os.Parcel;
import android.os.Parcelable;


public class JoueurEquipe implements Parcelable {
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
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeParcelable(joueur, flags);
		out.writeParcelable(equipe, flags);
		out.writeInt(numMaillot);
		out.writeByte((byte) (enCours ? 1 : 0)); 
	}
	
    public static final Parcelable.Creator<JoueurEquipe> CREATOR = new Parcelable.Creator<JoueurEquipe>() {
        public JoueurEquipe createFromParcel(Parcel in) {
            return new JoueurEquipe(in);
        }

        public JoueurEquipe[] newArray(int size) {
            return new JoueurEquipe[size];
        }
    };

    private JoueurEquipe(Parcel in) {
        id = in.readInt();
        joueur = in.readParcelable(getClass().getClassLoader());
        equipe = in.readParcelable(getClass().getClassLoader());
        numMaillot = in.readInt();
        enCours = in.readByte() != 0; 
    }
	
	
}