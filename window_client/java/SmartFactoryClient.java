// window monitoring app v0.1
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmartFactoryClient {
    private static JLabel ipLabel;
    private static JLabel temperatureLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inha Smart Factory!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));  // row, column

        ipLabel = new JLabel("IP Address: ");
        temperatureLabel = new JLabel("Temperature: ");

        JButton refreshButton = new JButton("Check Temperature!");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLabels();
            }
        });

        panel.add(ipLabel);
        panel.add(temperatureLabel);
        panel.add(refreshButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void updateLabels() {
        String urlStr = "http://192.168.45.56:80/";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    response.append("\n");
                }
                reader.close();

                String[] lines = response.toString().split("\n");
                if (lines.length >= 4) {
                    String ip_address = getValue(lines[2]);

                    ipLabel.setText("IP address : " + ip_address);
                    temperatureLabel.setText(lines[3]);
                } else {
                    JOptionPane.showMessageDialog(null, "Response format error!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Http connection failed : " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Exception occured." + e.getMessage());
        }
    }

    private static String getValue(String line) {
        return line.split(": ")[1];
    }
}
