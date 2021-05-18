package aula2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class InteracoesSegundo {
    public static void main(String[] args){
        String[] nomes = {"Joao", "Joao", "Paulo", "Oliveira", "Santos", "Instrutor", "Java"};
        Integer[] numeros = {1,2,3,4,5};
        //ImprimirNomesFiltrados(nomes);
        imprimirTodosNomes(nomes);
    }

    public static void ImprimirNomesFiltrados(String... nomes){
        String nomesParaImprimir = "";
        //Imperativo
        for (int i = 0; i < nomes.length; i++){
            if(nomes[i].equals("Joao")){
                nomesParaImprimir += "" + nomes[i];
            }
        }
        System.out.println("Nomes do for:" +nomesParaImprimir);

        //Funcional
        //simplificando a estrutura acima com Stream
        String nomesParaImprimirDaStream = Stream.of(nomes).filter(nome -> nome.equals("Joao")).collect(Collectors.joining());

        System.out.println("Nomes do Stream: " +nomesParaImprimirDaStream);
    }

    public static void imprimirTodosNomes(String... nomes){
        for (String nome: nomes){
            System.out.println("Imprimido pelo for: "+nome);
        }

        Stream.of(nomes)
                .forEach(nome -> System.out.println("Imprimido pelo forEach: " + nome));
    }

    public static void imprimirODobroDeCadaItemDaLista(Integer... numeros){
        for (Integer numero: numeros){
            System.out.println(numero*2);
        }
    }
}
