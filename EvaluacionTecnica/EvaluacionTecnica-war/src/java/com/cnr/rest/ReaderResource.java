/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnr.rest;

import com.cnr.ejb.ReadersBean;
import com.cnr.model.Readers;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author gganuza
 */
@Stateless
@Path("/readers")
public class ReaderResource {

    @EJB
    private ReadersBean readersBean;

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) {
        eshareBean.saveUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }*/
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{readerId}")
    public String getReader(@PathParam("readerId") Long readerId) {
        Gson gson = new Gson();
        JsonObject jason = new JsonObject();
        Readers response = readersBean.findReder(readerId);

        if (response != null) {
            return gson.toJson(response);
        } else {
            jason.addProperty("codigoRetorno", 0);
            jason.addProperty("mensajeRetorno", "Reader no encontrado");
            return gson.toJson(jason);
        }
    }
    @POST
@Produces("application/json")
@Consumes("application/json")
@Path("/save")
public Response saveReader(String readerData) {
    Gson gson = new Gson();
    JsonObject jsonResponse = new JsonObject();

    try {
        Readers newReader = gson.fromJson(readerData, Readers.class);
        Readers savedReader = readersBean.saveReader(newReader);

        if (savedReader != null) {
            return Response.ok(gson.toJson(savedReader)).build();
        } else {
            jsonResponse.addProperty("codigoRetorno", 0);
            jsonResponse.addProperty("mensajeRetorno", "Error al guardar el lector");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(jsonResponse)).build();
        }
    } catch (Exception e) {
        jsonResponse.addProperty("codigoRetorno", 0);
        jsonResponse.addProperty("mensajeRetorno", "Error en la solicitud: " + e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(jsonResponse)).build();
    }
}


   /* @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/save")
    public String saveReader(String readerData) {
        Gson gson = new Gson();
        JsonObject jason = new JsonObject();

        try {
            // Convertir el JSON recibido a un objeto Readers
            Readers newReader = gson.fromJson(readerData, Readers.class);

            // Guardar el nuevo lector usando el bean correspondiente
            Readers savedReader = readersBean.saveReader(newReader);

            // Verificar si se guardó correctamente
            if (savedReader != null) {
                return gson.toJson(savedReader);
            } else {
                jason.addProperty("codigoRetorno", 0);
                jason.addProperty("mensajeRetorno", "Error al guardar el lector");
                return gson.toJson(jason);
            }
        } catch (Exception e) {
            jason.addProperty("codigoRetorno", 0);
            jason.addProperty("mensajeRetorno", "Error en la solicitud: " + e.getMessage());
            return gson.toJson(jason);
        }
    }*/

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/all")
    public String getReaders() {
        Gson gson = new Gson();
        JsonObject jason = new JsonObject();

        List<Readers> readersList = readersBean.findRederAll(); // Obtener la lista de lectores

        //Readers response = readersBean.findRederAll();
        if (readersList != null && !readersList.isEmpty()) {
            return gson.toJson(readersList); // Convertir la lista en JSON
        } else {
            jason.addProperty("codigoRetorno", 0);
            jason.addProperty("mensajeRetorno", "No se encontraron lectores.");
            return gson.toJson(jason);
        }
    }

}
