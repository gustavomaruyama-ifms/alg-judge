package br.ifms.cx.algjudge.teste;

import java.awt.Color;

import br.com.vinyanalista.portugol.interpretador.Exemplo;
import br.com.vinyanalista.portugol.interpretador.Interpretador;
import br.com.vinyanalista.portugol.interpretador.Terminal;

public class MainTeste {

    public static void main(String[] args) {
        String codigoFonte = "";
        //String codigoFonte = Exemplo.ESTRUTURA_CONDICIONAL_SIMPLES.getProgramaFonte();

        codigoFonte = "algoritmo\n" +
"// Função : Realiza contagem de picos em um loop musical\n" +
"// Autor : Gustavo Yoshio Maruyama\n" +
"// Data : 03/01/2016\n" +
"// Seção de Declarações\n" +
"declare \n" +
"	n, atual, anterior, primeiro, segundo, picos, i numerico\n" +
"	estado, ESTADO_INICIAL, ESTADO_SECUNDARIO, SUBINDO, DESCENDO literal               \n" +
"      // Inicializando as constantes\n" +
"      ESTADO_INICIAL <- \"ESTADO_INICIAL\"\n" +
"      ESTADO_SECUNDARIO <- \"ESTADO_SECUDARIO\"\n" +
"      SUBINDO <- \"SUBINDO\"\n" +
"      DESCENDO <- \"DESCENDO\"\n" +
"\n" +
"      estado <- ESTADO_INICIAL\n" +
"      \n" +
"      // Realizando a leitura da quantidade de amostras que será lido (valor n)\n" +
"      leia n \n" +
"      \n" +
"      // Realizando a leitura das n amostras e as compara para obter o número de picos\n" +
"      para i <- 1 ate n faca\n" +
"      inicio\n" +
"           // Realiza a leitura da n-nésima amostra\n" +
"           leia atual\n" +
"           \n" +
"           se estado = ESTADO_INICIAL entao\n" +
"           inicio\n" +
"              primeiro <- atual\n" +
"              anterior <- atual\n" +
"              estado <- ESTADO_SECUNDARIO\n" +
"           fim\n" +
"           senao\n" +
"           inicio\n" +
"                se estado = ESTADO_SECUNDARIO entao       // é executado na segunda iteração\n" +
"                     inicio\n" +
"	                     segundo <- atual\n" +
"	                     se atual > anterior entao\n" +
"	                          estado <- SUBINDO\n" +
"	                     senao                     \n" +
"	                          estado <- DESCENDO\n" +
"                     fim\n" +
"                senao   \n" +
"                inicio\n" +
"                   se atual < anterior entao\n" +
"                   inicio\n" +
"                           se estado = SUBINDO entao\n" +
"                           inicio\n" +
"                              picos <- picos + 1\n" +
"                           fim\n" +
"                           estado <- DESCENDO\n" +
"                   fim\n" +
"                   senao\n" +
"                   inicio\n" +
"                       se atual > anterior entao\n" +
"                       inicio\n" +
"	                      se estado = DESCENDO entao\n" +
"	                      inicio\n" +
"	                           picos <- picos + 1\n" +
"	                  	  fim                 	\n" +
"                           estado <- SUBINDO\n" +
"                   	   fim\n" +
"                   	fim\n" +
"           	fim\n" +
"               anterior <-  atual\n" +
"      	fim\n" +
"      fim\n" +
"      \n" +
"      se (primeiro < atual) e (estado = SUBINDO) entao\n" +
"      inicio\n" +
"         picos <- picos + 1\n" +
"         se primeiro < segundo entao\n" +
"         inicio\n" +
"             picos <-  picos + 1\n" +
"         fim\n" +
"      fim\n" +
"      \n" +
"      se (primeiro > atual) e (estado = DESCENDO) entao\n" +
"      inicio\n" +
"         picos <- picos + 1\n" +
"         se primeiro > segundo entao\n" +
"         inicio\n" +
"             picos <- picos + 1\n" +
"         fim\n" +
"      fim\n" +
"\n" +
"      escreva picos\n" +
"fim_algoritmo\n" +
"";
        
        System.out.println(codigoFonte);
        Interpretador interpretador = new Interpretador(new Terminal() {
            //String[] entradas = {"6", "40", "0", "-41", "0", "41", "42"};
            //String[] entradas = {"4", "300", "450", "449", "450"};
            String[] entradas = {"2", "1", "-3"};
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
                System.out.println(mensagemDeInformacao);
            }

            @Override
            protected void escrever(String mensagem) {
                System.out.println(mensagem);
            }

            @Override
            public void erro(String mensagemDeErro) {
                System.out.println(mensagemDeErro);
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
