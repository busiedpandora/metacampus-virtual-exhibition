using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AudioManager : MonoBehaviour
{
    [SerializeField] private GameObject image;

    private AudioSource imageAudioSource;


    // Start is called before the first frame update
    void Start()
    {
        imageAudioSource = image.GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void RestartAudio()
    {
        if(imageAudioSource != null)
        { 
            imageAudioSource.Stop();  
            imageAudioSource.Play();
        }
    }

    public void PauseAudio()
    {
        if (imageAudioSource != null)
        {
            if (imageAudioSource.isPlaying)
            {
                imageAudioSource.Pause();
            }
        }
    }

    public void PlayAudio()
    {
        if (imageAudioSource != null)
        {
            if (!imageAudioSource.isPlaying)
            {
                imageAudioSource.Play();
            }
        }
    }
}
