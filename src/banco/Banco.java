package banco;
import java.util.ArrayList;

public class Banco {
    private String nombre; // Nombre del banco
    private ArrayList<Cuenta> cuentas; // Lista para almacenar las cuentas del banco

    // Constructor del banco, inicializa el nombre y la lista de cuentas
    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    // Método para abrir una cuenta y agregarla a la lista de cuentas
    public void abrirCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para realizar una transferencia entre dos cuentas
    public boolean transferir(int origen, int destino, double monto) {
        // Busca las cuentas de origen y destino por su número
        Cuenta cuentaOrigen = buscarCuenta(origen);
        Cuenta cuentaDestino = buscarCuenta(destino);

        // Si ambas cuentas existen, realiza la transferencia
        if (cuentaOrigen != null && cuentaDestino != null) {
            // Si la cuenta de origen tiene fondos suficientes, retira el monto y lo deposita en la cuenta destino
            if (cuentaOrigen.retirar(monto)) {
                cuentaDestino.depositar(monto);
                System.out.println("Transferencia realizada.");
                return true;
            }
        }
        System.out.println("Error en la transferencia.");
        return false;
    }

    // Método para realizar un retiro desde el cajero automático
    public boolean retirarCajero(int numeroCuenta, double monto, boolean cajeroPropio) {
        // Busca la cuenta correspondiente por el número
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            // Si es un cajero externo, se le suma la comisión de 4500 pesos
            double montoFinal = cajeroPropio ? monto : (monto + 4500);

            // Intenta retirar el monto de la cuenta
            if (cuenta.retirar(montoFinal)) {
                // Si el retiro fue en un cajero externo, se registra la comisión
                if (!cajeroPropio) {
                    cuenta.registrarTransaccion("Comisión por retiro en cajero externo: -$4500");
                }
                System.out.println("Retiro exitoso.");
                return true;
            }
        }
        System.out.println("Fondos insuficientes.");
        return false;
    }

    // Método que simula el cierre de mes, aplicando los cargos mensuales y mostrando el estado de cada cuenta
    public void cierreDeMes() {
        for (Cuenta cuenta : cuentas) {
            cuenta.aplicarCargoMensual(); // Aplica el cargo correspondiente
            cuenta.mostrarEstadoCuenta(); // Muestra el estado de la cuenta
        }
    }

    // Método para buscar una cuenta por su número
    Cuenta buscarCuenta(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            // Si encuentra una cuenta con el número proporcionado, la devuelve
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null; // Si no encuentra la cuenta, devuelve null
    }

    // Método para obtener la lista de cuentas
    public ArrayList<Cuenta> getCuentas() {  // Método corregido
        return cuentas;
    }
}

