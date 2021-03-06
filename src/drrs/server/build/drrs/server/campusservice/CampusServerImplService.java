
package drrs.server.build.drrs.server.campusservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CampusServerImplService", targetNamespace = "http://campusservice.server.drrs/", wsdlLocation = "file:/C:/Users/vanos/IdeaProjects/SOEN423-Assignment3/src/drrs/server/campus.wsdl")
public class CampusServerImplService
    extends Service
{

    private final static URL CAMPUSSERVERIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException CAMPUSSERVERIMPLSERVICE_EXCEPTION;
    private final static QName CAMPUSSERVERIMPLSERVICE_QNAME = new QName("http://campusservice.server.drrs/", "CampusServerImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/vanos/IdeaProjects/SOEN423-Assignment3/src/drrs/server/campus.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CAMPUSSERVERIMPLSERVICE_WSDL_LOCATION = url;
        CAMPUSSERVERIMPLSERVICE_EXCEPTION = e;
    }

    public CampusServerImplService() {
        super(__getWsdlLocation(), CAMPUSSERVERIMPLSERVICE_QNAME);
    }

    public CampusServerImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CAMPUSSERVERIMPLSERVICE_QNAME, features);
    }

    public CampusServerImplService(URL wsdlLocation) {
        super(wsdlLocation, CAMPUSSERVERIMPLSERVICE_QNAME);
    }

    public CampusServerImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CAMPUSSERVERIMPLSERVICE_QNAME, features);
    }

    public CampusServerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CampusServerImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ICampus
     */
    @WebEndpoint(name = "CampusServerImplPort")
    public ICampus getCampusServerImplPort() {
        return super.getPort(new QName("http://campusservice.server.drrs/", "CampusServerImplPort"), ICampus.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICampus
     */
    @WebEndpoint(name = "CampusServerImplPort")
    public ICampus getCampusServerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://campusservice.server.drrs/", "CampusServerImplPort"), ICampus.class, features);
    }

    /**
     * 
     * @return
     *     returns ICampus
     */
    @WebEndpoint(name = "CampusServerDVLImplPort")
    public ICampus getCampusServerDVLImplPort() {
        return super.getPort(new QName("http://campusservice.server.drrs/", "CampusServerDVLImplPort"), ICampus.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICampus
     */
    @WebEndpoint(name = "CampusServerDVLImplPort")
    public ICampus getCampusServerDVLImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://campusservice.server.drrs/", "CampusServerDVLImplPort"), ICampus.class, features);
    }

    /**
     * 
     * @return
     *     returns ICampus
     */
    @WebEndpoint(name = "CampusServerKKLImplPort")
    public ICampus getCampusServerKKLImplPort() {
        return super.getPort(new QName("http://campusservice.server.drrs/", "CampusServerKKLImplPort"), ICampus.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICampus
     */
    @WebEndpoint(name = "CampusServerKKLImplPort")
    public ICampus getCampusServerKKLImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://campusservice.server.drrs/", "CampusServerKKLImplPort"), ICampus.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CAMPUSSERVERIMPLSERVICE_EXCEPTION!= null) {
            throw CAMPUSSERVERIMPLSERVICE_EXCEPTION;
        }
        return CAMPUSSERVERIMPLSERVICE_WSDL_LOCATION;
    }

}
