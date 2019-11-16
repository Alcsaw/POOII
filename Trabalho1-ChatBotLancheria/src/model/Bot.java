package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class Bot {
    private final String token; //token do bot, não vai ser mudado
    
    //construtor
     public Bot(String token){ 
        this.token = token;
    }
     
    public void sendMessage(String userID, String message){
        String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", token, userID, this.encode(message));
        try {
            URL telegram = new URL(url); //instancia uma url
            URLConnection con = telegram.openConnection(); //abre a URL que é a mensagem a ser enviada ao usuário
            BufferedReader in = new BufferedReader (new InputStreamReader(con.getInputStream()));
        } 
        catch (MalformedURLException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    public String receiveMessage(int updateID){
        String url = String.format("https://api.telegram.org/bot%s/getUpdates?offset=%d", token, updateID);
        String ret = ""; //declara variável que é a mensagem inteira recebida em JSON
        try{
            URL telegram = new URL(url); //instancia uma url
            URLConnection con = telegram.openConnection(); //abre a URL que é destinada à mensagem do usuário
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); //lê o que tem nessa URL

            String line;
            while ((line = in.readLine()) != null)
               ret += line; //vai concatenando a(s) linha(s)
         }
        catch (MalformedURLException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
           }
        catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public ArrayList<TelegramMessage> parseMessage(String message){
        ArrayList<TelegramMessage> ret = new ArrayList<TelegramMessage>();
        JSONObject jsonObj = new JSONObject(message);
        JSONArray resultArray = new JSONArray(jsonObj.get("result").toString());
        for(int i = 0; i < resultArray.length(); i++) {
            TelegramMessage telegramMsg = new TelegramMessage();
            
            JSONObject curResultObj = resultArray.getJSONObject(i);
            JSONObject curMsgObj = curResultObj.getJSONObject("message");
            JSONObject curMsgFrom = curMsgObj.getJSONObject("from");
            
            telegramMsg.setSenderId(curMsgFrom.get("id").toString());
            telegramMsg.setUpdateId((int)curResultObj.get("update_id"));
            telegramMsg.setSenderFirstName(curMsgFrom.get("first_name").toString());
            telegramMsg.setSenderLastName(curMsgFrom.get("last_name").toString());
            telegramMsg.setMessageId((int)curMsgObj.get("message_id"));
            telegramMsg.setText(curMsgObj.get("text").toString());
            
            ret.add(telegramMsg);
        }
        return ret;
    }
        
    public String encode(String message){
        try{
            return URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
        } 
        catch (UnsupportedEncodingException ex){
            throw new RuntimeException("ERRO NA CODIFICAÇÃO: "+ex.getMessage());
        }
    }
}
