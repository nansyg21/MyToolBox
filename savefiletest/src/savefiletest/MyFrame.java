package savefiletest;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class MyFrame extends JFrame{
	JButton bt;
	JPanel panel;
	myButtonListener bl;

	public MyFrame()
	{
		//Window components
		bt=new JButton("Hit Save");
		bl=new myButtonListener();
		bt.addActionListener(bl);
		panel=new JPanel();
		panel.add(bt);
		
		this.setContentPane(panel);
		this.setSize(200, 200);
		this.setVisible(true);
	}
	
	//Open save window when button is clicked
	public class myButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// Create a file chooser and override approveSelection method to show confirmation dialog
			JFileChooser example = new JFileChooser(){
			    @Override
			    public void approveSelection(){
			    	//Write the file name without extension (this only works with txt files)
			    	//Then add extension programmatically
			        File f = new File(getSelectedFile()+".txt");
			        //If the file with the extension exists and the user hits save show Confirm dialog box
			        if(f.exists() && getDialogType() == SAVE_DIALOG){
			        	//Setup confirm dialog box and operations according to user choise
			            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
			            switch(result){
			                case JOptionPane.YES_OPTION:
			                    super.approveSelection();
			                    return;
			                case JOptionPane.NO_OPTION:
			                    return;
			                case JOptionPane.CLOSED_OPTION:
			                    return;
			                case JOptionPane.CANCEL_OPTION:
			                    cancelSelection();
			                    return;
			            }
			        }
			        super.approveSelection();
			    }        
			};
			
			//Set a default directory for save (path has to change to work on another PC if disk letter differs)
			//Create a dummy file and save it with the name the user typed and the txt extension
			example.setCurrentDirectory(new File("C:/Users"));
		    int retrival = example.showSaveDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            FileWriter fw = new FileWriter(example.getSelectedFile()+".txt");
		            fw.write("nasia");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
			
		}
		
	}
}
