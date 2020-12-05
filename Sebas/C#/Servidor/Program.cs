using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;


namespace C_
{
    class Program
    {
        static void Main(string[] args)
        {
            Socket envio = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            Socket conexion;
            IPEndPoint conectar = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 10101);

            envio.Bind(conectar);
            envio.Listen(10);

            conexion = envio.Accept();

            Console.WriteLine("Server Creado");

            byte[] informacion = new byte[100];
            string data = "";
            int tamañoArray = 0;


            tamañoArray = conexion.Receive(informacion, 0, informacion.Length, 0);
            Array.Resize(ref informacion, tamañoArray);
            data = Encoding.Default.GetString(informacion);

            Console.WriteLine("Informacion: ",data);
            Console.ReadKey();
        }
    }
}
