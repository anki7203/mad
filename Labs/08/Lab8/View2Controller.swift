//
//  View2Controller.swift
//  Lab8
//
//  Created by Andrew Kiproff on 10/29/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

class View2Controller: UIViewController {
    
    var responseStr = ""
    
    @IBOutlet weak var answerLabel: UILabel!

    override func viewDidLoad() {
        if userChoice == "42"{
            responseStr = "Yes, You answered 42! That is the correct answer!!!"
        }else if userChoice == "red"{
            responseStr = "Uh Oh!, You answered red?! That is the WRONG answer!!!"
        }else{
            responseStr = "Um something broke, you win... please leave now!"
        }
        answerLabel.text = responseStr
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func replayBtn(sender: AnyObject) {
        userChoice = ""
    }
}

