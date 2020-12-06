package buildatree;

import java.io.IOException;
import java.io.OutputStream;

public class EnvioRAPIDO {
    public void Enviar(String mensaje, OutputStream salida) throws IOException {

        byte[] enviar = mensaje.getBytes();
        int toSendLen = enviar.length;
        byte[] enviarLargo = new byte[4];
        enviarLargo[0] = (byte)(toSendLen & 0xff);
        enviarLargo[1] = (byte)((toSendLen >> 8) & 0xff);
        enviarLargo[2] = (byte)((toSendLen >> 16) & 0xff);
        enviarLargo[3] = (byte)((toSendLen >> 24) & 0xff);
        salida.write(enviarLargo);
        salida.write(enviar);

    }
}
