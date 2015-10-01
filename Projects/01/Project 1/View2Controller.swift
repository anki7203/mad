//
//  View2Controller.swift
//  Project 1
//
//  Created by Andrew Kiproff on 9/29/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

var timer = NSTimer()
var time = 0

class View2Controller: UIViewController {
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        timer = NSTimer.scheduledTimerWithTimeInterval(0.001, target: self, selector: "increaseTime", userInfo: nil, repeats: true)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func increaseTime(){
        time++
    }
    
    
    
}
