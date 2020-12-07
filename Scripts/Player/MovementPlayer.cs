using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovementPlayer : MonoBehaviour
{
	/// <summary>
	/// Class to listen the keys to move the player an active the powers of the player
	/// </summary>


	//Keys to move the player
	public KeyCode k_jump;//key to jump
	public KeyCode k_crouch;
	public KeyCode k_left;
	public KeyCode k_right;
	public KeyCode k_power;
	public CharacterController2D controller;//controller of the player

	public float runSpeed = 40f;

	public Animator animator;

	float horizontalMove = 0f;
	bool jump = false;
	bool crouch = false;

	
	//public keycode attack;

	// Update is called once per frame
	void Update()
	{
		if (Input.GetKey(k_left))
		{
			horizontalMove = -1 * runSpeed;
		}
		else if (Input.GetKey(k_right))
		{
			horizontalMove = 1 * runSpeed;
        }
        else
        {
			horizontalMove = 0;
        }

		animator.SetFloat("speed", Mathf.Abs(horizontalMove));

		if (Input.GetKeyDown(k_jump))//the key asignned to jump
		{
			jump = true;
			animator.SetBool("jumping", true);
		}

		if (Input.GetKeyDown(k_crouch))//the key assigned to crouch
		{
			crouch = true;
		}
		else if (Input.GetKeyUp(k_crouch))
		{
			crouch = false;
		}

        if (Input.GetKeyDown(k_power))//active the power
        {
            if (controller.GetPower() == "Shield")
            {
				controller.Shield();
            }
            if (controller.GetPower() == "Airjump")
            {
				controller.AirJump();
				animator.SetBool("jumping", true);
			}
            if (controller.GetPower()=="Forcepush")
            {
				controller.ForcePush();
            }
			controller.Resetpower();//reset the power of the player
		}

	}

	//If the player touch ground
	public void OnLanding()
    {
		animator.SetBool("jumping",false);
    }
	//if the player wants to crouch
	public void OnCrouch(bool crouch)
    {
		animator.SetBool("crouching", crouch);
    }
	///
	void FixedUpdate()
	{
		// Move our character
		controller.Move(horizontalMove * Time.fixedDeltaTime, crouch, jump);
		jump = false;
	}
}
