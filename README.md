# liveasyAssignment

/**
// I used localHot(127.0.0.1)
// 8090 is server post
// Here, I used loads for identify loadController
*/

// 1. post "/load"	Api for this is http://127.0.0.1:8090/loads/load 
(POST)	http://127.0.0.1:8090/loads/load
Request
{
    "loadingPoint": "delhi",
    "unloadingPoint": "jaipur",
    "productType": "chemicals",
    "truckType": "canter",
    "noOfTrucks": 1,
    "weight": 100,
    "comment": "I am rahul"
}

Here, I assumed  date and  shipperId will generate  by Application

//2. GET “/load” 	Api for this is http://127.0.0.1:8090/loads/load?shipperId = (any shipperId)  
(GET)	http://127.0.0.1:8090/loads/load?shipperId = shipper:23ad0453-23ac-4422-a1ad-d879a875ba0a

 // 1.GET “load/{loadId}”     Api for this is http://127.0.0.1:8090/loads/load/{loadId} (
(GET)	http://127.0.0.1:8090/loads/load/1

//2.PUT “load/{loadId}”	Api for this is http://127.0.0.1:8090/loads/load/{loadId}
(PUT)	http://127.0.0.1:8090/loads/load/1

//3.DELETE “load/{loadId}”     Api for this is http://127.0.0.1:8090/loads/load/{loadId} (DELETE Request)
(DELETE)    http://127.0.0.1:8090/loads/load/1
