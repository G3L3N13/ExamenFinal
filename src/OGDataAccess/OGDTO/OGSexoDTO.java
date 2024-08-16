package OGDataAccess.OGDTO;

public class OGSexoDTO {
    private Integer idCatalogoAl;
    private String nombre;
    private String descripcion;
    private String estado;
    private String fechaCreacion;

    public Integer getIdCatalogoAl() {
        return idCatalogoAl;
    }

    public void setIdCatalogoAl(Integer idCatalogoAl) {
        this.idCatalogoAl = idCatalogoAl;
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

    public OGSexoDTO() {
    }

    public OGSexoDTO(Integer idCatalogoAl, String nombre, String descripcion, String estado, String fechaCreacion) {
        this.idCatalogoAl = idCatalogoAl;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
}
