using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Newtonsoft.Json;
using Unity.XR.CoreUtils;
using System;
using UnityEngine.Windows;

public class ResourcesManager : MonoBehaviour
{
    private const string hostName = "www.localhost";
    private const string port = "8080";

    private const string metaverseName = "Campus Est SUPSI";
    
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
        StartCoroutine(InitClassrooms());
    }

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
}
