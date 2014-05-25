package modele;

import java.util.ArrayList;

import controleur.Controleur;

public class Point {
	
	ArrayList<ActionJoueur> actions;
	int gagne;
	
	
	public Point()
	{
		actions = new ArrayList<ActionJoueur>();
		gagne = -1;
	}
	
	public void ajouterAction(ActionJoueur a)
	{
		actions.add(a);
	}
	
	public void sauvegarderPoint(){
		Controleur c = Controleur.getInstance();
		c.jab.open();
		for(int i = 0; i < this.actions.size(); i++){
			c.jab.ajouter(this.actions.get(i));
		}
		c.jab.close();
	}

}
