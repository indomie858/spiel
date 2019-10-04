/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp380;

import java.util.ArrayList;

/**
 *
 * @author gaven grantz
 */
public class StringStorage_GG {
    
    private ArrayList<String> strArr;
    
    public StringStorage_GG(){
        strArr = new ArrayList<String>();
    }

    public ArrayList getStrArr() {
        return strArr;
    }

    public void setStrArr(ArrayList strArr) {
        this.strArr = strArr;
    }
    
    public void addToStrArr(String message){
        strArr.add(message);
    }
    
    public void printStrArr(){
        for (int i = 0; i < strArr.size(); i++){
            System.out.println(strArr.get(i));
        }
    }

    
    
    
    
}
