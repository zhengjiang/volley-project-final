package modele;

import android.os.Parcel;
import android.os.Parcelable;


public class Joueur implements Parcelable {
	private int id;
	private String nom;
	private int taille;
	private int age;
	private int poste;
	
	public Joueur(int id, String nom, int taille, int age, int poste) {
		this.id = id;
		this.nom = nom;
		this.taille = taille;
		this.age = age;
		this.poste = poste;
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

	public int getPosteEnCours() {
		return poste;
	}

	public void setPosteEnCours(int poste) {
		this.poste = poste;
	}
	
	//Verification des attributs
	public boolean nomEstValide()
	{
		return this.nom.trim().length()!=0;
	}
	
	public boolean tailleEstValide()
	{
		return this.taille >= 0 && Integer.valueOf(this.taille).toString().length()==3;
	}
	
	public boolean ageEstValide()
	{
		return this.age >= 0 && Integer.valueOf(this.age).toString().length()<=2;
	}
	
	public boolean posteEstValide()
	{
		return (this.poste>=1 && this.poste<=6);
	}
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(nom);
		out.writeInt(taille);
		out.writeInt(age);
		out.writeInt(poste);
	}
	
    public static final Parcelable.Creator<Joueur> CREATOR = new Parcelable.Creator<Joueur>() {
        public Joueur createFromParcel(Parcel in) {
            return new Joueur(in);
        }

        public Joueur[] newArray(int size) {
            return new Joueur[size];
        }
    };

    private Joueur(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        taille = in.readInt();
        age = in.readInt();
        poste = in.readInt();
    }
}


