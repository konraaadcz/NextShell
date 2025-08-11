package main.java.net.nextshell.panel;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import main.java.net.nextshell.assets.Version;
import main.java.net.nextshell.assets.Update;

public class Panel extends JPanel {


    private JTextArea terminalArea;
    private JTextField inputField;
    private File currentFolder;




    public Panel() {


        setLayout(new BorderLayout());


        setBackground(Color.BLACK);

        currentFolder = new File(".").getAbsoluteFile();

        terminalArea = new JTextArea();
        terminalArea.setBackground(Color.BLACK);
        terminalArea.setForeground(Color.WHITE);
        terminalArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        terminalArea.setCaretColor(Color.WHITE);
        terminalArea.setEditable(false);




        JScrollPane scrollPane = new JScrollPane(terminalArea);
        scrollPane.setBackground(Color.BLACK);
        add(scrollPane, BorderLayout.CENTER);




        inputField = new JTextField();
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.GREEN);
        inputField.setCaretColor(Color.GREEN);
        inputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        inputField.setText("Type your command here...");
        inputField.setForeground(Color.GREEN);




        inputField.addActionListener(e -> executeCommand(inputField.getText()));


        add(inputField, BorderLayout.SOUTH);


        initializeTerminal();
    }



    private void initializeTerminal() {
        terminalArea.setText("NextShell Terminal\n"
                + "Copyright (C) konraaadcz. All rights reserved.\n\n"
                + "Install the latest NextShell for new features and improvements! \n\n"
                + "FOLDER " + currentFolder.getAbsolutePath() + "\n\n");
    }



    private void executeCommand(String command) {
        if (command.trim().isEmpty()) return;



        terminalArea.append("\n> " + command + "\n");
        terminalArea.setCaretPosition(terminalArea.getDocument().getLength());



        try {

            if (command.equals("nextshell --version") || command.equals("nextshell --v")) {
                String version = Version.getVersion();
                terminalArea.append("NextShell Version: " + version + "\n");
            } else if (command.equals("nextshell --docs")) {


                terminalArea.append("The projects docs:  https://github.com/konraaadcz/NextShell/blob/main/README.md");



            } else if (command.equals("nextshell --updates")) {
                String updates = Update.getUpdates();



                if (updates.isEmpty()) {
                    terminalArea.append("No updates found.\n");
                } else {
                    terminalArea.append("Updates:\n" + updates);
                }



            } else {



                ProcessBuilder builder = new ProcessBuilder("powershell", "-Command", "cd \"" + currentFolder.getAbsolutePath() + "\"; " + command);
                builder.redirectErrorStream(true);
                Process process = builder.start();



                BufferedReader rr = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                StringBuilder ou = new StringBuilder();




                while ((line = rr.readLine()) != null) {
                    ou.append(line).append("\n");
                }



                process.waitFor();
                terminalArea.append(ou.toString() + "\n");
                terminalArea.setCaretPosition(terminalArea.getDocument().getLength());
                inputField.setText("");



            }




        } catch (Exception xo) {
            terminalArea.append("\nError: " + xo.getMessage() + "\n");
            terminalArea.setForeground(Color.RED);
            terminalArea.setCaretPosition(terminalArea.getDocument().getLength());
            inputField.setText("");


        }
    }
}