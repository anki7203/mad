//
//  ViewController.swift
//  Kiproff-Lab1
//
//  Created by Andrew Kiproff on 9/1/15.
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

    @IBOutlet weak var faceImg: UIImageView!
    @IBOutlet weak var msgLabel: UILabel!
    @IBAction func chooseEmo(sender: UIButton) {
        if sender.tag == 1{
            faceImg.image = UIImage(named: "happy")
            msgLabel.text = "Awesome!";
        }
        else if sender.tag == 2{
            faceImg.image = UIImage(named: "sad")
            msgLabel.text = "Oh No!";
        }
    }

}

