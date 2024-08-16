package OGDataAccess.OGDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import OGDataAccess.OGIDAO;
import OGDataAccess.OGDTO.OGHormigaDTO;
import OGDataAccess.OGDataHelper.OGDataHelper;

public class OGHormigaDAO implements OGIDAO<OGHormigaDTO> {
    private Connection ogConnection;

    public OGHormigaDAO() {
        this.ogConnection = OGDataHelper.ogConection();
    }

    private void ensureConnectionOpen() throws SQLException {
        if (ogConnection == null || ogConnection.isClosed()) {
            ogConnection = OGDataHelper.ogConection();
        }
    }

    @Override
    public boolean ogCreate(OGHormigaDTO entity) throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        String sql = "INSERT INTO Hormiga (idSexo, idGenoAlimento, idIngestaNativa, idProvincia, tipoHormiga, nombre, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdSexo());
            preparedStatement.setInt(2, entity.getIdGenoAlimento());
            preparedStatement.setInt(3, entity.getIdIngestaNativa());
            preparedStatement.setInt(4, entity.getIdProvincia());
            preparedStatement.setString(5, entity.getTipoHormiga());
            preparedStatement.setString(6, entity.getNombre());
            preparedStatement.setString(7, entity.getEstado());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Hormiga", e);
        }
    }

    @Override
    public List<OGHormigaDTO> ogReadAll() throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        List<OGHormigaDTO> hormigas = new ArrayList<>();
        String sql = "SELECT idHormiga, idSexo, idGenoAlimento, idIngestaNativa, idProvincia, tipoHormiga, nombre, estado, FechaCreacion FROM Hormiga WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OGHormigaDTO hormiga = new OGHormigaDTO();
                hormiga.setIdHormiga(rs.getInt("idHormiga"));
                hormiga.setIdSexo(rs.getInt("idSexo"));
                hormiga.setIdGenoAlimento(rs.getInt("idGenoAlimento"));
                hormiga.setIdIngestaNativa(rs.getInt("idIngestaNativa"));
                hormiga.setIdProvincia(rs.getInt("idProvincia"));
                hormiga.setTipoHormiga(rs.getString("tipoHormiga"));
                hormiga.setNombre(rs.getString("nombre"));
                hormiga.setEstado(rs.getString("estado"));
                hormiga.setFechaCreacion(rs.getString("FechaCreacion"));
                hormigas.add(hormiga);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Hormigas", e);
        }
        return hormigas;
    }

    @Override
    public boolean ogUpdate(OGHormigaDTO entity) throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        String sql = "UPDATE Hormiga SET idSexo = ?, idGenoAlimento = ?, idIngestaNativa = ?, idProvincia = ?, tipoHormiga = ?, nombre = ?, estado = ? WHERE idHormiga = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdSexo());
            preparedStatement.setInt(2, entity.getIdGenoAlimento());
            preparedStatement.setInt(3, entity.getIdIngestaNativa());
            preparedStatement.setInt(4, entity.getIdProvincia());
            preparedStatement.setString(5, entity.getTipoHormiga());
            preparedStatement.setString(6, entity.getNombre());
            preparedStatement.setString(7, entity.getEstado());
            preparedStatement.setInt(8, entity.getIdHormiga());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Hormiga", e);
        }
    }

    @Override
    public boolean ogDelete(int id) throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        String sql = "UPDATE Hormiga SET estado = 'X' WHERE idHormiga = ?";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar Hormiga", e);
        }
    }

    @Override
    public OGHormigaDTO ogReadBy(Integer id) throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        OGHormigaDTO hormiga = null;
        String sql = "SELECT idHormiga, idSexo, idGenoAlimento, idIngestaNativa, idProvincia, tipoHormiga, nombre, estado, FechaCreacion FROM Hormiga WHERE idHormiga = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = ogConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    hormiga = new OGHormigaDTO();
                    hormiga.setIdHormiga(rs.getInt("idHormiga"));
                    hormiga.setIdSexo(rs.getInt("idSexo"));
                    hormiga.setIdGenoAlimento(rs.getInt("idGenoAlimento"));
                    hormiga.setIdIngestaNativa(rs.getInt("idIngestaNativa"));
                    hormiga.setIdProvincia(rs.getInt("idProvincia"));
                    hormiga.setTipoHormiga(rs.getString("tipoHormiga"));
                    hormiga.setNombre(rs.getString("nombre"));
                    hormiga.setEstado(rs.getString("estado"));
                    hormiga.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Hormiga por ID", e);
        }
        return hormiga;
    }

    public String determinarEvolucion(String tipoHormigaAnterior, String tipoAlimento) {
        String nuevaEvolucion = null;

        // Determinar la evolución esperada según el tipo de hormiga anterior y el tipo
        // de alimento
        String evolucionEsperada = determinarEvolucionEsperada(tipoHormigaAnterior, tipoAlimento);

        // Consulta SQL para obtener el nombre de la evolución desde CatalogoEvoluciones
        String query = "SELECT nombreEvolucion FROM CatalogoEvoluciones WHERE nombreEvolucion = ?";

        try (Connection conn = OGDataHelper.ogConection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, evolucionEsperada);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nuevaEvolucion = rs.getString("nombreEvolucion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si se encontró una nueva evolución, la devolvemos; de lo contrario,
        // devolvemos el tipo de hormiga anterior
        return nuevaEvolucion != null ? nuevaEvolucion : tipoHormigaAnterior;
    }

    private String determinarEvolucionEsperada(String tipoHormigaAnterior, String tipoAlimento) {
        switch (tipoHormigaAnterior) {
            case "Larva":
                if (tipoAlimento.equals("GenoAlimento")) {
                    return "Obrera";
                } else if (tipoAlimento.equals("IngestaNativa")) {
                    return "Soldado";
                }
                break;
            case "Obrera":
                if (tipoAlimento.equals("GenoAlimento")) {
                    return "Reina";
                }
                break;
            case "Soldado":
                if (tipoAlimento.equals("IngestaNativa")) {
                    return "Guerrero";
                }
                break;
            // Puedes agregar más casos según sea necesario
        }
        return tipoHormigaAnterior; // Si no hay evolución, se devuelve el tipo actual
    }

}
