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
        audioButtons.SetActive(false);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("MainCamera"))
        {
            titleBar.SetActive(true);

            if(audioManager.GetAudioClipDuration() > 0f)
            {
                audioButtons.SetActive(true);
            }

            audioManager.IsCameraClose = true;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.CompareTag("MainCamera"))
        {
            titleBar.SetActive(false);

            audioButtons.SetActive(false);

            audioManager.IsCameraClose = false;
            //audioManager.StopAudio();
            audioManager.OnCameraExit();
        }
    }
}
