package com.Restaurante.GestorDeRestaurante.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Restaurante.GestorDeRestaurante.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findByEmail(String email);
    public Usuario findByNombre(String nombre);

    public boolean existsByEmail(String email);
    public boolean existsByNombre(String nombre);

}
