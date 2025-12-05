import java.io.File;

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

    public static void processFile(File... files)
    {

    }

    public static void processFolder(File folder)
    {
        File[] files = folder.listFiles();
        if (files != null)
            if (files.length > 1)
            {
                if (files[0].isDirectory())
                {
                    processFolder(files[0]);
                } else
                {
                    new manageHTML(files[0]).setNext(files[1]).create();
                }
                for (int i = 1; i < files.length - 1; i++)
                {
                    if (files[i].isDirectory())
                    {
                        processFolder(files[i]);
                    } else
                    {
                        new manageHTML(files[i]).setPrev(files[i - 1]).setNext(files[i + 1]).create();
                    }
                }
                if (files[files.length - 1].isDirectory())
                {
                    processFolder(files[files.length - 1]);
                } else
                {
                    new manageHTML(files[files.length - 1]).setPrev(files[files.length - 2]).create();
                }
            }
    }
    public static void deleteHTML(File folder)
    {
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                deleteHTML(files[i]);
            }
            else
            {
                new manageHTML(files[i]).remove();
            }
        }
    }
}
