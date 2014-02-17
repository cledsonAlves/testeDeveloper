package com.br.baseDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Banco {
	private static SQLiteDatabase db;
	private final static String NOME_BANCO = "TesteDeveloper";

	// private Context ctx;

	// Construtores da classe
	public Banco(Context ctx) {

		// this.ctx = ctx;
		// Abre o Banco de dados // NOME DO BANCO MODO DE ABERTURA NULLO
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	// M�todo retorna cursor com todos os dados da tabela
	public Cursor buscaDados(String tabela) {
		return db.query(tabela, null, null, null, null, null, null);
	}

	public Cursor buscaDados(String tabela1, String tabela2, String colunas[],String where) {
		return db.query(tabela1 + " INNER JOIN " + tabela2, colunas, where,
				null, null, null, null);
	}

	// M�todo retorna um determinado dado do banco
	public Cursor buscaDados(String tabela, String colunas[], String where) {
		return db.query(tabela, colunas, where, null, null, null, null);

	}
	

	// M�todo cadastra no banco
	public boolean cadastra(String tabela, ContentValues valores, Context ctx) {
		try {
			db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE,null);
			db.insert(tabela, null, valores);
			return true;
		} catch (SQLException erro) {
			erro.printStackTrace();
			return false;
		}
	}

	//  deleta um registro do banco 
	public boolean excluiRegistro(String tabela, String where, Context ctx) {
		try {
			db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE,null);
			db.delete(tabela, where, null);
			return true;
		} catch (SQLException erro) {
			erro.printStackTrace();
			return false;
		}
	}
	//  Atualiza um registro no banco 
	public int atualizaRegistro(String tabela, ContentValues valores, String where, String[] whereArgs){
		int count = db.update(tabela, valores, where, whereArgs);
		return count;
		
	}

	// fecha o banco
	public void fechaBanco() {
		db.close();
	}
	

}
