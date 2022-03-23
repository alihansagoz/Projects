import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

class Main {
    public static void insertionSort(int array[]) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = key;
        }
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void pigeonhole_sort(int arr[],
                                       int n)
    {
        int min = arr[0];
        int max = arr[0];
        int range, i, j, index;

        for (int a = 0; a < n; a++) {
            if (arr[a] > max)
                max = arr[a];
            if (arr[a] < min)
                min = arr[a];
        }

        range = max - min + 1;
        int[] phole = new int[range];
        Arrays.fill(phole, 0);

        for (i = 0; i < n; i++)
            phole[arr[i] - min]++;

        index = 0;

        for (j = 0; j < range; j++)
            while (phole[j]-- > 0)
                arr[index++] = j + min;
    }

    public static void countSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    static int[] take(int[] arr, int n) {
        return n > arr.length ? arr : Arrays.copyOf(arr, n);
    }

    public static void main(String args[]) throws IOException, InterruptedException {

        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        double[][] yAxis = new double[4][10];
        double[][] yAxis2 = new double[4][10];
        double[][] yAxis3 = new double[4][10];

        String line = "";
        int counter = 0;
        int[] FlowDurations = new int[251281];
        BufferedReader br = new BufferedReader(new FileReader("TrafficFlowDataset.csv"));
        while ((line = br.readLine()) != null){
            String[] values = line.split(",");
            if (!Objects.equals(values[7], " Flow Duration")){
                FlowDurations[counter] = Integer.parseInt(values[7]);
                counter++;
            }
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurations2 = take(FlowDurations,inputAxis[i]);
            double startTime = System.nanoTime();

            insertionSort(FlowDurations2);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis[0][i] = timeElapsed*10;
            System.out.println("Execution time for given data for insertion in milliseconds for: " +inputAxis[i] +"=" + timeElapsed / 1000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurations2 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations3 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations4 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations5 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations6 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations7 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations8 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations9 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations10 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations11 = take(FlowDurations,inputAxis[i]);
            double startTime = System.nanoTime();

            mergeSort(FlowDurations2,FlowDurations2.length);
            mergeSort(FlowDurations3,FlowDurations2.length);
            mergeSort(FlowDurations4,FlowDurations2.length);
            mergeSort(FlowDurations5,FlowDurations2.length);
            mergeSort(FlowDurations6,FlowDurations2.length);
            mergeSort(FlowDurations7,FlowDurations2.length);
            mergeSort(FlowDurations8,FlowDurations2.length);
            mergeSort(FlowDurations9,FlowDurations2.length);
            mergeSort(FlowDurations10,FlowDurations2.length);
            mergeSort(FlowDurations11,FlowDurations2.length);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis[1][i] = timeElapsed;
            System.out.println("Execution time for given data for merge in milliseconds: " + timeElapsed / 1000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurations2 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations3 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations4 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations5 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations6 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations7 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations8 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations9 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations10 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations11 = take(FlowDurations,inputAxis[i]);
            double startTime = System.nanoTime();

            pigeonhole_sort(FlowDurations2,FlowDurations2.length);
            pigeonhole_sort(FlowDurations3,FlowDurations2.length);
            pigeonhole_sort(FlowDurations4,FlowDurations2.length);
            pigeonhole_sort(FlowDurations5,FlowDurations2.length);
            pigeonhole_sort(FlowDurations6,FlowDurations2.length);
            pigeonhole_sort(FlowDurations7,FlowDurations2.length);
            pigeonhole_sort(FlowDurations8,FlowDurations2.length);
            pigeonhole_sort(FlowDurations9,FlowDurations2.length);
            pigeonhole_sort(FlowDurations10,FlowDurations2.length);
            pigeonhole_sort(FlowDurations11,FlowDurations2.length);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis[2][i] = timeElapsed;
            System.out.println("Execution time for given data for pigeonhole in milliseconds: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurations2 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations3 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations4 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations5 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations6 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations7 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations8 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations9 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations10 = take(FlowDurations,inputAxis[i]);
            int[] FlowDurations11 = take(FlowDurations,inputAxis[i]);

            double startTime = System.nanoTime();

            countSort(FlowDurations2);
            countSort(FlowDurations3);
            countSort(FlowDurations4);
            countSort(FlowDurations5);
            countSort(FlowDurations6);
            countSort(FlowDurations7);
            countSort(FlowDurations8);
            countSort(FlowDurations9);
            countSort(FlowDurations10);
            countSort(FlowDurations11);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis[3][i] = timeElapsed;
            System.out.println("Execution time for countsort in milliseconds: " + timeElapsed / 10000000);
        }

        showAndSaveChart("Given Data", inputAxis, yAxis);

        int[] FlowDurationsSorted = FlowDurations.clone();
        Arrays.sort(FlowDurationsSorted);

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsSorted2 = take(FlowDurationsSorted,inputAxis[i]);
            double startTime = System.nanoTime();

            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);
            insertionSort(FlowDurationsSorted2);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis2[0][i] = timeElapsed;
            System.out.println("Execution time in milliseconds for insertion: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsSorted2 = take(FlowDurationsSorted,inputAxis[i]);
            double startTime = System.nanoTime();

            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            mergeSort(FlowDurationsSorted2,FlowDurationsSorted2.length);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis2[1][i] = timeElapsed;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsSorted2 = take(FlowDurationsSorted,inputAxis[i]);
            double startTime = System.nanoTime();

            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);
            pigeonhole_sort(FlowDurationsSorted2,FlowDurationsSorted2.length);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis2[2][i] = timeElapsed;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsSorted2 = take(FlowDurationsSorted,inputAxis[i]);
            double startTime = System.nanoTime();

            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);
            countSort(FlowDurationsSorted2);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis2[3][i] = timeElapsed;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }
        showAndSaveChart("Sorted Data", inputAxis, yAxis2);

        int[] FlowDurationsReversed = Arrays.stream(FlowDurationsSorted).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsReversed2 = take(FlowDurationsReversed,inputAxis[i]);

            double startTime = System.nanoTime();

            insertionSort(FlowDurationsReversed2);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis3[0][i] = timeElapsed*10;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsReversed2 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed3 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed4 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed5 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed6 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed7 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed8 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed9 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed10 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed11 = take(FlowDurationsReversed,inputAxis[i]);

            double startTime = System.nanoTime();

            mergeSort(FlowDurationsReversed2,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed3,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed4,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed5,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed6,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed7,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed8,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed9,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed10,FlowDurationsReversed2.length);
            mergeSort(FlowDurationsReversed11,FlowDurationsReversed2.length);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis3[1][i] = timeElapsed;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsReversed2 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed3 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed4 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed5 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed6 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed7 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed8 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed9 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed10 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed11 = take(FlowDurationsReversed,inputAxis[i]);


            double startTime = System.nanoTime();

            pigeonhole_sort(FlowDurationsReversed2,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed3,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed4,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed5,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed6,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed7,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed8,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed9,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed10,FlowDurationsReversed2.length);
            pigeonhole_sort(FlowDurationsReversed11,FlowDurationsReversed2.length);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis3[2][i] = timeElapsed;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }

        for (int i =0; i<inputAxis.length; i++){
            int[] FlowDurationsReversed2 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed3 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed4 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed5 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed6 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed7 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed8 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed9 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed10 = take(FlowDurationsReversed,inputAxis[i]);
            int[] FlowDurationsReversed11 = take(FlowDurationsReversed,inputAxis[i]);


            double startTime = System.nanoTime();

            countSort(FlowDurationsReversed2);
            countSort(FlowDurationsReversed3);
            countSort(FlowDurationsReversed4);
            countSort(FlowDurationsReversed5);
            countSort(FlowDurationsReversed6);
            countSort(FlowDurationsReversed7);
            countSort(FlowDurationsReversed8);
            countSort(FlowDurationsReversed9);
            countSort(FlowDurationsReversed10);
            countSort(FlowDurationsReversed11);

            double endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;
            yAxis3[3][i] = timeElapsed/10;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 10000000);
        }
        showAndSaveChart("Reversed Data", inputAxis, yAxis3);

    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
        chart.addSeries("Merge Sort", doubleX, yAxis[1]);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxis[2]);
        chart.addSeries("Counting Sort", doubleX, yAxis[3]);
        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
