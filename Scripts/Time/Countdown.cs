using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Countdown : MonoBehaviour
{
    [SerializeField] private CharacterController2D p1;
    [SerializeField] private CharacterController2D p2;
    [SerializeField] private CharacterController2D p3;
    [SerializeField] private CharacterController2D p4;


    public Text indicator;
    public Partida partida;
    private Servert<Partida> servidor;

    public int tiempo = 210;
    // Start is called before the first frame update
    void Start()
    {
        servidor = new Servert<Partida>(9010);
    }

    // Update is called once per frame
    void Update()
    {
        partida = servidor.GetObjeto();
        indicator.text = partida.getTiempo().ToString();

        if (partida.getTiempo() < 0)
        {
            gamefinish();
        }

    }

    void gamefinish()
    {
        int[] scores = { p1.GetScores(), p2.GetScores(), p3.GetScores(),p4.GetScores()};
        permanentscore.permanent.Setscores(scores);
        SceneManager.LoadScene(2);
    }
}