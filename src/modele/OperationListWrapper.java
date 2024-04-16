package modele;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "operations")

public class OperationListWrapper {
	 private List<Operations> operations;

	    @XmlElement(name = "operations")
	    public List<Operations> getOperations() {
	        return operations;
	    }

	    public void setOperations(List<Operations> operations) {
	        this.operations =operations;
	    }

}
