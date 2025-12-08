import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Index
{
    final private List<File> files;
    final private List<File> folders;
    final private String path;
    final private boolean isRoot;
    public List<String> accepted = List.of(".jpg",".jpeg",".png",".webp");

    public Index(List<File> files, List<File> folders, String path, boolean isRoot)
    {
        this.files = files;
        this.folders = folders;
        this.path = path;
        this.isRoot = isRoot;
    }

    public String getExtension(File f)
    {
        return f.getName().substring(f.getName().lastIndexOf('.')).toLowerCase();
    }

    public String removeExtension(String f)
    {
        return f.substring(0,f.lastIndexOf('.'));
    }

    public void create()
    {
        try
        {   System.out.println(path);
            FileWriter fw = new FileWriter(path);
            if (!isRoot)
            {
                fw.append("<a href=\"..\\index.html\">Up</a>");
//                int layer = 0;
//                File rootPath = new File(path);
//                while (rootPath.getName().compareTo(root.getName()) != 0)
//                {
//                    rootPath = rootPath.getParentFile();
//                    layer++;
//                }
//                StringBuilder rootName = new StringBuilder();
//                rootName.append(".");
//                for (int i = 0; i < layer; i++)
//                {
//                    rootName.append(".\\");
//                }
//                rootName.toString();
//
//                fw.append("<a href=\"").append(rootName).append("\\index.html\">Home</a>");
            }
            fw.append("<h2><b>Folders</b></h2>");
            fw.append("<table>");
            for (File f : folders)
            {
                fw.append("<tr><th><a href=\"").append(f.getName()).append("\\index.html").append("\">").append(f.getName()).append("</a></th></tr>");
            }
            fw.append("</table>");
            fw.append("<h2><b>Files</b></h2>");
            fw.append("<table>");
            fw.append("<th style=\"width:10%\"><p style=\"text-align:center;\">");

            for (File f : files)
            {

                if (accepted.contains(getExtension(f)) && f.getName().compareTo("index.html") != 0)
                {
                    fw.append("<tr><th><a href=\"").append(removeExtension(f.getName())).append(".html\">").append(f.getName()).append("</a></th></tr>");
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
