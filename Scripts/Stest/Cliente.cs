using Newtonsoft.Json;
using System.Net.Sockets;
using System.Text;
using UnityEngine;

public class Cliente<T>
{
    Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

    public Cliente(int puerto, T objeto)
    {
        socket.Connect("127.0.0.1", puerto);

        //string Json = JsonConvert.SerializeObject(objeto);
        string Json = JsonUtility.ToJson(objeto);
        Debug.Log("Datos Formados: "+ Json);
        byte[] dataBytes = Encoding.Default.GetBytes(Json);

        socket.Send(dataBytes);

    }
}
