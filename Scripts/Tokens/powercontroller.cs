using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class powercontroller : MonoBehaviour
{
    /// <summary>
    /// Class to control the powerUp gameobject
    /// 
    /// look if the gameObject is on screen or not
    /// decides the type of powerup
    /// </summary>

    private string tipo;
    private Rigidbody2D body;

    private string[] powers= {"Forcepush","Shield","Airjump"};

    // Start is called before the first frame update
    void Start()
    {
        body = this.GetComponent<Rigidbody2D>();
        body.velocity = new Vector2(0, 0);

        this.tipo = powers[Random.Range(0, powers.Length)];
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter2D(Collider2D collider)//when the player touch any collider
    {
        if (collider.tag == "Fall")//Fall is the coollision that kills the player
        {
            Destroy(this.gameObject);
        }
    }

    public string GetTipo()
    {
        return this.tipo;
    }
}
