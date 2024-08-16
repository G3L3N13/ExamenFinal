package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDataHelper.OGDataHelper;
import OGDataAccess.OGDTO.OGSexoDTO;

public class OGSexoDAO implements OGIDAO<OGSexoDTO> {
    private Connection ogConnection;

    public OGSexoDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    @Override
    public boolean ogCreate(OGSexoDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoAlimento (idCatalogoTipoAl, nombre, descripcion) VALUES (3, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Sexo", e);
        }
    }

    @Override
    public List<OGSexoDTO> ogReadAll() throws Exception {
        List<OGSexoDTO> sexos = new ArrayList<>();
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE estado = 'A' AND idCatalogoTipoAl = 3";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGSexoDTO sexo = new OGSexoDTO();
                sexo.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                sexo.setNombre(rs.getString("nombre"));
                sexo.setDescripcion(rs.getString("descripcion"));
                sexo.setEstado(rs.getString("estado"));
                sexo.setFechaCreacion(rs.getString("FechaCreacion"));
                sexos.add(sexo);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Sexos", e);
        }
        return sexos;
    }

    @Override
    public boolean ogUpdate(OGSexoDTO entity) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET nombre = ?, descripcion = ? WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoAl());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Sexo", e);
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
            throw new Exception("Error al eliminar Sexo", e);
        }
    }

    @Override
    public OGSexoDTO ogReadBy(Integer id) throws Exception {
        OGSexoDTO sexo = null;
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE idCatalogoAl = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    sexo = new OGSexoDTO();
                    sexo.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                    sexo.setNombre(rs.getString("nombre"));
                    sexo.setDescripcion(rs.getString("descripcion"));
                    sexo.setEstado(rs.getString("estado"));
                    sexo.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Sexo por ID", e);
        }
        return sexo;
    }
}
