using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ColliderManager : MonoBehaviour
{
    [SerializeField] private AudioManager audioManager;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other)
    {
        Debug.Log("On trigger enter");
        if (other.CompareTag("MainCamera"))
        {
            audioManager.IsCameraClose = true;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        Debug.Log("On trigger exit");
        if (other.CompareTag("MainCamera"))
        {
            audioManager.IsCameraClose = false;
            audioManager.StopAudio();
        }
    }
}
