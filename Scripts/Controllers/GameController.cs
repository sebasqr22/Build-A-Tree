using System.Collections;
using UnityEngine;



[System.Serializable]
public class GameController : MonoBehaviour
{
    public static GameController Instance { get; private set; }
    /// <summary>
    /// The virtual camera in the scene.
    /// </summary>
    public Cinemachine.CinemachineVirtualCamera virtualCamera;

    /// <summary>
    /// The main component which controls the player sprite, controlled 
    /// by the user.
    /// </summary>
    public CharacterController2D player1;
    public CharacterController2D player2;
    public CharacterController2D player3;
    public CharacterController2D player4;



    /// <summary>
    /// The spawn point in the scene.
    /// </summary>
    public Transform spawnPoint;
}
