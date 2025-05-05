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

    // Campo para almacenar el ID del documento
    private String id;

    // Constructor vacío (requerido para Firestore)
    public Monumentos() {
    }

    // Constructor con los campos del monumento
    public Monumentos(String nombre, String descripcion, String direccion, String horarios, String info, String mapaUrl, String precio, String webOficial, String imagenUrl) {
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

    // Getter y setter para el nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y setter para la descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y setter para la direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Getter y setter para horarios
    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    // Getter y setter para info
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    // Getter y setter para mapaUrl
    public String getMapaUrl() {
        return mapaUrl;
    }

    public void setMapaUrl(String mapaUrl) {
        this.mapaUrl = mapaUrl;
    }

    // Getter y setter para precio
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    // Getter y setter para webOficial
    public String getWebOficial() {
        return webOficial;
    }

    public void setWebOficial(String webOficial) {
        this.webOficial = webOficial;
    }

    // Getter y setter para imagenUrl
    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    // Getter y setter para el ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
