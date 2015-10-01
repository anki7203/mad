//
//  View3Controller.swift
//  Project 1
//
//  Created by Andrew Kiproff on 9/29/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

class View3Controller: UIViewController {
    
    @IBOutlet weak var scoreLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        timer.invalidate()
        scoreLabel.text = String(time) + " ms"
        time = 0
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    
}
