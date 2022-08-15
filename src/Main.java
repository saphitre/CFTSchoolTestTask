import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        mergeSort(args);
    }

    private static void mergeSort(String[] args) throws IOException {
        ArrayList<String> fileList = CommandLineParser.parseCommandLineArgs(args);
        FileManager fileManager=new FileManager(fileList,CommandLineParser.isInteger(),CommandLineParser.isAscendingSort());
        fileManager.fillArrayFromFile();
        if(fileManager.getArrayList()!=null){
            fileManager.writeOutputFile(MergeSort.mergeSort(fileManager.getArrayList(),0,fileManager.getArrayList().size()-1));
        }
        else fileManager.writeOutputFileStr(MergeSort.mergeSortStr(fileManager.getArrayListString(),0,fileManager.getArrayListString().size()-1));
    }
}