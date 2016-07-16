package com.geekday.web;

import com.geekday.account.AccountService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by shreyas on 16/7/16.
 */
@Path("/account")
public class AccountResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccount(@PathParam("id") String id)
    {
        return new AccountService().getAccount(id).toString();
    }
}
