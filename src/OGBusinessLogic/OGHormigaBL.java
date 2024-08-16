package OGBusinessLogic;

import java.util.List;
import java.util.Random;

import OGDataAccess.OGDAO.OGHormigaDAO;
import OGDataAccess.OGDAO.OGIngestaNativaDAO;
import OGDataAccess.OGDAO.OGProvinciaDAO;
import OGDataAccess.OGDAO.OGRegionDAO;
import OGDataAccess.OGDTO.OGHormigaDTO;
import OGDataAccess.OGDTO.OGIngestaNativaDTO;
import OGDataAccess.OGDTO.OGProvinciaDTO;
import OGDataAccess.OGDTO.OGRegionDTO;

public class OGHormigaBL {
    
    private OGHormigaDAO hormigaDAO;
    private OGIngestaNativaDAO ingestaNativaDAO;
    
    
    public OGHormigaBL() {
        this.hormigaDAO = new OGHormigaDAO();
        this.ingestaNativaDAO = new OGIngestaNativaDAO(); 
        this.hormigaDAO = new OGHormigaDAO();
        this.provinciaDAO = new OGProvinciaDAO();
    }
    
    
    public String obtenerNombreIngestaNativa(int idIngestaNativa) throws Exception{
        OGIngestaNativaDTO ingestaNativa = ingestaNativaDAO.ogReadBy(idIngestaNativa);
        return ingestaNativa != null ? ingestaNativa.getNombre().toUpperCase(): "N/A";
    }

    
    public String obtenerSexo(int idHormiga) throws Exception {
        OGHormigaDTO hormiga = hormigaDAO.ogReadBy(idHormiga);
        return hormiga != null ? hormiga.getNombre().toUpperCase() : "N/A";
    }
    
    
    public String obtenerGenoAlimento(int idGenoAlimento) throws Exception {
        OGIngestaNativaDTO genoAlimento = ingestaNativaDAO.ogReadBy(idGenoAlimento);
        return genoAlimento != null ? genoAlimento.getNombre().toUpperCase() : "N/A";
    }

    public String obtenerProvincia(int idHormiga) throws Exception {
        OGHormigaDTO hormiga = hormigaDAO.ogReadBy(idHormiga);
        return hormiga != null ? hormiga.getNombre().toUpperCase() : "N/A";
    }

    public String obtenerTipoHormiga(int idHormiga) throws Exception {
        OGHormigaDTO hormiga = hormigaDAO.ogReadBy(idHormiga);
        return hormiga != null ? hormiga.getTipoHormiga().toUpperCase() : "N/A";
    }    
    private OGProvinciaDAO provinciaDAO;

    
    // Método para obtener una provincia aleatoria
    public String generarUbicacionAleatoria() throws Exception {
        List<OGProvinciaDTO> provincias = provinciaDAO.ogReadAll(); // Obtener todas las provincias
        if (provincias.isEmpty()) {
            return "Ubicación no disponible"; // Manejo de caso donde no hay provincias
        }

        Random random = new Random();
        int index = random.nextInt(provincias.size()); // Genera un índice aleatorio
        return provincias.get(index).getNombre(); // Devuelve el nombre de la provincia aleatoria
    }


    public boolean crearHormigaLarva(String nombre) throws Exception {
        // Validaciones de negocio
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre de la hormiga larva es obligatorio.");
        }
    
        // Obtener una provincia aleatoria
        List<OGProvinciaDTO> provincias = provinciaDAO.ogReadAll();
        if (provincias.isEmpty()) {
            throw new Exception("No hay provincias disponibles para asignar a la hormiga larva.");
        }
        Random random = new Random();
        int indexProvincia = random.nextInt(provincias.size());
        OGProvinciaDTO provinciaAleatoria = provincias.get(indexProvincia);
    
       
        OGHormigaDTO hormigaLarva = new OGHormigaDTO();
        hormigaLarva.setTipoHormiga("Larva");
        hormigaLarva.setNombre(nombre);
        hormigaLarva.setIdSexo(3); 
        hormigaLarva.setIdProvincia(provinciaAleatoria.getIdCatalogoGeo()); // ID de la provincia aleatoria
        hormigaLarva.setEstado("VIVA");
    
        
        return hormigaDAO.ogCreate(hormigaLarva);
    }

    public boolean actualizarHormiga(OGHormigaDTO hormiga) throws Exception {
        
        if (hormiga.getIdHormiga() == null) {
            throw new Exception("El ID de la hormiga es obligatorio para la actualización.");
        }
        
        return hormigaDAO.ogUpdate(hormiga);
    }

    public boolean eliminarHormiga(int idHormiga) throws Exception {
       
        if (idHormiga <= 0) {
            throw new Exception("ID de hormiga inválido.");
        }
        

        
        return hormigaDAO.ogDelete(idHormiga);
    }

    public OGHormigaDTO obtenerHormigaPorId(int idHormiga) throws Exception {
        
        return hormigaDAO.ogReadBy(idHormiga);
    }

    public String obtenerRegion(Integer idProvincia) throws Exception {
        OGRegionDAO regionDAO = new OGRegionDAO(); 
        OGRegionDTO region = regionDAO.ogReadBy(idProvincia); 

            if (region != null) {
                 return region.getNombre(); 
            }
    
        return "N/A"; 
    }
}
        