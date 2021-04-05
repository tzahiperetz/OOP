package homework1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A JPanel GUI for representing a Route. This Route is shown as a list of
 * GeoSegments. In addition, walking directions and driving directions for
 * traversing this route are shown.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class RouteFormatterGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 *  Route shown in this
	 */
	private Route route = null;				
	/**
	 *  secondary window
	 */
	private GeoSegmentsDialog dlgSegments;	

	/**
	 *  some of the controls contained in this
	 */
	private JList<GeoSegment> lstSegments;
	/**
	 * txtArea which would hold the walking dirctions description 
	 */
	private JTextArea txtWalkingDirections;
	/**
	 * txtArea which would hold the driving directions description
	 */
	private JTextArea txtDrivingDirections;


	/**
	 * Creates a new RoutFormatterGUI JPanel.
	 * @effects Creates a new RoutFormatterGUI JPanel contained in frame.
	 * @param frame - instance of Jframe , which used for the GUI formatter main window .
	 */
	public RouteFormatterGUI(JFrame frame) {
		// create a GeoSegmentsDialog (secondary window)
		dlgSegments = new GeoSegmentsDialog(frame, this);
		dlgSegments.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dlgSegments.pack();

		// create components
		lstSegments = new JList<>(new DefaultListModel<GeoSegment>());
		lstSegments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrlSegments = new JScrollPane(lstSegments);
		scrlSegments.setPreferredSize(new Dimension(450, 100));
		JLabel lblSegments = new JLabel("Route's GeoSegments:");
		lblSegments.setLabelFor(lstSegments);

		txtWalkingDirections = new JTextArea();
		txtWalkingDirections.setEditable(false);
		JScrollPane scrlWalkingDirections = new JScrollPane(txtWalkingDirections);
		scrlWalkingDirections.setPreferredSize(new Dimension(400, 70));
		
		JLabel lblWalkingDirections = new JLabel("Walking Directions:");
		lblWalkingDirections.setLabelFor(txtWalkingDirections);

		txtDrivingDirections = new JTextArea();
		txtDrivingDirections.setEditable(false);
		JScrollPane scrlDrivingDirections = new JScrollPane(txtDrivingDirections);
		scrlDrivingDirections.setPreferredSize(new Dimension(400, 70));
		JLabel lblDrivingDirections = new JLabel("Driving Directions:");
		lblDrivingDirections.setLabelFor(txtDrivingDirections);

		JButton btnAddSegment = new JButton("Add GeoSegment");
		btnAddSegment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgSegments.setVisible(true);
			}
		});

		// arrange components on grid
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,0,0,0);
		gridbag.setConstraints(lblSegments, c);
		this.add(lblSegments);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0,0,0,0);
		gridbag.setConstraints(scrlSegments, c);
		this.add(scrlSegments);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lblWalkingDirections, c);
		this.add(lblWalkingDirections);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(scrlWalkingDirections, c);
		this.add(scrlWalkingDirections);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lblDrivingDirections, c);
		this.add(lblDrivingDirections);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(scrlDrivingDirections, c);
		this.add(scrlDrivingDirections);

		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		c.anchor = GridBagConstraints.SOUTH;
		gridbag.setConstraints(btnAddSegment, c);
		this.add(btnAddSegment);
	}


	/**
	 * Add new segment to the Route shown by this.
	 * @requires segments != null &amp;&amp; segment.p1 == this.route.end
	 * @effects Add new segment to the end of the route shown by this as
	 * 			defined by Route.addSegment(). In addition, updates the
	 * 			walking direction and the driving direction
	 *  of the GUI
	 * 			with the return value of
	 * 			RouteDirection.computeDirections(this.route,0)
	 * @param segment - the segment which we are going to add to the Route.
	 */
	public void addSegment(GeoSegment segment) {
		DefaultListModel<GeoSegment> model =
				(DefaultListModel<GeoSegment>)(this.lstSegments.getModel());
		
		WalkingRouteFormatter wFormmater = new WalkingRouteFormatter() ;
		DrivingRouteFormatter dForammaer = new DrivingRouteFormatter() ; 
		//this.txtWalkingDirections.setEditable(true);
		// when we add the first segment to the route - call to Route(gs) constructor
		if (this.route == null ) {
			this.route = new Route(segment);
			model.addElement(segment);
			this.txtWalkingDirections.append(wFormmater.computeDirections(this.route, 0));
			this.txtDrivingDirections.append(dForammaer.computeDirections(this.route, 0));
		}
		// if Route isn't empty - call addSegment .
		else {
			if (! this.route.getEnd().equals(segment.getP1())){
				System.out.print("Not possible to add "+ segment.toString() +"segment to the Route .\n");
				return ; 
			}
			this.route = this.route.addSegment(segment);
			model.addElement(segment);
			this.txtWalkingDirections.setText(wFormmater.computeDirections(this.route, 0));
			this.txtDrivingDirections.setText(dForammaer.computeDirections(this.route, 0));
		}
	}
/**
 * main function 
 * @param args  - arguments of the main 
 */
    public static void main(String[] args) {
		JFrame frame = new JFrame("Route Formatter GUI");
		Container contentPane = frame.getContentPane();
		contentPane.add(new RouteFormatterGUI(frame));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
    }
}
