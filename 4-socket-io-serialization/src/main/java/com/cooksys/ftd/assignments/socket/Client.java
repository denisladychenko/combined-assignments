package com.cooksys.ftd.assignments.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

	
    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
    	//store the reference to the unmarshalled config file  at conf
       Config conf = Utils.loadConfig("C:/Users/ftd-5/code/combined-assignments/"
       		+ "4-socket-io-serialization/config/config.xml", Utils.createJAXBContext());
       try {
    	   //create client socket with host and port values retrieved from RemoteConfig file
		Socket soc = new Socket(conf.getRemote().getHost(), conf.getRemote().getPort());
		//create unmarshaller
		Unmarshaller unmarshal = Utils.createJAXBContext().createUnmarshaller();
		//unmarshall student file through the soc input stream and store the ref to it at st
		Student st = (Student)unmarshal.unmarshal(soc.getInputStream());
		//print out  student's info
		System.out.println(st);
		soc.close();
		System.out.println("socket is closed");
		
		
		
	}catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch(SocketException e){
		e.printStackTrace();
	} catch ( IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
        
    }
}
