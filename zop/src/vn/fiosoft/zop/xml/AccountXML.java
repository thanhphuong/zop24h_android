package vn.fiosoft.zop.xml;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlSerializer;

import vn.fiosoft.zop.dto.AccountDTO;

import android.util.Xml;

public class AccountXML {
	private static final String TAG_ACCOUNT = "account";
	private static final String TAG_ID = "id";
	private static final String TAG_USERNAME = "username";
	private static final String TAG_PASSWORD = "passoword";

	public AccountXML() {

	}

	public AccountDTO parseXMLToAccount(String xml) {

		AccountDTO account = null;

		if (xml == null || xml.equals(""))
			return account;

		try {
			// create a XMLReader from SAXParser
			XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
					.getXMLReader();
			// create a SAXXMLHandler
			SAXXMLHandler saxHandler = new SAXXMLHandler();
			// store handler in XMLReader
			xmlReader.setContentHandler(saxHandler);
			// the process starts
			xmlReader.parse(new InputSource(new StringReader(xml)));
			// get the `Employee list`
			account = saxHandler.getAccount();

		} catch (Exception ex) {
			return null;
		}

		// return Employee list
		if (account != null && account.getId() == 0)
			return null;
		return account;

	}

	public String parseAccountToXML(AccountDTO account) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", TAG_ACCOUNT);

			serializer.startTag("", TAG_ID);
			serializer.text(String.valueOf(account.getId()));
			serializer.endTag("", TAG_ID);
			serializer.startTag("", TAG_USERNAME);
			serializer.text(account.getUserName());
			serializer.endTag("", TAG_USERNAME);
			serializer.startTag("", TAG_PASSWORD);
			serializer.text(account.getPassword());
			serializer.endTag("", TAG_PASSWORD);

			serializer.endTag("", TAG_ACCOUNT);

			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			return "";
		}
	}

	class SAXXMLHandler extends DefaultHandler {

		private AccountDTO account;
		private String tempVal;

		public SAXXMLHandler() {
		}

		public AccountDTO getAccount() {
			return account;
		}

		// Event Handlers
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// reset
			tempVal = "";
			if (qName.equalsIgnoreCase(TAG_ACCOUNT)) {
				account = new AccountDTO();
			}
		}

		public void characters(char[] ch, int start, int length)
				throws SAXException {
			tempVal = new String(ch, start, length);
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if (qName.equalsIgnoreCase(TAG_ID)) {
				account.setId(Integer.parseInt(tempVal));
			} else if (qName.equalsIgnoreCase(TAG_USERNAME)) {
				account.setUserName(tempVal);
			} else if (qName.equalsIgnoreCase(TAG_PASSWORD)) {
				account.setPassword(tempVal);
			}
		}
	}

}
