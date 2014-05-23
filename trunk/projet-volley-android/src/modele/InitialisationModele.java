package modele;
import java.util.*;

public class InitialisationModele {
	
	public InitialisationModele()
	{
		
	}
	
	public static ArrayList<Equipe> initEquipes()
	{
		ArrayList<Equipe> equipes = new ArrayList<Equipe>();
		
		equipes.add(new Equipe(1,"RC Lens","Antoine Kombouare"));
		equipes.add(new Equipe(2,"PSV eindhoven","Louis Van Gaal"));
		equipes.add(new Equipe(3,"PSG","Laurent Blanc"));
		equipes.add(new Equipe(4,"OM","Jose Anigo"));
		equipes.add(new Equipe(5,"Le Mans FC","Regis Brouard"));
		
		return equipes;
	}
	
	public static ArrayList<Joueur> initJoueurs()
	{
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		joueurs.add(new Joueur(1,"Bazia",189,20,1));
		joueurs.add(new Joueur(2,"Barguil",188,23,6));
		joueurs.add(new Joueur(3,"Depret",182,21,2));
		joueurs.add(new Joueur(4,"Benichou",195,28,3));
		joueurs.add(new Joueur(5,"Banguet",178,22,4));
		joueurs.add(new Joueur(6,"Dupont",189,20,2));
		joueurs.add(new Joueur(7,"Durant",189,20,2));
		joueurs.add(new Joueur(8,"Yousfi",189,23,4));
		joueurs.add(new Joueur(9,"Aboud",189,20,4));
		joueurs.add(new Joueur(10,"Hamdaoui",169,20,6));
		joueurs.add(new Joueur(11,"Iotioa",189,23,1));
		joueurs.add(new Joueur(12,"Alphonse",189,20,3));
		joueurs.add(new Joueur(13,"Jean",180,21,6));
		joueurs.add(new Joueur(14,"Brisset",170,22,1));
		joueurs.add(new Joueur(15,"Malmassari",189,20,4));
		joueurs.add(new Joueur(16,"Fachini",190,23,5));
		joueurs.add(new Joueur(17,"Dupont",180,30,3));
		joueurs.add(new Joueur(18,"Martin",170,25,3));
		
		return joueurs;
	}
	
	public static ArrayList<JoueurEquipe> initJoueurEquipe()
	{
		ArrayList<Equipe> equipes = InitialisationModele.initEquipes();
		ArrayList<Joueur> joueurs = InitialisationModele.initJoueurs();
		
		ArrayList<JoueurEquipe> joueurEquipe = new ArrayList<JoueurEquipe>();
		
		joueurEquipe.add(new JoueurEquipe(1,joueurs.get(0),equipes.get(0),14,true));
		joueurEquipe.add(new JoueurEquipe(2,joueurs.get(1),equipes.get(0),13,true));
		joueurEquipe.add(new JoueurEquipe(3,joueurs.get(2),equipes.get(0),12,true));
		joueurEquipe.add(new JoueurEquipe(4,joueurs.get(3),equipes.get(0),11,true));
		joueurEquipe.add(new JoueurEquipe(5,joueurs.get(4),equipes.get(0),10,true));
		joueurEquipe.add(new JoueurEquipe(6,joueurs.get(5),equipes.get(0),9,true));
		joueurEquipe.add(new JoueurEquipe(7,joueurs.get(6),equipes.get(1),8,true));
		joueurEquipe.add(new JoueurEquipe(8,joueurs.get(7),equipes.get(1),7,true));
		joueurEquipe.add(new JoueurEquipe(9,joueurs.get(8),equipes.get(1),6,true));
		joueurEquipe.add(new JoueurEquipe(10,joueurs.get(9),equipes.get(1),5,true));
		joueurEquipe.add(new JoueurEquipe(11,joueurs.get(10),equipes.get(1),4,true));
		joueurEquipe.add(new JoueurEquipe(12,joueurs.get(11),equipes.get(3),3,true));
		joueurEquipe.add(new JoueurEquipe(13,joueurs.get(12),equipes.get(3),2,true));
		joueurEquipe.add(new JoueurEquipe(14,joueurs.get(13),equipes.get(3),1,true));
		joueurEquipe.add(new JoueurEquipe(15,joueurs.get(14),equipes.get(3),6,true));
		joueurEquipe.add(new JoueurEquipe(16,joueurs.get(15),equipes.get(3),7,true));
		joueurEquipe.add(new JoueurEquipe(17,joueurs.get(16),equipes.get(1),9,true));
		joueurEquipe.add(new JoueurEquipe(18,joueurs.get(17),equipes.get(1),10,true));
		
		
		return joueurEquipe;
	}

}
