//
//  ViewController.swift
//  Kiproff-Lab3
//
//  Created by Andrew Kiproff on 9/24/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBOutlet weak var userWeightInput: UITextField!

    @IBOutlet weak var moonOutput: UILabel!
    @IBOutlet weak var mercOutput: UILabel!
    @IBOutlet weak var venuOutput: UILabel!
    @IBOutlet weak var marsOutput: UILabel!
    @IBOutlet weak var jupiOutput: UILabel!
    @IBOutlet weak var satuOutput: UILabel!
    @IBOutlet weak var uranOutput: UILabel!
    @IBOutlet weak var neptOutput: UILabel!
    @IBOutlet weak var plutOutput: UILabel!
    
    @IBAction func calcBtn(sender: UIButton) {
        
        var userWeight = Double(userWeightInput.text.toInt()!)
        
        var moonWeight = userWeight * 0.17 //Gravtron Factors
        var mercWeight = userWeight * 0.38
        var venuWeight = userWeight * 0.91
        var marsWeight = userWeight * 0.38
        var jupiWeight = userWeight * 2.54
        var satuWeight = userWeight * 1.08
        var uranWeight = userWeight * 0.91
        var neptWeight = userWeight * 1.19
        var plutWeight = userWeight * 0.06
        
        moonOutput.text = moonWeight.description
        mercOutput.text = mercWeight.description
        venuOutput.text = venuWeight.description
        marsOutput.text = marsWeight.description
        jupiOutput.text = jupiWeight.description
        satuOutput.text = satuWeight.description
        uranOutput.text = uranWeight.description
        neptOutput.text = neptWeight.description
        plutOutput.text = plutWeight.description
        
    }


}

