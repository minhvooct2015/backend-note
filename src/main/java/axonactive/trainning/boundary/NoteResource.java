package axonactive.trainning.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import axonactive.trainning.control.NoteService;
import axonactive.trainning.entity.Note;
import axonactive.trainning.entity.NoteDTO;

@Path("/notes")
@Transactional
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class NoteResource{
  
    
    @Inject
    NoteService noteService;

    @PersistenceContext
    EntityManager em;

    @POST
    public Response create(@FormParam("contents") String contents) {
        Note note = noteService.persist(contents);
        return Response.ok(noteService.convertObjectJson(note)).build();
    }

    @GET
    public Response findByContents(@QueryParam("contents") String contents, @QueryParam("size") @DefaultValue("8") int pageSize)
            throws JsonProcessingException {
        List<Note> notes;
        if (contents == null || contents.isEmpty() || contents.isBlank()) {
            notes = noteService.getAll(pageSize);
        } else {
            notes = noteService.findByContent(contents);
        }
        String json = noteService.convertJson(notes);
        return Response.ok(json).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) throws JsonProcessingException {
        Note note = noteService.findById(id);
//      String message = MessageFormat.format("Cannot find address verification configuration property {0} for the tenant {1}.", propertyKey, tenantId);
        NoteDTO noteDto = noteService.convertDTO(note);
        String json = noteService.convertObjectJson(noteDto);
        return Response.ok(json).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @FormParam("contents") String contents) {
        Note note = noteService.updateNote(id, contents);
        String json = noteService.convertObjectJson(note);
        return Response.ok(json).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        noteService.delete(id);
        return Response.ok().build();
    }

}