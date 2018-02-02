<%@page import="java.net.InetAddress" %>
<%@page import="java.io.*" %>
<%@ page import="java.util.Map" %>
Hello from Java!<%out.println(InetAddress.getLocalHost().getHostName());%>
Output:<%
  try
  {
    Runtime r = Runtime.getRuntime();
    Map<String, String> env = System.getenv();
    String appHome = env.get("APP_HOME");
    String msg = "", emsg = "";
    String cmd = "sh "+appHome+"/clustered_app.sh";
    Process p = r.exec(cmd);
    p.waitFor();
    BufferedReader inOut =
      new BufferedReader(new InputStreamReader(p.getInputStream()));
    BufferedReader inErr =
      new BufferedReader(new InputStreamReader (p.getErrorStream()));
    while ((msg = inOut.readLine()) != null)
    {
      out.println("Out=" + msg);
    }
    p.destroy();
  } catch(Exception e) {
     out.println(e.toString());
  }
%>
