/**
* @author : Warren BANGUET
* @brief : Fichier contenant l'implémentation de l'Automate ainsi que des Etats
*/
package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author : Warren BANGUET
* @brief : Classe permettant de représenter un Etat d'un automate. Utilise une HashMap pour les transitions propres à un Etat
* 		   et une liste donnant les différentes transitions possibles ordonnées par probabilité décroissante
*/
class Etat {
	private String nom;
	private boolean efinal;				// Vrai si l'Etat est final, faux sinon
	private ArrayList<String> prefere;
	private Map<String, Etat> transitions;
	
	/**
	* @author : Warren BANGUET
	* @brief : Constructeur d'Etat à 3 paramètres, concerne les Etats non finaux
	*/
	Etat(boolean efinal, String nom, ArrayList<String> prefere) 
	{
		  this.efinal = efinal;
		  this.nom = nom;
		  this.prefere = prefere;
		  this.transitions = new HashMap<String, Etat>();
	}
	
	/**
	* @author : Warren BANGUET
	* @brief : Constructeur d'Etat à 2 paramètres, sans liste ordonnée de transitions possibles, concerne les Etats non finaux
	*/
	Etat(boolean efinal, String nom) 
	{
		  this.efinal = efinal;
		  this.nom = nom;
		  this.prefere = new ArrayList<String>();
	}
	
	/**
	* @author : Warren BANGUET
	* @brief : Getter retournant le nom de l'Etat.
	*/
	String getNom(){return nom;}
	
	/**
	* @author : Warren BANGUET
	* @brief : Ajoute une transition à l'Etat dans la HashMap
	* @param String s - nom de la transition à effectuer, sert de clé dans la Hashmap.
	* @param Etat q - Etat d'arrivée de la transition.
	*/
	void ajouteTransition(String s, Etat q) {transitions.put(s, q);}
	
	/**
	* @author : Warren BANGUET
	* @brief : Teste si l'Etat est un Etat final.
	*/
	boolean estFinal() {return efinal;}
	
	/**
	* @author : Warren BANGUET
	* @brief : Effectue la transition dont l'étiquette est passée en paramètre.
	* @param String s - Etiquette de la transition à effectuer.
	* @return Etat - Etat d'arrivée de la transition
	*/
	Etat transition(String s) {return (Etat) transitions.get(s);}
	
	/**
	* @author : Warren BANGUET
	* @brief : Getter retournant la liste ordonnée des transitions possibles a partir de l'Etat
	*/
	ArrayList<String> transitionsPossibles(){return prefere;}
}

/**
* @author : Warren BANGUET
* @brief : Classe permettant de représenter l'Automate utilisé pendant la saisie d'un point. Ses seuls attributs sont l'Etat courant
*          qui est automatiquement mis à jour lorsqu'on effectue une transition, et l'Etat initial servant a remettre à zero l'automate.
*/
class Automate {
	
	private Etat init;
	private Etat courant;
	
	/**
	* @author : Warren BANGUET
	* @brief : Constructeur par défaut créant et initialisant les Etats et transitions de l'automate.
	*/
	Automate() 
	{
		Etat in, se, re, at, pa, bl, ga, pe, de;
		ArrayList<String> tmp;
		tmp = new ArrayList<String>();
		tmp.add("se");
		in = new Etat(false, "in", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("pa");
		tmp.add("at");
		re = new Etat(false, "re", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("re");
		tmp.add("at");
		se = new Etat(false, "se", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("pa");
		tmp.add("at");
		de = new Etat(false, "de", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("de");
		tmp.add("bl");
		tmp.add("at");
		tmp.add("pa");
		at = new Etat(false, "at", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("at");
		tmp.add("pa");
		pa = new Etat(false, "pa", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("de");
		tmp.add("bl");
		tmp.add("at");
		tmp.add("pa");
		bl = new Etat(false, "bl", tmp);
		ga = new Etat(true, "ga");
		pe = new Etat(true, "pe");
		
		in.ajouteTransition("se", se);
		in.ajouteTransition("pe", pe);
		in.ajouteTransition("ga", ga);
		
		se.ajouteTransition("re", re);
		se.ajouteTransition("at", at);
		se.ajouteTransition("ga", ga);
		se.ajouteTransition("pe", pe);
		
		re.ajouteTransition("pa", pa);
		re.ajouteTransition("at", at);
		re.ajouteTransition("ga", ga);
		re.ajouteTransition("pe", pe);
		
		at.ajouteTransition("de", re);
		at.ajouteTransition("pa", pa);
		at.ajouteTransition("at", at);
		at.ajouteTransition("bl", bl);
		at.ajouteTransition("ga", ga);
		at.ajouteTransition("pe", pe);
		
		pa.ajouteTransition("at", at);
		pa.ajouteTransition("pa", pa);
		pa.ajouteTransition("ga", ga);
		pa.ajouteTransition("pe", pe);
		
		bl.ajouteTransition("bl", bl);
		bl.ajouteTransition("pa", bl);
		bl.ajouteTransition("de", bl);
		bl.ajouteTransition("at", bl);
		bl.ajouteTransition("ga", bl);
		bl.ajouteTransition("pe", bl);
		
		de.ajouteTransition("pa", pa);
		de.ajouteTransition("at", at);
		de.ajouteTransition("ga", bl);
		de.ajouteTransition("pe", bl);
		
		courant = in;
		init = in;
	}
	
	/**
	* @author : Warren BANGUET
	* @brief : Remet l'automate à zero.
	*/
	void reset(){courant = init;}
	
	/**
	* @author : Warren BANGUET
	* @brief : Effectue la transition étiquetée par s à partir de l'Etat courant
	*/
	void transition(String s){courant = courant.transition(s);}
	
	/**
	* @author : Warren BANGUET
	* @brief : Getter retournant le nom de l'Etat courant.
	*/
	String getEtat(){return courant.getNom();}
	
	/**
	* @author : Warren BANGUET
	* @brief : Teste si l'Etat courant est un Etat final
	*/
	boolean estFinal(){return courant.estFinal();}
	/**
	* @author : Warren BANGUET
	* @brief : Getter retournant la liste ordonnée des transitions possibles à partir de l'Etat courant.
	*/
	ArrayList<String> getEtats(){return courant.transitionsPossibles();}
}
