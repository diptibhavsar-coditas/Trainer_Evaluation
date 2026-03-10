package MyPackage;

import java.util.concurrent.*;
import java.util.Random;

public class ParallelReportSystem {

	public static void main(String[] args) throws InterruptedException {

		// Total reports
		int totalReports = 8;

		// Fixed Thread Pool of size 2
		ExecutorService executor = Executors.newFixedThreadPool(2);

		// CountDownLatch initialized with 8
		CountDownLatch latch = new CountDownLatch(totalReports);

		System.out.println("Starting Report Generation...\n");

		// Creating 8 report tasks
		for (int i = 1; i <= totalReports; i++) {
			String reportId = "Report-" + i;
			executor.execute(new ReportTask(reportId, latch));
		}

		
		
		
		// Main thread waiting
		System.out.println("Main thread is waiting for all reports to complete...\n");
		latch.await(); // Main thread blocked here

		System.out.println("\nAll reports generated. Dashboard is ready.");

		executor.shutdown();
	}
}

class ReportTask implements Runnable {

	private String reportId;
	private CountDownLatch latch;

	public ReportTask(String reportId, CountDownLatch latch) {
		this.reportId = reportId;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			System.out.println(reportId + " started by " + Thread.currentThread().getName());

			Thread.sleep(2000);

			System.out.println(reportId + " completed by " + Thread.currentThread().getName());

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			latch.countDown(); // Decrease latch count
		}
	}
}