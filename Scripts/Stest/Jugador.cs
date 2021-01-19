using System;
using UnityEngine;

[Serializable] 
public class Jugador
{
    /// <summary>
    /// Sends the information of the tokens to the server
    /// </summary>
    public int valor;
    public string tipo;
    public string nombre;

    public Jugador(int valor,string tipo, string nombre)
    {
        this.tipo = tipo;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getValor()
    {
        return valor;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }

    public string getTipo()
    {
        return tipo;
    }

    public void setTipo(string tipo)
    {
        this.tipo = tipo;
    }
    public string getNombre()
    {
        return nombre;
    }

    public void setNombre(string nombre)
    {
        this.nombre = nombre;
    }

}
