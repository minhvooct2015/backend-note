package axonactive.trainning.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import axonactive.trainning.entity.Note;
import axonactive.trainning.entity.NoteDTO;

@RequestScoped
public class NoteService {

    @PersistenceContext
    EntityManager em;

    public Note persist(String contents) {
        Note note = new Note();
        note.setContents(contents);
        note.setCreationDate(new Date());
        em.persist(note);
        return note;
    }

    public Note findById(Long id) {
        TypedQuery<Note> query = em.createNamedQuery(Note.GET_BY_ID, Note.class);
        query.setParameter("id", id);
        Note note = query.getResultStream().findFirst().orElse(null);
        return note;
    }

    public List<Note> findByContent(String content) {
        TypedQuery<Note> query = em.createNamedQuery(Note.GET_BY_CONTENT, Note.class);
        StringBuilder queryContent = new StringBuilder();
        queryContent.append("%");
        queryContent.append(content.toLowerCase());
        queryContent.append("%");
        query.setParameter("contents", queryContent.toString());
        return query.getResultList();
    }

    public List<Note> getAll(int pageSize) {
        TypedQuery<Note> query = em.createNamedQuery(Note.GET_ALL, Note.class);
//        query.setParameter("size", pageSize);
        return query.setMaxResults(pageSize).getResultList();
    }

    public Note updateNote(Long id, String contents) {
        Note note = findById(id);
        note.setContents(contents);
        return em.merge(note);
    }

    public void delete(Long id) {
        Note note = findById(id);
        if (Objects.nonNull(note)) {
            em.remove(note);
        }
    }

    public String convertJson(List<Note> notes) {
        List<NoteDTO> dtos = convertDTOs(notes);
        String json = convertObjectJson(dtos);
        return json;
    }

    public String convertObjectJson(Object dtos) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(dtos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public List<NoteDTO> convertDTOs(List<Note> notes) {
        List<NoteDTO> dtos = new ArrayList<NoteDTO>();
        notes.forEach(n -> {
            NoteDTO note = convertDTO(n);
            dtos.add(note);
        });
        return dtos;
    }

    public NoteDTO convertDTO(Note note) {
        NoteDTO noteDto = new NoteDTO();
        noteDto.setId(note.getId());
        noteDto.setContents(note.getContents());
        noteDto.setCreationDate(note.getCreationDate());
        return noteDto;
    }

}
