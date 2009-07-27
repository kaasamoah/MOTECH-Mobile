/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.hibernate.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jojo
 */
public class MessageDetailsDAOImpl implements MessageDetailsDAO {

  SessionContainer sessionManager;

  public MessageDetailsDAOImpl(){}

  public MessageDetailsDAOImpl(HibernateUtils sessionManager){
    this.sessionManager = sessionManager;
  }

    public boolean StoreMessage(MessageDetails messageDetails) {
        try
        {
            sessionManager = new HibernateUtils();
            Session session = sessionManager.requestSession();
            session.beginTransaction();
            session.save(messageDetails);
            session.getTransaction().commit();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
       
    }


//    public boolean StoreMessageAndResponse(MessageDetails messageDetails, ResponseDetails[] responseDetails) {
//         try
//         {
//           SessionContainer sessionManager = new HibernateUtils();
//        Session session = sessionManager.ResquestSession();
//        session.beginTransaction();
//        session.save(messageDetails);
//        Long id = messageDetails.getMessageId();
//        for(ResponseDetails dt: responseDetails)
//        {
//            dt.setMessageId(messageDetails);
//            messageDetails.getResponseDetails().add(dt);
//        }
//
//        session.saveOrUpdate(messageDetails);
//        session.getTransaction().commit();
//             return true;
//         }
//         catch(Exception ex)
//         {
//             ex.printStackTrace();
//             return false;
//         }
//    }


    public boolean DeleteMessageById(Long id) {
        try
        {
             sessionManager = new HibernateUtils();
             Session session = sessionManager.requestSession();
             session.beginTransaction();
             MessageDetails todelete = (MessageDetails) session.get(MessageDetails.class, id);
             session.delete(todelete);
             session.getTransaction().commit();

             return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

       
    }


    public boolean Delete(MessageDetails messageDetails) {
        try
        {
           sessionManager = new HibernateUtils();
           Session session = sessionManager.requestSession();

           session.beginTransaction();
           session.delete(messageDetails);
           session.getTransaction().commit();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public List<MessageDetails> GetAllByStatus(String status) {
        try
        {
            sessionManager = new HibernateUtils();
            Session session = sessionManager.requestSession();
            return (List<MessageDetails>) session.createCriteria(MessageDetails.class)
                    .add(Restrictions.eq("globalStatus", status)).list();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

}
