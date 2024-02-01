# Apache TIKA Demonstration

This demonstration application is for the purpose of using the Maven shade plugin to relocation the TIKA package.

To build the shaded jar, navigate to the /etc directory and issue mvn -f pom-shade.xml clean install.

The artifact produced will be net.jmp.demo.apache.shaded.tika:shaded-tika:2.9.1.

I learned that one can set -Djaxp.debug=true in order to observe what JAXP is doing.

I also learned that the XPathFactory has to be specified as a property; in this case -Djavax.shaded.xml.xpath.XPathFactory:http://java.sun.com/jaxp/xpath/dom=net.shaded.sf.saxon.xpath.XPathFactoryImpl

The typical property is -Djavax.xml.xpath.XPathFactory but in this case this class has been relocated to javax.shaded.xml.xpath.XPathFactory.

As an alternative to setting properties on the command line using System.setProperty() in early code does work.