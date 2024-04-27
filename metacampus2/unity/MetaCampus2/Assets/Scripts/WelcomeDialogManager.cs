using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class WelcomeDialogManager : MonoBehaviour
{
    [SerializeField] private GameObject titleText;
    [SerializeField] private GameObject descriptionText;


    // Start is called before the first frame update
    void Start()
    {
        string title = $"Welcome to {MetaverseSelectionManager.metaverseNameSelected}!";
        titleText.GetComponent<TextMeshPro>().text = title;

        string description = "Enjoy an exihibition about energy";
        descriptionText.GetComponent<TextMeshPro>().text = description;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void Hide()
    {
        this.gameObject.SetActive(false);
    }
}
