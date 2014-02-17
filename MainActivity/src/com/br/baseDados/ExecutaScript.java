package com.br.baseDados;

public class ExecutaScript {
	public ExecutaScript() {

	}

	public String[] insertEstados() {
		// valores que o banco deve ter ao ser criado
		// pela primeira vez!
		return new String[] {
				"INSERT INTO [MARCA](ID,NOME,DESCRICAO) VALUES (1,'APPLE','TESTE');" };
	}

	public String[] insertProdutos() {
		return new String[] {
	
		"INSERT INTO MODELO() values ();"};
	}
}
