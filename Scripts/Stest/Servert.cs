using UnityEngine;
using TMPro;
using System.Net.Sockets;
using System.Net;
using System.Threading;
using System.IO;
using System.Text;
using Newtonsoft.Json;

public class Servert<T>
{
    [SerializeField] TMP_Text indicador;

    Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
    EndPoint endPoint;

    bool on = false;
    string data;

    public Servert(int puerto)
    {
        endPoint = new IPEndPoint(IPAddress.Any, puerto);//puerto donde se escuchará
        Thread newThread = new Thread(new ThreadStart(Run));//Inicia el servidor
        newThread.Start();
    }
    public void Run()
    {
        socket.Bind(endPoint);
        socket.Listen(5);

        while (true)
        {
            //Debug.Log("Servidor Iniciado");

            Socket newSocket = socket.Accept();
            MemoryStream memoryStream = new MemoryStream();

            byte[] buffer = new byte[1024];

            int readBytes = newSocket.Receive(buffer);

            while (readBytes > 0)
            {
                memoryStream.Write(buffer, 0, readBytes);
                if (socket.Available > 0)
                {
                    readBytes = newSocket.Receive(buffer);
                }
                else
                {
                    break;
                }
            }

            byte[] totalbytes = memoryStream.ToArray();
            memoryStream.Close();

            string readData = Encoding.Default.GetString(totalbytes);
            this.data = readData;
            Debug.Log("Llego: " + readData);
        }
    }
    public T GetObjeto()
    {
        //return JsonUtility.FromJson<T>(data);
        return JsonConvert.DeserializeObject<T>(data);
    }
    public string GetData()
    {
        return data;
    }
    public bool On()
    {
        return on;
    }
}
