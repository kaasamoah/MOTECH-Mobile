package org.motechproject.mobile.omp.service;

import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.dao.GatewayRequestDetailsDAO;
import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

/**
 * An SMS specific implementation of the CacheService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSCacheServiceImpl implements CacheService {

    private CoreManager coreManager;
    private static Logger logger = Logger.getLogger(SMSCacheServiceImpl.class);

    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequest messageDetails, MotechContext context) {
        logger.debug("Initializing DAO");
        GatewayRequestDAO messageDAO = coreManager.createGatewayRequestDAO(context);
        
        logger.debug("Caching message");
        logger.debug(messageDetails);
        
        Transaction tx = (Transaction) messageDAO.getDBSession().getTransaction();
        tx.begin();
        messageDAO.save(messageDetails);
        tx.commit();
    }
    
    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequestDetails messageDetails, MotechContext context) {
        logger.debug("Initializing DAO");
        GatewayRequestDetailsDAO messageDAO = coreManager.createGatewayRequestDetailsDAO(context);
        
        logger.debug("Caching message");
        logger.debug(messageDetails);
        
        Transaction tx = (Transaction) messageDAO.getDBSession().getTransaction();
        tx.begin();
        messageDAO.save(messageDetails);
        tx.commit();
    }
    
    /**
     *
     * @see CacheService.saveResponse
     */
    public void saveResponse(GatewayResponse responseDetails, MotechContext context) {
        logger.debug("Initializing DAO");
        GatewayResponseDAO responseDAO = coreManager.createGatewayResponseDAO(context);
        
        logger.debug("Caching response");
        logger.debug(responseDetails);
        
        Transaction tx = (Transaction) responseDAO.getDBSession().getTransaction();
        tx.begin();
        responseDAO.save(responseDetails);
        tx.commit();
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria, MotechContext context) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO(context);
        return messageDao.findByExample(criteria);
    }
    
    /**
     * 
     * see CacheService.getMessagesByStatus
     */
    public List<GatewayRequest> getMessagesByStatus(MStatus criteria, MotechContext context) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO(context);
        return messageDao.getByStatus(criteria);
    }

    /**
     *
     * see CacheService.getMessagesByStatus
     */
    public List<GatewayRequest> getMessagesByStatusAndSchedule(MStatus criteria, Date schedule, MotechContext context) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO(context);
        return messageDao.getByStatusAndSchedule(criteria, schedule);
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria, MotechContext context) {
        GatewayResponseDAO responseDao = coreManager.createGatewayResponseDAO(context);
        return responseDao.findByExample(criteria);
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        logger.debug("Setting value of SMSCacheServiceImpl.coreManager");
        logger.debug(coreManager);
        this.coreManager = coreManager;
    }
}