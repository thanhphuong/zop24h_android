package vn.fiosoft.zop.data;

import vn.fiosoft.zop.util.FileManager;
import android.content.Context;

public class AccountStorage {

	private static final String FILE_ACCOUNT = "account.xml";
	private Context mContext;

	public AccountStorage(Context context) {
		mContext = context;
	}

	public boolean saveAccount(Account account) {		
		FileManager fileManager = new FileManager();
		AccountXML accountXML = new AccountXML();

		String xml = accountXML.parseAccountToXML(account);

		return fileManager.writeToFile(mContext, FILE_ACCOUNT, xml);
	}
	
	public Account getAccount(){
		FileManager fileManager = new FileManager();
		AccountXML accountXML = new AccountXML();
		
		String xml = fileManager.readFromFile(mContext, FILE_ACCOUNT);
		
		return accountXML.parseXMLToAccount(xml);		
	}

}
