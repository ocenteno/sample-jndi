package daw.mvc.persistencia;

import javax.sql.DataSource;

public class FabricaPersonaDao {
	//private static FabricaPersonaDao instance=new FabricaPersonaDao();
	
	private static FabricaPersonaDao instance;
	
	static {
		instance=new FabricaPersonaDao();
	}
	private FabricaPersonaDao() {}
	
	public static FabricaPersonaDao getInstance() {
		return instance;
	}
	public PersonaDao getDao(DataSource ds){
		return new PersonaDaoMysqlAndOracleDSImpl(ds);
			
		
	}
}
