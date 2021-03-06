package org.bardes.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.bardes.entities.Show;
import org.bardes.entities.Slide.Type;
import org.bardes.state.DB;
import org.bardes.state.DisplayPool;

@WebServlet("/fileupload")
public class Upload extends HttpServlet
{
	private enum FieldNames
	{
		cue,
		fade,
		file,
		display,
		type
	}
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String fileName = null;
			Double cueNum = null;
			Double fadeTime = null;
			Integer displayNum = null;
			
			DB db = DB.getInstance();
			Show show = db.getShow();

			Type type = Type.IMAGE;
			File savedFile;
			
			File uploadBaseDirectory = new File(show.getUploadDir());
			uploadBaseDirectory.mkdirs();
			
			ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(100, uploadBaseDirectory));
			
			@SuppressWarnings("unchecked")
			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem item : items)
			{
				try
				{
					FieldNames field = FieldNames.valueOf(item.getFieldName());
					switch (field)
					{
					case file:
						int n;
						byte buffer[] = new byte[8192];
						fileName = FilenameUtils.getName(item.getName());
						
						savedFile = new File(uploadBaseDirectory, fileName);
						FileOutputStream os = new FileOutputStream(savedFile);
						
						InputStream in = item.getInputStream();
						while ((n = in.read(buffer)) > 0)
						{
							os.write(buffer, 0, n);
						}
						
						os.close();
						in.close();
						break;

					case cue:
						String val = item.getString();
						cueNum = Double.valueOf(val);
						break;
						
					case display:
						val = item.getString();
						displayNum = Integer.parseInt(val);
						break;
						
					case fade:
						val = item.getString();
						fadeTime = Double.parseDouble(val);
						break;
						
					case type:
						try
						{
							type = Type.valueOf(item.getString());
						}
						catch (Exception ignore) {}
						break;
					}
				}
				catch (Exception ignore)
				{
				}
				
				if (!item.isInMemory())
					item.delete();
			}
			
			if (displayNum != null && cueNum != null)
			{
				db.saveImage(cueNum, displayNum, fileName, type, fadeTime);
				DisplayPool.refresh(db);
			}
			db.close();
			
			response.sendRedirect("operator.jsp");
		}
		catch (FileUploadException e)
		{
			throw new ServletException("Parsing file upload failed.", e);
		}
	}

}