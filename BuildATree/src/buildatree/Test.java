package buildatree;

public class Test {

    public static void main(String[] args) {
        ServerAux initserver = new ServerAux(9002);
        String valor = initserver.Iniciar();
        System.out.println(valor);

        if(valor.contains("Iniciar2")){
            Thread hilo = new Thread(new Server(2));
            hilo.start();
        }else if(valor.contains("Iniciar3")){
            Thread hilo = new Thread(new Server(3));
            hilo.start();
        }else if(valor.contains("Iniciar4")){
            Thread hilo = new Thread(new Server(4));
            hilo.start();
        }
    }
}
