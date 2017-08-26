package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
    	
    	Student st = new Student();
    	File f =  new File(studentFilePath);
    	try {
			Unmarshaller unmarshaler = jaxb.createUnmarshaller();
			st = (Student)unmarshaler.unmarshal(f);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return st; // TODO
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException, SocketTimeoutException {
        //stores the reference to an unmarshalled object 
    	Config conf = Utils.loadConfig("C:/Users/ftd-5/code/combined-assignments/"
       		+ "4-socket-io-serialization/config/config.xml", Utils.createJAXBContext());
    	try {
    		//creates the server socket with the port number specified at LocalConfig file
			ServerSocket serv = new ServerSocket(conf.getLocal().getPort());
			System.out.println("Server is ready!");
			//wait for 20 sec for connection
			serv.setSoTimeout(20000);
			
			 
			//accept client's request to connect
			while(true){
			Socket soc = serv.accept();
			System.out.println("Yeah! I am now connected");
			
			//create the reference to the student file
			File f = new File(conf.getStudentFilePath());
			//create unmarshaller
			Unmarshaller unmarshal = Utils.createJAXBContext().createUnmarshaller();
			//store the reference to unmarshalled file in the st variable
			Student st = (Student)unmarshal.unmarshal(f);
			
			//create marshaller
			Marshaller marsh = Utils.createJAXBContext().createMarshaller();
			//marshall student file through the socket's output stream
			marsh.marshal(st, soc.getOutputStream() );
			//print final message and close sockets and streams
			System.out.println("Connected!");
			soc.getOutputStream().close();
			serv.close();
			soc.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
