package controller;

import java.io.File;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Threading {

    private Controller controller;
    /**
     * These classes are used to find whatever file you want inside of the System32 directory.
     * You can obviously choose to search through the whole computer by only typing C:/ or D:/(FOR EXAMPLE) but the search will stop
     * after the first file which name is equal to the string the user inputs, so you might not find the one you were looking for.
     */

    public Threading(Controller controller, String dirPath) {
        this.controller = controller;
        final File baseDir = new File("C:/Windows/System32"); // Choose what directory to start searching from.
        final FindFile ff = new FindFile(dirPath, baseDir, 6,controller);
        final long ini = System.currentTimeMillis();
        final File f = ff.find();
        final long end = System.currentTimeMillis();
        System.out.println(f + " " + (end - ini) + " ms");
    }


    public static class FindFile {

        private static final File   POISONPILL  = new File("");

        private static class RunnableDirSearch implements Runnable {
            private final BlockingQueue<File> dirQueue;
            private final BlockingQueue<File>   fileQueue;
            private final AtomicLong count;
            private final int                   num;

            public RunnableDirSearch(final BlockingQueue<File> dirQueue, final BlockingQueue<File> fileQueue, final AtomicLong count, final int num) {
                this.dirQueue = dirQueue;
                this.fileQueue = fileQueue;
                this.count = count;
                this.num = num;
            }

            @Override
            public void run() {
                try {
                    File dir = dirQueue.take();
                    while (dir != POISONPILL) {
                        final File[] fi = dir.listFiles();
                        //System.out.println(Arrays.toString(new String[]{Arrays.toString(fi) + "\n"})); // OUTPUT SEARCH LOG
                        if (fi != null) {
                            for (final File element : fi) {
                                if (element.isDirectory()) {
                                    count.incrementAndGet();
                                    dirQueue.put(element);
                                } else {
                                    fileQueue.put(element);
                                }
                            }
                        }
                        final long c = count.decrementAndGet();
                        if (c == 0) {
                            end();
                        }
                        dir = dirQueue.take();
                    }
                } catch (final InterruptedException ie) {
                    // file found or error
                }
            }

            private final void end() {
                try {
                    fileQueue.put(POISONPILL);
                } catch (final InterruptedException e) {
                    // empty
                }
                for (int i = 0; i < num; i++) {
                    try {
                        dirQueue.put(POISONPILL);
                    } catch (final InterruptedException e) {
                        // empty
                    }
                }
            }
        }

        private static class CallableFileSearch implements Callable<File> {
            private final BlockingQueue<File>   dirQueue;
            private final BlockingQueue<File>   fileQueue;
            private final String                name;
            private final int                   num;

            public CallableFileSearch(final BlockingQueue<File> dirQueue, final BlockingQueue<File> fileQueue, final String name, final int num) {
                this.dirQueue = dirQueue;
                this.fileQueue = fileQueue;
                this.name = name;
                this.num = num;
            }

            @Override
            public File call() throws Exception {
                File file = fileQueue.take();
                while (file != POISONPILL) {
                    final String filename = file.getName().toLowerCase();
                    final String lf = name.toLowerCase();
                    if (filename.equalsIgnoreCase(name) || filename.startsWith(lf) || filename.endsWith(lf)) {

                        end();
                        return file;
                    }
                    file = fileQueue.take();
                }
                return null;
            }

            private final void end() {
                for (int i = 0; i < num; i++) {
                    try {
                        dirQueue.put(POISONPILL);
                    } catch (final InterruptedException e) {
                        // empty
                    }
                }
            }
        }

        private final String        filename;
        private final File          baseDir;
        private final int           concurrency;
        private final AtomicLong    count;
        private Controller          controller;

        public FindFile(final String filename, final File baseDir, final int concurrency,Controller controller) {
            this.controller = controller;
            this.filename = filename;
            this.baseDir = baseDir;
            this.concurrency = concurrency;
            count = new AtomicLong(0);
        }

        public File find() {
            final ExecutorService ex = Executors.newFixedThreadPool(concurrency + 1);
            final BlockingQueue<File> dirQueue = new LinkedBlockingQueue<File>();
            final BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>(10000);
            for (int i = 0; i < concurrency; i++) {
                ex.submit(new RunnableDirSearch(dirQueue, fileQueue, count, concurrency));
            }
            count.incrementAndGet();
            dirQueue.add(baseDir);
            final Future<File> c = ex.submit(new CallableFileSearch(dirQueue, fileQueue, filename, concurrency));
            try {

                final File f = c.get();

                controller.outPutFoundFile(f.toString());

                return f;
            } catch (final Exception e) {
                return null;
            } finally {
                ex.shutdownNow();
            }
        }
    }

}
