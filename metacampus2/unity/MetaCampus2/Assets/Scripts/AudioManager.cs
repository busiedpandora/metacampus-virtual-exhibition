using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AudioManager : MonoBehaviour
{
    private AudioSource imageAudioSource;
    private bool isPaused = false;
    private bool isWaitingToReplay = false;
    private Coroutine waitToReplayCoroutine = null;

    private bool isCameraClose = false;

    public bool IsCameraClose
    {
        get { return isCameraClose; }
        set { isCameraClose = value; }
    }

    public bool IsPaused
    {
        get { return isPaused; }
    }

    public bool IsWaitingToReplay
    {
        get { return isWaitingToReplay; }
    }


    // Start is called before the first frame update
    void Start()
    {
        imageAudioSource = GetComponent<AudioSource>();
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
            isPaused = false;

            if(waitToReplayCoroutine != null)
            {
                StopCoroutine(waitToReplayCoroutine);
            }

            isWaitingToReplay = true;
            waitToReplayCoroutine = StartCoroutine(WaitToReplayAudio(GetAudioClipDuration() + 5.0f));
        }
    }

    public void PauseAudio()
    {
        if (imageAudioSource != null)
        {
            if (imageAudioSource.isPlaying)
            {
                imageAudioSource.Pause();
                isPaused = true;
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
                isPaused = false;

                if (waitToReplayCoroutine != null)
                {
                    StopCoroutine(waitToReplayCoroutine);
                }

                isWaitingToReplay = true;
                waitToReplayCoroutine = StartCoroutine(WaitToReplayAudio(GetAudioClipDuration() + 5.0f));
            }
        }
    }

    public void StopAudio()
    {
        if(imageAudioSource != null)
        {
            imageAudioSource.Stop();
        }
    }

    public bool IsAudioPlaying()
    {
        return imageAudioSource.isPlaying;
    }

    public float GetAudioClipDuration()
    {
        if(imageAudioSource != null && imageAudioSource.clip != null)
        {
            return imageAudioSource.clip.length;
        }

        return 0f;
    }

    private IEnumerator WaitToReplayAudio(float delay)
    {
        yield return new WaitForSeconds(delay);

        isWaitingToReplay = false;
        waitToReplayCoroutine = null;
    }

    public void OnCameraExit()
    {
        imageAudioSource.Stop();

        if (waitToReplayCoroutine != null)
        {
            StopCoroutine(waitToReplayCoroutine);
        }

        isWaitingToReplay = false;
    }
}
