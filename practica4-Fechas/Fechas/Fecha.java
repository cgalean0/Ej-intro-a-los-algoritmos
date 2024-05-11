import java.lang.Math;
/**
 * Fecha: clase cuyos objetos representan fechas calendarias, para el 
 * calendario gregoriano. Esta clase implementa una variedad de 
 * funcionalidades sobre fechas, como comparaciones, cómputos de días,
 * etc.
 * 
 * @author N. Aguirre 
 * @version 0.1
 */
public class Fecha
{
    // dia de la fecha
    private int dia;
    
    // mes de la fecha
    private int mes;
    
    // año de la fecha
    private int anho;

    /**
     * Constructor por defecto, para la clase fecha.
     * Crea como fecha por defecto la fecha inicial del 
     * calendario gregoriano (15/10/1582)
     */
    public Fecha()
    {
        dia = 15;
        mes = 10;
        anho = 1582;
    }

    /**
     * Constructor de la clase Fecha, que recibe la fecha a 
     * crear como parámetro. La fecha no puede ser previa
     * al 15/10/1582. Debe tenerse en cuenta la creación de
     * fechas válidas, en relación a número de días en los
     * meses, años bisiestos, etc.
     */
    public Fecha(int nuevoDia, int nuevoMes, int nuevoAnho)
    {
        /*
        * Validar fechas
            *Validar que el dia ingresado sea correspondiente al mes para permitir que los meses tengan sus dias correpondientes.
            *Febrero no puede tener mas de 28 dias a menos que sea año bisiesto.
            *Los meses que pueden tener 31 dias son enero,marzo,mayo,julio,agosto,octubre y diciembre.
            *Los meses que pueden tener 30 son abril,junio,septiembre,noviembre.
        */
        //Validar mes.
        if (nuevoMes >= 1 && nuevoMes <= 12) {
            mes = nuevoMes;
        } else {
            System.out.println("Insertar un mes entre 1 y 12.");
        }
        /*
        * Validar anho
        * El año debe ser mayor a 1582 por el calendario gregoriano.  
        */
        if (nuevoAnho >= 1582) {
            anho = nuevoAnho;
            //Si el año es bisiesto, permitimos que febrero pueda tener 29 dias
            if (esBisiesto(anho)) {
                if (mes == 2) {
                    if(nuevoDia >= 1 && nuevoDia <= 29 ) {
                        dia = nuevoDia;
                    }
                }
            }
            //Si el año no es bisiesto entonces los dias maximos para febrero seran 28.
            if (mes == 2 && !esBisiesto(anho)) {
                //Si el dia ingresado es entre 1 y 28 se obtiene ese dia.
                   if (nuevoDia >= 1 && nuevoDia <= 28) {
                       dia = nuevoDia;
                   } else {
                       //Si el dia ingresado es menor a 1 pone por defecto el dia como 1.
                       if (nuevoDia < 1) {
                          dia = 1; 
                       }
                       //Si el dia ingresado es mayor a 28 pone por defecto el dia como 28.
                       if (nuevoDia > 28) {
                          dia = 28;
                       }
                       System.out.println("El mes ingresado es Febrero y solo puede tener hasta 28 dias por no ser un año bisiesto.");
                   }
            }
            
        } else {
            System.out.println("Insertar un año mayor a 1582.");
        }
        /*
        * Validar dias para enero,marzo,mayo,julio,agosto,octubre y diciembre.
        * Estos pueden tener hasta 31 dias.
        */
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            if (nuevoDia >= 1 && nuevoDia <= 31) {
                dia = nuevoDia;
            }
        } else {
            if (nuevoDia > 31) {
                dia = 31;
                System.out.println("El mes ingresado solo puede tener hasta 30 dias.");
            }
            if (nuevoDia < 1) {
                dia = 1;
                System.out.println("No puedes ingresar una cantidad menor a 1 dia.");
            }
        }
        /*
        * Validar dias para abril,junio,septiembre,noviembre.
        * Estos pueden tener hasta 30 dias.
        */
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
           if (nuevoDia >= 1 && nuevoDia <= 30) {
               dia = nuevoDia;
           }
        } else {
            if (nuevoDia > 30) {
                dia = 30;
                System.out.println("El mes ingresado solo puede tener hasta 30 dias.");
            }
            if (nuevoDia < 1) {
                dia = 1;
                System.out.println("No puedes ingresar una cantidad menor a 1 dia.");
            }
        }
    }
    
    /**
     * Retorna el dia de la fecha
     */
    public int obtenerDia() {
        return dia;
    }
    
    /**
     * Retorna el mes de la fecha
     */
    public int obtenerMes() {
        return mes;
    }
    
    /**
     * Retorna el año de la fecha
     */
    public int obtenerAnho() {
        return anho;
    }
    
    /**
     * Cambia el día de la fecha. El nuevo día debe ser válido
     * para el mes y año actuales.
     */
    public void cambiarDia(int nuevoDia) {
        if(nuevoDia >= 1 && nuevoDia <= 31){
            dia = nuevoDia;
        }
    }
    
    /**
     * Cambia el mes de la fecha. El nuevo mes debe ser válido
     * para el día y año actuales.
     */
    public void cambiarMes(int nuevoMes) {
        if(nuevoMes >= 1 && nuevoMes <= 12){
            mes = nuevoMes;
        }
    }
    
    /**
     * Cambia el año de la fecha. El nuevo año debe ser válido
     * para el día y mes actuales.
     */
    public void cambiarAnho(int nuevoAnho) {
        if(nuevoAnho >= 1582){
            anho = nuevoAnho;
        }
    }
    
    /**
     * Chequea si la fecha es igual a otra fecha dada
     */
    public boolean equals(Fecha otraFecha) {
        int otroDia = otraFecha.obtenerDia();
        int otroMes = otraFecha.obtenerMes();
        int otroAnho = otraFecha.obtenerAnho();
        
        if (otroDia == dia && otroMes == mes && otroAnho == anho) {
            return true;
        }
        return false;
    }
    
    /**
     * Chequea si la fecha es anterior a otra fecha dada
     */
    public boolean esAnterior(Fecha otraFecha) {
        if (anho < otraFecha.obtenerAnho()) {
            return true;
        } else if (anho == otraFecha.obtenerAnho()) {
            if (mes < otraFecha.obtenerMes()) {
                return true;
            } else if (mes == otraFecha.obtenerMes()) {
                return dia < otraFecha.obtenerDia();
            }
        }
        return false;
    }
    
    /**
     * Computa distancia en días entre dos fechas.
     * El parámetro otraFecha debe corresponder a una fecha igual o 
     * posterior a la fecha del objeto.
     */
    public void cantidad(Fecha otraFecha) {
        //inicializamos una copia de la fecha del objeto.
        Fecha corriente = new Fecha(dia, mes, anho);
        int contador = 0;
        
        while (corriente.esAnterior(otraFecha)) {
            if (corriente.obtenerDia() <= cantDias(corriente.obtenerMes(), corriente.obtenerAnho())) {
                corriente.dia+= 1;
            } else {
                corriente.dia = 1;
                if (corriente.obtenerMes() < 12) {
                    corriente.mes+= 1;
                } else {
                    corriente.mes = 1;
                    corriente.anho+= 1;
                }
            }
            contador++;
        }
    }
    /**
     * Computa la cantidad de días de un mes dado. El parámetro
     * debe corresponder a un mes válido.
     */
    private int cantDias(int unMes, int unAnho) //Bug arreglar urgente.
    {
        int diasDelMes = 0;
        if (unMes == 2 && esBisiesto(unAnho)) {
                diasDelMes = 29;
            if (unMes == 2 && !esBisiesto(unAnho)) {
                diasDelMes = 28;
        }
        }
        if (unMes == 1 || unMes == 3 || unMes == 5 || unMes == 7 || unMes == 8 || unMes == 10 || unMes == 12) {
            diasDelMes = 31;
        }
        if (unMes == 4 || unMes == 6 || unMes == 9 || unMes == 11) {
            diasDelMes = 30;
        }
        return diasDelMes;
    }
    
    /**
     * Decide si un año dado es bisiesto o no. Un año es bisiesto
     * si es múltiplo de 4, salvo los múltiplos de 100 que no son 
     * múltiplos de 400.
     * El parámetro debe corresponder a un año válido.
     */
    private boolean esBisiesto(int unAnho) 
    {
        //Aseguramos que el valor del parametro sea mayor a 1582.
        assert unAnho > 1582 : "Ingresa un numero mayor a 1582";
        if(unAnho >= 1582){
            //Comparamos que un numero sea multiplo de 4.
            if(unAnho % 4 == 0){
            //Compara que sea multiplo de 100 y no es multiplo de 400 a la vez.
                if(unAnho % 100 == 0 && unAnho % 400 == 0){
                    //Si es ambas a la vez retorna false.
                    return false;
            } else {
                //Sino retorna verdadero.
                return true;
            }
        } 
        else {
            return false;
        }
        }
        return false;
    }
    
    /**
    *Metodo que convierte el dia, mes, anho. En el formato tradicional. eg: "15/12/1582".
    *y lo imprime por pantalla.
    */
    private String toStringDate(int mostrarDia, int mostrarMes, int mostrarAnho)
    {
        return (""+ mostrarDia + "/" + mostrarMes + "/" + mostrarAnho);
    }
}
