package org.motechproject.mobile.core.model;

import org.motechproject.mobile.core.util.MotechIDGenerator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *  Date : Sep 24, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@SuppressWarnings("serial")
public class GatewayRequestDetailsImpl implements GatewayRequestDetails, Serializable {

    private int version=-1;
    private Long id;
    private MessageType messageType;
    private String message;
    private int numberOfPages;
    private Set gatewayRequests = new HashSet();

    public GatewayRequestDetailsImpl() {
        this.id = MotechIDGenerator.generateID();  
    }
    /**
     * @return the messageType
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * @return the gatewayRequests
     */
    public Set getGatewayRequests() {
        return gatewayRequests;
    }

    /**
     * @param gatewayRequests the gatewayRequests to set
     */
    public void setGatewayRequests(Set gatewayRequests) {
        this.gatewayRequests = gatewayRequests;
    }

    /**
     * Helper method to add GatewayRequest Object to GatewayRequestDetails
     * @param gatewayRequest the gatewayRequest object to add
     */
    public void addGatewayRequest(GatewayRequest gatewayRequest) {
        if (gatewayRequest != null) {
            gatewayRequest.setGatewayRequestDetails(this);
            this.gatewayRequests.add(gatewayRequest);
        }
    }

    /**
     * Helper method to add GatewayRequest Object to GatewayRequestDetails
     * @param gatewayRequest the gatewayRequest object to add
     */
    public void removeGatewayRequest(GatewayRequest gatewayRequest) {
        if (this.gatewayRequests.contains(gatewayRequest)) {
            this.gatewayRequests.remove(gatewayRequest);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id               value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.message != null) ? "key=message          value=" + this.message : "message is null  ");
            sb.append(newLine);
            sb.append((this.numberOfPages != -1) ? "key=numberOfPages          value=" + this.numberOfPages : "numberOfPages is null ");
            sb.append(newLine);
            sb.append((this.gatewayRequests.isEmpty()) ? "key=gatewayRequests          length=" + Integer.toString(this.gatewayRequests.size()) : "gatewayRequests is empty ");
            sb.append(newLine);
            sb.append((this.messageType != null) ? "key=messageType          value=" + this.messageType.toString() : "messageType is null ");
            sb.append(newLine);

            return sb.toString();

        } else {
            return "Object is null";
        }


    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

     /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }
}
