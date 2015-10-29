//
//  ViewController.swift
//  Midterm
//
//  Created by Andrew Kiproff on 10/29/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

class ViewController: UIViewController,UITextFieldDelegate {
    
    @IBOutlet weak var milesInput: UITextField!
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var gasLabel: UILabel!
    @IBOutlet weak var durationSwitch: UISwitch!
    @IBOutlet weak var sliderLabel: UILabel!
    
    
    override func viewDidLoad() {
        
        milesInput.delegate = self
        
        func textFieldShouldReturn(textField:UITextField!) -> Bool{
        textField.resignFirstResponder()
        return true;
        }
        
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func gasSlider(sender: UISlider) {
        var gas = roundf(sender.value * 10) / 10
        sliderLabel.text = "Gas in tank "+gas.description+" gallons"
    }


    @IBAction func calculateCommute(sender: UIButton) {
        
        
        //Alert if distance acceeds 50 miles
        if milesInput.text.toInt()! > 50{
            var alert = UIAlertController(title: "Exceeded Distance", message: "Your commute distance exceeds 50 miles!", preferredStyle: UIAlertControllerStyle.Alert)
            alert.addAction(UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Default, handler: nil))
            self.presentViewController(alert, animated: true, completion: nil)
        }else{
        
            //calculate "Total commute time"
            var miles = Double(milesInput.text.toInt()!)
            var commuteTime = (miles/20)*60;
        
            //calculate "Gas to puchase"
            var gasToPurchase = (miles/24)
        
            //If "show Monthly is switched on, multiply by 20
            if durationSwitch.on{
                commuteTime = commuteTime*20
                gasToPurchase = gasToPurchase*20
            }
        
            // update labels
            timeLabel.text = commuteTime.description+" mins"
            gasLabel.text = gasToPurchase.description+" gallons"
            
        }
        
    }
    
    @IBOutlet weak var testLabel: UILabel!
    
    // changing image view
    @IBOutlet weak var vehicleImg: UIImageView!
    @IBAction func vehicleSelect(sender: UISegmentedControl) {
        
        var vehicle = sender.titleForSegmentAtIndex(sender.selectedSegmentIndex)
        testLabel.text = vehicle
        vehicleImg.image = UIImage(named: vehicle!)
        
    }
    
}

