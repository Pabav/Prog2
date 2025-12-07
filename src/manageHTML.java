import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class manageHTML
{
    private final File path;
    private File next;
    private File prev;
    public List<String> accepted = List.of(".jpg",".jpeg",".png",".webp");
    public manageHTML(File path)
    {
        this.path = path;
    }

    public manageHTML setNext(File next)
    {
        this.next = next;
        return this;
    }

    public manageHTML setPrev(File prev)
    {
        this.prev = prev;
        return this;
    }

    public String getHTMLName()
    {
        return path.getParentFile().getPath()+"\\"+path.getName().substring(0,path.getName().lastIndexOf('.'))+".html";
    }

    public String getExtension()
    {
        return path.getName().substring(path.getName().lastIndexOf('.')).toLowerCase();
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
                        "<style>" +
                        "img {" +
                        "width: 50%;" +
                        "}" +
                        "</style>" +
                        "<title>Page Title</title>" +
                        "</head>" +
                        "<body>");
                System.out.println("created " + getHTMLName());
                if (prev != null)
                {
                    fw.append("<a href=\"").append(prev.getName().substring(0,prev.getName().lastIndexOf('.')).concat(".html")).append("\">Prev</a>");
                }
                if (next != null)
                {
                   fw.append("<a href=\"").append(next.getName().substring(0,next.getName().lastIndexOf('.')).concat(".html")).append("\">Next</a>");
                }
                fw.write("<img src=\"" + path.getName() + "\">" +
                        "</body>" +
                        "</html>");
                fw.close();
            }

        } catch (IOException e)
        {
            System.err.println("Fájlba írás sikertelen");
        }
    }
    public void remove()
    {
        if (path.exists() && getExtension().compareTo(".html") == 0)
        {
            if (path.delete())
            {
                System.out.println("removed " + path.getPath());
            }
        }
    }
}
