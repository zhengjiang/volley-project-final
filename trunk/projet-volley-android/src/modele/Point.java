package modele;

import java.util.ArrayList;

public class Point {
	
	ArrayList<Action> actions;
	int gagne;
	
	
	public Point()
	{
		actions = new ArrayList<Action>();
		gagne = -1;
	}
	
	public void ajouterAction(Action a)
	{
		actions.add(a);
	}

}
