package ch.hslu.edu.enapp.webshop.boundary;

import java.security.MessageDigest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.apache.wink.common.internal.MultivaluedMapImpl;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.enappdaemon.NcResponse;

/**
 * Session Bean implementation class PaymentManager
 */
@Stateless
@LocalBean
public class PaymentManager {

    private final String URI = "https://e-payment.postfinance.ch/ncol/test/orderdirect.asp";

    private final String PSPID = "HSLUiCompany";
    private final String USERID = "enappstudents";
    private final String PSWD = "ds2H9ZV.p!8r";
    private final String PSWD_SHA1 = "hslu!comp@ny.websh0p";
    private final String CURRENCY = "CHF";
    
//  Mastercard
//    private final String CARDNO = "5399 9999 9999 9999";
//    private final String EXPIRY_DATE = "09/17";
//    private final String CVC = "123";
    
    //VISA
    private final String CARDNO = "4111 1111 1111 1111";
    private final String EXPIRY_DATE = "09/17";
    private final String CVC = "123";
    
    /**
     * Default constructor. 
     */
    public PaymentManager() {
        // TODO Auto-generated constructor stub
    }
    
    public NcResponse execPayment(String orderId, double amount, CustomerDTO customer) {
        final MultivaluedMap<String, String> restData = new MultivaluedMapImpl<String, String>();
        final String amountPostfinance = Long.toString(Math.round(100 * amount));

        String shasign = "";
        final StringBuilder sha1sig = new StringBuilder();
        sha1sig.append("AMOUNT=" + amountPostfinance + PSWD_SHA1);
        sha1sig.append("CARDNO=" + CARDNO + PSWD_SHA1);
        sha1sig.append("CURRENCY=" + CURRENCY + PSWD_SHA1);
        sha1sig.append("CVC=" + CVC + PSWD_SHA1);
        sha1sig.append("ED=" + EXPIRY_DATE + PSWD_SHA1);
        sha1sig.append("ORDERID=" + orderId + PSWD_SHA1);
        sha1sig.append("PSPID=" + PSPID + PSWD_SHA1);
        sha1sig.append("PSWD=" + PSWD + PSWD_SHA1);
        sha1sig.append("USERID=" + USERID + PSWD_SHA1);
        
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-1");
            final byte[] hashedBytes = digest.digest(sha1sig.toString().getBytes("UTF-8"));
            shasign = byteArray2HexString(hashedBytes);
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        restData.add("AMOUNT", amountPostfinance);
        restData.add("CARDNO", CARDNO);
        restData.add("CURRENCY", CURRENCY);
        restData.add("CVC", CVC);
        restData.add("ED", EXPIRY_DATE);
        restData.add("ORDERID", orderId);
        restData.add("PSPID", PSPID);
        restData.add("PSWD", PSWD);
        restData.add("SHASIGN", shasign);
        restData.add("USERID", USERID);

        final RestClient client = new RestClient();
        final Resource webResource = client.resource(URI);
        
        NcResponse ncResponse = webResource.accept("application/x-www-form-urlencoded").post(NcResponse.class, restData);
        
        System.out.println("### OrderId:     " + ncResponse.getOrderId());
        System.out.println("### PAYID:       " + ncResponse.getPayId());
        System.out.println("### NCSTATUS:    " + ncResponse.getNcStatus());
        System.out.println("### NCERROR:     " + ncResponse.getNcError());
        System.out.println("### NCERRORPLUS: " + ncResponse.getNcErrorPlus());
        
        return ncResponse;
    }
    
    private static String byteArray2HexString(final byte[] bytes) {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buffer.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return buffer.toString();
    }
}
