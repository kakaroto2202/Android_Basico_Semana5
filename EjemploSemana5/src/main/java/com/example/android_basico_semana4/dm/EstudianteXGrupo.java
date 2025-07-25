package com.example.android_basico_semana4.dm;

public class EstudianteXGrupo {

    private int idGrupo;
    private int id;
    private String email;
    private String nombre;
    private String telefono;

    public EstudianteXGrupo() { }

    public EstudianteXGrupo(int idGrupo, int id, String email, String nombre, String telefono) {
        this.idGrupo = idGrupo;
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getidGrupo() {
        return idGrupo;
    }

    public void setidGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
