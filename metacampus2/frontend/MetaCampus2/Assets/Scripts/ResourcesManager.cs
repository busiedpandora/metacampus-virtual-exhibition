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

public class ResourcesManager : MonoBehaviour
{
    [SerializeField] private GameObject textPanelObject;
    [SerializeField] private GameObject singleDisplayPanelObject;
    [SerializeField] private GameObject sixPackDiagonalDisplayPanelObject;
    [SerializeField] private GameObject sixPackCircularDisplayPanelObject;

    private const string metaverseName = "Campus Est Supsi";

    private const string hostName = "www.localhost";
    private const string port = "8080";
    private const string baseUrlPath = "spaces";
    private string serverUrl = "";

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
        StartCoroutine(InitTexts());
    }

    private IEnumerator InitTexts()
    {
        string pathResource = "text-panels";
        serverUrl = $"http://{hostName}:{port}/{baseUrlPath}/{metaverseName}/{pathResource}";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

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

                        var frontTextName = textPanelInstance.transform.Find("Board/FrontTextName").gameObject;
                        var frontText = textPanelInstance.transform.Find("Board/FrontText").gameObject;
                        var backTextName = textPanelInstance.transform.Find("Board/BackTextName").gameObject;
                        var backText = textPanelInstance.transform.Find("Board/BackText").gameObject;

                        var text = textPanel.text;
                        frontTextName.GetComponent<TextMeshPro>().text = text.name;
                        frontText.GetComponent<TextMeshPro>().text = text.value;
                        backTextName.GetComponent<TextMeshPro>().text = text.name;
                        backText.GetComponent<TextMeshPro>().text = text.value;
                    }
                }
            }
        }

        yield return StartCoroutine(InitImages());
    }

    private IEnumerator InitImages()
    {
        string pathResource = "display-panels";
        serverUrl = $"http://{hostName}:{port}/{baseUrlPath}/{metaverseName}/{pathResource}";

        string responseData = null;

        yield return StartCoroutine(httpRequest.GetDataFromServer(serverUrl, ""));

        responseData = httpRequest.ResponseData;

        if(responseData != null )
        {
            Debug.Log($"Display panels: {responseData}");

            var displayPanels = JsonConvert.DeserializeObject<List<DisplayPanelSerializable>>(responseData);

            foreach(var displayPanel in displayPanels)
            {
                if (displayPanel.images != null && displayPanel.images.Count > 0)
                {
                    var coord = displayPanel.coordinates;
                    var coordObject = coordinatesManager.GetGameObject(coord.x, coord.y, coord.z);

                    if (coordObject != null)
                    {
                        var position = new Vector3(coord.x, coord.y, coord.z);
                        int imagesCount = displayPanel.images.Count;

                        if (displayPanel.type == "SINGLE")
                        {
                            var singleDisplalPanelInstance =
                                Instantiate(singleDisplayPanelObject, position, Quaternion.identity);
                            singleDisplalPanelInstance.transform.parent = coordObject.transform;

                            for (int i = 0; i < imagesCount; i++)
                            {
                                string imagePath = displayPanel.images[i].path;
                                
                                if (System.IO.File.Exists(imagePath))
                                {
                                    byte[] imageData = System.IO.File.ReadAllBytes(displayPanel.images[i].path);

                                    Texture2D texture = new Texture2D(2, 2);
                                    texture.LoadImage(imageData);
                                    var image = singleDisplalPanelInstance.transform.Find($"Board/Canvas/Image{i + 1}");
                                    image.GetComponent<RawImage>().texture = texture;
                                }
                            }
                        }

                        else if (displayPanel.type == "SIX_PACK_DIAGONAL")
                        {
                            var sixPackDiagonalDisplalPanelInstance =
                                Instantiate(sixPackDiagonalDisplayPanelObject, position, Quaternion.identity);
                            sixPackDiagonalDisplalPanelInstance.transform.parent = coordObject.transform;

                            for (int i = 0; i < imagesCount; i++)
                            {
                                string imagePath = displayPanel.images[i].path;

                                if (System.IO.File.Exists(imagePath))
                                {
                                    byte[] imageData = System.IO.File.ReadAllBytes(displayPanel.images[i].path);

                                    Texture2D texture = new Texture2D(2, 2);
                                    texture.LoadImage(imageData);
                                    var image = sixPackDiagonalDisplalPanelInstance.transform.Find($"Panel/Board/Canvas/Image{i + 1}");
                                    image.GetComponent<RawImage>().texture = texture;
                                }
                            }
                        }

                        else if (displayPanel.type == "SIX_PACK_CIRCULAR")
                        {
                            var sixPackCircularDisplalPanelInstance =
                                Instantiate(sixPackCircularDisplayPanelObject, position, Quaternion.identity);
                            sixPackCircularDisplalPanelInstance.transform.parent = coordObject.transform;

                            for (int i = 0; i < imagesCount; i++)
                            {
                                string imagePath = displayPanel.images[i].path;

                                if (System.IO.File.Exists(imagePath))
                                {
                                    byte[] imageData = System.IO.File.ReadAllBytes(displayPanel.images[i].path);

                                    Texture2D texture = new Texture2D(2, 2);
                                    texture.LoadImage(imageData);
                                    var image = sixPackCircularDisplalPanelInstance.transform.Find($"Panel/Board/Canvas/Image{i + 1}");
                                    image.GetComponent<RawImage>().texture = texture;
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
