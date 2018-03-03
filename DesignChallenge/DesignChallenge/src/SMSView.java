
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import values.AppStrings;

public class SMSView extends JFrame{
	
	private static int appIDTracker = 0;
	private final String newLine = "\n********************************\n";
	
	private int appID;
	private int smsNo;
	
	protected JButton btnClear = null;
	protected JTextPane paneFeed = null;
	
	protected StyleContext sc = null;
	protected DefaultStyledDocument doc = null;
	
	public SMSView() {				
		appID = ++appIDTracker;
		smsNo = 0;
		
		setTitle("Facebook App #" + appID);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //set the behavior of the window when the X icon is clicked
		
		initScreen(); //add components into your window
		
		setSize(400, 600); //set the size of the window by giving the width and height respectively
		
		setVisible(true); //makes the window visible
	}

	private void initScreen() {		
		sc = new StyleContext();
	    doc = new DefaultStyledDocument(sc);
		paneFeed = new JTextPane(doc);
	    
	    try {
			doc.insertString(0, new String(AppStrings.NOEVENTS+""), null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    getContentPane().add(new JScrollPane(paneFeed));
	}
	
	public void sendSMS(SMS newSMS) {
		if(smsNo == 0)
			paneFeed.setText("");
		
		smsNo++;
	    Style smsStyle = sc.addStyle("SMS"+smsNo, null);
	    smsStyle.addAttribute(StyleConstants.Foreground, newSMS.getColor());
	    smsStyle.addAttribute(StyleConstants.FontSize, new Integer(16));
	    smsStyle.addAttribute(StyleConstants.FontFamily, "serif");
	    
		try {
			doc.insertString(doc.getLength(), newSMS.toString()+newLine, smsStyle);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
