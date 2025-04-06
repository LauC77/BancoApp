package banco;

import java.util.Scanner;

public class BancoApp {

    private static int numCuenta = 1; // Contador para generar el número de cuenta

    public static void main(String[] args) {
        Banco banco = new Banco("Banco Ejemplo");
        
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            
            // Menú principal del sistema
            do {
                System.out.println("--- MENÚ BANCO ---");
                System.out.println("1. Apertura de Cuentas");
                System.out.println("2. Transferencias");
                System.out.println("3. Retiro en Cajero");
                System.out.println("4. Cierre de Mes");
                System.out.println("5. Emitir Cheque");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                
                switch (opcion) {
                    // Opción para la apertura de cuentas
                    case 1 -> {
                        scanner.nextLine(); // Limpiar el buffer del scanner
                        System.out.println("Digite sus nombres");
                        String nombres = scanner.nextLine();
                        
                        System.out.println("Digite sus apellidos");
                        String apellidos = scanner.nextLine();
                        
                        System.out.println("Digite su edad");
                        int edad = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer del scanner
                        
                        String representante;
                        if (edad < 18) { // Si es menor de edad, se pide representante
                            System.out.println("Digite el nombre completo de su representante legal");
                            representante = scanner.nextLine(); 
                        } else {
                            representante = ""; // No necesita representante si es mayor de edad
                        }
                        System.out.println("Ingrese el saldo inicial:");
                        double saldo = scanner.nextDouble();
                        
                        System.out.println("Seleccione tipo de cuenta (1: Ahorro, 2: Corriente):");
                        int tipoCuenta = scanner.nextInt();
                        
                        // Creación de la cuenta dependiendo del tipo seleccionado
                        if (tipoCuenta == 1) {
                            banco.abrirCuenta(new CuentaAhorro(numCuenta, nombres, apellidos, edad, representante, saldo));
                            System.out.println("Cuenta de ahorro creada exitosamente.");
                        } else if (tipoCuenta == 2 && saldo >= 200000) { // Requiere saldo mínimo para cuenta corriente
                            banco.abrirCuenta(new CuentaCorriente(numCuenta, nombres, apellidos, edad, representante, saldo));
                            System.out.println("Cuenta corriente creada exitosamente.");
                        } else {
                            System.out.println("Saldo insuficiente.");
                            break;
                        }
                        System.out.println("Anote su número de cuenta: " + numCuenta );
                        numCuenta++; // Incrementar el número de cuenta para la siguiente
                    }
                    // Opción para realizar retiros en cajeros
                    case 3 -> {
                        System.out.print("Ingrese el número de cuenta: ");
                        int numeroCuenta = scanner.nextInt();
                        
                        System.out.print("Ingrese el monto a retirar: ");
                        double monto = scanner.nextDouble();
                        
                        System.out.print("¿Está retirando en un cajero del banco? (1. Sí, 2. No): ");
                        int opcionCajero = scanner.nextInt();
                        
                        boolean cajeroPropio = (opcionCajero == 1);
                        
                        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
                        
                        // Realizar el retiro con verificación de comisión dependiendo del tipo de cajero
                        if (banco.retirarCajero(numeroCuenta, monto, cajeroPropio)) {
                            double comision = cajeroPropio ? 0 : 4500;
                            cuenta.registrarTransaccion("Retiro en cajero: -$" + monto);
                            System.out.println("Se descontaron $" + comision + " de comisión.");
                            
                        } else {
                            System.out.println("No se pudo realizar el retiro.");
                        }
                    }
                    // Opción para transferencvias, como en el enunciado del ejercicico
                    // no decia nada al respecto, dejaremos como que está en desarrollo
                
                    case 2 -> {
                        System.out.print("Opción en desarrollo, seleccione otra ");
                        System.out.print("\n");
                         
                    }
                    // Opción para realizar el cierre de mes y mostrar el estado de las cuentas
                    case 4 -> {
                            System.out.println("--- ESTADO DE CUENTAS ---");
                         for (Cuenta cuenta : banco.getCuentas()) {
                          cuenta.aplicarCargoMensual(); // Aplica los cargos de este mes
                          cuenta.mostrarEstadoCuenta();    // Muestra el estado de cada cuenta
                          System.out.println("----------------------------");
                      }
                    }
                    // Opción para emitir cheques desde una cuenta corriente
                 case 5 -> {
            System.out.println("¿Desea emitir un cheque? (1. Sí, 2. No)");
            int respuesta = scanner.nextInt();

            if (respuesta == 1) {
                System.out.println("Ingrese el número de cuenta corriente:");
                int numCuentaCheque = scanner.nextInt();

            Cuenta cuenta = banco.buscarCuenta(numCuentaCheque);

            // Verifica si la cuenta es de tipo CuentaCorriente
            if (cuenta instanceof CuentaCorriente) {
                System.out.println("Monto del cheque:");
                double montoCheque = scanner.nextDouble();

                // Verifica si hay saldo suficiente para emitir el cheque
                if (cuenta.getSaldo() >= montoCheque + 3000) { // Asegúrate de que el saldo sea suficiente para el cheque + comisión
                    // Deduce el monto del cheque y la comisión
                    cuenta.setSaldo(cuenta.getSaldo() - montoCheque - 3000); 

                    // Registrar las transacciones con los mensajes correctos
                    cuenta.registrarTransaccion("Comisión emisión de Cheque: -$" + 3000);
                    cuenta.registrarTransaccion("Valor de Cheque: -$" + montoCheque);

                    System.out.println("Cheque emitido por $" + montoCheque + ". Se cobraron $3000 por comisión.");
                } else {
                    System.out.println("Fondos insuficientes para emitir el cheque.");
                }
            } else {
                System.out.println("Número de cuenta inválido o no es cuenta corriente.");
            }
    }
}

                    // Opción para salir del sistema
                    case 6 -> System.out.println("Saliendo...");
                }
            } while (opcion != 6); // Se repite el menú hasta que el usuario elija salir
        }
    }
}
