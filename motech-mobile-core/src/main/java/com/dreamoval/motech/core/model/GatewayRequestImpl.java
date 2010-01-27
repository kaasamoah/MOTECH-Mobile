package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class GatewayRequestImpl extends MotechEntityImpl implements GatewayRequest {

    private GatewayRequestDetails gatewayRequestDetails;
    private Date dateTo;
    private String message;
    private String recipientsNumber;
    private Date dateFrom;
    private Set<GatewayResponse> responseDetails = new HashSet<GatewayResponse>();
    private Date dateSent;
    private int tryNumber;
    private String requestId;
    private MStatus messageStatus;
    private Date lastModified;

    public GatewayRequestImpl() {
    }

    public GatewayRequestImpl(Date dateTo, String messageText, String recipientsNumber, Date dateFrom, Date dateSent) {

        this.dateTo = dateTo;
        this.message = messageText;
        this.dateFrom = dateFrom;
        this.dateSent = dateSent;
        this.recipientsNumber = recipientsNumber;

    }

    /**
     * @return the messageType
     */
    /**
     * @return the numberOfPages
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the messageText
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param messageText the messageText to set
     */
    public void setMessage(String messageText) {
        this.message = messageText;
    }

    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the responseDetails
     */
    public Set<GatewayResponse> getResponseDetails() {
        return responseDetails;
    }

    /**
     * @param responseDetails the responseDetails to set
     */
    public void setResponseDetails(Set<GatewayResponse> responseDetails) {
        this.responseDetails = responseDetails;
    }

    /**
     * @return the dateSent
     */
    public Date getDateSent() {
        return dateSent;
    }

    /**
     * @param dateSent the dateSent to set
     */
    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    /**
     * @return the recipientsNumbers
     */
    public String getRecipientsNumber() {
        return recipientsNumber;
    }

    /**
     * @param recipientsNumbers the recipientsNumbers to set
     */
    public void setRecipientsNumber(String recipientsNumbers) {
        this.recipientsNumber = recipientsNumbers;
    }

    /**
     * @see {@link com.dreamoval.motech.core.model.GatewayRequest#addResponse(com.dreamoval.motech.core.model.GatewayResponse)  }
     */
    public void addResponse(GatewayResponse response) {
        response.setGatewayRequest(this);
        this.responseDetails.add(response);
    }

    /**
     * @see {@link  com.dreamoval.motech.core.model.GatewayRequest#removeResponse(com.dreamoval.motech.core.model.GatewayResponse)  }
     */
    public void removeResponse(GatewayResponse response) {
        if (this.responseDetails.contains(response)) {
            this.responseDetails.remove(response);
        }

    }

    /**
     * @see {@link com.dreamoval.motech.core.model.GatewayRequest#addResponse(java.util.List)  }
     */
    public void addResponse(List<GatewayResponse> responses) {

        for (GatewayResponse r : responses) {
            r.setGatewayRequest(this);
            this.responseDetails.add(r);
        }

    }

    /**
     * @see {@link com.dreamoval.motech.core.model.GatewayRequest#removeResponse(java.util.List)  }
     */
    public void removeResponse(List<GatewayResponse> responses) {
        for (GatewayResponse r : responses) {
            if (this.responseDetails.contains(r)) {
                this.responseDetails.remove(r);
            }
        }
    }

    /**
     * @return the gatewayRequestDetails
     */
    public GatewayRequestDetails getGatewayRequestDetails() {
        return gatewayRequestDetails;
    }

    /**
     * @param gatewayRequestDetails the gatewayRequestDetails to set
     */
    public void setGatewayRequestDetails(GatewayRequestDetails gatewayRequestDetails) {
        this.gatewayRequestDetails = gatewayRequestDetails;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the tryNumber
     */
    public int getTryNumber() {
        return tryNumber;
    }

    /**
     * @param tryNumber the tryNumber to set
     */
    public void setTryNumber(int tryNumber) {
        this.tryNumber = tryNumber;
    }

    /**
     * @return the messageStatus
     */
    public MStatus getMessageStatus() {
        return messageStatus;
    }

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(MStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.message != null) ? "key=message value=" + this.message : "message is null  ");
            sb.append(newLine);
            sb.append((this.recipientsNumber != null) ? "key=recipientsNumber value=" + this.recipientsNumber : "recipientsNumber is null ");
            sb.append(newLine);
            sb.append((this.requestId != null) ? "key=requestId value=" + this.requestId : "requestId is null ");
            sb.append(newLine);
            sb.append((this.gatewayRequestDetails != null) ? "key=gatewayRequestDetails.Id value=" + this.gatewayRequestDetails.getId() : "gatewayRequestDetails.Id is null ");
            sb.append(newLine);
            sb.append((this.tryNumber != -1) ? "key=tryNumber.Id value=" + Integer.toString(this.tryNumber) : "tryNumber is null ");
            sb.append(newLine);
            sb.append((this.responseDetails.isEmpty()) ? "key=responseDetails length=" + Integer.toString(this.responseDetails.size()) : "responseDetails is empty ");
            sb.append(newLine);

            for (Iterator it = this.responseDetails.iterator(); it.hasNext();) {
                GatewayResponse resp = (GatewayResponse) it.next();
                sb.append((resp != null) ? "key=GatewayResponse.Id value=" + resp.getId().toString() : "GatewayResponse.Id is null ");
                sb.append(newLine);
            }

            sb.append((this.dateSent != null) ? "key=dateSent value=" + this.dateSent.toString() : "dateSent is null ");
            sb.append(newLine);
            sb.append((this.dateTo != null) ? "key=dateTo value=" + this.dateTo.toString() : "dateTo is null ");
            sb.append(newLine);
            sb.append((this.dateFrom != null) ? "key=dateFrom value=" + this.dateFrom.toString() : "dateFrom is null ");
            sb.append(newLine);
            sb.append((this.lastModified != null) ? "key=lastModified value=" + this.lastModified.toString() : "lastModified is null ");
            sb.append(newLine);
            sb.append((this.messageStatus != null) ? "key=messageStatus value=" + this.messageStatus.toString() : "messageStatus is null ");
            sb.append(newLine);

            return sb.toString();

        } else {
            return "Object is null";
        }


    }
}
