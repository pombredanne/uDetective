package as.it.ubc.ca.udetective.model;

import java.sql.Date;

public class ServiceNowTicket extends AbstractModel
{
    private static final long serialVersionUID = 1L;
    
    private int    id;
    private String subject;
    private String body;
    private Date   date;
    
    public ServiceNowTicket() {
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
}