using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pause : MonoBehaviour
{
    /// <summary>
    /// Pause the game
    /// </summary>
    /// 
    [SerializeField] GameObject normalUi;
    [SerializeField] GameObject pauseUi;

    bool enable;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown("space"))
        {
            enable = !enable;
            normalUi.SetActive(!enable);
            pauseUi.SetActive(enable);
            Time.timeScale = (enable) ? 0 : 1f;
        }
    }
}
