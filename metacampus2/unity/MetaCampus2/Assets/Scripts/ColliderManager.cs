using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ColliderManager : MonoBehaviour
{
    [SerializeField] private AudioManager audioManager;
    [SerializeField] private GameObject audioButtons;
    [SerializeField] private GameObject titleBar;

    // Start is called before the first frame update
    void Start()
    {
        if(audioButtons != null)
        {
            audioButtons.SetActive(false);
        }

        if(titleBar != null)
        {
            titleBar.SetActive(false);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("MainCamera"))
        {
            if (titleBar != null)
            {
                titleBar.SetActive(true);
            }

            if (audioManager != null)
            {
                audioManager.IsCameraClose = true;

                if (audioManager.GetAudioClipDuration() > 0f && audioButtons != null)
                {
                    audioButtons.SetActive(true);
                }
            }
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.CompareTag("MainCamera"))
        {
            if (titleBar != null)
            {
                titleBar.SetActive(false);
            }

            if(audioButtons != null)
            {
                audioButtons.SetActive(false);
            }
            
            if(audioManager != null)
            {
                audioManager.IsCameraClose = false;
                //audioManager.StopAudio();
                audioManager.OnCameraExit();
            }
        }
    }
}
