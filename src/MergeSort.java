import java.util.ArrayList;

public class MergeSort {
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> in, int lowBound, int upperBound){
        if(in.size()==0)
            throw new RuntimeException("Отсутствуют входные данные");
        if(lowBound==upperBound){
            return in;
        }
        else{
            int mid = (lowBound+upperBound)/2;
            mergeSort(in,lowBound,mid);
            mergeSort(in,mid+1,upperBound);
            mergeSorting(in,lowBound,mid,upperBound);
        }
        return in;
    }

    private static void mergeSorting(ArrayList<Integer> in, int lowBound,int mid, int upperBound){
        ArrayList<Integer> tmp= new ArrayList<>();
        int lowPos=lowBound, upperPos=mid+1;
        while (lowPos<=mid & upperPos<=upperBound){
            if (in.get(lowPos)<=in.get(upperPos))
                tmp.add(in.get(lowPos++));
            else
                tmp.add(in.get(upperPos++));
        }
        while (lowPos<=mid){
           tmp.add(in.get(lowPos++));
        }
        while (upperPos<=upperBound){
            tmp.add(in.get(upperPos++));
        }

        for (int i=0;i<upperBound-lowBound+1;i++){
            in.set((lowBound+i),tmp.get(i));
        }
    }

    public static ArrayList<String> mergeSortStr(ArrayList<String> in, int lowBound, int upperBound){
        if(in.size()==0)
            throw new RuntimeException("Отсутствуют входные данные");
        if(lowBound==upperBound){
            return in;
        }
        else{
            int mid = (lowBound+upperBound)/2;
            mergeSortStr(in,lowBound,mid);
            mergeSortStr(in,mid+1,upperBound);
            mergeSortingStr(in,lowBound,mid,upperBound);
        }
        return in;
    }

    private static void mergeSortingStr(ArrayList<String> in, int lowBound,int mid, int upperBound){
        ArrayList<String> tmp= new ArrayList<>();
        int lowPos=lowBound, upperPos=mid+1;
        while (lowPos<=mid & upperPos<=upperBound){
            if (in.get(lowPos).compareTo(in.get(upperPos))<0)
                tmp.add(in.get(lowPos++));
            else
                tmp.add(in.get(upperPos++));
        }
        while (lowPos<=mid){
            tmp.add(in.get(lowPos++));
        }
        while (upperPos<=upperBound){
            tmp.add(in.get(upperPos++));
        }

        for (int i=0;i<upperBound-lowBound+1;i++){
            in.set((lowBound+i),tmp.get(i));
        }
    }
}

