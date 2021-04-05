package homework1;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * A JDailog GUI for choosing a GeoSegemnt and adding it to the route shown
 * by RoutDirectionGUI.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class GeoSegmentsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	/**
	 *  the RouteDirectionsGUI that this JDialog was opened from
	 */
	private RouteFormatterGUI parent;
	
	/**
	 * lstSegments -  a control contained in this 
	 */
	private JList<GeoSegment> lstSegments;
	
	/**
	 * Creates a new GeoSegmentsDialog JDialog.
	 * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame
	 * 			owner and parent pnlParent
	 * @param owner - the Frame of the father window. 
	 * @param pnlParent - the class which is in the father GUI .
	 */
	public GeoSegmentsDialog(Frame owner, RouteFormatterGUI pnlParent) {
		// create a modal JDialog with the an owner Frame (a modal window
		// in one that doesn't allow other windows to be active at the
		// same time).
		super(owner, "Please choose a GeoSegment", true);
		this.parent = pnlParent;
		// creates Jlist to hold all the given segments .
		// creates two buttons and scroller+txt plain in the dialog window 
		
		lstSegments = new JList<GeoSegment>(ExampleGeoSegments.segments);
		lstSegments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton addBtn = new JButton ("Add");
        JButton cancelBtn = new JButton ("Cancel");
		JPanel panel = new JPanel();
		// cancel button handler 
        cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                setVisible(false);
			}
		});
        // add button handler
        addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int index = lstSegments.getSelectedIndex();
			    pnlParent.addSegment(ExampleGeoSegments.segments[index]);
			}
		});

		JScrollPane scrlSegments = new JScrollPane(lstSegments);
		scrlSegments.setPreferredSize(new Dimension(450, 100));
		JLabel lblSegments = new JLabel("Geo Segments:");
		lblSegments.setLabelFor(lstSegments);
		// dialog window constraints .
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(gridbag);		
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		gridbag.setConstraints(lblSegments, c);
		panel.add(lblSegments);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		gridbag.setConstraints(scrlSegments, c);
		panel.add(scrlSegments);
		
		c.gridx = 18 ;
		c.gridy = 18;
		gridbag.setConstraints(cancelBtn, c);
		panel.add(cancelBtn);
	
		c.gridx = 0 ;
		c.gridy = 18;
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,400);
		gridbag.setConstraints(addBtn, c);
		panel.add(addBtn);
		
		this.add(panel);
	}
}


