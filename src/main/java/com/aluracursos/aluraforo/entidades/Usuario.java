package com.aluracursos.aluraforo.entidades;

import com.aluracursos.aluraforo.Dtos.usuario.DatosActualizarUsuario;
import com.aluracursos.aluraforo.Dtos.usuario.DatosUsuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
@Table(name = "usuario")
public class Usuario implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String contrasena;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "usuario_perfil", joinColumns = @JoinColumn( name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id" , referencedColumnName = "id")
    )
    @JsonManagedReference
    private List<Perfil> perfiles =  new ArrayList<>();

    public Usuario(DatosUsuario datosUsuario) {
        this.nombre = datosUsuario.nombre();
        this.email = datosUsuario.email();
        this.contrasena = datosUsuario.contrasena();
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario) {
        this.nombre = datosActualizarUsuario.nombre();
    }
    public void addPerfil(Perfil perfil){
        this.perfiles.add(perfil);
    }
    public Collection<GrantedAuthority> mapToPerfil(List<Perfil> perfiles){
        return perfiles.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getNombre())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapToPerfil(this.getPerfiles());
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Perfil> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Perfil role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNombre()));
        }

        return authorities;
    }
    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
