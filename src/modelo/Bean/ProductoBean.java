/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Bean;

import api.Consumeapi;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 *
 * @author carol
 */
public class ProductoBean {
    
    private final Producto productos = new Producto();
    private final List<Producto> lista_productos = new ArrayList<>();
    
    public List<Producto> todoProductos() {

        Consumeapi capi = new Consumeapi();
        Gson json = new Gson();
        String temporal = "";

        try {

            //List<Productos> lista_productos = new ArrayList<>();
            String datos_recuperados = capi.obtener(String.class);
            List<Producto> lista_temporal = json.fromJson(datos_recuperados, List.class);

            if (!lista_temporal.isEmpty()) {
                for (int i = 0; i < lista_temporal.size(); i++) {

                    //lista_productos = new ArrayList<>();
                    temporal = json.toJson(lista_temporal.get(i));
                    Producto item = json.fromJson(temporal, Producto.class);

                    lista_productos.add(item);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron productos", "Productos registrados", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : \n" + e, "Error de conexión", 0);
        }

        return lista_productos;
    }
    
    public List<Producto> buscarProductos(String tipo, String dato) {

        Consumeapi capi = new Consumeapi();
        Gson json = new Gson();
        String temporal = "";

        try {

            //List<Productos> lista_productos = new ArrayList<>();
            String datos_recuperados = capi.buscarProducto(tipo, dato, String.class);
            List<Producto> lista_temporal = json.fromJson(datos_recuperados, List.class);

            if (!lista_temporal.isEmpty()) {
                for (int i = 0; i < lista_temporal.size(); i++) {

                    //lista_productos = new ArrayList<>();
                    temporal = json.toJson(lista_temporal.get(i));
                    Producto item = json.fromJson(temporal, Producto.class);

                    lista_productos.add(item);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron productos con los siguientes valores : \n" + tipo +" < == >s "+ dato , "Productos registrados", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : \n" + e, "Error de conexión", 0);
        }

        return lista_productos;
    }

}
