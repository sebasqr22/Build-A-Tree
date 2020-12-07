using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class scenechanger : MonoBehaviour
{
    /// <summary>
    /// Change the scene of the game
    /// </summary>
    public void change()
    {
        try
        {
            Destroy(permanentscore.permanent.GetComponent<GameObject>());
        }
        catch
        {
            Debug.Log("permanet does't exist");
        }
        Time.timeScale = 1f;
        SceneManager.LoadScene(0);
    }
}
