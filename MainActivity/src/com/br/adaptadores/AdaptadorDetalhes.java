package com.br.adaptadores;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.gui.R;
import com.br.objetos.Marca;
import com.br.objetos.Produto_Colection;
import com.loopj.android.image.SmartImageView;

public class AdaptadorDetalhes extends BaseAdapter {
	Context ctx;
	List<Marca> listaMarcas;
	private List<Produto_Colection> listaDetalhes;
	public static String descricaoMarca;

	// construtor
	public AdaptadorDetalhes(Context ctx, List<Produto_Colection> lista) {
		this.ctx = ctx;
		this.listaDetalhes = lista;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return listaDetalhes.size();
	}

	public Object getItem(int posicao) {
		// TODO Auto-generated method stub
		return listaDetalhes.get(posicao);
	}

	public long getItemId(int posicao) {
		// TODO Auto-generated method stub
		return posicao;
	}

	public View getView(int posicao, View convertView, ViewGroup parent) {
		// recupera a posição atual 
		Produto_Colection modelo = listaDetalhes.get(posicao);

		View tela_produtos = convertView;

		if (tela_produtos == null) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			tela_produtos = inflater.inflate(R.layout.detalhes, parent, false);
		}

		// atualiza o xml
		TextView descricao = (TextView) tela_produtos.findViewById(R.id.textViewDescricao);
		descricao.setText(descricaoMarca);

		TextView descricaoProdA = (TextView) tela_produtos.findViewById(R.id.textViewDescricaoProdA);
		descricaoProdA.setText(modelo.getDescription());

		TextView priceA = (TextView) tela_produtos.findViewById(R.id.textViewPriceA);
		priceA.setText("R$" + modelo.getPrice());

		SmartImageView img = (SmartImageView) tela_produtos.findViewById(R.id.imageViewProdutoA);
		img.setImageUrl(modelo.getSnapshot());

		return tela_produtos;
	}

}
