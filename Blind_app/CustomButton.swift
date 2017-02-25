//
//  CustomButton.swift
//  Blind_app
//
//  Created by Soya Diaoune on 2/25/17.
//  Copyright © 2017 Soya. All rights reserved.
//

import UIKit

class CustomButton: UIButton {

    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.clipsToBounds = true
        self.backgroundColor = UIColor.red
        
        self.layer.shadowColor = UIColor.black.cgColor
        self.layer.shadowColor = UIColor(red: 1, green: 0, blue: 0, alpha: 0.4).cgColor
        self.layer.shadowOffset = CGSize(width: 3, height: 3)
        self.layer.shadowOpacity = 1.0
        self.layer.shadowRadius = 2.0
        self.layer.masksToBounds = false
        
        self.layer.cornerRadius = 20
        
        self.setTitleColor(.white, for: .normal)
        
    }

}
