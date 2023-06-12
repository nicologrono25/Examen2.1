import java.util.ArrayList;
import java.util.Comparator;


public class Ordenamiento {
    public static Comparator<Plato> porNombre = new Comparator<Plato>() {
        @Override
        public int compare(Plato p1, Plato p2) {
            return p1.getNombre().compareTo(p2.getNombre());
        }
    };

    public static Comparator<Plato> porPrecio = new Comparator<Plato>() {
        @Override
        public int compare(Plato p1, Plato p2) {
            return Double.compare(p1.getPrecio(), p2.getPrecio());
        }
    };

    public static Comparator<Plato> porCalorias = new Comparator<Plato>() {
        @Override
        public int compare(Plato p1, Plato p2) {
            return Integer.compare(p1.getCalorias(), p2.getCalorias());
        }
    };

    public static Comparator<Plato> porTiempoPreparacion = new Comparator<Plato>() {
        @Override
        public int compare(Plato p1, Plato p2) {
            return Integer.compare(p1.getTiempoPreparacion(), p2.getTiempoPreparacion());
        }
    };

    public static void ordenar(ArrayList<Plato> platos, Comparator<Plato> comparator) {
        platos.sort(comparator);
    }
}


