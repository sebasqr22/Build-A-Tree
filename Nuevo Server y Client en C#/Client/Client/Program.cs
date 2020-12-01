using System;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace Client
{
    class Program
    {
        private static readonly Socket ClientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

        static void Main()
        {
            Console.Title = "Cliente";
            ConnectToServer();
            TextLoop();
        }

        private static void ConnectToServer()
        {
            int attempts = 0;

            while (!ClientSocket.Connected)
            {
                try
                {
                    attempts++;
                    Console.WriteLine("Intento por conexion:  " + attempts);
                    ClientSocket.Connect("127.0.0.1", 9200);
                }
                catch (SocketException)
                {
                    Console.Clear();
                }
            }

            Console.Clear();
            Console.WriteLine("Conectado");
        }

        private static void TextLoop()
        {
            while (true)
            {
                SendText();
                ReceiveResponse();
            }
        }

        private static void SendText()
        {
            Console.Write("Esperando un mensaje: ");
            string text = Console.ReadLine();
            SendString(text);
        }
        private static void SendString(string text)
        {
            byte[] buffer = Encoding.ASCII.GetBytes(text);
            ClientSocket.Send(buffer, 0, buffer.Length, SocketFlags.None);
        }

        private static void ReceiveResponse()
        {
            var buffer = new byte[2048];
            int received = ClientSocket.Receive(buffer, SocketFlags.None);
            if (received == 0) return;
            var data = new byte[received];
            Array.Copy(buffer, data, received);
            string text = Encoding.ASCII.GetString(data);
            Console.WriteLine("Texto recibido: " + text);
        }
    }
}
