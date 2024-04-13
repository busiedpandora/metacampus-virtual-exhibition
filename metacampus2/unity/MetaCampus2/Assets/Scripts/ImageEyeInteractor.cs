using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ImageEyeInteractor : MonoBehaviour, EyeInteractive
{
    private AudioManager audioManager;

    // Start is called before the first frame update
    void Start()
    {
        audioManager = GetComponent<AudioManager>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void EyeInteract()
    {
        if(audioManager != null)
        {
            if(!audioManager.IsAudioPlaying())
            {
                if (!audioManager.IsPaused && !audioManager.IsWaitingToReplay && audioManager.IsCameraClose)
                {
                    audioManager.PlayAudio();
                }
            }
        }
    }
}
