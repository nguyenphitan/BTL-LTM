
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Webcam1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(123);
        System.out.println("Waiting ....");
        Socket socket = server.accept();
        System.out.println("Connected!");
        
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage bm = webcam.getImage();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());     // doc du lieu tu client
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());    // gui du lieu cho client
        ImageIcon im = new ImageIcon(bm);
        
        JLabel label = new JLabel();
        
        JFrame frame = new JFrame("PC server");
        frame.setSize(640, 360);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        label = new JLabel();
        label.setSize(640, 360);
        label.setVisible(true);
        frame.add(label);
        frame.setVisible(true);
        
        while(true) {
            System.out.println("Webcam1 nhận phản hồi");
            bm = webcam.getImage();
            im = new ImageIcon(bm);
            out.writeObject(im);
            label.setIcon((ImageIcon) in.readObject());
            out.flush();
        }
        
    }
    
}
