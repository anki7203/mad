//
//  ViewController.swift
//  Recording
//
//  Created by Andrew Kiproff on 10/15/15.
//  Copyright (c) 2015 Andrew Kiproff. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController, AVAudioPlayerDelegate, AVAudioRecorderDelegate {

    @IBOutlet weak var recordButton: UIButton!
    @IBOutlet weak var stopButton: UIButton!
    @IBOutlet weak var playButton: UIButton!
    
    var audioPlayer: AVAudioPlayer?
    var audioRecorder: AVAudioRecorder?
    let fileName = "audio.caf"
    
    
    
    
    override func viewDidLoad() {
        playButton.enabled = false;
        stopButton.enabled = false;
        
        let dirPath = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask, true)
        let docDir = dirPath[0] as! String
        let audioFilePath = docDir.stringByAppendingPathComponent(fileName)
        let audioFileURL = NSURL(fileURLWithPath: audioFilePath)
        
        let recordSettings = [AVEncoderAudioQualityKey: AVAudioQuality.Min.rawValue, AVEncoderBitRateKey: 16, AVNumberOfChannelsKey: 2, AVSampleRateKey: 44100.0]
        var error : NSError?
        
        audioRecorder = AVAudioRecorder(URL: audioFileURL, settings:
            recordSettings as [NSObject : AnyObject], error: &error)
        
        if let err = error {
            println("AVAudioRecorder error: \(err.localizedDescription)")
        } else {
            audioRecorder?.delegate = self
            audioRecorder?.prepareToRecord()
        }
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func recordAudio(sender: UIButton) {
        if audioRecorder?.recording == false{
            playButton.enabled = false
            stopButton.enabled = true
            audioRecorder?.record()
        }
    }
    
    
    @IBAction func stopAudio(sender: UIButton) {
        stopButton.enabled = false
        playButton.enabled = true
        recordButton.enabled = true

        if audioRecorder?.recording == true {
            audioRecorder?.stop()
        } else {
            audioPlayer?.stop()
        }
    }
    
    
    @IBAction func playAudio(sender: UIButton) {
        
        if audioRecorder?.recording == false{
            stopButton.enabled = true
            recordButton.enabled = false
            var error: NSError?
            
            audioPlayer=AVAudioPlayer(contentsOfURL:audioRecorder?.url, error: &error)
            
            if let err = error {
                println("AVAudioPlayer error: \(err.localizedDescription)")
            } else {
                audioPlayer?.delegate=self
                audioPlayer?.play()
            }
        }
    }
    
    
    func audioPlayerDidFinishPlaying(player: AVAudioPlayer!, successfully
        flag: Bool) {
            recordButton.enabled = true
            stopButton.enabled = false
    }
    
    


}

