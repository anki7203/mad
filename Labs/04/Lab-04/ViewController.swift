//
//  ViewController.swift
//  Lab-04
//
//  Created by Andrew Kiproff on 10/6/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var sliderLabel: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var slider: UISlider!
    
    var delta = CGPointMake(12,4) //How far to move x and y
    var ballRadius = CGFloat() //radius of image
    var timer = NSTimer()
    var translation = CGPointMake(0.0, 0.0)
    var angle:CGFloat = 0.0
    
    override func viewDidLoad() {
        ballRadius = (imageView.frame.size.width/2)-36
        changeSliderValue()
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    
    @IBAction func changeSliderValue() {
        sliderLabel.text=String(format: "%.2f", slider.value)
        timer = NSTimer.scheduledTimerWithTimeInterval(Double(slider.value), target: self, selector: "moveImage", userInfo: nil, repeats: true)
    }
    

    func sliderMoved(sender: UISlider) {
        timer.invalidate()
        changeSliderValue()
    }
    
    func moveImage(){
        let duration = Double(slider.value)
        UIView.beginAnimations("smile", context: nil)
        
        
        
        UIView.animateWithDuration(duration, animations:
            {

                self.imageView.center=CGPointMake(self.imageView.center.x +
                    self.delta.x, self.imageView.center.y + self.delta.y)
                
                var trans1 = CGAffineTransformMakeTranslation(self.translation.x, self.translation.y)
                
                var trans2 = CGAffineTransformMakeRotation(self.angle)
                
                self.imageView.transform = CGAffineTransformConcat(trans1, trans2)
                
                //increase values
                self.angle += 0.06
                
                if self.angle > CGFloat(2*M_PI){
                    self.angle = 0;
                }
                
                self.translation.x += self.delta.x
                
                self.translation.y += self.delta.y
                
            })
        
        
        UIView.commitAnimations()
        
        if imageView.center.x + translation.x > view.bounds.size.width - ballRadius || imageView.center.x + translation.x < ballRadius{
            delta.x = -delta.x
        }
        if imageView.center.y + translation.y > view.bounds.size.height - ballRadius || imageView.center.y + translation.y < ballRadius{
            delta.y = -delta.y
        }
    }

}

