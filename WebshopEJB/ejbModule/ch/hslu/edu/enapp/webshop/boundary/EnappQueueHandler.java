package ch.hslu.edu.enapp.webshop.boundary;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.RestClient;

import ch.hslu.edu.enapp.webshop.control.EnappReceiver;
import ch.hslu.edu.enapp.webshop.enappdaemon.SalesOrderDaemon;

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
    
//    @Inject EnappReceiver enappReceiver;
    
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
//        messageProducer.close();
//        conn.start();
        session.close();
        conn.close();
    }
    
    public SalesOrderDaemon getOrderState(final String correlationId) {
        final String requestURI = "http://enapp-daemons.el.eee.intern:9080/EnappDaemonWeb/rest/salesorder/corr/" + correlationId;

        System.out.println("Request-URL: " + requestURI);
        
        final RestClient client = new RestClient();
        org.apache.wink.client.Resource webResource = null;

//        SalesOrderDaemon salesOrderStatus = new SalesOrderDaemon();
//        salesOrderStatus.setOrderStatus("error");
        SalesOrderDaemon salesOrderStatus = null;
        try {
            int i = 0;
            
            while (salesOrderStatus == null && i<10) {
                System.out.println("SalesOrder-Loop" + String.valueOf(i++));

                webResource = client.resource(requestURI);
                
                final ClientResponse response = webResource.get();
                salesOrderStatus = response.getEntity(SalesOrderDaemon.class);
                Thread.sleep(1000);
            }
            
            int j = 0;
            
            if (salesOrderStatus != null) {
                System.out.println("Sales Order Status: " + salesOrderStatus.getOrderStatus());
                System.out.println("External Customer ID: " + salesOrderStatus.getExternalCustomerId());
                
                while (salesOrderStatus.getExternalCustomerId() == null && j<10) {
                    System.out.println("SalesOrder-Status-Loop" + String.valueOf(j++));
                    final ClientResponse response = webResource.get();
                    salesOrderStatus = response.getEntity(SalesOrderDaemon.class);
                    
                    System.out.println("Sales Order Status: " + salesOrderStatus.getOrderStatus());
                    System.out.println("External Customer ID: " + salesOrderStatus.getExternalCustomerId());
                    
                    Thread.sleep(1000);
//                    j++;
                }
            }
            
        } catch (final Exception e) {
            // EnAPP Daemon not yet up-to-date... ignore :)
        }

        return salesOrderStatus;
    }
}
