import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Interfaz extends JFrame {
    private TrackingSystem trackingSystem;

    private JTextField trackingNumberTextField;
    private JTextField senderStreetTextField;
    private JTextField senderCityTextField;
    private JTextField senderStateTextField;
    private JTextField senderZipCodeTextField;
    private JTextField recipientStreetTextField;
    private JTextField recipientCityTextField;
    private JTextField recipientStateTextField;
    private JTextField recipientZipCodeTextField;
    private JTextField estimatedDeliveryDateTextField;
    private JPanel panel1;

    public Interfaz() {
        trackingSystem = new TrackingSystem();

        JPanel addPackagePanel = new JPanel(new GridLayout(0, 2));
        addPackagePanel.add(new JLabel("Tracking Number:"));
        trackingNumberTextField = new JTextField();
        addPackagePanel.add(trackingNumberTextField);
        addPackagePanel.add(new JLabel("Sender Street:"));
        senderStreetTextField = new JTextField();
        addPackagePanel.add(senderStreetTextField);
        addPackagePanel.add(new JLabel("Sender City:"));
        senderCityTextField = new JTextField();
        addPackagePanel.add(senderCityTextField);
        addPackagePanel.add(new JLabel("Sender State:"));
        senderStateTextField = new JTextField();
        addPackagePanel.add(senderStateTextField);
        addPackagePanel.add(new JLabel("Sender Zip Code:"));
        senderZipCodeTextField = new JTextField();
        addPackagePanel.add(senderZipCodeTextField);
        addPackagePanel.add(new JLabel("Recipient Street:"));
        recipientStreetTextField = new JTextField();
        addPackagePanel.add(recipientStreetTextField);
        addPackagePanel.add(new JLabel("Recipient City:"));
        recipientCityTextField = new JTextField();
        addPackagePanel.add(recipientCityTextField);
        addPackagePanel.add(new JLabel("Recipient State:"));
        recipientStateTextField = new JTextField();
        addPackagePanel.add(recipientStateTextField);
        addPackagePanel.add(new JLabel("Recipient Zip Code:"));
        recipientZipCodeTextField = new JTextField();
        addPackagePanel.add(recipientZipCodeTextField);
        addPackagePanel.add(new JLabel("Estimated Delivery Date (YYYY-MM-DD):"));
        estimatedDeliveryDateTextField = new JTextField();
        addPackagePanel.add(estimatedDeliveryDateTextField);
        JButton addPackageButton = new JButton("Añadir Paquete");
        addPackageButton.addActionListener(e -> {
            String trackingNumber = trackingNumberTextField.getText();
            String senderStreet = senderStreetTextField.getText();
            String senderCity = senderCityTextField.getText();
            String senderState = senderStateTextField.getText();
            String senderZipCode = senderZipCodeTextField.getText();
            String recipientStreet = recipientStreetTextField.getText();
            String recipientCity = recipientCityTextField.getText();
            String recipientState = recipientStateTextField.getText();
            String recipientZipCode = recipientZipCodeTextField.getText();
            LocalDate estimatedDeliveryDate = LocalDate.parse(estimatedDeliveryDateTextField.getText());
            Address senderAddress = new Address(senderStreet, senderCity, senderState, senderZipCode);
            Address recipientAddress = new Address(recipientStreet, recipientCity, recipientState, recipientZipCode);
            Package pkg = new Package(trackingNumber, senderAddress, recipientAddress, estimatedDeliveryDate);
            trackingSystem.addPackage(pkg);
            JOptionPane.showMessageDialog(this, "Paquete añadido correctamente.");
        });
        addPackagePanel.add(addPackageButton);

        JPanel removePackagePanel = new JPanel(new GridLayout(0, 2));
        removePackagePanel.add(new JLabel("Numero de Tracking :"));
        JTextField removeTrackingNumberTextField = new JTextField();
        removePackagePanel.add(removeTrackingNumberTextField);
        JButton removePackageButton = new JButton("Eliminar paquete");
        removePackageButton.addActionListener(e -> {
            String trackingNumber = removeTrackingNumberTextField.getText();
            boolean removed = trackingSystem.removePackage(trackingNumber);
            if (removed) {
                JOptionPane.showMessageDialog(this, "Package removido exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Package no encontrado.");
            }
        });
        removePackagePanel.add(removePackageButton);

        JPanel searchByRecipientAddressPanel = new JPanel(new GridLayout(0, 2));
        searchByRecipientAddressPanel.add(new JLabel("Recipient Address:"));
        JTextField recipientAddressTextField = new JTextField();
        searchByRecipientAddressPanel.add(recipientAddressTextField);
        JButton searchByRecipientAddressButton = new JButton("Buscar por Recipient Address");
        searchByRecipientAddressButton.addActionListener(e -> {
            String recipientAddress = recipientAddressTextField.getText();
            Package pkg = trackingSystem.searchByRecipientAddress(recipientAddress);
            if (pkg != null) {
                JOptionPane.showMessageDialog(this, pkg.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Paquete no encontrado.");
            }
        });
        searchByRecipientAddressPanel.add(searchByRecipientAddressButton);

        JPanel searchByTrackingNumberPanel = new JPanel(new GridLayout(0, 2));
        searchByTrackingNumberPanel.add(new JLabel("Numero de Tracking:"));
        JTextField trackingNumberSearchTextField = new JTextField();
        searchByTrackingNumberPanel.add(trackingNumberSearchTextField);
        JButton searchByTrackingNumberButton = new JButton("Buscar por Numero de Tracking");
        searchByTrackingNumberButton.addActionListener(e -> {
            String trackingNumber = trackingNumberSearchTextField.getText();
            Package pkg = trackingSystem.searchByTrackingNumber(trackingNumber);
            if (pkg != null) {
                JOptionPane.showMessageDialog(this, pkg.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Paquete no encontrado.");
            }
        });
        searchByTrackingNumberPanel.add(searchByTrackingNumberButton);

        JPanel searchByCityPanel = new JPanel(new GridLayout(0, 2));
        searchByCityPanel.add(new JLabel("Ciudad:"));
        JTextField cityTextField = new JTextField();
        searchByCityPanel.add(cityTextField);
        JButton searchByCityButton = new JButton("Buscar por Ciudad");
        searchByCityButton.addActionListener(e -> {
            String city = cityTextField.getText();
            List<Package> packages = trackingSystem.searchByCity(city);
            if (!packages.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Package pkg : packages) {
                    sb.append(pkg.toString()).append("\n\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron paquetes.");
            }
        });
        searchByCityPanel.add(searchByCityButton);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(addPackagePanel);
        mainPanel.add(removePackagePanel);
        mainPanel.add(searchByRecipientAddressPanel);
        mainPanel.add(searchByTrackingNumberPanel);
        mainPanel.add(searchByCityPanel);


        setContentPane(mainPanel);
        setTitle("Tracking System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Interfaz();
    }
}

