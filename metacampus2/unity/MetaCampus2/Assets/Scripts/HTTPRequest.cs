using System;
using System.Collections;
using UnityEngine;
using UnityEngine.Networking;

public class HTTPRequest : MonoBehaviour
{
    private string responseData = null;
    private AudioClip audioClip = null;


    public string ResponseData
    {
        get { return responseData; }
    }

    public AudioClip AudioClip
    {
        get { return audioClip; }
    }

    private IEnumerator GetRequest(string serverUrl, string endpoint, Action<string> onSuccess, Action<string> onError)
    {
        string requestUrl = serverUrl + endpoint;

        using (UnityWebRequest webRequest = UnityWebRequest.Get(requestUrl))
        {
            yield return webRequest.SendWebRequest();

            if (webRequest.result == UnityWebRequest.Result.ConnectionError || webRequest.result == UnityWebRequest.Result.ProtocolError)
            {
                onError?.Invoke(webRequest.error);
            }
            else
            {
                onSuccess?.Invoke(webRequest.downloadHandler.text);
            }
        }
    }

    public IEnumerator GetDataFromServer(string serverUrl, string endpoint)
    {
        yield return StartCoroutine(GetRequest(serverUrl, endpoint,
            onSuccess: response =>
            {
                //Debug.Log("Received response: " + response);
                responseData = response;
            },
            onError: error =>
            {
                Debug.LogError("Error occurred: " + error);
                DebugLog.instance.Log("Error occurred: ", error + " " + serverUrl);
            }));

        if(responseData != null)
        {
            yield return responseData;
        }
    }

    public IEnumerator GetAudioClipFromServer(string url)
    {
        using (UnityWebRequest unityWebRequest = UnityWebRequestMultimedia.GetAudioClip(url, AudioType.WAV))
        {
           yield return unityWebRequest.SendWebRequest();

           if (unityWebRequest.result != UnityWebRequest.Result.Success)
           {
                Debug.Log("Audio download failed: " + unityWebRequest.error);
                DebugLog.instance.Log("Audio download failed: ", unityWebRequest.error);
            }
           else
           {
                audioClip = DownloadHandlerAudioClip.GetContent(unityWebRequest);
           }
        }

        if(audioClip != null)
        {
            yield return audioClip;
        }
    }
}
