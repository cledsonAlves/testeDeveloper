package com.br.baseDados;

import android.content.Context;

public class ManipulaBanco {
	Banco bd;
	private Context ctx;

	// Construtor pedindo um contexto..
	public ManipulaBanco(Context ctx) {
		this.ctx = ctx;
		bd = new Banco(ctx);
	}

}
