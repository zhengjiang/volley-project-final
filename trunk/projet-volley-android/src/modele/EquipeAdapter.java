package modele;

import java.util.ArrayList;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EquipeAdapter extends ArrayAdapter<Equipe> {
	private ArrayList<Equipe> arrayListEquipe;
	
	public EquipeAdapter(Context context, int textViewResourceId, ArrayList<Equipe> arrayListEquipe) {
		super(context, textViewResourceId, arrayListEquipe);
		this.arrayListEquipe = arrayListEquipe;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.layout_adapter, null);
		}
		
		
		Equipe i = arrayListEquipe.get(position);
		if (i != null) {
			TextView nom = (TextView) v.findViewById(R.id.elementListe);
			nom.setText(i.getNom());
		}
		
		return v;

	}
	
}
