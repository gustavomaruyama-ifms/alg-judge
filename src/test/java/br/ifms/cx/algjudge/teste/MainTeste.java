package br.ifms.cx.algjudge.teste;

import java.awt.Color;

import br.com.vinyanalista.portugol.interpretador.Exemplo;
import br.com.vinyanalista.portugol.interpretador.Interpretador;
import br.com.vinyanalista.portugol.interpretador.Terminal;

public class MainTeste {
	public static void main(String[] args) {
		String codigoFonte = Exemplo.ESTRUTURA_CONDICIONAL_SIMPLES.getProgramaFonte();

		System.out.println(codigoFonte);
		Interpretador interpretador = new Interpretador(new Terminal() {
			String[] entradas = {"50","20"};
			Integer seqEntrada = 0;

			@Override
			protected void mudarCor(Color cor) {
				// TODO Auto-generated method stub
			}

			@Override
			public void limpar() {
				// TODO Auto-generated method stub
			}

			@Override
			protected String ler() {
				return entradas[seqEntrada++];
			}

			@Override
			public void informacao(String mensagemDeInformacao) {
				System.out.println("Informação" + mensagemDeInformacao);
			}

			@Override
			protected void escrever(String mensagem) {
				System.out.println("escrever mensagem"+ mensagem);
			}

			@Override
			public void erro(String mensagemDeErro) {
				System.out.println("Erro" + mensagemDeErro);
			}
		});

		try {
			interpretador.analisar(codigoFonte);
			interpretador.executar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
