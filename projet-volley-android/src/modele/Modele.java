package modele;

import java.util.ArrayList;

public class Modele {
	/*classe qui servira à "stocker" les éléments "changeant" : 
		- Match courant
		- Joueurs équipes courant + poste
		- Automate courant (quel état etc, ...)
	*/
	Match match;
	ArrayList<Joueur> eqRouge;
	ArrayList<Joueur> eqBleu;
	ArrayList<Set> score;
	Automate automate;
	Point courant;
	int jSuiv;
	boolean nouveauMatch;
	boolean nouveauSet;
	boolean nouveauPoint;
	int rotation;
	int service;
	int gagne;
	int numPoint;
	
	
	public Modele()
	{
		automate = new Automate();
		score = new ArrayList<Set>(5);
		nouveauMatch = true;
		nouveauSet = true;
		numPoint = 0;
		jSuiv = -5;
		courant = new Point();
		initMatchTest();
	}
	
	public boolean estNouveauMatch(){return nouveauMatch;}
	public boolean estNouveauSet(){return nouveauSet;}
	public boolean estNouveauPoint(){return nouveauPoint;}

	public ArrayList<String> getEtatsAuto(){return automate.getEtats();}
	public String getEtatAuto(){return automate.getEtat();}
	public Match getMatch(){return match;}
	public ArrayList<Set> getTabSet(){return score;}
	public Set getSet(){return score.get(score.size()-1);}
	public int getNumPoint(){return numPoint;}
	public int getGagne(){return gagne;}
	public int getJSuiv(){return jSuiv;}
	public int getRotation(){return rotation;}
	
	public void setGagne(int i){gagne = i;}
	public void setService(int i){service = i;}
	public void setRotation(int b){rotation = b;}
	public void setNouveauPoint(boolean bool){nouveauPoint = bool;}
	public void setNouveauMatch(boolean bool){nouveauMatch = bool;}
	public void setEquipe1(ArrayList<Joueur> tit)
	{
		for (Joueur j : tit){eqBleu.add(j);}
		
	}
	public void setEquipe2(ArrayList<Joueur> tit)
	{
		for (Joueur j : tit){eqRouge.add(j);}
		
	}


	public void setNouveauSet(boolean bool){nouveauSet = bool;}
	public void setJSuiv(int i){jSuiv = i;}
	
	private void initMatchTest()
	{
		eqRouge = new ArrayList<Joueur>(12);
		eqBleu = new ArrayList<Joueur>(12);
		Equipe eq1 = new Equipe(1, "Cadors", "Zlatan");
		Equipe eq2 = new Equipe(2, "Arsouilles", "Zlatan");
		Competition compet = new Competition(1, 2014, "test", "test");

		match = new Match(1, "01/01/2014", "Grosville", eq1, eq2, new Competition(0, 2014, "Championnat", "Championnat"));
		service = 0;
		gagne = -1;
		rotation = -1;
		nouveauPoint = true;
		
		eqBleu.add(0, new Joueur(1, "Bleu0", 180, 25, 0));
		eqBleu.add(1, new Joueur(2, "Bleu1", 180, 25, 1));
		eqBleu.add(2, new Joueur(3, "Bleu2", 180, 25, 2));
		eqBleu.add(3, new Joueur(4, "Bleu3", 180, 25, 3));
		eqBleu.add(4, new Joueur(5, "Bleu4", 180, 25, 4));
		eqBleu.add(5, new Joueur(6, "Bleu5", 180, 25, 5));
		eqBleu.add(6, new Joueur(7, "Bleu6", 180, 25, 6));
		eqBleu.add(7, new Joueur(8, "Bleu7", 180, 25, 7));
		eqBleu.add(8, new Joueur(9, "Bleu8", 180, 25, 8));
		eqBleu.add(9, new Joueur(10, "Bleu9", 180, 25, 9));
		eqBleu.add(10, new Joueur(11, "Bleu10", 180, 25, 10));
		eqBleu.add(11, new Joueur(12, "Bleu11", 180, 25, 11));

		eqRouge.add(0, new Joueur(13, "Rouge0", 180, 25, 0));
		eqRouge.add(1, new Joueur(14, "Rouge1", 180, 25, 1));
		eqRouge.add(2, new Joueur(15, "Rouge2", 180, 25, 2));
		eqRouge.add(3, new Joueur(16, "Rouge3", 180, 25, 3));
		eqRouge.add(4, new Joueur(17, "Rouge4", 180, 25, 4));
		eqRouge.add(5, new Joueur(18, "Rouge5", 180, 25, 5));
		eqRouge.add(6, new Joueur(19, "Rouge6", 180, 25, 6));
		eqRouge.add(7, new Joueur(20, "Rouge7", 180, 25, 7));
		eqRouge.add(8, new Joueur(21, "Rouge8", 180, 25, 8));
		eqRouge.add(9, new Joueur(22, "Rouge9", 180, 25, 9));
		eqRouge.add(10, new Joueur(13, "Rouge10", 180, 25, 10));
		eqRouge.add(11, new Joueur(24, "Rouge11", 180, 25, 11));
		
		
		
	}
	
	public void echangeBleu(int j1, int j2)
	{
		Joueur tmp;
		tmp = eqBleu.get(j1);
		eqBleu.set(j1, eqBleu.get(j2));
		eqBleu.set(j2, tmp);
		
	}
	
	public void echangeRouge(int j1, int j2)
	{
		Joueur tmp;
		tmp = eqRouge.get(j1);
		eqRouge.set(j1, eqRouge.get(j2));
		eqRouge.set(j2, tmp);
	}
	
	public Joueur getJoueur(int indice)
	{
		if (indice > 11)
		{
			indice = indice%12;
			return eqBleu.get(indice);
		}
		else {return eqRouge.get(indice);}
	}
	
	
	
	public int getService(){return service;}
	
	public void ajouterAction(ActionJoueur act)
	{
		courant.ajouterAction(act);
	}
	
	public void ajouterNouveauSet()
	{
		score.add(new Set(0, score.size()+1, 0, 0, match));
	}
	
	public void nouveauPoint()
	{
		courant = new Point();
	}
	
	public void avancerAuto(String type)
	{
		automate.transition(type);
	}
	
	public void resetAuto(){automate.reset();}
	
	public void incrementePoint(int i)
	{
		if (i==0){score.get(score.size()-1).scoreEquipe1Plus();}
		else{score.get(score.size()-1).scoreEquipe2Plus();}
	}
	
	public void afficherScore()
	{
		System.out.println("========================================");
		System.out.println("Set numero : " + score.size());
		System.out.println("Bleu - Rouge : " + score.get(score.size()-1).getScoreEquipeDomicile() + " - " + score.get(score.size()-1).getScoreEquipeExterieur());
		System.out.println("========================================");

	}
	
	
}
