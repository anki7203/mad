//
//  ViewController.swift
//  Project2
//
//  Created by Andrew Kiproff on 10/25/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController, AVAudioPlayerDelegate, AVAudioRecorderDelegate{
    
    var audioPlayer: AVAudioPlayer?
    var audioRecorder: AVAudioRecorder?
    let fileName = "test.mp3"

    override func viewDidLoad() {
        
        playAudio("select")

        let dirPath = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask, true)
        let docDir = dirPath[0] as! String
        let audioFilePath = docDir.stringByAppendingPathComponent(fileName)
        let audioFileURL = NSURL(fileURLWithPath: audioFilePath)        
        
        super.viewDidLoad()

    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    func playAudio(fileName: String) {
        var filePath = "audio/"+fileName
        let audioFilePath = NSBundle.mainBundle().pathForResource(fileName, ofType: "mp3")
        let fileURL = NSURL(fileURLWithPath: audioFilePath!)
        audioPlayer = AVAudioPlayer(contentsOfURL: fileURL, error: nil)
        if audioPlayer != nil{
            audioPlayer!.play()
        }
    }
    
    
    /*

        SOUND BUTTONS
        ----------------------------------------
        Sounds sourced from the game 'Destiny' 
        by Bungie, captured and curated via 
        www.Reddit.com/r/destinythegame by 
        Connor Leo (u/Famous_Last_Turds).

    */
    
    //characters
    
    @IBAction func speakerBtn(sender: AnyObject) { playAudio("speaker") }
    
    @IBAction func ghostBtn(sender: AnyObject) { playAudio("ghost") }
    
    @IBAction func shaxxBtn(sender: AnyObject) { playAudio("shaxx") }
    
    @IBAction func claydeBtn(sender: AnyObject) { playAudio("cayde") }
    
    
    //weapons
    
    @IBAction func chaperoneBtn(sender: UIButton) { playAudio("chaperone") }
    
    @IBAction func gallyBtn(sender: AnyObject) { playAudio("gally") }
    
    @IBAction func telestoBtn(sender: AnyObject) { playAudio("telesto") }
    
    @IBAction func icebreakerBtn(sender: AnyObject) { playAudio("ice-breaker") }
    
    
    //classes
    
    @IBAction func warlockBtn(sender: AnyObject) { playAudio("warlock") }
    
    @IBAction func titanBtn(sender: AnyObject) { playAudio("titan") }
    
    @IBAction func hunterBtn(sender: AnyObject) { playAudio("hunter") }
    
    
    //ambient
    
    @IBAction func directoryBtn(sender: AnyObject) { playAudio("directory") }

    @IBAction func exoticdropBtn(sender: AnyObject) { playAudio("exotic-drop") }

    @IBAction func orbitBtn(sender: AnyObject) { playAudio("orbit") }
    
    @IBAction func patrolBtn(sender: AnyObject) { playAudio("patrol") }
}

