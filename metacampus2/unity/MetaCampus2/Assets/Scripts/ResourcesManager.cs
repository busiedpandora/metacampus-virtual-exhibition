using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Newtonsoft.Json;
using Unity.XR.CoreUtils;
using System;
using UnityEngine.Windows;
using TMPro;
using System.IO;
using UnityEngine.UIElements;
using UnityEngine.UI;
using UnityEngine.Tilemaps;
using System.Text;
using Newtonsoft.Json.Linq;
using Unity.Collections;
using UnityEngine.Networking;
using Unity.VisualScripting;
using System.Net;
using System.Resources;

public class ResourcesManager : MonoBehaviour
{
    [SerializeField] private GameObject textPanelObject;
    [SerializeField] private GameObject oneDisplayPanelExhibitionObject;
    [SerializeField] private GameObject fourDisplayPanelStraightExhibitionObject;
    [SerializeField] private GameObject fourDisplayPanelRectangularExhibitionObject;
    [SerializeField] private GameObject sixDisplayPanelDiagonalExhibitionObject;
    [SerializeField] private GameObject sixDisplayPanelCircularExhibitionObject;

    private string spacesServerUrl = "";
    private string resourcesServerUrl = "";

    private HTTPRequest httpRequest;

    private GameObject coordinatesSystem;
    private CoordinatesManager coordinatesManager;

    private float verticalMarginPosition = 0.15f;


    private void Awake()
    {
        httpRequest = GetComponent<HTTPRequest>();
    }

    // Start is called before the first frame update
    void Start()
    {
        coordinatesSystem = GameObject.Find("CoordinatesSystem");
        coordinatesManager = coordinatesSystem.GetComponent<CoordinatesManager>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void StartInitResources()
    {
        try
        {
            StartCoroutine(InitTexts());
        }
        catch(Exception e)
        {
            DebugLog.instance.Log("Exception Occurred", e.Message);
        }
    }

    private IEnumerator InitTexts()
    {
        spacesServerUrl = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.spacesPath}/{MetaverseSelectionManager.metaverseUrlNameSelected}/text-panels";
        
        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(spacesServerUrl, ""));

        responseData = httpRequest.ResponseData;

        if (responseData != null)
        {
            Debug.Log($"Text panels: {responseData}");

            var textPanels = JsonConvert.DeserializeObject<List<TextPanelSerializable>>(responseData);

            foreach (var textPanel in textPanels)
            {
                if (textPanel.text != null)
                {
                    var coord = textPanel.coordinates;
                    var coordObject = coordinatesManager.GetGameObject(coord.x, coord.y, coord.z);

                    if (coordObject != null)
                    {
                        var position = new Vector3(coord.x, coord.y - verticalMarginPosition, coord.z);

                        var textPanelInstance = Instantiate(textPanelObject, position, Quaternion.identity);
                        textPanelInstance.transform.parent = coordObject.transform;

                        var textTitle = textPanel.text.title;
                        var textFileName = textPanel.text.fileName;
                        resourcesServerUrl = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.spacesPath}/{MetaverseSelectionManager.metaverseUrlNameSelected}/text-panels/{textPanel.urlName}/texts/{textTitle}/{textFileName}";
                        yield return StartCoroutine(httpRequest.GetDataFromServer(resourcesServerUrl, ""));
                        responseData = httpRequest.ResponseData;

                        if (responseData != null)
                        {
                            byte[] textData = System.Convert.FromBase64String(responseData);
                            string text = Encoding.UTF8.GetString(textData);
                            
                            var frontText = textPanelInstance.transform.Find("Panel/Board/FrontText").gameObject;
                            var backText = textPanelInstance.transform.Find("Panel/Board/BackText").gameObject;

                            frontText.GetComponent<TextMeshPro>().text = text;
                            backText.GetComponent<TextMeshPro>().text = text;

                            var frontTextTitleBar = textPanelInstance.transform.Find("Panel/FrontTextTitleBar/Title").gameObject;
                            var backTextTitleBar = textPanelInstance.transform.Find("Panel/BackTextTitleBar/Title").gameObject;

                            frontTextTitleBar.GetComponent<TextMeshPro>().text = textTitle;
                            backTextTitleBar.GetComponent<TextMeshPro>().text = textTitle;
                        }
                    }
                }
            }
        }

        yield return StartCoroutine(InitImages());
    }

    private IEnumerator InitImages()
    {
        spacesServerUrl = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.spacesPath}/{MetaverseSelectionManager.metaverseUrlNameSelected}/display-panels";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(spacesServerUrl, ""));

        responseData = httpRequest.ResponseData;

        if (responseData != null)
        {
            Debug.Log($"Display panels: {responseData}");

            var displayPanels = JsonConvert.DeserializeObject<List<DisplayPanelSerializable>>(responseData);

            foreach (var displayPanel in displayPanels)
            {
                if (displayPanel.images != null && displayPanel.images.Count > 0)
                {
                    var coord = displayPanel.coordinates;
                    var coordObject = coordinatesManager.GetGameObject(coord.x, coord.y, coord.z);

                    if (coordObject != null)
                    {
                        var position = new Vector3(coord.x, coord.y - verticalMarginPosition, coord.z);
                        int imagesCount = displayPanel.images.Count;

                        GameObject panelInstance = null;
                        if (displayPanel.type == "ONE_DISPLAY_PANEL_EXHIBITION")
                        {
                            panelInstance = Instantiate(oneDisplayPanelExhibitionObject, position, Quaternion.identity);
                        }
                        else if (displayPanel.type == "FOUR_DISPLAY_PANEL_STRAIGHT_EXHIBITION")
                        {
                            panelInstance = Instantiate(fourDisplayPanelStraightExhibitionObject, position, Quaternion.identity);
                        }
                        else if (displayPanel.type == "FOUR_DISPLAY_PANEL_RECTANGULAR_EXHIBITION")
                        {
                            panelInstance = Instantiate(fourDisplayPanelRectangularExhibitionObject, position, Quaternion.identity);
                        }
                        else if (displayPanel.type == "SIX_DISPLAY_PANEL_DIAGONAL_EXHIBITION")
                        {
                            panelInstance = Instantiate(sixDisplayPanelDiagonalExhibitionObject, position, Quaternion.identity);
                        }
                        else if (displayPanel.type == "SIX_DISPLAY_PANEL_CIRCULAR_EXHIBITION")
                        {
                            panelInstance = Instantiate(sixDisplayPanelCircularExhibitionObject, position, Quaternion.identity);
                        }
                        panelInstance.transform.parent = coordObject.transform;

                        for (int i = 0; i < imagesCount; i++)
                        {
                            ImageSerializable image = displayPanel.images[i];
                            string imageTitle = image.title;
                            string imageFileName = image.fileName;

                            resourcesServerUrl = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.spacesPath}/{MetaverseSelectionManager.metaverseUrlNameSelected}/display-panels/{displayPanel.urlName}/images/{imageTitle}/{imageFileName}";
                            yield return StartCoroutine(httpRequest.GetDataFromServer(resourcesServerUrl, ""));
                            responseData = httpRequest.ResponseData;
                            if (responseData != null)
                            {
                                byte[] imageData = System.Convert.FromBase64String(responseData);

                                Texture2D texture = new Texture2D(2, 2);
                                texture.LoadImage(imageData);


                                var imageIstance = panelInstance.transform.Find($"DisplayPanel{(i / 2) + 1}/Board/Canvas/Image{(i % 2) + 1}").gameObject;
                                imageIstance.GetComponent<RawImage>().texture = texture;

                                var imageTitleBar = panelInstance.transform.Find($"DisplayPanel{(i / 2) + 1}/Image{(i % 2) + 1}TitleBar/Title").gameObject;
                                imageTitleBar.GetComponent<TextMeshPro>().text = imageTitle;

                                AudioSerializable audio = image.audio;
                                if(audio != null)
                                {
                                    resourcesServerUrl = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.spacesPath}/{MetaverseSelectionManager.metaverseUrlNameSelected}/display-panels/{displayPanel.urlName}/images/{imageTitle}/audios/{audio.title}/{audio.fileName}";
                                    yield return StartCoroutine(httpRequest.GetAudioClipFromServer(resourcesServerUrl));
                                    AudioClip audioClip = httpRequest.AudioClip;
                                    if (audioClip != null)
                                    {
                                        AudioSource audioSource = imageIstance.GetComponent<AudioSource>();
                                        audioSource.clip = audioClip;
                                    }

                                    var audioTitleBar = panelInstance.transform.Find($"DisplayPanel{(i / 2) + 1}/Board/Image{(i % 2) + 1}Buttons/Image{(i % 2) + 1}AudioTitleBar/Title").gameObject;
                                    audioTitleBar.GetComponent<TextMeshPro>().text = audio.title;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
