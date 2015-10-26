import java.util.List;

import javax.inject.Inject;

import ch.hslu.edu.enapp.webshop.common.ItemManagerLocal;

public class Main {
    @Inject
    ch.hslu.edu.enapp.webshop.common.ItemManagerLocal items;
    
	public static void main(String[] args) {
	    ItemManagerLocal itemManagerLocal = new ItemManagerLocal() {
            
            @Override
            public List getItems() {
                // TODO Auto-generated method stub
                return null;
            }
        };
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}