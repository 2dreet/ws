/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.web.jose.ws.rest;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author José
 */
@Path("/home")
public class HelloHorld {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject exibir() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("Nome", "Jose Augusto");
            return obj;
        } catch (JSONException e) {
            return null;
        }
    }
}
 