using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CoordinatesManager : MonoBehaviour
{
    [SerializeField] private GameObject floor0Coordinate;
    [SerializeField] private GameObject floor1Coordinate;
    [SerializeField] private GameObject floor2Coordinate;

    private const float floor0YCoordinate = 1.5f;
    private const float floor1YCoordinate = 4.5f;
    private const float floor2YCoordinate = 7.5f;
    private const float floor3YCoordinate = 10.5f;

    private const int minFloorNumber = 0;
    private const int maxFloorNumber = 2;

    private const int minXCoordinate = -10;
    private const int maxXCoordinate = 10;

    private const int minZCoordinate = 5;
    private const int maxZCoordinate = 10;

    //1° index: floor number, 2° index: x position, 3° index: z position
    private GameObject[][][] coordinates;

    private GameObject resourcesInitializer;



    // Start is called before the first frame update
    void Start()
    {
        InitCoordinates();

        resourcesInitializer = GameObject.Find("ResourcesInitializer");
        ResourcesManager resourcesManager = resourcesInitializer.GetComponent<ResourcesManager>();
        resourcesManager.StartInitResources();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void InitCoordinates()
    {
        //array dimensions
        int floorCount = maxFloorNumber - minFloorNumber + 1;
        int xSize = maxXCoordinate - minXCoordinate + 1;
        int zSize = maxZCoordinate - minZCoordinate + 1;

        coordinates = new GameObject[floorCount][][];

        for(int floorNumber = 0;  floorNumber < floorCount; floorNumber++)
        {
            coordinates[floorNumber] = new GameObject[xSize][];

            float floorYCoordinate = GetFloorYCoordinate(floorNumber);

            GameObject floorObject = GameObject.Find($"/Campus/{minFloorNumber + floorNumber}");

            for (int x = 0; x < xSize; x++)
            {
                coordinates[floorNumber][x] = new GameObject[zSize];

                for(int z = 0;  z < zSize; z++)
                {
                    Vector3 position = new Vector3(minXCoordinate + x, floorYCoordinate, minZCoordinate + z);
                    GameObject coordinate = Instantiate(floor0Coordinate, position, Quaternion.identity);

                    //Set floorObject as parent
                    coordinate.transform.parent = floorObject.transform;

                    coordinates[floorNumber][x][z] = coordinate;
                }
            }
        }
    }

    private float GetFloorYCoordinate(int floorNumber)
    {
        switch (floorNumber)
        {
            case 0:
                return floor0YCoordinate;
            case 1:
                return floor1YCoordinate;
            case 2:
                return floor2YCoordinate;
            case 3:
                return floor3YCoordinate;
            default: 
                return 0f;
        }
    }

    public GameObject GetGameObject(int floorNumber, int xPosition, int zPosition)
    {
        int floorIndex = floorNumber - minFloorNumber;
        int xIndex = xPosition - minXCoordinate;
        int zIndex = zPosition - minZCoordinate;

        if (floorIndex >= 0 && floorIndex < coordinates.Length && 
            xIndex >= 0 && xIndex < coordinates[0].Length &&
            zIndex >= 0 && zIndex < coordinates[0][0].Length)
        {
            return coordinates[floorIndex][xIndex][zIndex];
        }
        else
        {
            Debug.Log("getGameObject: Out of bounds");
            return null;
        }
    }
}
