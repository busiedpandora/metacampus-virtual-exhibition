using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class TestHTTPRequest : MonoBehaviour
{
    public static string metaverseName = "Campus Est SUPSI";

    public static string ServerUrl = $"http://www.localhost:8080/classrooms/get/{metaverseName}";


    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(GetDataFromServer());
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public IEnumerator GetRequest(string endpoint, Action<string> onSuccess, Action<string> onError)
    {
        string requestUrl = ServerUrl + endpoint;

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

    IEnumerator GetDataFromServer()
    {
        string endpoint = ""; // Replace this with your actual endpoint

        yield return StartCoroutine(GetRequest(endpoint,
            onSuccess: response =>
            {
                Debug.Log("Received response: " + response);
                // Do something with the response
            },
            onError: error =>
            {
                Debug.LogError("Error occurred: " + error);
                // Handle the error
            }));
    }
}
