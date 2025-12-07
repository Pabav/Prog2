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
                deleteHTML(file);
            }
        }
        else
        {
            processFolder(file);
        }
    }

    public static void createIndex(File folder)
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
                } else
                {
                    files.add(f);
                }
            }
        }


    }

    public static void processFolder(File folder)
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
                processFolder(f);
            }
            new Index(files, folders, folder.getPath()+"\\index.html").create();
            if (!files.isEmpty())
            {
                if (files.size() > 1)
                {
                    new manageHTML(files.getFirst()).setNext(files.get(1)).create();
                    for (int i = 1; i < files.size() - 1; i++)
                    {
                        new manageHTML(files.get(i)).setPrev(files.get(i-1)).setNext(files.get(i+1)).create();
                    }
                    new manageHTML(files.getLast()).setPrev(files.get(files.size()-2)).create();
                }
                else
                {
                    new manageHTML(files.getFirst()).create();
                }

            }
        }
    }
    public static void deleteHTML(File folder)
    {
        File[] files = folder.listFiles();

        if (files != null)
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    deleteHTML(file);
                } else
                {
                    new manageHTML(file).remove();
                }
            }
        }
    }
}
