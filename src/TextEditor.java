import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    // MenuBar
    JMenuBar menuBar;
    // Menu
    JMenu file,edit;
    // MenuItem
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut,copy,past,selectAll,close;
    JTextArea textArea;
    public TextEditor(){
        // initialize jframe window
        frame = new JFrame();
        // initialize menuItem for file
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        // Add to ActionListener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // initialize menuItem for Edit
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        past = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // Add to ActionLister
        cut.addActionListener(this);
        copy.addActionListener(this);
        past.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // add menu item for file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // add menu item for edit
        edit.add(cut);
        edit.add(copy);
        edit.add(past);
        edit.add(selectAll);
        edit.add(close);
        // initialize menuBar
        menuBar = new JMenuBar();
        // Add Menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        // intialize text area
        textArea = new JTextArea();
        // adding MenuBar to Jframe
        frame.setJMenuBar(menuBar);
        // add panel to have scroll pane and text area
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.setLayout(new BorderLayout(0,0));
        // add textArea to panel
        jPanel.add(textArea, BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scrollPane to panel
        jPanel.add(scrollPane);
        // add panel to fram
        frame.add(jPanel);
        // make frame visible
        frame.setBounds(100,100,600,600);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cut){
            // cut the text
            textArea.cut();
        }
        if(e.getSource() == copy){
            // copy the text
            textArea.copy();
        }
        if(e.getSource() == past){
            // paste the text into textArea
            textArea.paste();
        }
        if(e.getSource() == selectAll){
            //select All text area
            textArea.selectAll();
        }
        if(e.getSource() == close){
            // exit from the console with exit code 0
            System.exit(0);
        }
        // open file Action
        if(e.getSource() == openFile){
            // initialize file picker to select the file
            JFileChooser fileChooser = new JFileChooser("C");
            // store the option selected
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                // read the file
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "",output = "";
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    // add output to text area
                    textArea.setText(output);
                    bufferedReader.close();
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(e.getSource() == saveFile){
            // initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // initialize fileWriter
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize bufferWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // add text from textArea
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
        if(e.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}