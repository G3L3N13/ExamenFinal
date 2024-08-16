package OGDataAccess.OGDTO;

public class OGHormigaDTO {
    private Integer idHormiga;
    private Integer idSexo;
    private Integer idGenoAlimento;
    private Integer idIngestaNativa;
    private Integer idProvincia;
    private String tipoHormiga;
    private String nombre;
    private String estado;
    private String fechaCreacion;

    public OGHormigaDTO(Integer idHormiga, Integer idSexo, Integer idGenoAlimento, Integer idIngestaNativa,
            Integer idProvincia, String tipoHormiga, String nombre, String estado, String fechaCreacion) {
        this.idHormiga          = idHormiga;
        this.idSexo             = idSexo;
        this.idGenoAlimento     = idGenoAlimento;
        this.idIngestaNativa    = idIngestaNativa;
        this.idProvincia        = idProvincia;
        this.tipoHormiga        = tipoHormiga;
        this.nombre             = nombre;
        this.estado             = estado;
        this.fechaCreacion      = fechaCreacion;
    }

    public OGHormigaDTO(String regNro, String tipoHormiga, String ubicacion, String sexo, String genoAlimento, String ingestaNativa, String estado) {
      
    }

    public OGHormigaDTO() {
        //TODO Auto-generated constructor stub
    }

    public Integer getIdHormiga() {
        return idHormiga;
    }

    public void setIdHormiga(Integer idHormiga) {
        this.idHormiga = idHormiga;
    }

    public Integer getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
    }

    public Integer getIdGenoAlimento() {
        return idGenoAlimento;
    }

    public void setIdGenoAlimento(Integer idGenoAlimento) {
        this.idGenoAlimento = idGenoAlimento;
    }

    public Integer getIdIngestaNativa() {
        return idIngestaNativa;
    }

    public void setIdIngestaNativa(Integer idIngestaNativa) {
        this.idIngestaNativa = idIngestaNativa;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getTipoHormiga() {
        return tipoHormiga;
    }

    public void setTipoHormiga(String tipoHormiga) {
        this.tipoHormiga = tipoHormiga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

  
}
