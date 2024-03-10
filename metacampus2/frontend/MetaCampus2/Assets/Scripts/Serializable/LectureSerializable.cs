using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class LectureSerializable
{
    public string name;
    public string dateTime;
    public ClassroomSerializable classroom;
    public PersonSerializable lecturer;
    public LocationSerializable location;
}
