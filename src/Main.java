import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            if (args.length == 2 && args[1].charAt(0) != '-')
            {
                System.out.println("Add meg egy könyvtár elérési útját!");
                System.exit(1);
            }
        }
        File file = new File(args[0]);
        if (!file.isDirectory())
        {
            System.out.println("Add meg egy könyvtár elérési útját!");
            System.exit(2);
        }
        //System.out.println(new manageHTML(files[0]).getHTMLName());
        if (args.length == 2)
        {
            if (args[1].compareTo("-r") == 0)
            {
                deleteHTML(file, file);
            }
        }
        else
        {
            processFolder(file, file);
            indexFolders(file, file);
        }
    }

    public static void indexFolders(File folder,File root)
    {
        File[] allFiles = folder.listFiles();
        List<File> files = new ArrayList<>();
        List<File> folders = new ArrayList<>();

        if (allFiles != null)
        {
            for (File f : allFiles)
            {
                if (f.isDirectory())
                {
                    folders.add(f);
                    indexFolders(f,root);
                } else
                {
                    files.add(f);
                }
            }
        }
        if (folder.compareTo(root) == 0)
        {
            new Index(files, folders, folder.getPath()+"\\index.html",true).create();
        }
        else
        {
            new Index(files, folders, folder.getPath()+"\\index.html",false).create();
        }

    }

    public static void processFolder(File folder, File root)
    {
        File[] allFiles = folder.listFiles();
        List<File> files = new ArrayList<>();
        List<File> folders = new ArrayList<>();

        if (allFiles != null)
        {
            for (File f : allFiles)
            {
                if (f.isDirectory())
                {
                    folders.add(f);
                }
                else
                {
                    files.add(f);
                }
            }
            for (File f : folders)
            {
                processFolder(f, root);
            }
            if (!files.isEmpty())
            {
                if (files.size() > 1)
                {
                    new manageHTML(files.getFirst(), root).setNext(files.get(1)).create();
                    for (int i = 1; i < files.size() - 1; i++)
                    {
                        new manageHTML(files.get(i),root).setPrev(files.get(i-1)).setNext(files.get(i+1)).create();
                    }
                    new manageHTML(files.getLast(),root).setPrev(files.get(files.size()-2)).create();
                }
                else
                {
                    new manageHTML(files.getFirst(),root).create();
                }

            }
        }
    }
    public static void deleteHTML(File folder, File root)
    {
        File[] files = folder.listFiles();

        if (files != null)
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    deleteHTML(file, root);
                } else
                {
                    new manageHTML(file,root).remove();
                }
            }
        }
    }
}
