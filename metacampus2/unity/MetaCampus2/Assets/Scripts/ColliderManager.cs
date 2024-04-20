using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ColliderManager : MonoBehaviour
{
    [SerializeField] private AudioManager audioManager;
    [SerializeField] private GameObject imageButtons;

    // Start is called before the first frame update
    void Start()
    {
        imageButtons.SetActive(false);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("MainCamera"))
        {
            if(audioManager.GetAudioClipDuration() > 0f)
            {
                imageButtons.SetActive(true);
            }

            audioManager.IsCameraClose = true;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.CompareTag("MainCamera"))
        {
            imageButtons.SetActive(false);

            audioManager.IsCameraClose = false;
            //audioManager.StopAudio();
            audioManager.OnCameraExit();
        }
    }
}
