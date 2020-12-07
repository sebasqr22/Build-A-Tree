using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour

{
    /// <summary>
    /// Spawn the diferent tokens in game to  the position of the gameobject
    /// </summary>
    public float speed = 2f;//speed of the spawner
    private Rigidbody2D body;

    // Start is called before the first frame update
    void Start()
    {
        body = this.GetComponent<Rigidbody2D>();
        body.velocity = new Vector2(1 * speed, 0);
    }
    /// <summary>
    /// refresh the position of the spawner
    /// </summary>
    private void Update()
    {
        if (this.transform.position.x < -7.43)
        {
            body.velocity = new Vector2(1 * speed, 0);
        }
        else if (this.transform.position.x>7.43)
        {
            body.velocity = new Vector2(-1 * speed, 0);
        }
    }
}
