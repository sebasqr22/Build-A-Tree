package buildatree;

import java.io.IOException;

public class Test {
    public ServerAux initserver = new ServerAux(9002);
    public static void main(String[] args) {
        Test server = new Test();
        server.ReiniciarCiclo();
    }

    /**
     * Método que vuelve a crear el ciclo de inicio de una partida
     */
    public void ReiniciarCiclo(){
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
        try {
            initserver.Cerrar();
            System.out.println("Se cerro la vara");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
