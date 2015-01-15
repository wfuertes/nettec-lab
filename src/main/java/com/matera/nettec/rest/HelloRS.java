/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.nettec.rest;

import com.matera.nettec.domain.CalcData;
import com.matera.nettec.domain.CalcResponse;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 *
 * @author wbatista
 */
@Path("hello")
public class HelloRS {

    private static final DynamicStringProperty helloProp = DynamicPropertyFactory.getInstance().getStringProperty(
        "hello.property", "Not loaded");

    @GET
    public Response sayHello() {

        return Response.ok("I'm alive here:" + helloProp.get()).build();
    }

    @POST
    @Path("/calc")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doCalc(CalcData data) {

        double result = data.getSalary() * data.getPercentage() * CalcData.TAX;
        return Response.ok(new CalcResponse(result, "Caculated successful")).build();
    }
}
