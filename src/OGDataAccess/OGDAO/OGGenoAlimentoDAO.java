package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDTO.OGGenoAlimentoDTO;
import OGDataAccess.OGDataHelper.OGDataHelper;

public class OGGenoAlimentoDAO implements OGIDAO<OGGenoAlimentoDTO> {
    private Connection ogConnection;

    public OGGenoAlimentoDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    @Override
    public boolean ogCreate(OGGenoAlimentoDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoAlimento (idCatalogoTipoAl, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdCatalogoAl());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear GenoAlimento", e);
        }
    }

    @Override
    public List<OGGenoAlimentoDTO> ogReadAll() throws Exception {
        List<OGGenoAlimentoDTO> genoAlimentos = new ArrayList<>();
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE estado = 'A' AND idCatalogoTipoAl = 2";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGGenoAlimentoDTO genoAlimento = new OGGenoAlimentoDTO();
                genoAlimento.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                genoAlimento.setNombre(rs.getString("nombre"));
                genoAlimento.setDescripcion(rs.getString("descripcion"));
                genoAlimento.setEstado(rs.getString("estado"));
                genoAlimento.setFechaCreacion(rs.getString("FechaCreacion"));
                genoAlimentos.add(genoAlimento);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer GenoAlimentos", e);
        }
        return genoAlimentos;
    }

    @Override
    public boolean ogUpdate(OGGenoAlimentoDTO entity) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET nombre = ?, descripcion = ? WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoAl());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar GenoAlimento", e);
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
            throw new Exception("Error al eliminar GenoAlimento", e);
        }
    }

    @Override
    public OGGenoAlimentoDTO ogReadBy(Integer id) throws Exception {
        OGGenoAlimentoDTO genoAlimento = null;
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE idCatalogoAl = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    genoAlimento = new OGGenoAlimentoDTO();
                    genoAlimento.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                    genoAlimento.setNombre(rs.getString("nombre"));
                    genoAlimento.setDescripcion(rs.getString("descripcion"));
                    genoAlimento.setEstado(rs.getString("estado"));
                    genoAlimento.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer GenoAlimento por ID", e);
        }
        return genoAlimento;
    }
}
