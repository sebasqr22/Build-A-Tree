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

    public float clock = 30;
    public Text indicator;
    // Start is called before the first frame update
    void Start()
    {
        indicator.text = clock.ToString();
    }

    // Update is called once per frame
    void Update()
    {
        clock -= Time.deltaTime;
        indicator.text = Mathf.Round(clock).ToString();

        if (clock<0)
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
