package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDataHelper.OGDataHelper;
import OGDataAccess.OGDTO.OGProvinciaDTO;

public class OGProvinciaDAO implements OGIDAO<OGProvinciaDTO> {
    private Connection ogConnection;

    public OGProvinciaDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    @Override
    public boolean ogCreate(OGProvinciaDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, idRegion, nombre, descripcion) VALUES (3, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdRegion());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Provincia", e);
        }
    }

    @Override
    public List<OGProvinciaDTO> ogReadAll() throws Exception {
        List<OGProvinciaDTO> provincias = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, idRegion, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 3";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGProvinciaDTO provincia = new OGProvinciaDTO();
                provincia.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                provincia.setIdRegion(rs.getInt("idRegion"));
                provincia.setNombre(rs.getString("nombre"));
                provincia.setDescripcion(rs.getString("descripcion"));
                provincia.setEstado(rs.getString("estado"));
                provincia.setFechaCreacion(rs.getString("FechaCreacion"));
                provincias.add(provincia);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Provincias", e);
        }
        return provincias;
    }

    @Override
    public boolean ogUpdate(OGProvinciaDTO entity) throws Exception {
        String sql = "UPDATE CatalogoGeografia SET idRegion = ?, nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdRegion());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());
            preparedStatement.setInt(4, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Provincia", e);
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
            throw new Exception("Error al eliminar Provincia", e);
        }
    }

    @Override
    public OGProvinciaDTO ogReadBy(Integer id) throws Exception {
        OGProvinciaDTO provincia = null;
        String sql = "SELECT idCatalogoGeo, idRegion, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    provincia = new OGProvinciaDTO();
                    provincia.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    provincia.setIdRegion(rs.getInt("idRegion"));
                    provincia.setNombre(rs.getString("nombre"));
                    provincia.setDescripcion(rs.getString("descripcion"));
                    provincia.setEstado(rs.getString("estado"));
                    provincia.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Provincia por ID", e);
        }
        return provincia;
    }
}
