package ac.echo.practice.utils;

import org.bukkit.Bukkit;

public class FileUtils {
    public static void writeToTestFile(String string){
        Bukkit.getLogger().info(string);
    }

    public static String readFromTestFile(){
        // try {
        //     return Files.readString(Path.of("test.txt"));
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // return "";

        return "";
    }
}
