//
//  ViewController.swift
//  Location
//
//  Created by Andrew Kiproff on 10/8/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class ViewController: UIViewController, CLLocationManagerDelegate {

    @IBOutlet weak var mapView: MKMapView!
    var locationManager = CLLocationManager()
    let annotation = MKPointAnnotation()
    
    
    func locationManager(manager: CLLocationManager!, didUpdateLocations locations: [AnyObject]!) {
        let span = MKCoordinateSpanMake(0.05,0.05)
        let region = MKCoordinateRegionMake(manager.location.coordinate, span)
        mapView.setRegion(region, animated: true)
        
        annotation.coordinate = locationManager.location.coordinate
        annotation.title = "You are Here"
        annotation.subtitle = "Lat:\(manager.location.coordinate.latitude), Lon:\(manager.location.coordinate.longitude)"
        mapView.addAnnotation(annotation)
        
    }
    
    func locationManager(manager: CLLocationManager!, didChangeAuthorizationStatus status: CLAuthorizationStatus) {
        if status == CLAuthorizationStatus.AuthorizedWhenInUse{
            locationManager.startUpdatingLocation()
        }
    }
    
    func locationManager(manager: CLLocationManager!, didFailWithError error: NSError!) {
        var errorType = String()
        if let clError = CLError(rawValue: error.code){
            if clError == .Denied {
                errorType="Location Services are required to use this map! Settings > Privacy > Location"
                let alert=UIAlertController(title: "Error", message:
                    errorType, preferredStyle: UIAlertControllerStyle.Alert)
                let okAction:UIAlertAction=UIAlertAction(title: "OK",
                    style:UIAlertActionStyle.Default, handler: nil)
                alert.addAction(okAction)
                presentViewController(alert, animated: true, completion:
                    nil)
            }
        }
    }
    
    
    override func viewDidLoad() {
        mapView.mapType = MKMapType.Hybrid
        var status:CLAuthorizationStatus = CLLocationManager.authorizationStatus()
        if status == CLAuthorizationStatus.NotDetermined{
            locationManager.requestWhenInUseAuthorization()
        }
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.distanceFilter - kCLDistanceFilterNone
        mapView.showsUserLocation = true
        
        
        /*let location = CLLocationCoordinate2D(latitude: 40.74836, longitude: -73.984607)
        let span = MKCoordinateSpanMake(0.05,0.05)
        let region = MKCoordinateRegionMake(location, span)
        mapView.setRegion(region, animated: true)
        let annotation = MKPointAnnotation()
        annotation.coordinate = location
        annotation.title = "Empire State Building"
        annotation.subtitle = "New York"
        mapView.addAnnotation(annotation)*/
        
        
        
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

