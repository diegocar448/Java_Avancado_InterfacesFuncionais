package aula3;

import java.util.Set;
import java.lang.reflect.InvocationTargetException;
import java.lang.Thread;


class ThreadExemplo {

    public static void main(String[] args) {
        //BarraDeCarregamento2 barraDeCarregamento2 = new BarraDeCarregamento2();
        //BarraDeCarregamento3 barraDeCarregamento3 = new BarraDeCarregamento3();

        //Thread thread = new Thread(new BarraDeCarregamento2());
        //Thread thread2 = new Thread(new BarraDeCarregamento3());

        GeradorPDF iniciarGeradorPdf = new GeradorPDF();
        BarraDeCarregamento iniciarBarraDeCarregamento = new BarraDeCarregamento(iniciarGeradorPdf);

        iniciarGeradorPdf.start();
        iniciarBarraDeCarregamento.start();

        //thread.start();
        //thread2.start();

        //System.out.println("Nome da thread : "+thread.getName());
        //System.out.println("Nome da thread : "+thread2.getName());

        //barraDeCarregamento2.run();
        //barraDeCarregamento3.run();
    }
}

class GeradorPDF extends Thread{
    @Override
    public void run(){
        try {
            System.out.println("Iniciar geração de PDF");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("PDF Gerado");
    }
}

class BarraDeCarregamento extends Thread{
    private Thread iniciarGeradorPdf;

    public BarraDeCarregamento(Thread iniciarGeradorPdf){
        this.iniciarGeradorPdf = iniciarGeradorPdf;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(500);
                /* entrar qdo a thread não estiver mais em execucao*/
                if(!iniciarGeradorPdf.isAlive()){
                    break;
                }

                System.out.println("Loading...");

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class BarraDeCarregamento2 extends Thread{
    @Override
    public void run(){
        super.run();

        try{
            Thread.sleep(3000);
            System.out.println("rodei BarraDeCarregamento2 : " + this.getName());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class BarraDeCarregamento3 extends Thread{
    @Override
    public void run(){
        super.run();

        try{
            Thread.sleep(1000);
            System.out.println("rodei BarraDeCarregamento3 : " + this.getName());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

