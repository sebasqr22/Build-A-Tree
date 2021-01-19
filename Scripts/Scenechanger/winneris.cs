using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class winneris : MonoBehaviour
{
    /// <summary>
    /// Choose the winner of the game depending of the score of each one
    /// </summary>
    [SerializeField] private TMP_Text indicator;
    [SerializeField] private GameObject p1;
    [SerializeField] private GameObject p2;
    [SerializeField] private GameObject p3;
    [SerializeField] private GameObject p4;

    int pos;
    // Start is called before the game ends
    void Start()
    {
        bool win = false;
        int[] lista = permanentscore.permanent.scores;
        int max = 0;

        int primero = lista[0];// primero en la lista

        for(int i = 0; i < lista.Length; i++)//verifica que los jugadores no tengan los puntajes iguales
        {
            if (lista[i] != primero)
            {
                win = true;
            }
        }


        if (win)//si existe un ganador
        {
            for (int i = 0; i < lista.Length; i++)
            {
                if (lista[i] > max)
                {
                    max = lista[i];
                    this.pos = i;
                }
            }
            if (pos == 0)
            {
                indicator.text = "Player 1";
                p1.SetActive(true);
            }
            else if (pos == 1)
            {
                indicator.text = "Player 2";
                p2.SetActive(true);
            }
            else if (pos == 2)
            {
                indicator.text = "Player 3";
                p3.SetActive(true);
            }
            else if (pos == 3)
            {
                indicator.text = "Player 4";
                p4.SetActive(true);
            }
        }


        Destroy(permanentscore.permanent.gameObject);
    }
}
