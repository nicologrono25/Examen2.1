import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class mainForm extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textIngresoNombre;
    private JButton ingresarPlatoButton;
    private JTextArea textAIngresoPlatos;
    private JButton QuemarDatosButton;
    private JTextField textIngresoPrecio;
    private JTextField textIngresoCalorias;
    private JTextField textIngresoPreparacion;
    private JButton buscarModifButton;
    private JButton modificarModifButton;
    private JTextField textoModifNombre;
    private JTextField textoModifPrecio;
    private JTextField textoModifCalorias;
    private JTextField textoModifPreparacion;
    private JTextArea textAModif;
    private JButton ButtonBuscarEliminar;
    private JTextField textNombreEliminar;
    private JTextArea textAEliminar;
    private JButton eliminarButton;
    private JComboBox comboBoxOrder;
    private JButton mostrarButton;
    private JTextArea textAMostrar;
    private JButton buscarButton;
    private JTextField textBuscarPlatoOrden;

    private Menu menu;

    public mainForm() {
        menu = new Menu();

        ingresarPlatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textIngresoNombre.getText();
                if (nombre.isEmpty()) {
                    textAIngresoPlatos.append("Por favor, ingrese un nombre para el plato.\n");
                    return;
                }

                try {
                    double precio = Double.parseDouble(textIngresoPrecio.getText());
                    int calorias = Integer.parseInt(textIngresoCalorias.getText());
                    int tiempoPreparacion = Integer.parseInt(textIngresoPreparacion.getText());

                    Plato plato = new Plato(nombre, precio, calorias, tiempoPreparacion);
                    if (menu.agregarPlato(plato)) {
                        textAIngresoPlatos.append("Plato agregado: " + plato.toString() + "\n");
                    } else {
                        textAIngresoPlatos.append("El plato ya existe en el menú.\n");
                    }
                } catch (NumberFormatException ex) {
                    textAIngresoPlatos.append("Por favor, ingrese valores válidos para precio, calorías y tiempo de preparación.\n");
                }

            }
        });

        QuemarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar platos de ejemplo al menú
                Plato plato1 = new Plato("Salchipapa", 5.0, 500, 30);
                Plato plato2 = new Plato("Pizza", 25.0, 600, 40);
                Plato plato3 = new Plato("Pasta", 30.0, 450, 60);
                Plato plato4 = new Plato("Bife", 80.0, 400, 60);
                Plato plato5 = new Plato("Picaña", 76.0, 600, 40);
                Plato plato6 = new Plato("Rabioles", 30.0, 450, 60);

                menu.agregarPlato(plato1);
                menu.agregarPlato(plato2);
                menu.agregarPlato(plato3);
                menu.agregarPlato(plato4);
                menu.agregarPlato(plato5);
                menu.agregarPlato(plato6);


                textAIngresoPlatos.append("Datos quemados agregados:\n");
                textAIngresoPlatos.append(plato1.toString() + "\n");
                textAIngresoPlatos.append(plato2.toString() + "\n");
                textAIngresoPlatos.append(plato3.toString() + "\n");
                textAIngresoPlatos.append(plato4.toString() + "\n");
                textAIngresoPlatos.append(plato5.toString() + "\n");
                textAIngresoPlatos.append(plato6.toString() + "\n");

            }

        });

        buscarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textoModifNombre.getText();
                Plato plato = menu.buscarPlatoPorNombre(nombre);

                if (plato != null) {
                    textoModifPrecio.setText(Double.toString(plato.getPrecio()));
                    textoModifCalorias.setText(Integer.toString(plato.getCalorias()));
                    textoModifPreparacion.setText(Integer.toString(plato.getTiempoPreparacion()));
                } else {
                    textAModif.append("Plato no encontrado.\n");
                }
            }
        });

        modificarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textoModifNombre.getText();
                Plato plato = menu.buscarPlatoPorNombre(nombre);

                if (plato != null) {
                    double precio = Double.parseDouble(textoModifPrecio.getText());
                    int calorias = Integer.parseInt(textoModifCalorias.getText());
                    int tiempoPreparacion = Integer.parseInt(textoModifPreparacion.getText());

                    plato.setPrecio(precio);
                    plato.setCalorias(calorias);
                    plato.setTiempoPreparacion(tiempoPreparacion);

                    textAModif.append("Plato modificado: " + plato + "\n");
                } else {
                    textAModif.append("Plato no encontrado.\n");
                }
            }
        });

        ButtonBuscarEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textNombreEliminar.getText();
                Plato plato = menu.buscarPlatoPorNombre(nombre);

                if (plato != null) {
                    textAEliminar.setText(plato.toString());
                } else {
                    textAEliminar.setText("Plato no encontrado.");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textNombreEliminar.getText();
                Plato plato = menu.buscarPlatoPorNombre(nombre);

                if (plato != null) {
                    menu.eliminarPlato(plato);
                    textAEliminar.setText("Plato eliminado: " + plato);
                } else {
                    textAEliminar.setText("Plato no encontrado.");
                }
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orden = (String) comboBoxOrder.getSelectedItem();
                Comparator<Plato> comparator;

                switch (orden) {
                    case "Nombre":
                        comparator = Ordenamiento.porNombre;
                        break;
                    case "Precio":
                        comparator = Ordenamiento.porPrecio;
                        break;
                    case "Calorias":
                        comparator = Ordenamiento.porCalorias;
                        break;
                    case "Tiempo de preparación":
                        comparator = Ordenamiento.porTiempoPreparacion;
                        break;
                    default:
                        comparator = Ordenamiento.porNombre;
                }

                Ordenamiento.ordenar(menu.getPlatos(), comparator);

                textAMostrar.setText("");
                for (Plato plato : menu.getPlatos()) {
                    textAMostrar.append(plato.toString() + "\n");
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textBuscarPlatoOrden.getText();
                Plato plato = menu.busquedaSecuencial(nombre); // O utiliza busquedaBinaria si los platos están ordenados

                if (plato != null) {
                    textAMostrar.setText(plato.toString());
                } else {
                    textAMostrar.setText("Plato no encontrado.");
                }
            }
        });
    }

    //Get mainPanel
    public JPanel getMainPanel() {
        return mainPanel;
    }
}