package com.hackathon.clari.relationshipgraph.common;

import com.hackathon.clari.relationshipgraph.service.ActivityGraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:57 AM
 */
public class Constants {

    public static final String EMAIL = "email";
    public static final String LAST_ENGAGED_DATE = "lastEngagedDate";
    public static final String SHARED_ACTIVITY_COUNTS = "sharedActivityCounts";
    public static final Logger LOGGER = LoggerFactory.getLogger(ActivityGraphService.class);
    public static final String EMAIL_RECEIVED = "Email_Received";
    public static final String EMAIL_SENT = "Email_Sent";
    public static final String MEETING = "Meeting";
    public static final String ATTACHMENT_SENT = "Attachment_Sent";
    public static final String ATTACHMENT_RECEIVED = "Attachment_Received";
    public static final String USER_NAME = "userName";
    public static final String PATH = "path";
}
