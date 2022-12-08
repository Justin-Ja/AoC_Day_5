import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.io.IOException;


public class Load {
    private Path path;

    public Load(String fileName, String location){
        path = FileSystems.getDefault().getPath(location, fileName);
    }

    /**
     * Reads from a file and stores it in a string
     * @return lines
     * @exception IOException
     */
    public String readTheFile(){
        String lines = null;
        try{
            lines = Files.readString(path);
        } catch (IOException e){
            System.out.println(e.toString());
        }

     return lines;
       }
    }
