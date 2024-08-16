package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDTO.OGIngestaNativaDTO;
import OGDataAccess.OGDataHelper.OGDataHelper;

public class OGIngestaNativaDAO implements OGIDAO<OGIngestaNativaDTO> {
    private Connection ogConnection;

    public OGIngestaNativaDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    @Override
    public boolean ogCreate(OGIngestaNativaDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoAlimento (idCatalogoTipoAl, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear IngestaNativa", e);
        }
    }

    @Override
    public List<OGIngestaNativaDTO> ogReadAll() throws Exception {
        List<OGIngestaNativaDTO> ingestas = new ArrayList<>();
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE estado = 'A' AND idCatalogoTipoAl = 1";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGIngestaNativaDTO ingesta = new OGIngestaNativaDTO();
                ingesta.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                ingesta.setNombre(rs.getString("nombre"));
                ingesta.setDescripcion(rs.getString("descripcion"));
                ingesta.setEstado(rs.getString("estado"));
                ingesta.setFechaCreacion(rs.getString("FechaCreacion"));
                ingestas.add(ingesta);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer IngestaNativa", e);
        }
        return ingestas;
    }

    @Override
    public boolean ogUpdate(OGIngestaNativaDTO entity) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET nombre = ?, descripcion = ? WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoAl());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar IngestaNativa", e);
        }
    }

    @Override
    public boolean ogDelete(int id) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET estado = 'X' WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar IngestaNativa", e);
        }
    }

    @Override
    public OGIngestaNativaDTO ogReadBy(Integer id) throws Exception {
        OGIngestaNativaDTO ingesta = null;
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE idCatalogoAl = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ingesta = new OGIngestaNativaDTO();
                    ingesta.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                    ingesta.setNombre(rs.getString("nombre"));
                    ingesta.setDescripcion(rs.getString("descripcion"));
                    ingesta.setEstado(rs.getString("estado"));
                    ingesta.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer IngestaNativa por ID", e);
        }
        return ingesta;
    }
}
