import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class ClimarthaDashboard extends JFrame {
    private JTextField cityField;
    private JButton searchButton;
    private JPanel cityPanel;
    private HashMap<String, String[]> weatherData;
    private Random random = new Random();

    public ClimarthaDashboard() {
        setTitle("🌤️ Climartha – Advanced Weather Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255));

        // 🌟 Header
        JLabel title = new JLabel("🌎 Climartha – India's Smart Weather Dashboard 🌦️", JLabel.CENTER);
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
        title.setForeground(new Color(255, 111, 0));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // 💧 Weather Data (States + Major Cities)
        weatherData = new HashMap<>();
        weatherData.put("Maharashtra", new String[]{"32°C", "65%", "Sunny"});
        weatherData.put("Mumbai", new String[]{"31°C", "75%", "Humid"});
        weatherData.put("Pune", new String[]{"27°C", "68%", "Cloudy"});
         weatherData.put("Lucknow", new String[]{"33°C", "37%", "Cloudy"});
        weatherData.put("Nagpur", new String[]{"35°C", "60%", "Hot"});
        weatherData.put("Nashik", new String[]{"28°C", "62%", "Partly Cloudy"});
        weatherData.put("Goa", new String[]{"33°C", "85%", "Humid"});
        weatherData.put("Gujarat", new String[]{"36°C", "55%", "Sunny"});
        weatherData.put("Surat", new String[]{"34°C", "70%", "Cloudy"});
        weatherData.put("Delhi", new String[]{"37°C", "45%", "Sunny"});
        weatherData.put("Haryana", new String[]{"35°C", "50%", "Dry"});
        weatherData.put("Punjab", new String[]{"34°C", "55%", "Sunny"});
        weatherData.put("Rajasthan", new String[]{"40°C", "35%", "Hot"});
        weatherData.put("Jaipur", new String[]{"38°C", "40%", "Sunny"});
        weatherData.put("Karnataka", new String[]{"29°C", "75%", "Rainy"});
        weatherData.put("Bengaluru", new String[]{"26°C", "80%", "Rainy"});
        weatherData.put("Hyderabad", new String[]{"32°C", "65%", "Partly Cloudy"});
        weatherData.put("Chennai", new String[]{"34°C", "82%", "Humid"});
        weatherData.put("Kerala", new String[]{"30°C", "88%", "Rainy"});
        weatherData.put("Kolkata", new String[]{"33°C", "78%", "Cloudy"});
        weatherData.put("Assam", new String[]{"29°C", "85%", "Rainy"});
        weatherData.put("Odisha", new String[]{"31°C", "77%", "Cloudy"});
        weatherData.put("Bihar", new String[]{"34°C", "65%", "Sunny"});
        weatherData.put("Jharkhand", new String[]{"33°C", "70%", "Cloudy"});
        weatherData.put("Uttar Pradesh", new String[]{"35°C", "60%", "Hot"});
        weatherData.put("Uttarakhand", new String[]{"27°C", "68%", "Cool"});
        weatherData.put("Himachal Pradesh", new String[]{"22°C", "55%", "Cold"});
        weatherData.put("Sikkim", new String[]{"20°C", "70%", "Cold"});
        weatherData.put("Meghalaya", new String[]{"23°C", "80%", "Rainy"});
        weatherData.put("Madhya Pradesh", new String[]{"34°C", "58%", "Sunny"});
        weatherData.put("Indore", new String[]{"32°C", "60%", "Partly Cloudy"});

        // 🌈 Center panel with input box
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 240, 200),
                        getWidth(), getHeight(), new Color(200, 230, 255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(80, 150, 80, 150));

        JLabel prompt = new JLabel("Enter City or State Name:", JLabel.CENTER);
        prompt.setFont(new Font("Segoe UI", Font.BOLD, 20));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        prompt.setForeground(new Color(255, 111, 0));

        cityField = new JTextField(20);
        cityField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cityField.setMaximumSize(new Dimension(400, 40));
        cityField.setHorizontalAlignment(JTextField.CENTER);
        cityField.setBorder(new LineBorder(new Color(255, 165, 0), 2, true));

        searchButton = new JButton("🔍 Check Weather");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 17));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setBackground(new Color(255, 180, 90));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(new LineBorder(new Color(255, 120, 0), 2, true));

        searchButton.addActionListener(e -> searchWeather());
        cityField.addActionListener(e -> searchWeather());

        centerPanel.add(prompt);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(cityField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(searchButton);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void searchWeather() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) return;

        if (weatherData.containsKey(city)) {
            showWeatherCard(city, weatherData.get(city));
            cityField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "City not found! Try again.", "Not Found",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showWeatherCard(String city, String[] data) {
        JFrame weatherFrame = new JFrame("🌤️ Weather Report – " + city);
        weatherFrame.setSize(600, 500);
        weatherFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 250, 240));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel name = new JLabel(city + " Weather Report", JLabel.CENTER);
        name.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        name.setForeground(new Color(255, 111, 0));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel temp = new JLabel("🌡️ Temperature: " + data[0], JLabel.CENTER);
        JLabel hum = new JLabel("💧 Humidity: " + data[1], JLabel.CENTER);
        JLabel cond = new JLabel("☁️ Condition: " + data[2], JLabel.CENTER);

        temp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        hum.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cond.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JButton trendBtn = new JButton("📈 View Climate Graph");
        trendBtn.setBackground(new Color(255, 210, 150));
        trendBtn.setFocusPainted(false);
        trendBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        trendBtn.addActionListener(e -> showGraph(city));

        panel.add(name);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(temp);
        panel.add(hum);
        panel.add(cond);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(trendBtn);

        weatherFrame.add(panel);
        weatherFrame.setVisible(true);
    }

    // 📊 Graph with hover tooltips
    private void showGraph(String city) {
        JFrame frame = new JFrame("📊 Weather Trends – " + city);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(this);

        int[] temp = new int[12];
        int[] humidity = new int[12];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 20 + random.nextInt(20);
            humidity[i] = 40 + random.nextInt(50);
        }

        frame.add(new GraphPanel(city, temp, humidity));
        frame.setVisible(true);
    }

    class GraphPanel extends JPanel implements MouseMotionListener {
        int[] temp, humidity;
        int hover = -1;
        String city;

        GraphPanel(String city, int[] temp, int[] humidity) {
            this.city = city;
            this.temp = temp;
            this.humidity = humidity;
            addMouseMotionListener(this);
            setBackground(new Color(240, 250, 255));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth() - 100, h = getHeight() - 100;
            int startX = 60, startY = 40;
            g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
            g2.setColor(new Color(255, 111, 0));
            g2.drawString("Weather Trend – " + city, startX + 150, 25);

            g2.setColor(new Color(230, 240, 255));
            g2.fillRect(startX, startY, w, h);
            g2.setColor(Color.GRAY);
            g2.drawRect(startX, startY, w, h);

            int step = w / (temp.length - 1);
            int prevX = startX, prevY = startY + h - temp[0] * 3;

            g2.setColor(new Color(255, 80, 80));
            for (int i = 1; i < temp.length; i++) {
                int x = startX + i * step;
                int y = startY + h - temp[i] * 3;
                g2.drawLine(prevX, prevY, x, y);
                prevX = x;
                prevY = y;
            }

            g2.setColor(new Color(50, 150, 255));
            prevX = startX;
            prevY = startY + h - humidity[0] * 2;
            for (int i = 1; i < humidity.length; i++) {
                int x = startX + i * step;
                int y = startY + h - humidity[i] * 2;
                g2.drawLine(prevX, prevY, x, y);
                prevX = x;
                prevY = y;
            }

            if (hover >= 0 && hover < temp.length) {
                int x = startX + hover * step;
                int y = startY + h - temp[hover] * 3;
                g2.setColor(new Color(0, 0, 0, 180));
                g2.fillRoundRect(x + 10, y - 40, 130, 50, 10, 10);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                g2.drawString("Day: " + (hover + 1), x + 20, y - 25);
                g2.drawString("Temp: " + temp[hover] + "°C", x + 20, y - 12);
                g2.drawString("Humidity: " + humidity[hover] + "%", x + 20, y + 1);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int step = (getWidth() - 100) / (temp.length - 1);
            int startX = 60;
            for (int i = 0; i < temp.length; i++) {
                int x = startX + i * step;
                if (Math.abs(e.getX() - x) < 8) {
                    hover = i;
                    repaint();
                    return;
                }
            }
            hover = -1;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClimarthaDashboard().setVisible(true));
    }
}
