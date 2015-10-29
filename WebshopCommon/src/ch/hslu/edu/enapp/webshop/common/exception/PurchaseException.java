package ch.hslu.edu.enapp.webshop.common.exception;

public class PurchaseException extends Exception {
    public PurchaseException() {
        super("Purchase not finished");
    }
}
