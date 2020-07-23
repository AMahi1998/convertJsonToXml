package convertJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class Convert {
	static Document dom;
    static Element e = null;
    static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    
	public static void main(String[] args) throws ParserConfigurationException {
		JSONParser parser = new JSONParser();
		FileReader file;
		Document dom;
	    Element e = null;
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
      
        dom = db.newDocument();

        Element rootEle = dom.createElement("RES_Request");

     
        e = dom.createElement("Request_Type");
        e.appendChild(dom.createTextNode("UpdateRoomRates"));
        rootEle.appendChild(e);
	    
		try {
			System.out.println("Reading json file:- input.json");
			file = new FileReader("C:\\Master_folder\\Learning_space\\Mahima project\\project1\\convertJsonToXml\\convertJson\\src\\main\\java\\convertJson\\input_data.json");
			Object obj = parser.parse(file);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray rooms = (JSONArray) jsonObject.get("rooms");
			Room room = new Room();
			 room.setFromdate((String)jsonObject.get("startDate"));
		        room.setTodate((String)jsonObject.get("endDate"));
		        room.setTax_inc((Boolean)jsonObject.get("taxInclusive"));
			for (Object o : rooms) {
		        JSONObject jsonLineItem = (JSONObject) o;

		        room.setRoomtypeid((String) jsonLineItem.get("roomId"));
		       
		        for(Object j : (JSONArray)jsonLineItem.get("rateplans")){
		        	JSONObject jsonLineItem2 = (JSONObject) j;
		        	room.setRatetypeid((String)jsonLineItem2.get("rateplanId"));
		        	JSONObject rate = (JSONObject) jsonLineItem2.get("rate");
		        room.setRate(rate.get("rackRate"));
		        	 System.out.println(room);
		        	 Element rt = dom.createElement("RateType");
		        	 Element roomtype = dom.createElement("RoomTypeID");
			         roomtype.appendChild(dom.createTextNode(room.getRoomtypeid()));
			         rt.appendChild(roomtype);
			         
			         Element ratetype = dom.createElement("RateTypeID");
			         ratetype.appendChild(dom.createTextNode(room.getRatetypeid()));
			         rt.appendChild(ratetype);
			         
			         
			         Element fromdate = dom.createElement("FromDate");
			         fromdate.appendChild(dom.createTextNode(room.getFromdate()));
			         rt.appendChild(fromdate);
			         
			         
			         Element todate = dom.createElement("ToDate");
			         todate.appendChild(dom.createTextNode(room.getTodate()));
			         rt.appendChild(todate);
			         
			         Element tax = dom.createElement("Taxinclusive");
			         tax.appendChild(dom.createTextNode(room.getTax_inc().toString()));
			         rt.appendChild(tax);
			         
			         Element r_rate = dom.createElement("RoomRate");
		        	 Element price = dom.createElement("Base");
			         price.appendChild(dom.createTextNode(room.getRate().toString()));
			         r_rate.appendChild(price);
			         rt.appendChild(r_rate);
			         
			         
			         rootEle.appendChild(rt);
		        }
		       
		    }
			
			
	
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		

	         dom.appendChild(rootEle);
	         
	         
	         try {
	             Transformer tr = TransformerFactory.newInstance().newTransformer();
	             tr.setOutputProperty(OutputKeys.INDENT, "yes");
	             tr.setOutputProperty(OutputKeys.METHOD, "xml");
	             tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	             tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	             tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	             
	             
	
	             tr.transform(new DOMSource(dom), 
	                                  new StreamResult(new FileOutputStream("output.xml")));
	             System.out.println("generated xml file:- output.xml");
	         } catch (TransformerException te) {
	             System.out.println(te.getMessage());
	         } catch (IOException ioe) {
	             System.out.println(ioe.getMessage());
	         
	     }
	         }

	

}
