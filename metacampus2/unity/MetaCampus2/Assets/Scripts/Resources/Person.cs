using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Person : MonoBehaviour
{
    [SerializeField] private string _firstName;
    [SerializeField] private string _lastName;
    [SerializeField] private string _role;
    [SerializeField] private string _cellphone;
    [SerializeField] private string _officePhone;
    [SerializeField] private string _officeNumber;

    public string FirstName { get => _firstName; set => _firstName = value; }
    public string LastName { get => _lastName; set => _lastName = value; }
    public string Role { get => _role; set => _role = value; }
    public string Cellphone { get => _cellphone; set => _cellphone = value; }
    public string OfficePhone { get => _officePhone; set => _officePhone = value; }
    public string OfficeNumber { get => _officeNumber; set => _officeNumber = value; }
}
