using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;


namespace Cliente{

    class Cliente{
        static void Main(string[] args){
            Socket envio = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            IPEndPoint conectar = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 10101);

            envio.Connect(conectar);

            byte[] enviar = new byte[100];
            string data;
            Console.WriteLine("Info a enviar: ");

            data = Console.ReadLine();

            enviar = Encoding.Default.GetBytes(data);

            envio.Send(enviar);

            Console.ReadKey();

        }
    }
}