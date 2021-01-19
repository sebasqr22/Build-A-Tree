using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Mensaje : MonoBehaviour
{
        public string mensaje;

        public Mensaje(string mensaje)
        {
        this.mensaje = mensaje;
        }

        public string GetMensaje()
        {
            return mensaje;
        }
        public void SetMensaje(string mensaje)
        {
            this.mensaje = mensaje;
        }
}
