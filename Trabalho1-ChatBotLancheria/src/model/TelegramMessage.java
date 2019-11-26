/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author erico
 */
public class TelegramMessage {
    private int updateId;
    private int messageId;
    private String senderFirstName;
    private String senderId;
    private String text;

    public TelegramMessage() {
    }

    public TelegramMessage(int updateId, int messageId, String senderFirstName, String senderId, String text) {
        this.updateId = updateId;
        this.messageId = messageId;
        this.senderFirstName = senderFirstName;
        this.senderId = senderId;
        this.text = text;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
