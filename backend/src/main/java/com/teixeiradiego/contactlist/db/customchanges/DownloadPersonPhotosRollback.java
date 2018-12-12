package com.teixeiradiego.contactlist.db.customchanges;

import java.io.File;

import liquibase.change.custom.CustomTaskRollback;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.RollbackImpossibleException;

public class DownloadPersonPhotosRollback implements CustomTaskRollback {

	@Override
	public void rollback(Database database) throws CustomChangeException, RollbackImpossibleException {
		
		File photosFolder = new File("storage/people-photos/");
		
		if(photosFolder.exists()) {
			
			for(File fileToDelete: photosFolder.listFiles()) {
				fileToDelete.delete();
			}
			
		}
		
	}
    	
}
