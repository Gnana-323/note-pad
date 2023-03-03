import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

class Note extends JFrame implements ActionListener{
    //gui part
    JFrame f;
    //textarea declaration
    JTextArea t;


    //constructor
    Note(){
        //initialisation
       f=new JFrame("NotePad");
       t=new JTextArea();

       //create menu-bar
        JMenuBar menu=new JMenuBar();
        //creating file menu
        JMenu file=new JMenu("file");

        //creating jmenuitems for file
        JMenuItem f1=new JMenuItem("New");
        JMenuItem f2=new JMenuItem("Save");
        JMenuItem f3=new JMenuItem("Open");
        JMenuItem f4=new JMenuItem("Print");

        //adding actionlisteners to each file options
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding options to file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);


        //creating edit  menu
        JMenu edit=new JMenu("Edit");
        //create edit menuItems
        JMenuItem e1=new JMenuItem("Cut");
        JMenuItem e2=new JMenuItem("Copy");
        JMenuItem e3=new JMenuItem("Paste");

        //adding actionlisteners to edit options
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        //add optiond to edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        //creating close as jmenuitem
        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);


        //add menus to menubar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //add menubar to frame
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1280,720);
        f.setVisible(true);
    }







    //to add functionalities
    public void actionPerformed(ActionEvent e){
        //
        String s=e.getActionCommand();

        switch(s){
            case "New":
                t.setText("");
                break;
            case "Save":
                //create the objective of filechooser with starting a starting path
                JFileChooser j=new JFileChooser("");

                //invoke savedialogue
                int r=j.showSaveDialog(null);
                //r contains a file object and staore file location
                if(r==0){
                  //declare file object and file location
                  File fi=new File(j.getSelectedFile().getAbsolutePath());

                    FileWriter fw= null;
                    try {
                        fw = new FileWriter(fi);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(t.getText());
                        bw.flush();
                        bw.close();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(f,"The user has cancelled the operation");
                }
                break;
            case "Open":
                //create the objective of filechooser with starting a starting path
                JFileChooser jo=new JFileChooser("");

                //invoke Openialogue
                int ro=jo.showOpenDialog(null);
                //ro contains a file object and staore file location
                if(ro==0){
                    //declare file object and file location
                    File fi=new File(jo.getSelectedFile().getAbsolutePath());

                    try {
                        FileReader fw= new FileReader(fi);
                        BufferedReader bw=new BufferedReader(fw);


                    } catch (IOException ex) {
                         throw new RuntimeException(ex);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(f,"The user has cancelled the operation");
                }

                break;
            case "Print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;

        }
    }


    public static void main(String[] args){
        Note n=new Note();

    }
}