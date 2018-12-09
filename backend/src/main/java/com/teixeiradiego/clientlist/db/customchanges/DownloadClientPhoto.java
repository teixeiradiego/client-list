package com.teixeiradiego.clientlist.db.customchanges;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.util.FileCopyUtils;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class DownloadClientPhoto implements CustomTaskChange {

	
	
	@Override
	public String getConfirmationMessage() {
		return "Client photos succesfully downloaded.";
	}

	@Override
	public void setUp() throws SetupException {
		
	}

	@Override
	public void setFileOpener(ResourceAccessor resourceAccessor) {
		
	}

	@Override
	public ValidationErrors validate(Database database) {		
		return null;
	}

	@Override
	public void execute(Database database) throws CustomChangeException {
		
		JdbcConnection connection = (JdbcConnection) database.getConnection();
		
		File photosFolder = new File("storage/client-photos/");
		photosFolder.mkdirs();
			         
    	ResultSet resultSet = null;
    	
		try {
			
			resultSet = connection.createStatement().executeQuery("Select * from client_list.client");
			    	
	    	while (resultSet.next()) {
				
	    		Integer id = resultSet.getInt("id");
	    		URL photoUrl = new URL(resultSet.getString("photo_url"));
	    		String newFilename  = null;
	 	    		
	    		try (
	    			InputStream photoStream = photoUrl.openStream()
	    		) {
	 
	    			// Supposes every photo is of type PNG
	    			newFilename = id + ".png";
	    			
	    			File file = new File(photosFolder, newFilename);
	    			file.createNewFile();
	    			
	    			try (
	    				// Copy the photo from the remote url to a local storage
	    				FileOutputStream fileOutputStream = new FileOutputStream(file);
	    			) {
	    				
	    				FileCopyUtils.copy(photoStream, fileOutputStream);    				
	    					    				
	    			}
	    			
	    		} catch (IOException e) {
	    			throw new CustomChangeException(e);
				};
					    			    		    			
			}
	    		    
		} catch (DatabaseException | SQLException e) {
			throw new CustomChangeException(e);
		} catch (MalformedURLException e) {
			throw new CustomChangeException(e);
		} finally {
			
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
