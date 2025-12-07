import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Index
{
    final private List<File> files;
    final private List<File> folders;
    final private String path;

    public Index(List<File> files, List<File> folders, String path)
    {
        this.files = files;
        this.folders = folders;
        this.path = path;
    }

    public void create()
    {
        try
        {   System.out.println(path);
            FileWriter fw = new FileWriter(path);
            fw.append("<h2><b>Folders</b></h2>");
            fw.append("<table>");
            for (File f : folders)
            {
                fw.append("<tr><th><a href=\"").append(f.getName()).append("\\index.html").append("\">").append(f.getName()).append("</a></th></tr>");
            }
            fw.append("</table>");
            fw.append("<h2><b>Files</b></h2>");
            fw.append("<table>");
            for (File f : files)
            {

                if (f.getName().substring(f.getName().lastIndexOf('.')).compareTo(".html") == 0 && f.getName().compareTo("index.html") != 0)
                {
                    fw.append("<tr><th><a href=\"").append(f.getName()).append("\">").append(f.getName()).append("</a></th></tr>");
                }
            }
            fw.append("</table>");
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Index létrehozása sikertelen");
        }
    }
}
