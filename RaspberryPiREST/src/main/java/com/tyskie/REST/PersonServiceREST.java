package com.tyskie.REST;

import com.tyskie.DAOs.PersonDAOImpl;
import com.tyskie.Domain.Person;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rest/person")
public class PersonServiceREST {
    @Inject
    private PersonDAOImpl personDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/show/all")
    public Response getPersons(){
        List<Person> personList = personDAO.findAll();

        JSONObject personObject = new JSONObject();
        JSONArray personArray = new JSONArray();

        for (Person person : personList) {
            JSONObject personInformation = createPersonObject(person);
            personArray.add(personInformation);
        }

        personObject.put("persons", personArray);

        return Response.status(200).entity(personObject).build();
    }

    private JSONObject createPersonObject(Person person) {
        int id = person.getId();
        String name = person.getName();
        int age = person.getAge();

        JSONObject personInformation = new JSONObject();
        personInformation.put("id", id);
        personInformation.put("name", name);
        personInformation.put("age", age);
        return personInformation;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/show/{id}")
    public Response getPersonById(@PathParam("id") int id){
        List<Person> personList = personDAO.findPersonById(id);

        JSONObject personObject = new JSONObject();

        for (Person person : personList) {
            personObject = createPersonObject(person);
        }

        return Response.status(200).entity(personObject).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create")
    public String createPerson(Person person){
        int id = person.getId();
        String name = person.getName();
        int age = person.getAge();

        personDAO.createPerson(id, name, age);

        boolean exists = personDAO.findPerson(id, name, age);

        if(exists) {
            return "Successfully created the person!";
        } else {
            return "Person not created.";
        }
    }
}
