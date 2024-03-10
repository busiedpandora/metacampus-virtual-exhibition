using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class PersonSerializable
{
    public string firstName;
    public string lastName;
    public string role;
    public string cellphone;
    public string officePhone;
    public OfficeSerializable office;
    public LocationSerializable location;
}
