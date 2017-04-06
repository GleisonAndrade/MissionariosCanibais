package br.ufpi.ia.buscas;

import java.util.HashMap;
import java.util.LinkedList;

import br.ufpi.ia.estados.Estados;

public class BuscaEmProfundidade {

	private static BuscaEmProfundidade busca = new BuscaEmProfundidade();

	// Estado inicial.
	Estados inicio = new Estados(Estados.getMissionarios(),
			Estados.getCanibais(), 1, null);

	public Estados buscar() {

		// Iniciar o algoritimo de Busca em profundidade.
		Estados resultado = busca.buscaEmProfundidade(inicio);
		return resultado;
	}

	public void imprimirResultado(Estados resultado) {
		// Verificar se a solu��o foi encontrada.
		if (resultado == Estados.getNaoEncontrada()) {
			Estados.resultado += "Solucao nao encontrada.\n";

		} else {
			// Uma boa solu��o foi encontrada? Salvar o caminho.
			LinkedList<Estados> caminho = resultado.getCaminho();
			Estados.resultado += "MISSIONARIOS | CANIBAIS\t\tMISSIONARIOS | CANIBAIS\n";
			String string = "";
			// Mostrar o caminho.
			for (Estados cam : caminho) {
				string += "\t" + (3 - cam.getMis()) + " | "
						+ (3 - cam.getCan()) + "\t\t\t" + cam.getMis() + " | "
						+ cam.getCan() + "\t|| ";
				if (cam.getMargem() == 1) {
					string += "Margem direita.\n";
				} else {
					string += "Margem esquerda.\n";
				}
			}

			Estados.resultado += string + "\n";
		}

	}

	Estados buscaEmProfundidade(Estados estado) {

		LinkedList<Estados> salvar = new LinkedList<Estados>();
		HashMap<String, Estados> visitados = new HashMap<String, Estados>();

		// Adicionar o estado inicial.
		salvar.add(estado);
		int qtdMovimentos = 0;
		int quantidadeDeNosExpandidos = 0;
		while (salvar.size() != 0) {

			// Colocar como abrir.
			Estados atual = salvar.poll();
			Estados.resultado += "\nEstado atual: " + atual.toString() + "\n";
			qtdMovimentos++;
			// Colocar como fechar.
			visitados.put(atual.toString(), atual);

			// Checar se � a solu��o.
			if (atual.equals(Estados.getSolucao())) {
				Estados.resultado += "Busca em profundidade finalizada, total movimentos: "
						+ Integer.toString(qtdMovimentos) + ".\n";
				Estados.resultado += "Busca em profundidade finalizada, total nos gerados: "
						+ Integer.toString(quantidadeDeNosExpandidos) + ".\n";
				return atual;
			}

			// Verificar se � um sucessor v�lido.
			for (Estados sucessorValido : atual.proxMovimentos()) {
				quantidadeDeNosExpandidos += 1;
				if (!visitados.containsKey(sucessorValido.toString())) {
					// Adicionar para abrir.
					salvar.addFirst(sucessorValido);
//					quantidadeDeNosExpandidos += 1;
//					System.out.println(quantidadeDeNosExpandidos);
				}
			}
		}
		return Estados.getNaoEncontrada();
	}
	
}