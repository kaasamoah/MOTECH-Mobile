package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncMessageStatus;
import com.dreamoval.motech.model.imp.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.*;

/**
 * Converts IncomingMessages into IncomingMessageForms
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Dec 2, 2009
 */
public class IncomingMessageParserImpl implements IncomingMessageParser {

    private CoreManager coreManager;
    private String separator;
    private String delimiter;
    private String cmdRegex;
    private String typeRegex;
    private String paramRegex;

    /**
     * @see IncomingMessageParser.parseRequest
     */
    public synchronized IncomingMessage parseRequest(String message) {
        IncomingMessage inMsg = coreManager.createIncomingMessage();
        inMsg.setContent(message.trim());
        inMsg.setDateCreated(new Date());
        inMsg.setMessageStatus(IncMessageStatus.PROCESSING);

        return inMsg;
    }

    /**
     *
     * @see IncomingMessageParserImpl.getComand
     */
    public synchronized String getCommand(String message) {
        String command = "";

        Pattern pattern = Pattern.compile(cmdRegex);
        Matcher matcher = pattern.matcher(message.trim());

        if (matcher.find()) {
            command = matcher.group();
        }
        return command.toLowerCase();
    }

    /**
     *
     * @see IncomingMessageParserImpl.getFormCode
     */
    public synchronized String getFormCode(String message) {
        String command = "";
        String formCode = "";

        Pattern pattern = Pattern.compile(typeRegex);
        Matcher matcher = pattern.matcher(message.trim());

        if (matcher.find()) {
            command = matcher.group();
            String[] formCodeParts = command.trim().split("=");
            if (formCodeParts.length == 2) {
                formCode = formCodeParts[1].trim();
            }
        }
        return formCode;
    }

    /**
     *
     * @see IncomingMessageParserImpl.getParams
     */
    public synchronized List<IncomingMessageFormParameter> getParams(String message) {
        List<IncomingMessageFormParameter> params = new ArrayList<IncomingMessageFormParameter>();
        List<String> pList = new ArrayList<String>();

        if (delimiter != null && !delimiter.equals("")) {
            String[] paramArr = message.split(delimiter);
            for(String p : paramArr){
                if(!Pattern.matches(typeRegex, p))
                    pList.add(p);
            }
        }
        else {
            Pattern pattern = Pattern.compile(paramRegex);
            Matcher matcher = pattern.matcher(message.trim());

            while (matcher.find()) {
                String match = matcher.group();
                pList.add(match);
            }
        }

        for (String param : pList) {
            param = param.trim();
            param = param.replace(delimiter+delimiter, delimiter);
            String[] paramParts = param.split(separator);

            if (paramParts.length == 2) {
                IncomingMessageFormParameter imParam = coreManager.createIncomingMessageFormParameter();
                imParam.setDateCreated(new Date());
                imParam.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
                imParam.setName(paramParts[0].trim());
                imParam.setValue(paramParts[1].trim());
                params.add(imParam);
            }
        }
        return params;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @param delimeter the delimeter to set
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * @param cmdRegex the cmdRegex to set
     */
    public void setCmdRegex(String cmdRegex) {
        this.cmdRegex = cmdRegex;
    }

    /**
     * @param typeRegex the typeRegex to set
     */
    public void setTypeRegex(String typeRegex) {
        this.typeRegex = typeRegex;
    }

    /**
     * @param paramRegex the paramRegex to set
     */
    public void setParamRegex(String paramRegex) {
        this.paramRegex = paramRegex;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
