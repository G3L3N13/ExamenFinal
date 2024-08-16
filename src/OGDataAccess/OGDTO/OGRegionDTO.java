package OGDataAccess.OGDTO;

public class OGRegionDTO {
    private Integer idCatalogoGeo;
    private Integer idCatalogoTipoGeo;
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

    public Integer getIdCatalogoTipoGeo() {
        return idCatalogoTipoGeo;
    }

    public void setIdCatalogoTipoGeo(Integer idCatalogoTipoGeo) {
        this.idCatalogoTipoGeo = idCatalogoTipoGeo;
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

    public OGRegionDTO() {
    }

    public OGRegionDTO(Integer idCatalogoGeo, Integer idCatalogoTipoGeo, String nombre, String descripcion,
            String estado, String fechaCreacion) {
        this.idCatalogoGeo = idCatalogoGeo;
        this.idCatalogoTipoGeo = idCatalogoTipoGeo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
}
