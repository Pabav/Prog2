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

    public String getExtension(File f)
    {
        return f.getName().substring(f.getName().lastIndexOf('.')).toLowerCase();
    }

    public String removeExtension(File f)
    {
        return f.getName().substring(0,f.getName().lastIndexOf('.'));
    }

    public void create()
    {
        try
        {
            if (accepted.contains(getExtension(path)))
            {
                FileWriter fw = new FileWriter(getHTMLName());
                fw.write("<html>" +
                        "<head>" +
                        "<title>" + path.getName() + "</title>" +
                        "<style>" + "img {" + "width:500px;" + "}" +
                        "</style>" +
                        "<title>"+ removeExtension(path) +"</title>" +
                        "</head>" +
                        "<body>");
                System.out.println("created " + getHTMLName());

                fw.append("<h2>").append(path.getName()).append("</h2>");

                fw.append("<table><tr>");

                fw.append("<th style=\"width:10%\"><p style=\"text-align:center;\">");
                fw.append("<a href=\"index.html\">Up</a>");
                fw.append("</p></th>");

                fw.append("<th style=\"width:10%\">");
                if (prev != null && accepted.contains(getExtension(prev)))
                {
                    fw.append("<p style=\"text-align:center;\">");
                    fw.append("<a href=\"").append(removeExtension(prev).concat(".html")).append("\">Prev</a>");

                }
                fw.append("</p></th>");
                fw.append("<th style=\"width:10%\">");
                if (next != null && accepted.contains(getExtension(next)))
                {
                    fw.append("<p style=\"text-align:center;\">");
                    fw.append("<a href=\"").append(removeExtension(next).concat(".html")).append("\">Next</a>");

                }
                fw.append("</p></th>");
                fw.append("</tr></table>");
                fw.append("<img src=\"").append(path.getName()).append("\"></body></html>");

                fw.close();
            }

        } catch (IOException e)
        {
            System.err.println("Fájlba írás sikertelen");
        }
    }
    public void remove()
    {
        if (path.exists() && getExtension(path).compareTo(".html") == 0)
        {
            if (path.delete())
            {
                System.out.println("removed " + path.getPath());
            }
        }
    }
}
