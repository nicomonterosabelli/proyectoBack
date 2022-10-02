package com.example.IntegradorFinalNicolasMontero.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private Integer dni;
    @Column
    private LocalDate fecha;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_domicilio" , referencedColumnName ="id")
    private Domicilio domicilio;
    @Column
    private String mail;

    public Paciente(Long id, String apellido, String nombre, Integer dni, LocalDate fecha, Domicilio domicilio, String mail) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.fecha = fecha;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    public Paciente(String apellido, String nombre, Integer dni, LocalDate fecha, Domicilio domicilio, String mail) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.fecha = fecha;
        this.domicilio = domicilio;
        this.mail = mail;
    }
    public Paciente(){};

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDni() {
        return this.dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Domicilio getDomicilio() {
        return this.domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
