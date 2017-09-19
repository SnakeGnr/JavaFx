/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.ConnectionFactory;

/**
 *
 * @author SnakeGnr
 */
public class PessoaDao {

    private Connection con;
    private ResultSet rs;

    public PessoaDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public boolean add(Pessoa p) throws SQLException {
        String sql = "insert into Pessoaa (nome, email, senha, foto) values (?,?,?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getSenha());
            stmt.setString(4, p.getFoto());

            stmt.execute();
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            con.close();

        }
    }

    public boolean update(Pessoa p) throws SQLException {
        String sql = " update Pessoaa Set nome =?, email=?, senha=?, foto=? where id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getSenha());
            stmt.setString(4, p.getFoto());
            stmt.setLong(4, p.getId());

            stmt.execute();
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            con.close();
        }
    }

    public boolean delete(Pessoa p) throws SQLException {
        String sql = "Delete from Pessoaa where id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.close();
        }
    }

    public List<Pessoa> getList() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "Select * from Pessoaa";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                p.setFoto(rs.getString("foto"));
                pessoas.add(p);
            }
            stmt.execute();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            System.out.println("Erro, lista nao retornada!");
            return null;
        } finally {
            rs.close();
            con.close();

        }
        return pessoas;
    }

}
