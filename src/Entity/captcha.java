/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Random;

/**
 *
 * @author DELL
 */
public class captcha {
    public String getCaptcha(){
        char data[]={
            'a','b','c','d','e','f','g','h','i','9','8','7','6'
        };
        char index[] = new char[6];
        
        Random r = new Random();
        
        int i = 0;
        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i]=data[ran];
        }
        return new String(index);
    }
    
}
