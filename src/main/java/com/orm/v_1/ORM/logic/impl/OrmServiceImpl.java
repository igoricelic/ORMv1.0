package com.orm.v_1.ORM.logic.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.exceptions.TableNotFoundException;
import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.logic.ModelBuilderService;
import com.orm.v_1.ORM.logic.ORM;
import com.orm.v_1.ORM.model.Database;

public class OrmServiceImpl implements ORM {
	private static final Logger logger = Logger.getLogger( ORM.class.getName() );
	
	private Database database;
	private Connection connection;
	private ModelBuilderService modelBuilder;
	// DB Parameters:
	private String host, databaseName, username, password;
	private int port;

	public OrmServiceImpl(String host, int port, String username, String password)
			throws ClassNotFoundException, SQLException {
		// Model builder
		modelBuilder = new ModelBuilderServiceImpl();
		// DB Parameters
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	@Override
	public DatabaseDao generateMapping(String databaseName, List<Class<?>> entities, boolean createTables)
			throws NoSuchFieldException, SecurityException, SQLException, ClassNotFoundException, TableNotFoundException, NotSuportTypeException {
		this.databaseName = databaseName;
		this.connection = getConnection();
		this.connection.setCatalog(databaseName);

		this.database = modelBuilder.buildModel(this.databaseName, entities);
		if (createTables) createTables();
		controlEntitiesInDb(this.connection, database);

		return new DatabaseDaoImplementation(connection, database);
	}

	private Boolean createTables() throws SQLException {
		Statement statement = this.connection.createStatement();
		List<String> queries = this.database.createQuery();
		for(String query: queries) {
			logger.info(">>> " + query);
			statement.executeUpdate(query);
		}
		return true;
	}

	private Boolean controlEntitiesInDb(Connection connection, Database db) throws SQLException, TableNotFoundException {
		Statement st = (Statement) connection.createStatement();
		ResultSet rs = st.executeQuery("show tables;");
		List<String> tableNames = this.database.getTableNames();
		List<String> tablesInDb = new ArrayList<String>();
		
		while (rs.next()) tablesInDb.add(rs.getString(1));
		for(String tableName: tableNames) {
			if(!tablesInDb.contains(tableName)) throw new TableNotFoundException("Table " + tableName + " not found in database!");
		}

		return true;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// Setup the connection with the DB
		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionPath = "jdbc:mysql://%s:%d/%s?user=%s&password=%s";
		Connection connection = DriverManager
				.getConnection(String.format(connectionPath, host, port, databaseName, username, password));
		return connection;
	}

	@Override
	public void mapping(String directoryPath) {
		File packagee = new File(directoryPath);
		
		
	}

}
