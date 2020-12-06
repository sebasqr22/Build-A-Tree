/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildatree;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sebas
 */
public class VisualServer extends javax.swing.JFrame implements Runnable{
    Thread comienzo = new Thread(this::run);
    int contadorPartidas = 0;
    /**
     * Creates new form VisualServer
     */
    public VisualServer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        partidasText = new javax.swing.JLabel();
        partidasField = new javax.swing.JTextField();
        conectarBoton = new javax.swing.JButton();
        desconectarBoton = new javax.swing.JButton();
        salirBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        partidasText.setText("Cantidad de partidas conectadas:");

        partidasField.setEditable(false);
        partidasField.setText("0");

        conectarBoton.setText("Conectar");
        conectarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarBotonActionPerformed(evt);
            }
        });

        desconectarBoton.setText("Desconectar");
        desconectarBoton.setEnabled(false);
        desconectarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarBotonActionPerformed(evt);
            }
        });

        salirBoton.setText("Salir");
        salirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partidasText)
                            .addComponent(partidasField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(salirBoton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(conectarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(desconectarBoton)))
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(partidasText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partidasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desconectarBoton)
                    .addComponent(conectarBoton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addComponent(salirBoton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void conectarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarBotonActionPerformed
        // TODO add your handling code here:
        textArea.append("\n" + "---Conectando-el-servidor---");
        conectarBoton.setEnabled(false);
        desconectarBoton.setEnabled(true);


        //Servidor nuevoServer = new Servidor();
        //nuevoServer.CrearServidor();

        comienzo.start();
        textArea.append("\n" + "---Servidor--creado--en--el--puerto--" + 9200);

    }//GEN-LAST:event_conectarBotonActionPerformed

    private void desconectarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarBotonActionPerformed
        // TODO add your handling code here:
        textArea.append("\n" + "---Desconectando---");
        conectarBoton.setEnabled(true);
        desconectarBoton.setEnabled(false);

        comienzo.destroy();
    }//GEN-LAST:event_desconectarBotonActionPerformed

    private void salirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBotonActionPerformed
        // TODO add your handling code here:
        /*
        int salir = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres salir?");
        switch(salir){
            case JOptionPane.YES_OPTION:
                System.exit(0);

            case JOptionPane.NO_OPTION:
                break;
        }

         */
    }//GEN-LAST:event_salirBotonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conectarBoton;
    private javax.swing.JButton desconectarBoton;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField partidasField;
    private javax.swing.JLabel partidasText;
    private javax.swing.JButton salirBoton;
    public javax.swing.JTextArea textArea;

    @Override
    public void run() {
        try{
            while(true){
                ServerSocket server = new ServerSocket(9200);

                while(true){
                    Socket recibo = server.accept();
                    InputStream entrada = recibo.getInputStream();

                    byte[] largoBytes = new byte[4];
                    entrada.read(largoBytes, 0, 4);
                    int largo = (((largoBytes[3] & 0xff) << 24) | ((largoBytes[2] & 0xff) << 16) | ((largoBytes[1] & 0xff) << 8) | (largoBytes[0] & 0xff));
                    byte[] llegada = new byte[largo];
                    entrada.read(llegada, 0, largo);
                    String mensaje = new String(llegada, 0, largo);

                    System.out.println("Mensaje: " + mensaje);

                    if(mensaje.equals("iniciar2")){
                        textArea.append("\n" + "Creando nueva partida con 2 jugadores.....");
                        contadorPartidas ++;
                        partidasField.setText(String.valueOf(contadorPartidas));
                    }

                    else if(mensaje.equals("iniciar4")){
                        textArea.append("\n" + "Creando nueva partida con 4 jugadores.....");
                        contadorPartidas ++;
                        partidasField.setText(String.valueOf(contadorPartidas));
                        Cliente nuevoCliente = new Cliente();
                        nuevoCliente.EnviarMensaje("PartidaCreada");


                        Partida nuevaPartida = new Partida();
                        nuevaPartida.CrearNuevaPartida(4);
                    }

                }
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }
    // End of variables declaration//GEN-END:variables
}
