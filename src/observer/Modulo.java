package observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import observer.Permissao.ACAO;

public abstract class Modulo {
	protected Map<String, Permissao> permissoes = new HashMap<String, Permissao>();
	protected List<Observer> observers = new ArrayList<Observer>();

	public void verifica(String login, ACAO acao) throws Exception {
		if (!this.permissoes.get(login).getPermissao(acao)) {
			for (Observer observer : this.observers) {
				observer.update(this);
			}
			// exceção deveria ser lançada pelos observers
			throw new Exception(login + " nao tem permissao " + acao);
		}
	}

	public void attach(Observer observer) {
		this.observers.add(observer);
	}

	public void dettach(Observer observer) {
		this.observers.remove(observer);
	}

}
