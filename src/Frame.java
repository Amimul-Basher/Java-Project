import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

class Frame extends JFrame implements ActionListener, KeyListener {

    /*ImageIcon img = new ImageIcon("game main wallpaper.jpg");
    JLabel label = new JLabel(img);*/

    static int count = 0;
    static int game_start = 0;
    static int foundx = 0;
    static int foundy = 0;
    String[] xyString;
    //String[] toCheck;
    String xString;
    String yString;
    String subStringX = "", subStringY = "";

    JPanel panelMain = new JPanel();
    JPanel panelFront = new JPanel();
    JPanel panelMenu = new JPanel();
    JPanel panelStart = new JPanel();
    JPanel panelHighScore = new JPanel();
    JPanel panelHelp = new JPanel();
    JPanel panelExit = new JPanel();
    JPanel panelGame = new JPanel();

    //panelFront variables
    CardLayout cl = new CardLayout();
    JButton start, highScore, help, exit;
    JPanel paneldesign1 = new JPanel();
    JPanel panelDesign2 = new JPanel();

    //panelStart variables
    JLabel pName1 = new JLabel("Player1 Name: ");
    JLabel pName2 = new JLabel("Player2 Name: ");
    JTextField playerName1 = new JTextField("TypeName");

    String p1NameInput = "Basher";
    JTextField playerName2 = new JTextField("TypeName");
    String p2NameInput = "Afroz";
    JButton startNext = new JButton("Next");
    JButton startPrev = new JButton("Previous");

    //panelHighScore variables
    JButton hsPrev = new JButton("Previous");

    //panelHelp variables
    JLabel title = new JLabel("Game Instruction");
    JTextArea instruction = new JTextArea("");
    JButton helpPrev = new JButton("Previous");

    //panelGame variables
    JLabel p1Name = new JLabel("Player 1");
    JLabel p2Name = new JLabel("Player 2");
    JTextField p1Score = new JTextField("0");
    JTextField p2Score = new JTextField("0");
    JPanel panel1 = new JPanel();  //for player score section
    JPanel panel2 = new JPanel();  //for player input section
    JPanel panel3 = new JPanel();  //for player's turn and to hold replay option
    JButton replay= new JButton("Replay");
    GridBagConstraints c = new GridBagConstraints();
    static JTextField[][] input = new JTextField[10][10];
    JLabel playerTurn = new JLabel();
    String turn = "type Name";
    JButton confirm = new JButton("Confirm");




    //Fontso
    Font bigFont = new Font("serif",Font.ITALIC, 25);
    Font smallFont = new Font("Serif", Font.ITALIC, 15);
    Font lFont = new Font("Arial", Font.BOLD, 30);
    Font sFont = new Font("Arial", Font.BOLD, 20);

    public Frame() {                                            //Constructor of frame
        gui();

    }

    public void textFields(GridBagConstraints c){
        c.insets = new Insets(1, 1, 1, 1);
        c.gridx = 0;
        c.gridy = 0;
        for(int i= 0; i < 10; i++){
            c.gridx = 0;
            for(int j = 0; j < 10; j++){
                input[i][j] = new JTextField("",1);
                WordGame.letters[i][j] = input[i][j].getText();
                input[i][j].setFont(lFont);
                panel2.add(input[i][j], c);
                c.gridx++;
            }
            c.gridy++;
        }
    }

    public void wheatherIfFound(String fromFile){

    }


    public String toConjugateLetters(int i, int j,  String[][]  letters ){
        String arbitraryX = "";
        String arbitraryY = "";
        String xyString = "";



        int point = 0;
        int x = i;
        int y = j;
        for(int m= 0; m < 10; m++){

            for(int n = 0; n < 10; n++){
                if(letters[m][n].equals(""))
                    System.out.print("_");
                System.out.print(letters[m][n]);
            }
            System.out.println();
        }
        for(j = 0; j < 10; j++){
            if(letters[i][j].equals("") & point == 0){
                arbitraryX = "";
            }
            else if(letters[i][j].equals("") & point == 1){
                break;
            }
            else{
                arbitraryX += letters[i][j];
                if(j == y){
                    point = 1;
                }
            }
        }
        point = 0;
        j = y;
        for(i = 0; i < 10; i++){
            if(letters[i][j].equals("") & point == 0){
                arbitraryY = "";
            }
            else if(letters[i][j].equals("") & point == 1){
                break;
            }
            else{
                arbitraryY += letters[i][j];
                if(i == x){
                    point = 1;
                }
            }
        }

        System.out.println(arbitraryX + " " + arbitraryY);
        xyString = arbitraryX + " " + arbitraryY;

        return xyString;
    }



    public void gui() {
        setVisible(true);
        setBounds(500, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.lightGray);
        add(panelMain);
        panelMain.setLayout(cl);


        panelMain.add(panelFront, "1");
        panelMain.add(panelStart, "2");
        panelMain.add(panelHighScore, "3");
        panelMain.add(panelHelp, "4");
        panelMain.add(panelExit, "5");
        panelMain.add(panelGame,"6");

        cl.show(panelMain, "1");

        //panelMenu decoration
        panelFront.setLayout(null);
        panelFront.add(panelMenu);


        panelMenu.setLayout(new GridBagLayout());
        panelMenu.setBounds(150,0,300, 600);

        panelMenu.setBackground(Color.cyan);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        start = new JButton("Start");
        //start.setBackground(Color.lightGray);
        panelMenu.add(start, gbc);
        start.setFont(bigFont);

        gbc.gridy = 2;
        highScore = new JButton("High Score");
        //highScore.setBackground(Color.lightGray);
        panelMenu.add(highScore, gbc);
        highScore.setFont(bigFont);

        gbc.gridy = 3;
        help = new JButton("Help");
        //help.setBackground(Color.lightGray);
        panelMenu.add(help, gbc);
        help.setFont(bigFont);

        gbc.gridy = 4;
        exit = new JButton("Exit");
        //exit.setBackground(Color.lightGray);
        panelMenu.add(exit, gbc);
        exit.setFont(bigFont);


        //panelStart decoration
        /*panelStart.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelStart.add(pName1, gbc);
        gbc.gridy = 2;
        panelStart.add(playerName1, gbc);
        gbc.gridy = 3;
        panelStart.add(pName2, gbc);
        gbc.gridy = 4;
        panelStart.add(playerName2,gbc);
        */
        panelStart.setLayout(null);
        panelStart.add(pName1);
        pName1.setBounds(200, 50, 200 , 100);
        //textfield
        panelStart.add(playerName1);
        playerName1.setBounds(200,150, 200, 25);
        p1NameInput = playerName1.getText();
        panelStart.add(pName2);
        pName2.setBounds(200, 250, 200 , 100);
        //textfield
        panelStart.add(playerName2);
        playerName2.setBounds(200,350, 200, 25);
        p2NameInput = playerName2.getText();
        //Buttons
        panelStart.add(startNext);
        startNext.setFont(smallFont);
        startNext.setBounds(400, 500, 100, 25);
        panelStart.add(startPrev);
        startPrev.setFont(smallFont);
        startPrev.setBounds(100, 500, 100, 25);



        //panelHishScore decoration
        panelHighScore.setBackground(Color.YELLOW);
        panelHighScore.setLayout(null);
        panelHighScore.add(hsPrev);
        hsPrev.setFont(smallFont);
        hsPrev.setBounds(100,500,100,25);


        //panelHelp decoration
        panelHelp.setBackground(Color.CYAN);
        panelHelp.setLayout(null);
        panelHelp.add(title);
        title.setBounds(220, 50, 200, 30);
        panelHelp.add(instruction);
        instruction.setBounds(150, 100, 300, 300);
        instruction.setText("Its a game between you and your friend." +
                "\nHere you have to make a complete word." +
                "\nYou will have a option to fill a text field" +
                "\nwith a single charecter." +
                "\nEvery time you enter a charecter this should be" +
                "\nthe part of a complete word." +
                "\nYour opponent will have the same option." +
                "\nYou have to catch his word plan and make it" +
                "\nyours if possible." +
                "\nYou have to chose minimum 3 charecters of" +
                "\nword to make score.yellow ");
        panelHelp.add(helpPrev);
        helpPrev.setFont(smallFont);
        helpPrev.setBounds(150, 500, 100, 25);

        //panelGameDecoration
        panelGame.setLayout(null);
        panel1.setBounds(0, 0, 600,70);
        panel1.setBackground(Color.cyan);
        panelGame.add(panel1);
        panel1.setLayout(null);
        panel1.add(p1Name);
        p1Name.setBounds(50,25,100,25);
        panel1.add(p2Name);
        p2Name.setBounds(350,25,100,25);
        panel1.add(p1Score);
        p1Score.setBounds(150,25,50,25);
        p1Score.setFont(sFont);
        panel1.add(p2Score);
        p2Score.setBounds(500,25,50,25);
        p2Score.setFont(sFont);

        panel2.setBounds(0, 75, 600,450);
        panel2.setBackground(Color.LIGHT_GRAY);
        panelGame.add(panel2);
        panel2.setLayout(new GridBagLayout());
        textFields(c);

        panel3.setBounds(0, 530, 600,70);
        panel3.setBackground(Color.cyan);
        panelGame.add(panel3);
        panel3.setLayout(null);

        replay.setBounds(450, 15, 100,30);
        replay.setFont(smallFont);
        panel3.add(replay);
        playerTurn.setBounds(50, 15, 250,35);
        //playerTurn.setFont(smallFont);
        panel3.add(playerTurn);
        confirm.setBounds(300, 15, 100,30);
        confirm.setFont(smallFont);
        panel3.add(confirm);



        //panelExit decoration
        panelExit.setBackground(Color.LIGHT_GRAY);


                                                        //Adding actionListener to buttons
        start.addActionListener(this);
        startPrev.addActionListener(this);
        startNext.addActionListener(this);
        replay.addActionListener(this);
        confirm.addActionListener(this);
        highScore.addActionListener(this);
        hsPrev.addActionListener(this);
        help.addActionListener(this);
        helpPrev.addActionListener(this);
        exit.addActionListener(this);


        playerName1.addKeyListener(this);
        playerName2.addKeyListener(this);


        //pack();
    }

    //Adding keyListener to button

    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
        }
    }
    public void keyReleased(KeyEvent e){

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            cl.show(panelMain, "2");
        }
        if (e.getSource() == startPrev){
            cl.show(panelMain, "1");
        }
        if (e.getSource() == startNext) {
            if((playerName1.getText().equals("TypeName")) | (playerName2.getText().equals("TypeName")) | (playerName1.getText().equals("")) | (playerName2.getText().equals(""))){
                JOptionPane.showMessageDialog(null, "Give input A valid Name for both player", "Invalid Input!", JOptionPane.WARNING_MESSAGE);
            }else{
                cl.show(panelMain, "6");
                p1NameInput = playerName1.getText();
                p2NameInput = playerName2.getText();
                p1Name.setText(p1NameInput);   //panelGame er p1Name label
                p2Name.setText(p2NameInput);
                playerTurn.setText(p1NameInput + "'s turn");
            }

        }
        if(e.getSource() == replay){
            cl.show(panelMain, "2");
            WordGame.scorep1 = 0;
            WordGame.scorep2 = 0;
            p1Score.setText("0");
            p2Score.setText("0");
            for(int i= 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    input[i][j].setText("");
                    input[i][j].setBackground(Color.white);
                    WordGame.letters[i][j] = input[i][j].getText();
                }
            }
            count = 0;
            //textFields(c);      //later this may need to hundle the value of letter
        }
        if(e.getSource() == confirm){
            boolean inputGiven = false;
            int x, y;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(WordGame.letters[i][j].equals(input[i][j].getText())){

                    }else{
                        if(input[i][j].getText().length() > 1){
                            JOptionPane.showMessageDialog(null, "You entered double or more than one charecter","Invalid input",JOptionPane.INFORMATION_MESSAGE);
                            input[i][j].setText("");
                        }else{
                            foundx = 0;
                            foundy = 0;
                            WordGame.letters[i][j]=input[i][j].getText();
                            input[i][j].setBackground(Color.GREEN);
                            System.out.println(WordGame.letters[i][j] + " " + i + " " + j);
                            xyString = toConjugateLetters(i, j, WordGame.letters).split(" ");
                            xString = xyString[0];
                            yString = xyString[1];
                            System.out.println(xString + " " + yString);
                            String line = null;
                            String[] str;
                            try {

                                int xGot = 0;
                                int yGot = 0;
                                File f = new File("Source.txt");
                                FileReader read = new FileReader(f);
                                FileWriter w = new FileWriter("Score.txt");
                                BufferedWriter out = new BufferedWriter(w);
                                BufferedReader in = new BufferedReader(read);
                                str = new String[10];
                                while ((line = in.readLine()) != null) {
                                    str = line.split(" "); //str holds whole line from the file and there will have words separated by space
                                    for(int t = 0; t < str.length; t++) {
                                        //System.out.print(str[t] + " ");


                                        String[] ArrayOfxString = xString.split("");
                                        for(int n = 0; n < xString.length(); n++){
                                            if(ArrayOfxString[n].equals(WordGame.letters[i][j])){

                                                    xGot = n;     //here xGot is the index value of (string which) we have found for given charecter input
                                            }
                                        }

                                        for(int p = 0; p <= xGot; p++){
                                            for(int q = xGot+1; q <= xString.length(); q++){
                                                //System.out.print(xString.substring(p, q) + " ");
                                                subStringX = xString.substring(p, q);
                                                if(str[t].equals(subStringX)){     //checking with all possible substring for our given input
                                                    //System.out.println(xString.substring(p, q));
                                                    foundx = 1;
                                                    xString = subStringX;
                                                    System.out.println(xString);
                                                    JOptionPane.showMessageDialog(null, "You Got "+ subStringX.length() + " point for "+xString,"Congratulation!",JOptionPane.INFORMATION_MESSAGE);
                                                    break;
                                                }
                                            }
                                        }
                                        //System.out.println();
                                        String[] ArrayOfyString = yString.split("");
                                        for(int n = 0; n < yString.length(); n++){
                                            if(ArrayOfyString[n].equals(WordGame.letters[i][j])){
                                                yGot = n;
                                            }
                                        }

                                        for(int p = 0; p <= yGot; p++){
                                            for(int q = yGot + 1; q <= yString.length(); q++){
                                                //System.out.print(yString.substring(p, q)+ " ");
                                                subStringY = yString.substring(p, q);
                                                if(str[t].equals(subStringY)){
                                                    //System.out.println(xString.substring(p, q));
                                                    foundy = 1;
                                                    yString = subStringY;
                                                    JOptionPane.showMessageDialog(null, "You Got "+ subStringY.length() + " point for "+yString,"Congratulation!",JOptionPane.INFORMATION_MESSAGE);
                                                    break;
                                                }
                                            }
                                        }
                                        //System.out.println();

                                    }
                                    System.out.print(" " + xGot);
                                    System.out.print(" " + yGot);
                                    //System.out.println();
                                }

                                in.close();
                                read.close();
                            } catch (Exception exception)

                            {
                                exception.printStackTrace();
                            }

                                                                        //To add score
                            if(foundx == 1 && count%2 != 0){

                                WordGame.scorep2 += xString.length();
                                p2Score.setText("" +WordGame.scorep2);
                            }
                            if(foundy == 1 && count%2 != 0){
                                WordGame.scorep2 += yString.length();
                                p2Score.setText("" +WordGame.scorep2);
                            }
                            if(foundx == 1 && count%2 == 0){

                                WordGame.scorep1 += xString.length();
                                p1Score.setText("" +WordGame.scorep1);
                            }
                            if(foundy == 1 && count%2 == 0){
                                WordGame.scorep1 += yString.length();
                                p1Score.setText("" +WordGame.scorep1);
                            }
                            inputGiven = true;
                            count++;
                        }
                    }
                }
            }
            if(inputGiven == false){
                JOptionPane.showMessageDialog(null, "Give a single Charecter as input","Invalid input",JOptionPane.WARNING_MESSAGE);
            }

            if(count%2 == 0){
                playerTurn.setText(p1NameInput + "'s turn");
            }else{
                playerTurn.setText(p2NameInput + "'s turn");
            }

        }

        if (e.getSource() == highScore) {
            cl.show(panelMain, "3");
        }
        if(e.getSource() == hsPrev){
            cl.show(panelMain, "1");
        }
        if (e.getSource() == help) {
            cl.show(panelMain, "4");
        }
        if (e.getSource() == helpPrev) {
            cl.show(panelMain, "1");
        }
        if (e.getSource() == exit) {
            int yesNo = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Exit",JOptionPane.YES_NO_OPTION);
            if(yesNo == 0){
                System.exit(0);
            }
        }
    }
}