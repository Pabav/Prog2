import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class manageHTML
{
    private final File path;
    private File next;
    private File prev;
    public List<String> accepted = List.of(".jpg",".jpeg",".png");
    public manageHTML(File path)
    {
        this.path = path;
    }

    public File getPath()
    {
        return path;
    }

    public File getNext()
    {
        return next;
    }

    public manageHTML setNext(File next)
    {
        this.next = next;
        return this;
    }

    public File getPrev()
    {
        return prev;
    }

    public manageHTML setPrev(File prev)
    {
        this.prev = prev;
        return this;
    }

    public String getHTMLName(File f)
    {
        return f.getParentFile().getPath()+"\\"+f.getName().substring(0,f.getName().lastIndexOf('.'))+".html";
    }

    public String getExtension()
    {
        return path.getName().substring(path.getName().lastIndexOf('.')).toLowerCase();
    }

    public manageHTML create()
    {
        try
        {
            if (accepted.contains(getExtension()))
            {
                FileWriter fw = new FileWriter(getHTMLName(path));
                fw.write("<html>" +
                        "<head>" +
                        "<title>Page Title</title>" +
                        "</head>" +
                        "<body>");
                System.out.println("created " + getHTMLName(path));

                if (prev != null)
                {
                    fw.append("<a href=\"").append(getHTMLName(prev).concat("\">Prev</a>"));
                }
                if (next != null)
                {
                    fw.append("<a href=\"").append(getHTMLName(next).concat("\">Next</a>"));
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
        return this;
    }
    public manageHTML remove()
    {
        if (path.exists() && getExtension().compareTo(".html") == 0)
        {
            if (path.delete())
            {
                System.out.println("removed " + path.getPath());
            }
        }
        return this;
    }
}
