package buildatree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{

    int puertoEscucha = 9200;

    public Servidor() throws IOException {
    }

    public void CrearServidor() throws IOException {
        Thread comienzo = new Thread(this);
        comienzo.start();
    }
    public int getPuerto(){
        return puertoEscucha;
    }

    Socket recibo;
    InputStream entrada = recibo.getInputStream();
    OutputStream salida = recibo.getOutputStream();

    @Override
    public void run(){
        try{
            while(true){
                ServerSocket server = new ServerSocket(puertoEscucha);

                while(true){
                    recibo = server.accept();


                    byte[] largoBytes = new byte[4];
                    entrada.read(largoBytes, 0, 4);
                    int largo = (((largoBytes[3] & 0xff) << 24) | ((largoBytes[2] & 0xff) << 16) | ((largoBytes[1] & 0xff) << 8) | (largoBytes[0] & 0xff));
                    byte[] llegada = new byte[largo];
                    entrada.read(llegada, 0, largo);
                    String mensaje = new String(llegada, 0, largo);

                    System.out.println("Mensaje: " + mensaje);
                }
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }
    public OutputStream getOutput(){
        return salida;
    }

    public void Cliente(String envio) throws IOException {
        byte[] envioBytes = envio.getBytes();
        int largoNuevo = envioBytes.length;

        byte [] largoBytesEnviado = new byte[4];
        largoBytesEnviado[0] = (byte)(largoNuevo & 0xff);
        largoBytesEnviado[1] = (byte)((largoNuevo >> 8) & 0xff);
        largoBytesEnviado[2] = (byte)((largoNuevo >> 16) & 0xff);
        largoBytesEnviado[3] = (byte)((largoNuevo >> 24) & 0xff);

        salida.write(largoBytesEnviado);
        salida.write(envioBytes);
    }

}