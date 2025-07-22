import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoMySQL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sistema_notas";
        String usuario = "root";
        String senha = "Pantera33@"; // Troque pela sua senha real

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("✅ Conexão bem-sucedida!");

            String sql = "INSERT INTO alunos (nome, nota) VALUES (?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "Maria");
                stmt.setDouble(2, 9.3);
                stmt.executeUpdate();
                System.out.println("🎓 Dados inseridos com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("🚨 Erro de conexão ou inserção: " + e.getMessage());
        }
    }
}
