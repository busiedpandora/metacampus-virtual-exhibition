using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class HTTPRequest : MonoBehaviour
{
    private string responseData = null;


    public string ResponseData
    {
        get { return responseData; }
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
}
