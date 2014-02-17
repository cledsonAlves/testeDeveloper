package com.br.adaptadores;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.br.gui.R;
import com.br.objetos.Marca;
import com.loopj.android.image.SmartImageView;

public class AdaptadorMarca extends BaseAdapter {
	Context ctx;
	List<Marca> listaMarcas;
    final float mWeight = 1f;
	final int MARGIN = 16;

	//  construtor 
	public AdaptadorMarca(Context ctx, List<Marca>lista){
		this.ctx = ctx;
		this.listaMarcas = lista;		
	}
		
	public int getCount() {
		// TODO Auto-generated method stub
		return listaMarcas.size() ;
	}

	public Object getItem(int posicao) {
		// TODO Auto-generated method stub
		return listaMarcas.get(posicao);
	}

	public long getItemId(int posicao) {
		// TODO Auto-generated method stub
		return posicao;
	}

	public View getView(int posicao, View convertView, ViewGroup parent) {
		// recupera a posição atual do produto
		Marca marca = listaMarcas.get(posicao);
		// infla a  tela produtos 
		
		
		View tela_produtos = convertView;    
	    
		if (tela_produtos == null) {    
		        LayoutInflater inflater = (LayoutInflater) ctx      
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);      
		        tela_produtos = inflater.inflate(R.layout.marca, parent, false);      
		}   
		
		SmartImageView imgLogo = (SmartImageView) tela_produtos.findViewById(R.id.ImageViewLogo);
		imgLogo.setImageUrl(marca.getImage());

	
		return tela_produtos;
		
		
	
	}

}
