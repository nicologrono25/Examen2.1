import java.util.ArrayList;
import java.util.Comparator;

public class Menu {
    private ArrayList<Plato> platos;

    public Menu() {
        platos = new ArrayList<>();
    }

    public boolean agregarPlato(Plato plato) {
        if (buscarPlatoPorNombre(plato.getNombre()) == null) {
            platos.add(plato);
            return true;
        }
        return false;
    }

    public void eliminarPlato(Plato plato) {
        platos.remove(plato);
    }

    public Plato buscarPlatoPorNombre(String nombre) {
        for (Plato plato : platos) {
            if (plato.getNombre().equals(nombre)) {
                return plato;
            }
        }
        return null;
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public boolean existeNombre(String nombre) {
        return buscarPlatoPorNombre(nombre) != null;
    }

    public Plato busquedaSecuencial(String nombre) {
        for (Plato plato : platos) {
            if (plato.getNombre().equals(nombre)) {
                return plato;
            }
        }
        return null;
    }

    public Plato busquedaBinaria(String nombre, Comparator<Plato> comparator) {
        int left = 0;
        int right = platos.size() - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            Plato plato = platos.get(middle);
            int comparison = comparator.compare(plato, new Plato(nombre, 0, 0, 0));

            if (comparison == 0) {
                return plato;
            } else if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }
}
