package daw.mvc.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import daw.mvc.modelo.Persona;

public class PersonaDaoMysqlAndOracleDSImpl implements PersonaDao {
	private DataSource ds;
	
	public PersonaDaoMysqlAndOracleDSImpl() {
	
	}
	
	public PersonaDaoMysqlAndOracleDSImpl(DataSource ds) {
		this.ds = ds;
	}

	public DataSource getDs() {
		return ds;
	}
	
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void guardar(Persona p){
		Connection cn=null;
		try {
			cn=ds.getConnection();
			PreparedStatement pst = 
				cn.prepareStatement("insert into PERSONAS values(?,?,?)");
			pst.setString(1, p.getDni());
			pst.setString(2, p.getNombre());
			pst.setString(3, p.getApellidos());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void borrar(Persona p){
		Connection cn=null;
		try {
			cn=ds.getConnection();
			PreparedStatement pst = 
				cn.prepareStatement("delete from PERSONAS where dni=?");
			pst.setString(1, p.getDni());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Persona> listar(){
		List<Persona> lista=new ArrayList<Persona>();
		Connection cn=null;
		try {
			cn=ds.getConnection();
			PreparedStatement pst = 
				cn.prepareStatement("select * from PERSONAS");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
				lista.add(new Persona(rs.getString("dni"),
										rs.getString("nombre"),
										rs.getString("apellidos")));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public Persona buscar(String dni){
		Persona p=null;
		Connection cn=null;
		try {
			cn=ds.getConnection();
			PreparedStatement pst = 
				cn.prepareStatement("select * from PERSONAS where dni=?");
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				p=new Persona(rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("apellidos"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
}
