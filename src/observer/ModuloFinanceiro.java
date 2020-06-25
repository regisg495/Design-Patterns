package observer;

import observer.Permissao.ACAO;

public class ModuloFinanceiro extends Modulo {

	public ModuloFinanceiro() {
		Permissao p1 = new Permissao();
		p1.setPermissao(ACAO.Listar);
		p1.setPermissao(ACAO.Incluir);
		p1.setPermissao(ACAO.Alterar);
		p1.setPermissao(ACAO.Excluir);
		this.permissoes.put("betito", p1);

		Permissao p2 = new Permissao();
		p2.setPermissao(ACAO.Listar);
		this.permissoes.put("borges", p2);
	}

	public void listar(String login) throws Exception {
		this.verifica(login, ACAO.Listar);
		System.out.println("Listar");
	}

	public void incluir(String login) throws Exception {
		this.verifica(login, ACAO.Incluir);
		System.out.println("Incluir");
	}

	public void alterar(String login) throws Exception {
		this.verifica(login, ACAO.Alterar);
		System.out.println("Alterar");
	}

	public void excluir(String login) throws Exception {
		this.verifica(login, ACAO.Excluir);
		System.out.println("Excluir");
	}

}
