package observer;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

	public static enum ACAO {
		Listar, Incluir, Alterar, Excluir
	};

	private List<ACAO> acoes = new ArrayList<ACAO>(); // alterar para Set

	public boolean getPermissao(ACAO acao) {
		for (ACAO a : this.acoes) {
			if (acao == a)
				return (true);
		}
		return (false);
	}

	public void setPermissao(ACAO acao) {
		this.acoes.add(acao);
	}

}
