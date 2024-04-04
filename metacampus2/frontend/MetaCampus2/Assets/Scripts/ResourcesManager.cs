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
    [SerializeField] private GameObject sixDisplayPanelDiagonalExhibitionObject;
    [SerializeField] private GameObject sixDisplayPanelCircularExhibitionObject;

    private const string metaverseUrlName = "campus-est-supsi";

    //private const string hostName = "192.168.45.81"; //hotspot
    private const string hostName = "10.21.56.224"; //eduroam
    //private const string hostName = "localhost";
    private const string port = "8080";
    private const string spacesPath = "spaces";
    private string spacesServerUrl = "";
    private const string resourcesPath = "resources";
    private string resourcesServerUrl = "";

    private HTTPRequest httpRequest;

    private GameObject coordinatesSystem;
    private CoordinatesManager coordinatesManager;


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
        string spacePath = "text-panels";
        string resourcePath = "texts";

        spacesServerUrl = $"http://{hostName}:{port}/{spacesPath}/{metaverseUrlName}/{spacePath}";

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
                        var position = new Vector3(coord.x, coord.y, coord.z);

                        var textPanelInstance = Instantiate(textPanelObject, position, Quaternion.identity);
                        textPanelInstance.transform.parent = coordObject.transform;

                        var textName = textPanel.text.name;
                        resourcesServerUrl = $"http://{hostName}:{port}/{spacesPath}/{metaverseUrlName}/{spacePath}/{textPanel.urlName}/{resourcePath}/{textName}";
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
                        }
                    }
                }
            }
        }

        yield return StartCoroutine(InitImages());
    }

    private IEnumerator InitImages()
    {
        string spacePath = "display-panels";
        string imagesPath = "images";
        string audiosPath = "audios";
        spacesServerUrl = $"http://{hostName}:{port}/{spacesPath}/{metaverseUrlName}/{spacePath}";

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
                        var position = new Vector3(coord.x, coord.y, coord.z);
                        int imagesCount = displayPanel.images.Count;

                        GameObject panelInstance = null;
                        if (displayPanel.type == "SINGLE")
                        {
                            panelInstance = Instantiate(oneDisplayPanelExhibitionObject, position, Quaternion.identity);
                        }
                        else if (displayPanel.type == "SIX_PACK_DIAGONAL")
                        {
                            panelInstance = Instantiate(sixDisplayPanelDiagonalExhibitionObject, position, Quaternion.identity);
                        }
                        else if (displayPanel.type == "SIX_PACK_CIRCULAR")
                        {
                            panelInstance = Instantiate(sixDisplayPanelCircularExhibitionObject, position, Quaternion.identity);
                        }
                        panelInstance.transform.parent = coordObject.transform;

                        for (int i = 0; i < imagesCount; i++)
                        {
                            ImageSerializable image = displayPanel.images[i];
                            string imageName = image.name;

                            resourcesServerUrl = $"http://{hostName}:{port}/{spacesPath}/{metaverseUrlName}/{spacePath}/{displayPanel.urlName}/{imagesPath}/{imageName}";
                            yield return StartCoroutine(httpRequest.GetDataFromServer(resourcesServerUrl, ""));
                            responseData = httpRequest.ResponseData;
                            if (responseData != null)
                            {
                                byte[] imageData = System.Convert.FromBase64String(responseData);

                                Texture2D texture = new Texture2D(2, 2);
                                texture.LoadImage(imageData);


                                var imageIstance = panelInstance.transform.Find($"DisplayPanel{(i / 2) + 1}/Board/Canvas/Image{(i % 2) + 1}").gameObject;
                                imageIstance.GetComponent<RawImage>().texture = texture;

                                AudioSerializable audio = image.audio;
                                if(audio != null)
                                {
                                    resourcesServerUrl = $"http://{hostName}:{port}/{spacesPath}/{metaverseUrlName}/{spacePath}/{displayPanel.urlName}/{imagesPath}/{imageName}/{audiosPath}/{audio.name}";
                                    yield return StartCoroutine(httpRequest.GetAudioClipFromServer(resourcesServerUrl));
                                    AudioClip audioClip = httpRequest.AudioClip;
                                    if (audioClip != null)
                                    {
                                        AudioSource audioSource = imageIstance.GetComponent<AudioSource>();
                                        audioSource.clip = audioClip;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*
    private IEnumerator InitClassrooms()
    {
        string pathResource = "classrooms";
        serverUrl = $"http://{hostName}:{port}/{metaverseName}/{pathResource}";
        
        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

        responseData = httpRequest.ResponseData;

        if(responseData != null)
        {
            Debug.Log($"Classrooms: {responseData}");

            //var resourcesInfos = JsonConvert.DeserializeObject<List<ResourcesInfo>>(responseData);
            //var classrooms = JsonUtility.FromJson<ClassroomSerializable[]>(responseData);
            var classrooms = JsonConvert.DeserializeObject<List<ClassroomSerializable>>(responseData);

            foreach (var c in classrooms)
            {
                var l = c.location;

                var gameObject = coordinatesManager.GetGameObject(l.floorNumber, l.xPosition, l.zPosition);
                if(gameObject != null)
                {
                    var classroom = gameObject.AddComponent<Classroom>();
                    classroom.Number = c.number;
                }
            }
        }

        yield return StartCoroutine(InitOffices());
    }

    private IEnumerator InitOffices()
    {
        string pathResource = "offices";
        serverUrl = $"http://{hostName}:{port}/{metaverseName}/{pathResource}";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

        responseData = httpRequest.ResponseData;

        if (responseData != null)
        {
            Debug.Log($"Offices: {responseData}");

            var offices = JsonConvert.DeserializeObject<List<OfficeSerializable>>(responseData);

            foreach (var o in offices)
            {
                var l = o.location;

                var gameObject = coordinatesManager.GetGameObject(l.floorNumber, l.xPosition, l.zPosition);
                if (gameObject != null)
                {
                    var office = gameObject.AddComponent<Office>();
                    office.Number = o.number;
                }
            }
        }

        yield return StartCoroutine(InitPeople());
    }

    private IEnumerator InitPeople()
    {
        string pathResource = "people";
        serverUrl = $"http://{hostName}:{port}/{metaverseName}/{pathResource}";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

        responseData = httpRequest.ResponseData;

        if (responseData != null)
        {
            Debug.Log($"People: {responseData}");

            var people = JsonConvert.DeserializeObject<List<PersonSerializable>>(responseData);

            foreach (var p in people)
            {
                var l = p.location;

                var gameObject = coordinatesManager.GetGameObject(l.floorNumber, l.xPosition, l.zPosition);
                if (gameObject != null)
                {
                    var person = gameObject.AddComponent<Person>();
                    person.FirstName = p.firstName;
                    person.LastName = p.lastName;
                    person.Role = p.role;
                    person.Cellphone = p.cellphone;
                    person.OfficePhone = p.officePhone;
                    person.OfficeNumber = p.office != null ? p.office.number : "";
                }
            }
        }

        yield return StartCoroutine(InitEvent());
    }

    private IEnumerator InitEvent()
    {
        string pathResource = "events";
        serverUrl = $"http://{hostName}:{port}/{metaverseName}/{pathResource}";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

        responseData = httpRequest.ResponseData;

        if (responseData != null)
        {
            Debug.Log($"Events: {responseData}");

            var events = JsonConvert.DeserializeObject<List<EventSerializable>>(responseData);

            foreach (var e in events)
            {
                var l = e.location;

                var gameObject = coordinatesManager.GetGameObject(l.floorNumber, l.xPosition, l.zPosition);
                if (gameObject != null)
                {
                    var _event = gameObject.AddComponent<Event>();
                    _event.Name = e.name;

                    string format = "yyyy-MM-ddTHH:mm:ss";
                    DateTime result;
                    if(DateTime.TryParseExact(e.dateTime, format, null, System.Globalization.DateTimeStyles.None, out result))
                    { 
                        _event.DateTime = result.ToString("dd-MM-yyyy HH:mm");
                    }
                }
            }
        }

        yield return StartCoroutine(InitLectures());
    }

    private IEnumerator InitLectures()
    {
        string pathResource = "lectures";
        serverUrl = $"http://{hostName}:{port}/{metaverseName}/{pathResource}";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

        responseData = httpRequest.ResponseData;

        if (responseData != null)
        {
            Debug.Log($"Lectures: {responseData}");

            var lectures = JsonConvert.DeserializeObject<List<LectureSerializable>>(responseData);

            foreach (var lec in lectures)
            {
                var l = lec.location;

                var gameObject = coordinatesManager.GetGameObject(l.floorNumber, l.xPosition, l.zPosition);
                if (gameObject != null)
                {
                    var lecture = gameObject.AddComponent<Lecture>();
                    lecture.Name = lec.name;
                    lecture.ClassroomNumber = lec.classroom.number;
                    lecture.LecturerFirstName = lec.lecturer.firstName;
                    lecture.LecturerLastName = lec.lecturer.lastName;

                    string format = "yyyy-MM-ddTHH:mm:ss";
                    DateTime result;
                    if (DateTime.TryParseExact(lec.dateTime, format, null, System.Globalization.DateTimeStyles.None, out result))
                    {
                        lecture.DateTime = result.ToString("dd-MM-yyyy HH:mm");
                    }
                }
            }
        }
    }
    */
}
