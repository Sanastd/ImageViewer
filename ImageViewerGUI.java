import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "/home/...";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(3);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setResizable(true);
        this.setLayout(null);
        mainPanel();
        this.setVisible(true);
    }

    public void mainPanel(){

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,700,300);
        mainPanel.setLayout(null);

        JLabel label = new JLabel();
        label.setBounds(320,40,100,40);
        label.setText("Image Viewer");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(100,80,500,120);

        selectFileButton = new JButton("Choose Image");
        showImageButton = new JButton("Show Image");
        brightnessButton = new JButton("Brightness");
        grayscaleButton = new JButton("Gray scale");
        resizeButton = new JButton("Resize");
        closeButton = new JButton("Exit");
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);
        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        resizeButton.addActionListener(this);
        closeButton.addActionListener(this);

        mainPanel.add(buttonsPanel);
        mainPanel.add(label);
        mainPanel.setVisible(true);

        this.add(mainPanel);
    }

    public void resizePanel() throws IOException{
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setBounds(0,0,700,300);
        resizePanel.setVisible(true);

        var title = new JLabel("Resize Section");
        var width = new JLabel("Width:");
        var height = new JLabel("Height:");
        widthTextField = new JTextField();
        heightTextField = new JTextField();
        showResizeButton = new JButton("Show Result");
        backButton = new JButton("Back");

        title.setBounds(300,40,100,40);
        widthTextField.setBounds(250,80,200,50);
        heightTextField.setBounds(250,130, 200, 50 );
        width.setBounds(200,80,50,50);
        height.setBounds(200,130,50,50);
        backButton.setBounds(200,190,100,50);
        showResizeButton.setBounds(340,190,110,50);
        backButton.addActionListener(this);
        showResizeButton.addActionListener(this);

        resizePanel.add(title);
        resizePanel.add(widthTextField);
        resizePanel.add(heightTextField);
        resizePanel.add(showResizeButton);
        resizePanel.add(backButton);
        resizePanel.add(width);
        resizePanel.add(height);
        this.add(resizePanel);
    }
    public void brightnessPanel() throws IOException{
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        brightnessPanel.setBounds(0,0,700,300);
        brightnessPanel.setVisible(true);

        var label = new JLabel();
        label.setBounds(200,70,100,50);
        label.setText("Enter f ");
        var label2 = new JLabel();
        label2.setBounds(150,90,150,50);
        label2.setText("between 0 and 1");
        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(250,80,200,50);
        showBrightnessButton = new JButton("Show Result");
        backButton = new JButton("Back");
        backButton.setBounds(200,190,100,50);
        showBrightnessButton.setBounds(340,190,110,50);
        backButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);

        brightnessPanel.add(label);
        brightnessPanel.add(label2);
        brightnessPanel.add(backButton);
        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(label);

        this.add(brightnessPanel);
    }

    public void chooseFileImage() throws IOException {
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(1450,1000, Image.SCALE_DEFAULT));
            showOriginalImage();
        }
    }
    public void showOriginalImage() throws IOException{
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        filePath = "Image Viewer";
        fileChooser = new JFileChooser(filePath);
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        JLabel label = new JLabel(icon);
        label.setBounds(0,0,1450,1000);

        tempPanel.setSize(1450, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1450, 1000);
        tempFrame.setResizable(true);
        tempPanel.add(label);
        tempFrame.add(tempPanel);

        tempFrame.setVisible(true);
    }


    public void grayScaleImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage image = op.filter(bufferedImage, null);

        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setBounds(0,0,1450,1000);

        tempPanel.setSize(1450, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1450, 1000);
        tempPanel.add(label);
        tempFrame.add(tempPanel);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempFrame.setVisible(true);
    }
    public void showResizeImage(int w, int h) {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        JLabel label = new JLabel();
        label.setBounds(0,0,1450,1000);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(w,h, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);

        tempPanel.setSize(1450, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1450, 1000);
        tempPanel.add(label);
        tempFrame.add(tempPanel);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempFrame.setVisible(true);
    }
    public void showBrightnessImage(float f) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();


        BufferedImage bufferedImage = null;
        BufferedImage image = ImageIO.read(file);
        RescaleOp op = new RescaleOp(f, 0, null);
        image = op.filter(image, image);

        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setBounds(0,0,1450,1000);

        tempPanel.setSize(1450, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1450, 1000);
        tempPanel.add(label);
        tempFrame.add(tempPanel);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resizeButton){
            this.getContentPane().removeAll();
            try {
                this.resizePanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.revalidate();
            this.repaint();
        }
        else if(e.getSource()== showImageButton){
            try {
                this.showOriginalImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource()==grayscaleButton){
            try {
                grayScaleImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        else if(e.getSource()== showResizeButton){
            int w = Integer.parseInt(widthTextField.getText());
            int h = Integer.parseInt(heightTextField.getText());
            this.showResizeImage(w,h);
        }
        else if(e.getSource()==brightnessButton){
            this.getContentPane().removeAll();
            try {
                this.brightnessPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.revalidate();
            this.repaint();
        }
        else if(e.getSource()== showBrightnessButton){
            brightenFactor = Float.parseFloat(brightnessTextField.getText());
            try {
                this.showBrightnessImage(brightenFactor);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource()== selectFileButton){
            try {
                chooseFileImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}