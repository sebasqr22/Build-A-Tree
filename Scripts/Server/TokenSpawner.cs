using System.Collections;
using UnityEngine;
using TMPro;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


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

    [SerializeField] private CharacterController2D p1;
    [SerializeField] private CharacterController2D p2;
    [SerializeField] private CharacterController2D p3;
    [SerializeField] private CharacterController2D p4;

    public Text indicator;
    public Partida partida;
    private Servert<Partida> servidor;

    public int tiempo = 210;
    public int contadorToken = 0;

    private int actualizacion = 1;


    public GameObject p_up;//prefab of a power up object

    private string[] tree_list = { "BST", "AVL", "SPLAY", "BTREE","POWERUP"};
    public Transform spawner;




    // Start is called before the first frame update
    void Start()
    {
        //StartCoroutine(wave());//initialize the wave
        servidor = new Servert<Partida>(9010);
    }

    // Update is called once per frame
    void Update()
    {
        if (Time.time >= actualizacion)
        {
            actualizacion = Mathf.FloorToInt(Time.time) + 1;
            // Call your fonction
            StartCoroutine(TiempoToken());
        }
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

    void gamefinish()//send the score to the permanet score object
    {
        int[] scores = { p1.GetScores(), p2.GetScores(), p3.GetScores(), p4.GetScores() };
        permanentscore.permanent.Setscores(scores);
        SceneManager.LoadScene(2);
    }

    IEnumerator TiempoToken()//controla el tiempo para revisar los objetos que envia el server
    {
        yield return new WaitForSeconds(1);
        partida = servidor.GetObjeto();
        indicator.text = partida.getTiempo().ToString();
        i_reto.text = partida.GetArbolactual();

        //da los puntos segun el server a cada jugador
        p1.SetScore(partida.getPuntajes()[0]);
        p2.SetScore(partida.getPuntajes()[1]);
        p3.SetScore(partida.getPuntajes()[2]);
        p4.SetScore(partida.getPuntajes()[3]);

        p1.SetArbol(partida.getArbol1());
        p2.SetArbol(partida.getArbol2());
        p3.SetArbol(partida.getArbol3());
        p4.SetArbol(partida.getArbol4());

        if (partida.getTiempo() < 0)
        {
            gamefinish();
        }

        if (partida.getTiempoAcabado() == true)
        {
            TirarToken();
            
        }
    }
    public void TirarToken()
    {
        partida.setTiempoAcabado(false);
        //Random random = new Random();
        int numero = UnityEngine.Random.Range(0, 4);
        //Debug.Log("Numero de lista: " + numero);
        //Debug.Log(tree_list[numero]);  
        spawnToken(tree_list[numero]);
    }
}
