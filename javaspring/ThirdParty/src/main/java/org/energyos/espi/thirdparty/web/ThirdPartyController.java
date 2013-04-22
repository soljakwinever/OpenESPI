/*******************************************************************************
 * Copyright (c) 2011, 2012 EnergyOS.Org
 *
 * Licensed by EnergyOS.Org under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The EnergyOS.org licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at:
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *  
 ******************************************************************************
*/


package org.energyos.espi.thirdparty.web;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.energyos.espi.thirdparty.atom.FeedType;
import org.energyos.espi.thirdparty.domain.ThirdParty;
import org.energyos.espi.thirdparty.common.Subscription;
import org.energyos.espi.thirdparty.domain.ThirdParty;
import org.springframework.http.HttpHeaders;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/thirdpartys")
@Controller
@RooWebScaffold(path = "thirdpartys", formBackingObject = ThirdParty.class)
public class ThirdPartyController {

    @RequestMapping(method = RequestMethod.GET, value="/{id}/uploadmydata", headers="Accept=application/atom+xml")
    @ResponseBody
    public String getDownloadMyData(@PathVariable("id") Long id) {
    	String xmlResult;
		URL aFeed;
		FeedType theFeed;
	    Unmarshaller unmarshaller;
        JAXBContext context;
        Marshaller m;
        Writer w = null;
        JAXBContext jc;
		
        ThirdParty resource = ThirdParty.findThirdParty(id);
      
        if (resource == null) {
            // TODO establish the proper way to return the error streams                                                                                                                                                                                               
            // return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);   
            return "400: Resource Not Found";
        } else {
        	// first upload and unmarshal     	
    		try {
				jc = JAXBContext.newInstance(org.energyos.espi.thirdparty.atom.FeedType.class);
				try {
					aFeed = new URL("http://www.openespi.org/sampleData/enernoc/10.xml");
					try {
						unmarshaller = jc.createUnmarshaller();
			    		try {
			    			theFeed = (FeedType) JAXBIntrospector.getValue(unmarshaller.unmarshal(aFeed));

				            try {
								context = JAXBContext.newInstance(org.energyos.espi.thirdparty.atom.FeedType.class);
								m = context.createMarshaller();
								m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
								m = context.createMarshaller();
							    w = new StringWriter();
								m.marshal(theFeed, w);
							    xmlResult = w.toString();
					            try {
					                w.close();
					            } catch (IOException e) {
					                e.printStackTrace();
					            }
					            HttpHeaders hdr = new HttpHeaders();
					            hdr.set("Content-Type", "application/atom+xml");
					            return xmlResult;

							} catch (JAXBException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			    		} catch (JAXBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (JAXBException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
    		}
        }
        return null;
    }
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}/testData", headers="Accept=application/atom+xml")
    @ResponseBody
    public String getBatchList(@PathVariable("id") Long id)  {
    	   
    	Boolean result;
    	
    	List theBatch;

        String xmlResult;
        theBatch = null;
        // get the resource                                                                                                                                                                                                                                                
        ThirdParty resource = ThirdParty.findThirdParty(id);
        result = resource.NotifyData_(theBatch);
        if (resource == null) {
            // TODO establish the proper way to return the error streams                                                                                                                                                                                               
            // return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);   
            return "400: Resource Not Found";
        } else {
            // marshal the resource                                                                                                                                                                                                                                       
            JAXBContext context;
            Marshaller m;
            Writer w = null;

            try {
                context = JAXBContext.newInstance(org.energyos.espi.thirdparty.domain.ThirdParty.class);
                m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                w = new StringWriter();
                m.marshal(resource, w);
            } catch (JAXBException e1) {
                // TODO Auto-generated catch block                                                                                                                                                                                                                         
                e1.printStackTrace();
            }
            xmlResult = w.toString();
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpHeaders hdr = new HttpHeaders();
            hdr.set("Content-Type", "application/atom+xml");
            return xmlResult;
            // we may want a response entity for response encapsulation rather than just the raw string                                                                                                                                                                    
            //                                                                                                                                                                                                                                                             
            //      return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);                                                                                                                                                                          
        }
 
    }
 
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}", headers="Accept=application/atom+xml")
    @ResponseBody
    public String getResource(@PathVariable("id") Long id)  {

        String xmlResult;
        // get the resource                                                                                                                                                                                                                                                
        ThirdParty resource = ThirdParty.findThirdParty(id);
      
        if (resource == null) {
            // TODO establish the proper way to return the error streams                                                                                                                                                                                               
            // return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);   
            return "400: Resource Not Found";
        } else {
            // marshal the resource                                                                                                                                                                                                                                       
            JAXBContext context;
            Marshaller m;
            Writer w = null;

            try {
                context = JAXBContext.newInstance(org.energyos.espi.thirdparty.domain.ThirdParty.class);
                m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                w = new StringWriter();
                m.marshal(resource, w);
            } catch (JAXBException e1) {
                // TODO Auto-generated catch block                                                                                                                                                                                                                         
                e1.printStackTrace();
            }
            xmlResult = w.toString();
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpHeaders hdr = new HttpHeaders();
            hdr.set("Content-Type", "application/atom+xml");
            return xmlResult;
            // we may want a response entity for response encapsulation rather than just the raw string                                                                                                                                                                    
            //                                                                                                                                                                                                                                                             
            //      return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);                                                                                                                                                                          
        }
 
    }
    
 	
}
