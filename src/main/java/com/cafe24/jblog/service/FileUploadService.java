package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    private static final String SAVE_PATH = "/blog-uploads/";
    private static final String PREFIX_URL = "/logo/images/";
    public String restore( MultipartFile multipartFile ) {
	String url = "";

	if( multipartFile == null || "".equals(multipartFile.getOriginalFilename()) ){
	    return url;
	}

	try {
	    String originFileName = multipartFile.getOriginalFilename();
	    String extName = originFileName.substring( originFileName.lastIndexOf( "." ), originFileName.length() );
//	    Long size = multipartFile.getSize();

	    String saveFileName = genSaveFileName( extName );

//	    System.out.println( "######### " + originFileName );
//	    System.out.println( "######### " + extName );
//	    System.out.println( "######### " + size );
//	    System.out.println( "######### " + saveFileName );

	    writeFile( multipartFile, saveFileName );
	    
	    url = PREFIX_URL + saveFileName;
	    
	} catch ( IOException ex ) {
	    throw new RuntimeException( ex );
	}
	return url;
    }

    private void writeFile( MultipartFile multipartFile, String saveFileName ) throws IOException {
	byte[] fileData = multipartFile.getBytes();
	FileOutputStream fos = new FileOutputStream( SAVE_PATH + "/" + saveFileName );
	fos.write(fileData);
	fos.close();
    }

    private String genSaveFileName( String extName ) {
	String filename = "";

	Calendar calendar = Calendar.getInstance();
	filename += calendar.get( Calendar.YEAR );
	filename += calendar.get( Calendar.MONTH );
	filename += calendar.get( Calendar.DATE );
	filename += calendar.get( Calendar.HOUR );
	filename += calendar.get( Calendar.MINUTE );
	filename += calendar.get( Calendar.SECOND );
	filename += calendar.get( Calendar.MILLISECOND );
	filename += extName;

	return filename;
    }
}
