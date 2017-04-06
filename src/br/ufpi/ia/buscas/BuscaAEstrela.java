package br.ufpi.ia.buscas;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import br.ufpi.ia.estados.Estados;

public class BuscaAEstrela {

	private static BuscaAEstrela busca = new BuscaAEstrela();
	// Estado inicial.
	Estados inicio = new Estados(Estados.getMissionarios(),
			Estados.getCanibais(), 1, null);

	public Estados buscar() {

		// Iniciar o algoritimo de Busca A*.
		Estados resultado = busca.buscaAEstrela(inicio);
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

	private Estados buscaAEstrela(Estados estado) {

		LinkedList<Estados> salvar = new LinkedList<Estados>();
		HashMap<String, Estados> descartar = new HashMap<String, Estados>();
		HeuristicaAEstrela heuristica = new HeuristicaAEstrela();

		// Adicionar o estado inicial.
		salvar.add(estado);
		int qtdMoviementos = 0;
		int quantidadeDeNosExpandidos = 0;

		while (salvar.size() != 0) {
			// Escolha obedecendo a heur�stica.
			Collections.sort(salvar, heuristica);

			// Adicionar como abrir.
			Estados atual = salvar.poll();
			Estados.resultado += "\nEstado atual: " + atual.toString() + "\n";
			qtdMoviementos++;
			// Adicionar como fechar
			descartar.put(atual.toString(), atual);

			// Checar se � uma solu��o v�lida.
			if (atual.equals(Estados.getSolucao())) {
				Estados.resultado += "Busca A* finalizada, total de movimentos: "
						+ Integer.toString(qtdMoviementos) + ".\n";
				Estados.resultado += "Busca A* finalizada, total nos gerados: "
						+ Integer.toString(quantidadeDeNosExpandidos) + ".\n";
				return atual;
			}

			// Verificar se � um sucessor valido.
			for (Estados sucessorValido : atual.proxMovimentos()) {
				quantidadeDeNosExpandidos += 1;
				if (!descartar.containsKey(sucessorValido.toString())) {
					// Add to open
					salvar.addLast(sucessorValido);
//					quantidadeDeNosExpandidos += 1;
				}
			}
		}
		return Estados.getNaoEncontrada();
	}

	// Aplicar a heur�stica na classe.
	class HeuristicaAEstrela implements Comparator<Estados> {
		@Override
		public int compare(Estados a, Estados b) {
			return Heuristica(a).compareTo(Heuristica(b));
		}
	}

	// Fun��o heur�stica que avalia a dist�ncia para o pr�ximo estado sucessor.
	public Integer Heuristica(Estados s) {
		return s.getMis() - s.getProx().getMis() + s.getCan()
				- s.getProx().getCan() + s.getMargem()
				- s.getProx().getMargem();
	}	
	
}
