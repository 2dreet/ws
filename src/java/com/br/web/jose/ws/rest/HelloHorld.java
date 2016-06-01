/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.web.jose.ws.rest;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.type.CustomType;

/**
 *
 * @author José
 */
@Path("/home")
public class HelloHorld {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public User getTrackInJSON() {
        User user = new User();
        user.name = "Asze";
        return user;
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(User user) {
        String result = "Track saved : " + user;
        return Response.status(201).entity(result).build();

    }

    public class User {

        public String name;

        public User() {
            super();
        }

        @JsonCreator
        public User(@JsonProperty("name") String name) {
            this.name = name;
        }
    }
}
