/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnr.ejb;

import com.cnr.model.Readers;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author gganuza
 */
@Stateless
@LocalBean
public class ReadersBean {

    @PersistenceContext(unitName = "SystemPU")
    private EntityManager em;
    private EntityManagerFactory emf;

       /*public Readers saveReader(Readers reader) {
        try {
            em.persist(reader);
            return reader;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el lector: " + e.getMessage());
        }
    }*/
    // public void saveReader(Readers reader) {
     public Readers saveReader(Readers reader) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            // Crear el EntityManagerFactory y EntityManager
            emf = Persistence.createEntityManagerFactory("SystemPU");
            em = emf.createEntityManager();

            // Iniciar una transacción
            em.getTransaction().begin();

            // Guardar o actualizar el objeto User
            em.persist(reader);

            // Confirmar la transacción
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                // En caso de error, revertir la transacción
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el lector: " + e.getMessage());
        } finally {
            if (em != null) {
                // Cerrar el EntityManager
                em.close();
            }
            if (emf != null) {
                // Cerrar el EntityManagerFactory
                emf.close();
            }
        }
         return reader;
    } 

    /* public Readers saveReader(Readers newReader) {
        try {
            em.persist(newReader);
            em.flush(); // Asegurarse de que la transacción se complete
            return newReader; // Devolver el lector guardado
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejar excepciones según sea necesario
        }
    }*/
    public Readers findReder(Long readerId) {
        Readers reader = null;
        try {
            emf = Persistence.createEntityManagerFactory("SystemPU");
            em = emf.createEntityManager();

            // Creación de la consulta JPQL
            String jpql = "SELECT u FROM Readers u WHERE u.readerId = :readerId";
            TypedQuery<Readers> query = em.createQuery(jpql, Readers.class);
            query.setParameter("readerId", readerId);

            // Obtener el resultado
            reader = query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No se encontró el usuario: " + readerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return reader;
    }
    
        public List<Readers> findRederAll( ) {
       // public List<User> findAllUsers() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Readers> readers = null;
        try {
            // Crear el EntityManagerFactory y EntityManager
            emf = Persistence.createEntityManagerFactory("SystemPU");
            em = emf.createEntityManager();
            
            // Crear la consulta JPQL para seleccionar todos los usuarios
            String jpql = "SELECT u FROM Readers u";
            readers = em.createQuery(jpql, Readers.class).getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                // Cerrar el EntityManager
                em.close();
            }
            if (emf != null) {
                // Cerrar el EntityManagerFactory
                emf.close();
            }
        }
        return readers;
    }

    /*
    public Readers findReder(Long readerId) {
        Readers reader = null;
        try {
            emf = Persistence.createEntityManagerFactory("SystemPU");
            em = emf.createEntityManager();

            // Creación de la consulta JPQL
            String jpql = "SELECT u FROM Readers u WHERE u.readerId = :readerId";
            TypedQuery<Readers> query = em.createQuery(jpql, Readers.class);
            query.setParameter("readerId", readerId);

            // Obtener el resultado
            reader = query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No se encontró el usuario: " + readerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return reader;
    }
    
        public void saveReader(Readers reader) {
    EntityManagerFactory emf = null;
    EntityManager em = null;
    try {
        // Crear el EntityManagerFactory y EntityManager
        emf = Persistence.createEntityManagerFactory("SystemPU");
        em = emf.createEntityManager();
        
        // Iniciar una transacción
        em.getTransaction().begin();
        
        // Guardar o actualizar el objeto User
        em.persist(reader);
        
        // Confirmar la transacción
        em.getTransaction().commit();
        
    } catch (Exception e) {
        if (em != null && em.getTransaction().isActive()) {
            // En caso de error, revertir la transacción
            em.getTransaction().rollback();
        }
        e.printStackTrace();
    } finally {
        if (em != null) {
            // Cerrar el EntityManager
            em.close();
        }
        if (emf != null) {
            // Cerrar el EntityManagerFactory
            emf.close();
        }
    }
}*/
}
