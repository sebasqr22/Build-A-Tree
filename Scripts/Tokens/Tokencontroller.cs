using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class Tokencontroller : MonoBehaviour
{

    /// <summary>
    /// Controls the movement of the token in game
    /// </summary>
    [SerializeField] private TMP_Text indicator;
    public float speed = 2.0f;
    Rigidbody2D body;

    public int data;
    public string tipo;
    /// <summary>
    /// Makes the token falls and assign a random number to data
    /// </summary>
    void Start()
    {
        body = this.GetComponent<Rigidbody2D>();
        body.velocity = new Vector2(0, speed);

        this.data = Random.Range(2, 99);
        indicator.text = data.ToString();
    }

    void OnTriggerEnter2D(Collider2D collider)//when the player touch any collider
    {
        if (collider.tag == "Fall")//Fall is the coollision that kills the player
        {
            Destroy(this.gameObject);
        }
    }
    public string GetTipo()//returns the type of the token
    {
        return this.tipo;
    }
    public int GetData()
    {
        return this.data;
    }
}
