package com.aluracursos.aluraforo.entidades;


import com.aluracursos.aluraforo.Dtos.perfil.DatosActualizaPerfil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Perfil")
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    /*
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    @JsonBackReference
    private Usuario usuario;
    */
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "perfiles")
    @JsonBackReference
    private List<Usuario> usuario;

    public void actualizarDatos(DatosActualizaPerfil datosActualizarPerfil) {
        this.nombre = datosActualizarPerfil.nombre();
    }
}
