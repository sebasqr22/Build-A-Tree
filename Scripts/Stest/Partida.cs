using System;
using UnityEngine;


public class Partida : MonoBehaviour
{
    int tiempo;
    string arbolactual;
    bool tiempoAcabado;
    int[] puntajes;
    string[] arbol1;
    string[] arbol2;
    string[] arbol3;
    string[] arbol4;

    public Partida(int tiempo,string arbolactual, bool tiempoAcabado,int[] puntajes,string[] arbol1, string[] arbol2, string[] arbol3, string[] arbol4)
    {
        this.tiempo = tiempo;
        this.arbolactual = arbolactual;
        this.tiempoAcabado = tiempoAcabado;
        this.puntajes = puntajes;
        this.arbol1 = arbol1;
        this.arbol2 = arbol2;
        this.arbol3 = arbol3;
        this.arbol4 = arbol4;
    }
    

    public int getTiempo()
    {
        return tiempo;
    }

    public void setTiempo(int tiempo)
    {
        this.tiempo = tiempo;
    }
    public string GetArbolactual()
    {
        return arbolactual;
    }

    public string[] getArbol1()
    {
        return arbol1;
    }

    public string[] getArbol2()
    {
        return arbol2;
    }

    public string[] getArbol3()
    {
        return arbol3;
    }

    public string[] getArbol4()
    {
        return arbol4;
    }
    public void SetArbolactual(string arbolactual)
    {
        this.arbolactual = arbolactual;
    }
    public bool getTiempoAcabado() { 
        return this.tiempoAcabado; 
    }

    public void setTiempoAcabado(bool condicion) { 
        this.tiempoAcabado = condicion; 
    }

    public int[] getPuntajes()
    {
        return puntajes;
    }

    public void setPuntajes(int[] puntajes)
    {
        this.puntajes = puntajes;
    }



}
