using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;


namespace ServerC
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        public void IniciarServer()
        {
            Socket envio = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            Socket conexion;
            IPEndPoint conectar = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 9200);

            textArea.AppendText("\n " + "Escuchando al puerto");

            envio.Bind(conectar);
            envio.Listen(10);

            conexion = envio.Accept();

            textArea.AppendText("\n" + "Server Creado");

            byte[] informacion = new byte[100];
            string data = "";
            int tamañoArray = 0;


            tamañoArray = conexion.Receive(informacion, 0, informacion.Length, 0);
            Array.Resize(ref informacion, tamañoArray);
            data = Encoding.Default.GetString(informacion);

            textArea.AppendText("\n" + "Mensaje que llego: " + data);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            IniciarServer();
        }
    }
}
