package com.sistemanotas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
    // Configurações de conexão — ajuste conforme necessário
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_notas";
    private static final String USUARIO = "root";
    private static final String SENHA = "Pantera33@"; // troque pela sua senha real

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, nota1, nota2, nota3) VALUES (?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setDouble(2, aluno.getNota1());
            stmt.setDouble(3, aluno.getNota2());
            stmt.setDouble(4, aluno.getNota3());

            stmt.executeUpdate();
            System.out.println("📚 Aluno salvo com sucesso no MySQL!");

        } catch (SQLException e) {
            System.out.println("🚨 Erro ao salvar aluno no banco: " + e.getMessage());
        }
    }
}
