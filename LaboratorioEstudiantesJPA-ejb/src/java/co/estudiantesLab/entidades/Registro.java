package co.estudiantesLab.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "REGISTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r"),
    @NamedQuery(name = "Registro.findById", query = "SELECT r FROM Registro r WHERE r.id = :id")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CODIGOCURSO", referencedColumnName = "CODIGO")
    @ManyToOne
    private Curso codigocurso;
    @JoinColumn(name = "CODIGOESTUDIANTE", referencedColumnName = "CODIGO")
    @ManyToOne
    private Estudiante codigoestudiante;

    public Registro() {
    }

    public Registro(Integer id) {
        this.id = id;
    }

    public Registro(Integer id, Curso codigocurso, Estudiante codigoestudiante) {
        this.id = id;
        this.codigocurso = codigocurso;
        this.codigoestudiante = codigoestudiante;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Curso getCodigocurso() {
        return codigocurso;
    }

    public void setCodigocurso(Curso codigocurso) {
        this.codigocurso = codigocurso;
    }

    public Estudiante getCodigoestudiante() {
        return codigoestudiante;
    }

    public void setCodigoestudiante(Estudiante codigoestudiante) {
        this.codigoestudiante = codigoestudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.estudiantesLab.entidades.Registro[ id=" + id + " ]";
    }
    
}
