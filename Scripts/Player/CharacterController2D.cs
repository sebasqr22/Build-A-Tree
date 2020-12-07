using System.Collections;
using UnityEngine;
using UnityEngine.Events;
using UnityEngine.UI;
using TMPro;
using Client;
using System.Text;
using System;
using System.Net.Sockets;

public class CharacterController2D : MonoBehaviour
{

	/// <summary>
	/// Controls the player properties in game an check if the player is out the gamezone
	/// move the player an active the powerups in game
	/// </summary>
	[SerializeField] private float m_JumpForce = 400f;							// Amount of force added when the player jumps.
	[Range(0, 1)] [SerializeField] private float m_CrouchSpeed = .36f;			// Amount of maxSpeed applied to crouching movement. 1 = 100%
	[Range(0, .3f)] [SerializeField] private float m_MovementSmoothing = .05f;	// How much to smooth out the movement
	[SerializeField] private bool m_AirControl = false;							// Whether or not a player can steer while jumping;
	[SerializeField] private LayerMask m_WhatIsGround;							// A mask determining what is ground to the character
	[SerializeField] private Transform m_GroundCheck;                           // A position marking where to check if the player is grounded.
    [SerializeField] private Transform m_CeilingCheck;                          // A position marking where to check for ceilings
	[SerializeField] private GameObject m_shield;								//A object to create a shield for the player
	[SerializeField] private Collider2D m_CrouchDisableCollider;				// A collider that will be disabled when crouching
	[SerializeField] private Transform m_respawn;
	[SerializeField] private Text m_tokeni;										//A indicator to the score of the player
	[SerializeField] private int score = 0;										//Actual score of the player
	[SerializeField] private GameController gameController;                     //general gameobjects
	[SerializeField] private Collider2D m_shieldcollider;						//collider of the shield
	[SerializeField] private GameObject pushRadius;
	[SerializeField] private TMP_Text m_poderindicator;

	const float k_GroundedRadius = .2f; // Radius of the overlap circle to determine if grounded
	private bool m_Grounded;            // Whether or not the player is grounded.
	const float k_CeilingRadius = .2f; // Radius of the overlap circle to determine if the player can stand up
    
    private Rigidbody2D m_Rigidbody2D;
	private bool m_FacingRight = true;  // For determining which way the player is currently facing.
	private Vector3 m_Velocity = Vector3.zero;

	private string power;


	[Header("Events")]
	[Space]

	public UnityEvent OnLandEvent;

	[System.Serializable]
	public class BoolEvent : UnityEvent<bool> { }

	public BoolEvent OnCrouchEvent;
	private bool m_wasCrouching = false;

	private void Awake()
	{

		m_Rigidbody2D = GetComponent<Rigidbody2D>();

		if (OnLandEvent == null)
			OnLandEvent = new UnityEvent();

		if (OnCrouchEvent == null)
			OnCrouchEvent = new BoolEvent();
	}

	private void FixedUpdate()
	{
		bool wasGrounded = m_Grounded;
		m_Grounded = false;

		// The player is grounded if a circlecast to the groundcheck position hits anything designated as ground
		// This can be done using layers instead but Sample Assets will not overwrite your project settings.
		Collider2D[] colliders = Physics2D.OverlapCircleAll(m_GroundCheck.position, k_GroundedRadius, m_WhatIsGround);
		for (int i = 0; i < colliders.Length; i++)
		{
			if (colliders[i].gameObject != gameObject)
			{
				m_Grounded = true;
				if (!wasGrounded)
					OnLandEvent.Invoke();
			}
		}
        if (power == "")
        {
			m_poderindicator.text = "Sin poder Disponible";
        }
	}

	public void Move(float move, bool crouch, bool jump)
	{
		// If crouching, check to see if the character can stand up
		if (!crouch)
		{
			// If the character has a ceiling preventing them from standing up, keep them crouching
			if (Physics2D.OverlapCircle(m_CeilingCheck.position, k_CeilingRadius, m_WhatIsGround))
			{
				crouch = true;
			}
		}

		//only control the player if grounded or airControl is turned on
		if (m_Grounded || m_AirControl)
		{

			// If crouching
			if (crouch)
			{
				if (!m_wasCrouching)
				{
					m_wasCrouching = true;
					OnCrouchEvent.Invoke(true);
				}

				// Reduce the speed by the crouchSpeed multiplier
				move *= m_CrouchSpeed;

				// Disable one of the colliders when crouching
				if (m_CrouchDisableCollider != null)
					m_CrouchDisableCollider.enabled = false;
			} else
			{
				// Enable the collider when not crouching
				if (m_CrouchDisableCollider != null)
					m_CrouchDisableCollider.enabled = true;

				if (m_wasCrouching)
				{
					m_wasCrouching = false;
					OnCrouchEvent.Invoke(false);
				}
			}

			// Move the character by finding the target velocity
			Vector3 targetVelocity = new Vector2(move * 10f, m_Rigidbody2D.velocity.y);
			// And then smoothing it out and applying it to the character
			m_Rigidbody2D.velocity = Vector3.SmoothDamp(m_Rigidbody2D.velocity, targetVelocity, ref m_Velocity, m_MovementSmoothing);

			// If the input is moving the player right and the player is facing left...
			if (move > 0 && !m_FacingRight)
			{
				// ... flip the player.
				Flip();
			}
			// Otherwise if the input is moving the player left and the player is facing right...
			else if (move < 0 && m_FacingRight)
			{
				// ... flip the player.
				Flip();
			}
		}
		// If the player should jump...
		if (m_Grounded && jump)
		{
			// Add a vertical force to the player.
			m_Grounded = false;
			m_Rigidbody2D.AddForce(new Vector2(0f, m_JumpForce));
		}
	}


	private void Flip()
	{
		// Switch the way the player is labelled as facing.
		m_FacingRight = !m_FacingRight;

		// Multiply the player's x local scale by -1.
		Vector3 theScale = transform.localScale;
		theScale.x *= -1;
		transform.localScale = theScale;
	}

	void OnTriggerEnter2D(Collider2D collider)//when the player touch any collider
    {
        if (collider.tag == "Fall")//Fall is the coollision that kills the player
		{
			transform.position = m_respawn.position;
        }
		if(collider.tag == "Token")
        {
			Destroy(collider.gameObject);
			score += 1;
			m_tokeni.text = score.ToString();
        }
		if(collider.tag == "power_up")
        {
			powercontroller powercontrol = collider.gameObject.GetComponent<powercontroller>();
			power = powercontrol.GetTipo();
			m_poderindicator.text = string.Concat("Poder Disponible: ", power);


			Destroy(collider.gameObject);
        }
    }

	public void Shield()//fuction that initialize the shield of the player
    {
		m_CrouchDisableCollider.enabled = false;
		m_shieldcollider.enabled = true;
		m_shield.SetActive(true);
		m_Rigidbody2D.mass = 70;//makes the player almost imposible to move
		m_JumpForce = 45500;

		StartCoroutine(resetPower("Shield"));
    }
	private IEnumerator resetPower(string power)// wait 10 seconds and return to the default configuration
    {
		if(power == "Shield")
        {
			yield return new WaitForSeconds(10f);
			m_CrouchDisableCollider.enabled = true;
			m_shieldcollider.enabled = false;
			m_shield.SetActive(false);
			m_JumpForce = 550;
			m_Rigidbody2D.mass = 1;
        }else if(power=="PushForce"){
			yield return new WaitForSeconds(3f);//wait 3 seconds to desactivate the force push
			pushRadius.SetActive(false);
        }
	}


	public void AirJump()//create a force to the player
    {
		m_Rigidbody2D.AddForce(new Vector2(0f,1000f));//the player jump to high
	}

	public void ForcePush()//active the radius of repulsion
    {
		pushRadius.SetActive(true);

		StartCoroutine(resetPower("PushForce"));
    }


    public int GetScores()//return the score of the player
    {
		return score;
    }
	public string GetPower()//return the actualpower in inventory
    {
		return power;
    }

	public void Resetpower()
    {
		this.power = "";
    }
}

