package org.motechproject.mobile.omp.manager;

import org.motechproject.mobile.omp.service.CacheService;
import org.motechproject.mobile.omp.service.MessagingService;

/**
 * Handles the creation of all OMP objects
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public interface OMPManager{

    /**
     * creates a new GatewayMessageHandler object
     * @return the created GatewayMessageHandler object
     */
    public GatewayMessageHandler createGatewayMessageHandler();

    /**
     * creates a new SMSGatewayManager object
     * @return the created GatewayManager object
     */
    public GatewayManager createGatewayManager();

    /**
     * creates a new CacheService object
     * @return the created CacheService object
     */
    public CacheService createCacheService();

    /**
     * creates a new MessagingService object
     * @return the created MessagingService object
     */
    public MessagingService createMessagingService();
}
