using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace Server
{
    class Program
    {
        private static Socket serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        private static List<Socket> clientSockets = new List<Socket>();
        private const string IP = "127.0.0.1";
        private static byte[] buffer = new byte[1024];

        static void Main()
        {
            Console.Title = "Server";
            SetupServer();
            Console.ReadLine();
        }

        private static void SetupServer()
        {
            Console.WriteLine("Iniciado servidor");
            serverSocket.Bind(new IPEndPoint(IPAddress.Parse("127.0.0.1"), 9200));
            serverSocket.Listen(10);
            serverSocket.BeginAccept(AcceptCallback, null);
            Console.WriteLine("Servidor iniciado esperando conexion de cliente");
        }
        private static void AcceptCallback(IAsyncResult AR)
        {
            Socket socket;

            try
            {
                socket = serverSocket.EndAccept(AR);
            }
            catch (ObjectDisposedException)
            {
                return;
            }

            clientSockets.Add(socket);
            socket.BeginReceive(buffer, 0, 1024, SocketFlags.None, ReceiveCallback, socket);
            Console.WriteLine("Cliente conectado");
            serverSocket.BeginAccept(AcceptCallback, null);
        }

        private static void ReceiveCallback(IAsyncResult AR)
        {
            Socket current = (Socket)AR.AsyncState;
            int received;

            try
            {
                received = current.EndReceive(AR);
            }
            catch (SocketException)
            {
                current.Close();
                clientSockets.Remove(current);
                return;
            }

            byte[] recBuf = new byte[received];
            Array.Copy(buffer, recBuf, received);
            string text = Encoding.ASCII.GetString(recBuf);
            Console.WriteLine("Mensaje recibido: " + text);

            if (text.ToLower() == "hola servidor")
            {
                byte[] data = Encoding.ASCII.GetBytes("hola cliente");
                current.Send(data);
            }
            else if (text.ToLower() == "como estas?")
            {
                byte[] data = Encoding.ASCII.GetBytes("bien y usted cliente?");
                current.Send(data);
            }
            else
            {
                byte[] data = Encoding.ASCII.GetBytes("Mensaje enviado no esta registrado, vuelva a intentarlo");
                current.Send(data);
            }
            current.BeginReceive(buffer, 0, 1024, SocketFlags.None, ReceiveCallback, current);
        }
    }
}