//
//  ViewController.swift
//  Lab8
//
//  Created by Andrew Kiproff on 10/29/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

var userChoice = ""

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func answer1(sender: UIButton) {
        userChoice = "42"
    }

    @IBAction func answer2(sender: UIButton) {
        userChoice = "red"
    }
}

