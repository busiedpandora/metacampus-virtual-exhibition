using MixedReality.Toolkit.UX;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DebugLog : MonoBehaviour
{
    [SerializeField] private DialogPool dialogPool;
    public static DebugLog instance;

    private void Awake()
    {
        instance = this;
    }

    public void Log(string title, string message)
    {
        dialogPool.Get().SetHeader(title).SetBody(message).SetNeutral("OK", (args) =>
        {

        }).Show();
    }


    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
