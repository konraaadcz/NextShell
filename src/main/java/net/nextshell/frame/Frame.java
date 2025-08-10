package main.java.net.nextshell.frame;

import main.java.net.nextshell.panel.Panel;
import javax.swing.*;
import java.awt.*;
import main.java.net.nextshell.routes.AS;
import java.awt.event.*;


public class Frame extends JFrame {



    private JTabbedPane tabbedPane;
    private AS autoShutdown;




    public Frame() {




        setTitle("NextShell Terminal");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);




        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.BLACK);
        tabbedPane.setForeground(Color.WHITE);
        UIManager.put("TabbedPane.contentAreaColor", Color.BLACK);
        UIManager.put("TabbedPane.background", Color.BLACK);
        UIManager.put("TabbedPane.shadow", Color.BLACK);
        add(tabbedPane, BorderLayout.CENTER);




        addNewTerminalTab();

        autoShutdown = new AS(this);
        autoShutdown.registerActivityListener();




        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);




        JButton newTabButton = new JButton("+");
        newTabButton.setForeground(Color.WHITE);
        newTabButton.setBackground(Color.DARK_GRAY);
        newTabButton.setFocusPainted(false);
        newTabButton.addActionListener(e -> addNewTerminalTab());
        buttonPanel.add(newTabButton);






        JButton fullscreenButton = new JButton("⬜");
        fullscreenButton.setForeground(Color.WHITE);
        fullscreenButton.setBackground(Color.DARK_GRAY);
        fullscreenButton.setFocusPainted(false);
        fullscreenButton.addActionListener(new ActionListener() {



            private boolean fullscreen = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fullscreen) {
                    setExtendedState(JFrame.NORMAL);
                } else {
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                fullscreen = !fullscreen;
            }
        });




        buttonPanel.add(fullscreenButton);




        JButton closeButton = new JButton("X");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.RED);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(closeButton);







        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);





        JMenuItem newTerminalItem = new JMenuItem("New Terminal");
        newTerminalItem.addActionListener(e -> addNewTerminalTab());
        fileMenu.add(newTerminalItem);




        JMenuItem closeTerminalItem = new JMenuItem("Close Terminal");
        closeTerminalItem.addActionListener(e -> closeCurrentTab());
        fileMenu.add(closeTerminalItem);





        JMenuItem newWindowItem = new JMenuItem("New Window");
        newWindowItem.addActionListener(e -> openNewWindow());
        fileMenu.add(newWindowItem);






        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);






        JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(Color.WHITE);
        JMenuItem docsItem = new JMenuItem("Docs ↗");
        docsItem.addActionListener(e -> openURL("https://github.com/konraaadcz/NextShell/blob/main/README.md"));
        helpMenu.add(docsItem);





        JMenuItem repoItem = new JMenuItem("Repo ↗");
        repoItem.addActionListener(e -> openURL("https://github.com/konraaadcz/NextShell"));
        helpMenu.add(repoItem);




        JMenu licenseMenu = new JMenu("License");
        licenseMenu.setForeground(Color.WHITE);
        JMenuItem licenseItem = new JMenuItem("License ↗");
        licenseItem.addActionListener(e -> openURL("https://github.com/konraaadcz/NextShell/blob/main/LICENSE"));
        licenseMenu.add(licenseItem);



        JMenu problemsMenu = new JMenu("Problemes");
        problemsMenu.setForeground(Color.WHITE);
        JMenuItem problemsItem = new JMenuItem("Report ↗");
        problemsItem.addActionListener(e -> openURL("https://github.com/konraaadcz/NextShell/issues"));
        problemsMenu.add(problemsItem);




        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(licenseMenu);
        menuBar.add(problemsMenu);





        topBar.add(menuBar, BorderLayout.WEST);
        topBar.add(buttonPanel, BorderLayout.EAST);



        add(topBar, BorderLayout.NORTH);
    }




    private void addNewTerminalTab() {

        Panel terminalPanel = new Panel();
        tabbedPane.addTab("Terminal", terminalPanel);
        tabbedPane.setBackground(Color.BLACK);
    }

    private void closeCurrentTab() {

        int currentTabIndex = tabbedPane.getSelectedIndex();
        if (currentTabIndex >= 0) {
            tabbedPane.remove(currentTabIndex);

        }
    }

    private void openNewWindow() {


        SwingUtilities.invokeLater(() -> {
            Frame newFrame = new Frame();
            newFrame.setVisible(true);


        });
    }

    private void openURL(String url) {

        try {

            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception fes) {

            fes.printStackTrace();
        }
    }
}
