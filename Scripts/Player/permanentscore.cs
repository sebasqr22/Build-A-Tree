using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class permanentscore : MonoBehaviour
{
    /// <summary>
    /// Is a static class that saves the scores of the player an pass
    /// that info to the end scene of the game
    /// </summary>
    public int[] scores;



    public static permanentscore permanent;

    private void Start()//this is a singleton parttern
    {
        if (!permanent)
        {
            permanent = this;
        }
        else
        {
            Destroy(gameObject);
        }
        DontDestroyOnLoad(gameObject);
    }
    public void Setscores(int[] scores)
    {
        this.scores = scores;
    }
}
