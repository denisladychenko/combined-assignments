package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     */
    public static JAXBContext createJAXBContext() {
    	JAXBContext jax = null;
    	try {
    		//creates an instance of jaxb context
			jax = JAXBContext.newInstance(RemoteConfig.class,Config.class,LocalConfig.class, Student.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//returns context
        return jax; // TODO
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) {
    	//object variable to hold unmarshalled object
    	Config conf = new Config();
    	 try {
    		 //create the reference of the file
         	File f = new File(configFilePath);
         	//create unmarshaller
         	Unmarshaller unmarshall = jaxb.createUnmarshaller();
         	//store reference to an unmarshalled object to conf
 			conf = (Config) unmarshall.unmarshal(f);
 			
 			} catch (JAXBException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
        return conf; // TODO
    }
}
