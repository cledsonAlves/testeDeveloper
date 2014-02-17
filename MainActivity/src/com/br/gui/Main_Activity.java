package com.br.gui;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.br.adaptadores.AdaptadorDetalhes;
import com.br.adaptadores.AdaptadorMarca;
import com.br.logica.LeituraRest;
import com.br.objetos.Marca;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main_Activity extends SherlockFragmentActivity implements
		ActionBar.TabListener {
	private ProgressDialog dialog;
	private final Handler handler = new Handler();
	private boolean useLogo = false;
	private boolean showHomeUp = false;

	ListView listaItens = null;
	ListView listaDetalhes = null;

	AdaptadorMarca adapter;
	AdaptadorDetalhes adapterDetalhes;
	List<Marca> listaMarcas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kelma);
		final ActionBar ab = getSupportActionBar();

		// seta o logo	default 
		ab.setDisplayHomeAsUpEnabled(showHomeUp);
		ab.setDisplayUseLogoEnabled(useLogo);

		// navs bar menu
		ab.addTab(ab.newTab().setText("Marca ").setTabListener(this));
		ab.addTab(ab.newTab().setText("Categoria ").setTabListener(this));

		showTabsNav();
		listaItens = (ListView) findViewById(R.id.listViewMarcas);
		listaDetalhes = (ListView) findViewById(R.id.listViewDetalhes);

		// adaptador da lista customizado
		listaItens.setAdapter(adapter);

		listaItens.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView arg0, View view, int position,
					long index) {

				// cria objeto da marca escolhida
				Marca marca = (Marca) listaItens.getAdapter().getItem(position);

				AdaptadorDetalhes.descricaoMarca = marca.getDescription();
				adapterDetalhes = new AdaptadorDetalhes(
						getApplicationContext(), marca.getProduct_collection());
				adapterDetalhes.notifyDataSetChanged();
				handler.postDelayed(new Runnable() {
					public void run() {
						// atualiza o listView detalhes ...
						listaDetalhes.setAdapter(adapterDetalhes);
					}
				}, 100);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main_menu, menu);
		final MenuItem refresh = (MenuItem) menu.findItem(R.id.menu_refresh);
		refresh.setOnMenuItemClickListener(new OnMenuItemClickListener() {	
			public boolean onMenuItemClick(MenuItem item) {
				handler.postDelayed(new Runnable() {
					public void run() {
						refresh.setActionView(null);
					}
				}, 1000);
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:

			handler.postDelayed(new Runnable() {
				public void run() {
					// chama o webservice
					new TarefaWS().execute();
					;
				}
			}, 1000);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showTabsNav() {
		ActionBar ab = getSupportActionBar();
		if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
			ab.setDisplayShowTitleEnabled(false);
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}
	}

	// Monitar este código abaixo
	@SuppressLint("NewApi")
	private String getDados() {
		LeituraRest leitura = new LeituraRest();

		String result = leitura.getRESTFileContent("http://soa.coderockr.com/brand");
		
		//  verifica se baixou resultados 
		if (result.isEmpty()){
			Toast.makeText(this, "Não foi possível baixar informações!", Toast.LENGTH_LONG).show();
		}else {
		// passa o json em uma lista de objetos
		listaMarcas = new Gson().fromJson(result, new TypeToken<List<Marca>>() {}.getType());

		//  teste de recebimento de dados
		for (int i = 0; i < listaMarcas.size(); i++) {
			Log.i("Marca: ", "" + listaMarcas.get(i).getName());
			for (int j = 0; j < listaMarcas.get(i).getProduct_collection()
					.size(); j++) {
				Log.i("Modelo: ", ""
						+ listaMarcas.get(i).getProduct_collection().get(j)
								.getDescription());
			}
		}

		adapter = new AdaptadorMarca(this, listaMarcas);
		adapter.notifyDataSetChanged();
		
		// cria objeto da marca default
		Marca marca = (Marca) listaMarcas.get(0);
		
		AdaptadorDetalhes.descricaoMarca = marca.getDescription();
		adapterDetalhes = new AdaptadorDetalhes(
				getApplicationContext(), listaMarcas.get(0).getProduct_collection());
		
		
		handler.postDelayed(new Runnable() {
			public void run() {
				listaItens.setAdapter(adapter);
				listaDetalhes.setAdapter(adapterDetalhes);

				;

			}
		}, 1000);
		}
		return result;
	}

	// classe ansicrona interna
	class TarefaWS extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(Main_Activity.this, "Aguarde",
					"Atualizando Informações...");

		}

		@Override
		protected String doInBackground(Void... params) {
			return getDados();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();

		}

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// chama o webservice
		new TarefaWS().execute();

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
	// Metodo chamado automaticamente para rotacionar a tela não chamar o oncreate
	// apagando os itens do listview 
	@Override  
    public void onConfigurationChanged(Configuration newConfig) {  
        super.onConfigurationChanged(newConfig);  
    } 
	
	

	

}
