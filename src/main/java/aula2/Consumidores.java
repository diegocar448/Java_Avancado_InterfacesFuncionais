package aula2;

import java.util.function.Consumer;

public class Consumidores {

    public static void main(String[] args){
        //Method Reference
        // - apenas
        // - utilizar o parâmetro da forma que ele foi recebido

        Consumer<String> imprimirUmaFrase = System.out::println;
        Consumer<String> imprimirUmaFrase2 = frase -> System.out.println(frase);

        imprimirUmaFrase.accept("WhatsUp");

    }
}
