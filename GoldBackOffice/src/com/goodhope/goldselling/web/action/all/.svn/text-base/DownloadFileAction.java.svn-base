package com.goodhope.goldselling.web.action.all;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction extends ActionSupport {

	private static final long serialVersionUID = -5414872711622556033L;
	private static final Logger LOG = Logger.getLogger(DownloadFileAction.class);

	public InputStream getDownloadFile() {
		File file = new File("");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			LOG.error(e, e);
		}
		return fis;
	}

	public String execute() throws Exception {
		return ActionSupport.SUCCESS;
	}

}
