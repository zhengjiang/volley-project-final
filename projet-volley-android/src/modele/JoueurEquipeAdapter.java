package com.example.creationmatchs;

import java.util.ArrayList;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class JoueurEquipeAdapter extends ArrayAdapter<JoueurEquipe> {
	private ArrayList<JoueurEquipe> arrayListJoueurEquipe;
	
	public JoueurEquipeAdapter(Context context, int textViewResourceId, ArrayList<JoueurEquipe> arrayListJoueurEquipe) {
		super(context, textViewResourceId, arrayListJoueurEquipe);
		this.arrayListJoueurEquipe = arrayListJoueurEquipe;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.layout_adapter, null);
		}
		
		
		JoueurEquipe i = arrayListJoueurEquipe.get(position);
		if (i != null) {
			TextView nom = (TextView) v.findViewById(R.id.elementListe);
			nom.setText(i.getJoueur().getNom());
		}
		
		return v;

	}
	
}
