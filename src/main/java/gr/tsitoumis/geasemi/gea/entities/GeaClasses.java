package gr.tsitoumis.geasemi.gea.entities;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity(name = "GeaClasses")
@Table(name = "gea_classes")
@XmlRootElement
public class GeaClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Lob
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;
    @Basic(optional = false)
    @Column(name = "packageID")
    private int packageID;
    @Basic(optional = false)
    @Lob
    @Column(name = "projectName", columnDefinition = "TEXT")
    private String projectName;
    @Basic(optional = false)
    @Column(name = "cohesion")
    private double cohesion;
    @Basic(optional = false)
    @Column(name = "coupling")
    private double coupling;
    @Basic(optional = false)
    @Column(name = "isNew")
    private boolean isNew;

    public GeaClasses() {
    }

    public GeaClasses(Long id) {
        this.id = id;
    }

    public GeaClasses(Long id, String name, int packageID, String projectName, double cohesion, double coupling,
                      boolean isNew) {
        this.id = id;
        this.name = name;
        this.packageID = packageID;
        this.projectName = projectName;
        this.cohesion = cohesion;
        this.coupling = coupling;
        this.isNew = isNew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getCohesion() {
        return cohesion;
    }

    public void setCohesion(double cohesion) {
        this.cohesion = cohesion;
    }

    public double getCoupling() {
        return coupling;
    }

    public void setCoupling(double coupling) {
        this.coupling = coupling;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
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
        if (!(object instanceof GeaClasses)) {
            return false;
        }
        GeaClasses other = (GeaClasses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.sdk4ed.uom.td.domain.GeaClasses[ id=" + id + " ]";
    }

}