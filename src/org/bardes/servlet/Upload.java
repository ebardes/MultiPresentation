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
import org.bardes.state.DB;
import org.bardes.state.DisplayPool;

@WebServlet("/fileupload")
public class Upload extends HttpServlet
{
	private enum FieldNames
	{
		cue,
		file,
		display
	}
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String fileName = null;
			Double cueNum = null;
			Integer displayNum = null;
			DB db = new DB();
			Show show = db.getShow();
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
				db.saveImage(cueNum, displayNum, fileName);
				DisplayPool.refresh();
			}
			
			response.sendRedirect("operator.jsp");
		}
		catch (FileUploadException e)
		{
			throw new ServletException("Parsing file upload failed.", e);
		}
	}

}