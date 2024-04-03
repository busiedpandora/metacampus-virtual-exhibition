using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EyeTracker : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        Ray ray = new Ray(transform.position, transform.forward);
        RaycastHit hit;

        if(Physics.Raycast(ray, out hit, 2.0f))
        {
            var eyeInteractive = hit.collider.gameObject.GetComponent<EyeInteractive>();
            if(eyeInteractive != null)
            {
                eyeInteractive.EyeInteract();
            }
        }
    }
}
