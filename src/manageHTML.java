import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class manageHTML
{
    private File path;
    //private File prev;
    //private File next;
    public List<String> accepted = List.of(".jpg",".jpeg",".png");
    public manageHTML(File path)
    {
        this.path = path;
    }

    public File getPath()
    {
        return path;
    }

    public String getHTMLName()
    {
        return path.getParentFile().getPath()+"\\"+path.getName().substring(0,path.getName().lastIndexOf('.'))+".html";
    }

    public String getExtension()
    {
        return path.getName().substring(path.getName().lastIndexOf('.'),path.getName().length());
    }

    public void create()
    {
        try
        {
            if (accepted.contains(getExtension()))
            {
                FileWriter fw = new FileWriter(getHTMLName());
                fw.write("<html>" +
                        "<head>" +
                        "<title>Page Title</title>" +
                        "</head>" +
                        "<body>" +
                        //"<a href=\""+  +"\">link text</a>" +
                        "<img src=\"" + path.getName() + "\">" +
                        "</body>" +
                        "</html>");
                fw.close();
            }

        } catch (IOException e)
        {
            System.err.println("Fájlba írás sikertelen");
        }
    }
    public void delete()
    {
        if (path.exists() && getExtension().equals(".html"))
        {
            path.delete();
        }
    }
}
