package OGDataAccess.OGDTO;

public class OGPaisDTO {
    private Integer idCatalogoGeo;
    private String nombre;
    private String descripcion;
    private String estado;
    private String fechaCreacion;

    public Integer getIdCatalogoGeo() {
        return idCatalogoGeo;
    }

    public void setIdCatalogoGeo(Integer idCatalogoGeo) {
        this.idCatalogoGeo = idCatalogoGeo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public OGPaisDTO() {
    }

    public OGPaisDTO(Integer idCatalogoGeo, String nombre, String descripcion, String estado, String fechaCreacion) {
        this.idCatalogoGeo = idCatalogoGeo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
}
