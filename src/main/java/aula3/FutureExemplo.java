package aula3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.ConcurrentModificationException;

public class FutureExemplo {
    /*
     Aqui controlamos o número de threads na execução
    (exemplo para executar uma quarta thread precisará esperar finalizar uma das 3 que estão rodandos)
    */
    //private static final ExecutorService threadPool = Executors.newFixedThreadPool(3);
    private static final ExecutorService pessoasParaExecutarAtividade = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        //Casa casa = new Casa(new Quarto());
        /*casa.obterAfazeresDaCasa().forEach( atividade -> threadPool.execute(() -> {
            try {
                atividade.realizar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }) );*/
        //threadPool.shutdown();
        Casa casa = new Casa(new Quarto());
        List<Future<String>> futuros =
        new CopyOnWriteArrayList<>(casa.obterAfazeresDaCasa().stream()
                .map( atividade -> pessoasParaExecutarAtividade.submit(() -> {
                        try {
                            return atividade.realizar();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                )
                .collect(Collectors.toList()));

        //while (!futuros.stream().allMatch(Future::isDone)){
        while (true){
            int numeroDeAtividadesNaoFinalizada = 0;
            for (Future<?> futuro : futuros)
            {
                if(futuro.isDone()){
                    try {
                        System.out.println("Parabéns voce terminou de "+ futuro.get());
                        futuros.remove(futuro);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }catch (ExecutionException e){
                        e.printStackTrace();
                    }
                }else{
                    numeroDeAtividadesNaoFinalizada++;
                }
                if(futuros.stream().allMatch(Future::isDone)){
                    break;
                }
                System.out.println("Número de atividades não finalizadas :: "+ numeroDeAtividadesNaoFinalizada);
                Thread.sleep(500);
            };
            pessoasParaExecutarAtividade.shutdown();
        }



    }
}

class Casa{
    private List<Comodo> comodos;

    Casa(Comodo... comodos){ this.comodos = Arrays.asList(comodos); }

    List<Atividade> obterAfazeresDaCasa(){
        return this.comodos.stream().map(Comodo::obterAfazeresDoComodo)
                                    .reduce(new ArrayList<Atividade>(), (pivo, atividades) ->{
                                       pivo.addAll(atividades);
                                       return pivo;
                                    });
    }
}

interface Atividade{
    //void realizar() throws InterruptedException;
    String realizar() throws InterruptedException;
}

abstract class Comodo{
    abstract List<Atividade> obterAfazeresDoComodo();
}

class Quarto extends Comodo{
    @Override
    List<Atividade> obterAfazeresDoComodo(){
        return Arrays.asList(
                this::arrumarACama,
                this::varretOQuarto,
                this::arrumarGuardaRoupa
        );
    }

    private String arrumarGuardaRoupa() throws InterruptedException {
        Thread.sleep(500);
        String arrumar_o_guarda_roupa = "arrumar o guarda roupa";
        //System.out.println("arrumar o guarda roupa");
        return arrumar_o_guarda_roupa;
    }

    private String varretOQuarto() throws InterruptedException {
        Thread.sleep(8500);
        String varrer_o_quarto = "varrer o quarto";
        //System.out.println("varrer o quarto");
        return varrer_o_quarto;
    }

    private String arrumarACama() throws InterruptedException {
        Thread.sleep(2500);
        String arrumar_a_cama = "Arrumar a cama";
        //System.out.println("Arrumar a cama");
        return arrumar_a_cama;
    }
}
