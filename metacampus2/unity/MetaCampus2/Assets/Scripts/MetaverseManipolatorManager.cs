using MixedReality.Toolkit;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class MetaverseManipolatorManager : MonoBehaviour
{
    [SerializeField] private GameObject manipolatorStateButton;

    private GameObject alignmentObject;
    private TextMeshPro text;
    private bool alignmentEnabled = false;
 
    // Start is called before the first frame update
    void Start()
    {
        alignmentObject = transform.Find("Sphere").gameObject;

        manipolatorStateButton.SetActive(true);

        text = manipolatorStateButton.transform.Find("CompressableButtonVisuals/IconAndText/TextMeshPro").gameObject.GetComponent<TextMeshPro>();

        if(alignmentEnabled)
        {
            EnableAlignment();
            text.text = "Disable alignment";
        }
        else
        {
            DisableAlignment();
            text.text = "Enable alignment";
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void Clicked()
    {
        if(alignmentEnabled)
        {
            alignmentEnabled = false;
            DisableAlignment();
            text.text = "Enable alignment";
        }
        else
        {
            alignmentEnabled = true;
            EnableAlignment();
            text.text = "Disable alignment";
        }
    }

    private void EnableAlignment()
    {
        alignmentObject.SetActive(true);
    }

    private void DisableAlignment()
    {
        alignmentObject.SetActive(false);
    }
}
