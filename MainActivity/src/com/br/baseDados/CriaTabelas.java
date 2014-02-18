package com.br.baseDados;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

public class CriaTabelas {
	
	// Nome do Banco de Dados
	public static final String NOME_BANCO = "TesteDeveloper";
	
	// Controle da versão do banco de dados
	private static final int VERSAO_BD = 1;
	
	// classe utilitaria para abrir, criar e ultilizar o banco de dados..
	private CriaBanco bd;
	
	// Script para fazer drop na tabela
	private static String[] DELETA_TABELA = new String[] {
			"DROP TABLE IF EXISTS MARCA", 
			"DROP TABLE IF EXISTS MODELO"};
	
	// Script para criar as tabelas
	private static final String[] CRIA_TABELA = new String[] {
		//Tabela MARCA
		"CREATE TABLE [MARCA] (" +
			"[ID] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY AUTOINCREMENT, " +
			"[Nome] TEXT, " +
			"[DESCRICAO] TEXT NOT NULL ON CONFLICT FAIL);",

	// Tabela Configurações do sistema
		"CREATE TABLE [MODELO] ("+
				  "[ID] VARCHAR, "+
				  "[DATA] VARCHAR, "+            
				  "[DESCRICAO] VARCHAR, "+
				  "[PRECO] VARCHAR, "+ 
				  "[STATUS] VARCHAR, "+ 
				  "[SNAPSHOT] VARCHAR, "+ 
				  "[DESCRICAO] VARCHAR);"
				  };
	

	// Construtor da Classe.
	public CriaTabelas(Context ctx) {
		// passa os parametros para a construtor da classe criaBanco
		  try {
				bd = new CriaBanco(ctx, CriaTabelas.NOME_BANCO, CriaTabelas.VERSAO_BD,
						CriaTabelas.CRIA_TABELA, CriaTabelas.DELETA_TABELA);
				// abre o banco no modo escrita para alterar também
				bd.getWritableDatabase();
				
		    }catch(SQLiteConstraintException  e){
		    	
		    } finally{
		        bd.close();
		    }
	
		
  
	}

}
