package as.it.ubc.ca.udetective.dataretriever;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * This is just a test
 * 
 * @author Armenak Grigoryan
 */
public class JSONMassage {
    
    public static void main(String[] args) {
        String message = "{ \"result\": [{ \"sys_id\": \"05005f116f19324079ed618cbb3ee4e7\"},{\"sys_id\": \"0be5c7dd6fd5324079ed618cbb3ee405\"}]}";
        
        message = message.substring(1, message.length()-1);
        int index = message.indexOf("\"result\":");
        message = message.substring(index+9);
        System.out.println(message);
    }
}
