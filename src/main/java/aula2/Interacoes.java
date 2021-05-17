package aula2;

public class Interacoes {

    public static void imprimirTodosNomes(String... nomes){
        for (String nome : nomes){
            System.out.println(nome);
        }
    }

    public static void imprimirODobroDeCadaItemDaLista(Integer... numeros){
        for (Integer numero : numeros){
            System.out.println(numero*2);
        }
    }
}
