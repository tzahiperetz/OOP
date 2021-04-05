package Q2;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextArea;
//import javax.swing.BoxLayout;
//import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
//import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

// each GUI functions as a subject when he notify all clients that msg sent
// or function as Observer , when he get message from other GUIs .

/** representation invariant:
 * all the fields of the GUI is not nulls.
 * in particular all the element in the observers list aren't null.
 *
 */

 /** abstraction function:
 * 
 * observers - is the list of other members in the chat app.
 * userText - the window where the user write his message before sending it to all others chat members.
 * groupText - is the main chat window where each user can see the total chat conversation between all the chat members.
 * name - the name of the chat user.
 * userFont - the font that the user use in his chat frame.
 */

public class GUI extends JFrame {
	/** serial UID  */
	private static final long serialVersionUID = 1L;
	/** each GUI would hold list of its observers  */
	private List<GUI> observers = new ArrayList<GUI>() ;
	/** GUI panel */
	private JPanel contentPane;
	/** User Text Box  */
	public JTextField userText;
	/** Group Text Box */
	public JTextArea groupText ; 
	/** Name of the person who uses the chat  */
	public String name;
	/** User Font  */
	public Font userFont;

/**
 * 
 * @param args - .
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI student1 = new GUI("student1");
					GUI student2 = new GUI("student2");
					GUI student3 = new GUI("student3");
					student1.setVisible(true);
					student2.setVisible(true);
					student3.setVisible(true);
					
					student1.attach(student2);
					student1.attach(student3);
					
					student2.attach(student1);
					student2.attach(student3);
					
					student3.attach(student1);
					student3.attach(student2);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

/**
 * 
 * @param name of person who uses the char
 */
	public GUI(String name) {
		
		this.name = name;
		this.userFont = new Font("Default", Font.PLAIN,13);
		
		setDefaultCloseOperation(ending());
		setBounds(100, 100, 583, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.groupText = new JTextArea();
		
		this.userText = new JTextField();
		userText.setColumns(10);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkrep();
				groupText.append(name + " : " + userText.getText() + "\n");
				notiftAllObservers();
				userText.setText("");
				checkrep();
			}
		});
		
		JButton regularFontButton = new JButton("Regular Font");
		regularFontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// regular font action
				checkrep();
				Font font = new Font("Default", Font.PLAIN,13);
				userFont = font;
				userText.setFont(userFont);
				groupText.setFont(userFont);
				checkrep();
			}
		});
		
		JButton boldFontButton = new JButton("Bold Font");
		boldFontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bold font action
				checkrep();
				Font font = new Font("Courier", Font.BOLD,13);
				userFont = font;
				userText.setFont(userFont);
				groupText.setFont(userFont);
				checkrep();
			}
		});
		
		JButton otherFontButton = new JButton("Other Font");
		otherFontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//other font action 
				checkrep();
				Font font = new Font("Courier", Font.PLAIN,20);
				userFont = font;
				userText.setFont(userFont);
				groupText.setFont(userFont);
				checkrep();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(regularFontButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(boldFontButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(otherFontButton))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(userText)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(enterButton))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(6)
								.addComponent(groupText, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(groupText, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userText, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(enterButton))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(regularFontButton)
						.addComponent(boldFontButton)
						.addComponent(otherFontButton))
					.addGap(16))
		);
		contentPane.setLayout(gl_contentPane);
		checkrep();
	}
	/**
	 * 
	 * @param subject to update
	 */
	public void update(GUI subject) {
		checkrep();
		this.groupText.append(subject.name + " : " + subject.userText.getText() + "\n");
		checkrep();
	}
/**
 * 
 * @param observer - which observer to detach
 */
	public void attach(GUI observer) {
		checkrep();
		observers.add(observer);
		checkrep();
	}
	/**
	 * 
	 * @param observer - which observer to detach
	 */
	public void detach(GUI observer) {
		checkrep();
		observers.remove(observer);
		checkrep();
	}
	/**
	 * 
	 */
	public void notiftAllObservers() {
		checkrep();
		for (GUI g : observers ) {
			g.update(this); 
		}
		checkrep();
	}
	/**
	 * 
	 * @return DISPOSE_ON_CLOSE integer
	 */
	public int ending() {
		checkrep();
		for (GUI g : observers ) {
			g.detach(this); 
		}
		checkrep();
		return JFrame.DISPOSE_ON_CLOSE;
	}
	/**
	 * 
	 */
	public void checkrep() {
		assert(this.name == null) : "no name for the user!";
		assert(this.groupText == null) : "group text window is a null!";
		assert(this.userFont == null) : "the font of the user is a null!";
		assert(this.userText == null) : "user text window is a null!";
		assert(this.contentPane == null) : "contentPane is a null!";
		assert(this.observers == null) : "do not have a list of observers!";
		for (GUI g : this.observers) {
			assert(g == null) : "the observer is a null!";
		}
	}
	
}