package controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteThread extends Thread{

    private String      filePath;
    private Controller  controller;
    private String      websiteString;
    public DeleteThread(Controller controller,String filePath, String websiteString) {
        this.controller = controller;
        this.filePath = filePath;
        this.websiteString = websiteString;

        start();
    }

    /**
     * Reading through whole file until websiteString is found and collects it and removes it.
     */
    @Override
    public void run() {
        try{


            File file = new File(filePath);
            List<String> stringOut = Files.lines(file.toPath())
                    .filter(allLines -> !allLines.contains(websiteString))
                    .collect(Collectors.toList());

            Files.write(file.toPath(),stringOut, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("SUCCESS");
        } catch (Exception e) {
            System.out.println("nope");
            e.printStackTrace();
        }
    }
}
