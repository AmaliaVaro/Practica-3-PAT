package edu.comillas.icai.gitt.pat.spring.Practica2.Servicio;

import edu.comillas.icai.gitt.pat.spring.Practica2.Entidades.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica2.Entidades.LineadeCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica2.Repositorios.RepoCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica2.Repositorios.RepoLineaCarrito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServicioCarritos {
    @Autowired
    RepoCarrito repoCarrito;
    @Autowired
    RepoLineaCarrito repoLineaCarrito;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Transactional
    public Carrito creaCarrito(Carrito carritoNuevo){
        if(repoCarrito.findByCorreoUsuario(carritoNuevo.correoUsuario) != null){
            logger.warn("Intento de crear un carrito duplicado para: "+ carritoNuevo.correoUsuario);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Carrito ya existe");
        }

        carritoNuevo.precioTotal = 0.0;
        //guardo el nuevo carrito
        Carrito carritoGuardado = repoCarrito.save(carritoNuevo);

        return carritoGuardado;
    }

    @Transactional
    public LineadeCarrito añadirLinea(LineadeCarrito lineadeCarritoNueva, String correoUsuario){
       Carrito carrito = repoCarrito.findByCorreoUsuario(correoUsuario);

       if(carrito == null){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
       }

       lineadeCarritoNueva.costeLineaArticulo = lineadeCarritoNueva.precioUnitario*lineadeCarritoNueva.numeroUnidades;

       carrito.setPrecioTotal(carrito.getPrecioTotal() + lineadeCarritoNueva.costeLineaArticulo);
       lineadeCarritoNueva.carrito = carrito;

       repoCarrito.save(carrito);
       return repoLineaCarrito.save(lineadeCarritoNueva);
    }

    @Transactional
    public void eliminarLineaCarrito(LineadeCarrito lineaCarritoNueva, String correo){
        Carrito carrito = repoCarrito.findByCorreoUsuario(correo);

    }
}
