package aula3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ParaleloStreamExemplo {

    public static void main(String[] args){
        /*Apontagem do OS em milisegundos*/
        /*long inicio = System.currentTimeMillis();
        IntStream.range(1,100000).forEach(num -> fatorial(num) ); //Serial
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução Serial :: "+ (fim-inicio));

        inicio = System.currentTimeMillis();
        IntStream.range(1,100000).parallel().forEach(num -> fatorial(num) ); //Paralell
        fim = System.currentTimeMillis();
        System.out.println("Tempo de execução Paralell :: "+ (fim-inicio));*/

        List<String> nomes = Arrays.asList("Joao", "Paulo", "Oliveira", "Santos");
        nomes.parallelStream().forEach(System.out::println);
    }

    /*Método que calcula o fatorial*/
    public static long fatorial(long num){
        long fat = 1;

        for (long i = 2; i < num; i++) {
            fat = i;
        }

        return fat;
    }
}
