using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class TokenSpawner : MonoBehaviour
{

    /// <summary>
    /// Class to spawn diferent tokens an powerups to the game.
    /// 
    /// the server decides what type of token to spawn and when to spawn the tokens
    /// </summary>

    //prefabs of the objects

    public TMP_Text i_reto;
    public GameObject t_bst;
    public GameObject t_avl;
    public GameObject t_splay;
    public GameObject t_btree;


    public GameObject p_up;//prefab of a power up object

    private string[] tree_list = { "BST", "AVL", "SPLAY", "BTREE","POWERUP"};
    public Transform spawner;

    float respawntime = 3.0f;// experimental attribute


    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(wave());//initialize the wave
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void spawnToken(string token)//method to spawn tokens on screen
    {
        if (token == "BST")
        {
            GameObject bst = Instantiate(t_bst) as GameObject;//create a new game object from prefab
            bst.transform.position = new Vector2(spawner.position.x, spawner.position.y - 1);//the position of the object 
        }else if (token == "AVL")
        {
            GameObject avl = Instantiate(t_avl) as GameObject;//create a new game object from prefab
            avl.transform.position = new Vector2(spawner.position.x, spawner.position.y - 1);//the position of the object 
        }else if (token == "SPLAY")
        {
            GameObject splay = Instantiate(t_splay) as GameObject;//create a new game object from prefab
            splay.transform.position = new Vector2(spawner.position.x, spawner.position.y - 1);//the position of the object
        }else if (token == "BTREE")
        {
            GameObject btree = Instantiate(t_btree) as GameObject;//create a new game object from prefab
            btree.transform.position = new Vector2(spawner.position.x, spawner.position.y - 1);//the position of the object
        }
        else
        {
            GameObject power = Instantiate(p_up) as GameObject;//create a new game object from prefab
            power.transform.position = new Vector2(spawner.position.x, spawner.position.y - 1);//the position of the object
        }


    }

    IEnumerator wave()//experimental function
    {
        while (true)
        {
            yield return new WaitForSeconds(respawntime);
            spawnToken(tree_list[Random.Range(0,tree_list.Length)]);
        }
        
    }


    

}
