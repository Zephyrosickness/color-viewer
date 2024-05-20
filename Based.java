import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.UIManager.LookAndFeelInfo;


//welcome to hell

public class Based extends JFrame {

    //init variables
    int redDecimal;
    int greenDecimal;
    int blueDecimal;

    String redHex;
    String greenHex;
    String blueHex;

    String hex;

    Rectangle colorPreview = new Rectangle(190, 5, 180, 180);

    Color rectangleColor;

    public static void main(String[] args) {
        Based instance = new Based();
        //changes l&f to windows classic because im a basic bitch like that
        try{
            for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
                if("Windows Classic".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e){
            //error handler for l&f in case something doesnt work
        }


        // create a window
        JFrame frame = new JFrame("awesome color generator");
        frame.setSize(400, 230);


        // create a panel to hold components
        JPanel panel = new JPanel(){
            //im so tired im gonna go crazy its midnight
            @Override
            //draws rectangle for color preview if this doesnt work i will cry
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //draw a rectangle
                if (g == null){
                    System.out.println("IM GONNA GO WILD");
                }

                //fuckers from hell
                g.setColor(instance.rectangleColor);
                g.fillRect(instance.colorPreview.x, instance.colorPreview.y, instance.colorPreview.width, instance.colorPreview.height);
            }
        };
        frame.add(panel);
        panel.setSize(400, 200);
        placeComponents(instance, panel);
        frame.setVisible(true);
    }
    //panel to hold components
    public static void placeComponents(Based instance, JPanel panel) {

        //randomizes
        instance.randomizer();

        // set the layout manager to null for absolute positioning
        panel.setLayout(null);

        // ---labels---

        //for displaying full rgb color

        JLabel title = new JLabel("your color is:");
        title.setBounds(10, 10, 80, 25);
        panel.add(title);

        JLabel customtitle = new JLabel("custom RGB");
        customtitle.setBounds(100, 10, 80, 25);
        panel.add(customtitle);

        JLabel rgbValue = new JLabel("in rgb: "+instance.redDecimal+", "+instance.greenDecimal+", "+instance.blueDecimal);
        rgbValue.setBounds(10, 20, 100, 25);
        panel.add(rgbValue);

        //labels for rgb values

        JLabel redValue = new JLabel("red: "+instance.redDecimal);
        redValue.setBounds(10, 30, 100, 25);
        panel.add(redValue);

        JLabel greenValue = new JLabel("green: "+instance.greenDecimal);
        greenValue.setBounds(10, 40, 100, 25);
        panel.add(greenValue);

        JLabel blueValue = new JLabel("blue: "+instance.blueDecimal);
        blueValue.setBounds(10, 50, 100, 25);
        panel.add(blueValue);

        //for displaying hex color

        JLabel hexValue = new JLabel("in hex: "+instance.hex);
        hexValue.setBounds(10, 60, 80, 25);
        panel.add(hexValue);

        // ---textfields---

        //red
        JTextField redCustom = new JTextField("0");
        redCustom.setBounds(100, 50, 80, 25);
        panel.add(redCustom);

        //green
        JTextField greenCustom = new JTextField("0");
        greenCustom.setBounds(100, 80, 80, 25);
        panel.add(greenCustom);

        //blue
        JTextField blueCustom = new JTextField("0");
        blueCustom.setBounds(100, 110, 80, 25);
        panel.add(blueCustom);

        // ---buttons---

        JButton randomizeButton = new JButton("randomize");
        randomizeButton.setBounds(10, 140, 80, 25);
        panel.add(randomizeButton);

        // dd an actionListener to the button to prefomr actions wehn clivkec
        randomizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // reshuffles rgb values
                instance.randomizer();

                //updates text again. probably easier method but im really tired
                redValue.setText("red: "+instance.redDecimal);
                greenValue.setText("green: "+instance.greenDecimal);
                blueValue.setText("blue: "+instance.blueDecimal);
                hexValue.setText("in hex: "+instance.hex);
                rgbValue.setText("in rgb: "+instance.redDecimal+", "+instance.greenDecimal+", "+instance.blueDecimal);

                panel.repaint();
            }
        });

        //button to apply custom colors
        JButton applyButton = new JButton("apply");
        applyButton.setBounds(100, 140, 80, 25);
        panel.add(applyButton);

        //actionlistener for the apply color button
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //sets the text inputted into an integer
                int redInt = Integer.parseInt(redCustom.getText());
                int greenInt = Integer.parseInt(greenCustom.getText());
                int blueInt = Integer.parseInt(blueCustom.getText());


                //sets the color to the values specified
                instance.redDecimal = redInt;
                instance.greenDecimal = greenInt;
                instance.blueDecimal = blueInt;

                //updates text
                redValue.setText("red: "+instance.redDecimal);
                greenValue.setText("green: "+instance.greenDecimal);
                blueValue.setText("blue: "+instance.blueDecimal);
                rgbValue.setText("in rgb: "+instance.redDecimal+", "+instance.greenDecimal+", "+instance.blueDecimal);

                instance.hexConvert();
                hexValue.setText("in hex: "+instance.hex);

                instance.updateColor();
                panel.repaint();
            }
        });
    }




//calls all methods. this was originally gonna be one really big method but i couldnt return both int and string values so i had to break it up. makes it easier to read anyways
public void randomizer(){
    rgbShuffler();
    hexConvert();
    updateColor();
}


//assigns random number to each rgb value and returns
public int[] rgbShuffler(){

    //creates new instance of random class
    Random rand = new Random();

    //randomizes
    redDecimal = rand.nextInt(256);
    greenDecimal = rand.nextInt(256);
    blueDecimal = rand.nextInt(256);

    //converts all 3 values into an array for easy return
    int array[] = {redDecimal, greenDecimal, blueDecimal};

    return array;
}
    //converts rgb into hex and returns
    public String hexConvert(){

        redHex = Integer.toHexString(redDecimal);
        greenHex = Integer.toHexString(greenDecimal);
        blueHex = Integer.toHexString(blueDecimal);

        redHex = hexFix(redHex);
        greenHex = hexFix(greenHex);
        blueHex = hexFix(blueHex);


        hex = "#"+redHex+greenHex+blueHex;

        return hex;

    }

    //no way bro wants me to explain the function named updatecolor like ermmm take a guess
    public void  updateColor(){

        rectangleColor = new Color(redDecimal, greenDecimal, blueDecimal);

    }

    //if the length of a hex code is a single digit, add a 0 out front. had issues where the hex code would be less than 6 digits due to values being single digit
    public String hexFix(String num){

        int length = num.length();

        if(length==1){
            num = "0"+num;
        }
        return num;
    }
}

