package com.oracle.cloud.javase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VolumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String volume_name = request.getParameter("volume_name");
		String file_name = request.getParameter("file_name");
		String contents_add = request.getParameter("contents_add");
		try {
			write(volume_name + File.separator + file_name, contents_add);
			Map<String, String> contents = read(volume_name);
			request.setAttribute("volume_name", volume_name);
			request.setAttribute("contents_output", contents);
			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			out.println("<font color=red>couldn't write into the volume : " + e.getMessage() + "</font>");
			rd.include(request, response);
		}

	}

	private void write(String name, String contents) throws IOException {
		BufferedWriter bw = null;
		try {
			File f1 = new File(name);
			if (!f1.exists()) {
				f1.createNewFile();
			}

			bw = new BufferedWriter(new FileWriter(name, true));
			bw.write(contents);
			bw.newLine();
			bw.flush();
			System.out.println("Contents [" + contents + "] added to file-path[" + name + "] ");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		} finally { // always close the file
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
		}
	}

	private Map<String, String> read(String directoryName) throws IOException {
		
		System.out.println("Reading dir : " + directoryName);

		String line = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			File directory = new File(directoryName);
			if (directory.exists()) {

				// get all the files from a directory
				File[] fList = directory.listFiles();
				for (File file : fList) {
					if (file.isFile()) {
						/* FileReader reads text files in the default encoding */
						StringBuilder contents = new StringBuilder();
						FileReader fileReader = new FileReader(file);
						/* always wrap the FileReader in BufferedReader */
						BufferedReader bufferedReader = new BufferedReader(fileReader);

						while ((line = bufferedReader.readLine()) != null) {
							System.out.println(line);
							contents.append(line).append("\n");
						}

						/* always close the file after use */
						bufferedReader.close();
						map.put(file.getName(), contents.toString());
					}
					else {
						map.put(file.getName(), "Not a file");
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		return map;
	}
}
