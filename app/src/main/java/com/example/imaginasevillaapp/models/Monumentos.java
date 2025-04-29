package com.example.imaginasevillaapp.models;

public class Monumentos {
    private String nombre;
    private String descripcion;
    private String direccion;
    private String horarios;
    private String info;
    private String mapaUrl;
    private String precio;
    private String webOficial;
    private String imagenUrl;

    // Constructor vacío requerido por Firebase
    public Monumentos() {

    }

    public Monumentos(String nombre, String descripcion, String direccion, String horarios,
                     String info, String mapaUrl, String precio, String webOficial, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.horarios = horarios;
        this.info = info;
        this.mapaUrl = mapaUrl;
        this.precio = precio;
        this.webOficial = webOficial;
        this.imagenUrl = imagenUrl;
    }

    // Getters y setters para usos en otras clases.
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getHorarios() { return horarios; }
    public void setHorarios(String horarios) { this.horarios = horarios; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    public String getMapaUrl() { return mapaUrl; }
    public void setMapaUrl(String mapaUrl) { this.mapaUrl = mapaUrl; }

    public String getPrecio() { return precio; }
    public void setPrecio(String precio) { this.precio = precio; }

    public String getWebOficial() { return webOficial; }
    public void setWebOficial(String webOficial) { this.webOficial = webOficial; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) {this.imagenUrl = imagenUrl;}
}
