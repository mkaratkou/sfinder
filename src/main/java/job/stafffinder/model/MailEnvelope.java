package job.stafffinder.model;

import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MailEnvelope {
    private String subject;
    private List<String> toList = Collections.emptyList();
    private String from;
    private List<String> ccList = Collections.emptyList();

    /** Sets the subject of email. */
    @Required
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /** Returns the subject of email. */
    public String getSubject() {
        return subject;
    }

    /** Sets the list of recipients email. */
    @Required
    public void setToList(List<String> to) {
        this.toList = new ArrayList<String>(to);
    }
    /** Returns the list of recipients email. */
    public List<String> getToList() {
        return toList;
    }

    /**
     * Returns the owner's email address. <br/>
     * Optional, may return null. <br/>
     * In this case the sender is supposed to insert the default 'from' address. <br/>
     */
    public String getFrom() {
        return from;
    }
    /** Sets the owner's email address. */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the copy list of recipients email.
     * Optional parameter, empty by default.
     */
    public List<String> getCcList() {
        return Collections.unmodifiableList(ccList);
    }
    /** Sets the copy list of recipients email. */
    public void setCcList(List<String> ccList) {
        this.ccList = new ArrayList<String>(ccList);
    }
}
