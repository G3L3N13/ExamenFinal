package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDataHelper.OGDataHelper;
import OGDataAccess.OGDTO.OGRegionDTO;

public class OGRegionDAO implements OGIDAO<OGRegionDTO> {
    private Connection ogConnection;

    public OGRegionDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    @Override
    public boolean ogCreate(OGRegionDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, idRegion, nombre, descripcion) VALUES (2, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdCatalogoGeo());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Región", e);
        }
    }

    @Override
    public List<OGRegionDTO> ogReadAll() throws Exception {
        List<OGRegionDTO> regiones = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 2";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGRegionDTO region = new OGRegionDTO();
                region.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                region.setNombre(rs.getString("nombre"));
                region.setDescripcion(rs.getString("descripcion"));
                region.setEstado(rs.getString("estado"));
                region.setFechaCreacion(rs.getString("FechaCreacion"));
                regiones.add(region);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Regiones", e);
        }
        return regiones;
    }

    @Override
    public boolean ogUpdate(OGRegionDTO entity) throws Exception {
        String sql = "UPDATE CatalogoGeografia SET nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Región", e);
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
            throw new Exception("Error al eliminar Región", e);
        }
    }

    @Override
    public OGRegionDTO ogReadBy(Integer id) throws Exception {
        OGRegionDTO region = null;
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    region = new OGRegionDTO();
                    region.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    region.setNombre(rs.getString("nombre"));
                    region.setDescripcion(rs.getString("descripcion"));
                    region.setEstado(rs.getString("estado"));
                    region.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Región por ID", e);
        }
        return region;
    }
}
