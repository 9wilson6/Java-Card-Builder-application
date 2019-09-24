package com.FlashCardBuilder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class FlashCardBuilder {
	private JTextArea question, answer;
	private ArrayList<FlashCard> cardList;
	private JFrame frame;
 public FlashCardBuilder() {
	 frame= new JFrame("Flash Card");
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 //create a jpanel to hold everything
	 JPanel mainPanel = new JPanel();
	 //Create font
	 //Font greatFont= new Font("Helvetica Neue", Font.BOLD, 21);
	 Font greatFont = new Font("Garamond", Font.BOLD, 21);
	 cardList = new ArrayList<FlashCard>();
	 //Question Area
	 question = new JTextArea(6,18);
	 question.setLineWrap(true);
	 question.setWrapStyleWord(true);
	 question.setFont(greatFont);
	 
	 //JscrollPane for question
	 JScrollPane qscrollPane= new JScrollPane(question);
	 qscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	 qscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	//Answer Area
	 
	 answer = new JTextArea(6,18);
	 answer.setLineWrap(true);
	 answer.setWrapStyleWord(true);
	 answer.setFont(greatFont);
	 
	 
	 //JscrollPane for answer
	 JScrollPane ascrollPane= new JScrollPane(answer);
	 ascrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	 ascrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	 
	 JButton nextButton = new JButton("Next Card");
	 
	 //create several labels
	 JLabel qJlabel = new JLabel("Question");
	 JLabel aJlabel= new JLabel("Answer");
	 
	 
	 //Add components to main panel
	 mainPanel.add(qJlabel);
	 mainPanel.add(qscrollPane);
	 mainPanel.add(aJlabel);
	 mainPanel.add(ascrollPane);
	 mainPanel.add(nextButton);
	 nextButton.addActionListener(new NextCardListener());
	 
	 //Menubar
	 JMenuBar menuBar= new JMenuBar();
	 JMenu menu= new JMenu("File");
	 JMenuItem newFileMenu= new JMenuItem("New");
	 JMenuItem saveFileMenu= new JMenuItem("Save");
	 menu.add(newFileMenu);
	 menu.add(saveFileMenu);
	 menuBar.add(menu);
	 
	 //set menubar to frame
	 frame.setJMenuBar(menuBar);
	 
	 //addEventListeners
	 newFileMenu.addActionListener(new MenuItemListener());
	 saveFileMenu.addActionListener(new SaveItemListener());
	 //add to frame
	 frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
	 
	 frame.setSize(400, 500);
	 frame.setLocationRelativeTo(null);
	 frame.setVisible(true);
	 
	
 }
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new FlashCardBuilder();
			}
		});
	}
class NextCardListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	FlashCard card = new FlashCard(question.getText(), answer.getText());
		cardList.add(card);
		clearCard();
	}}
class MenuItemListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("You chose to create a new file");
		
	}
	
}

class SaveItemListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		FlashCard card = new FlashCard(question.getText(), answer.getText());
		cardList.add(card);
		
		//file save dialog with file chooser
		JFileChooser fileSave= new JFileChooser();
		
		fileSave.showSaveDialog(frame);
		saveFile(fileSave.getSelectedFile());
	}
	
}
private void saveFile(File file) {
	try {
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	Iterator<FlashCard> it= cardList.iterator();
	while(it.hasNext()) {
		FlashCard card = (FlashCard)it.next();
		bw.write(card.getQuestion()+ "/");
		bw.write(card.getAnswer()+ "\n");
	}
	bw.close();
	} catch (Exception e) {
		System.out.println("Could not print to file");
	}
}
private void clearCard() {
	question.setText("");
	answer.setText("");
	question.requestFocus();
}
}
