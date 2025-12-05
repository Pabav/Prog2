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
            if (args[1].toLowerCase().compareTo("-r") == 0)
            {
                deleteHTML(file);
            }
        }
        else
        {
            processFolder(file);
        }
    }

    public static void processFile(int i, File... file)
    {
        if (file.length == 2)
        {
            if (i == 0)
            {
                if (file[0].isDirectory())
                {
                    processFolder(file[0]);
                }
                else
                {
                    new manageHTML(file[0]).setNext(file[1]).create();
                }
            }
            else
            {
                if (file[0].isDirectory())
                {
                    processFolder(file[0]);
                }
                else
                {
                    new manageHTML(file[0]).setPrev(file[1]).create();
                }
            }
        }
        if (file.length == 3)
        {
            if (file[0].isDirectory())
            {
                processFolder(file[0]);
            } else
            {
                new manageHTML(file[0]).setPrev(file[1]).setNext(file[2]).create();
            }
        }
    }

    public static void processFolder(File folder)
    {
        File[] files = folder.listFiles();
        if (files != null)
        {
            if (files.length > 1)
            {
                processFile(0, files[0], files[1]);

                for (int i = 1; i < files.length - 1; i++)
                {
                    System.out.println(files[i].getName());
                    processFile(i, files[i], files[i - 1], files[i + 1]);
                }

                processFile(files.length, files[files.length - 1], files[files.length - 2]);
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
