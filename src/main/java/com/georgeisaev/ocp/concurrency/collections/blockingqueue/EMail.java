package com.georgeisaev.ocp.concurrency.collections.blockingqueue;

import org.apache.log4j.Logger;

public class EMail implements Mail {

    private static final Logger logger = Logger.getLogger(EMail.class);
    private final String text;
    private final String from;
    private final String to;
    private boolean sent;

    public EMail(String text, String from, String to) {
        this.text = text;
        this.from = from;
        this.to = to;
    }

    @Override
    public synchronized boolean send() {
        if (sent) {
            throw new IllegalStateException("The mail is already sent");
        }
        sent = true;
        logger.info(String.format("%s is sent", this));
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(sent ? "[SENT]" : "[DRAW]");
        sb.append("text='").append(text).append('\'');
        sb.append(", from='").append(from).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
