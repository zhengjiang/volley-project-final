package modele;

import java.util.ArrayList;

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
	
	

}
