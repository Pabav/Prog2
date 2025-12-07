import java.io.File;

public class Index
{
    private File[] files;
    private File[] folders;
    private File path;

    public Index(File[] files, File[] folders, File path)
    {
        this.files = files;
        this.folders = folders;
        this.path = path;
    }
}
