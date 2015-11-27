package ch.hslu.edu.enapp.webshop.boundary;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * Session Bean implementation class EnappQueueHandler
 */
@Stateless
@LocalBean
public class EnappQueueHandler {

    @Resource(name = "EnappQueue")
    Queue enappQueue;
    @Resource(name = "EnappQueueConnectionFactory")
    ConnectionFactory enappQueueConnectionFactory;
    
    /**
     * Default constructor. 
     */
    public EnappQueueHandler() {
        // TODO Auto-generated constructor stub
    }
    
    public void sendPurchaseMessage(String correlationId, String purchaseMessage) throws JMSException {
        Connection conn = enappQueueConnectionFactory.createConnection();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        MessageProducer messageProducer = session.createProducer(enappQueue);
        final Message message = session.createTextMessage(purchaseMessage);
        
        message.setStringProperty("MessageFormat", "Version 1.5");
        message.setJMSCorrelationID(correlationId);
        message.setJMSReplyTo(null);
        messageProducer.send(message);
        
        session.close();
        conn.close();
    }

}
