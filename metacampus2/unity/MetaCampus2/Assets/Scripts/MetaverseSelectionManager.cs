using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Newtonsoft.Json;
using TMPro;
using System;
using MixedReality.Toolkit.UX;
using UnityEngine.SceneManagement;

public class MetaverseSelectionManager : MonoBehaviour
{
    public static string metaverseNameSelected = "";
    public static string metaverseUrlNameSelected = "";

    [SerializeField] private GameObject metaversePressableButton;

    private HTTPRequest httpRequest;
    private const float offset = 0.041f;

    private int buttonsInstantiated = 0;


    private void Awake()
    {
        httpRequest = GetComponent<HTTPRequest>();
    }

    // Start is called before the first frame update
    void Start()
    {
        try
        {
            StartCoroutine(SetUpMetaverseButtons());
        }
        catch (Exception e)
        {
            DebugLog.instance.Log("Exception Occurred", e.Message);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private IEnumerator SetUpMetaverseButtons()
    {
        string url = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.metaversesPath}/{HTTPInfo.metaversesListPath}";

        string responseData = null;
        yield return StartCoroutine(httpRequest.GetDataFromServer(url, ""));
        responseData = httpRequest.ResponseData;

        if(responseData != null )
        {
            Debug.Log($"Metaverses: {responseData}");

            var metaverses = JsonConvert.DeserializeObject<List<MetaverseSerializable>>(responseData);

            foreach(var metaverse in metaverses) {
                var position = new Vector3(0f, 0 - offset * buttonsInstantiated++, 0f);

                var metaverseButtonInstance = Instantiate(metaversePressableButton);
                metaverseButtonInstance.transform.parent = this.transform;
                metaverseButtonInstance.transform.localPosition = position;
                
                var tmp = metaverseButtonInstance.transform.Find("CompressableButtonVisuals/IconAndText/TextMeshPro");
                if(tmp != null) {
                    tmp.GetComponent<TextMeshPro>().text = metaverse.name;
                }

                MetaverseInfo metaverseInfo = metaverseButtonInstance.AddComponent<MetaverseInfo>();
                metaverseInfo.name = metaverse.name;
                metaverseInfo.urlName = metaverse.urlName;

                var button = metaverseButtonInstance.GetComponent<PressableButton>();
                if (button != null)
                {
                    button.OnClicked.AddListener(() =>
                    {
                        LoadMetaverseScene(metaverse.name, metaverse.urlName);
                    });
                }
            }
        }
    }

    public void LoadMetaverseScene(string metaverseName, string metaverseUrlName)
    {
        Debug.Log($"Loading scene for {metaverseName} with URL {metaverseUrlName}");

        metaverseNameSelected = metaverseName;
        metaverseUrlNameSelected = metaverseUrlName;

        SceneManager.LoadSceneAsync(1);
    }
}
