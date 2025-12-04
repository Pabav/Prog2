import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Add meg egy könyvtár elérési útját!");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.isDirectory())
        {
            System.out.println("Add meg egy könyvtár elérési útját!");
            System.exit(2);
        }
        //System.out.println(new manageHTML(files[0]).getHTMLName());
        processFolder(file);
    }
    public static void processFolder(File folder)
    {
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                processFolder(files[i]);
            }
            else
            {
                new manageHTML(files[i]).create();
                //new manageHTML(files[i]).delete();
            }
        }
    }
}
