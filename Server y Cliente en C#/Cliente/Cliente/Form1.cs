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

namespace Cliente
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            EnviarMensaje(mensajeTexto.Text);

            Console.WriteLine("Mensaje enviado");
        }

        public void EnviarMensaje(string mensaje)
        {
            int puerto = 9200;
            Socket envio = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            IPEndPoint conectar = new IPEndPoint(IPAddress.Parse("127.0.0.1"), puerto);

            envio.Connect(conectar);

            byte[] enviar = new byte[100];
            //string data;
            //Console.WriteLine("Info a enviar: ");

            //string texto = mensajeTexto.Text;

            //data = Console.ReadLine();

            enviar = Encoding.Default.GetBytes(mensaje);

            envio.Send(enviar);


        }
    }
}
