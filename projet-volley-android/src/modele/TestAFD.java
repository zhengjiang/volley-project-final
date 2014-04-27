/**
	* @author : Warren BANGUET
	* @brief : Petite classe de test de l'automate
	*/

package modele;

import java.util.ArrayList;
import java.util.Iterator;



public class TestAFD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Automate af;
		af = new Automate();
		af.transition("se");
		af.transition("re");
		af.transition("pa");
		af.transition("at");
		af.transition("re");
		af.transition("pa");
		af.transition("at");
		
		
		System.out.println("Etat courant : " + af.getEtat());
		System.out.println("Etats suivants possibles : ");
		ArrayList<String> etats = af.getEtats();
		Iterator<String> it = etats.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next() + " , ");
		}
		


	}

}
