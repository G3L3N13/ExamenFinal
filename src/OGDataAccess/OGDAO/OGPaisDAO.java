package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDTO.OGPaisDTO;
import OGDataAccess.OGDataHelper.OGDataHelper;

public class OGPaisDAO implements OGIDAO<OGPaisDTO> {
    private Connection ogConnection;

    public OGPaisDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    @Override
    public boolean ogCreate(OGPaisDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, nombre, descripcion) VALUES (1, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear País", e);
        }
    }

    @Override
    public List<OGPaisDTO> ogReadAll() throws Exception {
        List<OGPaisDTO> paises = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 1";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGPaisDTO pais = new OGPaisDTO();
                pais.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                pais.setNombre(rs.getString("nombre"));
                pais.setDescripcion(rs.getString("descripcion"));
                pais.setEstado(rs.getString("estado"));
                pais.setFechaCreacion(rs.getString("FechaCreacion"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Países", e);
        }
        return paises;
    }

    @Override
    public boolean ogUpdate(OGPaisDTO entity) throws Exception {
        String sql = "UPDATE CatalogoGeografia SET nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar País", e);
        }
    }

    @Override
    public boolean ogDelete(int id) throws Exception {
        String sql = "UPDATE CatalogoGeografia SET estado = 'X' WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar País", e);
        }
    }

    @Override
    public OGPaisDTO ogReadBy(Integer id) throws Exception {
        OGPaisDTO pais = null;
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pais = new OGPaisDTO();
                    pais.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    pais.setNombre(rs.getString("nombre"));
                    pais.setDescripcion(rs.getString("descripcion"));
                    pais.setEstado(rs.getString("estado"));
                    pais.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer País por ID", e);
        }
        return pais;
    }
}
