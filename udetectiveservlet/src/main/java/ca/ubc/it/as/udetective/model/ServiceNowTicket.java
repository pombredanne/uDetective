package ca.ubc.it.as.udetective.model;

import java.sql.Date;

public class ServiceNowTicket extends AbstractModel
{
    private static final long serialVersionUID = 1L;
    
    private String sys_id;
    private String number;
    private String short_description;
    private String description;
    private Date   date;
    private String ipAddress;
    
    public ServiceNowTicket(String sys_id, String number, String short_description, String description) {
        this.sys_id      = sys_id;
        this.number = number;
        this.short_description = short_description;
        this.description    = description;
    }

    public void setSysId(String sys_id) {
        this.sys_id = sys_id;
    }
    
    public String getSysId() {
        return this.sys_id;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setShortDescription(String short_description) {
        this.short_description = short_description;
    }

    public String getShortDescription() {
        return this.short_description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public String getIpAddress() {
        return this.ipAddress;
    }
    
    @Override
    public String toString() {
        return (this.getSysId() + ":" + this.getNumber());
    }
}