package axonactive.trainning.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = Note.GET_BY_ID, query = "SELECT no FROM Note no WHERE no.id = :id"),
        @NamedQuery(name = Note.GET_BY_CONTENT, query = "SELECT no FROM Note no WHERE LOWER(no.contents) like :contents"),
        @NamedQuery(name = Note.GET_ALL, query = "SELECT no FROM Note no ORDER BY no.id ASC")

})
public class Note{
    
    private static final String QUALIFIER = "axonactive.trainning.entity.Note";

    public static final String GET_BY_ID = QUALIFIER + "getById";
    public static final String GET_BY_CONTENT = QUALIFIER + "getByContent";
    public static final String GET_ALL = QUALIFIER + "getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String contents;
    
    @Column(name = "creation_date")
    private Date creationDate;
    
    public String getContents() {
        return contents;
    }
    
    public void setContents(String contents) {
        this.contents = contents;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
