package com.chenhz.http.utils;

import com.chenhz.http.exception.ClientException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlReader implements Reader {
    Map<String, String> map = new HashMap();

    public XmlReader() {
    }

    public Map<String, String> read(String response, String endpoint) throws ClientException {
        try {
            Element root = XmlUtils.getRootElementFromString(response);
            this.read(root, endpoint, false);
        } catch (ParserConfigurationException var5) {
            new ClientException("SDK.InvalidXMLParser", var5.toString());
        } catch (SAXException var6) {
            new ClientException("SDK.InvalidXMLFormat", var6.toString());
        } catch (IOException var7) {
            new ClientException("SDK.InvalidContent", var7.toString());
        }

        return this.map;
    }

    public Map<String, String> readForHideArrayItem(String response, String endpoint) throws ClientException {
        return this.read(response, endpoint);
    }

    private void read(Element element, String path, boolean appendPath) {
        path = this.buildPath(element, path, appendPath);
        List<Element> childElements = XmlUtils.getChildElements(element);
        if (childElements.size() == 0) {
            this.map.put(path, element.getTextContent());
        } else {
            List<Element> listElements = XmlUtils.getChildElements(element, ((Element)childElements.get(0)).getNodeName());
            if (listElements.size() > 1 && childElements.size() == listElements.size()) {
                this.elementsAsList(childElements, path);
            } else if (listElements.size() == 1 && childElements.size() == 1) {
                this.elementsAsList(listElements, path);
                this.read((Element)childElements.get(0), path, true);
            } else {
                Iterator i$ = childElements.iterator();

                while(i$.hasNext()) {
                    Element childElement = (Element)i$.next();
                    this.read(childElement, path, true);
                }
            }

        }
    }

    private String buildPath(Element element, String path, boolean appendPath) {
        return appendPath ? path + "." + element.getNodeName() : path;
    }

    private void elementsAsList(List<Element> listElements, String path) {
        this.map.put(path + ".Length", String.valueOf(listElements.size()));

        for(int i = 0; i < listElements.size(); ++i) {
            this.read((Element)listElements.get(i), path + "[" + i + "]", false);
        }

    }
}
